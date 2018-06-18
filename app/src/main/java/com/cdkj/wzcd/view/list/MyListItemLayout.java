package com.cdkj.wzcd.view.list;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyListItemBinding;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MyListItemLayout extends LinearLayout {

    private Context context;
    private LayoutMyListItemBinding mBinding;

    private String txtTitle;
    private String txtContent;

    public MyListItemLayout(Context context) {
        this(context, null);
    }

    public MyListItemLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListItemLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyListItemLayout, 0, 0);
        txtTitle = typedArray.getString(R.styleable.MyListItemLayout_txt_list_item_title);
        txtContent = typedArray.getString(R.styleable.MyListItemLayout_txt_list_item_content);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        mBinding.tvContent.setHint(txtContent);

    }


    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_list_item, this, true);

    }

    public void setText(String content){
        mBinding.tvContent.setText(content);
    }


}
