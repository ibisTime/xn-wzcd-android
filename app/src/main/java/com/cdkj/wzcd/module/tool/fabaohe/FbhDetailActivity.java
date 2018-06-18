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
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.QiNiuHelper;
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
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN2;
import static com.cdkj.wzcd.util.DataDictionaryHelper.getValueOnTheKey;

/**
 * 发保合详情
 * Created by cdkj on 2018/6/17.
 */

public class FbhDetailActivity extends AbsBaseLoadActivity {

    private ActivityFbhDetailBinding mBinding;

    private String code;

    private boolean isViweDetails;//是否查看详情状态

    /**
     * @param context
     * @param code
     * @param isViweDetails
     */
    public static void open(Context context, String code, boolean isViweDetails) {
        if (context != null) {
            Intent intent = new Intent(context, FbhDetailActivity.class);
            intent.putExtra(DATA_SIGN, code);
            intent.putExtra(DATA_SIGN2, isViweDetails);
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


        code = getIntent().getStringExtra(DATA_SIGN);
        isViweDetails = getIntent().getBooleanExtra(DATA_SIGN2, false);

        if (isViweDetails) {
            mBaseBinding.titleView.setMidTitle("详情");
        } else {
            mBaseBinding.titleView.setMidTitle("录入");
        }

        initListener();
        initCustomView();

        getAdvanceFund();
    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()) {
                inputRequest();
            }
        });
    }

    private void initCustomView() {


        if (isViweDetails) {
            mBinding.mySlIsRight.setData(getDataDictionaries());
            return;
        }

        mBinding.mySlIsRight.setData(getDataDictionaries(), (dialog, which) -> {

        });

        mBinding.myNlBuyCarDateTime.setOnClickListener(view -> {
            new DatePickerHelper().build(this).getDate(mBinding.myNlBuyCarDateTime, true, true, true, false, false, false);
        });

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


    public void getAdvanceFund() {

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

        if (data == null) return;

        /*        mBinding.myIlBill.setActivity(this, 0, -1);
        mBinding.myIlCertification.setActivity(this, 1, -1);
        mBinding.myIlJqx.setActivity(this, 2, -1);
        mBinding.myIlSyx.setActivity(this, 3, -1);
        mBinding.myIlCarRegister.setActivity(this, 4, -1);
        mBinding.myIlEndorsement.setActivity(this, 5, -1);*/

        /*  map.put("invoice", mBinding.myIlBill.getFlImgUrl());
        map.put("certification", mBinding.myIlCertification.getFlImgUrl());
        map.put("forceInsurance", mBinding.myIlJqx.getFlImgUrl());
        map.put("businessInsurance", mBinding.myIlSyx.getFlImgUrl());
        map.put("motorRegCertification", mBinding.myIlCarRegister.getFlImgUrl());
        map.put("pdPdf", mBinding.myIlEndorsement.getFlImgUrl());*/

        if (isViweDetails) {  //查看详情状态
            mBinding.myCbConfirm.setVisibility(View.GONE);
            mBinding.myElCurrentBillPrice.setMoneyTextByRequest(data.getInvoicePrice());
            mBinding.myIlBill.setFlImgByRequest(data.getInvoice());
            mBinding.myIlCertification.setFlImgByRequest(data.getCertification());
            mBinding.myIlJqx.setFlImgByRequest(data.getForceInsurance());
            mBinding.myIlSyx.setFlImgByRequest(data.getBusinessInsurance());
            mBinding.myIlCarRegister.setFlImgByRequest(data.getMotorRegCertification());
            mBinding.myIlEndorsement.setFlImgByRequest(data.getPdPdf());


        } else {
            mBinding.myCbConfirm.setVisibility(View.VISIBLE);
            mBinding.myElCurrentBillPrice.setMoneyText(data.getInvoicePrice());

            mBinding.myIlBill.setFlImg(data.getInvoice());
            mBinding.myIlCertification.setFlImg(data.getCertification());
            mBinding.myIlJqx.setFlImg(data.getForceInsurance());
            mBinding.myIlSyx.setFlImg(data.getBusinessInsurance());
            mBinding.myIlCarRegister.setFlImg(data.getMotorRegCertification());
            mBinding.myIlEndorsement.setFlImg(data.getPdPdf());
        }

        mBinding.mySlIsRight.setContentByKey(data.getIsRightInvoice());

        mBinding.myNlBuyCarDateTime.setText(data.getDeliveryDatetime());

        mBinding.myNlName.setText(data.getCustomerName());
        mBinding.myNlCode.setText(data.getCode());
        mBinding.myNlLoanAmount.setText(RequestUtil.formatAmountDivSign(data.getLoanAmount()));
        mBinding.myNlBank.setText(data.getLoanBankName());
        mBinding.myNlWay.setText(getValueOnTheKey(data.getShopWay()));
        mBinding.myNlBillPrice.setMoneyText(data.getInvoicePrice());


    }

    private boolean check() {
        if (TextUtils.isEmpty(mBinding.myNlBuyCarDateTime.check())) {
            return false;
        }

        if (mBinding.mySlIsRight.check()) {
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myElCurrentBillPrice.check())) {
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myIlBill.check())) {
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myIlCertification.check())) {
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myIlJqx.check())) {
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myIlSyx.check())) {
            return false;
        }

        if (TextUtils.isEmpty(mBinding.myIlCarRegister.check())) {
            return false;
        }


        return true;
    }

    public void inputRequest() {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        String path = data.getStringExtra(CameraHelper.staticPath);
        showLoadingDialog();
        new QiNiuHelper(this).uploadSinglePic(new QiNiuHelper.QiNiuCallBack() {
            @Override
            public void onSuccess(String key) {

                if (requestCode == mBinding.myIlBill.getRequestCode()) {

                    mBinding.myIlBill.setFlImg(key);

                } else if (requestCode == mBinding.myIlCertification.getRequestCode()) {
                    mBinding.myIlCertification.setFlImg(key);
                } else if (requestCode == mBinding.myIlJqx.getRequestCode()) {
                    mBinding.myIlJqx.setFlImg(key);
                } else if (requestCode == mBinding.myIlSyx.getRequestCode()) {
                    mBinding.myIlSyx.setFlImg(key);
                } else if (requestCode == mBinding.myIlCarRegister.getRequestCode()) {
                    mBinding.myIlCarRegister.setFlImg(key);
                } else if (requestCode == mBinding.myIlEndorsement.getRequestCode()) {
                    mBinding.myIlEndorsement.setFlImg(key);
                }

                disMissLoading();
            }

            @Override
            public void onFal(String info) {
                disMissLoading();
            }
        }, path);
    }
}
