package com.cdkj.baselibrary.activitys;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.databinding.ActivityModifyPasswordBinding;
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

/**
 * 找回密码
 */
public class UpDataPwdActivity extends AbsBaseLoadActivity implements SendCodeInterface {

    private ActivityModifyPasswordBinding mBinding;

    private SendPhoneCodePresenter mSendCOdePresenter;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, UpDataPwdActivity.class);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_modify_password, null, false);
        return mBinding.getRoot();
    }


    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle(getString(R.string.activity_find_title));
        mSendCOdePresenter=new SendPhoneCodePresenter(this);

        mBinding.edtPhone.setText(SPUtilHelper.getUserPhoneNum());

        initListener();
    }

    /**
     *
     */
    private void initListener() {

        //发送验证码
        mBinding.tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSendCOdePresenter.sendCodeRequest(mBinding.edtPhone.getText().toString(),"630053", MyCdConfig.USER_TYPE,UpDataPwdActivity.this);
            }
        });


        //确定
        mBinding.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mBinding.edtPhone.getText().toString())) {
                    showToast(getString(R.string.activity_find_mobile_hint));
                    return;
                }

                if (TextUtils.isEmpty(mBinding.edtCode.getText().toString())) {
                    showToast(getString(R.string.activity_find_code_hint));
                    return;
                }

//                if (SPUtilHelper.getGoogleAuthFlag()){
//                    if (TextUtils.isEmpty(mBinding.edtGoogle.getText().toString())){
//                        showToast(getString(R.string.activity_find_google_hint));
//                        return;
//                    }
//                }

                if (TextUtils.isEmpty(mBinding.edtPassword.getText().toString())) {
                    showToast(getString(R.string.activity_find_password_hint));
                    return;
                }
                if (TextUtils.isEmpty(mBinding.edtRepassword.getText().toString())) {
                    showToast(getString(R.string.activity_find_repassword_hint));
                    return;
                }

                if (mBinding.edtPassword.getText().length() < 6) {
                    showToast(getString(R.string.activity_find_password_format_hint));
                    return;
                }

                if (!mBinding.edtPassword.getText().toString().equals(mBinding.edtRepassword.getText().toString())) {
                    showToast(getString(R.string.activity_find_repassword_format_hint));
                    return;
                }

                findPwdReqeust();
            }
        });
    }


    /**
     * 找回密码请求
     */
    private void findPwdReqeust() {

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("mobile", mBinding.edtPhone.getText().toString());
        hashMap.put("newLoginPwd", mBinding.edtPassword.getText().toString());
        hashMap.put("smsCaptcha", mBinding.edtCode.getText().toString());
        hashMap.put("kind", MyCdConfig.USER_TYPE);
        hashMap.put("systemCode", MyCdConfig.SYSTEM_CODE);
        hashMap.put("companyCode", MyCdConfig.COMPANY_CODE);

        Call call= RetrofitUtils.getBaseAPiService().successRequest("630053", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(UpDataPwdActivity.this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    showToast(getString(R.string.activity_find_success));
                    finish();
                } else {
                    showToast(getString(R.string.activity_find_failure));
                }
            }

            @Override
            protected void onFinish() {
             disMissLoading();
            }
        });


    }


    @Override
    public void CodeSuccess(String msg) {
        mSubscription.add(AppUtils.startCodeDown(60, mBinding.tvSend));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mSendCOdePresenter!=null){
            mSendCOdePresenter.clear();
            mSendCOdePresenter=null;
        }
    }
}
