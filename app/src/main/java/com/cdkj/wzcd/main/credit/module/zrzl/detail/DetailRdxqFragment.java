package com.cdkj.wzcd.main.credit.module.zrzl.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgDzxqBinding;
import com.cdkj.wzcd.databinding.FrgRdxqBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;

/**
 * 车辆图
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class DetailRdxqFragment extends BaseLazyFragment {

    private FrgRdxqBinding mBinding;

    private ZrzlBean data;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static DetailRdxqFragment getInstance() {
        DetailRdxqFragment fragment = new DetailRdxqFragment();
        return fragment;
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_rdxq, null, false);

        initView();
        initListener();

        setView();

        return mBinding.getRoot();
    }

    private void initListener() {

        mBinding.mlAdvanceContract.setOnClickListener(view -> {
            DetailSdActivity.open(mActivity, "advance_contract", data);
        });
        mBinding.mlGuarantorContract.setOnClickListener(view -> {
            DetailSdActivity.open(mActivity, "guarantor_contract", data);
        });
        mBinding.mlNodeFileList.setOnClickListener(view -> {
            DetailSdActivity.open(mActivity, "pledge_contract", data);
        });
        mBinding.mlOther.setOnClickListener(view -> {
            DetailSdActivity.open(mActivity, "enter_other_pdf", data);
        });

    }

    private void initView() {

        BaseViewUtil.setUnFocusable(mBinding.llInput);

        if (TextUtils.isEmpty(((CreditActivity) mActivity).code)
                || null == ((CreditActivity) mActivity).data) {
            return;
        }
        data = ((CreditActivity) mActivity).data;
    }

    private void setView() {
        if (null == data) {
            return;
        }

        mBinding.elEnterCode.setTextByRequest(data.getEnterCode());
        mBinding.elEnterLocationName.setTextByRequest(data.getEnterLocationName());
        mBinding.elInsuranceCompanyName.setTextByRequest(data.getInsuranceCompanyName());
        mBinding.elSyxDateStart.setTextByRequest(DateUtil.formatStringData(data.getSyxDateStart()));
        mBinding.elSyxDateEnd.setTextByRequest(DateUtil.formatStringData(data.getSyxDateEnd()));


    }


}
