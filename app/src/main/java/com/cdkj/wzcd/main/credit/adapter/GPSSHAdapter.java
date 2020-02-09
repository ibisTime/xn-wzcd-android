package com.cdkj.wzcd.main.credit.adapter;

import android.support.annotation.Nullable;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.credit.bean.GPSSHBean;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @updateDts 2020/2/4
 */
public class GPSSHAdapter extends BaseQuickAdapter<GPSSHBean, BaseViewHolder> {
    public GPSSHAdapter(@Nullable List<GPSSHBean> data) {
        super(R.layout.item_gpssh, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GPSSHBean item) {
        helper.setText(R.id.tv_code, item.getCompanyName());
        helper.setText(R.id.tv_status, DataDictionaryHelper.getValueBuyKey(DataDictionaryHelper.gps_apply_status, item.getStatus()));
        helper.setText(R.id.tv_people, "申领人：" + item.getApplyUserName());
        helper.setText(R.id.tv_teamName, "所属团队：" + item.getTeamName());
        helper.setText(R.id.tv_time, "申领时间：" + DateUtil.formatStringData(item.getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));
        helper.setText(R.id.tv_number, "申领个数：" + item.getApplyCount());
        helper.setText(R.id.tv_remaker, "备注：" + item.getRemark());
    }
}
