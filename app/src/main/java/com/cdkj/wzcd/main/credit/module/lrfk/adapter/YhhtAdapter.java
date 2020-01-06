package com.cdkj.wzcd.main.credit.module.lrfk.adapter;

import android.support.annotation.Nullable;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.custom.BaseEditLayout;
import com.cdkj.wzcd.main.credit.module.lrfk.bean.YhhtBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DATE_YMD;

/**
 * @author : qianLei
 * @since : 2020/1/1 21:57
 */
public class YhhtAdapter extends BaseQuickAdapter<YhhtBean, BaseViewHolder> {

    public YhhtAdapter(@Nullable List<YhhtBean> data) {
        super(R.layout.item_yhht, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, YhhtBean item) {

        BaseEditLayout elName = helper.getView(R.id.el_name);
        elName.setTextByRequest(item.getCustomerName());

        BaseEditLayout elIdNo = helper.getView(R.id.el_idNo);
        elIdNo.setTextByRequest(item.getIdNo());

        BaseEditLayout elLoanCode = helper.getView(R.id.el_loanCode);
        elLoanCode.setTextByRequest(item.getLoanCode());

        BaseEditLayout elLoanCardNumber = helper.getView(R.id.el_loanCardNumber);
        elLoanCardNumber.setTextByRequest(item.getLoanCardNumber());

        BaseEditLayout elLoanAmount = helper.getView(R.id.el_loanAmount);
        elLoanAmount.setTextByRequest(item.getLoanAmount());

        BaseEditLayout elRate = helper.getView(R.id.el_rate);
        elRate.setTextByRequest(item.getRate());

        BaseEditLayout elPeriods = helper.getView(R.id.el_periods);
        elPeriods.setTextByRequest(item.getPeriods());

        BaseEditLayout elFkDatetime = helper.getView(R.id.el_fkDatetime);
        elFkDatetime.setTextByRequest(item.getFkDatetime());
    }
}
