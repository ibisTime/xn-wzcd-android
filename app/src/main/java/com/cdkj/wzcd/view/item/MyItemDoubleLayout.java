package com.cdkj.wzcd.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyItemDoubleBinding;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MyItemDoubleLayout extends LinearLayout {

    private Context context;
    private LayoutMyItemDoubleBinding mBinding;

    private String txtContent;
    private String txtContentRight;

    public MyItemDoubleLayout(Context context) {
        this(context, null);
    }

    public MyItemDoubleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyItemDoubleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyItemDoubleLayout, 0, 0);
        txtContent = typedArray.getString(R.styleable.MyItemNormalLayout_txt_item_normal_content);
        txtContentRight = typedArray.getString(R.styleable.MyItemNormalLayout_txt_item_normal_content_right);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void setData() {
        mBinding.tvContent.setText(txtContent);
        mBinding.tvContentRight.setText(txtContentRight);

        if (!TextUtils.isEmpty(txtContentRight)){
            mBinding.tvContentRight.setVisibility(VISIBLE);
        }

    }

    public void setContent(String content){
        mBinding.tvContent.setText(content);
    }

    public void setContent(String content, String contentRight){
        mBinding.tvContent.setText(content);

        mBinding.tvContentRight.setText(contentRight);
        mBinding.tvContentRight.setVisibility(VISIBLE);
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_item_double, this, true);

    }
}
