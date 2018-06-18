package com.cdkj.wzcd.module.tool.fabaohe;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
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
import com.cdkj.wzcd.databinding.ActivityFbhDetailBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.util.DatePickerHelper;
import com.cdkj.wzcd.util.RequestUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.getValueOnTheKey;

/**
 * Created by cdkj on 2018/6/17.
 */

public class FbhDetailActivity extends AbsBaseLoadActivity {

    private ActivityFbhDetailBinding mBinding;

    private String code;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, FbhDetailActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }

    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_fbh_detail, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("录入");

        initListener();
        initCustomView();

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getAdvanceFund();
    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                inputRequest();
            }
        });
    }

    private void initCustomView() {
        mBinding.myNlBuyCarDateTime.setOnClickListener(view -> {
            new DatePickerHelper().build(this).getDate(mBinding.myNlBuyCarDateTime, true, true,  true, false, false, false);
        });

        mBinding.mySlIsRight.setData(getDataDictionaries(), null);

        mBinding.myIlBill.setActivity(this, 0, -1);
        mBinding.myIlCertification.setActivity(this, 1, -1);
        mBinding.myIlJqx.setActivity(this, 2, -1);
        mBinding.myIlSyx.setActivity(this, 3, -1);
        mBinding.myIlCarRegister.setActivity(this, 4, -1);
        mBinding.myIlEndorsement.setActivity(this, 5, -1);
    }

    @NonNull
    private List<DataDictionary> getDataDictionaries() {
        List<DataDictionary> data = new ArrayList<>();
        data.add(new DataDictionary().setDkey("1").setDvalue("是"));
        data.add(new DataDictionary().setDkey("0").setDvalue("否"));
        return data;
    }


    public void getAdvanceFund(){
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
        mBinding.myNlLoanAmount.setText(RequestUtil.formatAmountDivSign(data.getLoanAmount()));
        mBinding.myNlBank.setText(data.getLoanBankName());
        mBinding.myNlWay.setText(getValueOnTheKey(data.getShopWay()));

    }

    private boolean check(){
        if (TextUtils.isEmpty(mBinding.myNlBuyCarDateTime.check())){
            return false;
        }

        if (mBinding.mySlIsRight.check()){
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myElCurrentBillPrice.check())){
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myIlBill.check())){
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myIlCertification.check())){
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myIlJqx.check())){
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myIlSyx.check())){
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myIlCarRegister.check())){
            return false;
        }


        return true;
    }

    public void inputRequest(){
        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("code", code);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("deliveryDatetime", mBinding.myNlBuyCarDateTime.getText());
        map.put("isRightInvoice", mBinding.mySlIsRight.getDataKey());
        map.put("currentInvoicePrice", mBinding.myElCurrentBillPrice.getMoneyText());
        map.put("invoice", mBinding.myIlBill.getFlImgUrl());
        map.put("certification", mBinding.myIlCertification.getFlImgUrl());
        map.put("forceInsurance", mBinding.myIlJqx.getFlImgUrl());
        map.put("businessInsurance", mBinding.myIlSyx.getFlImgUrl());
        map.put("motorRegCertification", mBinding.myIlCarRegister.getFlImgUrl());
        map.put("pdPdf", mBinding.myIlEndorsement.getFlImgUrl());

        showLoadingDialog();

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632220", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {
            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(FbhDetailActivity.this, "录入成功", dialogInterface -> {
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
