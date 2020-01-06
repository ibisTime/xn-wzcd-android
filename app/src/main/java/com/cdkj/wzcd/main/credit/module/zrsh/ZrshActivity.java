package com.cdkj.wzcd.main.credit.module.zrsh;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.cdkj.baselibrary.activitys.FindPwdActivity;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.MainActivity2;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActZrshBinding;
import com.cdkj.wzcd.main.credit.bean.CreditPageBean;
import com.cdkj.wzcd.main.credit.module.CreditDetailFragment;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import retrofit2.Call;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : qianLei
 * @since : 2019/12/30 16:49
 */
public class ZrshActivity extends AbsBaseLoadActivity {

    private ActZrshBinding mBinding;

    private String code;
    private ZrzlBean bean;

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ZrshActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, code);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_zrsh, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("准入审核");

        init();
        initListener();

        getDetail();
    }

    private void init() {
        code = getIntent().getStringExtra(CdRouteHelper.DATA_SIGN);
    }

    private void initListener() {
        mBinding.btnConfirm.setOnConfirmListener(view -> {
            doRequest("1");
        });

        mBinding.btnConfirm.setOnConfirmRightListener(view -> {
            doRequest("0");
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

    private void doRequest(String approveResult) {

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("code", bean.getCode());
        hashMap.put("approveResult", approveResult);
        hashMap.put("approveNote", mBinding.rlRemark.getText());
        hashMap.put("operator", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632540", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(ZrshActivity.this, "操作成功", dialogInterface -> {
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
