package com.cdkj.wzcd;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.wzcd.databinding.ActivityMainBinding;
import com.cdkj.wzcd.module.business.zxdc.BssZxdcListActivity;

public class MainActivity extends AbsBaseLoadActivity {

    private ActivityMainBinding mBinding;

    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected boolean canLoadTopTitleView() {
        return false;
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_main, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {


        initListener();
    }

    private void initListener() {
        mBinding.mySrZxdc.setOnClickListener(view -> {
            BssZxdcListActivity.open(this);
        });
    }
}
