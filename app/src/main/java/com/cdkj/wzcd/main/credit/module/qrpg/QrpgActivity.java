package com.cdkj.wzcd.main.credit.module.qrpg;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.custom.bean.BaseImageBean;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.ActivityRrpgBinding;
import com.cdkj.wzcd.main.credit.module.CreditDetailFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

public class QrpgActivity extends AbsBaseLoadActivity {

    private ActivityRrpgBinding mBinding;
    private boolean isDetials;
    private String code;
    private ZrzlBean bean;

    public static void open(Context context, String code, boolean isDetials) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, QrpgActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, code);
        intent.putExtra(CdRouteHelper.DATA_SIGN2, isDetials);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_rrpg, null, false);
        init();
        initView();
        initListener();
        getDetail();
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle(isDetials ? "确认评估" : "接收评估");
    }

    private void init() {
        if (getIntent() != null) {
            isDetials = getIntent().getBooleanExtra(CdRouteHelper.DATA_SIGN2, false);
            code = getIntent().getStringExtra(CdRouteHelper.DATA_SIGN);
        }

    }

    private void initDetail() {
        FragmentTransaction mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.add(R.id.ll_detail, CreditDetailFragment.getInstance(bean));
        mFragmentTransaction.commit();
    }


    private void initListener() {
        mBinding.btnConfirm.setOnConfirmListener(view -> {
            if (check()) {
                doRequest("0");
            }
        });

        mBinding.btnConfirm.setOnConfirmRightListener(view -> {
            if (check()) {
                doRequest("1");
            }
        });
        //接收评估
        mBinding.btnConfirm2.setOnConfirmListener(view -> {
            doRequest2();
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
                initDetail();
                setView();

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void initView() {
        List<BaseImageBean> list = new ArrayList<>();
        list.add(new BaseImageBean("身份证正面", "idFront"));
        list.add(new BaseImageBean("身份证反面", "idReverse"));
        list.add(new BaseImageBean("人证照片", "holdIdCardPdf", false));
        mBinding.ilInfo.init(this, list);

        mBinding.ilCarHead.initMultiple(this, "carHead");
        mBinding.ilCarRegisterCertificateFirst.initMultiple(this, "carRegisterCertificateFirst");


        List<BaseImageBean> driveLicenseList = new ArrayList<>();
        driveLicenseList.add(new BaseImageBean("行驶证", "driveLicense"));
        mBinding.ilDriveLicense.init(this, driveLicenseList);
        mBinding.ilPolicy.initMultiple(this, "policy");
        List<BaseImageBean> carInvoiceList = new ArrayList<>();
        carInvoiceList.add(new BaseImageBean("发票", "carInvoice"));
        mBinding.ilCarInvoice.init(this, carInvoiceList);

    }

    private void setView() {
        if (null != bean) {
            if (null != bean.getAttachments()) {

                List<ZrzlBean.AttachmentsBean> attachments = bean.getAttachments();

                for (ZrzlBean.AttachmentsBean bean : attachments) {
                    if (bean.getKname().equals("car_head")) {
                        mBinding.ilCarHead.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("car_register_certificate_first")) {
                        mBinding.ilCarRegisterCertificateFirst.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("policy")) {
                        mBinding.ilPolicy.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("car_invoice")) {
                        mBinding.ilCarInvoice.setData("carInvoice", bean.getUrl());
                    }
                    if (bean.getKname().equals("drive_license")) {
                        mBinding.ilDriveLicense.setData("driveLicense", bean.getUrl());
                    }

                    if (bean.getKname().equals("id_no_front_apply")) {
                        mBinding.ilInfo.setData("idFront", bean.getUrl());
                    }
                    if (bean.getKname().equals("id_no_reverse_apply")) {
                        mBinding.ilInfo.setData("idReverse", bean.getUrl());
                    }
                    if (bean.getKname().equals("hold_id_card_apply")) {
                        mBinding.ilInfo.setData("holdIdCardPdf", bean.getUrl());
                    }

                }
            }
        }
        mBinding.ilInfo.setOnClickEnable(false);
        mBinding.ilCarHead.setOnClickEnable(false);
        if (isDetials) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm2.setVisibility(View.VISIBLE);
        } else {
            mBinding.btnConfirm.setVisibility(View.VISIBLE);
        }
    }

    private boolean check() {

        if (mBinding.ilCarRegisterCertificateFirst.check()) {
            return false;
        }
        if (mBinding.ilPolicy.check()) {
            return false;
        }
        if (mBinding.ilCarInvoice.check()) {
            return false;
        }
        if (mBinding.ilDriveLicense.check()) {
            return false;
        }
        return true;
    }

    /**
     * 是否提交(0保存，1确认)
     *
     * @param isSend
     */
    private void doRequest(String isSend) {

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        Map<String, String> map = BaseViewUtil.buildMap(mBinding.llInput);
        hashMap.putAll(map);
        hashMap.put("code", code);
        hashMap.put("operator", SPUtilHelper.getUserId());
        hashMap.put("isSend", isSend);

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632543", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(QrpgActivity.this, "操作成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    /**
     * 接收评估
     */
    private void doRequest2() {

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();
        hashMap.put("code", code);
        hashMap.put("operator", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632544", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(QrpgActivity.this, "操作成功", dialogInterface -> {
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
