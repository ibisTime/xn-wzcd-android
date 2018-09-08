package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemMyMultipleBinding;
import com.cdkj.wzcd.view.MyMultipleLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPreviewActivity;

/**
 * Created by cdkj on 2018/6/26.
 */

public class MyMultipleAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private boolean isdetails;
    private ItemMyMultipleBinding mBinding;

    private int ADD = 0;
    private int NORMAL = 1;

    /**
     * 点击添加图片跳转
     */
    private OnAddPicClickListener mOnAddPicClickListener;

    public interface OnAddPicClickListener {
        void onAddPicClick();
    }


    public MyMultipleAdapter(@Nullable List<String> data, OnAddPicClickListener mOnAddPicClickListener) {
        super(R.layout.item_my_multiple, data);
        this.mOnAddPicClickListener = mOnAddPicClickListener;

    }

    public MyMultipleAdapter(@Nullable List<String> data, boolean isdetails, OnAddPicClickListener mOnAddPicClickListener) {
        super(R.layout.item_my_multiple, data);
        this.mOnAddPicClickListener = mOnAddPicClickListener;
        this.isdetails = isdetails;

    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
        if (TextUtils.isEmpty(item))
            return;

        mBinding = DataBindingUtil.bind(helper.itemView);


        if (TextUtils.equals(item, MyMultipleLayout.ADD)) {

            mBinding.llDel.setVisibility(View.GONE);
            mBinding.flImg.setOnClickListener(view -> {
                mOnAddPicClickListener.onAddPicClick();
            });


        } else {

            ImgUtils.loadQiniuImg(mContext, item, mBinding.ivImg);

            if (isdetails) {
                //这里应该可以点击查看大图
                mBinding.llHint.setVisibility(View.INVISIBLE);
                mBinding.llDel.setVisibility(View.INVISIBLE);
                mBinding.ivImg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(mContext)
                                .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能

//                        photoPreviewIntentBuilder.previewPhoto("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1536235318&di=40753b5008a15bdf31dd8daf6681f59e&src=http://img0.pconline.com.cn/pconline/1703/30/9027001_53_thumb.jpg");
                        photoPreviewIntentBuilder.previewPhoto(MyCdConfig.QINIU_URL + item);
                        mContext.startActivity(photoPreviewIntentBuilder.build());
                    }
                });


            } else {
                mBinding.llHint.setVisibility(View.VISIBLE);
                mBinding.llDel.setVisibility(View.VISIBLE);
                //这里是点击修改和删除
                mBinding.ivHint.setImageResource(R.mipmap.modifi);
                mBinding.tvHint.setText("点击修改");
                mBinding.tvHint.setTextColor(ContextCompat.getColor(mContext, R.color.white));

                mBinding.tvHint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BGAPhotoPreviewActivity.IntentBuilder photoPreviewIntentBuilder = new BGAPhotoPreviewActivity.IntentBuilder(mContext)
                                .saveImgDir(null); // 保存图片的目录，如果传 null，则没有保存图片功能
//                        photoPreviewIntentBuilder.previewPhoto("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1536235318&di=40753b5008a15bdf31dd8daf6681f59e&src=http://img0.pconline.com.cn/pconline/1703/30/9027001_53_thumb.jpg");
                        photoPreviewIntentBuilder.previewPhoto(MyCdConfig.QINIU_URL + item);
                        mContext.startActivity(photoPreviewIntentBuilder.build());

                    }
                });

                mBinding.llDel.setOnClickListener(view -> {
                    int index = helper.getAdapterPosition();
                    // 这里有时会返回-1造成数据下标越界,具体可参考getAdapterPosition()源码，
                    // 通过源码分析应该是bindViewHolder()暂未绘制完成导致
                    if (index != RecyclerView.NO_POSITION) {
                        getData().remove(index);
                        notifyItemRemoved(index);
                        notifyItemRangeChanged(index, getData().size());
                    }
                });
            }


        }
    }
}
