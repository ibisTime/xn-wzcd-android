package com.cdkj.wzcd;

import android.content.Intent;
import android.os.Bundle;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseActivity;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.module.user.SignInActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by cdkj on 2018/4/10.
 */

public class SplashActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 用于第一次安装APP，进入到除这个启动activity的其他activity，点击home键，再点击桌面启动图标时，
        // 系统会重启此activty，而不是直接打开之前已经打开过的activity，因此需要关闭此activity

        try {
            if (getIntent() != null && (getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
                finish();
                return;
            }
        } catch (Exception e) {
        }
        setContentView(R.layout.activity_splash);

        DataDictionaryHelper.getAllDataDictionary(this, new DataDictionaryHelper.AllDataDictionaryInterface() {
            @Override
            public void onSuccess(List<DataDictionary> list) {
                if (list == null)
                    return;

                // 如果数据库已有数据，清空重新加载
                if (DataSupport.isExist(DataDictionary.class))
                    DataSupport.deleteAll(DataDictionary.class);

                DataSupport.saveAll(list);
                open();
            }

            @Override
            public void onReqFailure(String errorCode, String errorMessage) {
                // 如果数据库已有数据，直接加载数据库
                if (DataSupport.isExist(DataDictionary.class)) {
                    open();
                } else {
                    ToastUtil.show(SplashActivity.this, "无法连接服务器，请检查网络");
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }


    private void open() {

        mSubscription.add(Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {

                    if (!SPUtilHelper.isLoginNoStart()) {  //没有登录
                        SignInActivity.open(this, false);
                    }else {
                        MainActivity.open(this);
                    }
                    finish();

                }, Throwable::printStackTrace));
    }

}
