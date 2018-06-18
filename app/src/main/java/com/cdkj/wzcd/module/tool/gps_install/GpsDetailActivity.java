package com.cdkj.wzcd.module.tool.gps_install;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adpter.adapter.GpsInstallAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityGpsInfoInputBinding;
import com.cdkj.wzcd.model.GpsInstallModel;
import com.cdkj.wzcd.model.NodeListModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * GPS 安装详情
 * Created by cdkj on 2018/5/30.
 */

public class GpsDetailActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivityGpsInfoInputBinding mBinding;

    private GpsInstallAdapter mAdapter;
    private List<GpsInstallModel> mList = new ArrayList<>();

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, GpsDetailActivity.class);
        intent.putExtra(DATA_SIGN, code);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_gps_info_input, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("安装详情");

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getGPS();

        initListAdapter();
    }

    public void initListAdapter() {
        mAdapter = new GpsInstallAdapter(mList);

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
//            CreditUserModel model = mAdapter.getItem(position);
        });

        mBinding.rvGps.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvGps.setAdapter(mAdapter);
    }

    public void getGPS(){
        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("code", code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNode("632146", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<NodeListModel>(this) {
            @Override
            protected void onSuccess(NodeListModel data, String SucMessage) {

                setView(data);

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void setView(NodeListModel data) {
        mBinding.myNlName.setText(data.getCustomerName());
        mBinding.myNlCode.setText(data.getCode());
        mBinding.myNlBank.setText(data.getLoanBankName());

        mBinding.llAddGps.setVisibility(View.GONE);


        if (data.getBudgetOrderGpsList() != null || data.getBudgetOrderGpsList().size() != 0){

            mList.clear();
            for (NodeListModel.BudgetOrderGpsListBean bean : data.getBudgetOrderGpsList()){

                GpsInstallModel model = new GpsInstallModel();
                model.setCode(bean.getCode());
                model.setGpsDevNo(bean.getGpsDevNo());
                model.setAzLocation(bean.getAzLocation());
                model.setAzUser(bean.getAzUser());
                model.setAzDatetime(bean.getAzDatetime());

                mList.add(model);
            }
            mAdapter.notifyDataSetChanged();

        }

    }

}
