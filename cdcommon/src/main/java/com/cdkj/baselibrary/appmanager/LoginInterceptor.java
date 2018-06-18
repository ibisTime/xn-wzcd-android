package com.cdkj.baselibrary.appmanager;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;

/**
 * 登录拦截
 */
@Interceptor(priority = 1)
public class LoginInterceptor implements IInterceptor {
    Context mContext;

    /**
     * The operation of this interceptor.
     *
     * @param postcard meta
     * @param callback cb
     */
    @Override
    public void process(final Postcard postcard, final InterceptorCallback callback) {

        if (!SPUtilHelper.isLoginNoStart()) {         //未登录 进行拦截 并跳转导登录界面
            callback.onInterrupt(null);
            CdRouteHelper.openLogin(false); //
        } else {
            callback.onContinue(postcard);
        }

    }

    /**
     * @param context ctx
     */
    @Override
    public void init(Context context) {
        mContext = context;
    }
}
