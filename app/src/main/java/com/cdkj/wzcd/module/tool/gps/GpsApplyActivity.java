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
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivityGpsApplyBinding;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;


public class GpsApplyActivity extends AbsBaseLoadActivity {
    private ActivityGpsApplyBinding mBinding;

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

        initListener();
    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                applyRequest();
            }
        });
    }

    private boolean check() {
        // 银行
        if (TextUtils.isEmpty(mBinding.myElNumber.getText())) {
            return false;
        }

        return true;
    }

    private void applyRequest(){
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
}
