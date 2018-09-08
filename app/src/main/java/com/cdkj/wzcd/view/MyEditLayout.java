package com.cdkj.wzcd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.Editable;
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
import com.cdkj.wzcd.databinding.LayoutMyInputHorizontalBinding;
import com.cdkj.wzcd.util.RequestUtil;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 编辑框Layout
 * Created by cdkj on 2018/5/29.
 */

public class MyEditLayout extends LinearLayout {

    private LayoutMyInputHorizontalBinding mBinding;
    private Context context;

    private String txtHint;
    private String txtTitle;
    private String txtContent;
    private String txtTitleRight;
    private String inputType;

    public MyEditLayout(Context context) {
        this(context, null);
    }

    public MyEditLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyEditLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyEditLayout, 0, 0);
        txtHint = typedArray.getString(R.styleable.MyEditLayout_txt_input_hint);
        txtTitle = typedArray.getString(R.styleable.MyEditLayout_txt_input_title);
        txtContent = typedArray.getString(R.styleable.MyEditLayout_txt_input_content);
        txtTitleRight = typedArray.getString(R.styleable.MyEditLayout_txt_input_title_right);
        inputType = typedArray.getString(R.styleable.MyEditLayout_inputType);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_my_input_horizontal, this, true);

    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        mBinding.tvTitleRight.setText(txtTitleRight);
        mBinding.edtInput.setText(txtContent);
        mBinding.tvTitleRight.setVisibility(!TextUtils.isEmpty(txtTitleRight) ? VISIBLE : GONE);

        if (!TextUtils.isEmpty(txtHint))
            mBinding.edtInput.setHint(txtHint);

        if (!TextUtils.isEmpty(inputType)) {

            switch (inputType) {
                case "0": // 电话
                    mBinding.edtInput.setInputType(InputType.TYPE_CLASS_PHONE);
                    InputFilter[] filtersPhone = {new InputFilter.LengthFilter(11)};
                    mBinding.edtInput.setFilters(filtersPhone);


                    mBinding.edtInput.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            String phone = editable.toString();
                            if (phone.length() >= 2) {
                                //判断第一位
                                if (!TextUtils.equals("1", phone.substring(0, 1))) {
                                    ToastUtil.show(context, "格式错误");
                                    mBinding.edtInput.setText("");
                                    return;
                                }
                                //判断第二位
                                String two = "345678";
                                if (!two.contains(phone.substring(1, 2))) {
                                    ToastUtil.show(context, "格式错误");
                                    mBinding.edtInput.setText(phone.substring(0, 1));
                                    return;
                                }
                                //判断第三位
//                                不用判断  因为  第三位是0-9  并且只能输入数字
                            }
                        }
                    });
                    break;

                case "1": // 身份证类型
                    mBinding.edtInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                    InputFilter[] filtersId = {new InputFilter.LengthFilter(18)};
                    mBinding.edtInput.setFilters(filtersId);
                    break;

                case "3": // 金钱类型
                    mBinding.edtInput.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_NUMBER);
                    //设置字符过滤
                    mBinding.edtInput.setFilters(new InputFilter[]{(source, start, end, dest, dstart, dend) -> {
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

                default: // 文本
                    mBinding.edtInput.setInputType(InputType.TYPE_CLASS_TEXT);
                    break;
            }
        }
    }


    public boolean check() {

        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString().trim())) {
            ToastUtil.show(context, mBinding.edtInput.getHint().toString());
            return true;
        }

        if (TextUtils.equals(inputType, "0")) {
            //验证手机号
            String regex = "^1[3|4|5|7|8][0-9]\\d{4,8}$";
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
        return mBinding.edtInput.getText().toString();
    }

    /**
     * 获取处理后的金额文本
     *
     * @return 乘以1000的金额字符串
     */
    public String getMoneyText() {

        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString().trim())) {
            return "0";
        }

        return RequestUtil.formatAmountMul(mBinding.edtInput.getText().toString().trim());
    }

    /**
     * 获取处理后的金额文本
     *
     * @return 除以1000的金额字符串
     */
    public void setMoneyText(String moneyText) {

        mBinding.edtInput.setText(RequestUtil.formatAmountDiv(moneyText));

        mBinding.tvTitleRight.setText(context.getString(R.string.money_sing));
        mBinding.tvTitleRight.setVisibility(VISIBLE);
    }

    /**
     * 获取处理后的金额文本
     *
     * @return 除以1000的金额字符串
     */
    public void setMoneyTextRequest(String moneyText) {

        mBinding.edtInput.setFocusable(false);
        String yuan = RequestUtil.formatAmountDiv(moneyText);

        if (Double.parseDouble(yuan) > 10000) {
            double v = Double.parseDouble(yuan) / 10000;
            DecimalFormat df = new DecimalFormat("#.00");
            mBinding.edtInput.setText(df.format(v));
            mBinding.tvTitleRight.setText("万");
            mBinding.tvTitleRight.setVisibility(VISIBLE);
        } else {
            mBinding.edtInput.setText(yuan);
            mBinding.tvTitleRight.setText(context.getString(R.string.money_sing));
            mBinding.tvTitleRight.setVisibility(VISIBLE);
        }


    }

    /**
     * 设置布局内容，内容来自于详情或其他请求，此时布局不可输入
     *
     * @param moneyText
     */
    public void setMoneyTextByRequest(String moneyText) {
        String yuan = RequestUtil.formatAmountDiv(moneyText);

        mBinding.edtInput.setFocusable(false);
        if (Double.parseDouble(yuan) > 10000) {
            double v = Double.parseDouble(yuan) / 10000;
            DecimalFormat df = new DecimalFormat("#.00");
            mBinding.edtInput.setText(df.format(v));
            mBinding.tvTitleRight.setText("万");
            mBinding.tvTitleRight.setVisibility(VISIBLE);
        } else {
            mBinding.edtInput.setText(yuan);
            mBinding.tvTitleRight.setText(context.getString(R.string.money_sing));
            mBinding.tvTitleRight.setVisibility(VISIBLE);
        }
    }

    public EditText getEditText() {
        return mBinding.edtInput;
    }

    public void setTextWatcher(TextWatcher watcher) {
        mBinding.edtInput.addTextChangedListener(watcher);
    }

    /**
     * 输入的内容为  靠左垂直居中  默认是  靠右垂直居中
     */
    public void setInputCountenLeft() {
        mBinding.edtInput.setGravity(Gravity.CENTER_VERTICAL);
    }

}
