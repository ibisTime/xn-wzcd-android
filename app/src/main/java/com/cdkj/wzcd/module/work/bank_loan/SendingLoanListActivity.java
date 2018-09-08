package com.cdkj.wzcd.module.work.bank_loan;

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
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivitySendingLoanListBinding;
import com.cdkj.wzcd.model.NodeListModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * 发送放款名单
 */
public class SendingLoanListActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivitySendingLoanListBinding mBinding;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, SendingLoanListActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sending_loan_list);
//    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_sending_loan_list, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("发送放款名单");

        init();
        initOnClick();

        initData();

    }


    private void initOnClick() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()) {
                //联网
                send();
            }
        });
    }

    private void init() {
        if (getIntent() != null) {
            code = getIntent().getStringExtra(DATA_SIGN);
        }


        mBinding.myIlLoan.setActivity(this, 1, 0);
    }

    private boolean check() {
        if (mBinding.myElBankRepaymentData.check()) {
            return false;
        }
        if (mBinding.myElCheckData.check()) {
            return false;
        }
        if (TextUtils.isEmpty(mBinding.myIlLoan.check())) {
            return false;
        }
        return true;
    }

    private void setView(NodeListModel data) {
        mBinding.myNlName.setText(data.getCustomerName());
    }

    private void send() {
        Map<String, Object> map = new HashMap<>();
        ArrayList codeList = new ArrayList<String>();
        codeList.add(code);
//        map.put("code", code);
        map.put("codeList", codeList);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("billDatetime", mBinding.myElCheckData.getText());
        map.put("repayBankDate", mBinding.myElBankRepaymentData.getText());
        map.put("hasLoanListPic", mBinding.myIlLoan.getFlImgUrl());

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632144", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(SendingLoanListActivity.this, "确认成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(SendingLoanListActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void initData() {
        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("code", code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getNode("632146", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<NodeListModel>(this) {
            @Override
            protected void onSuccess(NodeListModel data, String SucMessage) {
                if (data == null) {
                    return;
                }
                setView(data);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

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

                if (requestCode == mBinding.myIlLoan.getRequestCode()) {
                    mBinding.myIlLoan.setFlImg(key);
                }
                disMissLoading();
            }

            @Override
            public void onFal(String info) {
                disMissLoading();
            }
        }, path);
    }
}
