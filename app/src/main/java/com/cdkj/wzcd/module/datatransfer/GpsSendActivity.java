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
import com.cdkj.baselibrary.model.CodeModel;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivitySendBinding;
import com.cdkj.wzcd.model.DataTransferModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.DatePickerHelper;
import com.cdkj.wzcd.util.NodeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.kd_company;
import static com.cdkj.wzcd.util.DataDictionaryHelper.logistics_type;
import static com.cdkj.wzcd.util.DataDictionaryHelper.send_type;

public class GpsSendActivity extends AbsBaseLoadActivity {

    private String code;
    // 发件还是补件
    private boolean isSend;
    private ActivitySendBinding mBinding;

    public static void open(Context context, String code, boolean isSend) {
        if (context != null) {
            Intent intent = new Intent(context, GpsSendActivity.class);
            intent.putExtra(DATA_SIGN, code);
            intent.putExtra("isSend", isSend);
            context.startActivity(intent);
        }

    }


    @Override
    public View addMainView() {

        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_send, null, false);

        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        if (getIntent()==null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);
        isSend = getIntent().getBooleanExtra("isSend", false);

        mBaseBinding.titleView.setMidTitle(isSend ? "发件" : "补件");

        getData();
        initAdapter();

        initListener();
    }

    private void initListener() {
        mBinding.myNlDateTime.setOnClickListener(view -> {
            new DatePickerHelper().build(this).getDate(mBinding.myNlDateTime, true, true,  true, true, true, false);
        });

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                sendRequest();
            }
        });
    }

    private void initAdapter(){
    }

    public void getData(){
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

        mBinding.myNlSupplementReason.setVisibility(isSend ? View.GONE : View.VISIBLE);
        mBinding.myNlSupplementReason.setText(data.getSupplementReason());

        mBinding.mySlWay.setData(DataDictionaryHelper.getListByParentKey(send_type), (dialog, which) -> {
            // 如果寄送方式为快递则显示快递
            mBinding.llLogistics.setVisibility(TextUtils.equals(mBinding.mySlWay.getDataValue(), "快递") ? View.VISIBLE : View.GONE);
        });

        mBinding.mySlCompany.setData(DataDictionaryHelper.getListByParentKey(kd_company), null);

    }


    private boolean check(){

        // 寄送方式
        if (mBinding.mySlWay.check()){
            return false;
        }

        if (mBinding.llLogistics.getVisibility() == View.VISIBLE){

            // 快递公司
            if (mBinding.mySlCompany.check()){
                return false;
            }

            // 快递单号
            if (mBinding.myElNumber.check()){
                return false;
            }

        }

        // 发货时间
        if (TextUtils.isEmpty(mBinding.myNlDateTime.check())){
            return false;
        }


        return true;
    }

    private void sendRequest(){
        List<String> codeList = new ArrayList<>();
        codeList.add(code);

        Map<String, Object> map = new HashMap<>();
        if (isSend){
            map.put("codeList", codeList);
        }else {
            map.put("code", code);
        }

        map.put("sendNote", mBinding.myElNote.getText());
        map.put("sendType", mBinding.mySlWay.getDataKey());
        map.put("operator", SPUtilHelper.getUserId());
        map.put("sendDatetime", mBinding.myNlDateTime.getText());

        if (mBinding.llLogistics.getVisibility() == View.VISIBLE){
            map.put("logisticsCode", mBinding.myElNumber.getText());
            map.put("logisticsCompany", mBinding.mySlCompany.getDataKey());
        }

        Call call = RetrofitUtils.getBaseAPiService().codeRequest(isSend ? "632150" : "632153", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(GpsSendActivity.this, "操作成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(GpsSendActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
