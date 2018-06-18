package com.cdkj.wzcd.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.cdkj.wzcd.R;

/**
 * Created by cdkj on 2018/6/5.
 */

public class MyItemChoiceLayout extends LinearLayout {

    private int imgId;
    private String txtTitle;

    public MyItemChoiceLayout(Context context) {
        this(context, null);
    }

    public MyItemChoiceLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyItemChoiceLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyItemChoiceLayout, 0, 0);
        txtTitle = typedArray.getString(R.styleable.MyItemChoiceLayout_txt_item_choice_title);
        imgId = typedArray.getResourceId(R.styleable.MyItemChoiceLayout_txt_item_choice_img, R.drawable.default_pic);

        typedArray.recycle();

//        init(context);
//        setData();
    }
}
