package com.cdkj.wzcd.main.credit.module.lrfk;

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
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActLrfkBinding;
import com.cdkj.wzcd.databinding.ActYhtjBinding;
import com.cdkj.wzcd.main.credit.module.CreditDetailFragment;
import com.cdkj.wzcd.main.credit.module.zrsh.ZrshActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import retrofit2.Call;

import java.util.*;

/**
 * @author : qianLei
 * @since : 2020/1/1 19:07
 */
public class LrfkActivity extends AbsBaseLoadActivity {

    private ActLrfkBinding mBinding;

    private String code;
    private ZrzlBean bean;

    private List<DataDictionary> list = new ArrayList<>();

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, LrfkActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, code);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_lrfk, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("录入放款");

        init();
        initListener();

        getDetail();
    }

    private void init() {
        code = getIntent().getStringExtra(CdRouteHelper.DATA_SIGN);

        for (int i = 1; i < 32; i++) {

            list.add(new DataDictionary().setDkey(i + "").setDvalue(i + ""));

        }

        mBinding.slRepayBankDate.setData(list);
        mBinding.slRepayBillDate.setData(list);

    }

    private void initListener() {
        mBinding.mlYhht.setOnClickListener(view -> {
            YhhtActivity.open(this, bean);
        });

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

        if (mBinding.elLoanNumber.check()) {
            return false;
        }
        if (mBinding.slRepayBankDate.check()) {
            return false;
        }
        if (mBinding.slRepayBillDate.check()) {
            return false;
        }
        if (mBinding.elBankcardNumber.check()) {
            return false;
        }

        return true;
    }

    private void doRequest() {

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("code", bean.getCode());
        hashMap.put("operator", SPUtilHelper.getUserId());
        hashMap.put("loanNumber", mBinding.elLoanNumber.getText());
        hashMap.put("repayBankDate", mBinding.slRepayBankDate.getDataKey());
        hashMap.put("repayBillDate", mBinding.slRepayBillDate.getDataKey());
        hashMap.put("bankcardNumber", mBinding.elBankcardNumber.getText());
        hashMap.put("bankFkRemark", mBinding.rlBankFkRemark.getText());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632572", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(LrfkActivity.this, "操作成功", dialogInterface -> {
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
