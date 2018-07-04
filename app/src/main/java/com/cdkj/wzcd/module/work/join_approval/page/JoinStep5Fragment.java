package com.cdkj.wzcd.module.work.join_approval.page;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cdkj.baselibrary.base.BaseLazyFragment;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.adapter.RePointAdapter;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.FragmentJoinStep5Binding;
import com.cdkj.wzcd.model.AdvanceReplaceModel;
import com.cdkj.wzcd.model.DealersModel;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.model.RePointReplaceModel;
import com.cdkj.wzcd.model.SystemParameterModel;
import com.cdkj.wzcd.model.event.BudgetCheckModel;
import com.cdkj.wzcd.model.event.ChangeRePointList1Model;
import com.cdkj.wzcd.module.work.join_approval.AdvanceFundActivity;
import com.cdkj.wzcd.module.work.join_approval.JoinApplyActivity;
import com.cdkj.wzcd.module.work.join_approval.RePointAddActivity;
import com.cdkj.wzcd.util.DataDictionaryHelper;
import com.cdkj.wzcd.util.RequestUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

import static com.cdkj.baselibrary.appmanager.CdRouteHelper.DATA_SIGN;
import static com.cdkj.wzcd.util.DataDictionaryHelper.fee_way;
import static com.cdkj.wzcd.util.DataDictionaryHelper.gps_fee_way;
import static com.cdkj.wzcd.util.RequestUtil.formatInt;

/**
 * Created by cdkj on 2018/6/7.
 */

public class JoinStep5Fragment extends BaseLazyFragment {

    private String code;
    private FragmentJoinStep5Binding mBinding;

    public boolean isOutside;
    private NodeListModel data;

    // 收客户手续费
    double skhsxf;
    // gps费
    double gpsf;
    // 是否垫资 默认垫资
    boolean isAdvanceFund = true;

    private RePointAdapter mAdapter1;
    private List<NodeListModel.RepointDetailListBean> mList1 = new ArrayList<>();

    private RePointAdapter mAdapter3;
    private List<NodeListModel.RepointDetailListBean> mList3 = new ArrayList<>();



    public static JoinStep5Fragment getInstance(String code) {
        JoinStep5Fragment fragment = new JoinStep5Fragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_SIGN, code);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_join_step5, null, false);

        if (getArguments() != null) {
            code = getArguments().getString(DATA_SIGN);
        }

        data = ((JoinApplyActivity) mActivity).mData;
        isOutside = ((JoinApplyActivity) mActivity).isOutside;

        initAdapter();

        initView();
        setView();

        getGpsDeductRate();
        getOilDeductRate();

        return mBinding.getRoot();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void onInvisible() {

    }

    private void initAdapter() {
        mList1.add(new NodeListModel.RepointDetailListBean());
        mAdapter1 = new RePointAdapter(mList1);
        mAdapter1.setOnItemClickListener((adapter, view, position) -> {
            if (!isOutside){// 正常单

                LogUtil.E("isAdvanceFund = "+isAdvanceFund);

                if (isAdvanceFund){ // 已垫资
                    // 不能修改应退垫资款
                    return;
                }
            }

            AdvanceFundActivity.open(mActivity, mAdapter1.getItem(position), position);
        });

        mAdapter3 = new RePointAdapter(mList3);
        mAdapter3.setOnItemChildClickListener((adapter, view, position) -> {
            RePointAddActivity.open(mActivity, mAdapter3.getItem(position), position);

        });
    }

    private void initView() {

        if (!isOutside){

            mBinding.mySlGpsFee.setVisibility(View.GONE);
            mBinding.mySlLyAmount.setVisibility(View.GONE);
            mBinding.mySlFxAmount.setVisibility(View.GONE);
            mBinding.mySlOtherFee.setVisibility(View.GONE);

        }

        mBinding.mySlIsPlatInsure.setDataIs(null);
        mBinding.mySlGpsFeeWay.setData(DataDictionaryHelper.getListByParentKey(gps_fee_way), (dialog, which) -> {
            setRepointDetailList1();
        });
        mBinding.mySlServiceChargeWay.setData(DataDictionaryHelper.getListByParentKey(fee_way), (dialog, which) -> {
            setRepointDetailList1();
        });

        mBinding.myRlRepointDetailList1.build(mAdapter1, false, null);

        mBinding.myRlRepointDetailList3.build(mAdapter3, true, view -> {

            RePointAddActivity.open(mActivity);

        });

    }

    private void setView() {

        mBinding.myElOilSubsidyKil.setText(data.getOilSubsidyKil()+"");
        mBinding.mySlIsPlatInsure.setContentByKey(data.getIsPlatInsure());

        mBinding.mySlGpsFeeWay.setContentByKey(data.getGpsFeeWay());
        mBinding.mySlServiceChargeWay.setContentByKey(data.getFeeWay());

        if (data.getRepointDetailList1() != null ){
            for (NodeListModel.RepointDetailListBean bean : data.getRepointDetailList1()){
                mList1.set(0 , bean);
            }
            mAdapter1.notifyDataSetChanged();
        }


        if (data.getRepointDetailList3() != null ){
            mList3.clear();
            for (NodeListModel.RepointDetailListBean bean : data.getRepointDetailList3()){
                mList3.add(bean);
            }
            mAdapter3.notifyDataSetChanged();
        }

    }



    @Subscribe
    public void carDealerSubsidyChangeRepointlList(ChangeRePointList1Model model){
        if (model == null)
            return;

        // 厂家贴息修改时 修改应退按揭款
        setRepointDetailList1();
    }




    private void setRepointDetailList1(){

        DealersModel model = ((JoinApplyActivity) mActivity).getDealers();
        if (model != null){

            for (DealersModel.CarDealerProtocolListBean bean : model.getCarDealerProtocolList()){
                if (TextUtils.equals(bean.getBankCode(), data.getBankSubbranch().getBank().getBankCode())){

                    mBinding.mySlGpsFee.setMoneyText(!TextUtils.isEmpty(bean.getGpsFee()) ? bean.getGpsFee() : RequestUtil.mul(bean.getGpsRate()+"", data.getLoanAmount()));
                    mBinding.mySlLyAmount.setMoneyText(!TextUtils.isEmpty(bean.getLyAmountFee()) ? bean.getLyAmountFee() : RequestUtil.mul(bean.getLyAmountRate()+"", data.getLoanAmount()));
                    mBinding.mySlFxAmount.setMoneyText(!TextUtils.isEmpty(bean.getAssureFee()) ? bean.getAssureFee() : RequestUtil.mul(bean.getAssureRate()+"", data.getLoanAmount()));
                    mBinding.mySlOtherFee.setMoneyText(!TextUtils.isEmpty(bean.getOtherFee()) ? bean.getOtherFee() : RequestUtil.mul(bean.getOtherRate()+"", data.getLoanAmount()));

                    isAdvanceFund = ((JoinApplyActivity) mActivity).getIsAdvanceFund();

                    LogUtil.E("getIsAdvanceFund = "+isAdvanceFund);

                    // 如果不垫资，则需要手动输入用途和金额以外的其他信息， 垫资则自动填入
                    if (isAdvanceFund){

                        for (DealersModel.JxsCollectBankcardListBean jxsCollectBankcardListBean : model.getJxsCollectBankcardList()){

                            if (TextUtils.equals(jxsCollectBankcardListBean.getBankCode(), data.getBankSubbranch().getBank().getBankCode())){
                                mList1.get(0).setCarDealerName(model.getParentGroup()+"-"+model.getAbbrName());
                                mList1.get(0).setAccountNO(jxsCollectBankcardListBean.getBankcardNumber());
                                mList1.get(0).setOpenBankName(jxsCollectBankcardListBean.getSubbranch());
                            }
                        }

                        mAdapter1.notifyDataSetChanged();

                    }

                }

            }
        }



        if (TextUtils.isEmpty(mBinding.mySlServiceChargeWay.getDataKey()))
            return;

        if (TextUtils.equals(mBinding.mySlServiceChargeWay.getDataKey(), "2")){ // 按揭款
            skhsxf = Double.parseDouble(mBinding.mySlGpsFee.getMoneyText())
                    + Double.parseDouble(mBinding.mySlLyAmount.getMoneyText())
                    + Double.parseDouble(mBinding.mySlFxAmount.getMoneyText())
                    + Double.parseDouble(mBinding.mySlOtherFee.getMoneyText());
        }else {
            skhsxf = 0;
        }


        if (TextUtils.isEmpty(mBinding.mySlGpsFeeWay.getDataKey()))
            return;

        if (TextUtils.equals(mBinding.mySlGpsFeeWay.getDataKey(), "2")){ // 按揭款
            gpsf = Double.parseDouble(mBinding.mySlGpsFee.getMoneyText());
        }else {
            gpsf = 0;
        }


        Double d = Double.parseDouble(data.getLoanAmount()) - skhsxf - gpsf - Double.parseDouble(((JoinApplyActivity) mActivity).getCjtx());

        mList1.get(0).setRepointAmount(formatInt(d));
        mList1.get(0).setUseMoneyPurpose("1");
        mAdapter1.notifyDataSetChanged();
    }


    private String checkFail;

    public boolean check(){
//        if (mBinding.myElOilSubsidy.check()){
//            checkFail = mBinding.myElOilSubsidy.getTitle();
//            return false;
//        }

        if (mBinding.myElOilSubsidyKil.check()){
            checkFail = mBinding.myElOilSubsidyKil.getTitle();
            return false;
        }

        if (mBinding.mySlIsPlatInsure.check()){
            checkFail = mBinding.mySlIsPlatInsure.getTitle();
            return false;
        }


        return true;
    }

    @Subscribe
    public void doCheck(BudgetCheckModel model){

        if (model == null)
            return;

        // 检查未通过，由Activity提示，不往下check
        if (!model.isCheckResult()){
            return;
        }

        if (model.getCheckIndex() == 4){
            if (check()){

                EventBus.getDefault().post(new BudgetCheckModel()
                        .setCheckIndex(model.getCheckIndex()+1)
                        .setCheckResult(true)
                        .setCheckFail(null));

            }else {

                EventBus.getDefault().post(new BudgetCheckModel()
                        .setCheckIndex(model.getCheckIndex())
                        .setCheckResult(false)
                        .setCheckFail(checkFail));

            }
        }
    }

    public Map<String, Object> getData(){

        Map<String, Object> map = new HashMap<>();

        map.put("oilSubsidy",mBinding.myNlOilSubsidy.getMoneyText());
        map.put("oilSubsidyKil",mBinding.myElOilSubsidyKil.getText());
        map.put("isPlatInsure",mBinding.mySlIsPlatInsure.getDataKey());
        map.put("gpsDeduct",mBinding.myElGpsDeduct.getText());
        map.put("gpsFeeWay",mBinding.mySlGpsFeeWay.getDataKey());
        map.put("serviceChargeWay",mBinding.mySlServiceChargeWay.getDataKey());

        map.put("gpsFee",mBinding.mySlGpsFee.getMoneyText());
        map.put("lyAmount",mBinding.mySlLyAmount.getMoneyText());
        map.put("fxAmount",mBinding.mySlFxAmount.getMoneyText());
        map.put("otherFee",mBinding.mySlOtherFee.getMoneyText());


        List<NodeListModel.RepointDetailListBean> list = new ArrayList<>();
        list.addAll(mList1);
        list.addAll(mList3);
        map.put("repointDetailList", list);

        return map;
    }

    public void getGpsDeductRate(){
        if (TextUtils.isEmpty(code))
            return;

        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("key", "budget_gps_deduct_rate");

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getSystemParameter("630047", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<SystemParameterModel>(mActivity) {
            @Override
            protected void onSuccess(SystemParameterModel model, String SucMessage) {
                mBinding.myElGpsDeduct.setMoneyText(RequestUtil.mul(data.getLoanAmount(), model.getCvalue()));
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    public void getOilDeductRate(){
        if (TextUtils.isEmpty(code))
            return;

        Map<String, String> map = RetrofitUtils.getRequestMap();

        map.put("key", "budget_oil_subsidy_rate");

        showLoadingDialog();

        Call call = RetrofitUtils.createApi(MyApiServer.class).getSystemParameter("630047", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseModelCallBack<SystemParameterModel>(mActivity) {
            @Override
            protected void onSuccess(SystemParameterModel model, String SucMessage) {
                mBinding.myNlOilSubsidy.setMoneyText(RequestUtil.mul(data.getLoanAmount(), model.getCvalue()));
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
    }

    @Subscribe
    public void doAddCreditPerson(NodeListModel.RepointDetailListBean model){
        mList3.add(model);
        mAdapter3.notifyDataSetChanged();
    }

    @Subscribe
    public void doReplaceCreditPerson(RePointReplaceModel model){

        mList3.set(model.getPosition(), model.getRePointModel());
        mAdapter3.notifyDataSetChanged();

    }

    @Subscribe
    public void doReplaceCreditPerson(AdvanceReplaceModel model){

        mList1.get(0).setCarDealerName(model.getRePointModel().getCarDealerName());
        mList1.get(0).setAccountNO(model.getRePointModel().getAccountNO());
        mList1.get(0).setOpenBankName(model.getRePointModel().getOpenBankName());
        mAdapter1.notifyDataSetChanged();

    }
}
