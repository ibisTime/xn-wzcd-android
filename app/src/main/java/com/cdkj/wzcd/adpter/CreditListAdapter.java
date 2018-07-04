package com.cdkj.wzcd.adpter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemCreditListBinding;
import com.cdkj.wzcd.model.CreditModel;
import com.cdkj.wzcd.module.work.credit.CreditDetailActivity;
import com.cdkj.wzcd.module.work.credit.CreditInitiateActivity;
import com.cdkj.wzcd.module.work.credit.audit.AuditCreditActivity;
import com.cdkj.wzcd.util.BankHelper;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.NodeHelper;
import com.cdkj.wzcd.util.RequestUtil;
import com.cdkj.wzcd.util.UserHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cdkj on 2018/5/30.
 */

public class CreditListAdapter extends BaseQuickAdapter<CreditModel, BaseViewHolder> {

    private ItemCreditListBinding mBinding;
    private List<DataDictionary> mType;

    public CreditListAdapter(@Nullable List<CreditModel> data, List<DataDictionary> type) {
        super(R.layout.item_credit_list, data);

        mType = type;
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getCurNodeCode()));

        mBinding.myIlType.setText(DataDictionaryHelper.getValueBuyList(item.getShopWay(), mType));

        if (item.getCreditUser() != null)
            mBinding.myIlName.setText(item.getCreditUser().getUserName());
        mBinding.myIlAmount.setText(MoneyUtils.MONEYSING + RequestUtil.formatAmountDiv(item.getLoanAmount()));
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));

        BankHelper.getValueOnTheKey(mContext, item.getLoanBankCode(), mBinding.myIlBank, null);

        mBinding.myItemCblConfirm.setContent("","");

        if (UserHelper.isZHRY()){

            if (TextUtils.equals(item.getCurNodeCode(), "001_02")){ // 录入征信结果
                mBinding.myItemCblConfirm.setRightTextAndListener("录入银行征信结果", view -> {
                    AuditCreditActivity.open(mContext, item.getCode());
                });
            }
        }else {

            if (TextUtils.equals(item.getCurNodeCode(), "001_03")){ // 征信初审
                mBinding.myItemCblConfirm.setRightTextAndListener("征信初审", view -> {
                    CreditDetailActivity.open(mContext, item.getCode(), true);
                });
            }

            if (TextUtils.equals(item.getCurNodeCode(), "001_05")){ // 重新上传征信资料
                mBinding.myItemCblConfirm.setRightTextAndListener("修改征信信息", view -> {
                    CreditInitiateActivity.open(mContext, item.getCode());
                });
            }
        }

    }
}
