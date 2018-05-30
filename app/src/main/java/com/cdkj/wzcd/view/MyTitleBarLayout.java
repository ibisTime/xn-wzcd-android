package com.cdkj.wzcd.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.wzcd.R;

/**
 * Created by cdkj on 2018/5/28.
 */

public class MyTitleBarLayout extends LinearLayout {

    private Context context;

    private View vTitle;
    private String txtTitle;
    private TextView tvTitle;

    public MyTitleBarLayout(Context context) {
        this(context, null);
    }

    public MyTitleBarLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTitleBarLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTitleBarLayout, 0, 0);
        txtTitle = typedArray.getString(R.styleable.MyTitleBarLayout_txt_title);
        typedArray.recycle();

        init(context);

        setData();
    }

    private void setData() {
        tvTitle.setText(txtTitle);
    }

    public void setTvTitle(String txt) {
        tvTitle.setText(txt);
    }


    private void init(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.layout_my_title_bar, this, true);

        tvTitle = findViewById(R.id.tv_title);
    }

}
