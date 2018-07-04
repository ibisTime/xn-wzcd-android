package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemRepayPlanListBinding;
import com.cdkj.wzcd.model.RepaymentModel;
import com.cdkj.wzcd.util.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cdkj on 2018/6/6.
 */

public class RepayPlanAdapter extends BaseQuickAdapter<RepaymentModel.RepayPlanListBean, BaseViewHolder> {

    private ItemRepayPlanListBinding mBinding;

    public RepayPlanAdapter(@Nullable List<RepaymentModel.RepayPlanListBean> data) {
        super(R.layout.item_repay_plan_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepaymentModel.RepayPlanListBean item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myItemDlPeriodsAndRepayCapital.setContent("当前期数"+item.getCurPeriods(),"应还本息"+ RequestUtil.formatAmountDivSign(item.getRepayCapital()));
        mBinding.myItemDlOverdueAmount.setContent(RequestUtil.formatAmountDivSign(item.getOverdueAmount()));
        mBinding.myItemDlOverplusAmount.setContent(RequestUtil.formatAmountDivSign(item.getOverplusAmount()));
        mBinding.myItemDlPayedAmount.setContent(RequestUtil.formatAmountDivSign(item.getPayedAmount()));
    }
}
