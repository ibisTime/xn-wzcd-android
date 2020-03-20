package com.cdkj.wzcd.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.activitys.UpDataPhoneActivity;
import com.cdkj.baselibrary.activitys.UpDataPwdActivity;
import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.dialog.CommonDialog;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.eventmodels.EventFinishAll;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.AppUtils;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.BuildConfig;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.MainFrgUserBinding;
import com.cdkj.wzcd.main.credit.bean.ConfirmBean;
import com.cdkj.wzcd.model.UserModel;
import com.cdkj.wzcd.model.VersionModel;
import com.cdkj.wzcd.module.user.SignInActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.utils.UpdateUtil.isForceUpload;
import static com.cdkj.baselibrary.utils.UpdateUtil.startWeb;

/**
 * @author : qianLei
 * @since : 2019/12/25 14:28
 */
public class MainUserFragment extends BaseLazyFragment {

    private MainFrgUserBinding mBinding;

    private boolean isUpdate = false;
    private VersionModel versionModel;

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
        init();
        initListener();

        getVersion();
        getUserInfoRequest();

        return mBinding.getRoot();
    }

    private void init() {

        mBinding.tvVesionName.setText(BuildConfig.VERSION_NAME);

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

        mBinding.llVersionName.setOnClickListener(v -> {
            if (versionModel != null && isUpdate) {
                showUploadDialog(versionModel);
            }
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
        mBinding.tvRole.setText("角色名称：" + data.getRoleName());
//        if (TextUtils.equals(data.getRoleCode(), ZHRY)) { // 驻行人员
//            mBinding.tvRole.setText("角色名称：驻行人员");
//
//        } else if (TextUtils.equals(data.getRoleCode(), YWY)) {// 业务员
//            mBinding.tvRole.setText("角色名称：业务员");
//
//        } else if (TextUtils.equals(data.getRoleCode(), NQZY)) {// 内勤专员
//            mBinding.tvRole.setText("角色名称：内勤专员");
//
//        } else if (TextUtils.equals(data.getRoleCode(), PGY)) {// 评估员
//            mBinding.tvRole.setText("角色名称：评估员");
//
//        } else {
//            mBinding.tvRole.setText("角色名称：其他");
//        }

    }

    /**
     * 退出登录
     */
    private void logOut() {

        CommonDialog commonDialog = new CommonDialog(mActivity).builder()
                .setTitle(getString(com.cdkj.baselibrary.R.string.tips))
                .setContentMsg("你确定要退出当前账号吗?")
                .setPositiveBtn(getString(com.cdkj.baselibrary.R.string.sure), view -> {

                    sendPushToken();

                })
                .setNegativeBtn(getString(com.cdkj.baselibrary.R.string.cancel), null, false);

        commonDialog.show();

    }

    private void sendPushToken() {

        HashMap<String, String> map = new HashMap<>();
        map.put("userId", SPUtilHelper.getUserId());

        Call<BaseResponseModel<ConfirmBean>> confirm = RetrofitUtils.createApi(MyApiServer.class).confirm("805085", StringUtils.getJsonToString(map));
        confirm.enqueue(new BaseResponseModelCallBack<ConfirmBean>(mActivity) {
            @Override
            protected void onSuccess(ConfirmBean data, String SucMessage) {

                UITipDialog.showSuccess(mActivity, "退出成功", dialogInterface -> {
                    SPUtilHelper.logOutClear();
                    mActivity.finish();
                    SignInActivity.open(mActivity, false);

                });

            }

            @Override
            protected void onFinish() {
            }
        });
    }

    /**
     * 获取最新版本
     *
     * @return
     */
    private void getVersion() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "android");
        map.put("systemCode", MyCdConfig.SYSTEM_CODE);
        map.put("companyCode", MyCdConfig.COMPANY_CODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getVersion("630048", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<VersionModel>(mActivity) {

            @Override
            protected void onSuccess(VersionModel data, String SucMessage) {
                if (data == null) {
                    return;
                }
                versionModel = data;

                //版本号不一致说明有更新
                isUpdate = data.getVersion() > AppUtils.getAppVersionCode(mActivity);
                if (isUpdate) {
                    mBinding.tvVesionName.setText(mBinding.tvVesionName.getText() + "(有新版本可更新)");
                    showUploadDialog(data);
                } else {
                    mBinding.tvVesionName.setText(mBinding.tvVesionName.getText() + "(当前最新版本)");
                }

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    /**
     * 显示更新dialog
     *
     * @param versionModel
     */
    private void showUploadDialog(VersionModel versionModel) {

        if (isForceUpload(versionModel.getForceUpdate())) { // 强制更新
            showSureDialog("更新提示", versionModel.getNote(), view -> {
                startWeb(mActivity, versionModel.getDownloadUrl());
                EventBus.getDefault().post(new EventFinishAll()); //结束所有界面
                mActivity.finish();
            });

        } else {
            showDoubleWarnListen("更新提示", versionModel.getNote(), view -> {
                startWeb(mActivity, versionModel.getDownloadUrl());
            }, view -> {

            });
        }
    }

    /**
     * 只显示确认弹框的按钮
     *
     * @param title
     * @param str
     * @param onPositiveListener
     */
    protected void showSureDialog(String title, String str, CommonDialog.OnPositiveListener onPositiveListener) {

        CommonDialog commonDialog = new CommonDialog(mActivity).builder()
                .setTitle(title)
                .setContentMsg(str)
                .setPositiveBtn(getString(R.string.sure), onPositiveListener);

        commonDialog.show();
    }

    /**
     * 显示确认取消弹框
     */
    protected void showDoubleWarnListen(String title, String content,
                                        CommonDialog.OnPositiveListener onPositiveListener,
                                        CommonDialog.OnNegativeListener onNegativeListener) {

        CommonDialog commonDialog = new CommonDialog(mActivity).builder()
                .setTitle(title).setContentMsg(content)
                .setPositiveBtn(getString(R.string.sure),
                        onPositiveListener)
                .setNegativeBtn(getString(R.string.cancel),
                        onNegativeListener, false);

        commonDialog.show();
    }


}
