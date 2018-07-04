package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemDataReFileBinding;
import com.cdkj.wzcd.model.DataFileModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cdkj on 2018/6/5.
 */

public class DataFileAdapter extends BaseQuickAdapter<DataFileModel, BaseViewHolder> {

    private ItemDataReFileBinding mBinding;

    public DataFileAdapter(@Nullable List<DataFileModel> data) {
        super(R.layout.item_data_re_file, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataFileModel item) {
        mBinding = DataBindingUtil.bind(helper.itemView);
        LogUtil.E("item = "+item.getFile());
        mBinding.myItemNlFile.setContent(item.getFile());
    }
}
