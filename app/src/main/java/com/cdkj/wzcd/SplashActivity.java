package com.cdkj.wzcd;

import android.content.Intent;
import android.os.Bundle;

import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseActivity;
import com.cdkj.baselibrary.dialog.CommonDialog;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.AppUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.VersionModel;
import com.cdkj.wzcd.module.user.SignInActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Call;

import static com.cdkj.baselibrary.utils.UpdateUtil.isForceUpload;
import static com.cdkj.baselibrary.utils.UpdateUtil.startWeb;

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

                checkVersion();
            }

            @Override
            public void onReqFailure(String errorCode, String errorMessage) {
                // 如果数据库已有数据，直接加载数据库
                if (DataSupport.isExist(DataDictionary.class)) {
                    checkVersion();
                } else {
                    checkVersion();
                    ToastUtil.show(SplashActivity.this, "无法连接服务器，请检查网络");
                }
            }

            @Override
            public void onFinish() {

            }
        });
    }

    /**
     * 获取更新，下一步是获取七牛
     */
    private void checkVersion() {
        Map<String, String> map = new HashMap<>();
        map.put("type", "android");
        map.put("systemCode", MyCdConfig.SYSTEM_CODE);
        map.put("companyCode", MyCdConfig.COMPANY_CODE);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getVersion("630048", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<VersionModel>(this) {

            @Override
            protected void onSuccess(VersionModel data, String SucMessage) {
                if (data == null) {
                    open();
                    return;
                }

                if (data.getVersion() > AppUtils.getAppVersionCode(MyApplication.getInstance())) {  //版本号不一致说明有更新

                    versionUpdateDialog(data);

                } else {
                    open();
                }
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                open();
            }

            @Override
            protected void onFinish() {

            }
        });
    }

    public void versionUpdateDialog(VersionModel versionModel) {
        if (isForceUpload(versionModel.getForceUpdate())) { // 强制更新
            showSureDialog("更新提示", versionModel.getNote(), view -> {
                startWeb(SplashActivity.this, versionModel.getDownloadUrl());
                finish();
            });

        } else {
            showDoubleWarnListen("更新提示", versionModel.getNote(), view -> {
                startWeb(SplashActivity.this, versionModel.getDownloadUrl());
            }, view -> {
                open();
            });
        }
    }

    /**
     * 显示确认取消弹框
     */
    protected void showDoubleWarnListen(String title, String content,
                                        CommonDialog.OnPositiveListener onPositiveListener,
                                        CommonDialog.OnNegativeListener onNegativeListener) {

        if (isFinishing()) {
            return;
        }

        CommonDialog commonDialog = new CommonDialog(this).builder()
                .setTitle(title).setContentMsg(content)
                .setPositiveBtn(getString(R.string.sure),
                        onPositiveListener)
                .setNegativeBtn(getString(R.string.cancel),
                        onNegativeListener, false);

        commonDialog.show();
    }

    /**
     * 只显示确认弹框的按钮
     */
    protected void showSureDialog(String title, String str,
                                  CommonDialog.OnPositiveListener onPositiveListener) {

        if (this == null || isFinishing()) {
            return;
        }

        CommonDialog commonDialog = new CommonDialog(this).builder()
                .setTitle(title)
                .setContentMsg(str)
                .setPositiveBtn(getString(R.string.sure),
                        onPositiveListener);

        commonDialog.show();
    }

    private void open() {

        mSubscription.add(Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {

                    if (!SPUtilHelper.isLoginNoStart()) {  //没有登录
                        SignInActivity.open(this, false);
                    }else {
                        MainActivity2.open(this);
                    }
                    finish();

                }, Throwable::printStackTrace));
    }

}
