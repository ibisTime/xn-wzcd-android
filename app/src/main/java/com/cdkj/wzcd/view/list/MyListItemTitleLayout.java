package com.cdkj.wzcd.view.list;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyListItemTitleBinding;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MyListItemTitleLayout extends LinearLayout {

    private Context context;
    private LayoutMyListItemTitleBinding mBinding;

    private String txtLeft;
    private String txtRight;

    public MyListItemTitleLayout(Context context) {
        this(context, null);
    }

    public MyListItemTitleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListItemTitleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyListItemTitleLayout, 0, 0);
        txtLeft = typedArray.getString(R.styleable.MyListItemTitleLayout_txt_list_item_left);
        txtRight = typedArray.getString(R.styleable.MyListItemTitleLayout_txt_list_item_right);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void setData() {
        mBinding.tvLeft.setText(txtLeft);
        mBinding.tvRight.setHint(txtRight);

    }


    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_list_item_title, this, true);

    }

    public void setText(String left, String right){
        mBinding.tvLeft.setText(left);
        mBinding.tvRight.setText(right);
    }


}
