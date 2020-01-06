package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemFabricationListBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.module.tool.fabrication.FabricationApplyActivity;
import com.cdkj.wzcd.module.tool.fabrication.FabricationInputActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * X
 * @updateDts 2018/5/30
 */

public class FabricationListAdapter extends BaseQuickAdapter<NodeListModel, BaseViewHolder> {

    private List<DataDictionary> mType;
    private ItemFabricationListBinding mBinding;

    public FabricationListAdapter(@Nullable List<NodeListModel> data, List<DataDictionary> type) {
        super(R.layout.item_fabrication_list, data);

        mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, NodeListModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getCode(), DataDictionaryHelper.getValueBuyList(item.getMakeCardStatus(), mType));

//        NodeHelper.getNameOnTheCode(item.getCurNodeCode())
        mBinding.myIlBank.setText(item.getLoanBankName());
        mBinding.myIlName.setText(item.getCustomerName());
        mBinding.myIlType.setText(DataDictionaryHelper.getBizTypeByKey(item.getShopWay()));
        mBinding.myIlAmount.setText(item.getLoanAmount());
        mBinding.myIlAdvanceFund.setText(TextUtils.equals(item.getIsAdvanceFund(),"1") ? "已垫资" : "未垫资");
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));

        mBinding.myItemCblConfirm.setContent("", "");

        if (TextUtils.equals(item.getMakeCardStatus(), "0")){
            mBinding.myItemCblConfirm.setRightTextAndListener("提交制卡", view -> {
                FabricationApplyActivity.open(mContext, item.getCode());
            });
        }

        if (TextUtils.equals(item.getMakeCardStatus(), "2")){
            mBinding.myItemCblConfirm.setRightTextAndListener("录入", view -> {
                FabricationInputActivity.open(mContext, item.getCode());
            });
        }

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
