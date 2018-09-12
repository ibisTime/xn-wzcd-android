package com.cdkj.wzcd.view;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cdkj.baselibrary.activitys.ImageSelectActivity;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adapter.MyMultipleAdapter;
import com.cdkj.wzcd.databinding.LayoutMyMultipleBinding;
import com.cdkj.wzcd.view.manager.FullyGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * 多选Layout
 * Created by cdkj on 2018/6/26.
 */

public class MyMultipleLayout extends LinearLayout {

    private LayoutMyMultipleBinding mBinding;

    private Context context;

    private Activity mActivity;

    private int mRequestCode;
    private String tvTitle;


    public static String ADD = "add";

    private List<String> mList = new ArrayList<>();

    private MyMultipleAdapter adapter;
    private boolean isadd = true;//是否有添加功能
    boolean isFrist = true;//是不是第一次

    public MyMultipleLayout(Context context) {
        this(context, null);
    }

    public MyMultipleLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyMultipleLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        final TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyLayout, 0, 0);
        tvTitle = typedArray.getString(R.styleable.MyLayout_title);

        init(context);
        setData();
    }

    private void init(Context context) {
        this.context = context;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.layout_my_multiple, this, true);
    }


    private void setData() {
        mBinding.tvTitle.setText(tvTitle);

    }

    public void setTitle(String title) {
        mBinding.tvTitle.setText(title);

    }

    /**
     * MyMultipleLayout 初始化方法
     *
     * @param activity    Activity上下文，用于启动ImageSelectActivity和传入FullyGridLayoutManager
     * @param requestCode 多选Layout图片requestCode，用于相册或相机回调时判断图片显示Layout
     */
    public void build(Activity activity, int maxSize, int requestCode) {
        mActivity = activity;
        mRequestCode = requestCode;

        mList.clear();
        mList.add(ADD);

        FullyGridLayoutManager manager = new FullyGridLayoutManager(mActivity, 3, GridLayoutManager.VERTICAL, false);
        mBinding.rvMultiple.setLayoutManager(manager);
        mBinding.rvMultiple.setNestedScrollingEnabled(false);

        adapter = new MyMultipleAdapter(mList, listener);
//        adapter.setList(selectList);
        mBinding.rvMultiple.setAdapter(adapter);


//        adapter.setOnItemClickListener((adapter, view, position) -> {
//            if (isadd) {
//                if (position != 0) {
//                    BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(mActivity)
//                            .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能
//                    photoPreviewIntentBuilder.previewPhoto("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1536235318&di=40753b5008a15bdf31dd8daf6681f59e&src=http://img0.pconline.com.cn/pconline/1703/30/9027001_53_thumb.jpg");
//                    mActivity.startActivity(photoPreviewIntentBuilder.build());
//
//                }
//            } else {
//                BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(mActivity)
//                        .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能
//                photoPreviewIntentBuilder.previewPhoto("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1536235318&di=40753b5008a15bdf31dd8daf6681f59e&src=http://img0.pconline.com.cn/pconline/1703/30/9027001_53_thumb.jpg");
//                mActivity.startActivity(photoPreviewIntentBuilder.build());
//
//            }
//        });

//        adapter.setOnItemClickListener((position, v) -> {
//            if (selectList.size() > 0) {
//                LocalMedia media = selectList.get(position);
//                String pictureType = media.getPictureType();
//                int mediaType = PictureMimeType.pictureToVideo(pictureType);
//                switch (mediaType) {
//                    case 1:
//                        // 预览图片 可自定长按保存路径
//                        PictureSelector.create(mActivity).themeStyle(themeId).openExternalPreview(position, selectList);
//                        break;
//                    case 2:
//                        // 预览视频
//                        PictureSelector.create(mActivity).externalPictureVideo(media.getPath());
//                        break;
//                    case 3:
//                        // 预览音频
//                        PictureSelector.create(mActivity).externalPictureAudio(media.getPath());
//                        break;
//                }
//            }
//        });
    }

    /**
     * 相册或相机回调后向mList添加图片
     *
     * @param url
     */
    public void addList(String url) {
        mList.add(url);
        adapter.notifyDataSetChanged();
    }

    /**
     * 相册或相机回调后向mList添加图片
     *
     * @param urls
     */
    public void addList(List urls) {
        if (urls == null || urls.size() == 0)
            return;
        mList.addAll(urls);
        adapter.notifyDataSetChanged();
    }

    /**
     * 相册或相机回调后向mList添加图片
     *
     * @param urls
     */
    public void addListRequest(List urls) {

        isadd = false;
        adapter = new MyMultipleAdapter(mList, true, listener);
        mBinding.rvMultiple.setAdapter(adapter);
        if (mList != null && mList.size() > 0) {
            if (TextUtils.equals(mList.get(0), ADD)) {
                mList.remove(0);
            }
        }
        if (urls == null || urls.size() == 0) {
            adapter.notifyDataSetChanged();
            return;
        }
        mList.addAll(urls);
        adapter.notifyDataSetChanged();
    }

    /**
     * 获取图片List数据
     *
     * @return
     */
    public String getListData() {
        //是否是第一次调用  第一次调用才会remove(0);   否则如果在activity中多次调用的话会每次都remove(0);   导致数据丢失
        if (isFrist) {
            if (isadd) {
                if (mList.size() > 0) {
                    mList.remove(0);
                }
            }
        } else {
            isFrist = false;
        }


        return StringUtils.listToString(mList, "||");
    }

    /**
     * 图片多选Layout requestCode
     *
     * @return
     */
    public int getRequestCode() {
        return mRequestCode;
    }


    private MyMultipleAdapter.OnAddPicClickListener listener = new MyMultipleAdapter.OnAddPicClickListener() {
        @Override
        public void onAddPicClick() {
            ImageSelectActivity.launch(mActivity, mRequestCode, false);
        }
    };

}
