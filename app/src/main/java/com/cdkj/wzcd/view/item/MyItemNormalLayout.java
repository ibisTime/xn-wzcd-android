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
import com.cdkj.wzcd.databinding.LayoutMyItemNormalBinding;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MyItemNormalLayout extends LinearLayout {

    private Context context;
    private LayoutMyItemNormalBinding mBinding;

    private String txtTitle;
    private String txtContent;
    private String txtContentRight;
    private String txtContentMiddle;

    public MyItemNormalLayout(Context context) {
        this(context, null);
    }

    public MyItemNormalLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyItemNormalLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyItemNormalLayout, 0, 0);
        txtTitle = typedArray.getString(R.styleable.MyItemNormalLayout_txt_item_normal_title);
        txtContent = typedArray.getString(R.styleable.MyItemNormalLayout_txt_item_normal_content);
        txtContentRight = typedArray.getString(R.styleable.MyItemNormalLayout_txt_item_normal_content_right);
        txtContentMiddle = typedArray.getString(R.styleable.MyItemNormalLayout_txt_item_normal_content_middle);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void setData() {
        if (!TextUtils.isEmpty(txtTitle)){
            mBinding.tvTitle.setText(txtTitle);
            mBinding.tvTitle.setVisibility(VISIBLE);
        }

        if (!TextUtils.isEmpty(txtContent))
            mBinding.tvContent.setText(txtContent);

        if (!TextUtils.isEmpty(txtContentMiddle)){
            mBinding.tvContentMiddle.setText(txtContentMiddle);
            mBinding.tvContentMiddle.setVisibility(VISIBLE);
        }

        if (!TextUtils.isEmpty(txtContentRight)){
            mBinding.tvContentRight.setText(txtContentRight);
            mBinding.tvContentRight.setVisibility(VISIBLE);
        }

    }

    public void setTitle(String title){
        mBinding.tvTitle.setText(title);
    }

    public void setContent(String content){
        mBinding.tvContent.setText(content);
    }

    public void setMiddleContent(String content){
        mBinding.tvContentMiddle.setText(content);
        mBinding.tvContentMiddle.setVisibility(VISIBLE);
    }

    public void setContent(String content, String contentRight){
        mBinding.tvContent.setText(content);
        mBinding.tvContentRight.setText(contentRight);
        mBinding.tvContentRight.setVisibility(VISIBLE);
    }

    public void setContent(String content, String contentMiddle, String contentRight){
        mBinding.tvContent.setText(content);

        mBinding.tvContentMiddle.setText(contentMiddle);
        mBinding.tvContentMiddle.setVisibility(VISIBLE);

        mBinding.tvContentRight.setText(contentRight);
        mBinding.tvContentRight.setVisibility(VISIBLE);
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_item_normal, this, true);

    }
}
