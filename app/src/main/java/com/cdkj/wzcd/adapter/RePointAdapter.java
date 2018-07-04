package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemRePointBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.util.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cdkj on 2018/7/2.
 */

public class RePointAdapter extends BaseQuickAdapter<NodeListModel.RepointDetailListBean, BaseViewHolder> {

    ItemRePointBinding mBinding;

    public RePointAdapter(@Nullable List<NodeListModel.RepointDetailListBean> data) {
        super(R.layout.item_re_point, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NodeListModel.RepointDetailListBean item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myItemNlUseMoneyPurpose.setContent(TextUtils.equals(item.getUseMoneyPurpose(), "1") ? "应退按揭款" : "协议外返点");
        mBinding.myItemNlRepointAmount.setContent(RequestUtil.formatAmountDivSign(item.getRepointAmount()));
        mBinding.myItemNlCarDealerName.setContent(item.getCarDealerName());
        mBinding.myItemNlAccountNO.setContent(item.getAccountNO());
        mBinding.myItemNlOpenBankName.setContent(item.getOpenBankName());

    }
}
