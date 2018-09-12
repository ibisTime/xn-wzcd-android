package com.cdkj.wzcd.module.work.join_approval.page;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.databinding.FragmentJoinStep4Binding;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.model.event.BudgetCheckModel;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;

/**
 * Created by cdkj on 2018/6/7.
 */

public class JoinStep4Fragment extends BaseLazyFragment {

    private String code;
    private FragmentJoinStep4Binding mBinding;

    private NodeListModel data;
    private CityPicker cityPicker;
    private String mProvince;
    private String mCity;
    private String mDistrict;
    private String applyBirthAddressProvince;
    private String applyBirthAddressCity;
    private String applyBirthAddressArea;
    private String applyNowAddressProvince;
    private String applyNowAddressCity;
    private String applyNowAddressArea;

    public static JoinStep4Fragment getInstance(String code) {
        JoinStep4Fragment fragment = new JoinStep4Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, code);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_join_step4, null, false);

        if (getArguments() != null) {
            code = getArguments().getString(DATA_SIGN);
        }

        data = ((JoinApplyActivity) mActivity).mData;

        initOnclick();
        initDefalutAddressInfo();
        initView();
        setView();

        return mBinding.getRoot();
    }

    private void initOnclick() {


        mBinding.llApplyBirthAddress.setOnClickListener(view -> {
            if (cityPicker == null) {
                initCityPicker();
            }
            cityPicker.show();

            //监听方法，获取选择结果
            cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
                @Override
                public void onSelected(String... citySelected) {
                    //省份
                    mProvince = citySelected[0];
                    applyBirthAddressProvince = mProvince;
                    //城市
                    mCity = citySelected[1];
                    applyBirthAddressCity = mCity;
                    //区县（如果设定了两级联动，那么该项返回空）
                    mDistrict = citySelected[2];
                    applyBirthAddressArea = mDistrict;
                    //邮编
                    String code = citySelected[3];

                    mBinding.tvApplyBirthAddress.setText(mProvince + " " + mCity + " " + mDistrict);
                }

                @Override
                public void onCancel() {

                }
            });
        });

        mBinding.llApplyNowAddress.setOnClickListener(view -> {
            if (cityPicker == null) {
                initCityPicker();
            }
            cityPicker.show();

            //监听方法，获取选择结果
            cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {

                @Override
                public void onSelected(String... citySelected) {
                    //省份
                    mProvince = citySelected[0];
                    applyNowAddressProvince = mProvince;
                    //城市
                    mCity = citySelected[1];
                    applyNowAddressCity = mCity;
                    //区县（如果设定了两级联动，那么该项返回空）
                    mDistrict = citySelected[2];
                    applyNowAddressArea = mDistrict;
                    //邮编
                    String code = citySelected[3];

                    mBinding.tvApplyNowAddress.setText(mProvince + " " + mCity + " " + mDistrict);
                }

                @Override
                public void onCancel() {

                }
            });
        });
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    private void initView() {

        mBinding.mySlHouseType.setData("0", "自有", "1", "租用", null);

    }

    private void setView() {
//将之前的地址
        applyBirthAddressArea=data.getApplyBirthAddressArea();
        applyBirthAddressCity=data.getApplyBirthAddressCity();
        applyBirthAddressProvince=data.getApplyBirthAddressProvince();
        applyNowAddressArea=data.getApplyNowAddressArea();
        applyNowAddressCity=data.getApplyNowAddressCity();
        applyNowAddressProvince=data.getApplyNowAddressProvince();

        if (((JoinApplyActivity) mActivity).isDetails) {

            mBinding.llApplyBirthAddress.setOnClickListener(null);
            if (!TextUtils.isEmpty(data.getApplyBirthAddressProvince())) {

                mBinding.tvApplyBirthAddress.setText(data.getApplyBirthAddressProvince() + " " + data.getApplyBirthAddressCity() + " " + data.getApplyBirthAddressArea());
            }
            mBinding.llApplyNowAddress.setOnClickListener(null);
            if (!TextUtils.isEmpty(data.getApplyNowAddressProvince())) {

                mBinding.tvApplyNowAddress.setText(data.getApplyNowAddressProvince() + " " + data.getApplyNowAddressCity() + " " + data.getApplyNowAddressArea());
            }

            mBinding.myElApplyBirthAddress.setTextByRequest(data.getApplyBirthAddress());
            mBinding.myElApplyNowAddress.setTextByRequest(data.getApplyNowAddress());

            mBinding.mySlHouseType.setTextByRequestByKey(data.getHouseType());

            mBinding.myElGhBirthAddress.setTextByRequest(data.getGhBirthAddress());
            mBinding.myElGuarantor1BirthAddress.setTextByRequest(data.getGuarantor1BirthAddress());
            mBinding.myElGuarantor2BirthAddress.setTextByRequest(data.getGuarantor2BirthAddress());
            mBinding.myElOtherNote.setTextByRequest(data.getOtherNote());

        } else {

            if (!TextUtils.isEmpty(data.getApplyBirthAddressProvince())) {

                mBinding.tvApplyBirthAddress.setText(data.getApplyBirthAddressProvince() + " " + data.getApplyBirthAddressCity() + " " + data.getApplyBirthAddressArea());
            }
            if (!TextUtils.isEmpty(data.getApplyNowAddressProvince())) {

                mBinding.tvApplyNowAddress.setText(data.getApplyNowAddressProvince() + " " + data.getApplyNowAddressCity() + " " + data.getApplyNowAddressArea());
            }

            mBinding.myElApplyBirthAddress.setText(data.getApplyBirthAddress());
            mBinding.myElApplyNowAddress.setText(data.getApplyNowAddress());

            mBinding.mySlHouseType.setContentByKey(data.getHouseType());

            mBinding.myElGhBirthAddress.setText(data.getGhBirthAddress());
            mBinding.myElGuarantor1BirthAddress.setText(data.getGuarantor1BirthAddress());
            mBinding.myElGuarantor2BirthAddress.setText(data.getGuarantor2BirthAddress());
            mBinding.myElOtherNote.setText(data.getOtherNote());
        }
    }

    private String checkFail;

    public boolean check() {
        if (mBinding.myElApplyBirthAddress.check()) {
            checkFail = mBinding.myElApplyBirthAddress.getTitle();
            return false;
        }

        if (mBinding.myElApplyNowAddress.check()) {
            checkFail = mBinding.myElApplyNowAddress.getTitle();
            return false;
        }

        if (mBinding.mySlHouseType.check()) {
            checkFail = mBinding.mySlHouseType.getTitle();
            return false;
        }
        if (TextUtils.isEmpty(mBinding.tvApplyBirthAddress.getText())) {
            checkFail = "请输入申请人户籍地";
            return false;
        }
        if (TextUtils.isEmpty(mBinding.tvApplyNowAddress.getText())) {
            checkFail = "请输入申请现住地址";
            return false;
        }

//        if (mBinding.myElGhBirthAddress.check()){
//            checkFail = mBinding.myElGhBirthAddress.getTitle();
//            return false;
//        }

//        if (mBinding.myElGuarantor1BirthAddress.check()){
//            checkFail = mBinding.myElGuarantor1BirthAddress.getTitle();
//            return false;
//        }

//        if (mBinding.myElGuarantor2BirthAddress.check()){
//            checkFail = mBinding.myElGuarantor2BirthAddress.getTitle();
//            return false;
//        }

        return true;
    }

    @Subscribe
    public void doCheck(BudgetCheckModel model) {

        if (model == null)
            return;

        // 检查未通过，由Activity提示，不往下check
        if (!model.isCheckResult()) {
            return;
        }

        if (model.getCheckIndex() == 4) {
            if (check()) {

                EventBus.getDefault().post(new BudgetCheckModel()
                        .setCheckIndex(model.getCheckIndex() + 1)
                        .setCheckResult(true)
                        .setCheckFail(null));

            } else {

                EventBus.getDefault().post(new BudgetCheckModel()
                        .setCheckIndex(model.getCheckIndex())
                        .setCheckResult(false)
                        .setCheckFail(checkFail));

            }
        }
    }

    public Map<String, Object> getData() {

        Map<String, Object> map = new HashMap<>();


        map.put("applyBirthAddressArea", applyBirthAddressArea);
        map.put("applyBirthAddressCity", applyBirthAddressCity);
        map.put("applyBirthAddressProvince", applyBirthAddressProvince);

        map.put("applyNowAddressArea", applyNowAddressArea);
        map.put("applyNowAddressCity", applyNowAddressCity);
        map.put("applyNowAddressProvince", applyNowAddressProvince);


        map.put("applyBirthAddress", mBinding.myElApplyBirthAddress.getText());
        map.put("applyNowAddress", mBinding.myElApplyNowAddress.getText());
        map.put("houseType", mBinding.mySlHouseType.getDataKey());
        map.put("ghBirthAddress", mBinding.myElGhBirthAddress.getText());
        map.put("guarantor1BirthAddress", mBinding.myElGuarantor1BirthAddress.getText());
        map.put("guarantor2BirthAddress", mBinding.myElGuarantor2BirthAddress.getText());

        map.put("otherNote", mBinding.myElOtherNote.getText());

        return map;
    }


    private void initCityPicker() {

        cityPicker = new CityPicker.Builder(mActivity)
                .textSize(18)
                .titleBackgroundColor("#ffffff")
                .titleTextColor("#ffffff")
                .backgroundPop(0xa0000000)
                .confirTextColor("#48b0fb")
                .cancelTextColor("#48b0fb")
                .province(mProvince)
                .city(mCity)
                .district(mDistrict)
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
    }

    /**
     * 初始化选择框默认地址
     */
    public void initDefalutAddressInfo() {

        if (!TextUtils.isEmpty(mProvince) && !TextUtils.isEmpty(mCity) && !TextUtils.isEmpty(mDistrict)) {

        } else {
            mProvince = getString(com.cdkj.baselibrary.R.string.select_province_def);
            mCity = getString(com.cdkj.baselibrary.R.string.select_city_def);
            mDistrict = getString(com.cdkj.baselibrary.R.string.select_district_def);
        }
    }

}
