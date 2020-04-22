package com.cdkj.wzcd.main.message.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.message.bean.AgendaBean;
import com.cdkj.wzcd.main.message.bean.MessageBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;

/**
 * @author : qianLei
 * @since : 2019/12/25 20:38
 */
public class AgendaAdapter extends BaseQuickAdapter<AgendaBean, BaseViewHolder> {

    public AgendaAdapter(@Nullable List<AgendaBean> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AgendaBean item) {

        helper.setText(R.id.tv_title,
                item.getContent() + "-" + (TextUtils.isEmpty(item.getUserName()) ? ""
                        : item.getUserName()));
        helper.setText(R.id.tv_time,
                DateUtil.formatStringData(item.getCreateDatetime(), DEFAULT_DATE_FMT));

        helper.setText(R.id.tv_info, item.getBizCode());


    }
}
