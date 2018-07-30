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
import com.cdkj.wzcd.databinding.LayoutMyConfirmBtnBinding;
import com.cdkj.wzcd.view.interfaces.MyConfirmInterface;

/**
 * 按钮Layout
 * Created by cdkj on 2018/5/29.
 */

public class MyConfirmBtn extends LinearLayout {

    private Context context;
    private LayoutMyConfirmBtnBinding mBinding;

    private MyConfirmInterface mConfirmInterface;
    private MyConfirmInterface mConfirmInterfaceRight;

    private String txtContent;
    private String txtContentRight;

    public MyConfirmBtn(Context context) {
        this(context, null);
    }

    public MyConfirmBtn(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyConfirmBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyConfirmBtn, 0, 0);
        txtContent = typedArray.getString(R.styleable.MyConfirmBtn_txt_confirm_content);
        txtContentRight = typedArray.getString(R.styleable.MyConfirmBtn_txt_confirm_content_right);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_confirm_btn, this, true);

        initListener();
    }

    private void setData() {
        mBinding.btnConfirm.setText(txtContent);

        if (!TextUtils.isEmpty(txtContentRight)){
            mBinding.btnConfirmRight.setVisibility(VISIBLE);
            mBinding.btnConfirmRight.setText(txtContentRight);
        }
    }

    /**
     * 设置左部按钮点击事件回调
     * @param confirmInterface 点击事件回调
     */
    public void setOnConfirmListener(MyConfirmInterface confirmInterface){
        mConfirmInterface = confirmInterface;
    }

    /**
     * 设置右部按钮点击事件回调
     * @param confirmInterface 点击事件回调
     */
    public void setOnConfirmRightListener(MyConfirmInterface confirmInterface){
        mConfirmInterfaceRight = confirmInterface;
    }

    /**
     * 设置按钮点击事件
     */
    private void initListener() {
        mBinding.btnConfirm.setOnClickListener(view -> {
            if (mConfirmInterface == null)
                return;

            mConfirmInterface.onClick(view);
        });

        mBinding.btnConfirmRight.setOnClickListener(view -> {
            if (mConfirmInterfaceRight == null)
                return;

            mConfirmInterfaceRight.onClick(view);
        });
    }

    /**
     * 设置左部按钮内容文本
     */
    public void setText(String content) {
        mBinding.btnConfirm.setText(content);
    }

    /**
     * 设置右部按钮内容文本
     */
    public void setRightText(String content) {
        mBinding.btnConfirmRight.setText(content);
    }
}
