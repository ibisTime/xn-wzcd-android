package com.cdkj.wzcd.api;

import com.cdkj.baselibrary.api.BaseResponseListModel;
import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.wzcd.model.AdvanceFundModel;
import com.cdkj.wzcd.model.CreditModel;
import com.cdkj.wzcd.model.DataTransferModel;
import com.cdkj.wzcd.model.DealersModel;
import com.cdkj.wzcd.model.ExchangeBankModel;
import com.cdkj.wzcd.model.GpsApplyModel;
import com.cdkj.wzcd.model.GpsModel;
import com.cdkj.wzcd.model.LoanProductModel;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.model.NodeModel;
import com.cdkj.wzcd.model.RepaymentModel;
import com.cdkj.wzcd.model.SystemParameterModel;
import com.cdkj.wzcd.model.UserModel;
import com.cdkj.wzcd.model.ZrdModel;
import com.cdkj.wzcd.model.event.IdCardModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by cdkj on 2018/5/29.
 */

public interface MyApiServer {

//    /**
//     * 获取腾讯用户签名
//     * @param code
//     * @param json
//     * @return
//     */
//    @FormUrlEncoded
//    @POST("api")
//    Call<BaseResponseModel<TencentSignModel>> getTencentSign(@Field("code") String code, @Field("json") String json);

    /**
     * 获取用户信息详情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<UserModel>> getUserInfoDetails(@Field("code") String code, @Field("json") String json);


    /**
     * 获取数据字典
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<DataDictionary>> getDataDictionary(@Field("code") String code, @Field("json") String json);

    /**
     * 获取平台银行
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<ExchangeBankModel>> getBankList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取平台银行
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ExchangeBankModel>> getBank(@Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<NodeListModel>>> getNodeList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<NodeModel>> getNodeDataList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<NodeListModel>> getNode(@Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<SystemParameterModel>> getSystemParameter(@Field("code") String code, @Field("json") String json);


    //--------------------------------------------征信API--------------------------------------------

    /**
     * 获征信列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<CreditModel>>> getCreditList(@Field("code") String code, @Field("json") String json);

    /**
     * 获征信详情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<CreditModel>> getCredit(@Field("code") String code, @Field("json") String json);

    /**
     * 获征信详情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<IdCardModel>> analysisIdCard(@Field("code") String code, @Field("json") String json);


    //--------------------------------------------GPS申领API--------------------------------------------

    /**
     * 获取申领列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<GpsApplyModel>>> getGPSApplyList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取申领列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<GpsApplyModel>> getGps(@Field("code") String code, @Field("json") String json);

    /**
     * 获取申领列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<GpsModel>> getGpsList(@Field("code") String code, @Field("json") String json);


    //--------------------------------------------资料传递--------------------------------------------

    /**
     * 获取申领列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<DataTransferModel>>> getDataTransfer(@Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<DataTransferModel>> getData(@Field("code") String code, @Field("json") String json);


    //--------------------------------------------历史客户--------------------------------------------

    /**
     * 分页查还款业务
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<RepaymentModel>>> getRepaymentList(@Field("code") String code, @Field("json") String json);

    /**
     * 分页查还款业务
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<RepaymentModel>> getRepayment(@Field("code") String code, @Field("json") String json);

    //--------------------------------------------贷前准入--------------------------------------------

    /**
     * 获取平台银行
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<LoanProductModel>> getLoanProduct(@Field("code") String code, @Field("json") String json);

    /**
     * 获取准入单列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<ZrdModel>>> getZrdList(@Field("code") String code, @Field("json") String json);


    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<DealersModel>> getDealersList(@Field("code") String code, @Field("json") String json);


    //--------------------------------------------财务垫资--------------------------------------------

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<AdvanceFundModel>>> getAdvanceFundList(@Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<AdvanceFundModel>> getAdvanceFund(@Field("code") String code, @Field("json") String json);

}
