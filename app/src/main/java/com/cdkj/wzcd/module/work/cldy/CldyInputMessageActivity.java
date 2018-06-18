package com.cdkj.wzcd.module.work.cldy;

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
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityCldyInputMessageBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.util.DatePickerHelper;
import com.cdkj.wzcd.util.RequestUtil;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;


/**
 * 车辆抵押跳转的界面
 */
public class CldyInputMessageActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivityCldyInputMessageBinding mBinding;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, CldyInputMessageActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_cldy_input_message, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("录入");


        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        getNode();

        initListener();
        initCustomView();
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
        mBinding.myNlBank.setText(data.getLoanBankName());
        mBinding.myNlLoanAmount.setText(RequestUtil.formatAmountDivSign(data.getLoanAmount()));
    }

    private void initCustomView() {
        mBinding.myNlDateTime.setOnClickListener(view -> {
            new DatePickerHelper().build(this).getDate(mBinding.myNlDateTime, true, true,  true, false, false, false);
        });

        mBinding.myIlLdb.setActivity(this,1,0);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        String path = data.getStringExtra(CameraHelper.staticPath);
        showLoadingDialog();
        new QiNiuHelper(this).uploadSinglePic(new QiNiuHelper.QiNiuCallBack() {
            @Override
            public void onSuccess(String key) {

                if (requestCode == mBinding.myIlLdb.getRequestCode()){
                    mBinding.myIlLdb.setFlImg(key);
                }

                disMissLoading();
            }

            @Override
            public void onFal(String info) {
                disMissLoading();
            }
        }, path);
    }

    private boolean check(){
        // 抵押日期
        if (TextUtils.isEmpty(mBinding.myNlDateTime.check())){
            return false;
        }

        // 绿大本扫描件
        if (TextUtils.isEmpty(mBinding.myIlLdb.getFlImgUrl())){
            LogUtil.E("绿大本扫描件");
            return false;
        }

        return true;
    }

    private void inputRequest(){
        Map<String, Object> map = new HashMap<>();

        map.put("code", code);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("pledgeDatetime", mBinding.myNlDateTime.getText());
        map.put("greenBigSmj", mBinding.myIlLdb.getFlImgUrl());

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632131", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(CldyInputMessageActivity.this, "发起成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(CldyInputMessageActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
