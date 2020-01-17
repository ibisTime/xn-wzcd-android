package com.cdkj.wzcd.common;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.wzcd.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @updateDts 2019/3/13
 */
public class SearchAdapter extends BaseQuickAdapter<DataDictionary, BaseViewHolder> {

    private final boolean isCenter;
    private final boolean isShowLogo;

    public SearchAdapter(@Nullable List<DataDictionary> data, boolean isShowLogo, boolean isCenter) {
        super(R.layout.item_brand_hor_layout, data);
        this.isCenter = isCenter;
        this.isShowLogo = isShowLogo;
    }

    @Override
    protected void convert(BaseViewHolder helper, DataDictionary item) {
        if (isCenter) {
            LinearLayout view = helper.getView(R.id.ll_content);
            view.setGravity(Gravity.CENTER);
        }else{
            LinearLayout view = helper.getView(R.id.ll_content);
            view.setGravity(Gravity.CENTER_VERTICAL);
        }
        if (isShowLogo) {
            helper.setGone(R.id.iv_brand_img, true);
            ImgUtils.loadQiniuImg(mContext, "item.getLogo()", helper.getView(R.id.iv_brand_img));
        } else {
            helper.setGone(R.id.iv_brand_img, false);
        }

        if (!TextUtils.isEmpty(item.getDkey())) {
            helper.setText(R.id.tv_brand_name, item.getDvalue());
        } else {
            helper.setText(R.id.tv_brand_name, "");
        }

//        helper.setText(R.id.tv_sort, item.getLetter());

//        helper.setGone(R.id.ll_sort, false);
    }
}
