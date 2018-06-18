package com.cdkj.wzcd.adpter.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemDaikuanZhunruBinding;
import com.cdkj.wzcd.model.NodeListModel;
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

        mBinding.myItemNlCodeAndStatus.setContent(item.getCode(), NodeHelper.getNameOnTheCode(item.getCurNodeCode()));
        mBinding.myItemNlNameAndTypeAmount.setContent(item.getCustomerName(), RequestUtil.formatAmountDivSign(item.getAdvanceFundAmount()));
        mBinding.myItemNlBank.setContent(item.getLoanBankName());

        mBinding.myItemNlDzAndDateTime.setContent(TextUtils.equals(item.getIsAdvanceFund(),"1") ? "已垫资" : "未垫资",
                DateUtil.formatStringData(item.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));

        if (TextUtils.equals(item.getCurNodeCode(),"002_01") || TextUtils.equals(item.getCurNodeCode(),"002_04")){ // 填写准入申请单 / 重新填写准入申请单
            mBinding.myItemCblConfirm.setRightTextAndListener("填写", view -> {
//                JoinApplyActivity.open(mContext, item.getCode());
            });
        }else {
            mBinding.myItemCblConfirm.setContent("","");
        }

    }
}
