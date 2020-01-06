package com.cdkj.wzcd.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cdkj.baselibrary.activitys.FindPwdActivity;
import com.cdkj.baselibrary.activitys.UpDataPhoneActivity;
import com.cdkj.baselibrary.activitys.UpDataPwdActivity;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.dialog.CommonDialog;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.MainActivity;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.MainFrgUserBinding;
import com.cdkj.wzcd.model.UserModel;
import com.cdkj.wzcd.module.user.SignInActivity;
import retrofit2.Call;

import java.util.HashMap;
import java.util.Map;

import static com.cdkj.wzcd.util.UserHelper.NQZY;
import static com.cdkj.wzcd.util.UserHelper.YWY;
import static com.cdkj.wzcd.util.UserHelper.ZHRY;

/**
 * @author : qianLei
 * @since : 2019/12/25 14:28
 */
public class MainUserFragment extends BaseLazyFragment {

    private MainFrgUserBinding mBinding;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static MainUserFragment getInstance() {
        MainUserFragment fragment = new MainUserFragment();
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.main_frg_user, null, false);

        initListener();

        getUserInfoRequest();

        return mBinding.getRoot();
    }

    private void initListener() {

        mBinding.llMobile.setOnClickListener(view -> {
            UpDataPhoneActivity.open(mActivity);
        });

        mBinding.llPwd.setOnClickListener(view -> {
            UpDataPwdActivity.open(mActivity);
        });

        mBinding.btnLogout.setOnClickListener(view -> {
            logOut();
        });

    }

    /**
     * 获取用户信息
     */
    public void getUserInfoRequest() {

        if (!SPUtilHelper.isLoginNoStart()) {  //没有登录不用请求
            return;
        }

        Map<String, String> map = new HashMap<>();

        map.put("userId", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils
                .createApi(MyApiServer.class)
                .getUserInfoDetails("630067", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<UserModel>(mActivity) {
            @Override
            protected void onSuccess(UserModel data, String SucMessage) {
                saveUserInfo(data);
                setView(data);
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                UITipDialog.showFail(mActivity, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    /**
     * 保存用户相关信息
     *
     * @param data
     */
    private void saveUserInfo(UserModel data) {
        SPUtilHelper.saveisTradepwdFlag(data.isTradepwdFlag());
        SPUtilHelper.saveUserPhoneNum(data.getMobile());
        SPUtilHelper.saveUserName(data.getRealName());
        SPUtilHelper.saveUserNickName(data.getNickname());
        SPUtilHelper.saveUserPhoto(data.getPhoto());
        SPUtilHelper.saveRoleCode(data.getRoleCode());
        SPUtilHelper.saveUserCompanyCode(data.getCompanyCode());


    }

    private void setView(UserModel data) {

        ImgUtils.loadLogo(mActivity, data.getPhoto(), mBinding.ivAvatar);
        mBinding.tvName.setText("真实姓名：" + data.getRealName());
        mBinding.tvNick.setText(data.getLoginName());
        mBinding.tvMobile.setText(data.getMobile());
        mBinding.tvCompany.setText(data.getCompanyName());

        if (TextUtils.equals(data.getRoleCode(), ZHRY)) { // 驻行人员
            mBinding.tvRole.setText("角色名称：驻行人员");

        } else if (TextUtils.equals(data.getRoleCode(), YWY)) {// 业务员
            mBinding.tvRole.setText("角色名称：业务员");

        } else if (TextUtils.equals(data.getRoleCode(), NQZY)) {// 内勤专员
            mBinding.tvRole.setText("角色名称：内勤专员");

        } else {
            mBinding.tvRole.setText("角色名称：其他");
        }

    }

    /**
     * 退出登录
     */
    private void logOut() {

        CommonDialog commonDialog = new CommonDialog(mActivity).builder()
                .setTitle(getString(com.cdkj.baselibrary.R.string.tips))
                .setContentMsg("你确定要退出当前账号吗?")
                .setPositiveBtn(getString(com.cdkj.baselibrary.R.string.sure), view -> {
                    SPUtilHelper.logOutClear();
                    UITipDialog.showSuccess(mActivity, "退出成功", dialogInterface -> {
                        mActivity.finish();
                        SignInActivity.open(mActivity, false);
                    });
                })
                .setNegativeBtn(getString(com.cdkj.baselibrary.R.string.cancel), null, false);

        commonDialog.show();

    }
}
