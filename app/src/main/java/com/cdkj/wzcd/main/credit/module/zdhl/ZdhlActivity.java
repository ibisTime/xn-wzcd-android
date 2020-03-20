package com.cdkj.wzcd.main.credit.module.zdhl;

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
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActYhtjBinding;
import com.cdkj.wzcd.databinding.ActZdhlBinding;
import com.cdkj.wzcd.main.credit.module.CreditDetailFragment;
import com.cdkj.wzcd.main.credit.module.zdhl.bean.ShoukuanAccountBean;
import com.cdkj.wzcd.main.credit.module.zrsh.ZrshActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import com.cdkj.wzcd.model.LocalityModel;
import retrofit2.Call;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author : qianLei
 * @since : 2020/1/1 19:07
 */
public class ZdhlActivity extends AbsBaseLoadActivity {

    private ActZdhlBinding mBinding;

    private String code;
    private ZrzlBean bean;

    private List<DataDictionary> localityList = new ArrayList<>();
    private List<ShoukuanAccountBean> accountList = new ArrayList<>();

    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ZdhlActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, code);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.act_zdhl, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("制单回录");

        init();
        initListener();

        getDetail();
    }

    private void init() {
        code = getIntent().getStringExtra(CdRouteHelper.DATA_SIGN);

        mBinding.elLoanAmount.setFocusable(false);
        mBinding.elRepointAmount.setFocusable(false);
        mBinding.elHeji.setFocusable(false);
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

                getAccounnt();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    public void getAccounnt() {

        Map<String, String> map = new HashMap<>();
        map.put("start", "1");
        map.put("limit", "10000");
        map.put("type", "4");

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getShoukuanAccountPage("632005", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<ShoukuanAccountBean>>(this) {

            @Override
            protected void onSuccess(ResponseInListModel<ShoukuanAccountBean> data, String SucMessage) {

                accountList.clear();
                accountList.addAll(data.getList());

                localityList.clear();
                for (ShoukuanAccountBean model : data.getList()) {

                    localityList.add(new DataDictionary().setDkey(model.getCode() + "")
                            .setDvalue(model.getCompanyName() + "-" + model.getBankName()));

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

        mBinding.elLoanAmount.setText(bean.getLoanAmount());
        mBinding.elRepointAmount.setText(bean.getRepointAmount());
        mBinding.elHeji.setText(new BigDecimal(bean.getRepointAmount()).add(new BigDecimal(bean.getLoanAmount())).toPlainString());

        mBinding.slTeam.setData(localityList, (dialog, which) -> {

            mBinding.elRealName.setTextByRequest(accountList.get(which).getRealName());
            mBinding.elBankcardNumber.setTextByRequest(accountList.get(which).getBankcardNumber());

        });

    }

    private void check(){

        if (mBinding.slTeam.check()){
            return;
        }

    }

    private void doRequest() {

        check();

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("code", bean.getCode());
        hashMap.put("operator", SPUtilHelper.getUserId());
        hashMap.put("advanceOutCardCode", mBinding.slTeam.getDataKey());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632553", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(ZdhlActivity.this, "操作成功", dialogInterface -> {
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
