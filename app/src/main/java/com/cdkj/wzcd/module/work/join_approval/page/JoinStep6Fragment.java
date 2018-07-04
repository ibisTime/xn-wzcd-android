package com.cdkj.wzcd.module.work.join_approval.page;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.utils.CameraHelper;
import com.cdkj.baselibrary.utils.QiNiuHelper;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.FragmentJoinStep6Binding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;

import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;
import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/7.
 */

public class JoinStep6Fragment extends BaseLazyFragment {

    private String code;
    private FragmentJoinStep6Binding mBinding;

    private NodeListModel data;

    public static JoinStep6Fragment getInstance(String code) {
        JoinStep6Fragment fragment = new JoinStep6Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, code);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_join_step6, null, false);

        if (getArguments() != null) {
            code = getArguments().getString(DATA_SIGN);
        }

        data = ((JoinApplyActivity) mActivity).mData;

        initView();
        setView();

        return mBinding.getRoot();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    private void initView() {
        mBinding.myMlMarryDivorce.build(mActivity, 10, 0);
        mBinding.myMlApplyUserHkb.build(mActivity, 10, 1);
        mBinding.myMlBankBillPdf.build(mActivity, 10, 2);
        mBinding.myMlSingleProvePdf.build(mActivity, 10, 3);
        mBinding.myMlIncomeProvePdf.build(mActivity, 10, 4);
        mBinding.myMlLiveProvePdf.build(mActivity, 10, 5);
        mBinding.myMlHouseInvoice.build(mActivity, 10, 6);
        mBinding.myMlBuildProvePdf.build(mActivity, 10, 7);
        mBinding.myMlHkbFirstPage.build(mActivity, 10, 8);
        mBinding.myMlHkbMainPage.build(mActivity, 10, 9);
        mBinding.myMlGuarantor1IdNo.build(mActivity, 10, 10);
        mBinding.myMlGuarantor1Hkb.build(mActivity, 10, 11);
        mBinding.myMlGuarantor2IdNo.build(mActivity, 10, 12);
        mBinding.myMlGuarantor2Hkb.build(mActivity, 10, 13);
        mBinding.myMlGhHkb.build(mActivity, 10, 14);
    }

    private void setView() {

    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK || data == null) {
            return;
        }
        String path = data.getStringExtra(CameraHelper.staticPath);
        showLoadingDialog();
        new QiNiuHelper(mActivity).uploadSinglePic(new QiNiuHelper.QiNiuCallBack() {
            @Override
            public void onSuccess(String key) {

                if (requestCode == mBinding.myMlMarryDivorce.getRequestCode()){
                    mBinding.myMlMarryDivorce.addList(key);
                }

                if (requestCode == mBinding.myMlApplyUserHkb.getRequestCode()){
                    mBinding.myMlApplyUserHkb.addList(key);
                }

                if (requestCode == mBinding.myMlBankBillPdf.getRequestCode()){
                    mBinding.myMlBankBillPdf.addList(key);
                }

                if (requestCode == mBinding.myMlSingleProvePdf.getRequestCode()){
                    mBinding.myMlSingleProvePdf.addList(key);
                }

                if (requestCode == mBinding.myMlIncomeProvePdf.getRequestCode()){
                    mBinding.myMlIncomeProvePdf.addList(key);
                }

                if (requestCode == mBinding.myMlLiveProvePdf.getRequestCode()){
                    mBinding.myMlLiveProvePdf.addList(key);
                }

                if (requestCode == mBinding.myMlHouseInvoice.getRequestCode()){
                    mBinding.myMlHouseInvoice.addList(key);
                }

                if (requestCode == mBinding.myMlBuildProvePdf.getRequestCode()){
                    mBinding.myMlBuildProvePdf.addList(key);
                }

                if (requestCode == mBinding.myMlHkbFirstPage.getRequestCode()){
                    mBinding.myMlHkbFirstPage.addList(key);
                }

                if (requestCode == mBinding.myMlHkbMainPage.getRequestCode()){
                    mBinding.myMlHkbMainPage.addList(key);
                }

                if (requestCode == mBinding.myMlGuarantor1IdNo.getRequestCode()){
                    mBinding.myMlGuarantor1IdNo.addList(key);
                }

                if (requestCode == mBinding.myMlGuarantor1Hkb.getRequestCode()){
                    mBinding.myMlGuarantor1Hkb.addList(key);
                }

                if (requestCode == mBinding.myMlGuarantor2IdNo.getRequestCode()){
                    mBinding.myMlGuarantor2IdNo.addList(key);
                }

                if (requestCode == mBinding.myMlGuarantor2Hkb.getRequestCode()){
                    mBinding.myMlGuarantor2Hkb.addList(key);
                }

                if (requestCode == mBinding.myMlGhHkb.getRequestCode()){
                    mBinding.myMlGhHkb.addList(key);
                }

                disMissLoading();

            }

            @Override
            public void onFal(String info) {
                disMissLoading();
            }
        }, path);
    }

    public Map<String, Object> getData(){

        Map<String, Object> map = new HashMap<>();

        map.put("marryDivorce",mBinding.myMlMarryDivorce.getListData());
        map.put("applyUserHkb",mBinding.myMlApplyUserHkb.getListData());
        map.put("bankBillPdf",mBinding.myMlBankBillPdf.getListData());
        map.put("singleProvePdf",mBinding.myMlSingleProvePdf.getListData());
        map.put("incomeProvePdf",mBinding.myMlIncomeProvePdf.getListData());
        map.put("liveProvePdf",mBinding.myMlLiveProvePdf.getListData());
        map.put("houseInvoice",mBinding.myMlHouseInvoice.getListData());
        map.put("buildProvePdf",mBinding.myMlBuildProvePdf.getListData());
        map.put("hkbFirstPage",mBinding.myMlHkbFirstPage.getListData());
        map.put("hkbMainPage",mBinding.myMlHkbMainPage.getListData());
        map.put("guarantor1IdNo",mBinding.myMlGuarantor1IdNo.getListData());
        map.put("guarantor1Hkb",mBinding.myMlGuarantor1Hkb.getListData());
        map.put("guarantor2IdNo",mBinding.myMlGuarantor2IdNo.getListData());
        map.put("guarantor2Hkb",mBinding.myMlGuarantor2Hkb.getListData());
        map.put("ghHkb",mBinding.myMlGhHkb.getListData());

        return map;
    }
}
