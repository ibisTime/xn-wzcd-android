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
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adpter.adapter.DataFileAdapter;
import com.cdkj.wzcd.adpter.adapter.DataFileChoiceAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivitySendBinding;
import com.cdkj.wzcd.model.DataFileModel;
import com.cdkj.wzcd.model.DataTransferModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.DatePickerHelper;
import com.cdkj.wzcd.view.MySelectLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

public class SendActivity extends AbsBaseLoadActivity {

    private String code;
    private ActivitySendBinding mBinding;

    private List<DataFileModel> refFileList = new ArrayList<>();
    private DataFileAdapter refFileAdapter;

    private List<DataFileModel> sendFileList = new ArrayList<>();
    private DataFileChoiceAdapter sendFileAdapter;

    public static void open(Context context, String code) {
        if (context != null) {
            Intent intent = new Intent(context, SendActivity.class);
            intent.putExtra(DATA_SIGN, code);
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
        mBaseBinding.titleView.setMidTitle("发件");

        if (getIntent()==null)
            return;

        code = getIntent().getStringExtra(DATA_SIGN);

        getData();
        initAdapter();

        initListener();
    }

    private void initListener() {
        mBinding.myNlDateTime.setOnClickListener(view -> {
            new DatePickerHelper().build(this).getDate(mBinding.myNlDateTime, true, true,  true, false, false, false);
        });

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                sendRequest();
            }
        });
    }

    private void initAdapter(){
        refFileAdapter = new DataFileAdapter(refFileList);
        mBinding.rvRefFile.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvRefFile.setAdapter(refFileAdapter);

        sendFileAdapter = new DataFileChoiceAdapter(sendFileList);
        sendFileAdapter.setOnItemChildClickListener((adapter, view, position) -> {

            DataFileModel model = sendFileAdapter.getItem(position);

            model.setChoice(!model.isChoice());
            sendFileAdapter.notifyItemChanged(position);

        });
        mBinding.rvSendFile.setLayoutManager(getLinearLayoutManager(false));
        mBinding.rvSendFile.setAdapter(sendFileAdapter);
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

        mBinding.mySlWay.setData(this, MySelectLayout.DATA_DICTIONARY, DataDictionaryHelper.send_type, (dialog, which) -> {
            // 如果寄送方式为快递则显示快递
            mBinding.llLogistics.setVisibility(TextUtils.equals(mBinding.mySlWay.getDataValue(), "快递") ? View.VISIBLE : View.GONE);
        });

        mBinding.mySlCompany.setData(this, MySelectLayout.DATA_DICTIONARY, DataDictionaryHelper.kd_company, null);

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

        sendFileList.clear();
        for (String file : sendFileStr){
            DataFileModel model = new DataFileModel();
            model.setFile(file);
            model.setChoice(false);
            sendFileList.add(model);
        }
        sendFileAdapter.notifyDataSetChanged();
    }


    private List<DataFileModel> getConfirmSendFileList(){

        List<DataFileModel> list = new ArrayList<>();

        for (DataFileModel model : sendFileList){

            if (model.isChoice()){
                list.add(model);
            }

        }

        return list;
    }

    private String getConfirmSendFile(){

        String sendFile = "";

        for (DataFileModel model : sendFileList){

            if (model.isChoice()){
                sendFile = sendFile + model.getFile() + ",";
            }

        }

        return sendFile;
    }

    private boolean check(){
        if (mBinding.llSendFile.getVisibility() == View.VISIBLE){
            int i = 0;

            for (DataFileModel model : sendFileList){

                if (model.isChoice()){
                    i++;
                }

            }

            if (i == 0){
                ToastUtil.show(this,"请勾选寄送材料");
                return false;
            }
        }

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
            if (TextUtils.isEmpty(mBinding.myElNumber.check())){
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
        Map<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("sendNote", mBinding.myElNote.getText());
        map.put("sendType", mBinding.mySlWay.getDataKey());
        map.put("operater", SPUtilHelper.getUserId());
        map.put("sendDatetime", mBinding.myNlDateTime.getText());
        if (mBinding.llSendFile.getVisibility() == View.VISIBLE){
            map.put("sendFileList", getConfirmSendFile().substring(0, getConfirmSendFile().length()-1));
        }else {
            map.put("sendFileList", "合同,材料"); // 要去掉!!!要去掉!!!要去掉!!!
        }



        if (mBinding.llLogistics.getVisibility() == View.VISIBLE){
            map.put("logisticsCode", mBinding.myElNumber.getText());
            map.put("logisticsCompany", mBinding.mySlCompany.getDataKey());
        }


        Call call = RetrofitUtils.getBaseAPiService().codeRequest("632150", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<CodeModel>(this) {

            @Override
            protected void onSuccess(CodeModel data, String SucMessage) {
                UITipDialog.showSuccess(SendActivity.this, "发件成功", dialogInterface -> {
                    finish();
                });
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                UITipDialog.showFail(SendActivity.this, errorMessage);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
