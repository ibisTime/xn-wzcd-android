package com.cdkj.wzcd.util;

import android.text.TextUtils;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;

/**
 * Created by cdkj on 2018/6/8.
 */

public class UserHelper {

    public static final String YWY = "SR201800000000000000YWY"; // 业务员
    public static final String ZHRY = "SR20180000000000000ZHRY"; // 驻行人员
    public static final String NQZY = "SR20180000000000000NQZY"; // 内勤专员

    public static boolean isZHRY(){
        if (TextUtils.isEmpty(SPUtilHelper.getRoleCode()))
            return false;

        // 是否是驻行人员
        if (TextUtils.equals(SPUtilHelper.getRoleCode(), ZHRY)){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isYWY(){
        if (TextUtils.isEmpty(SPUtilHelper.getRoleCode()))
            return false;

        // 是否是驻行人员
        if (TextUtils.equals(SPUtilHelper.getRoleCode(), YWY)){
            return true;
        }else {
            return false;
        }
    }

}
