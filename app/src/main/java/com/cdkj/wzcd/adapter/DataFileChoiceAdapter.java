package com.cdkj.wzcd.adapter;

import android.support.annotation.Nullable;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.model.DataFileModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by cdkj on 2018/6/5.
 */

public class DataFileChoiceAdapter extends BaseQuickAdapter<DataFileModel, BaseViewHolder> {

    public DataFileChoiceAdapter(@Nullable List<DataFileModel> data) {
        super(R.layout.item_data_send_file, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DataFileModel item) {

        if (item.isChoice()){
            helper.setBackgroundRes(R.id.iv_choice, R.mipmap.pay_choose);
        }else {
            helper.setBackgroundRes(R.id.iv_choice, R.mipmap.pay_no);
        }

        helper.setText(R.id.tv_file, item.getFile());

    }
}
