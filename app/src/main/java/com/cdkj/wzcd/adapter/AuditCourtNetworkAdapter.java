package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemAuditCourtNetworkBinding;
import com.cdkj.wzcd.model.CreditUserModel;
import com.cdkj.wzcd.module.work.credit.audit.AuditCourtNetwork;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdkj on 2018/5/30.
 */

public class AuditCourtNetworkAdapter extends BaseQuickAdapter<CreditUserModel, BaseViewHolder> {

    private ItemAuditCourtNetworkBinding mBinding;

    List<DataDictionary> mRole = new ArrayList<>();
    List<DataDictionary> mRelation = new ArrayList<>();

    public AuditCourtNetworkAdapter(@Nullable List<CreditUserModel> data, List<DataDictionary> role, List<DataDictionary> relation) {
        super(R.layout.item_audit_court_network, data);

        mRole.addAll(role);
        mRelation.addAll(relation);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditUserModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myItemNlName.setContent(item.getUserName());
        mBinding.myItemNlPhone.setContent(item.getMobile());
        mBinding.myItemNlId.setContent(item.getIdNo());

        mBinding.myItemNlRole.setContent(DataDictionaryHelper.getValueBuyList(item.getLoanRole(), mRole));
        mBinding.myItemNlRelation.setContent(DataDictionaryHelper.getValueBuyList(item.getRelation(), mRelation));

        if (TextUtils.isEmpty(item.getCourtNetworkMesg())) {
            //说明还没有录入结果
            mBinding.myIlBtn.setVisibility(View.VISIBLE);
            mBinding.myIlBtn.setRightTextAndListener("录入", view -> {
                AuditCourtNetwork.open(mContext, item, helper.getLayoutPosition(),mRole,mRelation);
            });
        } else {
            mBinding.myIlBtn.setVisibility(View.GONE);
        }
//
//        DataDictionaryHelper.getValueOnTheKeyRequest(mContext, DataDictionaryHelper.credit_user_loan_role,
//                 item.getLoanRole(), ,null);
//
//        DataDictionaryHelper.getValueOnTheKeyRequest(mContext, DataDictionaryHelper.credit_user_relation,
//                item.getRelation() ,,null);


    }
}
