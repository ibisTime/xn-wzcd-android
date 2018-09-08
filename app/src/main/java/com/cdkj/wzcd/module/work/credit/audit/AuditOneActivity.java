package com.cdkj.wzcd.module.work.credit.audit;

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
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adapter.AuditCourtNetworkAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityAuditOneBinding;
import com.cdkj.wzcd.model.CourtNetworkResultsModel;
import com.cdkj.wzcd.model.CreditModel;
import com.cdkj.wzcd.model.CreditUserModel;
import com.cdkj.wzcd.module.work.credit.CreditUserActivity;
import com.cdkj.wzcd.util.BankHelper;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.RequestUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.credit_user_loan_role;
import static com.cdkj.wzcd.util.DataDictionaryHelper.credit_user_relation;

/**
 * 征信一审
 */
public class AuditOneActivity extends AbsBaseLoadActivity {

    private ActivityAuditOneBinding mBinding;
    private String creditCode;
    private CreditModel mData;
    private AuditCourtNetworkAdapter mAdapter;
    public static List<CreditUserModel> mList = new ArrayList<>();

    // 角色
    private List<DataDictionary> mRole = new ArrayList<>();
    // 关系
    private List<DataDictionary> mRelation = new ArrayList<>();

    /**
     * @param context
     */
    public static void open(Context context, String code) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, AuditOneActivity.class);
        intent.putExtra(DATA_SIGN, code);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
//        activity_audit_one.xml
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_audit_one, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("一审");

        init();
        initOnclick();
        initListAdapter();

    }


    private void init() {
        if (getIntent() != null) {
            creditCode = getIntent().getStringExtra(DATA_SIGN);
        }
    }

    private void initOnclick() {


        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()) {
                // 通过
                approveResult("1");
            }
        });

        mBinding.myCbConfirm.setOnConfirmRightListener(view -> {
            //不通过
            if (check()) {
                approveResult("0");
            }
        });
    }

    /**
     * 不通过 0
     * 通过  1
     */
    private void approveResult(String approveResult) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("approveNote", mBinding.myElApproveNote.getText());
        map.put("approveResult", approveResult);
        map.put("code", mData.getCode());
        map.put("operator", SPUtilHelper.getUserId());
        List list = new ArrayList<CourtNetworkResultsModel>();
        for (CreditUserModel item : mList) {
            CourtNetworkResultsModel courtNetworkResultsModel = new CourtNetworkResultsModel();
            courtNetworkResultsModel.setCode(item.getCode());
            courtNetworkResultsModel.setCourtNetworkResults(item.getCourtNetworkMesg());
            list.add(courtNetworkResultsModel);
        }
        map.put("courtNetworkResultsList", list);

        Call<BaseResponseModel<IsSuccessModes>> call = RetrofitUtils.createApi(MyApiServer.class).firstInstanceNo("632114", StringUtils.getJsonToString(map));

        showLoadingDialog();
        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(AuditOneActivity.this, "操作成功");
                    finish();
                } else {
                    UITipDialog.showSuccess(AuditOneActivity.this, "操作失败");
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void initListAdapter() {

        List<DataDictionary> listRole = DataDictionaryHelper.getListByParentKey(credit_user_loan_role);

        if (listRole == null || listRole.size() == 0) {
            return;
        }
        mRole.addAll(listRole);


        List<DataDictionary> listRelation = DataDictionaryHelper.getListByParentKey(credit_user_relation);

        if (listRelation == null || listRelation.size() == 0) {
            return;
        }
        mRelation.addAll(listRelation);

        mAdapter = new AuditCourtNetworkAdapter(mList, mRole, mRelation);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            CreditUserModel model = mAdapter.getItem(position);

            CreditUserActivity.open(this, model, position, false, mRole, mRelation);


        });

        mBinding.rvZxr.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvZxr.setAdapter(mAdapter);

        initData();

    }

    private void initData() {
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
            mBinding.mySlBank.setTextByRequest(data.getBankName());
        });

        mBinding.mySlWay.setTextByRequest(DataDictionaryHelper.getBizTypeByKey(mData.getShopWay()));
        mBinding.myElAmount.setTextByRequest(RequestUtil.formatAmountDiv(mData.getLoanAmount()));

        if (TextUtils.equals(mData.getShopWay(), "2")) { //二手车
            // 新车则隐藏证件
            mBinding.myIlDocuments.setVisibility(View.VISIBLE);

            mBinding.myIlDocuments.setFlImg(mData.getXszFront());
            mBinding.myIlDocuments.setFlImgRight(mData.getXszReverse());

        }

        mList.clear();
        mList.addAll(mData.getCreditUserList());
        mAdapter.notifyDataSetChanged();
    }


    private boolean check() {

        for (CreditUserModel item : mList) {
            if (TextUtils.isEmpty(item.getCourtNetworkMesg())) {
                UITipDialog.showInfo(this, "请填写" + item.getUserName() + "法院网结果");
                return false;
            }
        }
        if (TextUtils.isEmpty(mBinding.myElApproveNote.getText())) {
            UITipDialog.showInfo(this, "请填写备注");
            return false;
        }
        return true;
    }

}
