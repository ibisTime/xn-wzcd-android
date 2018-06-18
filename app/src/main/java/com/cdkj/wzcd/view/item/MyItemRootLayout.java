package com.cdkj.wzcd.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyItemRootBinding;

/**
 * Created by cdkj on 2018/5/30.
 */

public class MyItemRootLayout extends LinearLayout {

    private Context context;
    private LayoutMyItemRootBinding mBinding;

    private int resourceId;

    public MyItemRootLayout(Context context) {
        this(context, null);
    }

    public MyItemRootLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyItemRootLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyItemRootLayout, 0, 0);
        resourceId = typedArray.getResourceId(R.styleable.MyItemRootLayout_background, R.drawable.common_my_image_bg);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_item_root, this, true);

    }

    private void setData() {
        this.setBackgroundResource(resourceId);
    }
}
