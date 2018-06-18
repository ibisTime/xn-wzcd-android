package com.cdkj.wzcd.module.work.credit;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivityCreditPersonAddBinding;
import com.cdkj.wzcd.model.CreditUserModel;
import com.cdkj.wzcd.model.CreditUserReplaceModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.view.MySelectLayout;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.List;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.baselibrary.utils.StringUtils.isIDCard;

/**
 * Created by cdkj on 2018/5/30.
 */

public class CreditUserActivity extends AbsBaseLoadActivity {

    private ActivityCreditPersonAddBinding mBinding;

    private CreditUserModel model;

    private boolean isCanEdit;
    // List的position
    private int position;

    private List<DataDictionary> role;
    private List<DataDictionary> relation;

    /**
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CreditUserActivity.class);
        context.startActivity(intent);
    }

    /**
     *
     * @param context 上下文
     * @param model 征信人Model
//     * @param isCanEdit 当前页面是否可编辑,true:可编辑,false:不可编辑
     */
    public static void open(Context context, CreditUserModel model, int position, boolean isCanEdit, List<DataDictionary> role, List<DataDictionary> relation) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CreditUserActivity.class);
        intent.putExtra(DATA_SIGN, model);
        intent.putExtra("position", position);
        intent.putExtra("isCanEdit", isCanEdit);
        intent.putExtra("role", (Serializable) role);
        intent.putExtra("relation", (Serializable) relation);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_credit_person_add, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("征信人");

        initCustomView();
        initListener();

        if (getIntent() != null && getIntent().getExtras() != null){
            model = (CreditUserModel) getIntent().getSerializableExtra(DATA_SIGN);
            position = getIntent().getIntExtra("position", 0);
            isCanEdit = getIntent().getBooleanExtra("isCanEdit", false);

            role = (List<DataDictionary>) getIntent().getSerializableExtra("role");
            relation = (List<DataDictionary>) getIntent().getSerializableExtra("relation");

            setView();
        }

    }

    private void setView() {
        if (isCanEdit){
            mBinding.myElName.setText(model.getUserName());
            mBinding.myElPhone.setText(model.getMobile());

            mBinding.mySlRole.setTextAndKey(model.getLoanRole(), DataDictionaryHelper.getValueOnTheKey(model.getLoanRole(), role));
            mBinding.mySlRelation.setTextAndKey(model.getRelation(), DataDictionaryHelper.getValueOnTheKey(model.getRelation(), relation));

            mBinding.myElId.setText(model.getIdNo());
            mBinding.myIlIdCard.setFlImg(model.getIdNoFront());
            mBinding.myIlIdCard.setFlImgRight(model.getIdNoReverse());
            mBinding.myIlCredit.setFlImg(model.getAuthPdf());
            mBinding.myIlInterview.setFlImg(model.getInterviewPic());
        }else {
            mBinding.myElName.setTextByRequest(model.getUserName());
            mBinding.myElPhone.setTextByRequest(model.getMobile());

            mBinding.mySlRole.setTextByRequest(DataDictionaryHelper.getValueOnTheKey(model.getLoanRole(), role));
            mBinding.mySlRelation.setTextByRequest(DataDictionaryHelper.getValueOnTheKey(model.getRelation(), relation));

            mBinding.myElId.setTextByRequest(model.getIdNo());
            mBinding.myIlIdCard.setFlImgByRequest(model.getIdNoFront());
            mBinding.myIlIdCard.setFlImgRightByRequest(model.getIdNoReverse());
            mBinding.myIlCredit.setFlImgByRequest(model.getAuthPdf());
            mBinding.myIlInterview.setFlImgByRequest(model.getInterviewPic());

            mBinding.myCbConfirm.setVisibility(View.GONE);
        }


    }

    private void initCustomView() {

        mBinding.mySlRole.setData(this, MySelectLayout.DATA_DICTIONARY, DataDictionaryHelper.credit_user_loan_role,null);
        mBinding.mySlRelation.setData(this, MySelectLayout.DATA_DICTIONARY, DataDictionaryHelper.credit_user_relation,null);

        mBinding.myIlIdCard.setActivity(this,1,2);
        mBinding.myIlCredit.setActivity(this,3,0);
        mBinding.myIlInterview.setActivity(this,4,0);
    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                // 组装数据
                CreditUserModel model = new CreditUserModel();
                model.setUserName(mBinding.myElName.getText());
                model.setMobile(mBinding.myElPhone.getText());
                model.setLoanRole(mBinding.mySlRole.getDataKey());
                model.setRelation(mBinding.mySlRelation.getDataKey());
                model.setIdNo(mBinding.myElId.getText());
                model.setIdNoFront(mBinding.myIlIdCard.getFlImgUrl());
                model.setIdNoReverse(mBinding.myIlIdCard.getFlImgRightUrl());
                model.setAuthPdf(mBinding.myIlCredit.getFlImgUrl());
                model.setInterviewPic(mBinding.myIlInterview.getFlImgUrl());

                // 发送数据
                if (getIntent() != null && getIntent().getExtras() != null){
                    // 替换
                    EventBus.getDefault().post(new CreditUserReplaceModel().setLocation(position).setCreditUserModel(model));
                    finish();
                }else {
                    // 新增
                    EventBus.getDefault().post(model);
                    finish();
                }

            }

        });
    }

    private boolean check(){

        // 姓名
        if (TextUtils.isEmpty(mBinding.myElName.check())){
            return false;
        }
        // 手机号
        if (TextUtils.isEmpty(mBinding.myElPhone.check())){
            return false;
        }
        // 贷款角色
        if (mBinding.mySlRole.check()){
            return false;
        }
        // 与借款人关系
        if (mBinding.mySlRelation.check()){
            return false;
        }
        // 身份证号
        if (TextUtils.isEmpty(mBinding.myElId.check())){
            return false;
        }

        if(!isIDCard(mBinding.myElId.getText())){
            ToastUtil.show(this, "请输入合法身份证号");
            return false;
        }

        // 身份证正面
        if (TextUtils.isEmpty(mBinding.myIlIdCard.check())){
            return false;
        }
        // 身份证反面
        if (TextUtils.isEmpty(mBinding.myIlIdCard.check())){
            return false;
        }
        // 征信查询授权书
        if (TextUtils.isEmpty(mBinding.myIlCredit.check())){
            return false;
        }
        // 面签照片
        if (TextUtils.isEmpty(mBinding.myIlInterview.check())){
            return false;
        }
        return true;
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

                LogUtil.E("requestCode="+requestCode);

                if (requestCode == mBinding.myIlIdCard.getRequestCode()){
                    mBinding.myIlIdCard.setFlImg(key);
                }

                if (requestCode == mBinding.myIlIdCard.getRightRequestCode()){
                    mBinding.myIlIdCard.setFlImgRight(key);
                }

                if (requestCode == mBinding.myIlCredit.getRequestCode()){
                    mBinding.myIlCredit.setFlImg(key);
                }

                if (requestCode == mBinding.myIlInterview.getRequestCode()){
                    mBinding.myIlInterview.setFlImg(key);
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
