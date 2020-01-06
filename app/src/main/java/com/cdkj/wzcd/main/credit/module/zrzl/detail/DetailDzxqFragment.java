package com.cdkj.wzcd.main.credit.module.zrzl.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgDzxqBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.yksq.bean.TeamBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import retrofit2.Call;

import java.util.Map;

/**
 * 车辆图
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class DetailDzxqFragment extends BaseLazyFragment {

    private FrgDzxqBinding mBinding;

    private ZrzlBean bean;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static DetailDzxqFragment getInstance() {
        DetailDzxqFragment fragment = new DetailDzxqFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_dzxq, null, false);

        initView();
        initListener();

        setView();

        return mBinding.getRoot();
    }

    private void initListener() {

        mBinding.mlSd.setOnClickListener(view -> {
            DetailSdActivity.open(mActivity, "advance_bill_pdf", bean);
        });

    }

    private void initView() {

        BaseViewUtil.setUnFocusable(mBinding.llInput);

        if (TextUtils.isEmpty(((CreditActivity) mActivity).code)
                || null == ((CreditActivity) mActivity).data) {
            return;
        }
        bean = ((CreditActivity) mActivity).data;

    }

    private void setView() {

        if (bean == null) {
            return;
        }

        getTeam();

        if (bean.getAdvance() == null) {
            return;
        }

        if (bean.getAdvance().getAdvanceOutCard() != null) {
            mBinding.elRealName
                    .setTextByRequest(bean.getAdvance().getAdvanceOutCard().getRealName());
            mBinding.elBankcardNumber
                    .setTextByRequest(bean.getAdvance().getAdvanceOutCard().getBankcardNumber());
        }

        mBinding.elAdvanceFundDatetime.setTextByRequest(
                DateUtil.formatStringData(bean.getAdvance().getAdvanceFundDatetime()));
        mBinding.elAdvanceFundAmount
                .setTextByRequest(bean.getAdvance().getAdvanceFundAmount());
        mBinding.rlMakeBillNote.setTextByRequest(bean.getAdvance().getMakeBillNote());

    }

    private void getTeam() {

        Map<String, Object> map = RetrofitUtils.getRequestMap();
        map.put("code", bean.getTeamCode());

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getTeam("630196", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<TeamBean>(mActivity) {
            @Override
            protected void onSuccess(TeamBean data, String SucMessage) {

                setView(data);
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void setView(TeamBean data) {

        mBinding.elTeam.setTextByRequest(data.getAccountNo());
        mBinding.elBank.setTextByRequest(data.getBankName());
        mBinding.elSub.setTextByRequest(data.getSubbranch());
        mBinding.elInfo.setTextByRequest(
                data.getName() + " " + MoneyUtils.showPrice(bean.getRepointAmount()) + "(车款2)");

    }

}
