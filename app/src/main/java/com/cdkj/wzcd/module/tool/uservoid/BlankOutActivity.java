package com.cdkj.wzcd.module.tool.uservoid;

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
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivityBlankOutBinding;
import com.cdkj.wzcd.model.ZrdModel;
import com.cdkj.wzcd.util.RequestUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

/**
 * 申请作废界面
 */
public class BlankOutActivity extends AbsBaseLoadActivity {

    private ActivityBlankOutBinding mBinding;
    private ZrdModel mZrdModel;

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, BlankOutActivity.class);
            context.startActivity(intent);
        }

    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_blank_out, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("申请");
//        mBaseBinding.titleView.setRightTitle("选择准入单");
//        mBaseBinding.titleView.setRightFraClickListener(view -> {
//
//        });

        initListener();
    }

    private void initListener() {
        mBinding.myNlCode.setOnClickListener(view -> {
            ZrdListActivity.open(this);
        });

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                showDoubleWarnListen("您确定要申请作废此准入单吗?",view1 -> {
                    cancel();
                });
            }
        });
    }

    @Subscribe
    public void getZrdModel(ZrdModel zrdModel){

        if (zrdModel == null)
            return;

        mZrdModel = zrdModel;
        setView();
    }

    private void setView() {
        mBinding.myNlName.setText(mZrdModel.getApplyUserName());
        mBinding.myNlCode.setText(mZrdModel.getCode());
        mBinding.myNlBank.setText(mZrdModel.getLoanBankName());
        mBinding.myNlLoanAmount.setText(RequestUtil.formatAmountDivSign(mZrdModel.getLoanAmount()));

    }

    private boolean check(){

        if (mZrdModel == null){
            ToastUtil.show(this, "请选择需作废的预约单");
            return false;
        }

        if (mBinding.myElReason.check()){
            return false;
        }

        return true;
    }

    public void cancel(){
        Map<String, Object> map = new HashMap<>();

        map.put("code", mZrdModel.getCode());
        map.put("remark", mBinding.myElReason.getText());
        map.put("operator", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632190", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(BlankOutActivity.this, "申请成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(BlankOutActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
