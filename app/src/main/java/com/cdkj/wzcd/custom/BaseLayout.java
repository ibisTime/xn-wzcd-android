package com.cdkj.wzcd.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutBaseBinding;
import com.cdkj.wzcd.databinding.LayoutMyNormalBinding;
import com.cdkj.wzcd.util.RequestUtil;

import java.text.DecimalFormat;

/**
 * 普通的横条Layout
 * Created by cdkj on 2018/5/29.
 */

public class BaseLayout extends LinearLayout {

    private Context context;
    private LayoutBaseBinding mBinding;

    private String txtTitle;
    private String txtRightTitle;

    public BaseLayout(Context context) {
        this(context, null);
    }

    public BaseLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.BaseLayout, 0, 0);
        txtTitle = typedArray.getString(R.styleable.BaseLayout_title);
        txtRightTitle = typedArray.getString(R.styleable.BaseLayout_rightTitle);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.layout_base, this, true);

    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);

        if (!TextUtils.isEmpty(txtRightTitle)) {

            mBinding.tvRight.setVisibility(VISIBLE);
            mBinding.tvRight.setText(txtRightTitle);
        }

    }

    public void setTextByRequest(String content) {
        mBinding.tvText.setText(content);
    }

    public void setText(String content) {
        if (!TextUtils.isEmpty(content)) {
            mBinding.tvText.setText(content);
        }
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

        if (TextUtils.isEmpty(mBinding.tvText.getText().toString().trim())) {
            return "0";
        }

        return RequestUtil.formatAmountMul(mBinding.tvText.getText().toString().trim());
    }

    /**
     * 获取处理后的金额文本
     *
     * @return 除以1000的金额字符串
     */
    public void setMoneyText(String moneyText) {
        mBinding.tvText.setText(RequestUtil.formatAmountDiv(moneyText));
        String yuan = RequestUtil.formatAmountDiv(moneyText);

        if (Double.parseDouble(yuan) > 10000) {
            double v = Double.parseDouble(yuan) / 10000;
            DecimalFormat df = new DecimalFormat("#.00");
            mBinding.tvText.setText(df.format(v) + "万");
        } else {
            mBinding.tvText.setText(yuan + context.getString(R.string.money_sing));
        }
    }

    public String check() {

        if (TextUtils.isEmpty(mBinding.tvText.getText().toString().trim())) {
            ToastUtil.show(context, mBinding.tvText.getHint().toString());
            return "";
        }

        return mBinding.tvText.getText().toString();
    }

    public String getText() {
        return mBinding.tvText.getText().toString();
    }


    public String getTags() {
        return mBinding.tvText.getTag().toString();
    }


    public void setRightListener(OnClickListener listener) {
        mBinding.tvRight.setOnClickListener(listener);
    }
}
