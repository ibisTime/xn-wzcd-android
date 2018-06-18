package com.cdkj.wzcd.util;

import android.content.Context;
import android.text.TextUtils;

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
 * Created by cdkj on 2018/6/5.
 */

public class BizTypeHelper {

    private static Call call;

    public static String getNameOnTheKey(String key, List<DataDictionary> data){

        for (DataDictionary dictionary : data){
            if (TextUtils.equals(key, dictionary.getDkey()))
                return dictionary.getDvalue();
        }

        return "";
    }

    public static String getNameOnTheKey(String key){
        if (MainActivity.BASE_BIZ_TYPE == null || MainActivity.BASE_BIZ_TYPE.size() == 0)
            return "";

        if (TextUtils.isEmpty(key))
            return "";

        for (DataDictionary dictionary : MainActivity.BASE_BIZ_TYPE){
            if (TextUtils.equals(key, dictionary.getDkey()))
                return dictionary.getDvalue();
        }

        return "";
    }

    public static void getBizTypeBaseDataRequest(Context context, String parentKey, BizTypeInterface listInterface){
        Map<String, String> map = new HashMap<>();
        map.put("dkey", "");
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

    public interface BizTypeInterface{

        void onSuccess(List<DataDictionary> list);
        void onReqFailure(String errorCode, String errorMessage);

    }

    private static void clearCall(){
        if (call != null)
            call.cancel();
    }
}
