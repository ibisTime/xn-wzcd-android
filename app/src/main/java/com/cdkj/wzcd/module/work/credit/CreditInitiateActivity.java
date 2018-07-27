package com.cdkj.wzcd.module.work.credit;

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
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adapter.CreditUserAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityZxLaunchBinding;
import com.cdkj.wzcd.model.CreditModel;
import com.cdkj.wzcd.model.CreditUserModel;
import com.cdkj.wzcd.model.CreditUserReplaceModel;
import com.cdkj.wzcd.model.ExchangeBankModel;
import com.cdkj.wzcd.util.BankHelper;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.RequestUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.budget_orde_biz_typer;
import static com.cdkj.wzcd.util.DataDictionaryHelper.credit_user_loan_role;
import static com.cdkj.wzcd.util.DataDictionaryHelper.credit_user_relation;
import static com.cdkj.wzcd.util.RequestUtil.formatAmountMul;

/**
 * 发起征信
 * Created by cdkj on 2018/5/29.
 */

public class CreditInitiateActivity extends AbsBaseLoadActivity {

    private ActivityZxLaunchBinding mBinding;

    private String creditCode;
    private CreditModel mData;

    // 银行
    private List<DataDictionary> mBank;

    // 角色
    private List<DataDictionary> mRole = new ArrayList<>();
    // 关系
    private List<DataDictionary> mRelation = new ArrayList<>();

    private CreditUserAdapter mAdapter;
    private List<CreditUserModel> mList = new ArrayList<>();

    /**
     * @param context
     */
    public static void open(Context context,String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CreditInitiateActivity.class);
        intent.putExtra(DATA_SIGN, code);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_zx_launch, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("发起征信");

        init();

        initListener();
        initListAdapter();

        getBank();


    }

    private void init() {
        mBank = new ArrayList<>();

        if (getIntent() != null){
            creditCode = getIntent().getStringExtra(DATA_SIGN);
        }

    }

    private void initListener() {
        mBinding.llAdd.setOnClickListener(view -> {
            CreditUserActivity.open(this);
        });

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){

                creditAddRequest();

            }
        });
    }

    private boolean check(){
        // 银行
        if (mBinding.mySlBank.check()){
            return false;
        }
        // 购车途径
        if (mBinding.mySlWay.check()){
            return false;
        }
        // 贷款金额
        if (mBinding.myElAmount.check()){
            return false;
        }
        if (mBinding.myIlDocuments.getVisibility() == View.VISIBLE){
            // 行驶证正面
            if (TextUtils.isEmpty(mBinding.myIlDocuments.check())){
                LogUtil.E("行驶证正面");
                return false;
            }
            // 行驶证反面
            if (TextUtils.isEmpty(mBinding.myIlDocuments.check())){
                LogUtil.E("行驶证反面");
                return false;
            }
        }

        // 征信人
        if (mList.size() == 0){
            ToastUtil.show(this, "请添加征信人");
            return false;
        }

        return true;
    }

    /**
     * 获取银行卡渠道
     */
    private void getBank() {

        BankHelper.getBankListRequest(this, data -> {

            if (data == null)
                return;

            for (ExchangeBankModel model : data){
                mBank.add(new DataDictionary().setDkey(model.getCode()).setDvalue(model.getBankName()+model.getAbbrName()));
            }

            mBinding.mySlBank.setData(mBank, null);

            initCustomView();
            getCredit();
        });


    }


    private void initCustomView() {

        mBinding.mySlWay.setData(DataDictionaryHelper.getListByParentKey(budget_orde_biz_typer), (dialog, which) -> {
            // 新车则隐藏证件
            mBinding.myIlDocuments.setVisibility(which == 0 ? View.GONE : View.VISIBLE);
        });

        mBinding.myIlDocuments.setActivity(this,1,2);
    }

    public void initListAdapter() {

        List<DataDictionary> listRole = DataDictionaryHelper.getListByParentKey(credit_user_loan_role);

        if (listRole == null || listRole.size() == 0){
            return;
        }
        mRole.addAll(listRole);


        List<DataDictionary> listRelation = DataDictionaryHelper.getListByParentKey(credit_user_relation);

        if (listRelation == null || listRelation.size() == 0){
            return;
        }
        mRelation.addAll(listRelation);

        mAdapter = new CreditUserAdapter(mList, mRole, mRelation);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            CreditUserModel model = mAdapter.getItem(position);

            CreditUserActivity.open(this, model, position, false, mRole, mRelation);


        });

        mBinding.rvZxr.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvZxr.setAdapter(mAdapter);

        getCredit();

        mAdapter = new CreditUserAdapter(mList, mRole, mRelation);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            CreditUserModel model = mAdapter.getItem(position);

            CreditUserActivity.open(this, model, position, true, mRole, mRelation);

        });

        mBinding.rvZxr.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvZxr.setAdapter(mAdapter);


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

                if (requestCode == mBinding.myIlDocuments.getRequestCode()){
                    mBinding.myIlDocuments.setFlImg(key);
                }

                if (requestCode == mBinding.myIlDocuments.getRightRequestCode()){
                    mBinding.myIlDocuments.setFlImgRight(key);
                }


                disMissLoading();
            }

            @Override
            public void onFal(String info) {
                disMissLoading();
            }
        }, path);
    }

    @Subscribe
    public void doAddCreditPerson(CreditUserModel model){

        mList.add(model);

        mAdapter.notifyDataSetChanged();

    }

    @Subscribe
    public void doReplaceCreditPerson(CreditUserReplaceModel model){

        mList.set(model.getLocation(), model.getCreditUserModel());

        mAdapter.notifyDataSetChanged();

    }

    /**
     * 发起征信
     */
    private void creditAddRequest(){
        Map<String, Object> map = new HashMap<>();

        if(!TextUtils.isEmpty(creditCode)){
            map.put("creditCode", creditCode);
        }

        map.put("shopWay", mBinding.mySlWay.getDataKey());
        map.put("buttonCode", "1");
        map.put("creditUserList", mList);
        map.put("loanAmount", formatAmountMul(mBinding.myElAmount.getText()));
        map.put("loanBankCode", mBinding.mySlBank.getDataKey());
        map.put("operator", SPUtilHelper.getUserId());
        map.put("xszFront", mBinding.myIlDocuments.getFlImgUrl());
        map.put("xszReverse", mBinding.myIlDocuments.getFlImgRightUrl());

        Call call = RetrofitUtils.getBaseAPiService().codeRequest(TextUtils.isEmpty(creditCode) ? "632110" : "632112", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(CreditInitiateActivity.this,TextUtils.isEmpty(creditCode) ? "发起成功" : "修改成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(CreditInitiateActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    /**
     * 获取征信详情
     */
    private void getCredit() {

        if (TextUtils.isEmpty(creditCode))
            return;

        Map<String, String> map = new HashMap<>();

        map.put("token", SPUtilHelper.getUserToken());
        map.put("code", creditCode);

        Call call = RetrofitUtils.createApi(MyApiServer.class).getCredit("632117", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CreditModel>(this) {

            @Override
            protected void onSuccess(CreditModel data, String SucMessage) {

                if (data == null)
                    return;

                mData = data;

                setView();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    private void setView() {
        BankHelper.getValueOnTheKey(this, mData.getLoanBankCode(), null, data -> {
            mBinding.mySlBank.setTextAndKey(mData.getLoanBankCode(), data.getBankName() + data.getAbbrName());
        });

        mBinding.mySlWay.setTextByRequest(DataDictionaryHelper.getBizTypeByKey(mData.getShopWay()));
        mBinding.myElAmount.setText(RequestUtil.formatAmountDiv(mData.getLoanAmount()));

        if (TextUtils.equals(mData.getShopWay(), "1")){ //二手车
            // 新车则隐藏证件
            mBinding.myIlDocuments.setVisibility(View.VISIBLE);

            mBinding.myIlDocuments.setFlImg(mData.getXszFront());
            mBinding.myIlDocuments.setFlImgRight(mData.getXszReverse());

        }

        mBaseBinding.titleView.setMidTitle("修改征信信息");
        mBinding.myCbConfirm.setText("修改");

        mList.addAll(mData.getCreditUserList());
        mAdapter.notifyDataSetChanged();

    }

}
