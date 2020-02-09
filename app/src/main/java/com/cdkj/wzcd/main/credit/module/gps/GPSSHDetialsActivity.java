package com.cdkj.wzcd.main.credit.module.gps;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.api.BaseResponseListModel;
import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityGpsshdetialsBinding;
import com.cdkj.wzcd.main.credit.bean.ConfirmBean;
import com.cdkj.wzcd.main.credit.bean.GPSDetialsGPSListBean;
import com.cdkj.wzcd.main.credit.bean.GPSSHDetialsBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;

public class GPSSHDetialsActivity extends AbsBaseLoadActivity {
    private ActivityGpsshdetialsBinding mBinding;
    private String code;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_gpsshdetials);
//    }

    public static void open(Context context, String code) {
        Intent intent = new Intent(context, GPSSHDetialsActivity.class);
        intent.putExtra("code", code);
        context.startActivity(intent);

    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_gpsshdetials, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("GPS审核");
        if (getIntent() != null) {
            code = getIntent().getStringExtra("code");
        }
        init();
        getData();
    }

    private void init() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()) {
                pass();
            }
        });
        mBinding.myCbConfirm.setOnConfirmRightListener(view -> {
            notPass();
        });
    }

    private void getData() {
        HashMap<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("token", SPUtilHelper.getUserToken());
        Call<BaseResponseModel<GPSSHDetialsBean>> gpsExamineDetials = RetrofitUtils.createApi(MyApiServer.class).getGPSExamineDetials("632716", StringUtils.getJsonToString(map));
        showLoadingDialog();
        gpsExamineDetials.enqueue(new BaseResponseModelCallBack<GPSSHDetialsBean>(this) {
            @Override
            protected void onSuccess(GPSSHDetialsBean data, String SucMessage) {

                mBinding.tvPople.setText(data.getApplyUserName() + "_" + data.getRoleName());
                mBinding.tvTeamName.setText(data.getTeamName());
                mBinding.tvRemaker.setText(data.getApplyReason());
            }

            @Override
            protected void onFinish() {
//                disMissLoading();
                getGPSList();
            }
        });
    }

    public void getGPSList() {
        HashMap<String, String> map = new HashMap<>();
        map.put("useStatus", "0");
        map.put("token", SPUtilHelper.getUserToken());
        Call<BaseResponseListModel<GPSDetialsGPSListBean>> gpsExamineDetials = RetrofitUtils.createApi(MyApiServer.class).getGPSExamineDetialsGPSList("632708", StringUtils.getJsonToString(map));
        showLoadingDialog();
        gpsExamineDetials.enqueue(new BaseResponseListCallBack<GPSDetialsGPSListBean>(this) {
            @Override
            protected void onSuccess(List<GPSDetialsGPSListBean> data, String SucMessage) {
                if (data != null && data.size() > 0) {
                    ArrayList<DataDictionary> gpsList = new ArrayList<>();

                    for (GPSDetialsGPSListBean datum : data) {
                        gpsList.add(new DataDictionary().setDkey(datum.getCode()).setDvalue(datum.getGpsDevNo()));
                    }
                    mBinding.slGpsList.setSelectDataList(gpsList);
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    public boolean check() {
        if (mBinding.slGpsList.check()) {
            return false;
        }

        if (mBinding.elRemaker.check()) {
            return false;
        }
        return true;
    }

    /**
     * 通过
     */
    public void pass() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", SPUtilHelper.getUserToken());
        map.put("code", code);
        map.put("operator", SPUtilHelper.getUserId());//操作人
        map.putAll(mBinding.slGpsList.getMap_List());
        Call<BaseResponseModel<ConfirmBean>> confirm = RetrofitUtils.createApi(MyApiServer.class).confirm("632711", StringUtils.getJsonToString(map));
        showLoadingDialog();
        confirm.enqueue(new BaseResponseModelCallBack<ConfirmBean>(this) {
            @Override
            protected void onSuccess(ConfirmBean data, String SucMessage) {
                ToastUtil.show(GPSSHDetialsActivity.this, "通过");
                finish();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    /**
     * 不同过
     */
    public void notPass() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("token", SPUtilHelper.getUserToken());
        map.put("code", code);
        map.put("operator", SPUtilHelper.getUserId());//操作人
        map.put("gpsList", mBinding.slGpsList.getMap_List());
        Call<BaseResponseModel<ConfirmBean>> confirm = RetrofitUtils.createApi(MyApiServer.class).confirm("632712", StringUtils.getJsonToString(map));
        showLoadingDialog();
        confirm.enqueue(new BaseResponseModelCallBack<ConfirmBean>(this) {
            @Override
            protected void onSuccess(ConfirmBean data, String SucMessage) {
                ToastUtil.show(GPSSHDetialsActivity.this, "不通过");
                finish();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

}
