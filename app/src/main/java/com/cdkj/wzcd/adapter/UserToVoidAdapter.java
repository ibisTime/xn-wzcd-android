package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemUserToVoidBinding;
import com.cdkj.wzcd.model.NodeListModel;
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

public class UserToVoidAdapter extends BaseQuickAdapter<NodeListModel, BaseViewHolder> {

    private ItemUserToVoidBinding mBinding;

    public UserToVoidAdapter(@Nullable List<NodeListModel> data) {
        super(R.layout.item_user_to_void, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NodeListModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getCurNodeCode()));

        mBinding.myIlName.setText(item.getCustomerName());
        mBinding.myIlType.setText(DataDictionaryHelper.getBizTypeByKey(item.getShopWay()));
        mBinding.myIlAmount.setMoneyText(item.getLoanAmount());
        mBinding.myIlBank.setText(item.getLoanBankName());
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));

    }
}
