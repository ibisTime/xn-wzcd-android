package com.cdkj.baselibrary.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.databinding.ActivityUpDataPhoneBinding;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.interfaces.SendCodeInterface;
import com.cdkj.baselibrary.interfaces.SendPhoneCodePresenter;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.AppUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import retrofit2.Call;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class UpDataPhoneActivity extends AbsBaseLoadActivity implements SendCodeInterface {
    ActivityUpDataPhoneBinding mBinding;
    private SendPhoneCodePresenter mSendCOdePresenter;

    private boolean isOld = true;

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, UpDataPhoneActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_up_data_phone, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("修改手机号");
        mBinding.etOldPhone.setText(SPUtilHelper.getUserPhoneNum());

        mSendCOdePresenter = new SendPhoneCodePresenter(this);

        mBinding.btnCheckCode.setOnClickListener(v -> {

            isOld = true;
            mSendCOdePresenter.sendCodeRequest(SPUtilHelper.getUserPhoneNum(), "630052", MyCdConfig.USER_TYPE, UpDataPhoneActivity.this);

        });

        mBinding.btnNewCheckCode.setOnClickListener(v -> {

            isOld = false;
            mSendCOdePresenter.sendCodeRequest(mBinding.etNewPhone.getText().toString(), "630052", MyCdConfig.USER_TYPE, UpDataPhoneActivity.this);

        });

        mBinding.btnYesUpdataPsw.setOnClickListener(v -> {
            if (TextUtils.isEmpty(mBinding.etCheckCode.getText().toString())) {
                showToast(getString(com.cdkj.baselibrary.R.string.please_input_verification_code));
                return;
            }
            if (TextUtils.isEmpty(mBinding.etNewPhone.getText().toString())) {
                showToast(getString(com.cdkj.baselibrary.R.string.please_input_phone));
                return;
            }
            if (TextUtils.isEmpty(mBinding.etNewCheckCode.getText().toString())) {
                showToast(getString(com.cdkj.baselibrary.R.string.please_input_verification_code));
                return;
            }
            requestUpPhoneNumber();
        });


    }

    /**
     * 请求接口修改手机号
     */
    private void requestUpPhoneNumber() {
        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        showLoadingDialog();
        hashMap.put("oldMobile", mBinding.etOldPhone.getText().toString());
        hashMap.put("oldCaptcha", mBinding.etCheckCode.getText().toString());
        hashMap.put("newMobile", mBinding.etNewPhone.getText().toString());
        hashMap.put("newCaptcha", mBinding.etNewCheckCode.getText().toString());
        hashMap.put("userId", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("630052", StringUtils.getJsonToString(hashMap));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(UpDataPhoneActivity.this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    SPUtilHelper.saveUserPhoneNum(mBinding.etNewPhone.getText().toString());
                    UITipDialog.showSuccess(UpDataPhoneActivity.this, "手机号码修改成功",dialog -> finish() );
                } else {
                    showToast("手机号码修改失败");
                }
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                Log.i("pppppp", "onReqFailure: "+errorMessage);
                showToast(errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();

            }
        });

    }


    @Override
    public void CodeSuccess(String msg) {
        if (isOld){
            mSubscription.add(AppUtils.startCodeDown(60, mBinding.btnCheckCode));
        }else {
            mSubscription.add(AppUtils.startCodeDown(60, mBinding.btnNewCheckCode));
        }



    }

    @Override
    public void CodeFailed(String code, String msg) {

        showToast(msg);
    }

    @Override
    public void StartSend() {
        showLoadingDialog();
    }

    @Override
    public void EndSend() {
        disMissLoading();
    }
}
