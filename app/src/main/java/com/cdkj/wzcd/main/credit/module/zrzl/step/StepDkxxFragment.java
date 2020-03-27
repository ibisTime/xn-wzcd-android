package com.cdkj.wzcd.main.credit.module.zrzl.step;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepDkxxBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlMonthAmountBean;
import com.cdkj.wzcd.model.SystemParameterModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.SET_UPLOAD_RESULT;

/**
 * 贷款信息
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class StepDkxxFragment extends BaseLazyFragment {

    private FrgStepDkxxBinding mBinding;

    private boolean isDetail;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static StepDkxxFragment getInstance(boolean isDetail) {
        StepDkxxFragment fragment = new StepDkxxFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(CdRouteHelper.DATA_SIGN, isDetail);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_step_dkxx, null, false);

        init();
        initListener();

        getRebateRate();

        return mBinding.getRoot();
    }

    private void init() {
        isDetail = getArguments().getBoolean(CdRouteHelper.DATA_SIGN);
    }

    private void initListener() {
        mBinding.btnConfirm.setOnClickListener(view -> {
            if (BaseViewUtil.check(mBinding.llInputDkxx)) {

                if (BaseViewUtil.check(mBinding.llInputFyxx)) {

                    doRequest();

                }

            }
        });
    }

    public void getRebateRate() {
        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("key", "rebate_rate");

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getSystemParameter("630047", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<SystemParameterModel>(mActivity) {
            @Override
            protected void onSuccess(SystemParameterModel model, String SucMessage) {
                mBinding.elRebateRate.setText(model.getCvalue());

                initView();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void initView() {
        // 贷款信息
        // 设置控件不可输入
        mBinding.elBankRate.setFocusable(false);
        mBinding.elMonthAmount.setFocusable(false);
        mBinding.elRepayFirstMonthAmount.setFocusable(false);
        mBinding.elOpenCardAmount.setFocusable(false);
        mBinding.elLoanRatio.setFocusable(false);
        mBinding.elFee.setFocusable(false);
        mBinding.elTotalFee.setFocusable(false);

        mBinding.slPeriods.setData("loan_period", (dialog, which) -> {

            ZrzlBean data = ((ZrzlActivity) mActivity).data;

            if (which == 0) {
                mBinding.elBankRate.setText(data.getBank().getRate12());
            } else if (which == 1) {
                mBinding.elBankRate.setText(data.getBank().getRate18());
            } else if (which == 2) {
                mBinding.elBankRate.setText(data.getBank().getRate24());
            } else if (which == 3) {
                mBinding.elBankRate.setText(data.getBank().getRate36());
            }
        });
        mBinding.slIsAdvanceFund.setDataIs(null);
        mBinding.slRateType.setData("1", "传统", "2", "直客");
        mBinding.slIsDiscount.setDataIs(null);

        mBinding.elBankRate.setTextWatcher(new Watcher());
        mBinding.elLoanAmount.setTextWatcher(new Watcher());
        mBinding.elRebateRate.setTextWatcher(new Watcher());
        mBinding.elTotalRate.setTextWatcher(new Watcher());
        mBinding.elInvoicePrice.setTextWatcher(new Watcher());

        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInputDkxx);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }

        // 费用信息
        // 设置控件不可输入
        mBinding.elLoanAmountFy.setFocusable(false);
        mBinding.elRepointAmount.setFocusable(false);
        mBinding.elCarFunds3.setFocusable(false);

        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInputFyxx);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }

        setView();
    }

    public class Watcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            // 月供
            checkMonthAmount();
            // 车款1
            checkLoanAmount();
            // 车款2
            checkRepointAmount();
            // 车款3
            checkCarFunds3();
        }
    }

    private ZrzlBean getData() {
        if (isDetail) {
            if (TextUtils.isEmpty(((CreditActivity) mActivity).code)
                    || null == ((CreditActivity) mActivity).data) {
                return null;
            }
            return ((CreditActivity) mActivity).data;
        } else {
            if (TextUtils.isEmpty(((ZrzlActivity) mActivity).code)
                    || null == ((ZrzlActivity) mActivity).data) {
                return null;
            }
            return ((ZrzlActivity) mActivity).data;
        }

    }

    private void setView() {

        ZrzlBean data = getData();
        if (null == data) {
            return;
        }

        if (null == data.getBankLoan()) {
            return;
        }

        // 贷款信息
        mBinding.elLoanAmount.setText(data.getBankLoan().getLoanAmount());
        mBinding.slPeriods.setContentByKey(data.getBankLoan().getPeriods() + "");
        mBinding.elBankRate.setText(data.getBankLoan().getBankRate());
        mBinding.elTotalRate.setText(data.getBankLoan().getTotalRate());
        mBinding.elRebateRate.setText(data.getBankLoan().getRebateRate());
        mBinding.slIsAdvanceFund.setContentByKey(data.getBankLoan().getIsAdvanceFund());
        mBinding.elMonthAmount.setText(data.getBankLoan().getMonthAmount());
        mBinding.elRepayFirstMonthAmount
                .setText(data.getBankLoan().getRepayFirstMonthAmount());
        mBinding.elOpenCardAmount.setText(data.getBankLoan().getOpenCardAmount());
        mBinding.elDiscountRate.setText(data.getBankLoan().getDiscountRate());
        mBinding.elDiscountAmount.setText(data.getBankLoan().getDiscountAmount());
        mBinding.elInvoicePrice.setText(data.getCarInfo().getInvoicePrice());
        mBinding.dlWanFactor.setText(data.getBankLoan().getWanFactor());
        mBinding.elLoanRatio.setText(data.getBankLoan().getLoanRatio());
        mBinding.slRateType.setContentByKey(data.getBankLoan().getRateType());
        mBinding.elFee.setText(data.getBankLoan().getFee());
        mBinding.slIsDiscount.setContentByKey(data.getBankLoan().getIsDiscount());
        mBinding.slHighCashAmount.setText(data.getBankLoan().getHighCashAmount());
        mBinding.elTotalFee.setText(data.getBankLoan().getTotalFee());
        mBinding.elCustomerBearRate.setText(data.getBankLoan().getCustomerBearRate());
        mBinding.elSurchargeRate.setText(data.getBankLoan().getSurchargeRate());
        mBinding.elSurchargeAmount.setText(data.getBankLoan().getSurchargeAmount());
        mBinding.rlNotes.setText(data.getBankLoan().getNotes());


        // 费用信息
        mBinding.elGpsFee.setText(data.getGpsFee());
        mBinding.elFxAmount.setText(data.getFxAmount());
        mBinding.elLyDeposit.setText(data.getLyDeposit());
        mBinding.elOtherFee.setText(data.getOtherFee());
        mBinding.elLoanAmountFy.setText(data.getLoanAmount());
        mBinding.elRepointAmount.setText(data.getRepointAmount());
        mBinding.elCarFunds3.setText(data.getCarFunds3());
        mBinding.elCarFunds4.setText(data.getCarFunds4());
        mBinding.elCarFunds5.setText(data.getCarFunds5());

        if (null != data.getBankLoan()) {
            setRepointAmount(data.getBankLoan().getLoanAmount(), data.getBankLoan().getRebateRate(), data.getBankLoan().getTotalRate());

            setCarFunds3(data.getBankLoan().getLoanAmount(),
                    data.getBankLoan().getRebateRate(), data.getBankLoan().getBankRate());
        }
    }

    private void checkMonthAmount() {

        if (mBinding.slPeriods.checkNoTip()) {
            return;
        }

        if (mBinding.elLoanAmount.checkNoTip()) {
            return;
        }

        if (mBinding.elBankRate.checkNoTip()) {
            return;
        }

        if (mBinding.elTotalRate.checkNoTip()) {
            return;
        }

        getMonthAmount();
    }

    private void getMonthAmount() {

        Map<String, Object> map = RetrofitUtils.getRequestMap();
        if (isDetail) {
            map.put("loanBankCode", ((CreditActivity) mActivity).data.getBank().getCode());
        } else {
            map.put("loanBankCode", ((ZrzlActivity) mActivity).data.getBank().getCode());
        }
        map.put("loanPeriods", mBinding.slPeriods.getDataKey());
        map.put("loanAmount", mBinding.elLoanAmount.getText());
        map.put("bankRate", mBinding.elBankRate.getText());
        map.put("totalRate", mBinding.elTotalRate.getText());
        if (!TextUtils.isEmpty(mBinding.elInvoicePrice.getText())) {
            map.put("invoicePrice", mBinding.elInvoicePrice.getText());
        }

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getZrzlMonthAmount("632541", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ZrzlMonthAmountBean>(mActivity) {
            @Override
            protected void onSuccess(ZrzlMonthAmountBean data, String SucMessage) {

                mBinding.elRepayFirstMonthAmount.setText(data.getInitialAmount());
                mBinding.elMonthAmount.setText(data.getAnnualAmount());
                mBinding.elLoanRatio.setText(data.getLoanRatio());
                mBinding.elOpenCardAmount.setText(data.getOpenCardAmount());
                mBinding.elTotalFee.setText(data.getOpenCardAmount());
                mBinding.elFee.setText(data.getPoundage());

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    private void checkLoanAmount() {

        if (mBinding.elLoanAmount.checkNoTip()) {
            return;
        }

        mBinding.elLoanAmountFy.setText(mBinding.elLoanAmount.getText());

    }

    private void checkRepointAmount() {

        if (mBinding.elLoanAmount.checkNoTip()) {
            return;
        }

        if (mBinding.elRebateRate.checkNoTip()) {
            return;
        }

        setRepointAmount(mBinding.elLoanAmount.getText(), mBinding.elRebateRate.getText(), mBinding.elTotalRate.getText());
    }

    private void checkCarFunds3() {

        if (mBinding.elLoanAmount.checkNoTip()) {
            return;
        }

        if (mBinding.elRebateRate.checkNoTip()) {
            return;
        }

        if (mBinding.elBankRate.checkNoTip()) {
            return;
        }

        setCarFunds3(mBinding.elLoanAmount.getText(), mBinding.elRebateRate.getText(), mBinding.elBankRate.getText());
    }

    private void doRequest() {

        Map<String, String> map = BaseViewUtil.buildMap(mBinding.llInputDkxx);
        map.put("code", ((ZrzlActivity) mActivity).code);
        map.put("operator", SPUtilHelper.getUserId());
        map.putAll(mBinding.rlNotes.getMap());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632532", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {


                doFYRequest();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }


    @Subscribe
    public void setRate(EventBean eventBean) {

        if (eventBean != null && TextUtils.equals("change_car_type", eventBean.getTag())) {
            boolean newCar = TextUtils.equals("新车", eventBean.getValue1());

            if (newCar){
                mBinding.dlWanFactor.isRequired(false);
            }else {
                mBinding.dlWanFactor.isRequired(true);
            }

        }

    }


    // ------------------ 费用信息 ------------------

    private void setRepointAmount(String loanAmountStr, String rebateRateStr, String tooleRateStr) {

        if (TextUtils.isEmpty(loanAmountStr) || TextUtils.isEmpty(rebateRateStr) || TextUtils.isEmpty(tooleRateStr)) {
            return;
        }

        BigDecimal loanAmount = new BigDecimal(loanAmountStr.trim());
        BigDecimal rebateRate = new BigDecimal(rebateRateStr.trim());
        BigDecimal tooleRate = new BigDecimal(tooleRateStr.trim());
        //车款2的计算公式为  (总利率-返存利率)*贷款本金

        mBinding.elRepointAmount.setText(loanAmount.multiply(tooleRate.subtract(rebateRate)).toPlainString());

    }

    private void setCarFunds3(String loanAmountStr, String rebateRateStr, String bankRateStr) {
        //车款3  （返存利率-银行利率）*贷款本金
        if (TextUtils.isEmpty(loanAmountStr) || TextUtils.isEmpty(rebateRateStr) || TextUtils.isEmpty(bankRateStr)) {
            return;
        }

        BigDecimal loanAmount = new BigDecimal(loanAmountStr.trim());
        BigDecimal rebateRate = new BigDecimal(rebateRateStr.trim());
        BigDecimal bankRate = new BigDecimal(bankRateStr.trim());

        BigDecimal rate = rebateRate.subtract(bankRate);

        mBinding.elCarFunds3.setText(loanAmount.multiply(rate).toPlainString());

    }

    private void doFYRequest() {

        Map<String, String> map = BaseViewUtil.buildMap(mBinding.llInputFyxx);
        map.put("code", ((ZrzlActivity) mActivity).code);
        map.put("operator", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632533", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
//                UITipDialog.showSuccess(mActivity, "保存成功", dialogInterface -> {
//                    EventBus.getDefault()
//                            .post(new EventBean().setTag(SET_UPLOAD_RESULT).setValue1("4"));
//                });

                UITipDialog.showSuccess(mActivity, "保存成功", dialogInterface -> {
                    EventBus.getDefault()
                            .post(new EventBean().setTag(SET_UPLOAD_RESULT).setValue1("3"));
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }
}
