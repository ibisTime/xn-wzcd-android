package com.cdkj.wzcd.module.work.credit.audit;

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
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adapter.CreditUserAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityZxLaunchBinding;
import com.cdkj.wzcd.model.CreditModel;
import com.cdkj.wzcd.model.CreditResult;
import com.cdkj.wzcd.model.CreditUserModel;
import com.cdkj.wzcd.model.CreditUserReplaceModel;
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
import static com.cdkj.wzcd.util.DataDictionaryHelper.credit_user_loan_role;
import static com.cdkj.wzcd.util.DataDictionaryHelper.credit_user_relation;

/**
 * 发起征信
 * Created by cdkj on 2018/5/29.
 */

public class AuditCreditActivity extends AbsBaseLoadActivity {

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
        Intent intent = new Intent(context, AuditCreditActivity.class);
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

        mBaseBinding.titleView.setMidTitle("录入征信结果");

        init();

        initListener();
        initListAdapter();


    }

    private void init() {
        mBank = new ArrayList<>();

        if (getIntent() != null){
            creditCode = getIntent().getStringExtra(DATA_SIGN);
        }

    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                inputResultRequest();
            }
        });
    }

    private boolean check(){
        for (CreditUserModel model : mList){

            if (model.getBankCreditResult() == null){
                ToastUtil.show(this, "请完善"+DataDictionaryHelper.getDataByKey(credit_user_loan_role, model.getLoanRole()).getDvalue()+"征信报告");
                return false;
            }

        }

        return true;
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

            AuditUserActivity.open(this, model, position);

        });

        mBinding.rvZxr.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvZxr.setAdapter(mAdapter);

        getCredit();

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
     * 录入征信结果
     */
    private void inputResultRequest(){
        Map<String, Object> map = new HashMap<>();

        List<CreditResult> bankCreditResultList = new ArrayList<>();

        for (CreditUserModel model : mList){
            bankCreditResultList.add(model.getBankCreditResult());
        }

        map.put("creditCode", creditCode);
        map.put("bankCreditResultList", bankCreditResultList);
        map.put("operator", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632111", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(AuditCreditActivity.this, "录入成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(AuditCreditActivity.this, errorMessage);
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
            mBinding.mySlBank.setTextByRequest(data.getBankName());
        });

        mBinding.mySlWay.setTextByRequest(DataDictionaryHelper.getBizTypeByKey(mData.getShopWay()));

        mBinding.myElAmount.setTextByRequest(RequestUtil.formatAmountDiv(mData.getLoanAmount()));

        if (TextUtils.equals(mData.getShopWay(), "2")){ //二手车
            // 新车则隐藏证件
            mBinding.myIlDocuments.setVisibility(View.VISIBLE);

            mBinding.myIlDocuments.setFlImgByRequest(mData.getXszFront());
            mBinding.myIlDocuments.setFlImgRightByRequest(mData.getXszReverse());

        }

        mBinding.llAdd.setVisibility(View.GONE);
        mBinding.myCbConfirm.setText("录入征信结果");

        mList.addAll(mData.getCreditUserList());
        mAdapter.notifyDataSetChanged();

    }

}
