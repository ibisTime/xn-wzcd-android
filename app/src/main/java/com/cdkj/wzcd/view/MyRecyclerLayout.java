package com.cdkj.wzcd.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyRecyclerBinding;

/**
 * Created by cdkj on 2018/6/26.
 */

public class MyRecyclerLayout extends LinearLayout {

    private LayoutMyRecyclerBinding mBinding;

    private Context context;
    private Activity mActivity;

    private String tvTitle;

    private int themeId;
    private int mRequestCode;


    public MyRecyclerLayout(Context context) {
        this(context, null);
    }

    public MyRecyclerLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRecyclerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyLayout, 0, 0);
        tvTitle = typedArray.getString(R.styleable.MyLayout_title);

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_recycler, this, true);

        initListener();
    }

    private void initListener() {

    }

    private void setData() {
        mBinding.tvTitle.setText(tvTitle);

    }

    public void setTitle(String title) {
        mBinding.tvTitle.setText(title);

    }

    public void build(RecyclerView.Adapter mAdapter, boolean isShowAdd, OnAddClickListener listener){
        if (mAdapter != null) {

            mBinding.rv.setLayoutManager(getLinearLayoutManager(false));
            mBinding.rv.setAdapter(mAdapter);

            mBinding.llAddRoot.setVisibility(isShowAdd ? VISIBLE : GONE);
            mBinding.llAddRoot.setOnClickListener((View view) -> {
                if (mBinding.llAddRoot.getVisibility() == GONE)
                    return;

                if (listener == null)
                    return;

                listener.click(view);
            });

        }
    }

    /**
     * 获取布局管理器
     *
     * @return
     */
    @NonNull
    public LinearLayoutManager getLinearLayoutManager(boolean canScrollVertically) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {  //禁止自滚动
                return canScrollVertically;
            }
        };
    }

    public interface OnAddClickListener{
        void click(View view);
    }

}
