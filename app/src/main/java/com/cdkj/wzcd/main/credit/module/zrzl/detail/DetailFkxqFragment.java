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
import com.cdkj.wzcd.databinding.FrgFkxqBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;

/**
 * 车辆图
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class DetailFkxqFragment extends BaseLazyFragment {

    private FrgFkxqBinding mBinding;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static DetailFkxqFragment getInstance() {
        DetailFkxqFragment fragment = new DetailFkxqFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_fkxq, null, false);

        initListener();

        initView();
        setView();

        return mBinding.getRoot();
    }

    private void initListener() {

    }

    private void initView() {

        BaseViewUtil.setUnFocusable(mBinding.llInput);

    }

    private void setView() {
        if (TextUtils.isEmpty(((CreditActivity) mActivity).code)
                || null == ((CreditActivity) mActivity).data) {
            return;
        }
        ZrzlBean data = ((CreditActivity) mActivity).data;

        if (data == null) {
            return;
        }

        if (data.getBankLoan() == null) {
            return;
        }

        mBinding.elBankFkDate
                .setTextByRequest(data.getBankLoan().getBankFkDate());
        mBinding.elBankFkNote.setTextByRequest(data.getBankLoan().getBankFkNote());

        mBinding.elBankCommitDatetime.setTextByRequest(
                DateUtil.formatStringData(data.getBankLoan().getBankCommitDatetime()));
        mBinding.elBankCommitNote.setTextByRequest(data.getBankLoan().getBankCommitNote());

        mBinding.elLoanNumber.setTextByRequest(data.getBankLoan().getLoanNumber());
        mBinding.elRepayBankDate
                .setTextByRequest(data.getBankLoan().getRepayBankDate() + "");
        mBinding.elRepayBillDate
                .setTextByRequest(data.getBankLoan().getRepayBillDate() + "");
        mBinding.elRepayBankcardNumber
                .setTextByRequest(data.getBankLoan().getRepayBankcardNumber());
        mBinding.elBankFkRemark
                .setTextByRequest(data.getBankLoan().getBankFkRemark());
        mBinding.elBankFkDatetime.setTextByRequest(
                DateUtil.formatStringData(data.getBankLoan().getBankFkDatetime()));
        mBinding.elReceiptRemark.setTextByRequest(data.getBankLoan().getReceiptRemark());

    }


}
