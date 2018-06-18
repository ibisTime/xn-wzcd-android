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

    private void setData() {
        mBinding.btnConfirm.setText(txtContent);

        if (!TextUtils.isEmpty(txtContentRight)){
            mBinding.btnConfirmRight.setVisibility(VISIBLE);
            mBinding.btnConfirmRight.setText(txtContentRight);
        }
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_confirm_btn, this, true);

        initListener();
    }

    public void setOnConfirmListener(MyConfirmInterface confirmInterface){
        mConfirmInterface = confirmInterface;
    }

    public void setOnConfirmRightListener(MyConfirmInterface confirmInterface){
        mConfirmInterfaceRight = confirmInterface;
    }



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

    public void setText(String content) {
        mBinding.btnConfirm.setText(content);
    }
}
