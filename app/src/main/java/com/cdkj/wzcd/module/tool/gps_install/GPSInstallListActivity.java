package com.cdkj.wzcd.module.tool.gps_install;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.base.AbsRefreshListActivity;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adpter.adapter.GpsInstallListAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * GPS 安装
 * Created by cdkj on 2018/5/30.
 */

public class GPSInstallListActivity extends AbsRefreshListActivity {

    private List<DataDictionary> mList;

    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, GPSInstallListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle(getString(R.string.gps_install));
        initRefreshHelper(10);
    }

    @Override
    public void onResume() {
        super.onResume();

        mRefreshHelper.onDefaultMRefresh(true);
    }


    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        GpsInstallListAdapter gpsInstallListAdapter = new GpsInstallListAdapter(listData, mList);
        gpsInstallListAdapter.setOnItemClickListener((adapter, view, position) -> {
            GpsDetailActivity.open(GPSInstallListActivity.this, gpsInstallListAdapter.getItem(position).getCode());
        });
        return gpsInstallListAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        DataDictionaryHelper.getDataDictionaryRequest(GPSInstallListActivity.this, DataDictionaryHelper.gps_apply_status, "", (List<DataDictionary> list) -> {

            if (list == null || list.size()==0)
                return;

            mList = list;

            Map<String, String> map = RetrofitUtils.getNodeListMap();

            map.put("start", pageIndex + "");
            map.put("limit", limit + "");
//            map.put("saleUserId", SPUtilHelper.getUserId());

            if (isShowDialog) showLoadingDialog();

            Call call = RetrofitUtils.createApi(MyApiServer.class).getNodeList("632148", StringUtils.getJsonToString(map));
            addCall(call);

            call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<NodeListModel>>(this) {
                @Override
                protected void onSuccess(ResponseInListModel<NodeListModel> data, String SucMessage) {
                    mRefreshHelper.setData(data.getList(), "暂无安装记录", 0);
                }

                @Override
                protected void onFinish() {
                    disMissLoading();
                }
            });

        });
    }
}
