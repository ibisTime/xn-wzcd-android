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
import com.cdkj.wzcd.databinding.FrgStepJjlxrBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import org.greenrobot.eventbus.EventBus;
import retrofit2.Call;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.SET_UPLOAD_RESULT;

/**
 * 紧急联系人
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class StepJjlxrFragment extends BaseLazyFragment {

    private FrgStepJjlxrBinding mBinding;

    private boolean isDetail;

    private List<DkrxxBean> list = new ArrayList<>();

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static StepJjlxrFragment getInstance(boolean isDetail) {
        StepJjlxrFragment fragment = new StepJjlxrFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_step_jjlxr, null, false);

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
            list.clear();
            doRequest();
        });
    }

    private void initView() {
        mBinding.slEmergencyRelation1.setData("credit_user_relation");
        mBinding.slEmergencyRelation2.setData("credit_user_relation");

        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }
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
        if (null == data) {
            return;
        }

        if (null == data.getCreditUser()) {
            return;
        }

        mBinding.elEmergencyName1.setText(data.getCreditUser().getEmergencyName1());
        mBinding.slEmergencyRelation1.setContentByKey(data.getCreditUser().getEmergencyRelation1());
        mBinding.elEmergencyMobile1.setText(data.getCreditUser().getEmergencyMobile1());

        mBinding.elEmergencyName2.setText(data.getCreditUser().getEmergencyName2());
        mBinding.slEmergencyRelation2.setContentByKey(data.getCreditUser().getEmergencyRelation2());
        mBinding.elEmergencyMobile2.setText(data.getCreditUser().getEmergencyMobile2());


    }

    private void doRequest() {

        DkrxxBean bean = new DkrxxBean();
        bean.setEmergencyName1(mBinding.elEmergencyName1.getText());
        bean.setEmergencyRelation1(mBinding.slEmergencyRelation1.getDataKey());
        bean.setEmergencyMobile1(mBinding.elEmergencyMobile1.getText());
        bean.setEmergencyName2(mBinding.elEmergencyName2.getText());
        bean.setEmergencyRelation2(mBinding.slEmergencyRelation2.getDataKey());
        bean.setEmergencyMobile2(mBinding.elEmergencyMobile2.getText());
        bean.setLoanRole("1");
        list.add(bean);

        HashMap<String, Object> hashMap = new LinkedHashMap<String, Object>();
        hashMap.put("code", ((ZrzlActivity) mActivity).code);
        hashMap.put("creditUserList", list);
        hashMap.put("operator", SPUtilHelper.getUserId());

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632531", StringUtils.getJsonToString(hashMap));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {
                UITipDialog.showSuccess(mActivity, "保存成功", dialogInterface -> {
                    EventBus.getDefault()
                            .post(new EventBean().setTag(SET_UPLOAD_RESULT).setValue1("1"));
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });


    }
}
