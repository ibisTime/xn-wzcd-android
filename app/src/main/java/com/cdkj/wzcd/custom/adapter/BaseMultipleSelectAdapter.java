package com.cdkj.wzcd.custom.adapter;

import android.support.annotation.Nullable;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.wzcd.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @updateDts 2020/2/4
 */
public class BaseMultipleSelectAdapter extends BaseQuickAdapter<DataDictionary, BaseViewHolder> {
    private Boolean isEdit;

    public BaseMultipleSelectAdapter(Boolean isEdit, @Nullable List<DataDictionary> data) {
        super(R.layout.item_multiple_select_layout, data);
        this.isEdit = isEdit;
    }


    @Override
    protected void convert(BaseViewHolder helper, DataDictionary item) {
        if (isEdit) {
            helper.setGone(R.id.iv_close, true);
        } else {
            helper.setGone(R.id.iv_close, false);
        }
        helper.setText(R.id.tv_content, item.getDvalue());
        helper.addOnClickListener(R.id.iv_close);
    }

    public void setEdit(Boolean isEdit) {
        this.isEdit = isEdit;
    }
}
