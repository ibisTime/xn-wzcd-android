package com.cdkj.wzcd.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutBaseEditBinding;
import com.cdkj.wzcd.databinding.LayoutBaseRemarkBinding;
import com.cdkj.wzcd.util.RequestUtil;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 编辑框Layout
 * Created by cdkj on 2018/5/29.
 */

public class BaseRemarkLayout extends LinearLayout {

    private LayoutBaseRemarkBinding mBinding;
    private Context context;

    private String field;
    private String txtTitle;
    private String txtText;
    private boolean isRequired;

    public BaseRemarkLayout(Context context) {
        this(context, null);
    }

    public BaseRemarkLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseRemarkLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.BaseRemarkLayout, 0, 0);
        field = typedArray.getString(R.styleable.BaseRemarkLayout_field);
        txtTitle = typedArray.getString(R.styleable.BaseRemarkLayout_title);
        txtText = typedArray.getString(R.styleable.BaseRemarkLayout_text);
        isRequired = typedArray.getBoolean(R.styleable.BaseRemarkLayout_isRequired, false);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.layout_base_remark, this, true);

    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        mBinding.edtInput.setText(txtText);
        mBinding.edtInput.setHint("请输入" + txtTitle);

        mBinding.tvRequired.setVisibility(isRequired ? VISIBLE : GONE);


    }


    public boolean check() {

        if (!isRequired) {
            return true;
        }

        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString().trim())) {
            ToastUtil.show(context, mBinding.edtInput.getHint().toString());
            return true;
        }

        return false;
    }

    public String getTitle() {
        return mBinding.tvTitle.getText().toString().trim();
    }


    public void setText(String content) {
        mBinding.edtInput.setText(content);
    }

    /**
     * 设置布局内容，内容来自于详情或其他请求，此时布局不可输入
     *
     * @param content
     */
    public void setTextByRequest(String content) {
        mBinding.edtInput.setText(content);
        mBinding.edtInput.setFocusable(false);
    }

    /**
     * 设置布局内容hint，内容来自于详情或其他请求，此时布局不可输入
     *
     * @param hint 提示内容
     */
    public void setTextHint(String hint) {
        if (TextUtils.isEmpty(hint)) {
            mBinding.edtInput.setHint("");
            return;
        }
        mBinding.edtInput.setHint(hint);
    }

    public String getText() {
        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString())){
            return null;
        }

        return mBinding.edtInput.getText().toString();
    }

    public void setFocusable(boolean focusable) {
        mBinding.edtInput.setFocusable(focusable);
        mBinding.edtInput.setHint("");
    }

    public Map<String, String> getMap() {

        Map<String, String> map = new HashMap<>();
        map.put(field, getText());
        return map;
    }

}
