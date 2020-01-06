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
import com.cdkj.wzcd.model.CreditUserModel;

import org.greenrobot.eventbus.EventBus;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/17.
 */

public class BankCreditResultActivity extends AbsBaseLoadActivity {

    private ActivityBankCreditResultBinding mBinding;

    private CreditUserModel mResult;
    private boolean isCanEdit;

//    public static void open(Context context, CreditResult result, boolean isCanEdit) {
//        if (context == null) {
//            return;
//        }
//        Intent intent = new Intent(context, BankCreditResultActivity.class);
//        intent.putExtra(DATA_SIGN, result);
//        intent.putExtra("isCanEdit", isCanEdit);
//        context.startActivity(intent);
//    }

    /**
     * 新加的
     * @param context
     * @param result
     * @param isCanEdit
     */
    public static void open(Context context, CreditUserModel result, boolean isCanEdit) {
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
        mResult = (CreditUserModel) getIntent().getSerializableExtra(DATA_SIGN);


        setView();
    }

    private void setView() {



        if (isCanEdit) {
            if (mResult == null) {
                //没有数据  不进行数据设置了
//                mBinding.myCbConfirm.setVisibility(View.GONE);
                return;
            }
            mBinding.myElDkdyCount.setText(mResult.getDkdyCount()+"");
            mBinding.myElDkdyAmount.setText(mResult.getDkdyAmount());
            mBinding.myElDkdy2yearOverTimes.setText(mResult.getDkdy2YearOverTimes());
            mBinding.myElDkdyMaxOverAmount.setText(mResult.getDkdyMaxOverAmount());
            mBinding.myElDkdyCurrentOverAmount.setText(mResult.getDkdyCurrentOverAmount());
            mBinding.myElDkdy6monthAvgAmount.setText(mResult.getDkdy6MonthAvgAmount());


            mBinding.myElHkxyUnsettleCount.setText(mResult.getHkxyUnsettleCount()+"");
            mBinding.myElHkxyUnsettleAmount.setText(mResult.getHkxyUnsettleAmount());
            mBinding.myElHkxy2yearOverTimes.setText(mResult.getHkxy2YearOverTimes());
            mBinding.myElHkxyMonthMaxOverAmount.setText(mResult.getHkxyMonthMaxOverAmount());
            mBinding.myElHkxyCurrentOverAmount.setText(mResult.getHkxyCurrentOverAmount());
            mBinding.myElHkxy6monthAvgAmount.setText(mResult.getHkxy6MonthAvgAmount());

            mBinding.myElXykCount.setText(mResult.getXykCount()+"");
            mBinding.myElXykCreditAmount.setText(mResult.getXykCreditAmount());
            mBinding.myElXyk6monthUseAmount.setText(mResult.getXyk6MonthUseAmount());
            mBinding.myElXyk2yearOverTimes.setText(mResult.getXyk2YearOverTimes());
            mBinding.myElXykMonthMaxOverAmount.setText(mResult.getXykMonthMaxOverAmount());
            mBinding.myElXykCurrentOverAmount.setText(mResult.getXykCurrentOverAmount());

            mBinding.myElOutGuaranteesCount.setText(mResult.getOutGuaranteesCount()+"");
            mBinding.myElOutGuaranteesAmount.setText(mResult.getOutGuaranteesAmount());
            mBinding.myElOutGuaranteesRemark.setText(mResult.getOutGuaranteesRemark());
        } else {
            mBinding.myElDkdyCount.setTextByRequest(mResult.getDkdyCount()+"");
            mBinding.myElDkdyAmount.setTextByRequest(mResult.getDkdyAmount());
            mBinding.myElDkdy2yearOverTimes.setTextByRequest(mResult.getDkdy2YearOverTimes());
            mBinding.myElDkdyMaxOverAmount.setTextByRequest(mResult.getDkdyMaxOverAmount());
            mBinding.myElDkdyCurrentOverAmount.setTextByRequest(mResult.getDkdyCurrentOverAmount());
            mBinding.myElDkdy6monthAvgAmount.setTextByRequest(mResult.getDkdy6MonthAvgAmount());


            mBinding.myElHkxyUnsettleCount.setTextByRequest(mResult.getHkxyUnsettleCount()+"");
            mBinding.myElHkxyUnsettleAmount.setTextByRequest(mResult.getHkxyUnsettleAmount());
            mBinding.myElHkxy2yearOverTimes.setTextByRequest(mResult.getHkxy2YearOverTimes());
            mBinding.myElHkxyMonthMaxOverAmount.setTextByRequest(mResult.getHkxyMonthMaxOverAmount());
            mBinding.myElHkxyCurrentOverAmount.setTextByRequest(mResult.getHkxyCurrentOverAmount());
            mBinding.myElHkxy6monthAvgAmount.setTextByRequest(mResult.getHkxy6MonthAvgAmount());

            mBinding.myElXykCount.setTextByRequest(mResult.getXykCount()+"");
            mBinding.myElXykCreditAmount.setTextByRequest(mResult.getXykCreditAmount());
            mBinding.myElXyk6monthUseAmount.setTextByRequest(mResult.getXyk6MonthUseAmount());
            mBinding.myElXyk2yearOverTimes.setTextByRequest(mResult.getXyk2YearOverTimes());
            mBinding.myElXykMonthMaxOverAmount.setTextByRequest(mResult.getXykMonthMaxOverAmount());
            mBinding.myElXykCurrentOverAmount.setTextByRequest(mResult.getXykCurrentOverAmount());

            mBinding.myElOutGuaranteesCount.setTextByRequest(mResult.getOutGuaranteesCount()+"");
            mBinding.myElOutGuaranteesAmount.setTextByRequest(mResult.getOutGuaranteesAmount());
            mBinding.myElOutGuaranteesRemark.setTextByRequest(mResult.getOutGuaranteesRemark());

            mBinding.myCbConfirm.setVisibility(View.GONE);
        }


    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()) {

                returnData();

            }
        });
    }

    private boolean check() {

        if (mBinding.myElDkdyCount.check()) {
//        笔数
            return false;
        }

        if (mBinding.myElDkdyAmount.check()) {
//        贷款余额
            return false;
        }

        if (mBinding.myElDkdy2yearOverTimes.check()) {
            return false;
        }

        if (mBinding.myElDkdyMaxOverAmount.check()) {
            return false;
        }

        if (mBinding.myElDkdyCurrentOverAmount.check()) {
            return false;
        }

        if (mBinding.myElDkdy6monthAvgAmount.check()) {
            return false;
        }

        if (mBinding.myElHkxyUnsettleCount.check()) {
            return false;
        }

        if (mBinding.myElHkxyUnsettleAmount.check()) {
            return false;
        }

        if (mBinding.myElHkxy2yearOverTimes.check()) {
            return false;
        }

        if (mBinding.myElHkxyMonthMaxOverAmount.check()) {
            return false;
        }

        if (mBinding.myElHkxyCurrentOverAmount.check()) {
            return false;
        }

        if (mBinding.myElHkxy6monthAvgAmount.check()) {
            return false;
        }

        if (mBinding.myElXykCount.check()) {
            return false;
        }

        if (mBinding.myElXykCreditAmount.check()) {
            return false;
        }

        if (mBinding.myElXyk6monthUseAmount.check()) {
            return false;
        }

        if (mBinding.myElXyk2yearOverTimes.check()) {
            return false;
        }

        if (mBinding.myElXykMonthMaxOverAmount.check()) {
            return false;
        }

        if (mBinding.myElXykCurrentOverAmount.check()) {
            return false;
        }

        if (mBinding.myElOutGuaranteesCount.check()) {
            return false;
        }

        if (mBinding.myElOutGuaranteesAmount.check()) {
            return false;
        }

        if (mBinding.myElOutGuaranteesRemark.check()) {
            return false;
        }

        return true;
    }

    private void returnData() {

        CreditResult result = new CreditResult();

//        result.setDkdyCount(mBinding.myElDkdyCount.getText());
//        result.setDkdyAmount(mBinding.myElDkdyAmount.getText());
        result.setDkdy2YearOverTimes(mBinding.myElDkdy2yearOverTimes.getText());
        result.setDkdyMaxOverAmount(mBinding.myElDkdyMaxOverAmount.getText());
        result.setDkdyCurrentOverAmount(mBinding.myElDkdyCurrentOverAmount.getText());
        result.setDkdy6MonthAvgAmount(mBinding.myElDkdy6monthAvgAmount.getText());

        result.setHkxyUnsettleCount(mBinding.myElHkxyUnsettleCount.getText());
        result.setHkxyUnsettleAmount(mBinding.myElHkxyUnsettleAmount.getText());
        result.setHkxy2YearOverTimes(mBinding.myElHkxy2yearOverTimes.getText());
        result.setHkxyMonthMaxOverAmount(mBinding.myElHkxyMonthMaxOverAmount.getText());
        result.setHkxyCurrentOverAmount(mBinding.myElHkxyCurrentOverAmount.getText());
        result.setHkxy6MonthAvgAmount(mBinding.myElHkxy6monthAvgAmount.getText());

        result.setXykCount(mBinding.myElXykCount.getText());
        result.setXykCreditAmount(mBinding.myElXykCreditAmount.getText());
        result.setXyk6MonthUseAmount(mBinding.myElXyk6monthUseAmount.getText());
        result.setXyk2YearOverTimes(mBinding.myElXyk2yearOverTimes.getText());
        result.setXykMonthMaxOverAmount(mBinding.myElXykMonthMaxOverAmount.getText());
        result.setXykCurrentOverAmount(mBinding.myElXykCurrentOverAmount.getText());

        result.setOutGuaranteesCount(mBinding.myElOutGuaranteesCount.getText());
        result.setOutGuaranteesAmount(mBinding.myElOutGuaranteesAmount.getText());
        result.setOutGuaranteesRemark(mBinding.myElOutGuaranteesRemark.getText());

        // 发送数据
        EventBus.getDefault().post(result);
        finish();

    }
}
