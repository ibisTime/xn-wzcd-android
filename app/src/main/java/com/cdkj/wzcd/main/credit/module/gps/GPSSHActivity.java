package com.cdkj.wzcd.main.credit.module.gps;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsRefreshListActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.main.credit.adapter.GPSSHAdapter;
import com.cdkj.wzcd.main.credit.bean.GPSSHBean;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

public class GPSSHActivity extends AbsRefreshListActivity {

    public static void open(Context context) {
        context.startActivity(new Intent(context, GPSSHActivity.class));
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("GPS审核列表");
        initRefreshHelper(10);
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {

        GPSSHAdapter mAdapter = new GPSSHAdapter(listData);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            //跳转到详情
            GPSSHBean bean = (GPSSHBean) adapter.getItem(position);
            GPSSHDetialsActivity.open(this, bean.getCode());
        });
        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", SPUtilHelper.getUserToken());
        map.put("limit", limit + "");
        map.put("start", pageIndex + "");
        Call<BaseResponseModel<ResponseInListModel<GPSSHBean>>> gpsExamine = RetrofitUtils.createApi(MyApiServer.class).getGPSExamine("632715", StringUtils.getJsonToString(map));
        showLoadingDialog();
        gpsExamine.enqueue(new BaseResponseModelCallBack<ResponseInListModel<GPSSHBean>>(this) {
            @Override
            protected void onSuccess(ResponseInListModel<GPSSHBean> data, String SucMessage) {
                List<GPSSHBean> list = data.getList();
                mRefreshHelper.setData(list, "暂无审核数据", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRefreshHelper.onDefaultMRefresh(true);
    }
}
