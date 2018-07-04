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
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adapter.CreditUserAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityZxLaunchBinding;
import com.cdkj.wzcd.model.CreditModel;
import com.cdkj.wzcd.model.CreditUserModel;
import com.cdkj.wzcd.util.BankHelper;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.RequestUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

public class CreditDetailActivity extends AbsBaseLoadActivity {

    private ActivityZxLaunchBinding mBinding;

    private String creditCode;
    private boolean isApply;
    private CreditModel mData;

    // 角色
    private List<DataDictionary> mRole = new ArrayList<>();
    // 关系
    private List<DataDictionary> mRelation = new ArrayList<>();

    private CreditUserAdapter mAdapter;
    private List<CreditUserModel> mList = new ArrayList<>();

    /**
     *
     * @param context
     * @param code 征信单Code
     * @param isApply 是否是申请初审
     */
    public static void open(Context context, String code, boolean isApply) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, CreditDetailActivity.class);
        intent.putExtra(DATA_SIGN, code);
        intent.putExtra("isApply", isApply);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_zx_launch, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("征信详情");

        if (getIntent() != null){
            creditCode = getIntent().getStringExtra(DATA_SIGN);
            isApply = getIntent().getBooleanExtra("isApply", false);

            initAdapter();

        }

        initListener();

    }

    private void initListener() {
        mBinding.myCbApply.setOnConfirmListener(view -> {
            apply("1");
        });

        mBinding.myCbApply.setOnConfirmRightListener(view -> {
            apply("0");
        });
    }

    public void initAdapter() {

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
            mBinding.mySlBank.setTextByRequest(data.getBankName());
        });

        mBinding.mySlWay.setTextByRequest(DataDictionaryHelper.getBizTypeBuyKey(mData.getShopWay()));
        mBinding.myElAmount.setTextByRequest(RequestUtil.formatAmountDiv(mData.getLoanAmount()));

        if (TextUtils.equals(mData.getShopWay(), "2")){ //二手车
            // 新车则隐藏证件
            mBinding.myIlDocuments.setVisibility(View.VISIBLE);

            mBinding.myIlDocuments.setFlImgByRequest(mData.getXszFront());
            mBinding.myIlDocuments.setFlImgRightByRequest(mData.getXszReverse());

        }

        mBinding.llAdd.setVisibility(View.GONE);
        mBinding.myCbConfirm.setVisibility(View.GONE);

        mList.addAll(mData.getCreditUserList());
        mAdapter.notifyDataSetChanged();


        mBinding.llApply.setVisibility(isApply ? View.VISIBLE : View.GONE);

    }


    /**
     * 征信初审
     */
    private void apply(String approveResult) {

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("code", creditCode);
        hashMap.put("approveResult", approveResult);
        hashMap.put("operator", SPUtilHelper.getUserId());
        hashMap.put("approveNote", mBinding.myElApproveNote.getText());

        Call call= RetrofitUtils.getBaseAPiService().successRequest("632113", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(CreditDetailActivity.this, "操作成功", dialogInterface -> {
                        finish();
                    });
                } else {
                    UITipDialog.showFail(CreditDetailActivity.this, "操作失败");
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }
}
