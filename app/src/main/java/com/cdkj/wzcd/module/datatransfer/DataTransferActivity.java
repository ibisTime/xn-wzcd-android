package com.cdkj.wzcd.module.datatransfer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsRefreshListActivity;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.adpter.DataTransferAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.CllhListBean;
import com.cdkj.wzcd.model.DataTransferModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class DataTransferActivity extends AbsRefreshListActivity<CllhListBean> {

    private List<DataDictionary> mCompany = new ArrayList<>();

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, DataTransferActivity.class);
            context.startActivity(intent);
        }

    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("资料传递");

        initRefreshHelper(10);
    }

    @Override
    protected void onResume() {
        super.onResume();

        mRefreshHelper.onDefaultMRefresh(true);
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        DataTransferAdapter mAdapter = new DataTransferAdapter(listData, mCompany);

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
        });
        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        List<DataDictionary> list = DataDictionaryHelper.getListByParentKey(DataDictionaryHelper.kd_company);

        if (list == null || list.size() == 0){
            return;
        }
        mCompany.addAll(list);

        Map<String, String> map = new HashMap<>();

        map.put("start", pageIndex + "");
        map.put("limit", limit + "");
        map.put("userId", SPUtilHelper.getUserId());

        if (isShowDialog)
            showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getDataTransfer("632155", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<DataTransferModel>>(this) {
            @Override
            protected void onSuccess(ResponseInListModel<DataTransferModel> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无资料", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

}
