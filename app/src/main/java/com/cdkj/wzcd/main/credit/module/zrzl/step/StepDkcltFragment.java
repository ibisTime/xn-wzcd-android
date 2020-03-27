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
import com.cdkj.wzcd.databinding.FrgStepDkcltBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.SET_UPLOAD_RESULT;

/**
 * 贷款材料图
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class StepDkcltFragment extends BaseLazyFragment {

    private FrgStepDkcltBinding mBinding;

    private boolean isDetail;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static StepDkcltFragment getInstance(boolean isDetail) {
        StepDkcltFragment fragment = new StepDkcltFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_step_dkclt, null, false);

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
    }

    private void initView() {

        List<BaseImageBean> list = new ArrayList<>();
        list.add(new BaseImageBean("驾驶证", "driveCard"));
        list.add(new BaseImageBean("结婚证", "marryPdf"));
        list.add(new BaseImageBean("离婚证", "divorcePdf"));
        list.add(new BaseImageBean("单身证明", "singleProve"));
        list.add(new BaseImageBean("收入证明", "incomeProve"));
        list.add(new BaseImageBean("居住证", "liveProvePdf"));
        list.add(new BaseImageBean("产权证内容页", "housePropertyCardPdf"));
        mBinding.ilInfo.init(mActivity, list);

        mBinding.ilHk.initMultiple(mActivity, "hkBookFirstPage");
        mBinding.ilBank.initMultiple(mActivity, "bankJourFirstPage");
        mBinding.ilZfb.initMultiple(mActivity, "zfbJour");
        mBinding.ilWx.initMultiple(mActivity, "wxJour");
        mBinding.ilOther.initMultiple(mActivity, "otherPdf");

        List<BaseImageBean> signingList = new ArrayList<>();
        signingList.add(new BaseImageBean("合同签约视频", "contractAwardVideo"));
        mBinding.ilSigningVideo.initMultiple(mActivity, "contractAwardVideo");

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

                    if (bean.getKname().equals("drive_card")) {
                        mBinding.ilInfo.setData("driveCard", bean.getUrl());
                    }

                    if (bean.getKname().equals("marry_pdf")) {
                        mBinding.ilInfo.setData("marryPdf", bean.getUrl());
                    }

                    if (bean.getKname().equals("divorce_pdf")) {
                        mBinding.ilInfo.setData("divorcePdf", bean.getUrl());
                    }

                    if (bean.getKname().equals("single_prove")) {
                        mBinding.ilInfo.setData("singleProve", bean.getUrl());
                    }

                    if (bean.getKname().equals("income_prove")) {
                        mBinding.ilInfo.setData("incomeProve", bean.getUrl());
                    }

                    if (bean.getKname().equals("live_prove_pdf")) {
                        mBinding.ilInfo.setData("liveProvePdf", bean.getUrl());
                    }

                    if (bean.getKname().equals("house_property_card_pdf")) {
                        mBinding.ilInfo.setData("housePropertyCardPdf", bean.getUrl());
                    }

                    if (bean.getKname().equals("hk_book_first_page")) {
                        mBinding.ilHk.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("bank_jour_first_page")) {
                        mBinding.ilBank.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("zfb_jour")) {
                        mBinding.ilZfb.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("wx_jour")) {
                        mBinding.ilWx.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("other_pdf")) {
                        mBinding.ilOther.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("contract_award_video")) {
                        mBinding.ilSigningVideo.setData("contractAwardVideo",bean.getUrl());
                    }
                }
            }

        }


        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }
    }

    private void doRequest() {

        Map<String, String> map = BaseViewUtil.buildMap(mBinding.llInput);
        map.put("code", ((ZrzlActivity) mActivity).code);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632535", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(mActivity, "保存成功", dialogInterface -> {
                    EventBus.getDefault()
                            .post(new EventBean().setTag(SET_UPLOAD_RESULT).setValue1("5"));
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }
}
