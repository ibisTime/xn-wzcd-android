package com.cdkj.wzcd.main.credit.module.yksh.adapter;

import android.support.annotation.Nullable;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.credit.module.yksh.bean.RwBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : qianLei
 * @since : 2020/1/1 22:27
 */
public class RwglAdapter extends BaseQuickAdapter<RwBean, BaseViewHolder> {

    public RwglAdapter(@Nullable List<RwBean> data) {
        super(R.layout.item_yksh_rw, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, RwBean item) {

        helper.setText(R.id.tv_name, item.getGetUserName());
        helper.setText(R.id.tv_rw, item.getName());
        helper.setText(R.id.tv_time, item.getTime());

    }
}
