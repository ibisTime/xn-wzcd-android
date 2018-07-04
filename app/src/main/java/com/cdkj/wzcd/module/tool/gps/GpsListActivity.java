package com.cdkj.wzcd.module.tool.gps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsRefreshListActivity;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.adapter.GpsAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.GpsApplyModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;


/**
 * gps申领
 */
public class GpsListActivity extends AbsRefreshListActivity<GpsApplyModel> {

    private List<DataDictionary> mList = new ArrayList<>();

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, GpsListActivity.class);
            context.startActivity(intent);
        }

    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("GPS申领");
        mBaseBinding.titleView.setRightTitle("申领");
        mBaseBinding.titleView.setRightFraClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //申请作废
                GpsApplyActivity.open(GpsListActivity.this);
            }
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
        GpsAdapter mAdapter = new GpsAdapter(listData, mList);

        mAdapter.setOnItemClickListener((adapter, view, position) -> GpsDetailsActivity.open(GpsListActivity.this, mAdapter.getItem(position).getCode()));

        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        List<DataDictionary> list = DataDictionaryHelper.getListByParentKey(DataDictionaryHelper.gps_apply_status);

        if (list == null || list.size() == 0){
            return;
        }
        mList.addAll(list);

        Map<String, String> map = new HashMap<>();

        map.put("applyUser", SPUtilHelper.getUserId());
        map.put("start", pageIndex + "");
        map.put("limit", limit + "");

        if (isShowDialog) showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getGPSApplyList("632715", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<GpsApplyModel>>(this) {
            @Override
            protected void onSuccess(ResponseInListModel<GpsApplyModel> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无申领记录", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });



    }



}