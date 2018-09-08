package com.cdkj.wzcd.module.datatransfer;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adapter.SupplementReasonAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivitySendAndExamineBinding;
import com.cdkj.wzcd.model.DataTransferModel;
import com.cdkj.wzcd.model.ReasonModel;
import com.cdkj.wzcd.model.ReasonReplaceModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.NodeHelper;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.logistics_type;

public class DataReceiveActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivitySendAndExamineBinding mBinding;

    private List<ReasonModel> reasonList = new ArrayList<>();
    private SupplementReasonAdapter reasonAdapter;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, DataReceiveActivity.class);
            intent.putExtra(DATA_SIGN, code);
            context.startActivity(intent);
        }

    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_send_and_examine, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("收件");

        if (getIntent() == null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);

        initAdapter();
        initListener();

        getData();
    }


    private void initAdapter() {

        reasonAdapter = new SupplementReasonAdapter(reasonList);
        reasonAdapter.setOnItemClickListener((adapter, view, position) -> {
            DataAddReasonActivity.open(this, reasonAdapter.getItem(position), position);
        });
        reasonAdapter.setOnItemChildClickListener((adapter, view, position) -> {

            ReasonModel model = reasonAdapter.getItem(position);
            model.setChoice(!model.isChoice());
            reasonAdapter.notifyItemChanged(position);

        });
        mBinding.rvReason.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvReason.setAdapter(reasonAdapter);

    }

    @Subscribe
    public void doAddCreditPerson(ReasonModel model) {

        reasonList.add(model);
        reasonAdapter.notifyDataSetChanged();

    }

    @Subscribe
    public void doReplaceCreditPerson(ReasonReplaceModel model) {

        reasonList.set(model.getLocation(), model.getReasonModel());
        reasonAdapter.notifyDataSetChanged();

    }

    private void initListener() {
        mBinding.llAdd.setOnClickListener(view -> {
            DataAddReasonActivity.open(this);
        });

        mBinding.btnConfirm.setOnClickListener(view -> {
            if (check()) {
                reissueRequest();
            }
        });

        mBinding.btnConfirmCenter.setOnClickListener(view -> {
            showDoubleWarnListen("您确定要退回此件吗？", view1 -> {
                returnRequest();
            });

        });

        mBinding.btnConfirmRight.setOnClickListener(view -> {
            passRequest();
        });
    }

    public boolean check() {

        int i = 0;

        for (ReasonModel model : reasonList) {

            if (model.isChoice()) {
                i++;
            }

        }

        if (i == 0) {
            ToastUtil.show(this, "请勾选补件原因");
            return false;
        }

//        if (reasonList.size() == 0){
//            return false;
//        }

        return true;
    }

    private List<ReasonModel> getSelectedReasonList() {

        List<ReasonModel> list = new ArrayList<>();

        for (ReasonModel model : reasonList) {

            if (model.isChoice()) {
                list.add(model);
            }

        }

        return list;
    }


    public void getData() {
        Map<String, String> map = new HashMap<>();

        map.put("code", code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getData("632156", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<DataTransferModel>(this) {
            @Override
            protected void onSuccess(DataTransferModel data, String SucMessage) {

                setView(data);

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void setView(DataTransferModel data) {
        mBinding.myNlName.setText(data.getUserName());
        mBinding.myNlCode.setText(data.getCode());
        mBinding.myNlType.setText(DataDictionaryHelper.getValueBuyKey(logistics_type, data.getType()));
        mBinding.myNlNodeSend.setText(NodeHelper.getNameOnTheCode(data.getToNodeCode()));
        mBinding.myNlNodeRe.setText(NodeHelper.getNameOnTheCode(data.getFromNodeCode()));
        mBinding.myNlSendNote.setText(data.getSendNote());

        String dValue = DataDictionaryHelper.getValueBuyKey(DataDictionaryHelper.send_type, data.getSendType());
        mBinding.myNlSendType.setText(dValue);

        if (TextUtils.equals(dValue, "快递")) {
            mBinding.llLogistics.setVisibility(View.VISIBLE);
            mBinding.myNlLogisticsCode.setText(data.getLogisticsCode());
            mBinding.myNlLogisticsCompany.setText(DataDictionaryHelper.getValueBuyKey(DataDictionaryHelper.kd_company, data.getLogisticsCompany()));
        }


        if (data.getSupplementReasonList() != null) {
            for (DataTransferModel.SupplementReasonListBean bean : data.getSupplementReasonList()) {

                ReasonModel reasonModel = new ReasonModel();
                reasonModel.setReason(bean.getReason());
                reasonList.add(reasonModel);

            }
            reasonAdapter.notifyDataSetChanged();
        }

        mBinding.myNlSendDatetime.setText(DateUtil.formatStringData(data.getSendDatetime(), DateUtil.DEFAULT_DATE_FMT));
    }

    /**
     * 退件
     */
    private void returnRequest() {
        if (TextUtils.isEmpty(code))
            return;

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("code", code);
        hashMap.put("operator", SPUtilHelper.getUserId());
//        hashMap.put("remark", mBinding.myElSendNote.getText());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("632158", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(DataReceiveActivity.this, "操作成功", dialogInterface -> {
                        finish();
                    });
                } else {
                    UITipDialog.showFail(DataReceiveActivity.this, "操作失败");
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    /**
     * 审核通过
     */
    private void passRequest() {
//        632154  审核接口
//        code operator token updater
        if (TextUtils.isEmpty(code))
            return;

        List<String> codeList = new ArrayList<>();
        codeList.add(code);

        Map<String, Object> map = new HashMap<>();

        map.put("code", code);
        map.put("codeList", codeList);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("updater", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());
//        hashMap.put("remark", mBinding.myElSendNote.getText());
//        632151
        Call call = RetrofitUtils.getBaseAPiService().successRequest("632154", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(DataReceiveActivity.this, "操作成功", dialogInterface -> {
                        finish();
                    });
                } else {
                    UITipDialog.showFail(DataReceiveActivity.this, "操作失败");
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    /**
     * 补件
     */
    private void reissueRequest() {
        if (TextUtils.isEmpty(code))
            return;

        HashMap<String, Object> hashMap = new HashMap<>();
        ArrayList<String> codeList = new ArrayList();
        codeList.add(code);
        hashMap.put("codeList", codeList);
//        hashMap.put("code", code);
        hashMap.put("updater", SPUtilHelper.getUserId());
        hashMap.put("operator", SPUtilHelper.getUserId());

        hashMap.put("supplementReasonList", getSelectedReasonList());

        Call call = RetrofitUtils.getBaseAPiService().successRequest("632152", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(DataReceiveActivity.this, "操作成功", dialogInterface -> {
                        finish();
                    });
                } else {
                    UITipDialog.showFail(DataReceiveActivity.this, "操作失败");
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }
}
