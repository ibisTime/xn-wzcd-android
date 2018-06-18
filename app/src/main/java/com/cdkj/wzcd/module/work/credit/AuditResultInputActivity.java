package com.cdkj.wzcd.module.work.credit;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivityAuditResultInputBinding;
import com.cdkj.wzcd.model.CreditResult;
import com.cdkj.wzcd.model.CreditUserModel;
import com.cdkj.wzcd.module.work.credit.audit.BankCreditResultActivity;

import org.greenrobot.eventbus.Subscribe;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/8.
 */

public class AuditResultInputActivity extends AbsBaseLoadActivity {

    private ActivityAuditResultInputBinding mBinding;

    private CreditUserModel model;
    // 页面是否可编辑
    private boolean isCanEdit;
    // 当前节点
    private String nodeCode;

    /**
     *
     * @param context 上下文
     * @param model 征信人Model
     * @param isCanEdit 当前页面是否可编辑,true:可编辑,false:不可编辑
     */
    public static void open(Context context, CreditUserModel model, String nodeCode, boolean isCanEdit) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CreditUserActivity.class);
        intent.putExtra(DATA_SIGN, model);
        intent.putExtra("isCanEdit", isCanEdit);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_audit_result_input, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("添加征信人");

        initListener();

        if (getIntent() == null)
            return;
        model = (CreditUserModel) getIntent().getSerializableExtra(DATA_SIGN);
        isCanEdit = getIntent().getBooleanExtra("isCanEdit",false);

        setView();
    }

    private void setView() {
        if (isCanEdit){

            mBinding.myElName.setText(model.getUserName());
            mBinding.myElPhone.setText(model.getMobile());
            mBinding.mySlRole.setText(model.getLoanRole());
            mBinding.mySlRelation.setText(model.getRelation());
            mBinding.myElId.setText(model.getIdNo());
            mBinding.myIlIdCard.setFlImg(model.getIdNoFront());
            mBinding.myIlIdCard.setFlImgRight(model.getIdNoReverse());
            mBinding.myIlCredit.setFlImg(model.getAuthPdf());
            mBinding.myIlInterview.setFlImg(model.getInterviewPic());

        }else {
            mBinding.myElName.setTextByRequest(model.getUserName());
            mBinding.myElPhone.setTextByRequest(model.getMobile());
            mBinding.mySlRole.setTextByRequest(model.getLoanRole());
            mBinding.mySlRelation.setTextByRequest(model.getRelation());
            mBinding.myElId.setTextByRequest(model.getIdNo());
            mBinding.myIlIdCard.setFlImgByRequest(model.getIdNoFront());
            mBinding.myIlIdCard.setFlImgRightByRequest(model.getIdNoReverse());
            mBinding.myIlCredit.setFlImgByRequest(model.getAuthPdf());
            mBinding.myIlInterview.setFlImgByRequest(model.getInterviewPic());
        }


        // 是否是 录入征信结果节点
        if (TextUtils.equals(nodeCode, "001_02")){
            mBaseBinding.titleView.setRightTitle("录入");
            mBaseBinding.titleView.setRightFraClickListener(view -> {
                BankCreditResultActivity.open(this, model.getBankCreditResult(), true);
            });
        }else {

            mBaseBinding.titleView.setRightTitle("征信报告");
            mBaseBinding.titleView.setRightFraClickListener(view -> {
                BankCreditResultActivity.open(this, model.getBankCreditResult(), false);
            });
        }


    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){

            }

        });
    }

    private boolean check(){

        // 征信报告
        if (model.getBankCreditResult() == null){
            ToastUtil.show(this, "请完善银行征信报告");
            return false;
        }


        return true;
    }


    @Subscribe
    public void addBankResult(CreditResult result){
        model.setBankCreditResult(result);
    }

}
