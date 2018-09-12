package com.cdkj.wzcd.module.tool.fabrication;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
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
import com.cdkj.wzcd.databinding.ActivityFabricationInputBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.util.RequestUtil;

import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/17.
 */

public class FabricationInputActivity extends AbsBaseLoadActivity {

    private ActivityFabricationInputBinding mBinding;

    private String code;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, FabricationInputActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_fabrication_input, null ,false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("录入卡号");
        initListener();

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getAdvanceFund();
    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check())
                apply();
        });
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
        mBinding.myNlCompanyName.setText(data.getCompanyName());
        mBinding.myNlLoanAmount.setText(RequestUtil.formatAmountDivSign(data.getLoanAmount()));
        mBinding.myNlBank.setText(data.getLoanBankName());

    }

    private boolean check(){
        if (mBinding.myElNumber.check()){
            return false;
        }

        return true;
    }

    public void apply(){
        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("code", code);
        map.put("bankCardNumber", mBinding.myElNumber.getText());
        map.put("makeCardRemark", mBinding.myElRemark.getText());
        map.put("operator", SPUtilHelper.getUserId());

        showLoadingDialog();

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632211", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {
            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {

                if (data == null)
                    return;

                UITipDialog.showSuccess(FabricationInputActivity.this, "申请成功", dialogInterface -> {
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
