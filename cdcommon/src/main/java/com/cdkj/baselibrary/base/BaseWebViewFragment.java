package com.cdkj.baselibrary.base;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.databinding.FragmentWebviewBinding;
import com.cdkj.baselibrary.model.eventmodels.EventMarketOpenChart;
import com.cdkj.baselibrary.model.eventmodels.FullScreenModel;

import org.greenrobot.eventbus.EventBus;

import static com.cdkj.baselibrary.activitys.WebViewActivity.WEBVIEWISZOOM;
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/4/19.
 */

public class BaseWebViewFragment extends BaseLazyFragment {

    private WebView webView;

    private boolean isZoom;//webview是否支持缩放

    private FragmentWebviewBinding mBinding;

    public static BaseWebViewFragment getInstance(String url, boolean isZoom) {
        BaseWebViewFragment fragment = new BaseWebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, url);
        bundle.putBoolean(WEBVIEWISZOOM, isZoom);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_webview, null, false);

        if (getArguments() != null) {
            isZoom = getArguments().getBoolean(WEBVIEWISZOOM, false);

            initLayout();
            initData();
        }

        return mBinding.getRoot();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    private void initLayout() {
        //输入法
        if (mActivity.getWindow() != null) {
            mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        }
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        webView = new WebView(mActivity);
        webView.setLayoutParams(params);

        WebSettings webSettings = webView.getSettings();
        if (webSettings != null) {
            webSettings.setJavaScriptEnabled(true);//js
            webSettings.setDefaultTextEncodingName("UTF-8");

            if (isZoom) {
                webSettings.setSupportZoom(true);   //// 支持缩放
                webSettings.setBuiltInZoomControls(true);//// 支持缩放
                webSettings.setDomStorageEnabled(true);//开启DOM
                webSettings.setLoadWithOverviewMode(true);//// 缩放至屏幕的大小
                webSettings.setUseWideViewPort(true);//将图片调整到适合webview的大小
                webSettings.setLoadsImagesAutomatically(true);//支持自动加载图片
            }
        }

//        webView.setWebChromeClient(new WebViewActivity.MyWebViewClient1());
        //JS映射
        webView.addJavascriptInterface(new WebHost(mActivity), "js");
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed(); // 接受网站证书
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                mBinding.getRoot().setVisibility(View.VISIBLE);   //当web内容加载完成时显示页面
//                disMissLoading();
//            }
        });
        mBinding.llWebview.addView(webView);

    }

    /**
     * JS映射类
     */
    public class WebHost {
        public Context context;

        public WebHost(Context context){
            this.context = context;
        }

        @JavascriptInterface
        public void acllJs(String index){ // JS事件映射

            //通知FastMessageFragment 将tab切换到热门
            FullScreenModel fullScreenModel = new FullScreenModel();
            fullScreenModel.setIndex(index);
            EventBus.getDefault().post(fullScreenModel);
        }

        @JavascriptInterface
        public void chartClick() { // JS事件映射

            //MarketActivity 响应H5点击事件，跳转到MarketChartActivity
            EventMarketOpenChart eventMarketOpenChart = new EventMarketOpenChart();
            EventBus.getDefault().post(eventMarketOpenChart);

        }
    }

    private void initData() {

        webView.loadUrl(getArguments().getString(DATA_SIGN));
    }


    @Override
    public void onDestroy() {
        if (webView != null) {
            webView.clearHistory();
            ((ViewGroup) webView.getParent()).removeView(webView);
            webView.loadUrl("about:blank");
            webView.stopLoading();
            webView.setWebChromeClient(null);
            webView.setWebViewClient(null);
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

}
