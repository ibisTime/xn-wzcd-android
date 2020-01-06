package com.cdkj.wzcd.custom.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.custom.bean.BaseImageBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : qianLei
 * @since : 2019/12/28 20:25
 */
public class BaseImageAdapter extends BaseQuickAdapter<BaseImageBean, BaseViewHolder> {

    public BaseImageAdapter(@Nullable List<BaseImageBean> data) {
        super(R.layout.layout_base_image_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BaseImageBean item) {

//        ViewGroup.LayoutParams params = helper.getView(R.id.ll_root).getLayoutParams();
//        params.width =
//                (DisplayHelper.getScreenWidth(mContext) - DisplayHelper.dp2px(mContext, 22)) / 3;
//        helper.getView(R.id.ll_root).setLayoutParams(params);

        if (TextUtils.isEmpty(item.getTitle())) {
            helper.setGone(R.id.tv_title, false);
        } else {
            helper.setGone(R.id.tv_title, true);
            helper.setText(R.id.tv_title, item.getTitle());
        }

        if (TextUtils.isEmpty(item.getPic())) {
            helper.setGone(R.id.ll_del, false);
            helper.setImageResource(R.id.iv_pic, R.mipmap.icon_base_image);
        } else {
            helper.setGone(R.id.ll_del, !item.isDetail());
            ImgUtils.loadImg(mContext, item.getPic(), helper.getView(R.id.iv_pic));
        }

        helper.addOnClickListener(R.id.ll_del);
    }
}
