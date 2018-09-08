package com.cdkj.wzcd.util;

import android.content.Context;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.base.BaseActivity;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.BaseResponseModelCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.ExchangeBankModel;
import com.cdkj.wzcd.view.list.MyListItemLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cdkj on 2018/5/30.
 */

public class BankHelper {

    private static Call call;

    public static void getValueOnTheKey(Context context, String code, MyListItemLayout myListItemLayout, BankInterface bankInterface) {

        Map<String, String> map = new HashMap<>();

        map.put("token", SPUtilHelper.getUserToken());
        map.put("code", code);
//        map.put("bankCode", bankCode);


        call = RetrofitUtils.createApi(MyApiServer.class).getBank("632056", StringUtils.getJsonToString(map));

        if (context instanceof BaseActivity) {
            ((BaseActivity) context).showLoadingDialog();
        }
        LogUtil.I("pppppp对应的银行开始请求" + code);
        call.enqueue(new BaseResponseModelCallBack<ExchangeBankModel>(context) {

            @Override
            protected void onSuccess(ExchangeBankModel data, String SucMessage) {
                if (data == null) {
                    if (context instanceof BaseActivity) {
                        ((BaseActivity) context).disMissLoading();
                    }
                    LogUtil.I("pppppp没有获取到对应的银行信息" + code);
                    return;
                }

                if (myListItemLayout != null)
                    myListItemLayout.setText(data.getBankName());

                if (bankInterface != null)
                    bankInterface.onSuccess(data);
            }

            @Override
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);
                LogUtil.I("pppppp银行信息请求异常" + code+"异常信息为:"+errorMessage);
            }


            @Override
            protected void onFinish() {
                LogUtil.I("pppppp银行信息请求结束" + code);
                if (context instanceof BaseActivity) {
                    ((BaseActivity) context).disMissLoading();
                }
//                clearCall();
            }
        });
    }

    public interface BankInterface {

        void onSuccess(ExchangeBankModel data);

    }


    public static void getBankListRequest(Context context, BankListInterface listInterface) {
        Map<String, String> map = new HashMap<>();

        call = RetrofitUtils.createApi(MyApiServer.class).getBankList("632057", StringUtils.getJsonToString(map));

        call.enqueue(new BaseResponseListCallBack<ExchangeBankModel>(context) {

            @Override
            protected void onSuccess(List<ExchangeBankModel> data, String SucMessage) {
                if (data == null || data.size() == 0)
                    return;

                if (listInterface != null)
                    listInterface.onSuccess(data);
            }

            @Override
            protected void onFinish() {
                clearCall();
            }
        });
    }

    public interface BankListInterface {

        void onSuccess(List<ExchangeBankModel> list);

    }

    private static void clearCall() {
        if (call != null)
            call.cancel();
    }
}
