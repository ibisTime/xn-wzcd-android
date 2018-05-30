package com.cdkj.wzcd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdkj.wzcd.R;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MyInputHorizontalLayout extends LinearLayout {

    private Context context;

    private String txtHint;
    private String txtTitle;

    private EditText edtInput;
    private TextView tvTitle;

    public MyInputHorizontalLayout(Context context) {
        this(context, null);
    }

    public MyInputHorizontalLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyInputHorizontalLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyInputHorizontalLayout, 0, 0);
        txtHint = typedArray.getString(R.styleable.MyInputHorizontalLayout_txt_input_hint);
        txtTitle = typedArray.getString(R.styleable.MyInputHorizontalLayout_txt_input_title);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void setData() {
        tvTitle.setText(txtTitle);
        if (!TextUtils.isEmpty(txtHint))
            edtInput.setHint(txtHint);
    }


    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_my_input_horizontal, this, true);

        tvTitle = findViewById(R.id.tv_title);
        edtInput = findViewById(R.id.edt_input);
    }
}
