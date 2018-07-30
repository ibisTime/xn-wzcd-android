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
import android.widget.LinearLayout;

import com.cdkj.baselibrary.activitys.ImageSelectActivity;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.LayoutMyImageBinding;

/**
 * 图片选择Layout
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

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context),R.layout.layout_my_image, this, true);

        initListener();
    }

    private void setData() {
        mBinding.tvTitle.setText(txtTitle);
        mBinding.tvHint.setHint(txtHint);

        mBinding.flImgRight.setVisibility(isSingle ? INVISIBLE : VISIBLE);

        if (!TextUtils.isEmpty(txtHintRight))
            mBinding.tvHintRight.setHint(txtHintRight);
    }

    /**
     * MyImageLayout 初始化方法
     * @param activity Activity上下文，用于启动ImageSelectActivity
     * @param requestCode 左部图片requestCode，用于相册或相机回调时判断图片显示位置
     * @param rightRequestCode 右部图片requestCode，用于相册或相机回调时判断图片显示位置
     */
    public void setActivity(Activity activity, int requestCode, int rightRequestCode){
        mActivity = activity;
        mRequestCode = requestCode;
        mRightRequestCode = rightRequestCode;
    }

    private void initListener() {
        mBinding.flImg.setOnClickListener(view -> {

            if (TextUtils.isEmpty(FlImgUrl)){

                if (mActivity == null)
                    return;
//                    ToastUtil.show(context, "请先setActivity");

                ImageSelectActivity.launch(mActivity, mRequestCode, false);

            }else {

                // 此处可添加图片预览

            }

        });

        mBinding.flImgRight.setOnClickListener(view -> {

            if (TextUtils.isEmpty(FlImgRightUrl)){

                if (mActivity == null)
                    return;
//                    ToastUtil.show(context, "请先setActivity");

                ImageSelectActivity.launch(mActivity, mRightRequestCode, false);

            }else {

                // 此处可添加图片预览

            }


        });
    }

    /**
     * 左部图片requestCode
     * @return
     */
    public int getRequestCode(){
        return mRequestCode;
    }

    /**
     * 左部图片requestCode
     * @return
     */
    public int getRightRequestCode(){
        return mRightRequestCode;
    }

    /**
     * 检查左部图片选择结果
     * @return
     */
    public String check(){
        if (TextUtils.isEmpty(FlImgUrl)){
            ToastUtil.show(context, mBinding.tvHint.getHint().toString());
            return null;
        }

        return FlImgUrl;
    }

    /**
     * 获取左部图片url
     * @return
     */
    public String getFlImgUrl(){

        return FlImgUrl;
    }

    /**
     * 检查右部图片选择结果
     * @return
     */
    public String checkRight(){
        if (TextUtils.isEmpty(FlImgRightUrl)){
            ToastUtil.show(context, "请上传"+mBinding.tvHintRight.getHint().toString());
            return null;
        }

        return FlImgRightUrl;
    }

    /**
     * 获取右部图片url
     * @return
     */
    public String getFlImgRightUrl(){

        return FlImgRightUrl;
    }

    /**
     * 根据url加载左部图片
     * @param url
     */
    public void setFlImg(String url){
        if (TextUtils.isEmpty(url))
            return;

        FlImgUrl = url;
        ImgUtils.loadQiniuImg(context, FlImgUrl, mBinding.ivImg);

        mBinding.ivHint.setImageResource(R.mipmap.modifi);
        mBinding.tvHint.setText("点击修改");
        mBinding.tvHint.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
    }

    /**
     * 根据url加载右部图片
     * @param url
     */
    public void setFlImgRight(String url){
        if (TextUtils.isEmpty(url))
            return;

        FlImgRightUrl = url;
        ImgUtils.loadQiniuImg(context, FlImgRightUrl, mBinding.ivImgRight);

        mBinding.ivHintRight.setImageResource(R.mipmap.modifi);
        mBinding.tvHintRight.setText("点击修改");
        mBinding.tvHintRight.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
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
