package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemGpsDetailsBinding;
import com.cdkj.wzcd.model.GpsApplyModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * GPS 安装列表
 * Created by cdkj on 2018/4/9.
 */

public class GpsDetailsAdapter extends BaseQuickAdapter<GpsApplyModel.GpsListBean, BaseViewHolder> {

    private ItemGpsDetailsBinding mBinding;


    public GpsDetailsAdapter(List<GpsApplyModel.GpsListBean> list) {
        super(R.layout.item_gps_details, list);
    }

    @Override
    protected void convert(BaseViewHolder helper, GpsApplyModel.GpsListBean item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myNlNumber.setTextByRequest(item.getGpsDevNo());
    }
}
