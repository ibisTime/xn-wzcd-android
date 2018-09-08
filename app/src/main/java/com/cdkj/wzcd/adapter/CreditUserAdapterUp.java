package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemCreditPersonUpBinding;
import com.cdkj.wzcd.model.CreditUserModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdkj on 2018/5/30.
 * 修改征信详情  多一个可选的  按钮
 */

public class CreditUserAdapterUp extends BaseQuickAdapter<CreditUserModel, BaseViewHolder> {

    private ItemCreditPersonUpBinding mBinding;


    List<DataDictionary> mRole = new ArrayList<>();
    List<DataDictionary> mRelation = new ArrayList<>();

    public CreditUserAdapterUp(@Nullable List<CreditUserModel> data, List<DataDictionary> role, List<DataDictionary> relation) {
        super(R.layout.item_credit_person_up, data);

        mRole.addAll(role);
        mRelation.addAll(relation);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditUserModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        if (item.isChoice()) {
            helper.setBackgroundRes(R.id.iv_choice, R.mipmap.pay_choose);
        } else {
            helper.setBackgroundRes(R.id.iv_choice, R.mipmap.pay_no);
        }
        helper.addOnClickListener(R.id.iv_choice);

        mBinding.myItemNlName.setContent(item.getUserName());
        mBinding.myItemNlPhone.setContent(item.getMobile());
        mBinding.myItemNlId.setContent(item.getIdNo());

        mBinding.myItemNlRole.setContent(DataDictionaryHelper.getValueBuyList(item.getLoanRole(), mRole));
        mBinding.myItemNlRelation.setContent(DataDictionaryHelper.getValueBuyList(item.getRelation(), mRelation));


//
//        DataDictionaryHelper.getValueOnTheKeyRequest(mContext, DataDictionaryHelper.credit_user_loan_role,
//                 item.getLoanRole(), ,null);
//
//        DataDictionaryHelper.getValueOnTheKeyRequest(mContext, DataDictionaryHelper.credit_user_relation,
//                item.getRelation() ,,null);


    }
}
