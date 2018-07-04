package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemApplyGpsListBinding;
import com.cdkj.wzcd.model.GpsInstallModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cdkj on 2018/6/27.
 */

public class JoinApplyGpsAdapter extends BaseQuickAdapter<GpsInstallModel, BaseViewHolder> {

    private ItemApplyGpsListBinding mBinding;

    public JoinApplyGpsAdapter(@Nullable List<GpsInstallModel> data) {
        super(R.layout.item_apply_gps_list, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GpsInstallModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myItemNlCode.setContent(item.getGpsDevNo());
        mBinding.myItemNlType.setContent(TextUtils.equals(item.getGpsType(), "0") ? "无线" : "有线");
        mBinding.myItemNlLocation.setContent(DataDictionaryHelper.getValueBuyKey(DataDictionaryHelper.az_location, item.getAzLocation()));
    }
}
