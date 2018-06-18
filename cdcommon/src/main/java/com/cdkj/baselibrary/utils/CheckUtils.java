package com.cdkj.baselibrary.utils;

import android.app.Activity;
import android.text.TextUtils;

import com.cdkj.baselibrary.CdApplication;
import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.interfaces.CheckInfoReleaseStateListener;
import com.cdkj.baselibrary.model.CodeModel;

/**
 * 一些状态检测
 * Created by cdkj on 2018/3/27.
 */

public class CheckUtils {

    /**
     * 信息发布状态
     *
     * @param code
     */
    public static void checkReleaseState(String code, CheckInfoReleaseStateListener listener) {
        String s = "approve";//是否包含敏感词汇
        if (!TextUtils.isEmpty(code) && !StringUtils.contains(code, s)) { //发布成功
            if (listener != null) {
                listener.succ();
            }
        } else if (StringUtils.contains(code, s)) { //包含敏感字符
            if (listener != null) {
                listener.haveSensitive(CdApplication.getContext().getString(R.string.release_succ_1));
            }

        } else {                  //发布失败
            if (listener != null) {
                listener.fail();
            }
        }
    }

}
