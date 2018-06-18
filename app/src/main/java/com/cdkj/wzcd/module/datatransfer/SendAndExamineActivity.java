package com.cdkj.wzcd.module.datatransfer;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adpter.adapter.DataFileAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivitySendAndExamineBinding;
import com.cdkj.wzcd.model.DataFileModel;
import com.cdkj.wzcd.model.DataTransferModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.NodeHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

public class SendAndExamineActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivitySendAndExamineBinding mBinding;

    private List<DataFileModel> refFileList = new ArrayList<>();
    private DataFileAdapter refFileAdapter;

    private List<DataFileModel> sendFileList = new ArrayList<>();
    private DataFileAdapter sendFileAdapter;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, SendAndExamineActivity.class);
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

        if (getIntent()==null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);

        initAdapter();
        initListener();

        getData();
    }

    private void initAdapter(){
        refFileAdapter = new DataFileAdapter(refFileList);
        mBinding.rvRefFile.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvRefFile.setAdapter(refFileAdapter);

        sendFileAdapter = new DataFileAdapter(sendFileList);
        mBinding.rvSendFile.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvSendFile.setAdapter(sendFileAdapter);
    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            pickUpAndPassRequest();
        });

        mBinding.myCbConfirm.setOnConfirmRightListener(view -> {
            pickUpAndReissueRequest();
        });
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
        mBinding.myNlNodeSend.setText(NodeHelper.getNameOnTheCode(data.getToNodeCode()));
        mBinding.myNlNodeRe.setText(NodeHelper.getNameOnTheCode(data.getFromNodeCode()));

        if (!TextUtils.isEmpty(data.getRefFileList())){
            mBinding.llRefFile.setVisibility(View.VISIBLE);
            setReFileListData(data.getRefFileList());
        }else {
            mBinding.llRefFile.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(data.getSendFileList())){
            mBinding.llSendFile.setVisibility(View.VISIBLE);

            setSendFileListData(data.getSendFileList());
        }else {
            mBinding.llSendFile.setVisibility(View.GONE);
        }

        DataDictionaryHelper.getValueOnTheKeyRequest(this, DataDictionaryHelper.send_type, data.getSendType(), data1 -> {
            mBinding.myNlSendType.setText(data1.getDvalue());

            if (TextUtils.equals(data1.getDvalue(), "快递")){
                mBinding.llLogistics.setVisibility(View.VISIBLE);

                DataDictionaryHelper.getValueOnTheKeyRequest(this, DataDictionaryHelper.kd_company, data.getLogisticsCompany(), data2 -> {
                    mBinding.myNlLogisticsCompany.setText(data2.getDvalue());
                });

                mBinding.myNlLogisticsCode.setText(data.getLogisticsCode());
            }

        });

        mBinding.myNlSendDatetime.setText(DateUtil.formatStringData(data.getSendDatetime(), DateUtil.DEFAULT_DATE_FMT));
    }

    private void setReFileListData(String reFile){

        String[] reFileStr = reFile.split(",");

        List<DataFileModel> list = new ArrayList<>();

        for (String file : reFileStr){
            DataFileModel model = new DataFileModel();
            model.setFile(file);
            model.setChoice(false);
            list.add(model);
        }
        refFileList.clear();
        refFileList.addAll(list);
        refFileAdapter.notifyDataSetChanged();
    }

    private void setSendFileListData(String sendFile){

        String[] sendFileStr = sendFile.split(",");

        List<DataFileModel> list = new ArrayList<>();

        for (String file : sendFileStr){
            DataFileModel model = new DataFileModel();
            model.setFile(file);
            model.setChoice(false);
            list.add(model);
        }
        sendFileList.clear();
        sendFileList.addAll(list);
        sendFileAdapter.notifyDataSetChanged();
    }

    /**
     * 收件并审核通过
     */
    private void pickUpAndPassRequest() {
        if (TextUtils.isEmpty(code))
            return;

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("code", code);
        hashMap.put("remark", mBinding.myElSendNote.getText());

        Call call= RetrofitUtils.getBaseAPiService().successRequest("632151", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(SendAndExamineActivity.this, "操作成功", dialogInterface -> {
                        finish();
                    });
                } else {
                    UITipDialog.showFail(SendAndExamineActivity.this, "操作失败");
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    /**
     * 收件并补发
     */
    private void pickUpAndReissueRequest() {
        if (TextUtils.isEmpty(code))
            return;

        HashMap<String, String> hashMap = new LinkedHashMap<String, String>();

        hashMap.put("code", code);
        hashMap.put("remark", mBinding.myElSendNote.getText());

        Call call= RetrofitUtils.getBaseAPiService().successRequest("632152", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(this) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                if (data.isSuccess()) {
                    UITipDialog.showSuccess(SendAndExamineActivity.this, "操作成功", dialogInterface -> {
                        finish();
                    });
                } else {
                    UITipDialog.showFail(SendAndExamineActivity.this, "操作失败");
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }
}
