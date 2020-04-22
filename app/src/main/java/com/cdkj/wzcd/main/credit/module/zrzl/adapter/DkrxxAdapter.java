package com.cdkj.wzcd.main.credit.module.zrzl.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : qianLei
 * @since : 2019/12/31 13:28
 */
public class DkrxxAdapter extends BaseQuickAdapter<DkrxxBean, BaseViewHolder> {

    public DkrxxAdapter(@Nullable List<DkrxxBean> data) {
        super(R.layout.item_dkrxx, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, DkrxxBean item) {

        helper.setText(R.id.tv_role, item.getLoanRoleName());
        helper.setText(R.id.tv_name,
                TextUtils.isEmpty(item.getUserName()) ? "请完善" + item.getLoanRoleName() + "资料"
                        : item.getUserName());

        if (!TextUtils.isEmpty(item.getBankCreditResult())){
            helper.setText(R.id.tv_status, item.getBankCreditResult().equals("0") ? "不通过" : "通过");
        }

        if (helper.getLayoutPosition() == 0){

            helper.setGone(R.id.btn_clear,false);

        }else {

            // 有身份证正面说明已填写资料
            if (TextUtils.isEmpty(item.getIdFront())){

                helper.setGone(R.id.btn_clear,false);

            }else {

                helper.setGone(R.id.btn_clear,true);

            }

        }

        helper.addOnClickListener(R.id.btn_clear);
    }
}
