package com.cdkj.wzcd.module.user;


import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cdkj.baselibrary.activitys.FindPwdActivity;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.interfaces.LoginInterface;
import com.cdkj.baselibrary.interfaces.LoginPresenter;
import com.cdkj.baselibrary.model.UserLoginModel;
import com.cdkj.wzcd.MainActivity;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivitySignInBinding;

@Route(path = CdRouteHelper.APPLOGIN)
public class SignInActivity extends AbsBaseLoadActivity implements LoginInterface {

    private boolean canOpenMain;

    private LoginPresenter mPresenter;
    private ActivitySignInBinding mBinding;

    /**
     * 打开当前页面
     *
     * @param context
     */
    public static void open(Context context, boolean canOpenMain) {
        if (context == null) {
            return;
        }
        Intent intent= new Intent(context, SignInActivity.class);
        intent.putExtra("canOpenMain",canOpenMain);
        context.startActivity(intent);
    }

    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_sign_in, null, false);
        return mBinding.getRoot();
    }


    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("登录");


        mPresenter = new LoginPresenter(this);

        init();
        initListener();
        initSignInBtn();
    }

    private void init() {
        if (getIntent() == null)
            return;

        canOpenMain = getIntent().getBooleanExtra("canOpenMain",false);
    }

    private void initListener() {
        mBinding.llFinish.setOnClickListener(view -> {
            finish();
        });

        //登录
        mBinding.btnConfirm.setOnClickListener(v -> {
            if (check()){
                mPresenter.login(mBinding.edtUsername.getText().toString(), mBinding.edtPassword.getText().toString(), this);
            }

        });

        //找回密码
        mBinding.tvForget.setOnClickListener(v -> {
            FindPwdActivity.open(this, mBinding.edtUsername.getText().toString().trim());
        });
    }

    private boolean check(){
        if (TextUtils.isEmpty(mBinding.edtUsername.getText().toString().trim())){
            showToast(getString(R.string.user_mobile_hint));
            return false;
        }
        if (mBinding.edtUsername.getText().toString().trim().length() != 11){
            showToast(getString(R.string.user_mobile_format_hint));
            return false;
        }
        if (mBinding.edtPassword.getText().toString().trim().length() < 6){
            showToast(getString(R.string.user_password_format_hint));
            return false;
        }

        return true;
    }

    private void initSignInBtn() {

        mBinding.edtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (mBinding.edtUsername.getText().toString().length() == 11){
                    if (mBinding.edtPassword.getText().toString().length() >= 6){
                        // 可以登录
                        mBinding.btnConfirm.setBackgroundResource(R.drawable.common_login_btn_light);
                    }else {
                        // 不可登录
                        mBinding.btnConfirm.setBackgroundResource(R.drawable.common_login_btn_dark);
                    }
                }else {
                    // 不可登录
                    mBinding.btnConfirm.setBackgroundResource(R.drawable.common_login_btn_dark);
                }
            }
        });

    }

    @Override
    public void LoginSuccess(UserLoginModel user, String msg) {

        SPUtilHelper.saveUserId(user.getUserId());
        SPUtilHelper.saveUserToken(user.getToken());
        SPUtilHelper.saveUserPhoneNum(mBinding.edtUsername.getText().toString().trim());

        MainActivity.open(this);

        finish();
    }

    @Override
    public void LoginFailed(String code, String msg) {
        showToast(msg);
    }

    @Override
    public void StartLogin() {
        showLoadingDialog();
    }

    @Override
    public void EndLogin() {
        disMissLoading();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.clear();
            mPresenter = null;
        }
    }

    @Override
    protected boolean canFinish() {
        if(canOpenMain){
            MainActivity.open(this);
            finish();
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if(canOpenMain){
            MainActivity.open(this);
            finish();
        }else{
            super.onBackPressed();
        }
    }



}
