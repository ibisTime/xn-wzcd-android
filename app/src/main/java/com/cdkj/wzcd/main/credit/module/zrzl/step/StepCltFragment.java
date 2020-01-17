package com.cdkj.wzcd.main.credit.module.zrzl.step;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.custom.bean.BaseImageBean;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepCltBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.SET_UPLOAD_RESULT;
import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.UPLOAD;

/**
 * 车辆图
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class StepCltFragment extends BaseLazyFragment {

    private FrgStepCltBinding mBinding;

    private boolean isDetail;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static StepCltFragment getInstance(boolean isDetail) {
        StepCltFragment fragment = new StepCltFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean(CdRouteHelper.DATA_SIGN, isDetail);
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_step_clt, null, false);

        init();
        initListener();

        initView();
        setView();

        return mBinding.getRoot();
    }

    private void init() {
        isDetail = getArguments().getBoolean(CdRouteHelper.DATA_SIGN);
    }

    private void initListener() {
        mBinding.btnConfirm.setOnClickListener(view -> {
            doRequest();
        });

        mBinding.btnApply.setOnClickListener(view -> {
            EventBus.getDefault().post(new EventBean().setTag(UPLOAD));
        });
    }

    private void initView() {

        mBinding.ilCarHead.initMultiple(mActivity, "carHead");
        mBinding.ilCarRegisterCertificateFirst
                .initMultiple(mActivity, "carRegisterCertificateFirst");

        mBinding.ilPolicy.initMultiple(mActivity, "policy");
        List<BaseImageBean> carInvoiceList = new ArrayList<>();
        carInvoiceList.add(new BaseImageBean("发票", "carInvoice"));
        mBinding.ilCarInvoice.init(mActivity, carInvoiceList);

    }

    private ZrzlBean getData() {
        if (isDetail) {
            if (TextUtils.isEmpty(((CreditActivity) mActivity).code)
                    || null == ((CreditActivity) mActivity).data) {
                return null;
            }
            return ((CreditActivity) mActivity).data;
        } else {
            if (TextUtils.isEmpty(((ZrzlActivity) mActivity).code)
                    || null == ((ZrzlActivity) mActivity).data) {
                return null;
            }
            return ((ZrzlActivity) mActivity).data;
        }

    }

    private void setView() {

        ZrzlBean data = getData();
        if (null != data) {

            if (null != data.getAttachments()) {

                List<ZrzlBean.AttachmentsBean> attachments = data.getAttachments();

                for (ZrzlBean.AttachmentsBean bean : attachments) {

                    if (bean.getKname().equals("car_head")) {
                        mBinding.ilCarHead.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("car_register_certificate_first")) {
                        mBinding.ilCarRegisterCertificateFirst.setData(bean.getUrl());
                    }

                    if (bean.getKname().equals("policy")) {
                        mBinding.ilPolicy.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("car_invoice")) {
                        mBinding.ilCarInvoice.setData("carInvoice", bean.getUrl());
                    }

                }

            }

        }

        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
            mBinding.btnApply.setVisibility(View.GONE);
        }

    }

    private void doRequest() {

        Map<String, String> map = BaseViewUtil.buildMap(mBinding.llInput);
        map.put("code", ((ZrzlActivity) mActivity).code);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632537", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(mActivity, "保存成功", dialogInterface -> {
                    EventBus.getDefault()
                            .post(new EventBean().setTag(SET_UPLOAD_RESULT).setValue1("7"));
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }
}
