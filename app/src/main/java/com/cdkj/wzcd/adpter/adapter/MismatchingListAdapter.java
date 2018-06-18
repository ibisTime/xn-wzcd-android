package com.cdkj.wzcd.adpter.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemMismatchingListBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.util.BizTypeHelper;
import com.cdkj.wzcd.util.NodeHelper;
import com.cdkj.wzcd.util.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * X
 * @updateDts 2018/5/30
 */

public class MismatchingListAdapter extends BaseQuickAdapter<NodeListModel, BaseViewHolder> {
    private ItemMismatchingListBinding mBinding;

    public MismatchingListAdapter(@Nullable List<NodeListModel> data) {
        super(R.layout.item_mismatching_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NodeListModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getCurNodeCode()));

        mBinding.myIlBank.setText(item.getLoanBankName());
        mBinding.myIlName.setText(item.getCustomerName());
        mBinding.myIlType.setText(BizTypeHelper.getNameOnTheKey(item.getShopWay()));
        mBinding.myIlAmount.setText(RequestUtil.formatAmountDivSign(item.getAdvanceFundAmount()));
        mBinding.myIlAdvanceFund.setText(TextUtils.equals(item.getIsAdvanceFund(),"1") ? "已垫资" : "未垫资");
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));

        mBinding.myItemCblConfirm.setContent("", "");

//        if (TextUtils.equals(item.getCurNodeCode(),"002_16")){ // 驻行人员录入银行放款信息
//            mBinding.myItemCblConfirm.setRightTextAndListener("录入放款信息", view -> {
//                BankLoanInputActivity.open(mContext, item.getCode());
//            });
//        }
//
//        if (TextUtils.equals(item.getCurNodeCode(),"002_15")){ // 驻行人员回录提交放款材料
//            mBinding.myItemCblConfirm.setRightTextAndListener("确认提交银行", view -> {
//                BankLoanCommitActivity.open(mContext, item.getCode());
//            });
//        }
    }
}
