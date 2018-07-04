package com.cdkj.wzcd.module.tool.gps_install;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityGpsInfoAddBinding;
import com.cdkj.wzcd.model.GpsInstallModel;
import com.cdkj.wzcd.model.GpsInstallReplaceModel;
import com.cdkj.wzcd.model.GpsModel;
import com.cdkj.wzcd.util.DatePickerHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * 添加GPS
 * Created by cdkj on 2018/5/30.
 */

public class GPSInfoAddActivity extends AbsBaseLoadActivity {

    private ActivityGpsInfoAddBinding mBinding;

    private int position;
    private GpsInstallModel model;

    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, GPSInfoAddActivity.class);
        context.startActivity(intent);
    }

    public static void open(Context context, GpsInstallModel model, int position) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, GPSInfoAddActivity.class);
        intent.putExtra(DATA_SIGN, model);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_gps_info_add, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("添加GPS");

        if (getIntent() != null && getIntent().getExtras() != null){
            model = (GpsInstallModel) getIntent().getSerializableExtra(DATA_SIGN);
            position = getIntent().getIntExtra("position", 0);

        }

        initListener();
        getGpsRequest();

    }


    private void initListener() {
        mBinding.myNlDateTime.setOnClickListener(view -> {
            new DatePickerHelper().build(this).getDate(mBinding.myNlDateTime, true, true,  true, false, false, false);
        });

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                try{
                    // 组装数据
                    GpsInstallModel model = new GpsInstallModel();
                    model.setCode(mBinding.mySlCode.getDataKey());
                    model.setAzDatetime(mBinding.myNlDateTime.getText());
                    model.setAzLocation(mBinding.myElLocation.getText());
                    model.setAzUser(mBinding.myElUser.getText());
                    model.setGpsDevNo(mBinding.mySlCode.getDataValue());
                    model.setRemark(mBinding.myElRemark.getText());

                    // 发送数据
                    if (getIntent() != null && getIntent().getExtras() != null){
                        // 替换
                        EventBus.getDefault().post(new GpsInstallReplaceModel().setLocation(position).setGpsInstallModel(model));
                        finish();
                    }else {
                        // 发送数据
                        EventBus.getDefault().post(model);
                        finish();
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

    }

    private boolean check(){
        // 设备号
        if (TextUtils.isEmpty(mBinding.mySlCode.getDataKey())){
            return false;
        }

        // 安装位置
        if (mBinding.myElLocation.check()){
            return false;
        }

        // 安装时间
        if (TextUtils.isEmpty(mBinding.myNlDateTime.check())){
            return false;
        }

        // 安装人员
        if (mBinding.myElUser.check()){
            return false;
        }

        return true;
    }

    public void getGpsRequest() {

        Map<String, String> map = RetrofitUtils.getRequestMap();
        map.put("applyStatus", "1");
        map.put("useStatus", "0");
        map.put("applyUser", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getGpsList("632707", StringUtils.getJsonToString(map));

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<GpsModel>(this) {

            @Override
            protected void onSuccess(List<GpsModel> data, String SucMessage) {
                if (data == null || data.size() == 0)
                    return;

                initCustomView(data);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void initCustomView(List<GpsModel> data) {

        List<DataDictionary> dictionaryList = new ArrayList<>();

        for (GpsModel model : data){
            dictionaryList.add(new DataDictionary().setDkey(model.getCode()).setDvalue(model.getGpsDevNo()));
        }

        mBinding.mySlCode.setData(dictionaryList, null);

        if (model != null){
            setView(dictionaryList);
        }

    }

    private void setView(List<DataDictionary> dictionaryList) {

        for (DataDictionary dataDictionary : dictionaryList){
            if (TextUtils.equals(dataDictionary.getDkey(), model.getCode())){
                mBinding.mySlCode.setTextAndKey(model.getCode(), dataDictionary.getDvalue());
            }
        }

        mBinding.myElLocation.setText(model.getAzLocation());
        mBinding.myNlDateTime.setText(model.getAzDatetime());
        mBinding.myElUser.setText(model.getAzUser());
        mBinding.myElRemark.setText(model.getRemark());


    }


}
