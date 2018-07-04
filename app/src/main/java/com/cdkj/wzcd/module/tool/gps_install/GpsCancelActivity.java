package com.cdkj.wzcd.module.tool.gps_install;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.CodeModel;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adpter.GpsInstallAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityGpsCancelBinding;
import com.cdkj.wzcd.model.GpsInstallModel;
import com.cdkj.wzcd.model.NodeListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * GPS 作废
 * Created by cdkj on 2018/5/30.
 */

public class GpsCancelActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivityGpsCancelBinding mBinding;

    private GpsInstallAdapter mAdapter;
    private List<GpsInstallModel> mList = new ArrayList<>();
    private List<DataDictionary> mDataList = new ArrayList<>();

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, GpsCancelActivity.class);
        intent.putExtra(DATA_SIGN, code);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_gps_cancel, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("回收作废");
        initCustomView();
        initListener();

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getGPS();

        initListAdapter();
    }

    private void initCustomView() {
        mBinding.mySlCancelCode.setData(mDataList,null);
    }

    private void initListener() {

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            LogUtil.E("myCbConfirm = " + check());

            if (check())
                request();
        });
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
        mBinding.myNlLoanAmount.setMoneyText(data.getLoanAmount());



        if (data.getBudgetOrderGpsList() == null || data.getBudgetOrderGpsList().size() == 0){
            return;
        }

        mList.clear();
        for (NodeListModel.BudgetOrderGpsListBean bean : data.getBudgetOrderGpsList()){

            GpsInstallModel model = new GpsInstallModel();
            model.setCode(bean.getCode());
            model.setStatus(bean.getStatus());
            model.setGpsType(bean.getGpsType());
            model.setGpsDevNo(bean.getGpsDevNo());
            model.setAzLocation(bean.getAzLocation());
            model.setAzUser(bean.getAzUser());
            model.setAzDatetime(bean.getAzDatetime());

            if (TextUtils.equals(model.getStatus(), "1")) //使用中
                mDataList.add(new DataDictionary().setDkey(bean.getCode()).setDvalue(bean.getGpsDevNo()));

            mList.add(model);
        }

        mAdapter.notifyDataSetChanged();
        mBinding.mySlCancelCode.setData(mDataList,null);

    }

    private boolean check(){

        // 作废设备号
        if (mBinding.mySlCancelCode.check()){
            return false;
        }

        return true;
    }

    private void request(){
        Map<String, Object> map = new HashMap<>();

        map.put("code", mBinding.mySlCancelCode.getDataKey());
        map.put("operator", SPUtilHelper.getUserId());
        map.put("remark", mBinding.myElRemark.getText());

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632343", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(GpsCancelActivity.this, "报废成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(GpsCancelActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

}
