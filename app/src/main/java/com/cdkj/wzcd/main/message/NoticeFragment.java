package com.cdkj.wzcd.main.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsRefreshListFragment;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.main.message.adapter.NoticeAdapter;
import com.cdkj.wzcd.main.message.bean.NoticeBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by lei on 2020/3/17.
 */

public class NoticeFragment extends AbsRefreshListFragment {

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static NoticeFragment getInstance() {
        NoticeFragment fragment = new NoticeFragment();
        Bundle bundle = new Bundle();
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

        initRefreshHelper(10);
        mRefreshHelper.onDefaultMRefresh(true);

    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        NoticeAdapter mAdapter = new NoticeAdapter(listData);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            NoticeBean item = mAdapter.getItem(position);
            NoticeActivity.open(mActivity, item);
        });

        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        Map<String, Object> map = RetrofitUtils.getRequestMap();

        map.put("start", pageIndex + "");
        map.put("limit", limit + "");
        map.put("status", "1");
        map.put("type", "2");
        map.put("notifier", SPUtilHelper.getUserId());

        if (isShowDialog) {
            showLoadingDialog();
        }

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getNoticePage("805305", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<NoticeBean>>(mActivity) {
            @Override
            protected void onSuccess(ResponseInListModel<NoticeBean> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无消息", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
