package com.cdkj.wzcd.main.credit.module.zrzl.step;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cdkj.baselibrary.appmanager.CdRouteHelper;
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
import com.cdkj.wzcd.custom.util.BaseViewUtil;
import com.cdkj.wzcd.databinding.FrgStepClxxBinding;
import com.cdkj.wzcd.main.credit.CreditActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.ZrzlActivity;
import com.cdkj.wzcd.main.credit.module.zrzl.adapter.GPSAdapter;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.GPSBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import retrofit2.Call;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

            if (mBinding.slIsAzGps.getDataKey().equals("1")) {

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
        mBinding.elRegAddress.setFocusable(false);
        mBinding.slIsPublicCard.setDataIs(null);
        mBinding.slIsAzGps.setDataIs((dialog, which) -> {

            if (which == 0) {
                mBinding.llGps.setVisibility(View.VISIBLE);
            } else {
                list.clear();
                mAdapter.notifyDataSetChanged();
                mBinding.llGps.setVisibility(View.GONE);
            }

        });

        if (isDetail) {
            BaseViewUtil.setUnFocusable(mBinding.llInput);
            mBinding.btnConfirm.setVisibility(View.GONE);
        }
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
        mBinding.elRegAddress.setText(data.getRegionName());
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

    private void doRequest() {

        Map<String, String> map = BaseViewUtil.buildMap(mBinding.llInput);
        map.put("code", ((ZrzlActivity) mActivity).code);
        map.put("operator", SPUtilHelper.getUserId());

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
                    .post(new EventBean().setTag(SET_UPLOAD_RESULT).setValue1("5"));
        });
    }

    @Subscribe
    public void zrzlChange(EventBean bean) {

        if (bean.getTag().equals("zrzl_region")) {

            mBinding.elRegAddress.setText(bean.getValue1());

        }

        if (bean.getTag().equals("zrzl_regDate")) {

            mBinding.dlRegDate.setText(bean.getValue1());

        }

        if (bean.getTag().equals("zrzl_mile")) {

            mBinding.elMile.setText(bean.getValue1());

        }

    }


}
