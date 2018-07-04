package com.cdkj.baselibrary.nets;


import com.cdkj.baselibrary.api.BaseApiServer;
import com.cdkj.baselibrary.appmanager.SPUtilHelper;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

/**
 * 服务器api
 * Created by Administrator on 2016/9/1.
 */
public class RetrofitUtils {

    private static Retrofit retrofitInstance = null;

    public static final int RELEASE = 0; //正式环境
    public static final int DEBUG = 1;//研发环境
    public static final int TEST = 2;//测试环境
    public static final String FORWARD_SERVICE = "/forward-service/";


    private RetrofitUtils() {

    }

    /**
     * 获取Retrofit实例
     *
     * @return Retrofit
     */
    private static Retrofit getInstance() {

        if (retrofitInstance == null) {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(getBaseURL(DEBUG))
                    .client(OkHttpUtils.getInstance())
                    .addConverterFactory(FastJsonConverter.create())
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }

    /**
     * 创建Retrofit请求Api
     *
     * @param clazz Retrofit Api接口
     * @return api实例
     */
    public static <T> T createApi(Class<T> clazz) {
        return getInstance().create(clazz);
    }

    public static BaseApiServer getBaseAPiService() {
        return createApi(BaseApiServer.class);
    }

    /**
     * 获取URL  根据版本切换不同版本
     *
     * @return
     */
    public static String getBaseURL(int urlType) {
        switch (urlType) {
            case DEBUG:
                return "http://120.26.6.213:2601/forward-service/";//研发环境

            case TEST:
                return "http://47.96.161.183:2601/forward-service/";//测试

        }
        return "http://39.104.89.43:2601/forward-service/";//正式环境
    }

    /**
     * 添加公共请求参数
     *
     * @return
     */
    public static Map getRequestMap() {
        Map map = new HashMap();
        map.put("token", SPUtilHelper.getUserToken());
        return map;
    }

    /**
     * 节点请求参数
     *
     * @return
     */
    public static Map getNodeListMap() {
        Map map = new HashMap();
        map.put("token", SPUtilHelper.getUserToken());
        map.put("roleCode", SPUtilHelper.getRoleCode());
        return map;
    }

}
