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
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adapter.CreditUserAdapter;
import com.cdkj.wzcd.adapter.CreditUserAdapterUp;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityZxLaunchBinding;
import com.cdkj.wzcd.model.CreditModel;
import com.cdkj.wzcd.model.CreditUserModel;
import com.cdkj.wzcd.util.BankHelper;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.chad.library.adapter.base.BaseQuickAdapter;

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

    private BaseQuickAdapter mAdapter;
    private List<CreditUserModel> mList = new ArrayList<>();

    /**
     * @param context
     * @param code    征信单Code
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

        if (getIntent() != null) {
            creditCode = getIntent().getStringExtra(DATA_SIGN);
            isApply = getIntent().getBooleanExtra("isApply", false);

            initAdapter();

        }

        initListener();

    }

    private void initListener() {
        mBinding.myCbApply.setOnConfirmListener(view -> {
            //通过
            if (isCheck()) {
                apply("1");
            }
        });

        mBinding.myCbApply.setOnConfirmRightListener(view -> {

            if (isCheck()) {
                apply("0");
            }
        });
    }

    private boolean isCheck() {
        if (TextUtils.isEmpty(mBinding.myElApproveNote.getText())) {
            UITipDialog.showInfo(this, "备注不能为空");
            return false;
        }
        return true;
    }

    public void initAdapter() {

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

        mAdapter = new CreditUserAdapter(mList, mRole, mRelation);
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            CreditUserModel model = (CreditUserModel) mAdapter.getItem(position);

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

        mBinding.mySlWay.setTextByRequest(DataDictionaryHelper.getBizTypeByKey(mData.getShopWay()));
        mBinding.myElAmount.setMoneyTextRequest(mData.getLoanAmount());

        if (TextUtils.equals(mData.getShopWay(), "2")) { //二手车
            // 新车则隐藏证件
            mBinding.myIlDocuments.setVisibility(View.VISIBLE);

            mBinding.myIlDocuments.setFlImgByRequest(mData.getXszFront());
            mBinding.myIlDocuments.setFlImgRightByRequest(mData.getXszReverse());

        }

        mBinding.llAdd.setVisibility(View.GONE);
        mBinding.myCbConfirm.setVisibility(View.GONE);


        if (isApply) {
            mBaseBinding.titleView.setMidTitle("征信初审");
            mAdapter = new CreditUserAdapterUp(mList, mRole, mRelation);
            mBinding.rvZxr.setAdapter(mAdapter);
//            mBinding.myElOtherFilePdf.setVisibility(View.VISIBLE);//显示附件
            mBinding.myElOtherFilePdf.build(this, 10, 0);
        }
        mList.addAll(mData.getCreditUserList());
        mAdapter.notifyDataSetChanged();

        //这是一个新的 adapter 审核的时候  条目会多一个 选中的那按钮
        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            switch (view.getId()) {
                case R.id.iv_choice:
                    mList.get(position).setChoice(!mList.get(position).isChoice());
                    mAdapter.notifyItemChanged(position);
                    break;
            }
        });
        mAdapter.setOnItemClickListener((adapter, view, position) -> {
            CreditUserModel model = (CreditUserModel) mAdapter.getItem(position);

            CreditUserActivity.open(this, model, position, false, mRole, mRelation);
        });


        mBinding.llApply.setVisibility(isApply ? View.VISIBLE : View.GONE);

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

                if (requestCode == mBinding.myElOtherFilePdf.getRequestCode()) {
                    mBinding.myElOtherFilePdf.addList(key);
                }

                disMissLoading();

            }

            @Override
            public void onFal(String info) {
                disMissLoading();
            }
        }, path);
    }


    /**
     * 征信初审
     */
    private void apply(String approveResult) {

        HashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();

        hashMap.put("code", creditCode);
        hashMap.put("approveResult", approveResult);
        hashMap.put("operator", SPUtilHelper.getUserId());
        hashMap.put("approveNote", mBinding.myElApproveNote.getText());

        String listData = mBinding.myElOtherFilePdf.getListData();
        if (!TextUtils.isEmpty(listData)) {
            hashMap.put("accessory", listData);
        }


        if (TextUtils.equals("1", approveResult)) {

            List list = new ArrayList<CreditUserModel>();
            for (CreditUserModel item : mList) {
                if (item.isChoice()) {
                    list.add(item);
                }
            }
            if (list.size() == 0) {
                UITipDialog.showInfo(this, "请勾选征信人");
                return;
            }
            hashMap.put("creditUserList", list);
        }
        Call call = RetrofitUtils.getBaseAPiService().successRequest("632113", StringUtils.getJsonToString(hashMap));

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
