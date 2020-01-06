package com.cdkj.wzcd.main.credit.module.yksq;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActYksqBinding;
import com.cdkj.wzcd.databinding.ActZrshBinding;
import com.cdkj.wzcd.main.credit.bean.CreditPageBean;
import com.cdkj.wzcd.main.credit.module.CreditDetailFragment;
import com.cdkj.wzcd.main.credit.module.yksq.bean.TeamBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import retrofit2.Call;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : qianLei
 * @since : 2019/12/30 16:49
 */
public class YksqActivity extends AbsBaseLoadActivity {

    private ActYksqBinding mBinding;

    private String code;
    private ZrzlBean bean;

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, YksqActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, code);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_yksq, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("用款申请");

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
            doRequest();
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
                getTeam();
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

    private void getTeam() {

        if (null == bean) {
            return;
        }

        Map<String, Object> map = RetrofitUtils.getRequestMap();
        map.put("code", bean.getTeamCode());

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getTeam("630196", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<TeamBean>(this) {
            @Override
            protected void onSuccess(TeamBean data, String SucMessage) {

                setView(data);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void setView(TeamBean data) {

        mBinding.elTeam.setTextByRequest(data.getAccountNo());
        mBinding.elBank.setTextByRequest(data.getBankName());
        mBinding.elSub.setTextByRequest(data.getSubbranch());
        mBinding.elInfo.setTextByRequest(
                data.getName() + " " + MoneyUtils.showPrice(bean.getRepointAmount()) + "(车款2)");

    }

    private void doRequest() {

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("code", bean.getCode());
        hashMap.put("operator", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632550", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(YksqActivity.this, "操作成功", dialogInterface -> {
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
