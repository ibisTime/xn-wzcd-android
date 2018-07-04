package com.cdkj.wzcd.util;

import android.content.Context;
import android.text.TextUtils;

import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.api.MyApiServer;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cdkj on 2018/5/30.
 */

public class DataDictionaryHelper {

    static List<DataDictionary> BASE_DATA_LIST = new ArrayList<>();

    public static String credit_user_relation = "credit_user_relation";
    public static String marry_state = "marry_state";
    public static String credit_user_loan_role = "credit_user_loan_role";
    public static String template_id = "template_id";
    public static String make_card_status = "make_card_status";
    public static String budget_orde_biz_typer = "budget_orde_biz_typer";
    public static String loan_period = "loan_period";
    public static String rate_type = "rate_type";

    // 寄送方式
    public static String send_type = "send_type";
    // 快递公司
    public static String kd_company = "kd_company";
    // GPS申领状态
    public static String gps_apply_status = "gps_apply_status";
    // GPS安装位置
    public static String az_location = "az_location";
    // 还款业务状态
    public static String status = "status";

    public static String gps_fee_way = "gps_fee_way";

    public static String fee_way = "fee_way";
    // 补件原因
    public static String supplement_reason = "supplement_reason";

    private static Call call;



    private static void initBaseCoinList() {
        BASE_DATA_LIST.clear();
        BASE_DATA_LIST.addAll(DataSupport.findAll(DataDictionary.class));
    }

    public static List<DataDictionary> getListByParentKey(String parentKey){
        initBaseCoinList();

        List<DataDictionary> list = new ArrayList<>();

        for (DataDictionary dataDictionary : BASE_DATA_LIST){

            if (TextUtils.equals(dataDictionary.getParentKey(), parentKey)){
                list.add(dataDictionary);
            }

        }

        return list;

    }

    public static DataDictionary getDataByKey(String parentKey, String dKey){
        initBaseCoinList();

        List<DataDictionary> list = new ArrayList<>();

        for (DataDictionary dataDictionary : BASE_DATA_LIST){

            if (TextUtils.equals(dataDictionary.getParentKey(), parentKey)){
                list.add(dataDictionary);
            }

        }

        for (DataDictionary dataDictionary : list){

            if (TextUtils.equals(dataDictionary.getDkey(), dKey)){
                return dataDictionary;
            }

        }

        return null;

    }

    public static String getValueBuyKey(String parentKey, String dKey){
        initBaseCoinList();

        List<DataDictionary> list = new ArrayList<>();

        for (DataDictionary dataDictionary : BASE_DATA_LIST){

            if (TextUtils.equals(dataDictionary.getParentKey(), parentKey)){
                list.add(dataDictionary);
            }

        }

        for (DataDictionary dataDictionary : list){

            if (TextUtils.equals(dataDictionary.getDkey(), dKey)){
                return dataDictionary.getDvalue();
            }

        }

        return "";
    }

    public static String getBizTypeBuyKey(String dKey){
        initBaseCoinList();

        List<DataDictionary> list = new ArrayList<>();

        for (DataDictionary dataDictionary : BASE_DATA_LIST){

            if (TextUtils.equals(dataDictionary.getParentKey(), budget_orde_biz_typer)){
                list.add(dataDictionary);
            }

        }

        for (DataDictionary dataDictionary : list){

            if (TextUtils.equals(dataDictionary.getDkey(), dKey)){
                return dataDictionary.getDvalue();
            }

        }

        return "";
    }


    public static String getValueBuyList(String key, List<DataDictionary> data){

        for (DataDictionary dataDictionary : data){
            if (TextUtils.equals(key, dataDictionary.getDkey()))
                return dataDictionary.getDvalue();
        }

        return "";
    }



    public static void getAllDataDictionary(Context context, AllDataDictionaryInterface listInterface){
        Map<String, String> map = new HashMap<>();
        map.put("dkey", "");
        map.put("orderColumn", "");
        map.put("orderDir", "");
        map.put("parentKey", "");
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
            protected void onReqFailure(String errorCode, String errorMessage) {
                super.onReqFailure(errorCode, errorMessage);

                if (listInterface != null)
                    listInterface.onReqFailure(errorCode, errorMessage);
            }

            @Override
            protected void onFinish() {
                clearCall();

                if (listInterface != null)
                    listInterface.onFinish();
            }
        });
    }

    public interface AllDataDictionaryInterface{

        void onSuccess(List<DataDictionary> list);

        void onReqFailure(String errorCode, String errorMessage);

        void onFinish();

    }

    private static void clearCall(){
        if (call != null)
            call.cancel();
    }
}
