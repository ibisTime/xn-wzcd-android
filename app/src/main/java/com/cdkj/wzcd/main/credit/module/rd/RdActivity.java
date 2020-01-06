package com.cdkj.wzcd.main.credit.module.rd;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActRdBinding;
import com.cdkj.wzcd.databinding.ActYhtjBinding;
import com.cdkj.wzcd.main.credit.module.CreditDetailFragment;
import com.cdkj.wzcd.main.credit.module.rd.bean.BXCompanyBean;
import com.cdkj.wzcd.main.credit.module.rd.bean.RdPlaceBean;
import com.cdkj.wzcd.main.credit.module.zrsh.ZrshActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CarSeriesBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;

import java.util.*;

import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.SET_UPLOAD_RESULT;

/**
 * @author : qianLei
 * @since : 2020/1/1 19:07
 */
public class RdActivity extends AbsBaseLoadActivity {

    private ActRdBinding mBinding;

    private String code;
    private ZrzlBean bean;

    private List<DataDictionary> rdPlaceList = new ArrayList<>();
    private List<DataDictionary> bxCompanyList = new ArrayList<>();

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, RdActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, code);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_rd, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("入档");

        init();
        initListener();

        getDetail();
    }

    private void init() {
        code = getIntent().getStringExtra(CdRouteHelper.DATA_SIGN);
    }

    private void initListener() {
        mBinding.btnConfirm.setOnConfirmListener(view -> {
            finish();
        });

        mBinding.btnConfirm.setOnConfirmRightListener(view -> {
            if (check()) {
                doRequest();
            }
        });
    }

    private void getDetail() {

        Map<String, Object> map = RetrofitUtils.getRequestMap();
        map.put("code", code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getZrzlDetail("632516", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ZrzlBean>(this) {
            @Override
            protected void onSuccess(ZrzlBean data, String SucMessage) {
                bean = data;

                getCode();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void getCode() {

        Call call = RetrofitUtils.getBaseAPiService()
                .stringRequest("632592", StringUtils.getJsonToString(new HashMap<>()));

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<String>(this) {
            @Override
            protected void onSuccess(String data, String SucMessage) {
                mBinding.elEnterCode.setTextByRequest(data);

                getPlace();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    /**
     * 分页查询档案存放位置
     */
    public void getPlace() {

        Map<String, String> map = new HashMap<>();
        map.put("start", "1");
        map.put("limit", "10000");

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getRdPlacePage("632825", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<RdPlaceBean>>(this) {

            @Override
            protected void onSuccess(ResponseInListModel<RdPlaceBean> data, String SucMessage) {

                rdPlaceList.clear();
                for (RdPlaceBean model : data.getList()) {

                    rdPlaceList.add(new DataDictionary().setDkey(model.getCode() + "")
                            .setDvalue(model.getName()));

                }

                getCompany();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    /**
     * 分页查保险公司
     */
    public void getCompany() {

        Map<String, String> map = new HashMap<>();
        map.put("start", "1");
        map.put("limit", "10000");

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getBXCompanyPage("632045", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<BXCompanyBean>>(this) {

            @Override
            protected void onSuccess(ResponseInListModel<BXCompanyBean> data, String SucMessage) {

                bxCompanyList.clear();
                for (BXCompanyBean model : data.getList()) {

                    bxCompanyList.add(new DataDictionary().setDkey(model.getCode() + "")
                            .setDvalue(model.getName()));

                }

                initDetail();
                setView();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void initDetail() {
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.ll_detail, CreditDetailFragment.getInstance(bean));
        mFragmentTransaction.commit();
    }

    private void setView() {

        mBinding.slEnterLocation.setData(rdPlaceList);
        mBinding.slInsuranceCompany.setData(bxCompanyList);

        mBinding.ilAdvanceContract.initMultiple(this, "advanceContract");
        mBinding.ilGuarantorContract.initMultiple(this, "guarantorContract");
        mBinding.ilPledgeContract.initMultiple(this, "pledgeContract");
        mBinding.ilEnterOtherPdf.initMultiple(this, "enterOtherPdf");
    }

    private boolean check() {

        if (mBinding.slEnterLocation.check()) {
            return false;
        }
        if (mBinding.slInsuranceCompany.check()) {
            return false;
        }
        if (mBinding.dlSyxDateStart.check()) {
            return false;
        }
        if (mBinding.dlSyxDateEnd.check()) {
            return false;
        }
        if (mBinding.ilAdvanceContract.check()) {
            return false;
        }
        if (mBinding.ilGuarantorContract.check()) {
            return false;
        }
        if (mBinding.ilPledgeContract.check()) {
            return false;
        }
        if (mBinding.ilEnterOtherPdf.check()) {
            return false;
        }

        return true;
    }

    private void doRequest() {

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("code", bean.getCode());
        hashMap.put("operator", SPUtilHelper.getUserId());
        hashMap.put("enterCode", mBinding.elEnterCode.getText());
        hashMap.put("enterLocation", mBinding.slEnterLocation.getDataKey());
        hashMap.put("insuranceCompany", mBinding.slInsuranceCompany.getDataKey());
        hashMap.put("syxDateStart", mBinding.dlSyxDateStart.getText());
        hashMap.put("syxDateEnd", mBinding.dlSyxDateEnd.getText());
        hashMap.put("advanceContract", mBinding.ilAdvanceContract.getData());
        hashMap.put("guarantorContract", mBinding.ilGuarantorContract.getData());
        hashMap.put("pledgeContract", mBinding.ilPledgeContract.getData());
        hashMap.put("enterOtherPdf", mBinding.ilEnterOtherPdf.getData());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632590", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(RdActivity.this, "操作成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });


    }
}
