package com.cdkj.wzcd.module.tool.gps;

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
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityGpsApplyBinding;
import com.cdkj.wzcd.model.GpsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;


public class GpsApplyActivity extends AbsBaseLoadActivity {
    private ActivityGpsApplyBinding mBinding;

    private String mSelectGPSCode;

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, GpsApplyActivity.class);
            context.startActivity(intent);
        }

    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_gps_apply, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("申领");
        getGpsRequest();
        initListener();
    }

    private void initListener() {

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
//            applyRequest();

            String text = mBinding.myElNumber.getText();
            try {
                int i = Integer.parseInt(text);
            } catch (Exception ex) {
                UITipDialog.showInfo(this, "请输入正确的数量");
                return;
            }


//            if (TextUtils.isEmpty(mSelectGPSCode)) {
//                showToast("请选择GPS");
//                return;
//            }
            applyRequest2();
        });
    }

    private boolean check() {

        if (TextUtils.isEmpty(mBinding.myElNumber.getText())) {
            return false;
        }

        return true;
    }

    public void getGpsRequest() {

        Map<String, String> map = RetrofitUtils.getRequestMap();
        map.put("applyStatus", "0");
//        map.put("useStatus", "0");
        map.put("companyApplyStatus", "1");
        map.put("companyCode", SPUtilHelper.getUserCompanyCode());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils.createApi(MyApiServer.class).getGpsList("632708", StringUtils.getJsonToString(map));

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

        for (GpsModel model : data) {
            dictionaryList.add(new DataDictionary().setDkey(model.getCode()).setDvalue(model.getGpsDevNo()));
        }

        mBinding.mySlAddGps.setData(dictionaryList, (dialog, which) -> {
            mSelectGPSCode = mBinding.mySlAddGps.getDataKey();
        });

    }


    /**
     * 公司申领
     */
    private void applyRequest() {
        Map<String, Object> map = new HashMap<>();

        map.put("applyCount", mBinding.myElNumber.getText());
        map.put("applyReason", mBinding.myElExplain.getText());
        map.put("applyUser", SPUtilHelper.getUserId());
        map.put("type", "2");

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632710", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(GpsApplyActivity.this, "申领成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(GpsApplyActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    /**
     * 个人申领
     */
    private void applyRequest2() {
        Map<String, Object> map = new HashMap<>();

        map.put("applyReason", mBinding.myElExplain.getText());
        map.put("applyUser", SPUtilHelper.getUserId());
//        map.put("gpsList", getGpsCodeList());
        map.put("applyCount", mBinding.myElNumber.getText());

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632711", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(GpsApplyActivity.this, "申领成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(GpsApplyActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    public List<Map<String, String>> getGpsCodeList() {

        List<Map<String, String>> codeList = new ArrayList<>();

        Map<String, String> map = new HashMap<>();

        map.put("code", mSelectGPSCode);
        codeList.add(map);
        return codeList;
    }
}
