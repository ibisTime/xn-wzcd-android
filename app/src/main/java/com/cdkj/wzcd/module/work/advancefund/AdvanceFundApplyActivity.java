package com.cdkj.wzcd.module.work.advancefund;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.CodeModel;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityAdvanceFundApplyBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.util.RequestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/16.
 */

public class AdvanceFundApplyActivity extends AbsBaseLoadActivity {

    private ActivityAdvanceFundApplyBinding mBinding;

    private String code;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, AdvanceFundApplyActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }

    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_advance_fund_apply, null , false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("垫资申请");
        initListener();
        initCustomView();

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getAdvanceFund();
    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check())
                apply();
        });
    }

    private void initCustomView() {

        List<DataDictionary> data = new ArrayList<>();
        data.add(new DataDictionary().setDkey("1").setDvalue("是"));
        data.add(new DataDictionary().setDkey("0").setDvalue("否"));
        mBinding.mySlIsAdvanceFund.setData(data, null);

    }

    public void getAdvanceFund(){
        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("code", code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNode("632186", StringUtils.getJsonToString(map));
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
        mBinding.myNlCompanyName.setText(data.getCompanyName());
        mBinding.myNlAmount.setText(RequestUtil.formatAmountDivSign(data.getUseAmount()));
        mBinding.myNlBank.setText(data.getLoanBankName());
        mBinding.myNlNumber.setText(data.getCollectBankcardCode());
    }

    private boolean check(){
        if (mBinding.mySlIsAdvanceFund.check()){
            return false;
        }

        return true;
    }

    public void apply(){
        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("code", code);
        map.put("isAdvanceFund", mBinding.mySlIsAdvanceFund.getDataKey());
        map.put("operator", SPUtilHelper.getUserId());

        showLoadingDialog();

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632170", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {
            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {

                UITipDialog.showSuccess(AdvanceFundApplyActivity.this, "申请成功", dialogInterface -> {
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
