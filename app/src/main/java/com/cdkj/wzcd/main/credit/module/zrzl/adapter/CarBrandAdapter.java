package com.cdkj.wzcd.main.credit.module.zrzl.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CarBrandBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @updateDts 2019/3/13
 */
public class CarBrandAdapter extends BaseQuickAdapter<CarBrandBean, BaseViewHolder> {
    public CarBrandAdapter(@Nullable List<CarBrandBean> data) {
        super(R.layout.item_brand_hor_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CarBrandBean item) {
        ImgUtils.loadQiniuImg(mContext,item.getLogo(),helper.getView(R.id.iv_brand_img));
        if (!TextUtils.isEmpty(item.getName())) {
            helper.setText(R.id.tv_brand_name, item.getName());
        } else {
            helper.setText(R.id.tv_brand_name, "");
        }

        helper.setText(R.id.tv_sort, item.getLetter());

        helper.setGone(R.id.ll_sort, false);
//        if (helper.getLayoutPosition() == 0){
//
//        }else {
//            if (!item.getLetter().equals(getDataByField().get(helper.getLayoutPosition()-1).getLetter())){
//                helper.setGone(R.id.ll_sort, true);
//            }else {
//                helper.setGone(R.id.ll_sort, false);
//            }
//        }

    }


}
