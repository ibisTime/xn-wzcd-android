package com.cdkj.wzcd.model;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cdkj on 2018/6/4.
 */

public class NodeListModel {

    /**
     * code : BG201806261341079951458
     * creditCode : C201806261339409108880
     * customerType : 1
     * customerName : 大黄
     * carDealerCode : CD201806200836148544767
     * loanBankCode : BS201806200844047884304
     * originalPrice : 400000000
     * carModel : 22
     * loanPeriods : 12
     * invoicePrice : 3333000
     * shopWay : 1
     * rateType : 1
     * loanAmount : 333000
     * isSurvey : 1
     * bankRate : 0.5
     * companyLoanCs : 0.09
     * isAdvanceFund : 1
     * globalRate : 1.16
     * fee : 222000
     * carDealerSubsidy : 333000
     * bankLoanCs : 0.16
     * companyCode : DP201800000000000000001
     * saleUserId : U201806200828433021931
     * applyUserCompany : 申请人就职单位
     * applyUserDuty : 申请人职位
     * applyUserMonthIncome : 4444000
     * applyUserSettleInterest : 333000
     * applyUserBalance : 5555000
     * applyUserJourShowIncome : 1
     * applyUserIsPrint : 1
     * ghMonthIncome : 3333000
     * ghSettleInterest : 2222000
     * ghBalance : 6666000
     * ghJourShowIncome : 0
     * ghIsPrint : 0
     * guarantor1MonthIncome : 3333000
     * guarantor1SettleInterest : 3333000
     * guarantor1Balance : 3333000
     * guarantor1JourShowIncome : 1
     * guarantor1IsPrint : 1
     * guarantor2MonthIncome : 3333000
     * guarantor2SettleInterest : 3333000
     * guarantor2Balance : 3333000
     * guarantor2JourShowIncome : 1
     * guarantor2IsPrint : 1
     * isHouseProperty : 1
     * houseProperty : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * houseInvoice : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * isLicense : 1
     * license : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * isSiteProve : 1
     * siteProve : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * siteArea : 111111111111
     * carType : 0
     * isDriveLicense : 1
     * driveLicense : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * otherPropertyNote : 其他资产说明
     * applyBirthAddress : 申请人户籍地
     * applyNowAddress : 现住地址
     * houseType : 0
     * ghBirthAddress : 共还人户籍地
     * guarantor1BirthAddress : 担保人1户籍地
     * guarantor2BirthAddress : 担保人2户籍地
     * otherNote : 其他情况说明
     * oilSubsidy : 111
     * oilSubsidyKil : 22
     * isPlatInsure : 1
     * gpsFee : 3330
     * gpsDeduct : 44
     * gpsFeeWay : 1
     * lyAmount : 9990
     * fxAmount : 3330
     * otherFee : 2222000
     * feeWay : 1
     * serviceCharge : 2238650
     * marryDivorce : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * applyUserHkb : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * bankBillPdf : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * singleProvePdf : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * incomeProvePdf : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * liveProvePdf : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * buildProvePdf : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * hkbFirstPage : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * hkbMainPage : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * ghHkb : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * guarantor1IdNo : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * guarantor1Hkb : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * guarantor2IdNo : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * guarantor2Hkb : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * housePic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * houseUnitPic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * houseDoorPic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * houseRoomPic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * houseCustomerPic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * houseSaleCustomerPic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * companyNamePic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * companyPlacePic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * companyWorkshopPic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * companySaleCustomerPic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * secondHgz : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * secondOdometer : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * secondConsolePic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * second300Pdf : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * secondQxbPic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * secondCarInPic : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * secondNumber : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * otherFilePdf : FqNG4fbHn0pM5jzqn7G0j2COLi7M
     * otherApplyNote : 备注事项
     * applyDatetime : Jun 26, 2018 9:36:52 PM
     * insuranceRemindCount : 0
     * repayBankDate : 0
     * mobile : 18202771070
     * idKind : 1
     * idNo : 150606199809040312
     * curNodeCode : 002_01
     * carDealerName : 全称1
     * loanBankName : 中国工商银行
     * saleUserName : 悟空
     * companyName : 温州浩源有限公司
     * budgetOrderGpsList : [{"code":"G201806200848491196500","gpsDevNo":"007","gpsType":"1","status":"1","budgetOrder":"BG201806261341079951458"}]
     * bankSubbranch : {"code":"BS201806200844047884304","bankCode":"BA201806200837173794638","bankType":"ICBC","abbrName":"支行简称","fullName":"支行全称","openBank":"开户行","address":"银行地址","phoneNumber":"13222222222","postCode":"000000","bankClient":"银行委托人","autherName":"授权人姓名","autherPhoneNumber":"15522222222","autherIdNo":"150404199806080808","autherAddress":"授权人地址","creditCardType":"1","creditCardName":"信用卡名称","belongArea":"所属地区","updater":"USYS201800000000001","updateDatetime":"Jun 20, 2018 8:44:04 AM","remark":"备注","bank":{"code":"BA201806200837173794638","bankCode":"ICBC","bankName":"中国工商银行","status":"1","updater":"USYS201800000000001","updateDatetime":"Jun 20, 2018 8:37:17 AM","remark":"备注","bankRateList":[{"id":1,"bankCode":"BA201806200837173794638","period":12,"rate":0.5,"remark":"备注"}]}}
     * repointDetailList1 : [{"code":"RD201806261346505167557","budgetCode":"BG201806261341079951458","userName":"大黄","idNo":"150606199809040312","companyCode":"DP201800000000000000001","carType":"0","loanAmount":333000,"bankRate":0.5,"fee":222000,"useMoneyPurpose":"1","repointAmount":0,"curNodeCode":"0"},{"code":"RD201806262136524784811","budgetCode":"BG201806261341079951458","userName":"大黄","idNo":"150606199809040312","companyCode":"DP201800000000000000001","carType":"0","loanAmount":333000,"bankRate":0.5,"fee":222000,"useMoneyPurpose":"1","repointAmount":0,"curNodeCode":"0"}]
     * repointDetailList2 : [{"code":"RD201806261346505194611","budgetCode":"BG201806261341079951458","userName":"大黄","idNo":"150606199809040312","companyCode":"DP201800000000000000001","carType":"0","loanAmount":333000,"bankRate":0.5,"benchmarkRate":0.1,"fee":222000,"useMoneyPurpose":"2","repointAmount":104835,"accountCode":"222222222222222","curNodeCode":"0"},{"code":"RD201806262136524815542","budgetCode":"BG201806261341079951458","userName":"大黄","idNo":"150606199809040312","companyCode":"DP201800000000000000001","carType":"0","loanAmount":333000,"bankRate":0.5,"benchmarkRate":0.1,"fee":222000,"useMoneyPurpose":"2","repointAmount":104835,"accountCode":"222222222222222","curNodeCode":"0"}]
     * repointDetailList3 : []
     * companyLoanCsSection : 0.6-0.9
     */

    private String code;
    private String zfReason;
    private String ghRealName;
    private String guarantor1Name;//担保人
    //紧急联系人  1   2
    private String emergencyName1;
    private String emergencyRelation1;
    private String emergencyMobile1;
    private String emergencyName2;
    private String emergencyRelation2;
    private String emergencyMobile2;

    private String creditCode;
    private String customerType;
    private String customerName;
    private String carDealerCode;
    private String loanBankCode;
    private String originalPrice;
    private String carModel;
    private String loanPeriods;
    private String invoicePrice;
    private String shopWay;
    private String rateType;
    private String loanAmount;
    private String isSurvey;
    private double bankRate;
    private double companyLoanCs;
    private String isAdvanceFund;
    private String frameNo;
    private double globalRate;
    private String fee;
    private String carDealerSubsidy;
    private double bankLoanCs;
    private String companyCode;
    private String saleUserId;
    private String applyUserCompany;
    private String applyUserDuty;
    private String ApplyUserGhrRelation;
    private String marryState;
    private String applyUserMonthIncome;
    private String applyUserSettleInterest;
    private String applyUserBalance;
    private String applyUserJourShowIncome;
    private String applyUserIsPrint;
    private String ghMonthIncome;
    private String ghSettleInterest;
    private String ghBalance;
    private String ghJourShowIncome;
    private String ghIsPrint;
    private String guarantor1MonthIncome;
    private String guarantor1SettleInterest;
    private String guarantor1Balance;
    private String guarantor1JourShowIncome;
    private String guarantor1IsPrint;
    private String guarantor2MonthIncome;
    private String guarantor2SettleInterest;
    private String guarantor2Balance;
    private String guarantor2JourShowIncome;
    private String guarantor2IsPrint;
    private String otherIncomeNote;
    private String isHouseProperty;
    private String houseProperty;
    private String houseInvoice;
    private String isLicense;
    private String license;
    private String isSiteProve;
    private String siteProve;
    private String siteArea;
    private String carType;
    private String isDriceLicense;
    private String driveLicense;
    private String otherPropertyNote;
    private String applyBirthAddress;
    private String applyNowAddress;
    private String houseType;
    private String ghBirthAddress;
    private String guarantor1BirthAddress;
    private String guarantor2BirthAddress;
    private String otherNote;
    private String oilSubsidy;
    private int oilSubsidyKil;
    private String isPlatInsure;
    private String gpsFee;
    private String gpsDeduct;
    private String gpsFeeWay;
    private String lyAmount;
    private String fxAmount;
    private String otherFee;
    private String feeWay;
    private String serviceCharge;
    private String marryDivorce;
    private String applyUserHkb;
    private String bankBillPdf;
    private String singleProvePdf;
    private String incomeProvePdf;
    private String liveProvePdf;
    private String buildProvePdf;
    private String hkbFirstPage;
    private String hkbMainPage;
    private String ghHkb;
    private String guarantor1IdNo;
    private String guarantor1Hkb;
    private String guarantor2IdNo;
    private String guarantor2Hkb;
    private String housePic;
    private String houseUnitPic;
    private String houseDoorPic;
    private String houseRoomPic;
    private String houseCustomerPic;
    private String houseSaleCustomerPic;
    private String companyNamePic;
    private String companyPlacePic;
    private String companyWorkshopPic;
    private String companySaleCustomerPic;
    private String secondHgz;
    private String secondOdometer;
    private String secondConsolePic;
    private String second300Pdf;
    private String secondQxbPic;
    private String secondCarInPic;
    private String secondNumber;
    private String otherFilePdf;
    private String otherApplyNote;
    private String applyDatetime;
    private String insuranceRemindCount;
    private String repayBankDate;
    private String mobile;
    private String idKind;
    private String idNo;
    private String curNodeCode;
    private String carDealerName;
    private String outCarDealerName;
    private String loanBankName;
    private String saleUserName;
    private String companyName;
    private BankSubbranchBean bankSubbranch;
    private String companyLoanCsSection;
    private List<BudgetOrderGpsListBean> budgetOrderGpsList;
    private List<RepointDetailListBean> repointDetailList1;
    private List<RepointDetailListBean> repointDetailList2;
    private List<RepointDetailListBean> repointDetailList3;

    private String makeCardStatus;
    private String advanceFundAmount;
    private String carBrand;
    private String invoice;
    private String certification;
    private String forceInsurance;
    private String businessInsurance;
    private String motorRegCertification;
    private String pdPdf;
    private String deliveryDatetime;
    private String isRightInvoice;
    private String remark;
    private String useAmount;
    private String collectBankcardCode;
    private String currentInvoicePrice;
    private String advanceFundDatetime;
    private String serviceChargeWay;
    private String secondCarFrontPic;
    private String ghIdNo;
    private String fbhStatus;
    private String carColor;
    private String forceInsurancePdf;
    private String driceLicense;
    private Credit credit;

    public String getDriceLicense() {
        return driceLicense;
    }

    public void setDriceLicense(String driceLicense) {
        this.driceLicense = driceLicense;
    }

    public String getForceInsurancePdf() {
        return forceInsurancePdf;
    }

    public void setForceInsurancePdf(String forceInsurancePdf) {
        this.forceInsurancePdf = forceInsurancePdf;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getFbhStatus() {
        return fbhStatus;
    }

    public void setFbhStatus(String fbhStatus) {
        this.fbhStatus = fbhStatus;
    }

    public String getGhIdNo() {
        return ghIdNo;
    }

    public void setGhIdNo(String ghIdNo) {
        this.ghIdNo = ghIdNo;
    }

    public String getSecondCarFrontPic() {
        return secondCarFrontPic;
    }

    public void setSecondCarFrontPic(String secondCarFrontPic) {
        this.secondCarFrontPic = secondCarFrontPic;
    }

    public String getServiceChargeWay() {
        return serviceChargeWay;
    }

    public void setServiceChargeWay(String serviceChargeWay) {
        this.serviceChargeWay = serviceChargeWay;
    }

    public String getZfReason() {
        return zfReason;
    }

    public void setZfReason(String zfReason) {
        this.zfReason = zfReason;
    }


    public String getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(String frameNo) {
        this.frameNo = frameNo;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }


    public String getEmergencyName1() {
        return emergencyName1;
    }

    public void setEmergencyName1(String emergencyName1) {
        this.emergencyName1 = emergencyName1;
    }

    public String getEmergencyRelation1() {
        return emergencyRelation1;
    }

    public void setEmergencyRelation1(String emergencyRelation1) {
        this.emergencyRelation1 = emergencyRelation1;
    }

    public String getEmergencyMobile1() {
        return emergencyMobile1;
    }

    public void setEmergencyMobile1(String emergencyMobile1) {
        this.emergencyMobile1 = emergencyMobile1;
    }

    public String getEmergencyName2() {
        return emergencyName2;
    }

    public void setEmergencyName2(String emergencyName2) {
        this.emergencyName2 = emergencyName2;
    }

    public String getEmergencyRelation2() {
        return emergencyRelation2;
    }

    public void setEmergencyRelation2(String emergencyRelation2) {
        this.emergencyRelation2 = emergencyRelation2;
    }

    public String getEmergencyMobile2() {
        return emergencyMobile2;
    }

    public void setEmergencyMobile2(String emergencyMobile2) {
        this.emergencyMobile2 = emergencyMobile2;
    }

    public String getGuarantor1Name() {
        return guarantor1Name;
    }

    public void setGuarantor1Name(String guarantor1Name) {
        this.guarantor1Name = guarantor1Name;
    }

    public String getGhRealName() {
        return ghRealName;
    }

    public void setGhRealName(String ghRealName) {
        this.ghRealName = ghRealName;
    }


    public String getOtherIncomeNote() {
        return otherIncomeNote;
    }

    public void setOtherIncomeNote(String otherIncomeNote) {
        this.otherIncomeNote = otherIncomeNote;
    }

    public String getApplyUserGhrRelation() {
        return ApplyUserGhrRelation;
    }

    public void setApplyUserGhrRelation(String applyUserGhrRelation) {
        ApplyUserGhrRelation = applyUserGhrRelation;
    }

    public String getMarryState() {
        return marryState;
    }

    public void setMarryState(String marryState) {
        this.marryState = marryState;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getCarDealerSubsidy() {
        return carDealerSubsidy;
    }

    public void setCarDealerSubsidy(String carDealerSubsidy) {
        this.carDealerSubsidy = carDealerSubsidy;
    }

    public String getApplyUserMonthIncome() {
        return applyUserMonthIncome;
    }

    public void setApplyUserMonthIncome(String applyUserMonthIncome) {
        this.applyUserMonthIncome = applyUserMonthIncome;
    }

    public String getApplyUserSettleInterest() {
        return applyUserSettleInterest;
    }

    public void setApplyUserSettleInterest(String applyUserSettleInterest) {
        this.applyUserSettleInterest = applyUserSettleInterest;
    }

    public String getApplyUserBalance() {
        return applyUserBalance;
    }

    public void setApplyUserBalance(String applyUserBalance) {
        this.applyUserBalance = applyUserBalance;
    }

    public String getGhMonthIncome() {
        return ghMonthIncome;
    }

    public void setGhMonthIncome(String ghMonthIncome) {
        this.ghMonthIncome = ghMonthIncome;
    }

    public String getGhSettleInterest() {
        return ghSettleInterest;
    }

    public void setGhSettleInterest(String ghSettleInterest) {
        this.ghSettleInterest = ghSettleInterest;
    }

    public String getGhBalance() {
        return ghBalance;
    }

    public void setGhBalance(String ghBalance) {
        this.ghBalance = ghBalance;
    }

    public String getGuarantor1MonthIncome() {
        return guarantor1MonthIncome;
    }

    public void setGuarantor1MonthIncome(String guarantor1MonthIncome) {
        this.guarantor1MonthIncome = guarantor1MonthIncome;
    }

    public String getGuarantor1SettleInterest() {
        return guarantor1SettleInterest;
    }

    public void setGuarantor1SettleInterest(String guarantor1SettleInterest) {
        this.guarantor1SettleInterest = guarantor1SettleInterest;
    }

    public String getGuarantor1Balance() {
        return guarantor1Balance;
    }

    public void setGuarantor1Balance(String guarantor1Balance) {
        this.guarantor1Balance = guarantor1Balance;
    }

    public String getGuarantor2MonthIncome() {
        return guarantor2MonthIncome;
    }

    public void setGuarantor2MonthIncome(String guarantor2MonthIncome) {
        this.guarantor2MonthIncome = guarantor2MonthIncome;
    }

    public String getGuarantor2SettleInterest() {
        return guarantor2SettleInterest;
    }

    public void setGuarantor2SettleInterest(String guarantor2SettleInterest) {
        this.guarantor2SettleInterest = guarantor2SettleInterest;
    }

    public String getGuarantor2Balance() {
        return guarantor2Balance;
    }

    public void setGuarantor2Balance(String guarantor2Balance) {
        this.guarantor2Balance = guarantor2Balance;
    }

    public String getOilSubsidy() {
        return oilSubsidy;
    }

    public void setOilSubsidy(String oilSubsidy) {
        this.oilSubsidy = oilSubsidy;
    }

    public int getOilSubsidyKil() {
        return oilSubsidyKil;
    }

    public void setOilSubsidyKil(int oilSubsidyKil) {
        this.oilSubsidyKil = oilSubsidyKil;
    }

    public String getGpsFee() {
        return gpsFee;
    }

    public void setGpsFee(String gpsFee) {
        this.gpsFee = gpsFee;
    }

    public String getGpsDeduct() {
        return gpsDeduct;
    }

    public void setGpsDeduct(String gpsDeduct) {
        this.gpsDeduct = gpsDeduct;
    }

    public String getLyAmount() {
        return lyAmount;
    }

    public void setLyAmount(String lyAmount) {
        this.lyAmount = lyAmount;
    }

    public String getFxAmount() {
        return fxAmount;
    }

    public void setFxAmount(String fxAmount) {
        this.fxAmount = fxAmount;
    }

    public String getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee;
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public String getInsuranceRemindCount() {
        return insuranceRemindCount;
    }

    public void setInsuranceRemindCount(String insuranceRemindCount) {
        this.insuranceRemindCount = insuranceRemindCount;
    }

    public String getRepayBankDate() {
        return repayBankDate;
    }

    public void setRepayBankDate(String repayBankDate) {
        this.repayBankDate = repayBankDate;
    }

    public String getAdvanceFundDatetime() {
        return advanceFundDatetime;
    }

    public void setAdvanceFundDatetime(String advanceFundDatetime) {
        this.advanceFundDatetime = advanceFundDatetime;
    }

    public String getCurrentInvoicePrice() {
        return currentInvoicePrice;
    }

    public void setCurrentInvoicePrice(String currentInvoicePrice) {
        this.currentInvoicePrice = currentInvoicePrice;
    }

    public String getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(String useAmount) {
        this.useAmount = useAmount;
    }

    public String getCollectBankcardCode() {
        return collectBankcardCode;
    }

    public void setCollectBankcardCode(String collectBankcardCode) {
        this.collectBankcardCode = collectBankcardCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDeliveryDatetime() {
        return deliveryDatetime;
    }

    public void setDeliveryDatetime(String deliveryDatetime) {
        this.deliveryDatetime = deliveryDatetime;
    }

    public String getIsRightInvoice() {
        return isRightInvoice;
    }

    public void setIsRightInvoice(String isRightInvoice) {
        this.isRightInvoice = isRightInvoice;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getCertification() {
        return certification;
    }

    public void setCertification(String certification) {
        this.certification = certification;
    }

    public String getForceInsurance() {
        return forceInsurance;
    }

    public void setForceInsurance(String forceInsurance) {
        this.forceInsurance = forceInsurance;
    }

    public String getBusinessInsurance() {
        return businessInsurance;
    }

    public void setBusinessInsurance(String businessInsurance) {
        this.businessInsurance = businessInsurance;
    }

    public String getMotorRegCertification() {
        return motorRegCertification;
    }

    public void setMotorRegCertification(String motorRegCertification) {
        this.motorRegCertification = motorRegCertification;
    }

    public String getPdPdf() {
        return pdPdf;
    }

    public void setPdPdf(String pdPdf) {
        this.pdPdf = pdPdf;
    }

    public String getMakeCardStatus() {
        return makeCardStatus;
    }

    public void setMakeCardStatus(String makeCardStatus) {
        this.makeCardStatus = makeCardStatus;
    }

    public String getAdvanceFundAmount() {
        return advanceFundAmount;
    }

    public void setAdvanceFundAmount(String advanceFundAmount) {
        this.advanceFundAmount = advanceFundAmount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCarDealerCode() {
        return carDealerCode;
    }

    public void setCarDealerCode(String carDealerCode) {
        this.carDealerCode = carDealerCode;
    }

    public String getLoanBankCode() {
        return loanBankCode;
    }

    public void setLoanBankCode(String loanBankCode) {
        this.loanBankCode = loanBankCode;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getLoanPeriods() {
        return loanPeriods;
    }

    public void setLoanPeriods(String loanPeriods) {
        this.loanPeriods = loanPeriods;
    }

    public String getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(String invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public String getShopWay() {
        return shopWay;
    }

    public void setShopWay(String shopWay) {
        this.shopWay = shopWay;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getIsSurvey() {
        return isSurvey;
    }

    public void setIsSurvey(String isSurvey) {
        this.isSurvey = isSurvey;
    }

    public double getBankRate() {
        return bankRate;
    }

    public void setBankRate(double bankRate) {
        this.bankRate = bankRate;
    }

    public double getCompanyLoanCs() {
        return companyLoanCs;
    }

    public void setCompanyLoanCs(double companyLoanCs) {
        this.companyLoanCs = companyLoanCs;
    }

    public String getIsAdvanceFund() {
        return isAdvanceFund;
    }

    public void setIsAdvanceFund(String isAdvanceFund) {
        this.isAdvanceFund = isAdvanceFund;
    }

    public double getGlobalRate() {
        return globalRate;
    }

    public void setGlobalRate(double globalRate) {
        this.globalRate = globalRate;
    }

    public double getBankLoanCs() {
        return bankLoanCs;
    }

    public void setBankLoanCs(double bankLoanCs) {
        this.bankLoanCs = bankLoanCs;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getSaleUserId() {
        return saleUserId;
    }

    public void setSaleUserId(String saleUserId) {
        this.saleUserId = saleUserId;
    }

    public String getApplyUserCompany() {
        return applyUserCompany;
    }

    public void setApplyUserCompany(String applyUserCompany) {
        this.applyUserCompany = applyUserCompany;
    }

    public String getApplyUserDuty() {
        return applyUserDuty;
    }

    public void setApplyUserDuty(String applyUserDuty) {
        this.applyUserDuty = applyUserDuty;
    }

    public String getApplyUserJourShowIncome() {
        return applyUserJourShowIncome;
    }

    public void setApplyUserJourShowIncome(String applyUserJourShowIncome) {
        this.applyUserJourShowIncome = applyUserJourShowIncome;
    }

    public String getApplyUserIsPrint() {
        return applyUserIsPrint;
    }

    public void setApplyUserIsPrint(String applyUserIsPrint) {
        this.applyUserIsPrint = applyUserIsPrint;
    }

    public String getGhJourShowIncome() {
        return ghJourShowIncome;
    }

    public void setGhJourShowIncome(String ghJourShowIncome) {
        this.ghJourShowIncome = ghJourShowIncome;
    }

    public String getGhIsPrint() {
        return ghIsPrint;
    }

    public void setGhIsPrint(String ghIsPrint) {
        this.ghIsPrint = ghIsPrint;
    }

    public String getGuarantor1JourShowIncome() {
        return guarantor1JourShowIncome;
    }

    public void setGuarantor1JourShowIncome(String guarantor1JourShowIncome) {
        this.guarantor1JourShowIncome = guarantor1JourShowIncome;
    }

    public String getGuarantor1IsPrint() {
        return guarantor1IsPrint;
    }

    public void setGuarantor1IsPrint(String guarantor1IsPrint) {
        this.guarantor1IsPrint = guarantor1IsPrint;
    }

    public String getGuarantor2JourShowIncome() {
        return guarantor2JourShowIncome;
    }

    public void setGuarantor2JourShowIncome(String guarantor2JourShowIncome) {
        this.guarantor2JourShowIncome = guarantor2JourShowIncome;
    }

    public String getGuarantor2IsPrint() {
        return guarantor2IsPrint;
    }

    public void setGuarantor2IsPrint(String guarantor2IsPrint) {
        this.guarantor2IsPrint = guarantor2IsPrint;
    }

    public String getIsHouseProperty() {
        return isHouseProperty;
    }

    public void setIsHouseProperty(String isHouseProperty) {
        this.isHouseProperty = isHouseProperty;
    }

    public String getHouseProperty() {
        return houseProperty;
    }

    public void setHouseProperty(String houseProperty) {
        this.houseProperty = houseProperty;
    }

    public String getHouseInvoice() {
        return houseInvoice;
    }

    public void setHouseInvoice(String houseInvoice) {
        this.houseInvoice = houseInvoice;
    }

    public String getIsLicense() {
        return isLicense;
    }

    public void setIsLicense(String isLicense) {
        this.isLicense = isLicense;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getIsSiteProve() {
        return isSiteProve;
    }

    public void setIsSiteProve(String isSiteProve) {
        this.isSiteProve = isSiteProve;
    }

    public String getSiteProve() {
        return siteProve;
    }

    public void setSiteProve(String siteProve) {
        this.siteProve = siteProve;
    }

    public String getSiteArea() {
        return siteArea;
    }

    public void setSiteArea(String siteArea) {
        this.siteArea = siteArea;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getIsDriceLicense() {
        return isDriceLicense;
    }

    public void setIsDriceLicense(String isDriveLicense) {
        this.isDriceLicense = isDriveLicense;
    }

    public String getDriveLicense() {
        return driveLicense;
    }

    public void setDriveLicense(String driveLicense) {
        this.driveLicense = driveLicense;
    }

    public String getOtherPropertyNote() {
        return otherPropertyNote;
    }

    public void setOtherPropertyNote(String otherPropertyNote) {
        this.otherPropertyNote = otherPropertyNote;
    }

    public String getApplyBirthAddress() {
        return applyBirthAddress;
    }

    public void setApplyBirthAddress(String applyBirthAddress) {
        this.applyBirthAddress = applyBirthAddress;
    }

    public String getApplyNowAddress() {
        return applyNowAddress;
    }

    public void setApplyNowAddress(String applyNowAddress) {
        this.applyNowAddress = applyNowAddress;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType;
    }

    public String getGhBirthAddress() {
        return ghBirthAddress;
    }

    public void setGhBirthAddress(String ghBirthAddress) {
        this.ghBirthAddress = ghBirthAddress;
    }

    public String getGuarantor1BirthAddress() {
        return guarantor1BirthAddress;
    }

    public void setGuarantor1BirthAddress(String guarantor1BirthAddress) {
        this.guarantor1BirthAddress = guarantor1BirthAddress;
    }

    public String getGuarantor2BirthAddress() {
        return guarantor2BirthAddress;
    }

    public void setGuarantor2BirthAddress(String guarantor2BirthAddress) {
        this.guarantor2BirthAddress = guarantor2BirthAddress;
    }

    public String getOtherNote() {
        return otherNote;
    }

    public void setOtherNote(String otherNote) {
        this.otherNote = otherNote;
    }

    public String getIsPlatInsure() {
        return isPlatInsure;
    }

    public void setIsPlatInsure(String isPlatInsure) {
        this.isPlatInsure = isPlatInsure;
    }


    public String getGpsFeeWay() {
        return gpsFeeWay;
    }

    public void setGpsFeeWay(String gpsFeeWay) {
        this.gpsFeeWay = gpsFeeWay;
    }


    public String getFeeWay() {
        return feeWay;
    }

    public void setFeeWay(String feeWay) {
        this.feeWay = feeWay;
    }

    public String getMarryDivorce() {
        return marryDivorce;
    }

    public void setMarryDivorce(String marryDivorce) {
        this.marryDivorce = marryDivorce;
    }

    public String getApplyUserHkb() {
        return applyUserHkb;
    }

    public void setApplyUserHkb(String applyUserHkb) {
        this.applyUserHkb = applyUserHkb;
    }

    public String getBankBillPdf() {
        return bankBillPdf;
    }

    public void setBankBillPdf(String bankBillPdf) {
        this.bankBillPdf = bankBillPdf;
    }

    public String getSingleProvePdf() {
        return singleProvePdf;
    }

    public void setSingleProvePdf(String singleProvePdf) {
        this.singleProvePdf = singleProvePdf;
    }

    public String getIncomeProvePdf() {
        return incomeProvePdf;
    }

    public void setIncomeProvePdf(String incomeProvePdf) {
        this.incomeProvePdf = incomeProvePdf;
    }

    public String getLiveProvePdf() {
        return liveProvePdf;
    }

    public void setLiveProvePdf(String liveProvePdf) {
        this.liveProvePdf = liveProvePdf;
    }

    public String getBuildProvePdf() {
        return buildProvePdf;
    }

    public void setBuildProvePdf(String buildProvePdf) {
        this.buildProvePdf = buildProvePdf;
    }

    public String getHkbFirstPage() {
        return hkbFirstPage;
    }

    public void setHkbFirstPage(String hkbFirstPage) {
        this.hkbFirstPage = hkbFirstPage;
    }

    public String getHkbMainPage() {
        return hkbMainPage;
    }

    public void setHkbMainPage(String hkbMainPage) {
        this.hkbMainPage = hkbMainPage;
    }

    public String getGhHkb() {
        return ghHkb;
    }

    public void setGhHkb(String ghHkb) {
        this.ghHkb = ghHkb;
    }

    public String getGuarantor1IdNo() {
        return guarantor1IdNo;
    }

    public void setGuarantor1IdNo(String guarantor1IdNo) {
        this.guarantor1IdNo = guarantor1IdNo;
    }

    public String getGuarantor1Hkb() {
        return guarantor1Hkb;
    }

    public void setGuarantor1Hkb(String guarantor1Hkb) {
        this.guarantor1Hkb = guarantor1Hkb;
    }

    public String getGuarantor2IdNo() {
        return guarantor2IdNo;
    }

    public void setGuarantor2IdNo(String guarantor2IdNo) {
        this.guarantor2IdNo = guarantor2IdNo;
    }

    public String getGuarantor2Hkb() {
        return guarantor2Hkb;
    }

    public void setGuarantor2Hkb(String guarantor2Hkb) {
        this.guarantor2Hkb = guarantor2Hkb;
    }

    public String getHousePic() {
        return housePic;
    }

    public void setHousePic(String housePic) {
        this.housePic = housePic;
    }

    public String getHouseUnitPic() {
        return houseUnitPic;
    }

    public void setHouseUnitPic(String houseUnitPic) {
        this.houseUnitPic = houseUnitPic;
    }

    public String getHouseDoorPic() {
        return houseDoorPic;
    }

    public void setHouseDoorPic(String houseDoorPic) {
        this.houseDoorPic = houseDoorPic;
    }

    public String getHouseRoomPic() {
        return houseRoomPic;
    }

    public void setHouseRoomPic(String houseRoomPic) {
        this.houseRoomPic = houseRoomPic;
    }

    public String getHouseCustomerPic() {
        return houseCustomerPic;
    }

    public void setHouseCustomerPic(String houseCustomerPic) {
        this.houseCustomerPic = houseCustomerPic;
    }

    public String getHouseSaleCustomerPic() {
        return houseSaleCustomerPic;
    }

    public void setHouseSaleCustomerPic(String houseSaleCustomerPic) {
        this.houseSaleCustomerPic = houseSaleCustomerPic;
    }

    public String getCompanyNamePic() {
        return companyNamePic;
    }

    public void setCompanyNamePic(String companyNamePic) {
        this.companyNamePic = companyNamePic;
    }

    public String getCompanyPlacePic() {
        return companyPlacePic;
    }

    public void setCompanyPlacePic(String companyPlacePic) {
        this.companyPlacePic = companyPlacePic;
    }

    public String getCompanyWorkshopPic() {
        return companyWorkshopPic;
    }

    public void setCompanyWorkshopPic(String companyWorkshopPic) {
        this.companyWorkshopPic = companyWorkshopPic;
    }

    public String getCompanySaleCustomerPic() {
        return companySaleCustomerPic;
    }

    public void setCompanySaleCustomerPic(String companySaleCustomerPic) {
        this.companySaleCustomerPic = companySaleCustomerPic;
    }

    public String getSecondHgz() {
        return secondHgz;
    }

    public void setSecondHgz(String secondHgz) {
        this.secondHgz = secondHgz;
    }

    public String getSecondOdometer() {
        return secondOdometer;
    }

    public void setSecondOdometer(String secondOdometer) {
        this.secondOdometer = secondOdometer;
    }

    public String getSecondConsolePic() {
        return secondConsolePic;
    }

    public void setSecondConsolePic(String secondConsolePic) {
        this.secondConsolePic = secondConsolePic;
    }

    public String getSecond300Pdf() {
        return second300Pdf;
    }

    public void setSecond300Pdf(String second300Pdf) {
        this.second300Pdf = second300Pdf;
    }

    public String getSecondQxbPic() {
        return secondQxbPic;
    }

    public void setSecondQxbPic(String secondQxbPic) {
        this.secondQxbPic = secondQxbPic;
    }

    public String getSecondCarInPic() {
        return secondCarInPic;
    }

    public void setSecondCarInPic(String secondCarInPic) {
        this.secondCarInPic = secondCarInPic;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getOtherFilePdf() {
        return otherFilePdf;
    }

    public void setOtherFilePdf(String otherFilePdf) {
        this.otherFilePdf = otherFilePdf;
    }

    public String getOtherApplyNote() {
        return otherApplyNote;
    }

    public void setOtherApplyNote(String otherApplyNote) {
        this.otherApplyNote = otherApplyNote;
    }

    public String getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(String applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIdKind() {
        return idKind;
    }

    public void setIdKind(String idKind) {
        this.idKind = idKind;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getCurNodeCode() {
        return curNodeCode;
    }

    public void setCurNodeCode(String curNodeCode) {
        this.curNodeCode = curNodeCode;
    }

    public String getOutCarDealerName() {
        return outCarDealerName;
    }

    public void setOutCarDealerName(String outCarDealerName) {
        this.outCarDealerName = outCarDealerName;
    }

    public String getCarDealerName() {
        return carDealerName;
    }

    public void setCarDealerName(String carDealerName) {
        this.carDealerName = carDealerName;
    }

    public String getLoanBankName() {
        return loanBankName;
    }

    public void setLoanBankName(String loanBankName) {
        this.loanBankName = loanBankName;
    }

    public String getSaleUserName() {
        return saleUserName;
    }

    public void setSaleUserName(String saleUserName) {
        this.saleUserName = saleUserName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BankSubbranchBean getBankSubbranch() {
        return bankSubbranch;
    }

    public void setBankSubbranch(BankSubbranchBean bankSubbranch) {
        this.bankSubbranch = bankSubbranch;
    }

    public String getCompanyLoanCsSection() {
        return companyLoanCsSection;
    }

    public void setCompanyLoanCsSection(String companyLoanCsSection) {
        this.companyLoanCsSection = companyLoanCsSection;
    }

    public List<BudgetOrderGpsListBean> getBudgetOrderGpsList() {
        return budgetOrderGpsList;
    }

    public void setBudgetOrderGpsList(List<BudgetOrderGpsListBean> budgetOrderGpsList) {
        this.budgetOrderGpsList = budgetOrderGpsList;
    }

    public List<RepointDetailListBean> getRepointDetailList1() {
        return repointDetailList1;
    }

    public void setRepointDetailList1(List<RepointDetailListBean> repointDetailList1) {
        this.repointDetailList1 = repointDetailList1;
    }

    public List<RepointDetailListBean> getRepointDetailList2() {
        return repointDetailList2;
    }

    public void setRepointDetailList2(List<RepointDetailListBean> repointDetailList2) {
        this.repointDetailList2 = repointDetailList2;
    }

    public List<RepointDetailListBean> getRepointDetailList3() {
        return repointDetailList3;
    }

    public void setRepointDetailList3(List<RepointDetailListBean> repointDetailList3) {
        this.repointDetailList3 = repointDetailList3;
    }

    public static class BankSubbranchBean {
        /**
         * code : BS201806200844047884304
         * bankCode : BA201806200837173794638
         * bankType : ICBC
         * abbrName : 支行简称
         * fullName : 支行全称
         * openBank : 开户行
         * address : 银行地址
         * phoneNumber : 13222222222
         * postCode : 000000
         * bankClient : 银行委托人
         * autherName : 授权人姓名
         * autherPhoneNumber : 15522222222
         * autherIdNo : 150404199806080808
         * autherAddress : 授权人地址
         * creditCardType : 1
         * creditCardName : 信用卡名称
         * belongArea : 所属地区
         * updater : USYS201800000000001
         * updateDatetime : Jun 20, 2018 8:44:04 AM
         * remark : 备注
         * bank : {"code":"BA201806200837173794638","bankCode":"ICBC","bankName":"中国工商银行","status":"1","updater":"USYS201800000000001","updateDatetime":"Jun 20, 2018 8:37:17 AM","remark":"备注","bankRateList":[{"id":1,"bankCode":"BA201806200837173794638","period":12,"rate":0.5,"remark":"备注"}]}
         */

        private String code;
        private String bankCode;
        private String bankType;
        private String abbrName;
        private String fullName;
        private String openBank;
        private String address;
        private String phoneNumber;
        private String postCode;
        private String bankClient;
        private String autherName;
        private String autherPhoneNumber;
        private String autherIdNo;
        private String autherAddress;
        private String creditCardType;
        private String creditCardName;
        private String belongArea;
        private String updater;
        private String updateDatetime;
        private String remark;
        private BankBean bank;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getBankType() {
            return bankType;
        }

        public void setBankType(String bankType) {
            this.bankType = bankType;
        }

        public String getAbbrName() {
            return abbrName;
        }

        public void setAbbrName(String abbrName) {
            this.abbrName = abbrName;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getOpenBank() {
            return openBank;
        }

        public void setOpenBank(String openBank) {
            this.openBank = openBank;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPostCode() {
            return postCode;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }

        public String getBankClient() {
            return bankClient;
        }

        public void setBankClient(String bankClient) {
            this.bankClient = bankClient;
        }

        public String getAutherName() {
            return autherName;
        }

        public void setAutherName(String autherName) {
            this.autherName = autherName;
        }

        public String getAutherPhoneNumber() {
            return autherPhoneNumber;
        }

        public void setAutherPhoneNumber(String autherPhoneNumber) {
            this.autherPhoneNumber = autherPhoneNumber;
        }

        public String getAutherIdNo() {
            return autherIdNo;
        }

        public void setAutherIdNo(String autherIdNo) {
            this.autherIdNo = autherIdNo;
        }

        public String getAutherAddress() {
            return autherAddress;
        }

        public void setAutherAddress(String autherAddress) {
            this.autherAddress = autherAddress;
        }

        public String getCreditCardType() {
            return creditCardType;
        }

        public void setCreditCardType(String creditCardType) {
            this.creditCardType = creditCardType;
        }

        public String getCreditCardName() {
            return creditCardName;
        }

        public void setCreditCardName(String creditCardName) {
            this.creditCardName = creditCardName;
        }

        public String getBelongArea() {
            return belongArea;
        }

        public void setBelongArea(String belongArea) {
            this.belongArea = belongArea;
        }

        public String getUpdater() {
            return updater;
        }

        public void setUpdater(String updater) {
            this.updater = updater;
        }

        public String getUpdateDatetime() {
            return updateDatetime;
        }

        public void setUpdateDatetime(String updateDatetime) {
            this.updateDatetime = updateDatetime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public BankBean getBank() {
            return bank;
        }

        public void setBank(BankBean bank) {
            this.bank = bank;
        }

        public static class BankBean {
            /**
             * code : BA201806200837173794638
             * bankCode : ICBC
             * bankName : 中国工商银行
             * status : 1
             * updater : USYS201800000000001
             * updateDatetime : Jun 20, 2018 8:37:17 AM
             * remark : 备注
             * bankRateList : [{"id":1,"bankCode":"BA201806200837173794638","period":12,"rate":0.5,"remark":"备注"}]
             */

            private String code;
            private String bankCode;
            private String bankName;
            private String status;
            private String updater;
            private String updateDatetime;
            private String remark;
            private List<BankRateListBean> bankRateList;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getBankCode() {
                return bankCode;
            }

            public void setBankCode(String bankCode) {
                this.bankCode = bankCode;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getUpdater() {
                return updater;
            }

            public void setUpdater(String updater) {
                this.updater = updater;
            }

            public String getUpdateDatetime() {
                return updateDatetime;
            }

            public void setUpdateDatetime(String updateDatetime) {
                this.updateDatetime = updateDatetime;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public List<BankRateListBean> getBankRateList() {
                return bankRateList;
            }

            public void setBankRateList(List<BankRateListBean> bankRateList) {
                this.bankRateList = bankRateList;
            }

            public static class BankRateListBean {
                /**
                 * id : 1
                 * bankCode : BA201806200837173794638
                 * period : 12
                 * rate : 0.5
                 * remark : 备注
                 */

                private int id;
                private String bankCode;
                private int period;
                private double rate;
                private String remark;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getBankCode() {
                    return bankCode;
                }

                public void setBankCode(String bankCode) {
                    this.bankCode = bankCode;
                }

                public int getPeriod() {
                    return period;
                }

                public void setPeriod(int period) {
                    this.period = period;
                }

                public double getRate() {
                    return rate;
                }

                public void setRate(double rate) {
                    this.rate = rate;
                }

                public String getRemark() {
                    return remark;
                }

                public void setRemark(String remark) {
                    this.remark = remark;
                }
            }
        }
    }

    public static class BudgetOrderGpsListBean implements Serializable {
        /**
         * code : G201806200848491196500
         * gpsDevNo : 007
         * gpsType : 1
         * status : 1
         * budgetOrder : BG201806261341079951458
         */

        private String code;
        private String gpsDevNo;
        private String gpsType;
        private String status;
        private String azLocation;
        private String azDatetime;
        private String azUser;
        private String remark;
        private String budgetOrder;
        private String azLocationRemark;

        public String getAzLocationRemark() {
            return azLocationRemark;
        }

        public void setAzLocationRemark(String azLocationRemark) {
            this.azLocationRemark = azLocationRemark;
        }

        public String getAzLocation() {
            return azLocation;
        }

        public void setAzLocation(String azLocation) {
            this.azLocation = azLocation;
        }

        public String getAzDatetime() {
            return azDatetime;
        }

        public void setAzDatetime(String azDatetime) {
            this.azDatetime = azDatetime;
        }

        public String getAzUser() {
            return azUser;
        }

        public void setAzUser(String azUser) {
            this.azUser = azUser;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getGpsDevNo() {
            return gpsDevNo;
        }

        public void setGpsDevNo(String gpsDevNo) {
            this.gpsDevNo = gpsDevNo;
        }

        public String getGpsType() {
            return gpsType;
        }

        public void setGpsType(String gpsType) {
            this.gpsType = gpsType;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getBudgetOrder() {
            return budgetOrder;
        }

        public void setBudgetOrder(String budgetOrder) {
            this.budgetOrder = budgetOrder;
        }
    }

    public static class RepointDetailListBean implements Serializable {

        private String code;
        // 用款用途（1应退按揭款（不垫资） 3协议外返点
        private String useMoneyPurpose;

        // 金额(返点金额或应退按揭款)
        private String repointAmount;

        // 单位名称
        private String carDealerName;

        // 账号
        private String accountNO;

        // 开户行
        private String openBankName;

        // 户名
        private String accountName;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getUseMoneyPurpose() {
            return useMoneyPurpose;
        }

        public void setUseMoneyPurpose(String useMoneyPurpose) {
            this.useMoneyPurpose = useMoneyPurpose;
        }

        public String getRepointAmount() {
            if (TextUtils.isEmpty(repointAmount)) {
                return "0";
            }
            int repointAmountInt = (int) Double.parseDouble(repointAmount);
            return repointAmountInt + "";
        }

        public void setRepointAmount(String repointAmount) {


            this.repointAmount = repointAmount;
        }

        public String getCarDealerName() {
            return carDealerName;
        }

        public void setCarDealerName(String carDealerName) {
            this.carDealerName = carDealerName;
        }

        public String getAccountNO() {
            return accountNO;
        }

        public void setAccountNO(String accountNO) {
            this.accountNO = accountNO;
        }

        public String getOpenBankName() {
            return openBankName;
        }

        public void setOpenBankName(String openBankName) {
            this.openBankName = openBankName;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }
    }

    public static class Credit {


        /**
         * applyDatetime : Aug 21, 2018 9:12:20 PM
         * code : HX33180821123546003
         * companyCode : DP201800000000000000001
         * creditUserList : [{"authPdf":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","code":"CU201808212112206497724","courtNetworkResults":"法院网查询结果 ","dkdy2YearOverTimes":0,"dkdy6MonthAvgAmount":0,"dkdyAmount":0,"dkdyCount":0,"dkdyCurrentOverAmount":0,"dkdyMaxOverAmount":0,"hkxy2YearOverTimes":0,"hkxy6MonthAvgAmount":0,"hkxyCurrentOverAmount":0,"hkxyMonthMaxOverAmount":0,"hkxyUnsettleAmount":0,"hkxyUnsettleCount":0,"idNo":"410621199605101019","idNoFront":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","idNoReverse":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","interviewPic":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","isFirstAudit":"1","loanRole":"1","mobile":"13333333333","outGuaranteesAmount":0,"outGuaranteesCount":0,"outGuaranteesRemark":"备注","relation":"1","userName":"姓名8","xyk2YearOverTimes":0,"xyk6MonthUseAmount":0,"xykCount":0,"xykCreditAmount":0,"xykCurrentOverAmount":0,"xykMonthMaxOverAmount":0},{"authPdf":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","code":"CU201808212112206516019","courtNetworkResults":"法院网查询结果 ","dkdy2YearOverTimes":0,"dkdy6MonthAvgAmount":0,"dkdyAmount":0,"dkdyCount":0,"dkdyCurrentOverAmount":0,"dkdyMaxOverAmount":0,"hkxy2YearOverTimes":0,"hkxy6MonthAvgAmount":0,"hkxyCurrentOverAmount":0,"hkxyMonthMaxOverAmount":0,"hkxyUnsettleAmount":0,"hkxyUnsettleCount":0,"idNo":"410621199605101019","idNoFront":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","idNoReverse":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","interviewPic":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","isFirstAudit":"1","loanRole":"2","mobile":"13333333333","outGuaranteesAmount":0,"outGuaranteesCount":0,"outGuaranteesRemark":"备注","relation":"8","userName":"共换人","xyk2YearOverTimes":0,"xyk6MonthUseAmount":0,"xykCount":0,"xykCreditAmount":0,"xykCurrentOverAmount":0,"xykMonthMaxOverAmount":0},{"authPdf":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","code":"CU201808212112206538006","courtNetworkResults":"法院网查询结果 ","dkdy2YearOverTimes":0,"dkdy6MonthAvgAmount":0,"dkdyAmount":0,"dkdyCount":0,"dkdyCurrentOverAmount":0,"dkdyMaxOverAmount":0,"hkxy2YearOverTimes":0,"hkxy6MonthAvgAmount":0,"hkxyCurrentOverAmount":0,"hkxyMonthMaxOverAmount":0,"hkxyUnsettleAmount":0,"hkxyUnsettleCount":0,"idNo":"410621199605101019","idNoFront":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","idNoReverse":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","interviewPic":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","isFirstAudit":"1","loanRole":"3","mobile":"13333333333","outGuaranteesAmount":0,"outGuaranteesCount":0,"outGuaranteesRemark":"备注","relation":"5","userName":"担保人1","xyk2YearOverTimes":0,"xyk6MonthUseAmount":0,"xykCount":0,"xykCreditAmount":0,"xykCurrentOverAmount":0,"xykMonthMaxOverAmount":0},{"authPdf":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","code":"CU201808212112206555760","courtNetworkResults":"法院网查询结果 ","dkdy2YearOverTimes":0,"dkdy6MonthAvgAmount":0,"dkdyAmount":0,"dkdyCount":0,"dkdyCurrentOverAmount":0,"dkdyMaxOverAmount":0,"hkxy2YearOverTimes":0,"hkxy6MonthAvgAmount":0,"hkxyCurrentOverAmount":0,"hkxyMonthMaxOverAmount":0,"hkxyUnsettleAmount":0,"hkxyUnsettleCount":0,"idNo":"410621199605101019","idNoFront":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","idNoReverse":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","interviewPic":"Fumxo0uiOQlNa0pKxuvjsWPSCDXu","isFirstAudit":"1","loanRole":"3","mobile":"13333333333","outGuaranteesAmount":0,"outGuaranteesCount":0,"outGuaranteesRemark":"备注","relation":"8","userName":"担保人2","xyk2YearOverTimes":0,"xyk6MonthUseAmount":0,"xykCount":0,"xykCreditAmount":0,"xykCurrentOverAmount":0,"xykMonthMaxOverAmount":0}]
         * curNodeCode : 001_06
         * loanAmount : 2.0E9
         * loanBankCode : BS201807171350293781303
         * saleUserId : U201808181253226614914
         * shopWay : 1
         */

        private String applyDatetime;
        private String code;
        private String companyCode;
        private String curNodeCode;
        private double loanAmount;
        private String loanBankCode;
        private String saleUserId;
        private String shopWay;
        private List<CreditUserListBean> creditUserList;

        public String getApplyDatetime() {
            return applyDatetime;
        }

        public void setApplyDatetime(String applyDatetime) {
            this.applyDatetime = applyDatetime;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getCurNodeCode() {
            return curNodeCode;
        }

        public void setCurNodeCode(String curNodeCode) {
            this.curNodeCode = curNodeCode;
        }

        public double getLoanAmount() {
            return loanAmount;
        }

        public void setLoanAmount(double loanAmount) {
            this.loanAmount = loanAmount;
        }

        public String getLoanBankCode() {
            return loanBankCode;
        }

        public void setLoanBankCode(String loanBankCode) {
            this.loanBankCode = loanBankCode;
        }

        public String getSaleUserId() {
            return saleUserId;
        }

        public void setSaleUserId(String saleUserId) {
            this.saleUserId = saleUserId;
        }

        public String getShopWay() {
            return shopWay;
        }

        public void setShopWay(String shopWay) {
            this.shopWay = shopWay;
        }

        public List<CreditUserListBean> getCreditUserList() {
            return creditUserList;
        }

        public void setCreditUserList(List<CreditUserListBean> creditUserList) {
            this.creditUserList = creditUserList;
        }

        public static class CreditUserListBean {
            /**
             * authPdf : Fumxo0uiOQlNa0pKxuvjsWPSCDXu
             * code : CU201808212112206497724
             * courtNetworkResults : 法院网查询结果
             * dkdy2YearOverTimes : 0.0
             * dkdy6MonthAvgAmount : 0.0
             * dkdyAmount : 0.0
             * dkdyCount : 0.0
             * dkdyCurrentOverAmount : 0.0
             * dkdyMaxOverAmount : 0.0
             * hkxy2YearOverTimes : 0.0
             * hkxy6MonthAvgAmount : 0.0
             * hkxyCurrentOverAmount : 0.0
             * hkxyMonthMaxOverAmount : 0.0
             * hkxyUnsettleAmount : 0.0
             * hkxyUnsettleCount : 0.0
             * idNo : 410621199605101019
             * idNoFront : Fumxo0uiOQlNa0pKxuvjsWPSCDXu
             * idNoReverse : Fumxo0uiOQlNa0pKxuvjsWPSCDXu
             * interviewPic : Fumxo0uiOQlNa0pKxuvjsWPSCDXu
             * isFirstAudit : 1
             * loanRole : 1
             * mobile : 13333333333
             * outGuaranteesAmount : 0.0
             * outGuaranteesCount : 0.0
             * outGuaranteesRemark : 备注
             * relation : 1
             * userName : 姓名8
             * xyk2YearOverTimes : 0.0
             * xyk6MonthUseAmount : 0.0
             * xykCount : 0.0
             * xykCreditAmount : 0.0
             * xykCurrentOverAmount : 0.0
             * xykMonthMaxOverAmount : 0.0
             */

            private String authPdf;
            private String code;
            private String courtNetworkResults;
            private double dkdy2YearOverTimes;
            private double dkdy6MonthAvgAmount;
            private double dkdyAmount;
            private double dkdyCount;
            private double dkdyCurrentOverAmount;
            private double dkdyMaxOverAmount;
            private double hkxy2YearOverTimes;
            private double hkxy6MonthAvgAmount;
            private double hkxyCurrentOverAmount;
            private double hkxyMonthMaxOverAmount;
            private double hkxyUnsettleAmount;
            private double hkxyUnsettleCount;
            private String idNo;
            private String idNoFront;
            private String idNoReverse;
            private String interviewPic;
            private String isFirstAudit;
            private String loanRole;
            private String mobile;
            private double outGuaranteesAmount;
            private double outGuaranteesCount;
            private String outGuaranteesRemark;
            private String relation;
            private String userName;
            private double xyk2YearOverTimes;
            private double xyk6MonthUseAmount;
            private double xykCount;
            private double xykCreditAmount;
            private double xykCurrentOverAmount;
            private double xykMonthMaxOverAmount;

            public String getAuthPdf() {
                return authPdf;
            }

            public void setAuthPdf(String authPdf) {
                this.authPdf = authPdf;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getCourtNetworkResults() {
                return courtNetworkResults;
            }

            public void setCourtNetworkResults(String courtNetworkResults) {
                this.courtNetworkResults = courtNetworkResults;
            }

            public double getDkdy2YearOverTimes() {
                return dkdy2YearOverTimes;
            }

            public void setDkdy2YearOverTimes(double dkdy2YearOverTimes) {
                this.dkdy2YearOverTimes = dkdy2YearOverTimes;
            }

            public double getDkdy6MonthAvgAmount() {
                return dkdy6MonthAvgAmount;
            }

            public void setDkdy6MonthAvgAmount(double dkdy6MonthAvgAmount) {
                this.dkdy6MonthAvgAmount = dkdy6MonthAvgAmount;
            }

            public double getDkdyAmount() {
                return dkdyAmount;
            }

            public void setDkdyAmount(double dkdyAmount) {
                this.dkdyAmount = dkdyAmount;
            }

            public double getDkdyCount() {
                return dkdyCount;
            }

            public void setDkdyCount(double dkdyCount) {
                this.dkdyCount = dkdyCount;
            }

            public double getDkdyCurrentOverAmount() {
                return dkdyCurrentOverAmount;
            }

            public void setDkdyCurrentOverAmount(double dkdyCurrentOverAmount) {
                this.dkdyCurrentOverAmount = dkdyCurrentOverAmount;
            }

            public double getDkdyMaxOverAmount() {
                return dkdyMaxOverAmount;
            }

            public void setDkdyMaxOverAmount(double dkdyMaxOverAmount) {
                this.dkdyMaxOverAmount = dkdyMaxOverAmount;
            }

            public double getHkxy2YearOverTimes() {
                return hkxy2YearOverTimes;
            }

            public void setHkxy2YearOverTimes(double hkxy2YearOverTimes) {
                this.hkxy2YearOverTimes = hkxy2YearOverTimes;
            }

            public double getHkxy6MonthAvgAmount() {
                return hkxy6MonthAvgAmount;
            }

            public void setHkxy6MonthAvgAmount(double hkxy6MonthAvgAmount) {
                this.hkxy6MonthAvgAmount = hkxy6MonthAvgAmount;
            }

            public double getHkxyCurrentOverAmount() {
                return hkxyCurrentOverAmount;
            }

            public void setHkxyCurrentOverAmount(double hkxyCurrentOverAmount) {
                this.hkxyCurrentOverAmount = hkxyCurrentOverAmount;
            }

            public double getHkxyMonthMaxOverAmount() {
                return hkxyMonthMaxOverAmount;
            }

            public void setHkxyMonthMaxOverAmount(double hkxyMonthMaxOverAmount) {
                this.hkxyMonthMaxOverAmount = hkxyMonthMaxOverAmount;
            }

            public double getHkxyUnsettleAmount() {
                return hkxyUnsettleAmount;
            }

            public void setHkxyUnsettleAmount(double hkxyUnsettleAmount) {
                this.hkxyUnsettleAmount = hkxyUnsettleAmount;
            }

            public double getHkxyUnsettleCount() {
                return hkxyUnsettleCount;
            }

            public void setHkxyUnsettleCount(double hkxyUnsettleCount) {
                this.hkxyUnsettleCount = hkxyUnsettleCount;
            }

            public String getIdNo() {
                return idNo;
            }

            public void setIdNo(String idNo) {
                this.idNo = idNo;
            }

            public String getIdNoFront() {
                return idNoFront;
            }

            public void setIdNoFront(String idNoFront) {
                this.idNoFront = idNoFront;
            }

            public String getIdNoReverse() {
                return idNoReverse;
            }

            public void setIdNoReverse(String idNoReverse) {
                this.idNoReverse = idNoReverse;
            }

            public String getInterviewPic() {
                return interviewPic;
            }

            public void setInterviewPic(String interviewPic) {
                this.interviewPic = interviewPic;
            }

            public String getIsFirstAudit() {
                return isFirstAudit;
            }

            public void setIsFirstAudit(String isFirstAudit) {
                this.isFirstAudit = isFirstAudit;
            }

            public String getLoanRole() {
                return loanRole;
            }

            public void setLoanRole(String loanRole) {
                this.loanRole = loanRole;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public double getOutGuaranteesAmount() {
                return outGuaranteesAmount;
            }

            public void setOutGuaranteesAmount(double outGuaranteesAmount) {
                this.outGuaranteesAmount = outGuaranteesAmount;
            }

            public double getOutGuaranteesCount() {
                return outGuaranteesCount;
            }

            public void setOutGuaranteesCount(double outGuaranteesCount) {
                this.outGuaranteesCount = outGuaranteesCount;
            }

            public String getOutGuaranteesRemark() {
                return outGuaranteesRemark;
            }

            public void setOutGuaranteesRemark(String outGuaranteesRemark) {
                this.outGuaranteesRemark = outGuaranteesRemark;
            }

            public String getRelation() {
                return relation;
            }

            public void setRelation(String relation) {
                this.relation = relation;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public double getXyk2YearOverTimes() {
                return xyk2YearOverTimes;
            }

            public void setXyk2YearOverTimes(double xyk2YearOverTimes) {
                this.xyk2YearOverTimes = xyk2YearOverTimes;
            }

            public double getXyk6MonthUseAmount() {
                return xyk6MonthUseAmount;
            }

            public void setXyk6MonthUseAmount(double xyk6MonthUseAmount) {
                this.xyk6MonthUseAmount = xyk6MonthUseAmount;
            }

            public double getXykCount() {
                return xykCount;
            }

            public void setXykCount(double xykCount) {
                this.xykCount = xykCount;
            }

            public double getXykCreditAmount() {
                return xykCreditAmount;
            }

            public void setXykCreditAmount(double xykCreditAmount) {
                this.xykCreditAmount = xykCreditAmount;
            }

            public double getXykCurrentOverAmount() {
                return xykCurrentOverAmount;
            }

            public void setXykCurrentOverAmount(double xykCurrentOverAmount) {
                this.xykCurrentOverAmount = xykCurrentOverAmount;
            }

            public double getXykMonthMaxOverAmount() {
                return xykMonthMaxOverAmount;
            }

            public void setXykMonthMaxOverAmount(double xykMonthMaxOverAmount) {
                this.xykMonthMaxOverAmount = xykMonthMaxOverAmount;
            }
        }
    }
}
