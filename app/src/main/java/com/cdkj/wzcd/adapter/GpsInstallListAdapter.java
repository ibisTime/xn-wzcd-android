package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemGpsInstallBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.module.tool.gps_install.GPSInstallInfoActivity;
import com.cdkj.wzcd.module.tool.gps_install.GpsCancelActivity;
import com.cdkj.wzcd.util.NodeHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * GPS 安装列表
 * Created by cdkj on 2018/4/9.
 */

public class GpsInstallListAdapter extends BaseQuickAdapter<NodeListModel, BaseViewHolder> {

    private ItemGpsInstallBinding mBinding;
    private List<DataDictionary> mList;

    public GpsInstallListAdapter(@Nullable List<NodeListModel> data, List<DataDictionary> list) {
        super(R.layout.item_gps_install, data);

        mList = list;
    }

    @Override
    protected void convert(BaseViewHolder helper, NodeListModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);

        mBinding.myTlIdStatus.setText(item.getCode(), NodeHelper.getNameOnTheCode(item.getCurNodeCode()));

        mBinding.myIlName.setText(item.getCustomerName());
        mBinding.myIlCompany.setText(item.getCompanyName());
        mBinding.myIlCode.setText(item.getCarBrand());

        mBinding.myItemCblConfirm.setContent("安装回录","回收作废");
        mBinding.myItemCblConfirm.setLeftListener( view -> {
            GPSInstallInfoActivity.open(mContext, item.getCode());
        });
        mBinding.myItemCblConfirm.setRightListener( view -> {
            GpsCancelActivity.open(mContext, item.getCode());
        });

//        if (TextUtils.equals(item.getCurNodeCode(),"002_09") || TextUtils.equals(item.getCurNodeCode(),"002_12")){ // 业务团队安装GPS / 业务团队重新安装GPS
//
//        }



    }
}
