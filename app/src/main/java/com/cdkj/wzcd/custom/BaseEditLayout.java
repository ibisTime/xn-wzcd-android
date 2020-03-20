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
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutBaseEditBinding;
import com.cdkj.wzcd.util.RequestUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 编辑框Layout
 * Created by cdkj on 2018/5/29.
 */

public class BaseEditLayout extends LinearLayout {

    private LayoutBaseEditBinding mBinding;
    private Context context;

    private String field;
    private String txtTitle;
    private String txtText;
    private boolean isRequired;
    private String inputType;

    public BaseEditLayout(Context context) {
        this(context, null);
    }

    public BaseEditLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BaseEditLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.BaseEditLayout, 0, 0);
        field = typedArray.getString(R.styleable.BaseEditLayout_field);
        txtTitle = typedArray.getString(R.styleable.BaseEditLayout_title);
        txtText = typedArray.getString(R.styleable.BaseEditLayout_text);
        isRequired = typedArray.getBoolean(R.styleable.BaseEditLayout_isRequired, false);
        inputType = typedArray.getString(R.styleable.BaseEditLayout_inputType);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil
                .inflate(LayoutInflater.from(context), R.layout.layout_base_edit, this, true);

    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        mBinding.edtInput.setText(txtText);
        mBinding.edtInput.setHint("请填写" + txtTitle);

        mBinding.tvRequired.setVisibility(isRequired ? VISIBLE : GONE);

        if (!TextUtils.isEmpty(inputType)) {

            switch (inputType) {
                case "0": // 电话
                    mBinding.edtInput.setInputType(InputType.TYPE_CLASS_PHONE);
                    InputFilter[] filtersPhone = {new InputFilter.LengthFilter(11)};
                    mBinding.edtInput.setFilters(filtersPhone);

                    break;

                case "1": // 身份证类型
                    mBinding.edtInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                    InputFilter[] filtersId = {new InputFilter.LengthFilter(18)};
                    mBinding.edtInput.setFilters(filtersId);
                    break;

                case "3": // 金钱类型
                    mBinding.edtInput.setInputType(
                            InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                    //设置字符过滤
                    mBinding.edtInput.setFilters(
                            new InputFilter[]{(source, start, end, dest, dstart, dend) -> {
                                if (source.equals(".") && dest.toString().length() == 0) {
                                    return "0.";
                                }
                                if (dest.toString().contains(".")) {
                                    int index = dest.toString().indexOf(".");
                                    int mlength = dest.toString().substring(index).length();
                                    if (mlength == 3) {
                                        return "";
                                    }
                                }
                                return null;
                            }});

                    break;

                case "4": // 数字类型
                    mBinding.edtInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                    InputFilter[] filtersNumber = {new InputFilter.LengthFilter(9)};
                    mBinding.edtInput.setFilters(filtersNumber);
                    break;

                case "5": // 数字类型
                    mBinding.edtInput.setInputType(
                            InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                    mBinding.tvTitle.setText(txtTitle + "(%)");
                    break;

                default: // 文本
                    mBinding.edtInput.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
            }
        }
    }


    public boolean check() {

        if (!isRequired) {
            return false;
        }

        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString().trim())) {
            ToastUtil.show(context, mBinding.edtInput.getHint().toString());
            return true;
        }

        if (TextUtils.equals(inputType, "0")) {
            //验证手机号
            String regex = "^1[3|4|5|6|7|8|9][0-9]\\d{4,8}$";
            String phone = mBinding.edtInput.getText().toString().trim();
            if (phone.length() != 11) {
                ToastUtil.show(context, "请输入正确的手机号");
                return true;
            } else {
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(phone);
                boolean isMatch = m.matches();
                if (!isMatch) {
                    ToastUtil.show(context, "请输入正确的手机号");
                    return true;
                }
            }
        }
        return false;
    }

    public void isRequired(boolean isRequired) {
        this.isRequired = isRequired;
        mBinding.tvRequired.setVisibility(isRequired ? VISIBLE : GONE);

    }

    public boolean checkNoTip() {

        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString().trim())) {
            return true;
        }

        return false;
    }

    public String getField() {
        return field;
    }

    public String getTitle() {
        return mBinding.tvTitle.getText().toString().trim();
    }

    /**
     * 设置不可输入
     *
     * @param focusable 是否可输入
     */
    public void setFocusable(boolean focusable) {
        mBinding.edtInput.setFocusable(focusable);
        mBinding.edtInput.setHint("");
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


    public void setTextWatcher(TextWatcher watcher) {
        mBinding.edtInput.addTextChangedListener(watcher);
    }

    public void setText(String text) {

        if (!TextUtils.isEmpty(inputType)) {

            switch (inputType) {

                case "3": // 金钱类型
                    setMoneyText(text);
                    break;

                case "5": // 利率类型
                    setRateText(text);
                    break;

                default: // 文本
                    mBinding.edtInput.setText(text);
                    break;
            }
        } else {
            mBinding.edtInput.setText(text);
        }

    }

    /**
     * 获取处理后的金额文本
     *
     * @return 除以1000的金额字符串
     */
    private void setMoneyText(String moneyText) {

        if (TextUtils.isEmpty(moneyText)) {
            return;
        }

        mBinding.edtInput.setText(RequestUtil.formatAmountDiv(moneyText));

    }

    /**
     * 获取处理后的金额文本
     *
     * @return 除以1000的金额字符串
     */
    private void setRateText(String rateText) {

        if (TextUtils.isEmpty(rateText)) {
            return;
        }

        mBinding.edtInput.setText(RequestUtil.formatRateMul(rateText));

    }

    /**
     * 设置布局内容，内容来自于详情或其他请求，此时布局不可输入
     *
     * @param text
     */
    public void setTextByRequest(String text) {
        mBinding.edtInput.setFocusable(false);

        if (!TextUtils.isEmpty(inputType)) {

            switch (inputType) {

                case "3": // 金钱类型
                    setMoneyTextByRequest(text);
                    break;

                case "5": // 利率类型
                    setRateTextByRequest(text);
                    break;

                default: // 文本
                    mBinding.edtInput.setText(text);
                    break;
            }
        } else {
            mBinding.edtInput.setText(text);
        }

    }

    /**
     * 获取处理后的金额文本
     *
     * @return 除以1000的金额字符串
     */
    private void setRateTextByRequest(String rateText) {
        mBinding.edtInput.setText(RequestUtil.formatRateMul(rateText) + "%");

    }

    /**
     * 设置布局内容，内容来自于详情或其他请求，此时布局不可输入
     *
     * @param moneyText
     */
    private void setMoneyTextByRequest(String moneyText) {
        String yuan = RequestUtil.formatAmountDiv(moneyText);

        mBinding.edtInput.setFocusable(false);
        mBinding.edtInput.setText(yuan);

//        if (Double.parseDouble(yuan) > 10000) {
//            double v = Double.parseDouble(yuan) / 10000;
//            DecimalFormat df = new DecimalFormat("#.00");
//            mBinding.edtInput.setText(df.format(v) + "万");
//        } else {
//            mBinding.edtInput.setText(yuan + context.getString(R.string.money_sing));
//        }
    }

    public String getText() {

        String text = null;

        if (!TextUtils.isEmpty(inputType)) {

            switch (inputType) {

                case "3": // 金钱类型
                    text = getMoneyText();
                    break;

                case "5": // 利率类型
                    text = getRateText();
                    break;

                default: // 文本
                    if (!TextUtils.isEmpty(mBinding.edtInput.getText().toString())) {
                        text = mBinding.edtInput.getText().toString();
                    }
            }
        } else {
            if (!TextUtils.isEmpty(mBinding.edtInput.getText().toString())) {
                text = mBinding.edtInput.getText().toString();
            }
        }

        // 去除末尾的符号
        if (null != text) {

            if (text.substring(text.length() - 1, text.length()).equals("%")
                    || text.substring(text.length() - 1, text.length()).equals("¥")
                    || text.substring(text.length() - 1, text.length()).equals("万")
                    || text.substring(text.length() - 1, text.length()).equals("元")) {

                text = text.substring(0, text.length() - 1);

            }

        }

        return text;

    }

    /**
     * 获取处理后的金额文本
     *
     * @return 乘以1000的金额字符串
     */
    private String getMoneyText() {

        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString().trim())) {
            return null;
        }

        return RequestUtil.formatAmountMul(mBinding.edtInput.getText().toString().trim());
    }

    /**
     * 获取处理后的利率文本
     *
     * @return 乘以1000的金额字符串
     */
    private String getRateText() {

        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString().trim())) {
            return null;
        }

        return RequestUtil.formatRateDiv(mBinding.edtInput.getText().toString().trim());
    }


    public Map<String, String> getMap() {

        Map<String, String> map = new HashMap<>();
        map.put(field, getText());
        return map;
    }


}
