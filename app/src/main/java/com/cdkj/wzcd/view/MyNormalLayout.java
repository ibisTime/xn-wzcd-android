package com.cdkj.wzcd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyNormalBinding;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MyNormalLayout extends LinearLayout {

    private Context context;
    private LayoutMyNormalBinding mBinding;

    private String txtTitle;
    private String txtContent;

    public MyNormalLayout(Context context) {
        this(context, null);
    }

    public MyNormalLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyNormalLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyNormalLayout, 0, 0);
        txtTitle = typedArray.getString(R.styleable.MyNormalLayout_txt_normal_title);
        txtContent = typedArray.getString(R.styleable.MyNormalLayout_txt_normal_content);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        if (!TextUtils.isEmpty(txtContent))
            mBinding.tvContent.setText(txtContent);
    }


    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_normal, this, true);

    }
}
