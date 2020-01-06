package com.cdkj.wzcd.main.credit.module.zrzl.adapter;

import android.support.annotation.Nullable;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CzrzBean;
import com.cdkj.wzcd.util.NodeHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : qianLei
 * @since : 2020/1/2 18:15
 */
public class CzrzAdapter extends BaseQuickAdapter<CzrzBean, BaseViewHolder> {

    public CzrzAdapter(@Nullable List<CzrzBean> data) {
        super(R.layout.item_czrz, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CzrzBean item) {

        helper.setText(R.id.tv_title, NodeHelper.getNameOnTheCode(item.getDealNode()));
        helper.setText(R.id.tv_info, item.getDealNote());
        helper.setText(R.id.tv_name, "操作人：" + item.getOperatorName());
        helper.setText(R.id.tv_time, "操作时间：" + DateUtil.formatStringData(item.getStartDatetime()));

    }
}
