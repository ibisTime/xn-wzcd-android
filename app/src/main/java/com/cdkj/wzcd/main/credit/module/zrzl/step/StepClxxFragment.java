package com.cdkj.wzcd.main.credit.module.zrzl.step;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.baselibrary.model.eventmodels.EventBean;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.baselibrary.utils.ToastUtil;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.common.SearchActivity;
import com.cdkj.wzcd.custom.bean.BaseImageBean;
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepClxxBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.bean.CarVINDetailBean;
import com.cdkj.wzcd.main.credit.module.zrzl.CarBrandActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.adapter.GPSAdapter;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CarModelBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CarSeriesBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.GPSBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlReportBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity.SET_UPLOAD_RESULT;

/**
 * 车辆信息
 *
 * @author : qianLei
 * @since : 2019/12/26 15:19
 */
public class StepClxxFragment extends BaseLazyFragment {

    private FrgStepClxxBinding mBinding;

    private boolean isDetail;

    private GPSAdapter mAdapter;
    private List<GPSBean> list = new ArrayList<>();
    private List<DataDictionary> selectList = new ArrayList<>();


    // 汽车品牌
    String carBrand;
    // 汽车车系
    String carSeries;
    List<DataDictionary> carSeriesList = new ArrayList<>();
    // 汽车车型
    List<DataDictionary> carModelList = new ArrayList<>();

    /**
     * 获得fragment实例
     *
     * @return
     */
    public static StepClxxFragment getInstance(boolean isDetail) {
        StepClxxFragment fragment = new StepClxxFragment();
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

        mBinding = DataBindingUtil.inflate(inflater, R.layout.frg_step_clxx, null, false);

        init();
        initListener();

        initView();
        initAdapter();

        setView();

        return mBinding.getRoot();
    }

    private void init() {
        isDetail = getArguments().getBoolean(CdRouteHelper.DATA_SIGN);
    }

    private void initListener() {

        mBinding.tvRegenerate.setOnClickListener(view -> {
            if (mBinding.slCarModel.check()) {
                return;
            }

            if (mBinding.dlRegDate.check()) {
                return;
            }

            if (mBinding.elMile.check()) {
                return;
            }

            if (TextUtils.isEmpty(ZrzlActivity.slRegion)) {
                ToastUtil.show(mActivity, "请填写业务发生地");
                return;
            }
            new AlertDialog.Builder(getContext())
                    .setTitle("温馨提示")
                    .setMessage("确定重新生成报告")
                    .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            getReport();
                        }
                    })
                    .setPositiveButton("取消", null)
                    .show();

        });

        mBinding.tvReport.setOnClickListener(view -> {

            if (!mBinding.tvReport.getText().equals("点击生成评报告")) {

                if (TextUtils.isEmpty(mBinding.tvReport.getText())) {
                    return;
                }

                CdRouteHelper.openWebViewActivityForUrl("评估报告",
                        mBinding.tvReport.getText().toString());
            } else {
                if (mBinding.slCarModel.check()) {
                    return;
                }

                if (mBinding.dlRegDate.check()) {
                    return;
                }

                if (mBinding.elMile.check()) {
                    return;
                }

                if (TextUtils.isEmpty(ZrzlActivity.slRegion)) {
                    ToastUtil.show(mActivity, "请填写业务发生地");
                    return;
                }


                // 是否是二手车
                if (TextUtils.equals(ZrzlActivity.slBizType, "1")) {

                    if (mBinding.dlRegDate.check()) {
                        return;
                    }

                    if (mBinding.elMile.check()) {
                        return;
                    }
                }

                getReport();
            }
        });


        mBinding.llAddGps.setOnClickListener(view -> {

            if (TextUtils.isEmpty(((ZrzlActivity) mActivity).code)
                    || null == ((ZrzlActivity) mActivity).data) {
                return;
            }
            ZrzlBean data = ((ZrzlActivity) mActivity).data;

            GPSBean gpsBean = new GPSBean();
            gpsBean.setApplyUserId(data.getSaleUserId());
            list.add(gpsBean);

            mAdapter.notifyDataSetChanged();

        });

        mBinding.btnConfirm.setOnClickListener(view -> {

            if ("1".equals(mBinding.slIsAzGps.getDataKey())) {

                if (list.size() == 0) {
                    ToastUtil.show(mActivity, "请添加GPS");
                    return;
                }

                for (GPSBean bean : list) {

                    if (TextUtils.isEmpty(bean.getCode())) {
                        ToastUtil.show(mActivity, "请选择GPS");
                        return;
                    }

                    if (TextUtils.isEmpty(bean.getAzPhotos())) {
                        ToastUtil.show(mActivity, "请添加GPS图片");
                        return;
                    }

                }

            }
            doRequest();

        });

    }

    private void initView() {

        if (isDetail) {
            mBinding.tvRegenerate.setVisibility(View.GONE);
        }
        mBinding.elRegAddress.setFocusable(false);

        mBinding.slIsPublicCard.setDataIs(null);
        // 默认为否
        mBinding.slIsPublicCard.setTextAndKey("0","否");

        mBinding.slIsAzGps.setDataIs((dialog, which) -> {

            if (which == 0) {
                mBinding.llGps.setVisibility(View.VISIBLE);
            } else {
                list.clear();
                mAdapter.notifyDataSetChanged();
                mBinding.llGps.setVisibility(View.GONE);
            }

        });
        ArrayList<BaseImageBean> listVin = new ArrayList<>();
        BaseImageBean bean = new BaseImageBean("VIN码", "driveLicense");
        listVin.add(bean);
        mBinding.bilVinCode.init(mActivity, listVin);

        mBinding.bilVinCode.setImageInterface((location, field, key) -> {
            //图片上传完成,拿到路径去请求接口 获取数据  填充其他的数据

            getCarDetail(MyCdConfig.QINIU_URL + key);
        });

        mBinding.slCarBrand.setListener(view -> {
            if (isDetail) {
                return;
            }
            CarBrandActivity.open(mActivity);

        });

        mBinding.slCarSeries.setData(carSeriesList, (dialog, which) -> {
            carSeries = carSeriesList.get(which).getDkey();
            getCarModel();
        });

        mBinding.slCarSeries.setListener(view -> {
            if (isDetail) {
                return;
            }

            if (TextUtils.isEmpty(carBrand)) {
                UITipDialog.showInfo(mActivity, "请先选择品牌");
            } else {

                mBinding.slCarSeries.setData(carSeriesList);
                SearchActivity.open(getContext(), "请输入搜索内容", mBinding.slCarSeries.getField(), (ArrayList<DataDictionary>) carSeriesList);
//                mBinding.slCarSeries.showSelect();
            }
        });

        mBinding.slCarModel.setListener(view -> {
            if (isDetail) {
                return;
            }

            if (TextUtils.isEmpty(carSeries)) {
                UITipDialog.showInfo(mActivity, "请先选择车系");
            } else {

                mBinding.slCarModel.setData(carModelList);
                SearchActivity.open(getContext(), "请输入搜索内容", mBinding.slCarModel.getField(), (ArrayList<DataDictionary>) carModelList);
            }
        });

        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }
    }

    private void getReport() {

        Map<String, String> map = new HashMap<>();
        map.put("modelId", mBinding.slCarModel.getDataKey());
        map.put("regDate", mBinding.dlRegDate.getText());
        map.put("mile", mBinding.elMile.getText());
        map.put("zone", ZrzlActivity.slRegion);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getZrzlReport("630479", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ZrzlReportBean>(mActivity) {

            @Override
            protected void onSuccess(ZrzlReportBean data, String SucMessage) {

                mBinding.tvReport.setText(data.getUrl());
                mBinding.tvRegenerate.setVisibility(View.VISIBLE);

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    /**
     * 根据上传的vin码,获取车辆的信息
     *
     * @param key
     */
    private void getCarDetail(String key) {
        //获取成功设置数据
        boolean check3 = mBinding.elMile.check();
        boolean check1 = mBinding.dlRegDate.check();
        boolean check2 = mBinding.elRegAddress.check();
        if (check3 || check1 || check2) {
            mBinding.bilVinCode.clean();
            return;
        }
        Map<String, String> map = new HashMap<>();
        map.put("url", key);
        map.put("zone", (String) mBinding.elRegAddress.getTag());
        map.put("regDate", mBinding.dlRegDate.getText());
        map.put("mile", mBinding.elMile.getText());
        showLoadingDialog();
        RetrofitUtils.createApi(MyApiServer.class)
                .getCarVINDetail("632980", StringUtils.getJsonToString(map))
                .enqueue(new BaseResponseModelCallBack<CarVINDetailBean>(mActivity) {
                    @Override
                    protected void onSuccess(CarVINDetailBean data, String SucMessage) {
                        setCarDetails(data);
                    }

                    @Override
                    protected void onReqFailure(String errorCode, String errorMessage) {
                        super.onReqFailure(errorCode, errorMessage);
                        mBinding.bilVinCode.clean();
                    }

                    @Override
                    protected void onFinish() {
                        disMissLoading();
                    }
                });
    }

    /**
     * 根据返回的  车辆信息 设置数据
     */
    private void setCarDetails(CarVINDetailBean data) {
//        {"errorCode":"0","errorInfo":"成功","data":{"carBrand":"35","carSeries":"419","carModel":"24758","originalPrice":"399800.00","evalPrice":"275600.00","carNumber":"浙CB756X","carFrameNo":"1FA6P8THXG5261693","carEngineNo":"G5261693H","secondCarReport":"https://www.che300.com/pinggu/v12c70m24758r2020-1g1?from\u003dhykg\u0026_s\u003da43f9bb14b13b8b3","brandName":"福特","seriesName":"野马(进口)","modelName":"2015款 野马(进口) 2.3T 性能版"}}

        mBinding.slCarBrand.setTextAndKey(data.getCarBrand(), data.getBrandName());//品牌
        mBinding.slCarSeries.setTextAndKey(data.getCarSeries(), data.getSeriesName());//车系
        mBinding.slCarModel.setTextAndKey(data.getCarModel(), data.getModelName());//车型
        mBinding.elModel.setText(data.getModelNumber());//车辆型号
        mBinding.elCarPrice.setText(data.getOriginalPrice());//厂商指导价
        mBinding.elCarFrameNo.setText(data.getCarFrameNo());//车架号
        mBinding.elCarEngineNo.setText(data.getCarEngineNo());//发动机
        mBinding.elCarNumber.setText(data.getCarNumber());//车牌号
        mBinding.elEvalPrice.setText(data.getEvalPrice());//评估价
        mBinding.tvReport.setText(data.getSecondCarReport());//评估报告
    }

    private void initAdapter() {
        mAdapter = new GPSAdapter(list, selectList, isDetail, mActivity);

        mBinding.rvGps.setAdapter(mAdapter);
        mBinding.rvGps.setLayoutManager(
                new LinearLayoutManager(mActivity, LinearLayoutManager.VERTICAL, false) {
                    @Override
                    public boolean canScrollVertically() {
                        return false;
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
        mBinding.dlRegDate.setVisibility(data.getBizType().equals("0") ? View.GONE : View.VISIBLE);
        mBinding.elMile.setVisibility(data.getBizType().equals("0") ? View.GONE : View.VISIBLE);
        mBinding.flReport.setVisibility(data.getBizType().equals("0") ? View.GONE : View.VISIBLE);

        carBrand = data.getCarInfo().getCarBrand();
        carSeries = data.getCarInfo().getCarBrand();

        mBinding.slCarBrand.setTextAndKey(data.getCarInfo().getCarBrand(),
                data.getCarInfo().getCarBrandName());

        mBinding.slCarSeries.setTextAndKey(data.getCarInfo().getCarSeries(),
                data.getCarInfo().getCarSeriesName());
        mBinding.slCarModel.setTextAndKey(data.getCarInfo().getCarModel(),
                data.getCarInfo().getCarModelName());

        mBinding.dlRegDate.setText(data.getCarInfo().getRegDate());
        mBinding.elMile.setText(data.getCarInfo().getMile());
        if (!TextUtils.isEmpty(data.getCarInfo().getSecondCarReport())) {
            mBinding.tvReport.setText(data.getCarInfo().getSecondCarReport());
            mBinding.tvRegenerate.setVisibility(View.VISIBLE);
        }
        List<ZrzlBean.AttachmentsBean> attachments = data.getAttachments();
        if (attachments != null && attachments.size() > 0) {
            for (ZrzlBean.AttachmentsBean attachment : attachments) {
                if (TextUtils.equals("drive_license", attachment.getKname())) {
                    mBinding.bilVinCode.setData("driveLicense", attachment.getUrl());
                    break;
                }
            }
        }
        //        设置数据

        if (null == data.getCarInfo()) {
            return;
        }


        mBinding.elModel.setText(data.getCarInfo().getModel());
        mBinding.elCarPrice.setText(data.getCarInfo().getCarPrice());
        mBinding.elCarFrameNo.setText(data.getCarInfo().getCarFrameNo());
        mBinding.elCarEngineNo.setText(data.getCarInfo().getCarEngineNo());
        mBinding.elCarNumber.setText(data.getCarInfo().getCarNumber());
        mBinding.elMile.setText(data.getCarInfo().getMile());
        mBinding.elEvalPrice.setText(data.getCarInfo().getEvalPrice());
        mBinding.dlRegDate.setText(data.getCarInfo().getRegDate());
//        mBinding.elRegAddress.setText(data.getRegionName());
        mBinding.slIsPublicCard.setContentByKey(data.getCarInfo().getIsPublicCard());

        mBinding.slIsAzGps.setContentByKey(data.getCarInfo().getIsAzGps());
        if (!TextUtils.isEmpty(data.getCarInfo().getIsAzGps())) {
            if (data.getCarInfo().getIsAzGps().equals("1")) {
                mBinding.llGps.setVisibility(View.VISIBLE);
                list.addAll(data.getGpsAzList());
                mAdapter.notifyDataSetChanged();
            }
        }

    }

    @Subscribe
    public void setCarBrand(EventBean bean) {

        if (!bean.getTag().equals("jbxx_brand")) {
            return;
        }

        carBrand = bean.getValue1();
        mBinding.slCarBrand.setTextAndKey(bean.getValue1(), bean.getValue2());
        getCarSeries();
    }


    /**
     * 获取汽车车系
     */
    public void getCarSeries() {

        Map<String, String> map = new HashMap<>();
        map.put("start", "1");
        map.put("limit", "10000");
        map.put("brandCode", carBrand);
        map.put("type", "1");
        map.put("isConcise", "精简查询");//这个值随意传参,否则接口处理的非常慢导致 响应超时

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getCarSeriesPage("630415", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<CarSeriesBean>>(mActivity) {

            @Override
            protected void onSuccess(ResponseInListModel<CarSeriesBean> data, String SucMessage) {

                carSeriesList.clear();
                for (CarSeriesBean model : data.getList()) {

                    carSeriesList.add(new DataDictionary().setDkey(model.getCode() + "")
                            .setDvalue(model.getName()));

                }


            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    /**
     * 获取汽车车系
     */
    public void getCarModel() {

        Map<String, String> map = new HashMap<>();
        map.put("start", "1");
        map.put("limit", "10000");
        map.put("seriesCode", carSeries);

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class)
                .getCarModelsPage("630425", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<ResponseInListModel<CarModelBean>>(mActivity) {

            @Override
            protected void onSuccess(ResponseInListModel<CarModelBean> data, String SucMessage) {

                carModelList.clear();
                for (CarModelBean model : data.getList()) {

                    carModelList.add(new DataDictionary().setDkey(model.getCode() + "")
                            .setDvalue(model.getName()));

                }

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void doRequest() {

        Map<String, String> map = BaseViewUtil.buildMap(mBinding.llInput);
        map.put("code", ((ZrzlActivity) mActivity).code);
        map.put("operator", SPUtilHelper.getUserId());


        if (!mBinding.tvReport.getText().equals("点击生成评报告")) {
            map.put("secondCarReport", mBinding.tvReport.getText().toString());
        }

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632534", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {

                if (mBinding.llGps.getVisibility() == View.VISIBLE) {
                    installGPS();
                } else {
                    success();
                }
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });

    }

    private void installGPS() {

        Map<String, Object> map = RetrofitUtils.getRequestMap();
        map.put("code", ((ZrzlActivity) mActivity).code);
        map.put("operator", SPUtilHelper.getUserId());
        map.put("gpsAzList", list);

        Call call = RetrofitUtils.getBaseAPiService()
                .successRequest("632126", StringUtils.getJsonToString(map));

        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<IsSuccessModes>(mActivity) {
            @Override
            protected void onSuccess(IsSuccessModes data, String SucMessage) {

                success();

            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    private void success() {
        UITipDialog.showSuccess(mActivity, "保存成功", dialogInterface -> {
            EventBus.getDefault()
                    .post(new EventBean().setTag(SET_UPLOAD_RESULT).setValue1("4"));
        });
    }

    @Subscribe
    public void zrzlChange(EventBean bean) {

        if (bean.getTag().equals("zrzl_region")) {

            mBinding.elRegAddress.setText(bean.getValue1());
            mBinding.elRegAddress.setTag(bean.getValue2());
        }

        if (bean.getTag().equals("zrzl_regDate")) {

            mBinding.dlRegDate.setText(bean.getValue1());

        }

        if (bean.getTag().equals("zrzl_mile")) {

            mBinding.elMile.setText(bean.getValue1());

        }

    }

    @Subscribe
    public void changeCarType(EventBean bean) {
        if (TextUtils.equals("change_car_type", bean.getTag())) {
            boolean newCar = TextUtils.equals("新车", bean.getValue1());

            mBinding.dlRegDate.setVisibility(newCar ? View.GONE : View.VISIBLE);
            mBinding.elMile.setVisibility(newCar ? View.GONE : View.VISIBLE);
            mBinding.flReport.setVisibility(newCar ? View.GONE : View.VISIBLE);

            if (newCar) {
                mBinding.elModel.isRequired(false);
                mBinding.elCarPrice.isRequired(false);
                mBinding.elCarFrameNo.isRequired(false);
                mBinding.elCarEngineNo.isRequired(false);
                mBinding.elMile.isRequired(false);
                mBinding.elEvalPrice.isRequired(false);
                mBinding.dlRegDate.isRequired(false);
                mBinding.bilVinCode.setVisibility(View.GONE);
            } else {
                mBinding.elModel.isRequired(true);
                mBinding.elCarPrice.isRequired(true);
                mBinding.elCarFrameNo.isRequired(true);
                mBinding.elCarEngineNo.isRequired(true);
                mBinding.elMile.isRequired(true);
                mBinding.elEvalPrice.isRequired(true);
                mBinding.dlRegDate.isRequired(true);
                mBinding.bilVinCode.setVisibility(View.VISIBLE);
            }
        }

    }

}
