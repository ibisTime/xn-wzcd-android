package com.cdkj.wzcd.module.work.credit.audit;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivityBankCreditResultBinding;
import com.cdkj.wzcd.model.CreditResult;

import org.greenrobot.eventbus.EventBus;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/17.
 */

public class BankCreditResultActivity extends AbsBaseLoadActivity {

    private ActivityBankCreditResultBinding mBinding;

    private CreditResult mResult;
    private boolean isCanEdit;

    public static void open(Context context, CreditResult result, boolean isCanEdit) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, BankCreditResultActivity.class);
        intent.putExtra(DATA_SIGN, result);
        intent.putExtra("isCanEdit", isCanEdit);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_bank_credit_result, null, false);

        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("银行征信结果");

        initListener();

        if (getIntent() == null || getIntent().getExtras() == null)
            return;

        isCanEdit = getIntent().getBooleanExtra("isCanEdit", false);
        mResult = (CreditResult) getIntent().getSerializableExtra(DATA_SIGN);

        setView();
    }

    private void setView() {

        if (mResult == null)
            return;

        if (isCanEdit){
            mBinding.myElDkdyCount.setText(mResult.getDkdyCount());
            mBinding.myElDkdyAmount.setMoneyText(mResult.getDkdyAmount());
            mBinding.myElDkdy2yearOverTimes.setText(mResult.getDkdy2YearOverTimes());
            mBinding.myElDkdyMaxOverAmount.setMoneyText(mResult.getDkdyMaxOverAmount());
            mBinding.myElDkdyCurrentOverAmount.setMoneyText(mResult.getDkdyCurrentOverAmount());
            mBinding.myElDkdy6monthAvgAmount.setMoneyText(mResult.getDkdy6MonthAvgAmount());


            mBinding.myElHkxyUnsettleCount.setText(mResult.getHkxyUnsettleCount());
            mBinding.myElHkxyUnsettleAmount.setMoneyText(mResult.getHkxyUnsettleAmount());
            mBinding.myElHkxy2yearOverTimes.setText(mResult.getHkxy2YearOverTimes());
            mBinding.myElHkxyMonthMaxOverAmount.setMoneyText(mResult.getHkxyMonthMaxOverAmount());
            mBinding.myElHkxyCurrentOverAmount.setMoneyText(mResult.getHkxyCurrentOverAmount());
            mBinding.myElHkxy6monthAvgAmount.setMoneyText(mResult.getHkxy6MonthAvgAmount());

            mBinding.myElXykCount.setText(mResult.getXykCount());
            mBinding.myElXykCreditAmount.setMoneyText(mResult.getXykCreditAmount());
            mBinding.myElXyk6monthUseAmount.setMoneyText(mResult.getXyk6MonthUseAmount());
            mBinding.myElXyk2yearOverTimes.setText(mResult.getXyk2YearOverTimes());
            mBinding.myElXykMonthMaxOverAmount.setMoneyText(mResult.getXykMonthMaxOverAmount());
            mBinding.myElXykCurrentOverAmount.setMoneyText(mResult.getXykCurrentOverAmount());

            mBinding.myElOutGuaranteesCount.setText(mResult.getOutGuaranteesCount());
            mBinding.myElOutGuaranteesAmount.setMoneyText(mResult.getOutGuaranteesAmount());
            mBinding.myElOutGuaranteesRemark.setText(mResult.getOutGuaranteesRemark());
        }else {
            mBinding.myElDkdyCount.setTextByRequest(mResult.getDkdyCount());
            mBinding.myElDkdyAmount.setMoneyTextByRequest(mResult.getDkdyAmount());
            mBinding.myElDkdy2yearOverTimes.setTextByRequest(mResult.getDkdy2YearOverTimes());
            mBinding.myElDkdyMaxOverAmount.setMoneyTextByRequest(mResult.getDkdyMaxOverAmount());
            mBinding.myElDkdyCurrentOverAmount.setMoneyTextByRequest(mResult.getDkdyCurrentOverAmount());
            mBinding.myElDkdy6monthAvgAmount.setMoneyTextByRequest(mResult.getDkdy6MonthAvgAmount());


            mBinding.myElHkxyUnsettleCount.setTextByRequest(mResult.getHkxyUnsettleCount());
            mBinding.myElHkxyUnsettleAmount.setMoneyTextByRequest(mResult.getHkxyUnsettleAmount());
            mBinding.myElHkxy2yearOverTimes.setTextByRequest(mResult.getHkxy2YearOverTimes());
            mBinding.myElHkxyMonthMaxOverAmount.setMoneyTextByRequest(mResult.getHkxyMonthMaxOverAmount());
            mBinding.myElHkxyCurrentOverAmount.setMoneyTextByRequest(mResult.getHkxyCurrentOverAmount());
            mBinding.myElHkxy6monthAvgAmount.setMoneyTextByRequest(mResult.getHkxy6MonthAvgAmount());

            mBinding.myElXykCount.setTextByRequest(mResult.getXykCount());
            mBinding.myElXykCreditAmount.setMoneyTextByRequest(mResult.getXykCreditAmount());
            mBinding.myElXyk6monthUseAmount.setMoneyTextByRequest(mResult.getXyk6MonthUseAmount());
            mBinding.myElXyk2yearOverTimes.setTextByRequest(mResult.getXyk2YearOverTimes());
            mBinding.myElXykMonthMaxOverAmount.setMoneyTextByRequest(mResult.getXykMonthMaxOverAmount());
            mBinding.myElXykCurrentOverAmount.setMoneyTextByRequest(mResult.getXykCurrentOverAmount());

            mBinding.myElOutGuaranteesCount.setTextByRequest(mResult.getOutGuaranteesCount());
            mBinding.myElOutGuaranteesAmount.setMoneyTextByRequest(mResult.getOutGuaranteesAmount());
            mBinding.myElOutGuaranteesRemark.setTextByRequest(mResult.getOutGuaranteesRemark());

            mBinding.myCbConfirm.setVisibility(View.GONE);
        }



    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){

                returnData();

            }
        });
    }

    private boolean check() {

        if (mBinding.myElDkdyCount.check()){
            return false;
        }

        if (mBinding.myElDkdyAmount.check()){
            return false;
        }

        if (mBinding.myElDkdy2yearOverTimes.check()){
            return false;
        }

        if (mBinding.myElDkdyMaxOverAmount.check()){
            return false;
        }

        if (mBinding.myElDkdyCurrentOverAmount.check()){
            return false;
        }

        if (mBinding.myElDkdy6monthAvgAmount.check()){
            return false;
        }

        if (mBinding.myElHkxyUnsettleCount.check()){
            return false;
        }

        if (mBinding.myElHkxyUnsettleAmount.check()){
            return false;
        }

        if (mBinding.myElHkxy2yearOverTimes.check()){
            return false;
        }

        if (mBinding.myElHkxyMonthMaxOverAmount.check()){
            return false;
        }

        if (mBinding.myElHkxyCurrentOverAmount.check()){
            return false;
        }

        if (mBinding.myElHkxy6monthAvgAmount.check()){
            return false;
        }

        if (mBinding.myElXykCount.check()){
            return false;
        }

        if (mBinding.myElXykCreditAmount.check()){
            return false;
        }

        if (mBinding.myElXyk6monthUseAmount.check()){
            return false;
        }

        if (mBinding.myElXyk2yearOverTimes.check()){
            return false;
        }

        if (mBinding.myElXykMonthMaxOverAmount.check()){
            return false;
        }

        if (mBinding.myElXykCurrentOverAmount.check()){
            return false;
        }

        if (mBinding.myElOutGuaranteesCount.check()){
            return false;
        }

        if (mBinding.myElOutGuaranteesAmount.check()){
            return false;
        }

        if (mBinding.myElOutGuaranteesRemark.check()){
            return false;
        }

        return true;
    }

    private void returnData(){

        CreditResult result = new CreditResult();

        result.setDkdyCount(mBinding.myElDkdyCount.getText());
        result.setDkdyAmount(mBinding.myElDkdyAmount.getMoneyText());
        result.setDkdy2YearOverTimes(mBinding.myElDkdy2yearOverTimes.getText());
        result.setDkdyMaxOverAmount(mBinding.myElDkdyMaxOverAmount.getMoneyText());
        result.setDkdyCurrentOverAmount(mBinding.myElDkdyCurrentOverAmount.getMoneyText());
        result.setDkdy6MonthAvgAmount(mBinding.myElDkdy6monthAvgAmount.getMoneyText());

        result.setHkxyUnsettleCount(mBinding.myElHkxyUnsettleCount.getText());
        result.setHkxyUnsettleAmount(mBinding.myElHkxyUnsettleAmount.getMoneyText());
        result.setHkxy2YearOverTimes(mBinding.myElHkxy2yearOverTimes.getText());
        result.setHkxyMonthMaxOverAmount(mBinding.myElHkxyMonthMaxOverAmount.getMoneyText());
        result.setHkxyCurrentOverAmount(mBinding.myElHkxyCurrentOverAmount.getMoneyText());
        result.setHkxy6MonthAvgAmount(mBinding.myElHkxy6monthAvgAmount.getMoneyText());

        result.setXykCount(mBinding.myElXykCount.getText());
        result.setXykCreditAmount(mBinding.myElXykCreditAmount.getMoneyText());
        result.setXyk6MonthUseAmount(mBinding.myElXyk6monthUseAmount.getMoneyText());
        result.setXyk2YearOverTimes(mBinding.myElXyk2yearOverTimes.getText());
        result.setXykMonthMaxOverAmount(mBinding.myElXykMonthMaxOverAmount.getMoneyText());
        result.setXykCurrentOverAmount(mBinding.myElXykCurrentOverAmount.getMoneyText());

        result.setOutGuaranteesCount(mBinding.myElOutGuaranteesCount.getText());
        result.setOutGuaranteesAmount(mBinding.myElOutGuaranteesAmount.getMoneyText());
        result.setOutGuaranteesRemark(mBinding.myElOutGuaranteesRemark.getText());

        // 发送数据
        EventBus.getDefault().post(result);
        finish();

    }
}
