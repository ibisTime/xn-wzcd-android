package com.cdkj.wzcd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyInputHorizontalBinding;
import com.cdkj.wzcd.util.RequestUtil;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MyEditLayout extends LinearLayout {

    private LayoutMyInputHorizontalBinding mBinding;
    private Context context;

    private String txtHint;
    private String txtTitle;
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
        txtTitleRight = typedArray.getString(R.styleable.MyEditLayout_txt_input_title_right);
        inputType = typedArray.getString(R.styleable.MyEditLayout_inputType);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        mBinding.tvTitleRight.setText(txtTitleRight);
        mBinding.tvTitleRight.setVisibility(!TextUtils.isEmpty(txtTitleRight)? VISIBLE : GONE);


        if (!TextUtils.isEmpty(txtHint))
            mBinding.edtInput.setHint(txtHint);

        if (!TextUtils.isEmpty(inputType)){

            switch (inputType){
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


    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_input_horizontal, this, true);

    }

    public String check(){

        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString().trim())){
            ToastUtil.show(context, mBinding.edtInput.getHint().toString());
            return "";
        }

        return mBinding.edtInput.getText().toString();
    }

    public void setText(String content){
        mBinding.edtInput.setText(content);
    }

    /**
     * 设置布局内容，内容来自于详情或其他请求，此时布局不可输入
     * @param content
     */
    public void setTextByRequest(String content){
        mBinding.edtInput.setText(content);
        mBinding.edtInput.setFocusable(false);
    }

    public String getText(){
        return mBinding.edtInput.getText().toString();
    }

    /**
     * 获取处理后的金额文本
     * @return 乘以1000的金额字符串
     */
    public String getMoneyText(){

        if (TextUtils.isEmpty(mBinding.edtInput.getText().toString().trim())){
            return "";
        }

        return RequestUtil.formatAmountMul(mBinding.edtInput.getText().toString().trim());
    }

    /**
     * 获取处理后的金额文本
     * @return 除以1000的金额字符串
     */
    public void setMoneyText(String moneyText){

        mBinding.edtInput.setText(RequestUtil.formatAmountDivSign(moneyText));
    }

    /**
     * 设置布局内容，内容来自于详情或其他请求，此时布局不可输入
     * @param moneyText
     */
    public void setMoneyTextByRequest(String moneyText){
        mBinding.edtInput.setText(RequestUtil.formatAmountDivSign(moneyText));
        mBinding.edtInput.setFocusable(false);
    }
}
