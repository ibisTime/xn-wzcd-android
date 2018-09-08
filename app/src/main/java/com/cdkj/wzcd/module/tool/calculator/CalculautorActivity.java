package com.cdkj.wzcd.module.tool.calculator;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.baselibrary.base.AbsBaseLoadActivity;
import com.cdkj.baselibrary.dialog.UITipDialog;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.MoneyUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.R;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.databinding.ActivityCalculautorBinding;
import com.cdkj.wzcd.model.BanksModel;
import com.cdkj.wzcd.model.CalculautorModel;
import com.cdkj.wzcd.util.DataDictionaryHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * 月供计算器
 */
public class CalculautorActivity extends AbsBaseLoadActivity {
    ActivityCalculautorBinding mBinding;
//    private DealersModel mDealersMode;//经销商
    private BanksModel banksModel;//银行
    private DataDictionary rateType;//利率类型
    private String rate;//利率
    private DataDictionary rateTime;//贷款周期
    private DataDictionary poundageType;
    private DataDictionary surcharge;//附加费
    private HashMap<String, String> requestMap;//请求计算的入参


    public static void open(Context context) {
        if (context != null) {
            Intent intent = new Intent(context, CalculautorActivity.class);
            context.startActivity(intent);
        }
    }

    @Override
    public View addMainView() {
        mBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_calculautor, null, false);
        return mBinding.getRoot();
    }

    @Override
    public void afterCreate(Bundle savedInstanceState) {
        mBaseBinding.titleView.setMidTitle("月供计算器");
        mBinding.mElMoney.setInputCountenLeft();
//        getDealers();
        getBank();
        getRateTime();
        getRateType();
        getSurcharge();
        getRate();
        getmSlPoundageType();

        mBinding.btnConfirm.setOnConfirmListener(view -> {
            checkData();

        });

    }


    private void checkData() {
        //检查资料是否填写完毕
//        if (mDealersMode == null) {
//            UITipDialog.showInfo(this, "请选择经销商");
//            return;
//        }
        if (banksModel == null) {
            UITipDialog.showInfo(this, "请选择银行");
            return;
        }
        if (rateTime == null) {
            UITipDialog.showInfo(this, "请选择周期");
            return;
        }
        if (rateType == null) {
            UITipDialog.showInfo(this, "请选择利率类型");
            return;
        }

        rate = mBinding.myESlBankRate.getText();
        if (rate == null || TextUtils.isEmpty(rate)) {
            UITipDialog.showInfo(this, "请填写利率");
            return;
        }
        requestMap = new HashMap();
        if (banksModel != null) {
            if (banksModel.getBankCode().equals("BOC")) {
                if (poundageType == null) {
                    UITipDialog.showInfo(this, "请选择手续费收取方式");
                    return;
                }
                if (TextUtils.equals(poundageType.getDkey(), "3")) {
                    if (surcharge == null) {
                        UITipDialog.showInfo(this, "请选择附加费");
                        return;
                    }
                    requestMap.put("surcharge", surcharge.getDkey());
                }
                requestMap.put("serviceChargeWay", poundageType.getDkey());
            }
        }
//        requestMap.put("carDealerCode", mDealersMode.getCode());//经销商编号
        requestMap.put("loanBankCode", banksModel.getCode());//贷款银行
        requestMap.put("loanPeriods", rateTime.getDkey());//贷款周期
        requestMap.put("loanAmount", mBinding.mElMoney.getMoneyText());//贷款金额
        requestMap.put("rateType", rateType.getDkey());//利率类型
        requestMap.put("bankRate", rate);//银行利率

        calculationData();
    }

    /**
     * 去计算
     */
    private void calculationData() {
        Call<BaseResponseModel<CalculautorModel>> call = RetrofitUtils.createApi(MyApiServer.class).calculautor("632690", StringUtils.getJsonToString(requestMap));
        showLoadingDialog();
        call.enqueue(new BaseResponseModelCallBack<CalculautorModel>(this) {
            @Override
            protected void onSuccess(CalculautorModel data, String SucMessage) {
                mBinding.tvFirstPay.setText(MoneyUtils.showPrice(data.getInitialAmount()) + "¥");//首付
                mBinding.tvMonthPay.setText(MoneyUtils.showPrice(data.getAnnualAmount()) + "¥");//月付
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });


    }

    /**
     * 手续费收取方式 数据字典 boc_fee_way
     */
    private void getmSlPoundageType() {
        DataDictionaryHelper.getDataDictionaryList(this, "boc_fee_way", (data, SucMessage) -> {
            if (data == null || data.size() == 0)
                return;
            List<DataDictionary> list = new ArrayList<>();
            for (DataDictionary item : data) {
                list.add(new DataDictionary().setDkey(item.getDkey()).setDvalue(item.getDvalue()));
            }
            mBinding.mSlPoundageType.setData(list, (dialog, which) -> {
                poundageType = data.get(which);
                if (TextUtils.equals(poundageType.getDkey(), "3")) {
                    mBinding.mSlSurchargeMoney.setVisibility(View.VISIBLE);
                } else {
                    mBinding.mSlSurchargeMoney.setVisibility(View.GONE);
                }
            });
        });
    }

    /**
     * 获取附加费
     * surcharge
     */
    private void getSurcharge() {

        DataDictionaryHelper.getDataDictionaryList(this, "surcharge", (data, SucMessage) -> {
            if (data == null || data.size() == 0)
                return;
            List<DataDictionary> list = new ArrayList<>();
            for (DataDictionary item : data) {
                list.add(new DataDictionary().setDkey(item.getDkey()).setDvalue(item.getDvalue()));
            }
            mBinding.mSlSurchargeMoney.setData(list, (dialog, which) -> {
                surcharge = data.get(which);
            });
        });
//        HashMap<String, String> map = new HashMap<>();
//        map.put("parentKey", "surcharge");
//        Call<BaseResponseListModel<DataDictionary>> call = RetrofitUtils.createApi(MyApiServer.class).getDataDictionary("630036", StringUtils.getJsonToString(map));
//
//        call.enqueue(new BaseResponseListCallBack<DataDictionary>(this) {
//
//            @Override
//            protected void onSuccess(List<DataDictionary> data, String SucMessage) {
//                if (data == null || data.size() == 0)
//                    return;
//                List<DataDictionary> list = new ArrayList<>();
//                for (DataDictionary item : data) {
//                    list.add(new DataDictionary().setDkey(item.getDkey()).setDvalue(item.getDvalue()));
//                }
//                mBinding.mSlSurchargeMoney.setData(list, (dialog, which) -> {
//
//                    surcharge = data.get(which);
//                });
//            }
//
//            @Override
//            protected void onFinish() {
//                disMissLoading();
//            }
//        });
    }


    /**
     * 获取利率
     */
    private void getRate() {

        mBinding.myESlBankRate.setOnDropEnable(false);
    }

    /**
     * 获取利率类型  数据字典
     * rate_type
     */
    private void getRateType() {

        DataDictionaryHelper.getDataDictionaryList(this, "rate_type", (data, SucMessage) -> {
            if (data == null || data.size() == 0)
                return;
            List<DataDictionary> list = new ArrayList<>();
            for (DataDictionary item : data) {
                list.add(new DataDictionary().setDkey(item.getDkey()).setDvalue(item.getDvalue()));
            }
            mBinding.mSlRateType.setData(list, (dialog, which) -> {

                rateType = data.get(which);
            });
        });

//        HashMap<String, String> map = new HashMap<>();
//        map.put("parentKey", "rate_type");
//        Call<BaseResponseListModel<DataDictionary>> call = RetrofitUtils.createApi(MyApiServer.class).getDataDictionary("630036", StringUtils.getJsonToString(map));
//
//        call.enqueue(new BaseResponseListCallBack<DataDictionary>(this) {
//
//            @Override
//            protected void onSuccess(List<DataDictionary> data, String SucMessage) {
//                if (data == null || data.size() == 0)
//                    return;
//                List<DataDictionary> list = new ArrayList<>();
//                for (DataDictionary item : data) {
//                    list.add(new DataDictionary().setDkey(item.getDkey()).setDvalue(item.getDvalue()));
//                }
//                mBinding.mSlRateType.setData(list, (dialog, which) -> {
//
//                    rateType = data.get(which);
//                });
//            }
//
//            @Override
//            protected void onFinish() {
//                disMissLoading();
//            }
//        });
    }

    /**
     * 后期贷款周期  写死了
     */
    private void getRateTime() {
        List<DataDictionary> list = new ArrayList<>();
        list.add(new DataDictionary().setDkey("12").setDvalue("12期"));
        list.add(new DataDictionary().setDkey("24").setDvalue("24期"));
        list.add(new DataDictionary().setDkey("36").setDvalue("36期"));

        mBinding.mSlLoanTime.setData(list, () -> {
            if (banksModel == null) {
                UITipDialog.showInfo(this, "请先选择银行");
                return true;
            }
            return false;
        }, (dialog, which) -> {
            rateTime = list.get(which);
            List<BanksModel.BankRateListBean> bankRateList = banksModel.getBankRateList();
            if (bankRateList == null)
                return;

            int current = Integer.parseInt(rateTime.getDkey());
            for (int i = 0; i < bankRateList.size(); i++) {
                int period = (int) bankRateList.get(i).getPeriod();
                if (period == current) {
                    mBinding.myESlBankRate.setText(bankRateList.get(i).getRate() + "");
                    break;
                }
            }

        });

    }

    /**
     * 获取银行
     */
    public void getBank() {

//        Map<String, String> map = RetrofitUtils.getNodeListMap();
        Map<String, String> map = new HashMap<>();

        showLoadingDialog();
        Call call = RetrofitUtils.createApi(MyApiServer.class).getBanksList("632037", StringUtils.getJsonToString(map));
        addCall(call);

        call.enqueue(new BaseResponseListCallBack<BanksModel>(this) {
            @Override
            protected void onSuccess(List<BanksModel> data, String SucMessage) {

                if (data == null || data.size() == 0)
                    return;

                List<DataDictionary> list = new ArrayList<>();
                for (BanksModel model : data) {
                    list.add(new DataDictionary().setDkey(model.getCode()).setDvalue(model.getBankName()));
                }
                mBinding.mSlBankName.setData(list, (dialog, which) -> {
                    banksModel = data.get(which);
                    if ("BOC".equals(banksModel.getBankCode())) {
                        mBinding.mSlPoundageType.setVisibility(View.VISIBLE);
                    } else {
                        mBinding.mSlPoundageType.setVisibility(View.GONE);
                        mBinding.mSlSurchargeMoney.setVisibility(View.GONE);
                    }
                });
            }

            @Override
            protected void onFinish() {
                disMissLoading();
            }
        });
        return;
    }

//    /**
//     * 获取经销商
//     */
//    private void getDealers() {
//        Map<String, String> map = RetrofitUtils.getNodeListMap();
////        map.put("curNodeCode", "006_02");
//        showLoadingDialog();
//        Call call = RetrofitUtils.createApi(MyApiServer.class).getDealersList("632067", StringUtils.getJsonToString(map));
//        addCall(call);
//
//        call.enqueue(new BaseResponseListCallBack<DealersModel>(this) {
//            @Override
//            protected void onSuccess(List<DealersModel> data, String SucMessage) {
//
//                if (data == null || data.size() == 0)
//                    return;
//
//                List<DataDictionary> list = new ArrayList<>();
//                for (DealersModel model : data) {
//                    list.add(new DataDictionary().setDkey(model.getCode()).setDvalue(model.getParentGroup() + "-" + model.getAbbrName()));
//                }
//
//                mBinding.mSlDealer.setData(list, (dialog, which) -> {
//
//                    mDealersMode = data.get(which);
//
//                });
//            }
//
//            @Override
//            protected void onFinish() {
//                disMissLoading();
//            }
//        });
//    }


}
