package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemHistoryUserBinding;
import com.cdkj.wzcd.model.RepaymentModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.NodeHelper;
import com.cdkj.wzcd.util.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author cdkj
 * @updateDts 2018/5/30
 */

public class HistoryUserAdapter extends BaseQuickAdapter<RepaymentModel, BaseViewHolder> {

    private List<DataDictionary> mList;
    private ItemHistoryUserBinding mBinding;

    public HistoryUserAdapter(@Nullable List<RepaymentModel> data, List<DataDictionary> list) {
        super(R.layout.item_history_user, data);
        mList = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, RepaymentModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getBudgetOrder().getCurNodeCode()));

        mBinding.myIlName.setText(item.getUser().getRealName());
        mBinding.myIlType.setText(DataDictionaryHelper.getBizTypeByKey(item.getBudgetOrder().getBizType()));
        mBinding.myIlAmount.setMoneyText(item.getLoanAmount());
        mBinding.myIlBank.setText(item.getLoanBankName());
        mBinding.myIlAdvanceFund.setText(TextUtils.equals(item.getBudgetOrder().getIsAdvanceFund(),"1") ? "已垫资" : "未垫资");
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getUpdateDatetime(), DateUtil.DEFAULT_DATE_FMT));

    }
}
