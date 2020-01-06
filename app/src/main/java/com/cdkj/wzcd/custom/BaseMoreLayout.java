package com.cdkj.wzcd.custom;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutBaseMoreBinding;
import com.cdkj.wzcd.databinding.LayoutMyEditSelectBinding;
import com.cdkj.wzcd.view.interfaces.MySelectInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * 可编辑的下拉框Layout
 * Created by cdkj on 2018/5/29.
 */

public class BaseMoreLayout extends LinearLayout {

    private Context context;
    private LayoutBaseMoreBinding mBinding;

    private String field;
    private String txtTitle;
    private String txtContent;

    public BaseMoreLayout(Context context) {
        this(context, null);
    }

    public BaseMoreLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseMoreLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.BaseMoreLayout, 0, 0);
        field = typedArray.getString(R.styleable.BaseMoreLayout_field);
        txtTitle = typedArray.getString(R.styleable.BaseMoreLayout_title);
        txtContent = typedArray.getString(R.styleable.BaseMoreLayout_text);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.layout_base_more, this, true);
    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        if (!TextUtils.isEmpty(txtContent)) {
            mBinding.tvContent.setText(txtContent);
        }
    }

    public String getText() {
        if (TextUtils.isEmpty(mBinding.tvContent.getText().toString().trim())) {
            return "";
        }
        return mBinding.tvContent.getText().toString().trim();

    }

    public void setTitle(String title) {

        mBinding.tvTitle.setText(title);

    }

    public void setText(String text) {

        mBinding.tvContent.setText(text);

    }

    /**
     * 设置布局内容，内容来自于详情或其他请求，此时布局不应相应点击事件
     *
     * @param text
     */
    public void setTextByRequest(String text) {
        // 隐藏更多
        mBinding.ivRight.setVisibility(INVISIBLE);
        // 设置内容
        mBinding.tvContent.setText(text);
        mBinding.tvContent.setFocusable(false);
    }


    public String getTitle() {
        return mBinding.tvTitle.getText().toString().trim();
    }

}
