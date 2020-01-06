package com.cdkj.wzcd.main.credit.module;

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
import com.cdkj.wzcd.databinding.FrgCreditDetailBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import com.cdkj.wzcd.util.NodeHelper;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.baselibrary.utils.DateUtil.DEFAULT_DATE_FMT;

/**
 * @author : qianLei
 * @since : 2019/12/30 16:00
 */
public class CreditPledgeDetailFragment extends BaseLazyFragment {

    private FrgCreditDetailBinding mBinding;

    private ZrzlBean bean;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static CreditPledgeDetailFragment getInstance(ZrzlBean bean) {
        CreditPledgeDetailFragment fragment = new CreditPledgeDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA_SIGN, bean);
        fragment.setArguments(bundle);
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_credit_detail, null, false);

        init();
        initListener();

        setView();

        return mBinding.getRoot();
    }

    private void init() {

        bean = (ZrzlBean) getArguments().getSerializable(DATA_SIGN);

    }

    private void initListener() {
        mBinding.blCode.setRightListener(view -> {
            CreditActivity.open(mActivity, bean.getCode());
        });
    }

    private void setView() {

        if (null == bean) {
            return;
        }

        mBinding.blCode.setText(bean.getCode());
        mBinding.blName.setText(bean.getCreditUser().getUserName());
        mBinding.blBank.setText(bean.getLoanBankName());
        mBinding.blBizType.setText(bean.getBizType().equals("0") ? "新车" : "二手车");
        mBinding.blAmount.setMoneyText(bean.getLoanAmount());
        mBinding.blSaleMan.setText(bean.getSaleUserName() + "(" + bean.getTeamName() + ")");

        mBinding.blNode.setText(NodeHelper.getNameOnTheCode(bean.getPledgeNodeCode()));
        mBinding.blStart
                .setText(DateUtil.formatStringData(bean.getApplyDatetime(), DEFAULT_DATE_FMT));
    }
}
