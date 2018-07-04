package com.cdkj.wzcd.module.tool.jcdy;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.cdkj.wzcd.databinding.ActivityJcdyBinding;
import com.cdkj.wzcd.model.RepaymentModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.DatePickerHelper;
import com.cdkj.wzcd.util.RequestUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.template_id;

/**
 * Created by cdkj on 2018/6/18.
 */

public class JcdyActivity extends AbsBaseLoadActivity {

    private ActivityJcdyBinding mBinding;

    private String code;

    public static void open(Context context,String code) {
        if (context != null) {
            Intent intent = new Intent(context, JcdyActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }

    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_jcdy, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("回录");

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getNode();

        initListener();
        initCustomView();
    }

    private void initCustomView() {
        mBinding.mySlTemplateId.setData(DataDictionaryHelper.getListByParentKey(template_id),null);
    }

    private void initListener() {
        mBinding.myNlDateTime.setOnClickListener(view -> {
            new DatePickerHelper().build(this).getDate(mBinding.myNlDateTime, true, true,  true, false, false, false);
        });

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                inputRequest();
            }
        });
    }

    public void getNode(){
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
            }

            @Override
            protected void onFinish() {
                disMissLoading();

            }
        });
    }

    private void setView(RepaymentModel data) {
        mBinding.myNlName.setText(data.getBudgetOrder().getApplyUserName());
        mBinding.myNlCode.setText(data.getCode());
        mBinding.myNlBank.setText(data.getLoanBankName());
        mBinding.myNlLoanAmount.setText(RequestUtil.formatAmountDivSign(data.getLoanAmount()));
    }

    private boolean check() {

        if (TextUtils.isEmpty(mBinding.myNlDateTime.check())){
            return false;
        }

        if (mBinding.mySlTemplateId.check()){
            return false;
        }

        return true;
    }

    private void inputRequest(){
        Map<String, Object> map = new HashMap<>();

        map.put("code", code);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("releaseDatetime", mBinding.myNlDateTime.getText());
        map.put("pledgeCommitNote", mBinding.myElRemark.getText());
        map.put("releaseTemplateId", mBinding.mySlTemplateId.getDataKey());

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("630576", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(JcdyActivity.this, "录入成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(JcdyActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
