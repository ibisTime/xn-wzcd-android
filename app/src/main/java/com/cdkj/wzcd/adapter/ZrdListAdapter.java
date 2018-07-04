package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemZrdListBinding;
import com.cdkj.wzcd.model.ZrdModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.NodeHelper;
import com.cdkj.wzcd.util.RequestUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * 准入单列表
 * Created by cdkj on 2018/4/9.
 */

public class ZrdListAdapter extends BaseQuickAdapter<ZrdModel, BaseViewHolder> {

    private ItemZrdListBinding mBinding;
    private List<DataDictionary> mList;

    public ZrdListAdapter(@Nullable List<ZrdModel> data, List<DataDictionary> list) {
        super(R.layout.item_zrd_list, data);

        mList = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, ZrdModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

//        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getCurNodeCode()));
//
//        mBinding.myIlName.setText(item.getApplyUserName());
//        mBinding.myIlCompany.setText(item.getCompanyName());
//        mBinding.myIlCode.setText(item.getCarBrand());

        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getCurNodeCode()));

        mBinding.myIlName.setText(item.getApplyUserName());
        mBinding.myIlType.setText(DataDictionaryHelper.getBizTypeBuyKey(item.getBizType()));
        mBinding.myIlAmount.setText(RequestUtil.formatAmountDivSign(item.getLoanAmount()));
        mBinding.myIlBank.setText(item.getLoanBankName());
        mBinding.myIlAdvanceFund.setText(TextUtils.equals(item.getIsAdvanceFund(),"1") ? "已垫资" : "未垫资");
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));

    }
}
