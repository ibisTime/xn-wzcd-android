package com.cdkj.wzcd.main.credit.module.zrzl.adapter;

import android.support.annotation.Nullable;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.wzcd.R;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : qianLei
 * @since : 2020/1/2 17:28
 */
public class SdAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public SdAdapter(@Nullable List<String> data) {
        super(R.layout.item_shuidan, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

        ImgUtils.loadQiniuImg(mContext, item, helper.getView(R.id.iv_pic));

    }
}
