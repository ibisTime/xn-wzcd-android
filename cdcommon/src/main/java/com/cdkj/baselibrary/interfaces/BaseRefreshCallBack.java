package com.cdkj.baselibrary.interfaces;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.databinding.EmptyViewBinding;

/**
 * 刷新方法回调 空数据显示图片
 * Created by cdkj on 2017/10/17.
 */

public abstract class BaseRefreshCallBack<T> implements RefreshInterface<T> {

    private EmptyViewBinding emptyViewBinding;

    private Activity context;

    public BaseRefreshCallBack(Activity context) {
        this.context = context;
    }

    @Override
    public View getEmptyView() {
        if (context == null) {
            return null;
        }
        emptyViewBinding = DataBindingUtil.inflate(context.getLayoutInflater(), R.layout.empty_view, null, false);
        return emptyViewBinding.getRoot();
    }

    @Override
    public void showErrorState(String errorMsg, int img) {
        if (emptyViewBinding == null) {
            return;
        }
        emptyViewBinding.tv.setText(errorMsg);

        if(TextUtils.isEmpty(errorMsg)){
            emptyViewBinding.tv.setVisibility(View.GONE);
        }else{
            emptyViewBinding.tv.setVisibility(View.VISIBLE);
        }

        if (img <= 0) {
            emptyViewBinding.img.setVisibility(View.GONE);
        } else {
            emptyViewBinding.img.setImageResource(img);
            emptyViewBinding.img.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void showEmptyState(String errorMsg, int errorImg) {
        if (emptyViewBinding == null) {
            return;
        }
        emptyViewBinding.tv.setText(errorMsg);
        if(TextUtils.isEmpty(errorMsg)){
            emptyViewBinding.tv.setVisibility(View.GONE);
        }else{
            emptyViewBinding.tv.setVisibility(View.VISIBLE);
        }

        if (errorImg <= 0) {
            emptyViewBinding.img.setVisibility(View.GONE);
        } else {
            emptyViewBinding.img.setImageResource(errorImg);
            emptyViewBinding.img.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onRefresh(int pageIndex, int limit) {

    }

    @Override
    public void onLoadMore(int pageIndex, int limit) {

    }

    @Override
    public void onDestroy() {
        context = null;
    }
}
