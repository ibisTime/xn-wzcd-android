package com.cdkj.wzcd.module.work.bank_loan;

import android.app.AlertDialog;
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
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityBankLoanInputBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.util.DatePickerHelper;
import com.cdkj.wzcd.util.RequestUtil;
import com.cdkj.wzcd.view.MyNormalLayout;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/7.
 */

public class BankLoanInputActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivityBankLoanInputBinding mBinding;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, BankLoanInputActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_bank_loan_input, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("录入放款信息");

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getNode();

        initListener();
        initCustomView();
    }

    private void initListener() {
        mBinding.myNlFirstRepayDate.setOnClickListener(view -> {
            new DatePickerHelper().build(this).getDate(mBinding.myNlFirstRepayDate, true, true,  true, false, false, false);
        });

        mBinding.myNlBankFkDate.setOnClickListener(view -> {
            new DatePickerHelper().build(this).getDate(mBinding.myNlBankFkDate, true, true,  true, false, false, false);
        });

        mBinding.myNlRepayBillDate.setOnClickListener(view -> {
            showMonthDay(mBinding.myNlRepayBillDate);
        });

        mBinding.myNlRepayBankDate.setOnClickListener(view -> {
            showMonthDay(mBinding.myNlRepayBankDate);
        });

        mBinding.myNlRepayCompanyDate.setOnClickListener(view -> {
            showMonthDay(mBinding.myNlRepayCompanyDate);
        });

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                inputRequest();
            }
        });
    }

    private void initCustomView() {


    }

    public void getNode(){
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
        mBinding.myNlCompanyName.setText(data.getCompanyName());
        mBinding.myNlAmount.setText(RequestUtil.formatAmountDivSign(data.getLoanAmount()));
        mBinding.myNlBank.setText(data.getLoanBankName());

    }

    public boolean check(){
        // 卡号
        if (mBinding.myElNumber.check()){
            return false;
        }
        // 账单还款日
        if (TextUtils.isEmpty(mBinding.myNlRepayBillDate.check())){
            return false;
        }
        // 银行还款日
        if (TextUtils.isEmpty(mBinding.myNlRepayBankDate.check())){
            return false;
        }
        // 公司还款日
        if (TextUtils.isEmpty(mBinding.myNlRepayCompanyDate.check())){
            return false;
        }
        // 首期还款日期
        if (TextUtils.isEmpty(mBinding.myNlFirstRepayDate.check())){
            return false;
        }
        // 首期月供金额
        if (mBinding.myElRepayFirstMonthAmount.check()){
            return false;
        }
        // 每期月供金额
        if (mBinding.myElRepayMonthAmount.check()){
            return false;
        }
        // 首期放款日期
        if (TextUtils.isEmpty(mBinding.myNlBankFkDate.check())){
            return false;
        }

        return true;
    }

    public void inputRequest(){
        Map<String, Object> map = new HashMap<>();

        map.put("code", code);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("repayBankcardNumber", mBinding.myElNumber.getText());
        map.put("repayBillDate", mBinding.myNlRepayBillDate.getText());
        map.put("repayBankDate", mBinding.myNlRepayBankDate.getText());
        map.put("repayCompanyDate", mBinding.myNlRepayCompanyDate.getText());
        map.put("repayFirstMonthAmount", mBinding.myElRepayFirstMonthAmount.getText());
        map.put("repayMonthAmount", mBinding.myElRepayMonthAmount.getText());
        map.put("bankFkDate", mBinding.myNlBankFkDate.getText());
        map.put("repayFirstMonthDatetime", mBinding.myNlFirstRepayDate.getText());

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632135", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(BankLoanInputActivity.this, "录入成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(BankLoanInputActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void showMonthDay(MyNormalLayout normalLayout){

        String[] list = new String[28];

        for (int i = 0; i < 28; i++){

            list[i] = (1+i)+"";

        }

        new AlertDialog.Builder(this).setTitle("请选择").setSingleChoiceItems(
                list, 0, (dialog, which) -> {

                    normalLayout.setText(list[which]);
                    dialog.dismiss();

                }).setNegativeButton("取消", null).show();
    }
}
