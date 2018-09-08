package com.cdkj.wzcd.module.work.join_approval;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivityRePointAddBinding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.model.RePointReplaceModel;

import org.greenrobot.eventbus.EventBus;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/7/2.
 */

public class RePointAddActivity extends AbsBaseLoadActivity {

    private ActivityRePointAddBinding mBinding;

    private int position;
    private NodeListModel.RepointDetailListBean model;

    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, RePointAddActivity.class);
        context.startActivity(intent);
    }

    public static void open(Context context, NodeListModel.RepointDetailListBean model, int position) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, RePointAddActivity.class);
        intent.putExtra(DATA_SIGN, model);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_re_point_add, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("新增协议外返点");

        if (getIntent() != null && getIntent().getExtras() != null){
            model = (NodeListModel.RepointDetailListBean) getIntent().getSerializableExtra(DATA_SIGN);
            position = getIntent().getIntExtra("position", 0);

        }

        initListener();

        if (model != null){
            setView();
        }
    }

    private void initListener() {

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                try{
                    // 组装数据
                    NodeListModel.RepointDetailListBean model = new NodeListModel.RepointDetailListBean();
                    model.setUseMoneyPurpose("3");
                    model.setRepointAmount(mBinding.myElRepointAmount.getMoneyText());
                    model.setCarDealerName(mBinding.myElCarDealerName.getText());
                    model.setAccountName(mBinding.myElAccountName.getText());
                    model.setAccountNO(mBinding.myElAccountNO.getText());
                    model.setOpenBankName(mBinding.myElOpenBankName.getText());


                    // 发送数据
                    if (getIntent() != null && getIntent().getExtras() != null){
                        // 替换
                        EventBus.getDefault().post(new RePointReplaceModel().setPosition(position).setRePointModel(model));
                        finish();
                    }else {
                        // 发送数据
                        EventBus.getDefault().post(model);
                        finish();
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

    }

    private boolean check(){

        // 安装位置
        if (mBinding.myElRepointAmount.check()){
            return false;
        }

        // 单位
        if (mBinding.myElCarDealerName.check()){
            return false;
        }

        // 账号
        if (mBinding.myElAccountNO.check()){
            return false;
        }

        // 开户行
        if (mBinding.myElOpenBankName.check()){
            return false;
        }

        //户名
        if (mBinding.myElAccountName.check()) {
            return false;
        }

        return true;
    }


    private void setView() {

        mBinding.myElRepointAmount.setMoneyText(model.getRepointAmount());
        mBinding.myElCarDealerName.setText(model.getCarDealerName());
        mBinding.myElAccountNO.setText(model.getAccountNO());
        mBinding.myElOpenBankName.setText(model.getOpenBankName());


    }

}
