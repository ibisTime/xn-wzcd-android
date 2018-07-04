package com.cdkj.wzcd.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ItemBackMoneyBinding;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 返点适配器
 * Created by cdkj on 2018/5/30.
 */

public class BackMoneyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    private ItemBackMoneyBinding mBinding;

    List<DataDictionary> mRole = new ArrayList<>();
    List<DataDictionary> mRelation = new ArrayList<>();

    public BackMoneyAdapter(@Nullable List<String> data) {
        super(R.layout.item_back_money, data);
    }



    @Override
    protected void convert(BaseViewHolder helper, String item) {
        mBinding = DataBindingUtil.bind(helper.itemView);


    }
}
