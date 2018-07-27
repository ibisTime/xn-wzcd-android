package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemBankLoanListBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.module.work.bank_loan.BankLoanCommitActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.NodeHelper;
import com.cdkj.wzcd.util.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * X
 * @updateDts 2018/5/30
 */

public class BankLoanListAdapter extends BaseQuickAdapter<NodeListModel, BaseViewHolder> {
    private ItemBankLoanListBinding mBinding;

    public BankLoanListAdapter(@Nullable List<NodeListModel> data) {
        super(R.layout.item_bank_loan_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NodeListModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getCurNodeCode()));

        mBinding.myIlBank.setText(item.getLoanBankName());
        mBinding.myIlName.setText(item.getCustomerName());
        mBinding.myIlType.setText(DataDictionaryHelper.getBizTypeByKey(item.getShopWay()));
        mBinding.myIlAmount.setText(RequestUtil.formatAmountDivSign(item.getLoanAmount()));
        mBinding.myIlAdvanceFund.setText(TextUtils.equals(item.getIsAdvanceFund(),"1") ? "已垫资" : "未垫资");
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));

        mBinding.myItemCblConfirm.setContent("", "");

        if (TextUtils.equals(item.getCurNodeCode(),"007_04")){ // 确认提交银行
            mBinding.myItemCblConfirm.setRightTextAndListener("确认提交银行", view -> {
                BankLoanCommitActivity.open(mContext, item.getCode());
            });
        }
    }
}
