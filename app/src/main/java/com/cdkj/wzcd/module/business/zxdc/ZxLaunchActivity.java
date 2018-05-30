package com.cdkj.wzcd.module.business.zxdc;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.model.BankModel;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.ImgUtils;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.ActivityZxLaunchBinding;
import com.cdkj.wzcd.model.MySelectModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * 发起征信
 * Created by cdkj on 2018/5/29.
 */

public class ZxLaunchActivity extends AbsBaseLoadActivity {

    private ActivityZxLaunchBinding mBinding;

    // 银行
    private List<MySelectModel> mMapBank;
    // 购车途径
    private List<MySelectModel> mMapWay;
    // 行驶证正面
    private String mDocPositive;
    // 行驶证反面
    private String mDocNegative;

    /**
     * @param context
     */
    public static void open(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, ZxLaunchActivity.class);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_zx_launch, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("发起征信");

        init();
        initListener();

//        getBank();
        initCustomView();
    }

    private void init() {
        mMapWay = new ArrayList<>();
        mMapBank = new ArrayList<>();
    }

    private void initListener() {
        mBinding.llAdd.setOnClickListener(view -> {

        });
    }

    /**
     * 获取银行卡渠道
     */
    private void getBank() {
        Map<String, String> map = new HashMap<>();

        map.put("token", SPUtilHelper.getUserToken());
        map.put("bankCode", "");
        map.put("bankName", "");
        map.put("channelType", "");
        map.put("status", "");
        map.put("paybank", "");

        Call call = RetrofitUtils.getBaseAPiService().getBackModel("802116", StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseListCallBack<BankModel>(this) {

            @Override
            protected void onSuccess(List<BankModel> data, String SucMessage) {

                if (data == null)
                    return;

                for (BankModel model : data){
                    mMapBank.add(new MySelectModel().setKey(model.getBankName()).setValue(model.getBankCode()));
                }

                initCustomView();
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }


    private void initCustomView() {

//        mBinding.mySlBank.setData(mMapBank);

        mMapWay.add(new MySelectModel().setKey("新车").setValue("1"));
        mMapWay.add(new MySelectModel().setKey("二手车").setValue("2"));

        mBinding.mySlWay.setData(mMapWay, (dialog, which) -> {
            // 新车则隐藏证件
            mBinding.myIlDocuments.setVisibility(which == 0 ? View.GONE : View.VISIBLE);
        });

        mBinding.myIlDocuments.setData(this);
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

                if (requestCode == mBinding.myIlDocuments.getFlImgViewId()){
                    mDocPositive = key;
                    ImgUtils.loadQiniuImg(ZxLaunchActivity.this, mDocPositive, mBinding.myIlDocuments.getFlImgImageView());
                }else {
                    mDocNegative = key;
                    ImgUtils.loadQiniuImg(ZxLaunchActivity.this, mDocNegative, mBinding.myIlDocuments.getFlImgRightImageView());
                }
                disMissLoading();

            }

            @Override
            public void onFal(String info) {
                disMissLoading();
            }
        }, path);
    }

}
