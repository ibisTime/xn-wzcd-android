package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemAdvanceFoundListBinding;
import com.cdkj.wzcd.model.AdvanceFundModel;
import com.cdkj.wzcd.module.work.advancefund.AdvanceFundApplyActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.NodeHelper;
import com.cdkj.wzcd.util.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * X
 *
 * @updateDts 2018/5/30
 */

public class AdvanceFundListAdapter extends BaseQuickAdapter<AdvanceFundModel, BaseViewHolder> {
    private ItemAdvanceFoundListBinding mBinding;

    public AdvanceFundListAdapter(@Nullable List<AdvanceFundModel> data) {
        super(R.layout.item_advance_found_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AdvanceFundModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getCurNodeCode()));

        mBinding.myIlBank.setText(item.getLoanBankName());
        mBinding.myIlName.setText(item.getBudgetOrder().getCustomerName());
        mBinding.myIlType.setText(DataDictionaryHelper.getBizTypeByKey(item.getBudgetOrder().getShopWay()));

        mBinding.myIlAmount.setText(RequestUtil.formatAmountDivSign(item.getBudgetOrder().getLoanAmount()));
        mBinding.myIlAdvanceFund.setText(TextUtils.equals(item.getIsAdvanceFund(), "1") ? "是" : "否");
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getBudgetOrder().getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));

        mBinding.myItemCblConfirm.setContent("", "");

        if (TextUtils.equals(item.getBudgetOrder().getCurNodeCode(), "002_04")) { // 准入审核二审
            mBinding.myItemCblConfirm.setRightTextAndListener("准入审核二审", view -> {
                AdvanceFundApplyActivity.open(mContext, item.getCode());
            });
        }

        if (TextUtils.equals(item.getCode(), "003_01") || TextUtils.equals(item.getCode(), "004_01")) {

            if (TextUtils.equals(item.getBudgetOrder().getCurNodeCode(), "002_06")) { // 确认用款单
                mBinding.myItemCblConfirm.setRightTextAndListener("确认申请", view -> {
                    AdvanceFundApplyActivity.open(mContext, item.getCode());
                });
            }
        }

    }
}
