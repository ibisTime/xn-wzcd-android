package com.cdkj.wzcd.main.message.adapter;

import android.support.annotation.Nullable;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.main.message.bean.MessageBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.Date;
import java.util.List;

import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;

/**
 * @author : qianLei
 * @since : 2019/12/25 20:38
 */
public class MessageAdapter extends BaseQuickAdapter<MessageBean, BaseViewHolder> {

    public MessageAdapter(@Nullable List<MessageBean> data) {
        super(R.layout.item_message, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean item) {

        helper.setText(R.id.tv_title, item.getTitle());
        helper.setText(R.id.tv_time,
                DateUtil.formatStringData(item.getUpdateDatetime(), DEFAULT_DATE_FMT));

        helper.setText(R.id.tv_info, "系统公告");


    }
}
