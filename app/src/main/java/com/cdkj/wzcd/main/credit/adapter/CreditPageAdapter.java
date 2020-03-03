package com.cdkj.wzcd.main.credit.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.credit.bean.CreditPageBean;
import com.cdkj.wzcd.util.NodeHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;

/**
 * @author : qianLei
 * @since : 2019/12/26 14:04
 */
public class CreditPageAdapter extends BaseQuickAdapter<CreditPageBean, BaseViewHolder> {

    public CreditPageAdapter(@Nullable List<CreditPageBean> data) {
        super(R.layout.item_zrzl, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CreditPageBean item) {

        helper.setText(R.id.tv_code, item.getBizCode());

        if (TextUtils.isEmpty(item.getMaterialNodeCode())){
            helper.setText(R.id.tv_status, NodeHelper.getNameOnTheCode(item.getCurNodeCode()));
        }else {
            helper.setText(R.id.tv_status, NodeHelper.getNameOnTheCode(item.getMaterialNodeCode()));
        }

        helper.setText(R.id.tv_name, item.getCustomerName());
        helper.setText(R.id.tv_type, item.getBizType().equals("1") ? "二手车" : "新车");
        helper.setText(R.id.tv_price, MoneyUtils.showPrice(item.getLoanAmount()));
        helper.setText(R.id.tv_bank, "经办银行：" + item.getLoanBankName());
        helper.setText(R.id.tv_time,
                "开始时间：" + DateUtil.formatStringData(item.getApplyDatetime(), DEFAULT_DATE_FMT));


    }
}
