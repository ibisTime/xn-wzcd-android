package com.cdkj.wzcd.main.credit.module.qrsk;

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
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActQrskBinding;
import com.cdkj.wzcd.databinding.ActYhtjBinding;
import com.cdkj.wzcd.main.credit.module.CreditDetailFragment;
import com.cdkj.wzcd.main.credit.module.zrsh.ZrshActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import retrofit2.Call;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : qianLei
 * @since : 2020/1/1 19:07
 */
public class QrskActivity extends AbsBaseLoadActivity {

    private ActQrskBinding mBinding;

    private String code;
    private ZrzlBean bean;

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, QrskActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, code);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_qrsk, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("确认收款");

        init();
        initListener();

        getDetail();
    }

    private void init() {
        code = getIntent().getStringExtra(CdRouteHelper.DATA_SIGN);
        mBinding.dlDate.setText(DateUtil.format(new Date()));
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

    }

    private boolean check() {

        if (mBinding.dlDate.check()) {
            return false;
        }

        return true;
    }

    private void doRequest() {

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("code", bean.getCode());
        hashMap.put("operator", SPUtilHelper.getUserId());
        hashMap.put("bankFkDatetime", mBinding.dlDate.getText());
        hashMap.put("receiptRemark", mBinding.rlRemark.getText());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632573", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(QrskActivity.this, "操作成功", dialogInterface -> {
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
