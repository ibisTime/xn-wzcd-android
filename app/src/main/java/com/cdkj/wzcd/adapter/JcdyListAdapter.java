package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemJcdyListBinding;
import com.cdkj.wzcd.model.RepaymentModel;
import com.cdkj.wzcd.module.work.bank_loan.BankLoanCommitActivity;
import com.cdkj.wzcd.util.NodeHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * X
 * @updateDts 2018/5/30
 */

public class JcdyListAdapter extends BaseQuickAdapter<RepaymentModel, BaseViewHolder> {

    private List<DataDictionary> mList;
    private ItemJcdyListBinding mBinding;

    public JcdyListAdapter(@Nullable List<RepaymentModel> data, List<DataDictionary> list) {
        super(R.layout.item_jcdy_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RepaymentModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getBudgetOrder().getCurNodeCode()));

        mBinding.myIlName.setText(item.getUser().getRealName());
        mBinding.myIlAmount.setText(item.getLoanAmount());
        mBinding.myIlBank.setText(item.getLoanBankName());
        mBinding.myIlAdvanceFund.setText(TextUtils.equals(item.getBudgetOrder().getIsAdvanceFund(),"1") ? "已垫资" : "未垫资");
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getUpdateDatetime(), DateUtil.DEFAULT_DATE_FMT));

        mBinding.myItemCblConfirm.setContent("", "");

//        if (TextUtils.equals(item.getBudgetOrder().getCurNodeCode(),"007_01")){ // 驻行人员回录提交放款材料
        if (TextUtils.equals(item.getBudgetOrder().getCurNodeCode(),"007_09")){ // 驻行人员回录提交放款材料
            mBinding.myItemCblConfirm.setRightTextAndListener("确认提交银行", view -> {
                //oss端直接调取接口成功即可 没有信息的录入
                BankLoanCommitActivity.open(mContext, item.getCode());
            });
        }
    }
}
