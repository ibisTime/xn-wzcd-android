package com.cdkj.baselibrary.appmanager;


import android.content.Context;
import android.text.TextUtils;

import com.cdkj.baselibrary.CdApplication;
import com.cdkj.baselibrary.utils.SPUtils;

/**
 * SPUtils 工具辅助类
 */

public class SPUtilHelper {

    private static final String USER_TOKE = "user_toke";
    private static final String USER_ID = "user_id";


    /**
     * 设置用户token
     *
     * @param s
     */
    public static void saveUserToken(String s) {
        SPUtils.put(CdApplication.getContext(), USER_TOKE, s);
    }

    /**
     * 设置用户token
     *
     * @param
     */
    public static String getUserToken() {
        return SPUtils.getString(CdApplication.getContext(), USER_TOKE, "");
    }


    /**
     * 设置用户token
     *
     * @param s
     */
    public static void saveUserPhoto(String s) {
        SPUtils.put(CdApplication.getContext(), "USER_PHOTO", s);
    }

    /**
     * 设置用户token
     *
     * @param
     */
    public static String getUserPhoto() {
        return SPUtils.getString(CdApplication.getContext(), "USER_PHOTO", "");
    }

    /**
     * 设置用户token
     *
     * @param
     */
    public static String getUserQiniuPhoto() {
        return MyCdConfig.QINIU_URL + getUserPhoto();
    }


    /**
     * 设置用户token
     *
     * @param s
     */
    public static void saveUserId(String s) {
        SPUtils.put(CdApplication.getContext(), USER_ID, s);
    }

    /**
     * 设置用户手机号码
     *
     * @param s
     */
    public static void saveUserPhoneNum(String s) {
        SPUtils.put(CdApplication.getContext(), "user_phone", s);
    }

    /**
     * 获取用户手机号
     */
    public static String getUserPhoneNum() {
        return SPUtils.getString(CdApplication.getContext(), "user_phone", "");
    }

    /**
     * 设置用户手机姓名
     *
     * @param s
     */
    public static void saveUserName(String s) {
        SPUtils.put(CdApplication.getContext(), "user_name", s);
    }

    /**
     * 获取用户手机号
     */
    public static String getUserName() {
        return SPUtils.getString(CdApplication.getContext(), "user_name", "");
    }

    /**
     * 设置用户手机姓名
     *
     * @param s
     */
    public static void saveUserNickName(String s) {
        SPUtils.put(CdApplication.getContext(), "user_nick_name", s);
    }

    /**
     * 获取用户手机号
     */
    public static String getUserNickName() {
        return SPUtils.getString(CdApplication.getContext(), "user_nick_name", "");
    }

    /**
     * 用户是否保存银行卡
     *
     * @param s
     */
    public static void saveUserIsBindCard(boolean s) {
        SPUtils.put(CdApplication.getContext(), "user_is_bind_bankcard", s);
    }

    /**
     * 获取用户是否保存银行卡
     */
    public static boolean getUserIsBindCard() {
        return SPUtils.getBoolean(CdApplication.getContext(), "user_is_bind_bankcard", false);
    }

    /**
     * 设置用户邮箱
     *
     * @param s
     */
    public static void saveUserEmail(String s) {
        SPUtils.put(CdApplication.getContext(), "user_email", s);
    }

    /**
     * 获取用户邮箱
     */
    public static String getUserEmail() {
        return SPUtils.getString(CdApplication.getContext(), "user_email", "");
    }


    /**
     * 设置用户token
     *
     * @param
     */
    public static String getUserId() {
        return SPUtils.getString(CdApplication.getContext(), USER_ID, "");

    }

    /**
     * 设置用户token
     *
     * @param s
     */
    public static void saveRoleCode(String s) {
        SPUtils.put(CdApplication.getContext(), "roleCode", s);
    }

    /**
     * 设置用户token
     *
     * @param
     */
    public static String getRoleCode() {
        return SPUtils.getString(CdApplication.getContext(), "roleCode", "");

    }

    /**
     * 判断用户是否登录
     *
     * @return
     */
    public static boolean isLogin(Context context, boolean canopenmain) {
        if (TextUtils.isEmpty(getUserId())) {
            SPUtilHelper.logOutClear();
            // 路由跳转登录页面
            CdRouteHelper.openLogin(canopenmain);
            return false;
        }

        return true;
    }

    /**
     * 判断用户是否登录
     *
     * @return
     */
    public static boolean isLoginNoStart() {
        return !TextUtils.isEmpty(getUserId());
    }

    /**
     * 保存流水账户信息
     *
     * @param s
     */
    public static void saveAmountaccountNumber(String s) {
        SPUtils.put(CdApplication.getContext(), "mAmountaccountNumber", s);
    }

    /**
     * 保存流水账户信息
     *
     * @param
     */
    public static String getAmountaccountNumber() {
        return SPUtils.getString(CdApplication.getContext(), "mAmountaccountNumber", "");
    }

    /**
     * 保存是否设置过支付密码
     *
     * @param s
     */
    public static void saveisTradepwdFlag(boolean s) {
        SPUtils.put(CdApplication.getContext(), "isTradepwdFlag", s);
    }

    /**
     * 是否设置过支付密码
     *
     * @param
     */
    public static boolean isTradepwdFlag() {
        return SPUtils.getBoolean(CdApplication.getContext(), "isTradepwdFlag", false);
    }

    /**
     * 退出登录清除数据
     */
    public static void logOutClear() {
        saveUserToken("");
        saveUserId("");
        saveUserPhoneNum("");
        saveAmountaccountNumber("");
        saveUserName("");
        saveUserPhoto("");
        saveUserNickName("");
        saveisTradepwdFlag(false);
        saveUserIsBindCard(false);
    }

}
