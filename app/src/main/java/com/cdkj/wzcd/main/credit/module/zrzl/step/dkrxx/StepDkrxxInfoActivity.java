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
import com.cdkj.baselibrary.utils.DateUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepZdrxxInfoBinding;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxInfoBean;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

/**
 * @author : qianLei
 * @since : 2019/12/31 16:02
 */
public class StepDkrxxInfoActivity extends AbsBaseLoadActivity {

    private FrgStepZdrxxInfoBinding mBinding;

    private boolean isDetail;

    private DkrxxBean bean;

    private CityPicker cityPicker;
    private String nowAddressProvince;
    private String nowAddressCity;
    private String nowAddressArea;

    private String companyProvince;
    private String companyCity;
    private String companyArea;

    public static void open(Context context, DkrxxBean bean, boolean isDetail) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, StepDkrxxInfoActivity.class);
        intent.putExtra(CdRouteHelper.DATA_SIGN, bean);
        intent.putExtra(CdRouteHelper.DATA_SIGN2, isDetail);
        context.startActivity(intent);
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil
                .inflate(getLayoutInflater(), R.layout.frg_step_zdrxx_info, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {

        mBaseBinding.titleView.setMidTitle("完善主贷人信息");

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
            if (BaseViewUtil.check(mBinding.llInput)) {
                setData();
            }
        });

        mBinding.dlWorkDatetime.setInterface(dateTime -> {
            String currentDate = DateUtil.format(new Date(), "yyyy-M");
            long dateTimeDifference = DateUtil.getDateTimeDifference(currentDate, dateTime, "yyyy-M");
            double yearNumber = DateUtil.switchYear(dateTimeDifference);
            //不满一年不算
            mBinding.elPresentJobYears.setText((int) yearNumber + "");

        });
    }

    private void initView() {

        mBinding.slEducation.setData("education");

        mBinding.slNowAddressPCA.setListener(view -> {
            if (cityPicker == null) {
                initCityPicker();
            }
            cityPicker.show();

            //监听方法，获取选择结果
            cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                @Override
                public void onSelected(String... citySelected) {
                    //省份
                    nowAddressProvince = citySelected[0];
                    //城市
                    nowAddressCity = citySelected[1];
                    //区县
                    nowAddressArea = citySelected[2];

                    mBinding.slNowAddressPCA.setText(
                            nowAddressProvince + " " + nowAddressCity + " " + nowAddressArea);
                }

                @Override
                public void onCancel() {

                }
            });
        });

        mBinding.slNowAddressState.setData("now_address_state");

        mBinding.slMarryState.setData("marry_state");

        mBinding.slNowHouseType.setData("0", "自由", "1", "租用");
//        mBinding.slNowHouseType.setData("now_address_state");

        mBinding.slCompanyPCA.setListener(view -> {
            if (cityPicker == null) {
                initCityPicker();
            }
            cityPicker.show();

            //监听方法，获取选择结果
            cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                @Override
                public void onSelected(String... citySelected) {
                    //省份
                    companyProvince = citySelected[0];
                    //城市
                    companyCity = citySelected[1];
                    //区县
                    companyArea = citySelected[2];

                    mBinding.slCompanyPCA.setText(
                            companyProvince + " " + companyCity + " " + companyArea);
                }

                @Override
                public void onCancel() {

                }
            });
        });

        mBinding.slWorkCompanyProperty.setData("work_company_property");

        mBinding.slPosition.setData("work_profession");

        mBinding.slPermanentType.setData("permanent_type");
    }

    private void initCityPicker() {

        cityPicker = new CityPicker.Builder(this)
                .textSize(18)
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#ffffff")
                .backgroundPop(0xa0000000)
                .confirTextColor("#48b0fb")
                .cancelTextColor("#48b0fb")
//                .province(mProvince)
//                .city(mCity)
//                .district(mArea)
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
    }

    private void setView() {

        if (null != bean) {
            mBinding.slEducation.setContentByKey(bean.getEducation());
            nowAddressProvince = bean.getNowAddressProvince();
            nowAddressCity = bean.getNowAddressCity();
            nowAddressArea = bean.getNowAddressArea();
            if (!TextUtils.isEmpty(nowAddressProvince)) {
                mBinding.slNowAddressPCA.setText(
                        nowAddressProvince + " " + nowAddressCity + " " + nowAddressArea);
            }

            mBinding.nowAddress.setText(bean.getNowAddress());
            mBinding.elNowAddressMobile.setText(bean.getNowAddressMobile());
            mBinding.dlNowAddressDate.setText(bean.getNowAddressDate());
            mBinding.slNowAddressState.setContentByKey(bean.getNowAddressState());
            mBinding.slMarryState.setContentByKey(bean.getMarryState());
            mBinding.slNowHouseType.setContentByKey(bean.getNowHouseType());
            mBinding.elCompanyName.setText(bean.getCompanyName());
            companyProvince = bean.getCompanyProvince();
            companyCity = bean.getCompanyCity();
            companyArea = bean.getCompanyArea();
            if (!TextUtils.isEmpty(companyProvince)) {
                mBinding.slCompanyPCA.setText(
                        companyProvince + " " + companyCity + " " + companyArea);
            }

            mBinding.slCompanyAddress.setText(bean.getCompanyAddress());
            mBinding.slWorkCompanyProperty.setContentByKey(bean.getWorkCompanyProperty());
            mBinding.dlWorkDatetime.setText(bean.getWorkDatetime());
            mBinding.slPosition.setContentByKey(bean.getPosition());
            mBinding.elYearIncome.setText(bean.getYearIncome());
            mBinding.elPresentJobYears.setText(bean.getPresentJobYears());
            mBinding.slPermanentType.setContentByKey(bean.getPermanentType());
        }


        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }
    }

    private void setData() {
        DkrxxInfoBean bean = new DkrxxInfoBean();

        bean.setEducation(mBinding.slEducation.getDataKey());
        bean.setNowAddressProvince(nowAddressProvince);
        bean.setNowAddressCity(nowAddressCity);
        bean.setNowAddressArea(nowAddressArea);
        bean.setNowAddress(mBinding.nowAddress.getText());
        bean.setNowAddressMobile(mBinding.elNowAddressMobile.getText());
        bean.setNowAddressDate(mBinding.dlNowAddressDate.getText());
        bean.setNowAddressState(mBinding.slNowAddressState.getDataKey());
        bean.setMarryState(mBinding.slMarryState.getDataKey());
        bean.setNowHouseType(mBinding.slNowHouseType.getDataKey());
        bean.setCompanyName(mBinding.elCompanyName.getText());
        bean.setCompanyProvince(companyProvince);
        bean.setCompanyCity(companyCity);
        bean.setCompanyArea(companyArea);
        bean.setCompanyAddress(mBinding.slCompanyAddress.getText());
        bean.setWorkCompanyProperty(mBinding.slWorkCompanyProperty.getDataKey());
        bean.setWorkDatetime(mBinding.dlWorkDatetime.getText());
        bean.setPosition(mBinding.slPosition.getDataKey());
        bean.setYearIncome(mBinding.elYearIncome.getText());
        bean.setPresentJobYears(mBinding.elPresentJobYears.getText());
        bean.setPermanentType(mBinding.slPermanentType.getDataKey());

        EventBus.getDefault().post(new EventBean().setTag("dkrxx_info").setValue(bean));
        finish();
    }

}
