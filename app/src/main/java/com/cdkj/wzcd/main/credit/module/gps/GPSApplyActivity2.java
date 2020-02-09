package com.cdkj.wzcd.main.credit.module.gps;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.ActivityGpsapply2Binding;
import com.cdkj.wzcd.main.credit.bean.ConfirmBean;

import java.util.HashMap;

import retrofit2.Call;


public class GPSApplyActivity2 extends AbsBaseLoadActivity {

    public ActivityGpsapply2Binding mBinding;

    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, GPSApplyActivity2.class);
            context.startActivity(intent);
        }
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_gpsapply2, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("GPS申请");
        init();
        initClick();

    }

    private void init() {
        mBinding.elTeamName.setTextByRequest(SPUtilHelper.getTeamName());
        mBinding.elName.setTextByRequest(SPUtilHelper.getDepartmentName() + "-" + SPUtilHelper.getPostName() + "-" + SPUtilHelper.getUserName());
    }

    private void initClick() {

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (BaseViewUtil.check(mBinding.llRooot)) {
                confirm();
            }
        });
    }

    private void confirm() {
        HashMap<String, String> map = new HashMap<>();
        map.put("token", SPUtilHelper.getUserToken());
        map.put("applyUser", SPUtilHelper.getUserId());
        map.put("applyCount", mBinding.elNumber.getText());
        map.put("applyReason", mBinding.elRemaker.getText());
        Call<BaseResponseModel<ConfirmBean>> confirm = RetrofitUtils.createApi(MyApiServer.class).confirm("632710", StringUtils.getJsonToString(map));
        showLoadingDialog();
        confirm.enqueue(new BaseResponseModelCallBack<ConfirmBean>(this) {
            @Override
            protected void onSuccess(ConfirmBean data, String SucMessage) {
                ToastUtil.show(GPSApplyActivity2.this, "申领成功");
                finish();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }
}
