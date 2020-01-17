package com.cdkj.wzcd.main.credit.module.zrzl.step;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.custom.BaseDateLayout;
import com.cdkj.wzcd.custom.BaseEditLayout;
import com.cdkj.wzcd.custom.BaseSelectLayout;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepJbxxBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.bean.AscriptionBean;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import com.cdkj.wzcd.model.BanksModel;
import com.cdkj.wzcd.model.DealersModel;
import com.cdkj.wzcd.model.LocalityModel;
import com.cdkj.wzcd.model.SalesmanModel;
import com.cdkj.wzcd.util.StringStaticFinal;
import com.cdkj.wzcd.view.interfaces.MySelectInterface;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.SET_UPLOAD_RESULT;


/**
 * 基本信息
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class StepJbxxFragment extends BaseLazyFragment {

    private FrgStepJbxxBinding mBinding;

    private boolean isDetail;

    // 预填充的数据

    // 银行列表
    List<DataDictionary> bankList = new ArrayList<>();
    // 业务员列表
    List<DataDictionary> salesmanList = new ArrayList<>();
    // 业务发生地列表
    List<DataDictionary> localityList = new ArrayList<>();
    // 汽车经销商列表
    List<DataDictionary> dealersList = new ArrayList<>();

    //公司归口
    List<DataDictionary> ascriptionList = new ArrayList<>();
    private boolean isShowCreate;

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static StepJbxxFragment getInstance(boolean isDetail) {
        StepJbxxFragment fragment = new StepJbxxFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_step_jbxx, null, false);

        init();
        initListener();

        getBank();

        return mBinding.getRoot();
    }

    private void init() {
        isDetail = getArguments().getBoolean(CdRouteHelper.DATA_SIGN);
        isShowCreate = getArguments().getBoolean(CdRouteHelper.DATA_SIGN2);
    }

    private void initListener() {


        mBinding.btnConfirm.setOnClickListener(view -> {
            if (check()) {
                upload();
            }
        });
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

        if (null == data.getCarInfo()) {
            return;
        }

        mBinding.slOperator.setContentByKey(data.getSaleUserId());
        mBinding.slLoanBankCode.setContentByKey(data.getLoanBank());
        mBinding.slCompanyAscription.setContentByKey(data.getAscription());  //设置公司归口
        mBinding.slRegion.setContentByKey(data.getCarInfo().getRegion());
        EventBus.getDefault().post(new EventBean().setTag("zrzl_region")
                .setValue1(mBinding.slRegion.getDataValue()).setValue2(mBinding.slRegion.getDataKey()));
        ZrzlActivity.slRegion = data.getCarInfo().getRegion();
        mBinding.slShopCarGarage.setContentByKey(data.getCarInfo().getShopCarGarage());
        notifyRate(data.getCarInfo().getShopCarGarageRate());
//        mBinding.slCompanyAscription.setContentByKey(data.getCarInfo().getShopCarGarage());//公司归口
        mBinding.slBizType.setContentByKey(data.getBizType());
        ZrzlActivity.slBizType = data.getBizType();
        EventBus.getDefault().post(new EventBean().setTag("change_car_type").setValue1(TextUtils.equals(data.getBizType(), "0") ? "新车" : "二手车"));

    }

    /**
     * 获取银行
     */
    public void getBank() {

        Map<String, String> map = new HashMap<>();
        map.put("status", "1");

        showLoadingDialog();
        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getBanksList("632037", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseListCallBack<BanksModel>(mActivity) {
            @Override
            protected void onSuccess(List<BanksModel> data, String SucMessage) {

                for (BanksModel model : data) {

                    bankList.add(new DataDictionary().setDkey(model.getCode())
                            .setDvalue(model.getBankName() + "-" + model.getSubbranch()));

                }

            }

            @Override
            protected void onFinish() {
                getSalesman();
            }
        });
    }

    /**
     * 获取业务员
     */
    public void getSalesman() {

        Map<String, String> map = new HashMap<>();
        map.put("roleCode", "SR201800000000000000YWY");
        map.put("teamCode", SPUtilHelper.getTeamCode());

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getSalesmanList("630066", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseListCallBack<SalesmanModel>(mActivity) {
            @Override
            protected void onSuccess(List<SalesmanModel> data, String SucMessage) {

                for (SalesmanModel model : data) {

                    salesmanList.add(new DataDictionary().setDkey(model.getUserId())
                            .setDvalue(model.getRealName()));

                }


            }

            @Override
            protected void onFinish() {
                getLocality();
            }
        });
    }

    /**
     * 获取业务发生地
     */
    public void getLocality() {

        Map<String, String> map = new HashMap<>();
        map.put("start", "1");
        map.put("limit", "10000");

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getLocalityPage("630475", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<LocalityModel>>(mActivity) {

            @Override
            protected void onSuccess(ResponseInListModel<LocalityModel> data, String SucMessage) {

                for (LocalityModel model : data.getList()) {

                    localityList.add(new DataDictionary().setDkey(model.getId() + "")
                            .setDvalue(model.getProvName() + "-" + model.getCityName()));

                }

            }

            @Override
            protected void onFinish() {
                getDealers();
            }
        });
    }


    /**
     * 获取汽车经销商
     */
    public void getDealers() {

        Map<String, String> map = new HashMap<>();
        map.put("start", "1");
        map.put("limit", "10000");
        map.put("token", SPUtilHelper.getUserToken());

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getDealersPage("632065", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<DealersModel>>(mActivity) {

            @Override
            protected void onSuccess(ResponseInListModel<DealersModel> data, String SucMessage) {

                for (DealersModel model : data.getList()) {
                    dealersList.add(new DataDictionary().setDkey(model.getCode() + "")
                            .setDvalue(model.getFullName()).setTag(String.valueOf(model.getRebateRate())));

                }
            }

            @Override
            protected void onFinish() {
                getAscription();
            }
        });
    }


    /**
     * 获取公司归口
     */
    public void getAscription() {
        Map<String, String> map = new HashMap<>();
        map.put("start", "1");
        map.put("limit", "10000");

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getAscription("632905", StringUtils.getJsonToString(map));
        addCall(call);
        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<AscriptionBean>>(mActivity) {
            @Override
            protected void onSuccess(ResponseInListModel<AscriptionBean> data, String SucMessage) {
                for (AscriptionBean model : data.getList()) {
                    ascriptionList.add(new DataDictionary().setDkey(model.getId() + "")
                            .setDvalue(model.getFullName()).setTag(String.valueOf(model.getFullName())));
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
                initView();
            }
        });

    }


    private void initView() {

        mBinding.slOperator.setData(salesmanList, null);
        mBinding.slLoanBankCode.setData(bankList, null);
        mBinding.slRegion.setData(localityList, (dialog, which) -> {
            ZrzlActivity.slRegion = localityList.get(which).getDkey();
            EventBus.getDefault().post(new EventBean().setTag("zrzl_region")
                    .setValue1(mBinding.slRegion.getDataValue()).setValue2(mBinding.slRegion.getDataKey()));

        });
        // 默认选中第一个
        if (!localityList.isEmpty()) {
            mBinding.slRegion
                    .setTextAndKey(localityList.get(0).getDkey(), localityList.get(0).getDvalue());
        }
        mBinding.slShopCarGarage.setData(dealersList, new MySelectInterface() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataDictionary dataDictionary = dealersList.get(which);
                notifyRate(dataDictionary.getTag());
            }
        });
//        mBinding.slCompanyAscription.setData(dealersList);//公司归口
        mBinding.slBizType.setData("0", "新车", "1", "二手车", (dialog, which) -> {
            ZrzlActivity.slBizType = mBinding.slBizType.getDataKey();
            /**
             * 通知车辆信息界面  改为必填项或者非必填项
             */
            EventBus.getDefault().post(new EventBean().setTag("change_car_type").setValue1(which == 0 ? "新车" : "二手车"));
        });

        mBinding.slCompanyAscription.setData(ascriptionList);


        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }

        setView();
    }


    private boolean check() {

        int count = mBinding.llInput.getChildCount();

        View childView;
        for (int i = 0; i < count; i++) {
            childView = mBinding.llInput.getChildAt(i);

            if (childView instanceof BaseSelectLayout) {

                if (((BaseSelectLayout) childView).check()) {
                    return false;
                }

            }
        }

        // 是否是二手车
        if (mBinding.slBizType.getDataKey().equals("1")) {

//            if (mBinding.dlRegDate.check()) {
//                return false;
//            }
//
//            if (mBinding.elMile.check()) {
//                return false;
//            }
        }

        return true;
    }

    private Map<String, String> buildMap() {

        Map<String, String> map = new LinkedHashMap<>();

        int count = mBinding.llInput.getChildCount();

        View childView;
        for (int i = 0; i < count; i++) {
            childView = mBinding.llInput.getChildAt(i);

            if (childView instanceof BaseSelectLayout) {

                map.putAll(((BaseSelectLayout) childView).getMap());

            }

            if (childView instanceof BaseDateLayout) {

                map.putAll(((BaseDateLayout) childView).getMap());

            }

            if (childView instanceof BaseEditLayout) {

                map.putAll(((BaseEditLayout) childView).getMap());

            }

        }

        return map;

    }

    private void upload() {

        Map<String, String> map = buildMap();
        map.put("operator", SPUtilHelper.getUserId());


        if (!TextUtils.isEmpty(((ZrzlActivity) mActivity).code)) {
            map.put("code", ((ZrzlActivity) mActivity).code);
        }

        Call call = RetrofitUtils.getBaseAPiService()
                .stringRequest("632538", StringUtils.getJsonToString(map));

        showLoadingDialog();

        call.enqueue(new BaseResponseModelCallBack<String>(mActivity) {
            @Override
            protected void onSuccess(String data, String SucMessage) {

                // 通知需要交互的界面
                notifyChangeView();

                UITipDialog.showSuccess(mActivity, "保存成功", dialogInterface -> {
                    EventBus.getDefault()
                            .post(new EventBean().setTag(SET_UPLOAD_RESULT).setValue1("0")
                                    .setValue2(data));
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
                clearCall();
            }
        });

    }

    private void notifyChangeView() {
        // 只有保存以后才可以通知界面去修改

        EventBus.getDefault().post(new EventBean().setTag("zrzl_region")
                .setValue1(mBinding.slRegion.getDataValue()).setValue2(mBinding.slRegion.getDataKey()));

//                // 二手车
//                if (mBinding.slBizType.getDataKey().equals("1")) {
//                    EventBus.getDefault().post(new EventBean().setTag("zrzl_regDate")
//                            .setValue1(mBinding.dlRegDate.getText()));
//
//                    EventBus.getDefault().post(new EventBean().setTag("zrzl_mile")
//                            .setValue1(mBinding.elMile.getText()));
//                }

    }

    /**
     * 通知贷款信息设置利率
     *
     * @param value
     */
    private void notifyRate(String value) {
        EventBean eventBean = new EventBean();
        eventBean.setTag(StringStaticFinal.Default_dealer_rate);
        eventBean.setValue(value);
        EventBus.getDefault().post(eventBean);
    }
}
