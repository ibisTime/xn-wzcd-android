package com.cdkj.wzcd.module.tool.uservoid;

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
import com.cdkj.wzcd.adpter.adapter.ZrdListAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.ZrdModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * 准入单列表
 * Created by cdkj on 2018/5/30.
 */

public class ZrdListActivity extends AbsRefreshListActivity {

    private List<DataDictionary> mList;

    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ZrdListActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("准入单");
        initRefreshHelper(10);
        mRefreshHelper.onDefaultMRefresh(true);
    }


    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        ZrdListAdapter zrdListAdapter = new ZrdListAdapter(listData, mList);
        zrdListAdapter.setOnItemClickListener((adapter, view, position) -> {

            EventBus.getDefault().post(zrdListAdapter.getItem(position));
            finish();

        });
        return zrdListAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        DataDictionaryHelper.getDataDictionaryRequest(ZrdListActivity.this, DataDictionaryHelper.gps_apply_status, "", (List<DataDictionary> list) -> {

            if (list == null || list.size()==0)
                return;

            mList = list;

            Map<String, String> map = RetrofitUtils.getRequestMap();

            map.put("start", pageIndex + "");
            map.put("limit", limit + "");
//            map.put("saleUserId", SPUtilHelper.getUserId());　　

            if (isShowDialog) showLoadingDialog();

            Call call = RetrofitUtils.createApi(MyApiServer.class).getZrdList("632145", StringUtils.getJsonToString(map));
            addCall(call);

            call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<ZrdModel>>(this) {
                @Override
                protected void onSuccess(ResponseInListModel<ZrdModel> data, String SucMessage) {
                    mRefreshHelper.setData(data.getList(), "暂无准入单记录", 0);
                }

                @Override
                protected void onFinish() {
                    disMissLoading();
                }
            });

        });
    }
}
