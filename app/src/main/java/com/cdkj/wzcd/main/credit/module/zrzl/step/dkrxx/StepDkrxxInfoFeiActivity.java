package com.cdkj.wzcd.main.credit.module.zrzl.step.dkrxx;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepZdrxxInfoBinding;
import com.cdkj.wzcd.databinding.FrgStepZdrxxInfoFeiBinding;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxInfoBean;
import com.lljjcoder.citypickerview.widget.CityPicker;
import org.greenrobot.eventbus.EventBus;

/**
 * @author : qianLei
 * @since : 2019/12/31 16:02
 */
public class StepDkrxxInfoFeiActivity extends AbsBaseLoadActivity {

    private FrgStepZdrxxInfoFeiBinding mBinding;

    private boolean isDetail;

    private DkrxxBean bean;


    public static void open(Context context, DkrxxBean bean, boolean isDetail) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, StepDkrxxInfoFeiActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, bean);
        intent.putExtra(CdRouteHelper.DATA_SIGN2, isDetail);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil
                .inflate(getLayoutInflater(), R.layout.frg_step_zdrxx_info_fei, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("完善非主贷人信息");

        init();
        initListener();

        initView();
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

    private void initView() {

        mBinding.slPosition.setData("work_profession");
        mBinding.slRelation.setData("credit_user_relation");

    }


    private void setView() {

        if (null != bean) {
            mBinding.elCompanyName.setText(bean.getCompanyName());
            mBinding.slPosition.setContentByKey(bean.getPosition());
            mBinding.elNowAddress.setText(bean.getNowAddress());
            mBinding.elCompanyAddress.setText(bean.getCompanyAddress());
            mBinding.slRelation.setContentByKey(bean.getRelation());
        }

        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }

    }

    private void setData() {
        DkrxxInfoBean bean = new DkrxxInfoBean();

        bean.setCompanyName(mBinding.elCompanyName.getText());
        bean.setPosition(mBinding.slPosition.getDataKey());
        bean.setNowAddress(mBinding.elNowAddress.getText());
        bean.setCompanyAddress(mBinding.elCompanyAddress.getText());
        bean.setRelation(mBinding.slRelation.getDataKey());

        EventBus.getDefault().post(new EventBean().setTag("dkrxx_info").setValue(bean));
        finish();
    }

}
