package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemSupplementReasonBinding;
import com.cdkj.wzcd.model.ReasonModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cdkj on 2018/7/16.
 */

public class SupplementReasonAdapter extends BaseQuickAdapter<ReasonModel, BaseViewHolder> {

    private ItemSupplementReasonBinding mBinding;

    public SupplementReasonAdapter(@Nullable List<ReasonModel> data) {
        super(R.layout.item_supplement_reason, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ReasonModel item) {

        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myItemNlReason.setContent(item.getReason());

        if (item.isChoice()){
            helper.setBackgroundRes(R.id.iv_choice, R.mipmap.pay_choose);
        }else {
            helper.setBackgroundRes(R.id.iv_choice, R.mipmap.pay_no);
        }
        helper.addOnClickListener(R.id.iv_choice);
    }
}
