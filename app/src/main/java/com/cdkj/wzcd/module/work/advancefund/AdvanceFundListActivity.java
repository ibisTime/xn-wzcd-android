package com.cdkj.wzcd.module.work.advancefund;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.base.AbsRefreshListActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.adpter.AdvanceFundListAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.AdvanceFundModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cdkj on 2018/6/16.
 */

public class AdvanceFundListActivity extends AbsRefreshListActivity {

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, AdvanceFundListActivity.class);
            context.startActivity(intent);
        }

    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("财务垫资");

        initRefreshHelper(10);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mRefreshHelper.onDefaultMRefresh(true);
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        AdvanceFundListAdapter mAdapter = new AdvanceFundListAdapter(listData);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            AdvanceFundDetailActivity.open(AdvanceFundListActivity.this, mAdapter.getItem(position).getCode());
        });

        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        Map<String, String> map = RetrofitUtils.getNodeListMap();

        map.put("start", pageIndex + "");
        map.put("limit", limit + "");
//        map.put("operator", SPUtilHelper.getUserId());

        if (isShowDialog) showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getAdvanceFundList("632185", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<AdvanceFundModel>>(this) {
            @Override
            protected void onSuccess(ResponseInListModel<AdvanceFundModel> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无垫资记录", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
