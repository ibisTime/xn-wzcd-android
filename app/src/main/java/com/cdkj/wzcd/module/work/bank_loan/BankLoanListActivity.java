package com.cdkj.wzcd.module.work.bank_loan;

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
import com.cdkj.wzcd.adapter.BankLoanListAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.CllhListBean;
import com.cdkj.wzcd.model.NodeListModel;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * 银行放款
 */
public class BankLoanListActivity extends AbsRefreshListActivity<CllhListBean> {

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, BankLoanListActivity.class);
            context.startActivity(intent);
        }

    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("银行放款");

        initRefreshHelper(10);

    }

    @Override
    protected void onResume() {
        super.onResume();

        mRefreshHelper.onDefaultMRefresh(true);
    }

    @Override
    public RecyclerView.Adapter getListAdapter(List listData) {
        BankLoanListAdapter mAdapter = new BankLoanListAdapter(listData);

//        mAdapter.setOnItemClickListener((adapter, view, position) -> {
//            CllhInputMessageActivity.open(BankLoanListActivity.this, mAdapter.getItem(position).getCode());
//        });

        return mAdapter;
    }

    @Override
    public void getListRequest(int pageIndex, int limit, boolean isShowDialog) {
        Map<String, String> map = RetrofitUtils.getNodeListMap();

        map.put("start", pageIndex + "");
        map.put("limit", limit + "");
        map.put("currentUserCompanyCode", SPUtilHelper.getUserCompanyCode());


        if (isShowDialog) showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNodeList("632148", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<NodeListModel>>(this) {
            @Override
            protected void onSuccess(ResponseInListModel<NodeListModel> data, String SucMessage) {
                mRefreshHelper.setData(data.getList(), "暂无放款记录", 0);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }


}
