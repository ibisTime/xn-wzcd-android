package com.cdkj.wzcd.view.list;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyItemConfirmBtnBinding;
import com.cdkj.wzcd.view.interfaces.MyConfirmInterface;

/**
 * Created by cdkj on 2018/6/4.
 */

public class MyListItemBtnLayout extends LinearLayout {

    private Context context;
    private LayoutMyItemConfirmBtnBinding mBinding;

    private String txtContentLeft;
    private String txtContentRight;

    public MyListItemBtnLayout(Context context) {
        this(context, null);
    }

    public MyListItemBtnLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListItemBtnLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyListItemBtnLayout, 0, 0);
        txtContentLeft = typedArray.getString(R.styleable.MyListItemBtnLayout_txt_item_confirm_content_left);
        txtContentRight = typedArray.getString(R.styleable.MyListItemBtnLayout_txt_item_confirm_content_right);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void setData() {
        if (TextUtils.isEmpty(txtContentLeft) && TextUtils.isEmpty(txtContentRight)){

            mBinding.llRoot.setVisibility(GONE);

        }else {

            mBinding.btnLeft.setText(txtContentLeft);
            if (!TextUtils.isEmpty(txtContentLeft)){
                mBinding.btnLeft.setVisibility(VISIBLE);
            }

            mBinding.btnRight.setText(txtContentRight);
            if (!TextUtils.isEmpty(txtContentRight)){
                mBinding.btnRight.setVisibility(VISIBLE);
            }

        }

    }

    public void setContent(String content, String contentRight){

        if (TextUtils.isEmpty(content) && TextUtils.isEmpty(contentRight)){

            mBinding.llRoot.setVisibility(GONE);

        }else {

            if (!TextUtils.isEmpty(content)){
                mBinding.btnLeft.setText(content);
                mBinding.btnLeft.setVisibility(VISIBLE);
            }

            if (!TextUtils.isEmpty(contentRight)){
                mBinding.btnRight.setText(contentRight);
                mBinding.btnRight.setVisibility(VISIBLE);
            }

        }
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_item_confirm_btn, this, true);

    }

    public void setLeftListener(MyConfirmInterface mConfirmInterface) {
        mBinding.btnLeft.setOnClickListener(view -> {
            if (mConfirmInterface == null)
                return;

            mConfirmInterface.onClick(view);
        });
    }

    public void setRightListener(MyConfirmInterface mConfirmInterface) {
        mBinding.btnRight.setOnClickListener(view -> {
            if (mConfirmInterface == null)
                return;

            mConfirmInterface.onClick(view);
        });
    }

    public void setRightTextAndListener(String contentRight, MyConfirmInterface mConfirmInterface) {
        if (!TextUtils.isEmpty(contentRight)){
            mBinding.llRoot.setVisibility(VISIBLE);
            mBinding.btnRight.setVisibility(VISIBLE);
            mBinding.btnRight.setText(contentRight);
        }

        mBinding.btnRight.setOnClickListener(view -> {
            if (mConfirmInterface == null)
                return;

            mConfirmInterface.onClick(view);
        });
    }


}
