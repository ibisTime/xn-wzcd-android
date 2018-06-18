package com.cdkj.wzcd.util;

import android.content.Context;
import android.text.TextUtils;

import com.cdkj.baselibrary.nets.BaseResponseListCallBack;
import com.cdkj.baselibrary.nets.RetrofitUtils;
import com.cdkj.baselibrary.utils.StringUtils;
import com.cdkj.wzcd.MainActivity;
import com.cdkj.wzcd.api.MyApiServer;
import com.cdkj.wzcd.model.NodeModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;

/**
 * Created by cdkj on 2018/6/5.
 */

public class NodeHelper {

    private static Call call;

    public static String getNameOnTheCode(String code, List<NodeModel> data){

        for (NodeModel nodeModel : data){
            if (TextUtils.equals(code, nodeModel.getCode()))
                return nodeModel.getName();
        }

        return "";
    }

    public static String getNameOnTheCode(String code){
        if (MainActivity.BASE_NODE_LIST == null || MainActivity.BASE_NODE_LIST.size() == 0)
            return "";

        if (TextUtils.isEmpty(code))
            return "";

        for (NodeModel nodeModel : MainActivity.BASE_NODE_LIST){
            if (TextUtils.equals(code, nodeModel.getCode()))
                return nodeModel.getName();
        }

        return "";
    }

    public static void getNodeBaseDataRequest(Context context, String name, String key, NodeInterface listInterface){
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("type", key);

        call = RetrofitUtils.createApi(MyApiServer.class).getNodeDataList("630147", StringUtils.getJsonToString(map));

        call.enqueue(new BaseResponseListCallBack<NodeModel>(context) {

            @Override
            protected void onSuccess(List<NodeModel> data, String SucMessage) {
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
            }
        });
    }

    public interface NodeInterface{

        void onSuccess(List<NodeModel> list);
        void onReqFailure(String errorCode, String errorMessage);

    }

    private static void clearCall(){
        if (call != null)
            call.cancel();
    }
}
