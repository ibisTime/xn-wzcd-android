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
import com.cdkj.wzcd.databinding.FrgLjxqBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;

/**
 * 车辆图
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class DetailLjxqFragment extends BaseLazyFragment {

    private FrgLjxqBinding mBinding;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static DetailLjxqFragment getInstance() {
        DetailLjxqFragment fragment = new DetailLjxqFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_ljxq, null, false);

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


        mBinding.elRationaleDatetime.setText(DateUtil.formatStringData(data.getRationaleDatetime()));
        mBinding.elRationaleNote.setText(data.getRationaleNote());

        mBinding.elHitPieceDatetime.setText(DateUtil.formatStringData(data.getHitPieceDatetime()));
        mBinding.elHitPieceNote.setText(data.getHitPieceNote());

    }


}
