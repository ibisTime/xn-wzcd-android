package com.cdkj.wzcd;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.cdkj.baselibrary.CdApplication;

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
}
