package com.cdkj.wzcd.main.credit.module.zrzl.step.dkrxx;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepZdrxxIdBinding;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxIdBean;
import org.greenrobot.eventbus.EventBus;

/**
 * @author : qianLei
 * @since : 2019/12/31 15:20
 */
public class StepDkrxxIdActivity extends AbsBaseLoadActivity {

    private FrgStepZdrxxIdBinding mBinding;

    private boolean isDetail;

    private DkrxxBean bean;

    public static void open(Context context, DkrxxBean bean, boolean isDetail) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, StepDkrxxIdActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, bean);
        intent.putExtra(CdRouteHelper.DATA_SIGN2, isDetail);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil
                .inflate(getLayoutInflater(), R.layout.frg_step_zdrxx_id, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("身份证信息");

        init();
        initListener();

        setView();


    }

    private void init() {
        bean = (DkrxxBean) getIntent().getSerializableExtra(CdRouteHelper.DATA_SIGN);
        isDetail = getIntent().getBooleanExtra(CdRouteHelper.DATA_SIGN2, true);
    }

    private void initListener() {
        mBinding.btnConfirm.setOnClickListener(view -> {
            setData();
        });
    }

    private void setView() {

        if (null != bean) {
            mBinding.elName.setText(bean.getUserName());
            mBinding.elGender.setText(bean.getGender());
            mBinding.elNation.setText(bean.getNation());
            mBinding.dlCustomerBirth.setText(bean.getCustomerBirth());
            mBinding.elAuthref.setText(bean.getAuthref());
            mBinding.elBirthAddress.setText(bean.getBirthAddress());
            mBinding.dlStartDate.setText(bean.getStartDate());
            mBinding.dlStatdate.setText(bean.getStatdate());
            mBinding.elIdNo.setText(bean.getIdNo());
        }

        if (isDetail){
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }

    }

    private void setData() {
        DkrxxIdBean bean = new DkrxxIdBean();

        bean.setUserName(mBinding.elName.getText());
        bean.setGender(mBinding.elGender.getText());
        bean.setNation(mBinding.elNation.getText());
        bean.setCustomerBirth(mBinding.dlCustomerBirth.getText());
        bean.setAuthref(mBinding.elAuthref.getText());
        bean.setBirthAddress(mBinding.elBirthAddress.getText());
        bean.setStartDate(mBinding.dlStartDate.getText());
        bean.setStatdate(mBinding.dlStatdate.getText());
        bean.setIdNo(mBinding.elIdNo.getText());

        EventBus.getDefault().post(new EventBean().setTag("dkrxx_id").setValue(bean));
        finish();
    }
}
