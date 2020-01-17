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
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepSmdctBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;

import org.greenrobot.eventbus.EventBus;

import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.SET_UPLOAD_RESULT;

/**
 * 上门调查图
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class StepSmdctFragment extends BaseLazyFragment {

    private FrgStepSmdctBinding mBinding;

    private boolean isDetail;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static StepSmdctFragment getInstance(boolean isDetail) {
        StepSmdctFragment fragment = new StepSmdctFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_step_smdct, null, false);

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

        mBinding.ilDoorPdf.initMultiple(mActivity, "doorPdf");
        mBinding.ilGroupPhoto.initMultiple(mActivity, "groupPhoto");

//        List<BaseImageBean> list = new ArrayList<>();
//        list.add(new BaseImageBean("家访视频", "houseVideo"));
        mBinding.ilHouseVideo.initMultiple(mActivity, "houseVideo");

//        List<BaseImageBean> companyList = new ArrayList<>();
//        companyList.add(new BaseImageBean("公司视频", "companyVideo"));
        mBinding.ilCompanyVideo.initMultiple(mActivity, "companyVideo");

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

                    if (bean.getKname().equals("house_video")) {
//                        mBinding.ilHouseVideo.setData("houseVideo", bean.getUrl());
                        mBinding.ilHouseVideo.setData( bean.getUrl());
                    }

                    if (bean.getKname().equals("door_photo")) {
                        mBinding.ilDoorPdf.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("group_photo")) {
                        mBinding.ilGroupPhoto.setData(bean.getUrl());
                    }
                    if (bean.getKname().equals("company_video")) {
//                        mBinding.ilCompanyVideo.setData("companyVideo",bean.getUrl());
                        mBinding.ilCompanyVideo.setData(bean.getUrl());
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
                .successRequest("632536", StringUtils.getJsonToString(map));

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
