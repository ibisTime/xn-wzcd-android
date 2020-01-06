package com.cdkj.wzcd.main.credit.adapter;

import android.support.annotation.Nullable;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.credit.bean.CreditBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : qianLei
 * @since : 2019/12/26 10:45
 */
public class CreditAdapter extends BaseQuickAdapter<CreditBean, BaseViewHolder> {

    public CreditAdapter(@Nullable List<CreditBean> data) {
        super(R.layout.item_main_credit, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditBean item) {

        helper.setImageResource(R.id.iv_content, item.getPic());

        helper.setText(R.id.tv_red_point, item.getNumber()+"");

        helper.setText(R.id.tv_content, item.getName());

    }


}
