package com.cdkj.wzcd.module.work.join_approval;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivityBudgetAdvanceBinding;
import com.cdkj.wzcd.model.AdvanceReplaceModel;
import com.cdkj.wzcd.model.NodeListModel;

import org.greenrobot.eventbus.EventBus;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/7/2.
 */

public class AdvanceFundActivity extends AbsBaseLoadActivity {

    private ActivityBudgetAdvanceBinding mBinding;

    private int position;
    private NodeListModel.RepointDetailListBean model;


    public static void open(Context context, NodeListModel.RepointDetailListBean model, int position) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, AdvanceFundActivity.class);
        intent.putExtra(DATA_SIGN, model);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }


    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_budget_advance, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("应退按揭款");

        if (getIntent() != null && getIntent().getExtras() != null){
            model = (NodeListModel.RepointDetailListBean) getIntent().getSerializableExtra(DATA_SIGN);
            position = getIntent().getIntExtra("position", 0);

        }

        initListener();

        setView();
    }

    private void initListener() {

        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                try{
                    // 组装数据
                    NodeListModel.RepointDetailListBean model = new NodeListModel.RepointDetailListBean();
                    model.setCarDealerName(mBinding.myElCarDealerName.getText());
                    model.setAccountNO(mBinding.myElAccountNO.getText());
                    model.setOpenBankName(mBinding.myElOpenBankName.getText());


                    // 替换
                    EventBus.getDefault().post(new AdvanceReplaceModel().setPosition(position).setRePointModel(model));
                    finish();


                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        });

    }

    private boolean check(){

        // 户名
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

        return true;
    }


    private void setView() {

        mBinding.myElCarDealerName.setText(model.getCarDealerName());
        mBinding.myElAccountNO.setText(model.getAccountNO());
        mBinding.myElOpenBankName.setText(model.getOpenBankName());


    }

}
