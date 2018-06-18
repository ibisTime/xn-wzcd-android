package com.cdkj.wzcd.module.tool.mismatching;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityMismatchingDetailBinding;
import com.cdkj.wzcd.model.CreditModel;
import com.cdkj.wzcd.model.MismatchingRefresh;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.module.work.credit.audit.BankCreditResultActivity;
import com.cdkj.wzcd.util.RequestUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN2;
import static com.cdkj.wzcd.util.DataDictionaryHelper.getValueOnTheKey;

/**
 * 发票不匹配申请
 * Created by cdkj on 2018/6/17.
 */

public class MismatchingDetailActivity extends AbsBaseLoadActivity {

    private ActivityMismatchingDetailBinding mBinding;

    private String code;

    /**
     * @param context
     * @param code
     * @param isDetails 是否是详情状态
     */
    public static void open(Context context, String code, boolean isDetails) {
        if (context != null) {
            Intent intent = new Intent(context, MismatchingDetailActivity.class);
            intent.putExtra(DATA_SIGN, code);
            intent.putExtra(DATA_SIGN2, isDetails);
            context.startActivity(intent);
        }
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_mismatching_detail, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {


        initClickListener();

        if (getIntent() == null)
            return;

        setDetailsState();

        code = getIntent().getStringExtra(DATA_SIGN);
        getAdvanceFund();
    }

    /**
     * 点击事件
     */
    private void initClickListener() {
        mBinding.myNlResult.setOnClickListener(view -> {

        });

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (TextUtils.isEmpty(mBinding.myElLoanAmount.getText())) {
                UITipDialog.showInfo(this, "请输入现贷款金额");
                return;
            }
            submit();
        });
    }

    /**
     * 设置详情显示状态
     */
    private void setDetailsState() {
        if (getIntent().getBooleanExtra(DATA_SIGN2, false)) {  //详细状态不显示
            mBinding.myElLoanAmount.setVisibility(View.GONE);
            mBinding.myNlRebateType.setVisibility(View.GONE);
            mBinding.rvRebate.setVisibility(View.GONE);
            mBinding.myCbConfirm.setVisibility(View.GONE);
            mBaseBinding.titleView.setMidTitle("申请");
        } else {
            mBaseBinding.titleView.setMidTitle("详情");
        }
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
        mBinding.myNlName.setText(data.getCustomerName());
        mBinding.myNlCode.setText(data.getCode());
        mBinding.myNlLoanAmount.setText(RequestUtil.formatAmountDivSign(data.getLoanAmount()));
        mBinding.myNlBank.setText(data.getLoanBankName());
        mBinding.myNlWay.setText(getValueOnTheKey(data.getShopWay()));

        mBinding.myNlBillPriceOriginal.setMoneyText(data.getInvoicePrice());
        mBinding.myNlBillPriceCurrent.setMoneyText(data.getCurrentInvoicePrice());
    }

    public void submit() {

        if (TextUtils.isEmpty(code)) return;

        Map<String, String> map = new HashMap<>();

        map.put("code", code);
        map.put("dealType", "1"); //0保存1发送
        map.put("loanAmount", mBinding.myElLoanAmount.getMoneyText());
        map.put("operator", SPUtilHelper.getUserId());

        Call<BaseResponseModel<IsSuccessModes>> call = RetrofitUtils.getBaseAPiService().successRequest("632230", StringUtils.getJsonToString(map));

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(MismatchingDetailActivity.this, "申请成功", dialogInterface -> {
                        EventBus.getDefault().post(new MismatchingRefresh());
                        finish();
                    });

                } else {
                    UITipDialog.showFail(MismatchingDetailActivity.this, "申请失败");
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }


}
