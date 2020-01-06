package com.cdkj.wzcd.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.ViewGroup;
import com.cdkj.baselibrary.utils.DisplayHelper;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.model.NavigationBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author : qianLei
 * @since : 2019/9/17 16:13
 */
public class MainBottomTabAdapter extends BaseQuickAdapter<NavigationBean, BaseViewHolder> {

    public MainBottomTabAdapter(@Nullable List<NavigationBean> data) {
        super(R.layout.item_main_bottom_tab, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NavigationBean item) {

        ViewGroup.LayoutParams params = helper.getView(R.id.ll_root).getLayoutParams();
        params.width = DisplayHelper.getScreenWidth(mContext) / getData().size();
        helper.getView(R.id.ll_root).setLayoutParams(params);

        if (item.isMainSelect()) {
            helper.setImageResource(R.id.iv_icon, item.getPic());
            helper.setTextColor(R.id.tv_name,
                    ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            helper.setImageResource(R.id.iv_icon, item.getDarkPic());
            helper.setTextColor(R.id.tv_name, ContextCompat.getColor(mContext, R.color.text_999));
        }

        helper.setText(R.id.tv_name, getBottomTitle(item.getName()));

//        SkinHellp.getColor(R.color.skin_main_tab_un_select_cole)

    }

    private String getBottomTitle(String name) {
        switch (name) {

            case "DAPP":
                return "应用";
        }
        return name;
    }
}
