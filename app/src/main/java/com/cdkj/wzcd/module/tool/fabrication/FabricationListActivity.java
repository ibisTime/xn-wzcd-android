package com.cdkj.wzcd.module.tool.fabrication;

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
import com.cdkj.wzcd.adapter.FabricationListAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cdkj on 2018/6/16.
 */

public class FabricationListActivity extends AbsRefreshListActivity {

    private List<DataDictionary> mType = new ArrayList<>();

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, FabricationListActivity.class);
            context.startActivity(intent);
        }

    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("制卡");

        initRefreshHelper(10);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mRefreshHelper.onDefaultMRefresh(true);
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        FabricationListAdapter mAdapter = new FabricationListAdapter(listData, mType);

        mAdapter.setOnItemClickListener((adapter, view, position) -> {

        });

        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        List<DataDictionary> list = DataDictionaryHelper.getListByParentKey(DataDictionaryHelper.make_card_status);

        if (list == null || list.size() == 0){
            return;
        }
        mType.addAll(list);

        Map<String, String> map = RetrofitUtils.getNodeListMap();

        map.put("start", pageIndex + "");
        map.put("limit", limit + "");

        if (isShowDialog) showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNodeList("632148", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<NodeListModel>>(this) {
            @Override
            protected void onSuccess(ResponseInListModel<NodeListModel> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无制卡记录", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }
}
