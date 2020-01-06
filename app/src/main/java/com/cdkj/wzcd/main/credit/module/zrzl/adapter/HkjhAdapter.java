package com.cdkj.wzcd.main.credit.module.zrzl.adapter;

import android.support.annotation.Nullable;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.HkjhBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DATE_YMD;

/**
 * @author : qianLei
 * @since : 2020/1/2 16:30
 */
public class HkjhAdapter extends BaseQuickAdapter<HkjhBean.RepayPlanListBean, BaseViewHolder> {

    public HkjhAdapter(@Nullable List<HkjhBean.RepayPlanListBean> data) {
        super(R.layout.item_repayment, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, HkjhBean.RepayPlanListBean item) {

        helper.setText(R.id.tv_name, "第" + item.getCurPeriods() + "期");
        helper.setText(R.id.tv_time, DateUtil.formatStringData(item.getRepayDatetime(), DATE_YMD));
        helper.setText(R.id.tv_amount, MoneyUtils.showPrice(item.getRepayCapital()));

    }
}
