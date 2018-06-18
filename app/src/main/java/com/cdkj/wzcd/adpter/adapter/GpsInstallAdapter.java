package com.cdkj.wzcd.adpter.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemGpsInstallAddBinding;
import com.cdkj.wzcd.model.GpsInstallModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cdkj on 2018/6/4.
 */

public class GpsInstallAdapter extends BaseQuickAdapter<GpsInstallModel, BaseViewHolder> {

    private ItemGpsInstallAddBinding mBinding;

    public GpsInstallAdapter(@Nullable List<GpsInstallModel> data) {
        super(R.layout.item_gps_install_add, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GpsInstallModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myItemNlCode.setContent(item.getGpsDevNo());
        mBinding.myItemNlStatus.setContent(TextUtils.equals(item.getStatus(), "0") ? "已作废" : "使用中");
        mBinding.myItemNlType.setContent(TextUtils.equals(item.getGpsType(), "0") ? "无线" : "有线");
        mBinding.myItemNlUser.setContent(item.getAzUser());
        mBinding.myItemNlPosition.setContent(item.getAzLocation());
        mBinding.myItemNlDateTime.setContent(item.getAzDatetime());
    }
}
