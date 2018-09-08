package com.cdkj.wzcd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyNormalBinding;
import com.cdkj.wzcd.util.RequestUtil;

import java.text.DecimalFormat;

/**
 * 普通的横条Layout
 * Created by cdkj on 2018/5/29.
 */

public class MyNormalLayout extends LinearLayout {

    private Context context;
    private LayoutMyNormalBinding mBinding;

    private String txtTitle;
    private String txtHint;
    private String txtContent;
    private int resourceId;

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
        txtHint = typedArray.getString(R.styleable.MyNormalLayout_txt_normal_hint);
        txtContent = typedArray.getString(R.styleable.MyNormalLayout_txt_normal_content);
        resourceId = typedArray.getResourceId(R.styleable.MyNormalLayout_img_normal_right, 0);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_my_normal, this, true);

    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        mBinding.tvContent.setHint(txtHint);
        mBinding.tvContent.setText(txtContent);
        mBinding.ivRight.setImageResource(resourceId);

        if (resourceId != 0) {
            mBinding.ivRight.setVisibility(VISIBLE);
        }
    }

    public void setTextByRequest(String content) {
        mBinding.tvContent.setText(content);
    }

    public void setText(String content) {
        if (!TextUtils.isEmpty(content))
            mBinding.tvContent.setText(content);
    }

    public void setTitle(String title) {
        txtTitle = title;
        mBinding.tvTitle.setText(txtTitle);
    }

    /**
     * 获取处理后的金额文本
     *
     * @return 乘以1000的金额字符串
     */
    public String getMoneyText() {

        if (TextUtils.isEmpty(mBinding.tvContent.getText().toString().trim())) {
            return "0";
        }

        return RequestUtil.formatAmountMul(mBinding.tvContent.getText().toString().trim());
    }

    /**
     * 获取处理后的金额文本
     *
     * @return 除以1000的金额字符串
     */
    public void setMoneyText(String moneyText) {
        mBinding.tvContent.setText(RequestUtil.formatAmountDiv(moneyText));
        String yuan = RequestUtil.formatAmountDiv(moneyText);

        if (Double.parseDouble(yuan) > 10000) {
            double v = Double.parseDouble(yuan) / 10000;
            DecimalFormat df = new DecimalFormat("#.00");
            mBinding.tvContent.setText(df.format(v));
            mBinding.tvTitleRight.setText("万");
            mBinding.tvTitleRight.setVisibility(VISIBLE);
        } else {
            mBinding.tvContent.setText(yuan);
            mBinding.tvTitleRight.setText(context.getString(R.string.money_sing));
            mBinding.tvTitleRight.setVisibility(VISIBLE);
        }
    }

    public String check() {

        if (TextUtils.isEmpty(mBinding.tvContent.getText().toString().trim())) {
            ToastUtil.show(context, mBinding.tvContent.getHint().toString());
            return "";
        }

        return mBinding.tvContent.getText().toString();
    }

    public String getText() {
        return mBinding.tvContent.getText().toString();
    }

    public String getTags() {
        return mBinding.tvContent.getTag().toString();
    }

}
