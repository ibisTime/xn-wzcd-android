package com.cdkj.wzcd.main.credit.module.zrzl.step.dkrxx;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.custom.bean.BaseImageBean;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepZdrxxPeopleBinding;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxIdBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxInfoBean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import retrofit2.Call;

import java.util.*;

/**
 * @author : qianLei
 * @since : 2019/12/31 14:28
 */
public class StepDkrxxActivity extends AbsBaseLoadActivity {

    private FrgStepZdrxxPeopleBinding mBinding;

    private boolean isDetail;

    private DkrxxBean bean;
    private int position;

    public static void open(Context context, DkrxxBean bean, int position, boolean isDetail) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, StepDkrxxActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, bean);
        intent.putExtra(CdRouteHelper.DATA_SIGN2, position);
        intent.putExtra(CdRouteHelper.DATA_SIGN3, isDetail);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil
                .inflate(getLayoutInflater(), R.layout.frg_step_zdrxx_people, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("贷款人信息");

        init();
        initListener();

        initView();
        setView();
    }

    private void init() {
        bean = (DkrxxBean) getIntent().getSerializableExtra(CdRouteHelper.DATA_SIGN);
        position = getIntent().getIntExtra(CdRouteHelper.DATA_SIGN2, 0);
        isDetail = getIntent().getBooleanExtra(CdRouteHelper.DATA_SIGN3, true);
    }

    private void initListener() {
        mBinding.btnConfirm.setOnClickListener(view -> {

            if (BaseViewUtil.check(mBinding.llInput)) {
                bean.setIdFront(mBinding.ilInfo.getDataByField("idFront"));
                bean.setIdReverse(mBinding.ilInfo.getDataByField("idReverse"));
                bean.setHoldIdCardPdf(mBinding.ilInfo.getDataByField("holdIdCardPdf"));
                bean.setMobile(mBinding.elMobile.getText());
                bean.setBankCreditResult(mBinding.slResult.getDataKey());
                bean.setBankCreditResultRemark(mBinding.elRemark.getText());

                EventBus.getDefault().post(new EventBean().setTag("dkrxx").setValue(bean)
                        .setValue1(position + ""));
                finish();
            }

        });
    }

    private void initView() {

        mBinding.slOperator.setTextByRequest(bean.getLoanRoleName());
        mBinding.mlInfo.setTitle("完善" + bean.getLoanRoleName() + "信息");

        List<BaseImageBean> list = new ArrayList<>();
        list.add(new BaseImageBean("身份证正面", "idFront"));
        list.add(new BaseImageBean("身份证反面", "idReverse"));
        list.add(new BaseImageBean("人证照片", "holdIdCardPdf"));
        mBinding.ilInfo.init(this, list);
        mBinding.ilInfo.setImageInterface((location, field, key) -> {

            if (field.equals("idFront")) {
                idRecognition(key, "630092");
            }

            if (field.equals("idReverse")) {
                idRecognition(key, "630093");
            }

        });

        mBinding.mlId.setOnClickListener(view -> {
            StepDkrxxIdActivity.open(this, bean, isDetail);
        });

        mBinding.slResult.setData("0", "不通过", "1", "通过", (dialog, which) -> {

        });

        mBinding.mlInfo.setOnClickListener(view -> {
            if (bean.getLoanRole().equals("1")) {
                StepDkrxxInfoActivity.open(this, bean, isDetail);
            } else {
                StepDkrxxInfoFeiActivity.open(this, bean, isDetail);
            }

        });

    }

    private void setView() {
        if (null != bean) {
            List<String> pic = new ArrayList<>();
            pic.add(bean.getIdFront());
            pic.add(bean.getIdReverse());
            pic.add(bean.getHoldIdCardPdf());
            mBinding.ilInfo.setData(pic);

            mBinding.elMobile.setText(bean.getMobile());
            mBinding.slResult.setContentByKey(bean.getBankCreditResult());
            mBinding.elRemark.setText(bean.getBankCreditResultRemark());
        }

        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }

    }

    private void idRecognition(String url, String code) {

        Map<String, String> map = new HashMap<>();

        map.put("picUrl", MyCdConfig.QINIU_URL + url);

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .idRecognition(code, StringUtils.getJsonToString(map));

        addCall(call);

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<DkrxxIdBean>(this) {

            @Override
            protected void onSuccess(DkrxxIdBean data, String SucMessage) {

                if (TextUtils.equals(code, "630092")) {

                    bean.setBirthAddress(data.getBirthAddress());
                    bean.setCustomerBirth(data.getCustomerBirth());
                    bean.setUserName(data.getUserName());
                    bean.setNation(data.getNation());
                    bean.setIdNo(data.getIdNo());
                    bean.setGender(data.getGender());

                } else {

                    bean.setStartDate(data.getStartDate());
                    bean.setStatdate(data.getStatdate());
                    bean.setAuthref(data.getAuthref());

                }

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Subscribe
    public void dkrxxId(EventBean eventBean) {

        if (eventBean.getTag().equals("dkrxx_id")) {

            DkrxxIdBean data = (DkrxxIdBean) eventBean.getValue();

            bean.setBirthAddress(data.getBirthAddress());
            bean.setCustomerBirth(data.getCustomerBirth());
            bean.setUserName(data.getUserName());
            bean.setNation(data.getNation());
            bean.setIdNo(data.getIdNo());
            bean.setGender(data.getGender());
            bean.setStartDate(data.getStartDate());
            bean.setStatdate(data.getStatdate());
            bean.setAuthref(data.getAuthref());
        }

    }

    @Subscribe
    public void dkrxxInfo(EventBean eventBean) {

        if (eventBean.getTag().equals("dkrxx_info")) {

            DkrxxInfoBean data = (DkrxxInfoBean) eventBean.getValue();

            bean.setEducation(data.getEducation());
            bean.setNowAddressProvince(data.getNowAddressProvince());
            bean.setNowAddressCity(data.getNowAddressCity());
            bean.setNowAddressArea(data.getNowAddressArea());
            bean.setNowAddress(data.getNowAddress());
            bean.setNowAddressMobile(data.getNowAddressMobile());
            bean.setNowAddressDate(data.getNowAddressDate());
            bean.setNowAddressState(data.getNowAddressState());
            bean.setMarryState(data.getMarryState());
            bean.setNowHouseType(data.getNowHouseType());
            bean.setCompanyName(data.getCompanyName());
            bean.setCompanyProvince(data.getCompanyProvince());
            bean.setCompanyCity(data.getCompanyCity());
            bean.setCompanyArea(data.getCompanyArea());
            bean.setCompanyAddress(data.getCompanyAddress());
            bean.setWorkCompanyProperty(data.getWorkCompanyProperty());
            bean.setWorkDatetime(data.getWorkDatetime());
            bean.setPosition(data.getPosition());
            bean.setRelation(data.getRelation());
            bean.setYearIncome(data.getYearIncome());
            bean.setPresentJobYears(data.getPresentJobYears());
            bean.setPermanentType(data.getPermanentType());
        }

    }


}
