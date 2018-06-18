package com.cdkj.wzcd.util;

import android.content.Context;
import android.text.TextUtils;

import com.cdkj.baselibrary.appmanager.SPUtilHelper;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.MainActivity;
import com.cdkj.wzcd.api.MyApiServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cdkj on 2018/5/30.
 */

public class DataDictionaryHelper {

    public static String credit_user_relation = "credit_user_relation";
    public static String credit_user_loan_role = "credit_user_loan_role";
    public static String template_id = "template_id";
    public static String make_card_status = "make_card_status";
    public static String budget_orde_biz_typer = "budget_orde_biz_typer";
    public static String loan_period = "loan_period";

    // 寄送方式
    public static String send_type = "send_type";
    // 快递公司
    public static String kd_company = "kd_company";
    // GPS申领状态
    public static String gps_apply_status = "gps_apply_status";
    // 还款业务状态
    public static String status = "status";

    private static Call call;

    public static String getValueOnTheKey(String key, List<DataDictionary> data){

        for (DataDictionary dataDictionary : data){
            if (TextUtils.equals(key, dataDictionary.getDkey()))
                return dataDictionary.getDvalue();
        }

        return "";
    }

    public static String getValueOnTheKey(String key){
        if (MainActivity.BASE_NODE_LIST == null || MainActivity.BASE_NODE_LIST.size() == 0)
            return "";

        if (TextUtils.isEmpty(key))
            return "";

        for (DataDictionary dataDictionary : MainActivity.BASE_BIZ_TYPE){
            if (TextUtils.equals(key, dataDictionary.getDkey()))
                return dataDictionary.getDvalue();
        }

        return "";
    }

    public static void getValueOnTheKeyRequest(Context context, String parentKey, String key, DataDictionaryInterface dataDictionaryInterface){
        Map<String, String> map = new HashMap<>();
        map.put("dkey", key);
        map.put("orderColumn", "");
        map.put("orderDir", "");
        map.put("parentKey", parentKey);
        map.put("type", "");
        map.put("updater", "");
        map.put("token", SPUtilHelper.getUserToken());


        call = RetrofitUtils.createApi(MyApiServer.class).getDataDictionary("630036", StringUtils.getJsonToString(map));

        call.enqueue(new BaseResponseListCallBack<DataDictionary>(context) {

            @Override
            protected void onSuccess(List<DataDictionary> data, String SucMessage) {
                if (data == null || data.size() == 0)
                    return;

                if (dataDictionaryInterface != null)
                    dataDictionaryInterface.onSuccess(data.get(0));
            }

            @Override
            protected void onFinish() {
                clearCall();
            }
        });
    }

    public interface DataDictionaryInterface{

        void onSuccess(DataDictionary data);

    }

    public static void getDataDictionaryRequest(Context context, String parentKey, String key, DataDictionaryListInterface listInterface){
        Map<String, String> map = new HashMap<>();
        map.put("dkey", key);
        map.put("orderColumn", "");
        map.put("orderDir", "");
        map.put("parentKey", parentKey);
        map.put("type", "");
        map.put("updater", "");

        call = RetrofitUtils.createApi(MyApiServer.class).getDataDictionary("630036", StringUtils.getJsonToString(map));

        call.enqueue(new BaseResponseListCallBack<DataDictionary>(context) {

            @Override
            protected void onSuccess(List<DataDictionary> data, String SucMessage) {
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

    public interface DataDictionaryListInterface{

        void onSuccess(List<DataDictionary> list);

    }

    private static void clearCall(){
        if (call != null)
            call.cancel();
    }
}
