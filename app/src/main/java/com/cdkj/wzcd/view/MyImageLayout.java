package com.cdkj.wzcd.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cdkj.baselibrary.activitys.ImageSelectActivity;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyImageBinding;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MyImageLayout extends LinearLayout {

    private Context context;
    private Activity mActivity;
    private LayoutMyImageBinding mBinding;

    private String txtHint;
    private String txtTitle;
    private boolean isSingle;

    private String txtHintRight;

    public MyImageLayout(Context context) {
        this(context, null);
    }

    public MyImageLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyImageLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyImageLayout, 0, 0);
        txtHint = typedArray.getString(R.styleable.MyImageLayout_txt_image_hint);
        txtTitle = typedArray.getString(R.styleable.MyImageLayout_txt_image_title);
        isSingle = typedArray.getBoolean(R.styleable.MyImageLayout_isSingle, true);

        txtHintRight = typedArray.getString(R.styleable.MyImageLayout_txt_image_hint_right);

        typedArray.recycle();

        init(context);
        setData();
    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        mBinding.tvHint.setHint(txtHint);

        mBinding.flImgRight.setVisibility(isSingle ? INVISIBLE : VISIBLE);

        if (!TextUtils.isEmpty(txtHintRight))
            mBinding.tvHintRight.setHint(txtHintRight);
    }


    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_image, this, true);

        initListener();
    }

    public void setData(Activity activity){
        mActivity = activity;
    }

    private void initListener() {
        mBinding.flImg.setOnClickListener(view -> {

            ImageSelectActivity.launch(mActivity, formatViewId(mBinding.flImg), false);

        });

        mBinding.flImgRight.setOnClickListener(view -> {

            // 处理ViewId 不能为负值,也不能大于16位bit值65536
            String str = mBinding.flImgRight.getId()+"";
            String id = str.substring(str.length()-5, str.length());

            ImageSelectActivity.launch(mActivity, formatViewId(mBinding.flImgRight), false);

        });
    }

    public int getFlImgViewId(){

        return formatViewId(mBinding.flImg);
    }

    public ImageView getFlImgImageView(){
        return mBinding.ivImg;
    }

    public int getFlImgRithtViewId(){
        return formatViewId(mBinding.flImgRight);
    }

    public ImageView getFlImgRightImageView(){
        return mBinding.ivImgRight;
    }

    private int formatViewId(View view){
        // 处理ViewId 不能为负值,也不能大于16位bit值65536
        String str = view.getId()+"";
        int id = Integer.parseInt(str.substring(str.length()-5, str.length()));

        if (id > 65536){
            id = id - (100000 - 65536);
        }

        return id;
    }
}
