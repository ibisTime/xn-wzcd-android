package com.cdkj.wzcd.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cdkj.baselibrary.activitys.ImageSelectActivity;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyImageBinding;

/**
 * Created by cdkj on 2018/5/29.
 */

public class MyImageLayout extends LinearLayout {

    private Context context;
    private LayoutMyImageBinding mBinding;

    private Activity mActivity;
    private int mRequestCode;
    private int mRightRequestCode;

    private String txtHint;
    private String txtTitle;
    private boolean isSingle;

    private String txtHintRight;

    private String FlImgUrl = "";
    private String FlImgRightUrl = "";

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

    public void setActivity(Activity activity, int requestCode, int rightRequestCode){
        mActivity = activity;
        mRequestCode = requestCode;
        mRightRequestCode = rightRequestCode;
    }

    private void initListener() {
        mBinding.flImg.setOnClickListener(view -> {

            if (mActivity == null)
                ToastUtil.show(context, "请先setActivity");

            ImageSelectActivity.launch(mActivity, mRequestCode, false);

        });

        mBinding.flImgRight.setOnClickListener(view -> {

            if (mActivity == null)
                ToastUtil.show(context, "请先setActivity");

            ImageSelectActivity.launch(mActivity, mRightRequestCode, false);

        });
    }

    public int getRequestCode(){
        return mRequestCode;
    }

    public int getRightRequestCode(){
        return mRightRequestCode;
    }

    public String check(){
        if (TextUtils.isEmpty(FlImgUrl)){
            ToastUtil.show(context, "请上传"+mBinding.tvHint.getHint().toString());
            return null;
        }

        return FlImgUrl;
    }

    public String getFlImgUrl(){

        return FlImgUrl;
    }

    public String checkRight(){
        if (TextUtils.isEmpty(FlImgRightUrl)){
            ToastUtil.show(context, "请上传"+mBinding.tvHintRight.getHint().toString());
            return null;
        }

        return FlImgRightUrl;
    }

    public String getFlImgRightUrl(){

        return FlImgRightUrl;
    }

    public void setFlImg(String url){
        if (TextUtils.isEmpty(url))
            return;

        FlImgUrl = url;
        ImgUtils.loadQiniuImg(context, FlImgUrl, mBinding.ivImg);

        mBinding.ivHint.setImageResource(R.mipmap.modifi);
        mBinding.tvHint.setText("点击修改");
        mBinding.tvHint.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
    }

    public ImageView getFlImgImageView(){
        return mBinding.ivImg;
    }


    public void setFlImgRight(String url){
        if (TextUtils.isEmpty(url))
            return;

        FlImgRightUrl = url;
        ImgUtils.loadQiniuImg(context, FlImgRightUrl, mBinding.ivImgRight);

        mBinding.ivHintRight.setImageResource(R.mipmap.modifi);
        mBinding.tvHintRight.setText("点击修改");
        mBinding.tvHintRight.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
    }

    public ImageView getFlImgRightImageView(){
        return mBinding.ivImgRight;
    }

    private int formatViewId(View view){
        // 处理ViewId 不能为负值,也不能大于16位bit值65536
        String str = view.getId()+"";
        int id = Integer.parseInt(str.substring(str.length()-5, str.length()));

        if (id > 65536){
            id = id - 65536;
        }

        return id;
    }

    /**
     * 加载图片并取消点击事件和隐藏View
     * @param url
     */
    public void setFlImgByRequest(String url){

        mBinding.llHint.setVisibility(GONE);
        mBinding.flImg.setOnClickListener(null);

        FlImgUrl = url;
        ImgUtils.loadQiniuImg(context, FlImgUrl, mBinding.ivImg);


    }

    /**
     * 加载图片并取消点击事件和隐藏View
     * @param url
     */
    public void setFlImgRightByRequest(String url){

        mBinding.llHintRight.setVisibility(GONE);
        mBinding.flImgRight.setOnClickListener(null);

        FlImgRightUrl = url;
        ImgUtils.loadQiniuImg(context, FlImgRightUrl, mBinding.ivImgRight);


    }
}
