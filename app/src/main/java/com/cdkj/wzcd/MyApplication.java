package com.cdkj.wzcd;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.cdkj.baselibrary.CdApplication;
import com.tencent.android.tpush.XGPushConfig;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import org.litepal.LitePal;


/**
 * Created by cdkj on 2018/1/31.
 */

public class MyApplication extends Application {

    public static Context instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        CdApplication.initialize(this, BuildConfig.LOG_DEBUG);

        initLitePal();

        //二维码初始化
        ZXingLibrary.initDisplayOpinion(this);

        initXinGePush();
    }


    public static Context getInstance() {
        return instance;
    }


    private void initLitePal() {
        LitePal.initialize(this);
        LitePal.aesKey("tha_wallet");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);

    }

    /**
     * 信鸽推送初始化设置
     */
    private void initXinGePush(){
        XGPushConfig.enableDebug(this,true);

        XGPushConfig.enableOtherPush(getApplicationContext(), true);
        XGPushConfig.setHuaweiDebug(true);
        XGPushConfig.setMiPushAppId(getApplicationContext(), "5abfe0cef45a7");
        XGPushConfig.setMiPushAppKey(getApplicationContext(), "fec23468c91a48556148fc921d59db04");
        XGPushConfig.setMzPushAppId(this, "5abfe0cef45a7");
        XGPushConfig.setMzPushAppKey(this, "fec23468c91a48556148fc921d59db04");
    }
}
