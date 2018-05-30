package com.cdkj.wzcd.module.business.zxdc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cdkj.baselibrary.base.AbsRefreshListFragment;

import java.util.List;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.IS_FIRST_REQUEST;

/**
 * Created by cdkj on 2018/5/29.
 */

public class ZxdcListFragment extends AbsRefreshListFragment {

    /**
     * @param
     * @return
     */
    public static ZxdcListFragment getInstance(Boolean isFirstRequest, String status) {
        ZxdcListFragment fragment = new ZxdcListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, status);
        bundle.putBoolean(IS_FIRST_REQUEST, isFirstRequest);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Override
    protected void afterCreate(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        return null;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {

    }
}
