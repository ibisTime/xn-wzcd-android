package com.cdkj.wzcd.module.datatransfer;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivityDataAddReasonBinding;
import com.cdkj.wzcd.model.ReasonModel;
import com.cdkj.wzcd.model.ReasonReplaceModel;

import org.greenrobot.eventbus.EventBus;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/7/16.
 */

public class DataAddReasonActivity extends AbsBaseLoadActivity {

    private ActivityDataAddReasonBinding mBinding;

    private ReasonModel mUserModel;
    // List的position
    private int position;

    /**
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, DataAddReasonActivity.class);
        context.startActivity(intent);
    }

    /**
     *
     * @param context 上下文
     * @param model 征信人Model
    //     * @param isCanEdit 当前页面是否可编辑,true:可编辑,false:不可编辑
     */
    public static void open(Context context, ReasonModel model, int position) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, DataAddReasonActivity.class);
        intent.putExtra(DATA_SIGN, model);
        intent.putExtra("position", position);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_data_add_reason, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("补件理由");

        initListener();

        if (getIntent() != null && getIntent().getExtras() != null){
            mUserModel = (ReasonModel) getIntent().getSerializableExtra(DATA_SIGN);
            position = getIntent().getIntExtra("position", 0);

            setView();
        }
    }

    private void setView() {
        mBinding.myElReason.setText(mUserModel.getReason());

    }

    private void initListener() {
        mBinding.myCbConfirm.setOnConfirmListener(view -> {
            if (check()){
                // 组装数据
                ReasonModel model = new ReasonModel();
                model.setReason(mBinding.myElReason.getText());

                // 发送数据
                if (getIntent() != null && getIntent().getExtras() != null){
                    // 替换
                    EventBus.getDefault().post(new ReasonReplaceModel().setLocation(position).setReasonModel(model));
                    finish();
                }else {
                    // 新增
                    EventBus.getDefault().post(model);
                    finish();
                }

            }

        });
    }

    private boolean check(){

        // 理由
        if (mBinding.myElReason.check()){
            return false;
        }

        return true;
    }
}
