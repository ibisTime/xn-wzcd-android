package com.cdkj.wzcd.module.tool.uservoid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsRefreshListActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.adpter.adapter.UserToVoidAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.model.UserToVoidBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * 用户作废
 */
public class UserToVoidActivity extends AbsRefreshListActivity<UserToVoidBean> {

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, UserToVoidActivity.class);
            context.startActivity(intent);
        }

    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("客户作废");
        mBaseBinding.titleView.setRightTitle("申请作废");
        mBaseBinding.titleView.setRightFraClickListener(v -> {
            //申请作废
            BlankOutActivity.open(UserToVoidActivity.this);
        });

        initRefreshHelper(10);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mRefreshHelper.onDefaultMRefresh(true);
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        UserToVoidAdapter mAdapter = new UserToVoidAdapter(listData);

        mAdapter.setOnItemClickListener((adapter, view, position) -> UserToVoidDetailsActivity.open(UserToVoidActivity.this, mAdapter.getItem(position).getCode()));

        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        Map<String, String> map = RetrofitUtils.getNodeListMap();

        map.put("start", pageIndex + "");
        map.put("limit", limit + "");
        map.put("saleUserId", SPUtilHelper.getUserId());

        if (isShowDialog) showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNodeList("632195", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<NodeListModel>>(this) {
            @Override
            protected void onSuccess(ResponseInListModel<NodeListModel> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无作废记录", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

}