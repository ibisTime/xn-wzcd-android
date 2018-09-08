package com.cdkj.wzcd.module.work.join_approval;

import android.content.Context;
import android.content.DialogInterface;
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
import com.cdkj.wzcd.databinding.ActivityBudgetGpsAddBinding;
import com.cdkj.wzcd.model.GpsInstallModel;
import com.cdkj.wzcd.model.GpsInstallReplaceModel;
import com.cdkj.wzcd.model.GpsModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.view.interfaces.MySelectInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.az_location;

/**
 * 预算单添加GPS
 * Created by cdkj on 2018/5/30.
 */

public class GPSAddActivity extends AbsBaseLoadActivity {

    private ActivityBudgetGpsAddBinding mBinding;

    private int position;
    private GpsInstallModel model;

    // 被选择的Gps
    private GpsModel mGpsModel;

    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, GPSAddActivity.class);
        context.startActivity(intent);
    }

    public static void open(Context context, GpsInstallModel model, int position) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, GPSAddActivity.class);
        intent.putExtra(DATA_SIGN, model);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_budget_gps_add, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("添加GPS");

        if (getIntent() != null && getIntent().getExtras() != null) {
            model = (GpsInstallModel) getIntent().getSerializableExtra(DATA_SIGN);
            position = getIntent().getIntExtra("position", 0);

        }

        initListener();
        getGpsRequest();

    }


    private void initListener() {

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()) {
                try {
                    // 组装数据
                    GpsInstallModel model = new GpsInstallModel();
                    model.setCode(mBinding.mySlCode.getDataKey());
                    model.setAzLocation(mBinding.mySlAzLocation.getDataKey());
                    if (TextUtils.equals("9", mBinding.mySlAzLocation.getDataKey())) {
                        model.setAzLocationRemark(mBinding.myElAzLocation.getText());
                    }
                    model.setGpsDevNo(mBinding.mySlCode.getDataValue());

                    if (mGpsModel != null) {
                        model.setGpsType(mGpsModel.getGpsType());
                    }

                    // 发送数据
                    if (getIntent() != null && getIntent().getExtras() != null) {
                        // 替换
                        EventBus.getDefault().post(new GpsInstallReplaceModel().setLocation(position).setGpsInstallModel(model));
                        finish();
                    } else {
                        // 发送数据
                        EventBus.getDefault().post(model);
                        finish();
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

    }

    private boolean check() {
        // 设备号
        if (TextUtils.isEmpty(mBinding.mySlCode.getDataKey())) {
            return false;
        }

        // 安装位置
        if (mBinding.mySlAzLocation.check()) {
            return false;
        } else {
            //选择了  判断选择的是不是  其他
            if (TextUtils.equals("9", mBinding.mySlAzLocation.getDataKey())) {
                if (mBinding.myElAzLocation.check()) {
//                    UITipDialog.showInfo(this, "请输入安装位置");
                    return false;
                }
            }
        }

        // 安装时间
//        if (TextUtils.isEmpty(mBinding.myNlDateTime.check())){
//            return false;
//        }

        // 安装人员
//        if (TextUtils.isEmpty(mBinding.myElUser.check())){
//            return false;
//        }

        return true;
    }

    public void getGpsRequest() {

        Map<String, String> map = RetrofitUtils.getRequestMap();
        map.put("applyStatus", "2");
        map.put("useStatus", "0");
        map.put("applyUser", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getGpsList("632707", StringUtils.getJsonToString(map));

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<GpsModel>(this) {

            @Override
            protected void onSuccess(List<GpsModel> data, String SucMessage) {
                if (data == null)
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
        if (model != null) {
            dictionaryList.add(new DataDictionary().setDkey(model.getCode()).setDvalue(model.getGpsDevNo()));
        }

        for (GpsModel dataItem : data) {
            dictionaryList.add(new DataDictionary().setDkey(dataItem.getCode()).setDvalue(dataItem.getGpsDevNo()));
        }

        mBinding.mySlCode.setData(dictionaryList, (dialog, which) -> {
            mGpsModel = data.get(which);
        });

        List<DataDictionary> listByParentKey = DataDictionaryHelper.getListByParentKey(az_location);
        mBinding.mySlAzLocation.setData(listByParentKey, new MySelectInterface() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataDictionary dictionary = listByParentKey.get(which);
                if (TextUtils.equals(dictionary.getDkey(), "9")) {
                    //选择的是其他
                    mBinding.myElAzLocation.setVisibility(View.VISIBLE);
                } else {
                    mBinding.myElAzLocation.setVisibility(View.GONE);
                }
            }
        });

        if (model != null) {
            setView(dictionaryList);
        }

    }

    private void setView(List<DataDictionary> dictionaryList) {

        for (DataDictionary dataDictionary : dictionaryList) {
            if (TextUtils.equals(dataDictionary.getDkey(), model.getCode())) {
                mBinding.mySlCode.setTextAndKey(dataDictionary.getDkey(), dataDictionary.getDvalue());
            }
        }
        mBinding.mySlAzLocation.setContentByKey(model.getAzLocation());

        if (TextUtils.equals("9", model.getAzLocation())) {
            mBinding.myElAzLocation.setVisibility(View.VISIBLE);
            mBinding.myElAzLocation.setText(model.getAzLocationRemark());
        }
//        mBinding.myNlDateTime.setText(model.getAzDatetime());
//        mBinding.myElUser.setText(model.getAzUser());
//        mBinding.myElRemark.setText(model.getRemark());


    }


}
