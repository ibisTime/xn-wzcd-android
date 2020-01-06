package com.cdkj.wzcd.main.credit.module.zrzl.adapter;

import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlStepBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : qianLei
 * @since : 2019/12/26 14:48
 */
public class ZrzlStepAdapter extends BaseQuickAdapter<ZrzlStepBean, BaseViewHolder> {

    public ZrzlStepAdapter(@Nullable List<ZrzlStepBean> data) {
        super(R.layout.item_zrzl_step, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZrzlStepBean item) {

        helper.setText(R.id.tv_step, item.getName());

        if (item.isSelected()){
            helper.setBackgroundRes(R.id.tv_step, R.drawable.shape_zrzl_step);
            helper.setTextColor(R.id.tv_step, ContextCompat.getColor(mContext, R.color.white));
        }else {
            helper.setBackgroundRes(R.id.tv_step, R.drawable.shape_zrzl_step_un);
            helper.setTextColor(R.id.tv_step, ContextCompat.getColor(mContext, R.color.text_999));
        }


    }
}
