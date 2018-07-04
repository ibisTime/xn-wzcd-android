package com.cdkj.wzcd.module.tool.fabaohe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.base.AbsRefreshListActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.adpter.FbhListAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.NodeListModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cdkj on 2018/6/16.
 */

public class FbhListActivity extends AbsRefreshListActivity {

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, FbhListActivity.class);
            context.startActivity(intent);
        }

    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("发保合");

        initRefreshHelper(10);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mRefreshHelper.onDefaultMRefresh(true);
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        FbhListAdapter mAdapter = new FbhListAdapter(listData);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            FbhDetailActivity.open(FbhListActivity.this, mAdapter.getItem(position).getCode(),true);
        });

        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        Map<String, String> map = RetrofitUtils.getNodeListMap();

        map.put("start", pageIndex + "");
        map.put("limit", limit + "");

        if (isShowDialog) showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNodeList("632148", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<NodeListModel>>(this) {
            @Override
            protected void onSuccess(ResponseInListModel<NodeListModel> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无发保合记录", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
