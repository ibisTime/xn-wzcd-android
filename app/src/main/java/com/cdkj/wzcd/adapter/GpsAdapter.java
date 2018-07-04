package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemGpsBinding;
import com.cdkj.wzcd.model.GpsApplyModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author cdkj
 * @updateDts 2018/5/30
 */

public class GpsAdapter extends BaseQuickAdapter<GpsApplyModel, BaseViewHolder> {

    private ItemGpsBinding mBinding;
    private List<DataDictionary> mList;

    public GpsAdapter(@Nullable List<GpsApplyModel> data, List<DataDictionary> list) {
        super(R.layout.item_gps, data);

        this.mList = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, GpsApplyModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdNode.setText(item.getApplyUserName(), DataDictionaryHelper.getValueBuyList(item.getStatus(), mList));

        mBinding.myIlCompany.setText(item.getCompanyName());
        mBinding.myIlNumber.setText(item.getApplyCount() + "");
        mBinding.myIlDateTime.setText(DateUtil.formatStringData(item.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));

    }
}
