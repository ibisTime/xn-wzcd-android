package com.cdkj.wzcd.module.tool.gps_install;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.CodeModel;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adpter.adapter.GpsInstallAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityGpsInfoInputBinding;
import com.cdkj.wzcd.model.GpsInstallModel;
import com.cdkj.wzcd.model.GpsModel;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.util.DatePickerHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * GPS 安装回录信息填写
 * Created by cdkj on 2018/5/30.
 */

public class GPSInstallInfoActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivityGpsInfoInputBinding mBinding;

    private GpsInstallAdapter mAdapter;
    private List<GpsInstallModel> mList = new ArrayList<>();

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, GPSInstallInfoActivity.class);
        intent.putExtra(DATA_SIGN, code);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_gps_info_input, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("安装回录");

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getGPS();

        initListener();
        initListAdapter();
    }

    private void initListener() {

        mBinding.llAdd.setOnClickListener(v -> {
            GPSInfoAddActivity.open(this);
        });

        mBinding.myNlDateTime.setOnClickListener(view -> {
            new DatePickerHelper().build(this).getDate(mBinding.myNlDateTime, true, true,  true, false, false, false);
        });

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                request();
            }
        });

    }

    public void initListAdapter() {
        mAdapter = new GpsInstallAdapter(mList);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
//            CreditUserModel model = );
            GPSInfoAddActivity.open(this,mAdapter.getItem(position),position);
        });

        mBinding.rvGps.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvGps.setAdapter(mAdapter);
    }

    public void getGPS(){
        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("code", code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNode("632146", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<NodeListModel>(this) {
            @Override
            protected void onSuccess(NodeListModel data, String SucMessage) {

                setView(data);
                getGpsRequest();
            }

            @Override
            protected void onFinish() {
//                disMissLoading();
            }
        });
    }

    private void setView(NodeListModel data) {
        mBinding.myNlName.setText(data.getCustomerName());
        mBinding.myNlCode.setText(data.getCode());
        mBinding.myNlBank.setText(data.getLoanBankName());


        if (data.getBudgetOrderGpsList() != null || data.getBudgetOrderGpsList().size() !=0){
            mList.clear();
//            for (NodeListModel.BudgetOrderGpsListBean bean : data.getBudgetOrderGpsList()){
//
//                GpsInstallModel model = new GpsInstallModel();
//                model.setCode(bean.getCode());
//                model.setGpsDevNo(bean.getGpsDevNo());
//                model.setAzLocation(bean.getAzLocation());
//                model.setAzUser(bean.getAzUser());
//                model.setAzDatetime(bean.getAzDatetime());
//
//                mList.add(model);
//            }
            mAdapter.notifyDataSetChanged();
        }

    }


    public void getGpsRequest() {

        Map<String, String> map = RetrofitUtils.getRequestMap();
        map.put("applyStatus", "1");
        map.put("useStatus", "0");
        map.put("applyUser", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getGpsList("632707", StringUtils.getJsonToString(map));

//        showLoadingDialog();

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

    }


    private boolean check(){

        // 设备号
        if (TextUtils.isEmpty(mBinding.mySlCode.getDataKey())){
            return false;
        }

        // 安装位置
        if (TextUtils.isEmpty(mBinding.myElLocation.check())){
            return false;
        }

        // 安装时间
        if (TextUtils.isEmpty(mBinding.myNlDateTime.check())){
            return false;
        }

        // 安装人员
        if (TextUtils.isEmpty(mBinding.myElUser.check())){
            return false;
        }
        return true;
    }

    private void request(){
        Map<String, Object> map = new HashMap<>();

        map.put("code", code);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("gpsAzList", mList);

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632126", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(GPSInstallInfoActivity.this, "保存成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(GPSInstallInfoActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}