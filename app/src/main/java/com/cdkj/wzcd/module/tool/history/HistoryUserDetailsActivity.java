package com.cdkj.wzcd.module.tool.history;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adpter.adapter.RepayPlanAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityHistoryUserDetailsBinding;
import com.cdkj.wzcd.model.RepaymentModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.RequestUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

public class HistoryUserDetailsActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivityHistoryUserDetailsBinding mBinding;

    private RepayPlanAdapter mAdapter;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, HistoryUserDetailsActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_history_user_details, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("详情");

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);

        getRepaymentRequest();
    }


    public void getRepaymentRequest() {

        Map<String, String> map = new HashMap<>();

        map.put("code", code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getRepayment("630521", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<RepaymentModel>(this) {

            @Override
            protected void onSuccess(RepaymentModel data, String SucMessage) {
                if (data == null)
                    return;

                setView(data);
                initPlanAdapter(data);
            }

            @Override
            protected void onFinish() {
                disMissLoading();

            }
        });

    }

    private void setView(RepaymentModel data) {

        DataDictionaryHelper.getValueOnTheKeyRequest(this, DataDictionaryHelper.status, data.getStatus(), data1 -> {

            mBinding.myNlStatus.setText(data1.getDvalue());

        });

        mBinding.myNlName.setText(data.getBudgetOrder().getApplyUserName());
        mBinding.myNlCompanyName.setText(data.getBudgetOrder().getCompanyName());
        mBinding.myNlCode.setText(data.getCode());
        mBinding.myNlPeriods.setText(data.getPeriods()+"");
        mBinding.myNlAmount.setText(RequestUtil.formatAmountDivSign(data.getLoanAmount()));
        mBinding.myNlRest.setText(RequestUtil.formatAmountDivSign(data.getRestAmount()));
        mBinding.myNlBank.setText(data.getLoanBankName());

    }

    private void initPlanAdapter(RepaymentModel data){
        mAdapter = new RepayPlanAdapter(data.getRepayPlanList());
        mBinding.rvPlan.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvPlan.setAdapter(mAdapter);
    }

}
