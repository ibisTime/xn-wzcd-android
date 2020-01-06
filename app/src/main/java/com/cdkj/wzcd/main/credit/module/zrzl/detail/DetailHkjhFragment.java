package com.cdkj.wzcd.main.credit.module.zrzl.detail;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgDzxqBinding;
import com.cdkj.wzcd.databinding.FrgRepaymentBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.adapter.HkjhAdapter;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.HkjhBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import retrofit2.Call;

import java.util.Map;

/**
 * 车辆图
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class DetailHkjhFragment extends BaseLazyFragment {

    private FrgRepaymentBinding mBinding;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static DetailHkjhFragment getInstance() {
        DetailHkjhFragment fragment = new DetailHkjhFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_repayment, null, false);

        initListener();

        initView();
        getHkjh();

        return mBinding.getRoot();
    }

    private void initListener() {

    }

    private void initView() {

    }


    private void getHkjh() {

        if (TextUtils.isEmpty(((CreditActivity) mActivity).code)) {
            return;
        }

        Map<String, Object> map = RetrofitUtils.getRequestMap();
        map.put("code", ((CreditActivity) mActivity).code);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getHkjh("630521", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<HkjhBean>(mActivity) {
            @Override
            protected void onSuccess(HkjhBean data, String SucMessage) {

                mBinding.tvAmount.setText(MoneyUtils.showPrice(data.getRestAmount()));

                HkjhAdapter mAdapter = new HkjhAdapter(data.getRepayPlanList());
                mBinding.rv.setAdapter(mAdapter);
                mBinding.rv.setLayoutManager(
                        new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false));

            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {

                if (errorMessage.contains("还款业务编号")){

                }else {
                    ToastUtil.show(mActivity, errorMessage);
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }


}
