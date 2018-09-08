package com.cdkj.wzcd.module.work.advancefund;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityAdvanceFundDetailBinding;
import com.cdkj.wzcd.model.AdvanceFundModel;
import com.cdkj.wzcd.module.work.credit.CreditDetailActivity;
import com.cdkj.wzcd.util.RequestUtil;

import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/16.
 */

public class AdvanceFundDetailActivity extends AbsBaseLoadActivity {

    private ActivityAdvanceFundDetailBinding mBinding;

    private String code;

    private AdvanceFundModel mData;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, AdvanceFundDetailActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }

    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_advance_fund_detail, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("垫资详情");

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getAdvanceFund();
        initOnclick();
    }

    private void initOnclick() {
        mBinding.myNlResult.setOnClickListener(view -> {
            if (mData != null && mData.getBudgetOrder() != null) {
                CreditDetailActivity.open(this, mData.getBudgetOrder().getCreditCode(), false);
            }
        });
    }

    public void getAdvanceFund() {
        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("code", code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getAdvanceFund("632186", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<AdvanceFundModel>(this) {


            @Override
            protected void onSuccess(AdvanceFundModel data, String SucMessage) {
                mData = data;
                setView(data);

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void setView(AdvanceFundModel data) {
        mBinding.myNlName.setText(data.getCustomerName());
        mBinding.myNlCode.setText(data.getCode());
        mBinding.myNlCompanyName.setText(data.getBizCompanyName());
        mBinding.myNlDealers.setText(data.getCarDealerName());
        mBinding.myNlAmount.setText(RequestUtil.formatAmountDivSign(data.getBudgetOrder().getLoanAmount()));
        mBinding.myNlBank.setText(data.getLoanBankName());
        mBinding.myNlNumber.setText(data.getCollectionAccountNo());
        mBinding.myNlIsAdvanceFund.setText(TextUtils.equals(data.getIsAdvanceFund(), "1") ? "是" : "否");
        mBinding.myNlAdvanceFundDateTime.setText(DateUtil.formatStringData(data.getBudgetOrder().getApplyDatetime(), DateUtil.DEFAULT_DATE_FMT));
    }
}
