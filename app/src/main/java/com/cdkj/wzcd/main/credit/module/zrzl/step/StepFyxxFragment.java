package com.cdkj.wzcd.main.credit.module.zrzl.step;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
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
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepFyxxBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.math.BigDecimal;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.SET_UPLOAD_RESULT;

/**
 * 费用信息
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class StepFyxxFragment extends BaseLazyFragment {

    private FrgStepFyxxBinding mBinding;

    private boolean isDetail;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static StepFyxxFragment getInstance(boolean isDetail) {
        StepFyxxFragment fragment = new StepFyxxFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_step_fyxx, null, false);

        init();
        initListener();

        initView();
        setView();

        return mBinding.getRoot();
    }

    private void init() {
        isDetail = getArguments().getBoolean(CdRouteHelper.DATA_SIGN);
    }

    private void initListener() {
        mBinding.btnConfirm.setOnClickListener(view -> {
            doRequest();
        });
    }

    private void initView() {

        // 设置控件不可输入
        mBinding.elLoanAmount.setFocusable(false);
        mBinding.elRepointAmount.setFocusable(false);
        mBinding.elCarFunds3.setFocusable(false);

        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
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

        mBinding.elGpsFee.setText(data.getGpsFee());
        mBinding.elFxAmount.setText(data.getFxAmount());
        mBinding.elLyDeposit.setText(data.getLyDeposit());
        mBinding.elOtherFee.setText(data.getOtherFee());
        mBinding.elLoanAmount.setText(data.getLoanAmount());
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

    private void doRequest() {

        Map<String, String> map = BaseViewUtil.buildMap(mBinding.llInput);
        map.put("code", ((ZrzlActivity) mActivity).code);
        map.put("operator", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632533", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(mActivity, "保存成功", dialogInterface -> {
                    EventBus.getDefault()
                            .post(new EventBean().setTag(SET_UPLOAD_RESULT).setValue1("4"));
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    @Subscribe
    public void setJhView(EventBean bean) {

        if (mBinding == null) {
            return;
        }

        if (bean.getTag().equals("set_loanAmount")) {

            if (mBinding.elLoanAmount == null) {
                return;
            }

            mBinding.elLoanAmount.setText(bean.getValue1());

        }

        if (bean.getTag().equals("set_repointAmount")) {

            if (mBinding.elRepointAmount == null) {
                return;
            }

            setRepointAmount(bean.getValue1(), bean.getValue2(), bean.getValue3());

        }

        if (bean.getTag().equals("set_carFunds3")) {

            if (mBinding.elCarFunds3 == null) {
                return;
            }

            setCarFunds3(bean.getValue1(), bean.getValue2(), bean.getValue3());
        }
    }

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
//        BigDecimal totalRate = new BigDecimal(totalRateStr.trim());
        BigDecimal rebateRate = new BigDecimal(rebateRateStr.trim());
        BigDecimal bankRate = new BigDecimal(bankRateStr.trim());

        BigDecimal rate = rebateRate.subtract(bankRate);

        mBinding.elCarFunds3.setText(loanAmount.multiply(rate).toPlainString());

    }
}

