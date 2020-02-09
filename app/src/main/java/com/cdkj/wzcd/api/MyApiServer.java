package com.cdkj.wzcd.api;

import com.cdkj.baselibrary.api.BaseResponseListModel;
import com.cdkj.baselibrary.api.BaseResponseModel;
import com.cdkj.baselibrary.api.ResponseInListModel;
import com.cdkj.baselibrary.model.DataDictionary;
import com.cdkj.baselibrary.model.IsSuccessModes;
import com.cdkj.wzcd.main.credit.bean.AscriptionBean;
import com.cdkj.wzcd.main.credit.bean.CarVINDetailBean;
import com.cdkj.wzcd.main.credit.bean.ConfirmBean;
import com.cdkj.wzcd.main.credit.bean.CreditPageBean;
import com.cdkj.wzcd.main.credit.bean.GPSDetialsGPSListBean;
import com.cdkj.wzcd.main.credit.bean.GPSPermissionBean;
import com.cdkj.wzcd.main.credit.bean.GPSSHBean;
import com.cdkj.wzcd.main.credit.bean.GPSSHDetialsBean;
import com.cdkj.wzcd.main.credit.bean.JurisdictionBean;
import com.cdkj.wzcd.main.credit.module.lrfk.bean.YhhtBean;
import com.cdkj.wzcd.main.credit.module.rd.bean.BXCompanyBean;
import com.cdkj.wzcd.main.credit.module.rd.bean.RdPlaceBean;
import com.cdkj.wzcd.main.credit.module.yksq.bean.TeamBean;
import com.cdkj.wzcd.main.credit.module.zdhl.bean.ShoukuanAccountBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CarBrandBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CarModelBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CarSeriesBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.CzrzBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.DkrxxIdBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.GPSSaleBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.HkjhBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlMonthAmountBean;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlReportBean;
import com.cdkj.wzcd.main.message.bean.AgendaBean;
import com.cdkj.wzcd.main.message.bean.MessageBean;
import com.cdkj.wzcd.model.AdvanceFundModel;
import com.cdkj.wzcd.model.BanksModel;
import com.cdkj.wzcd.model.CalculautorModel;
import com.cdkj.wzcd.model.CreditModel;
import com.cdkj.wzcd.model.DataTransferModel;
import com.cdkj.wzcd.model.DealersModel;
import com.cdkj.wzcd.model.ExchangeBankModel;
import com.cdkj.wzcd.model.GpsApplyModel;
import com.cdkj.wzcd.model.GpsModel;
import com.cdkj.wzcd.model.LoanProductModel;
import com.cdkj.wzcd.model.LocalityModel;
import com.cdkj.wzcd.model.NodeListModel;
import com.cdkj.wzcd.model.NodeModel;
import com.cdkj.wzcd.model.RepaymentModel;
import com.cdkj.wzcd.model.SalesmanModel;
import com.cdkj.wzcd.model.SystemParameterModel;
import com.cdkj.wzcd.model.TodoBean;
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


    /**
     * 通用提交
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ConfirmBean>> confirm(@Field("code") String code,
                                                 @Field("json") String json);

    /**
     * 待办事项小红点
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<TodoBean>> getTodoData(@Field("code") String code,
                                                  @Field("json") String json);

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
    Call<BaseResponseModel<UserModel>> getUserInfoDetails(@Field("code") String code,
                                                          @Field("json") String json);


    /**
     * 获取数据字典
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<DataDictionary>> getDataDictionary(@Field("code") String code,
                                                                  @Field("json") String json);

    /**
     * 获取平台银行
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<ExchangeBankModel>> getBankList(@Field("code") String code,
                                                               @Field("json") String json);

    /**
     * 获取平台银行
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ExchangeBankModel>> getBank(@Field("code") String code,
                                                       @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<NodeListModel>>> getNodeList(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<NodeModel>> getNodeDataList(@Field("code") String code,
                                                           @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<NodeListModel>> getNode(@Field("code") String code,
                                                   @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<SystemParameterModel>> getSystemParameter(@Field("code") String code,
                                                                     @Field("json") String json);

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
    Call<BaseResponseModel<ResponseInListModel<CreditModel>>> getCreditList(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获征信详情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<CreditModel>> getCredit(@Field("code") String code,
                                                   @Field("json") String json);

    /**
     * 获征信详情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<IdCardModel>> analysisIdCard(@Field("code") String code,
                                                        @Field("json") String json);

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
    Call<BaseResponseModel<ResponseInListModel<GpsApplyModel>>> getGPSApplyList(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取申领列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<GpsApplyModel>> getGps(@Field("code") String code,
                                                  @Field("json") String json);

    /**
     * 获取申领列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<GpsModel>> getGpsList(@Field("code") String code,
                                                     @Field("json") String json);

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
    Call<BaseResponseModel<ResponseInListModel<DataTransferModel>>> getDataTransfer(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<DataTransferModel>> getData(@Field("code") String code,
                                                       @Field("json") String json);

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
    Call<BaseResponseModel<ResponseInListModel<RepaymentModel>>> getRepaymentList(
            @Field("code") String code, @Field("json") String json);

    /**
     * 分页查还款业务
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<RepaymentModel>> getRepayment(@Field("code") String code,
                                                         @Field("json") String json);

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
    Call<BaseResponseListModel<LoanProductModel>> getLoanProduct(@Field("code") String code,
                                                                 @Field("json") String json);

    /**
     * 获取准入单列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<ZrdModel>>> getZrdList(@Field("code") String code,
                                                                      @Field("json") String json);


    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<DealersModel>> getDealersList(@Field("code") String code,
                                                             @Field("json") String json);

    //--------------------------------------------月供计算器↓↓↓↓↓↓--------------------------------------------

    /**
     * 获取银行
     * 632037
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<BanksModel>> getBanksList(@Field("code") String code,
                                                         @Field("json") String json);

    /**
     * 计算月供
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<CalculautorModel>> calculautor(@Field("code") String code,
                                                          @Field("json") String json);

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
    Call<BaseResponseModel<ResponseInListModel<AdvanceFundModel>>> getAdvanceFundList(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<AdvanceFundModel>> getAdvanceFund(@Field("code") String code,
                                                             @Field("json") String json);

    /**
     * 一审不通过
     * 632114
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<IsSuccessModes>> firstInstanceNo(@Field("code") String code,
                                                            @Field("json") String json);

//--------------------------------------------新--------------------------------------------


    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<CreditPageBean>>> getCreditPage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取用户权限
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<JurisdictionBean>> getJurisdiction(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取业务员
     * 630066
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<SalesmanModel>> getSalesmanList(@Field("code") String code,
                                                               @Field("json") String json);

    /**
     * 获取业务发生地
     * 630475
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<LocalityModel>>> getLocalityPage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取汽车经销商
     * 632065
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<DealersModel>>> getDealersPage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取公司归口
     * 632905
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<AscriptionBean>>> getAscription(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<CarBrandBean>> getCarBrandList(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<CarSeriesBean>>> getCarSeriesPage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<CarModelBean>>> getCarModelsPage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ZrzlReportBean>> getZrzlReport(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获征信详情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<DkrxxIdBean>> idRecognition(@Field("code") String code,
                                                       @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ZrzlBean>> getZrzlDetail(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ZrzlMonthAmountBean>> getZrzlMonthAmount(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<GPSSaleBean>> getGPSSaleList(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取业务发生地
     * 630475
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<ShoukuanAccountBean>>> getShoukuanAccountPage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 分页查询档案存放位置
     * 632825
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<RdPlaceBean>>> getRdPlacePage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 分页查保险公司
     * 632045
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<BXCompanyBean>>> getBXCompanyPage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 分页查保险公司
     * 632045
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<YhhtBean>>> getYhhtPage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<MessageBean>>> getMessagePage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<HkjhBean>> getHkjh(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<CzrzBean>>> getCzrzPage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<AgendaBean>>> getAgendaPage(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<TeamBean>> getTeam(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取节列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<CarVINDetailBean>> getCarVINDetail(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取GPS审核 列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<ResponseInListModel<GPSSHBean>>> getGPSExamine(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取GPS审核 列表 详情
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseModel<GPSSHDetialsBean>> getGPSExamineDetials(
            @Field("code") String code, @Field("json") String json);

    /**
     * 获取GPS审核 列表 详情 可选择的gps列表
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<GPSDetialsGPSListBean>> getGPSExamineDetialsGPSList(
            @Field("code") String code, @Field("json") String json);


    /**
     * 获取有没有GPS审核 权限
     *
     * @param code
     * @param json
     * @return
     */
    @FormUrlEncoded
    @POST("api")
    Call<BaseResponseListModel<GPSPermissionBean>> getGPSExaminePermission(
            @Field("code") String code, @Field("json") String json);

//    @FormUrlEncoded
//    @POST("api")
//    Call<BaseResponseModel<GPSPermissionBean>> sendPushToken(
//            @Field("code") String code, @Field("json") String json);
}
