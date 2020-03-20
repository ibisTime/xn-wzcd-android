package com.cdkj.wzcd.main.message.adapter;

import android.support.annotation.Nullable;

import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.message.bean.NoticeBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;

/**
 * Created by lei on 2020/3/17.
 */

public class NoticeAdapter extends BaseQuickAdapter<NoticeBean, BaseViewHolder> {

    public NoticeAdapter(@Nullable List<NoticeBean> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NoticeBean item) {

        helper.setText(R.id.tv_title, item.getContent());
        helper.setText(R.id.tv_time,
                DateUtil.formatStringData(item.getUpdateDatetime(), DEFAULT_DATE_FMT));

        helper.setText(R.id.tv_info, "消息");
    }
}
