package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemDaikuanZhunruBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.NodeHelper;
import com.cdkj.wzcd.util.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 贷前准入列表
 * Created by cdkj on 2018/4/9.
 */

public class MyApplyListAdapter extends BaseQuickAdapter<NodeListModel, BaseViewHolder> {

    private ItemDaikuanZhunruBinding mBinding;

    public MyApplyListAdapter(@Nullable List<NodeListModel> data) {
        super(R.layout.item_daikuan_zhunru, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NodeListModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getCurNodeCode()));

        mBinding.myIlBank.setText(item.getLoanBankName());
        mBinding.myIlName.setText(item.getCustomerName());
        mBinding.myIlType.setText(DataDictionaryHelper.getBizTypeByKey(item.getShopWay()));
        mBinding.myIlAmount.setText(RequestUtil.formatAmountDivSign(item.getLoanAmount()));
        mBinding.myIlAdvanceFund.setText(TextUtils.equals(item.getIsAdvanceFund(), "1") ? "是" : "否");
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));


        //只有填写预算单的时候才  显示  申请和外单申请
//            if (TextUtils.equals(item.getCurNodeCode(), "002_01") || TextUtils.equals(item.getCurNodeCode(), "002_04")) { // 填写准入申请单 / 重新填写准入申请单
        if (TextUtils.equals(item.getCurNodeCode(), "002_01")) { // 填写准入申请单 / 重新填写准入申请单
            mBinding.myItemCblConfirm.setRightTextAndListener("申请", view -> {
                JoinApplyActivity.open(mContext, item.getCode(), false);
            });

            mBinding.myItemCblConfirm.setLeftTextAndListener("外单申请", view -> {
                JoinApplyActivity.open(mContext, item.getCode(), true);
            });
        } else {
            mBinding.myItemCblConfirm.setContent("", "");
        }
    }
}

