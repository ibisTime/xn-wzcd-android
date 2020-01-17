package com.cdkj.wzcd.main.credit.module.zrzl.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author : qianLei
 * @since : 2019/12/31 22:35
 */
public class ZrzlBean implements Serializable {


    /**
     * code : CB201904291712310259507
     * bizType : 1
     * saleUserId : USYS201800000000001
     * insideJob : USYS201800000000001
     * loanBank : BA201809101215201166542
     * loanAmount :
     * applyDatetime : Apr 29, 2019 5:12:31 PM
     * remark :
     * creditUserList : [{"code":"CU201904291712311129997","bizCode":"CB201904291712310259507","loanRole":"1","userName":"aaa","mobile":"17755533202","idNo":"340824199605261428","bankCreditResult":"1","gender":"","nation":"","customerBirth":"","birthAddress":"","authref":"","statdate":"","education":"","nowAddressArea":"","nowAddress":"","marryState":"","nowHouseType":"","companyName":"","companyProvince":"","companyCity":"","companyArea":"","companyAddress":"","position":"","yearIncome":"","presentJobYears":"","permanentType":"","nowAddressCity":"","nowAddressProvince":""},{"code":"CU201904291712311129997","bizCode":"CB201904291712310259507","loanRole":"1","userName":"aaa","mobile":"17755533202","idNo":"340824199605261428","bankCreditResult":"1","gender":"","nation":"","customerBirth":"","birthAddress":"","authref":"","statdate":"","education":"","nowAddressArea":"","nowAddress":"","marryState":"","nowHouseType":"","companyName":"","companyProvince":"","companyCity":"","companyArea":"","companyAddress":"","position":"","yearIncome":"","presentJobYears":"","permanentType":"","nowAddressCity":"","nowAddressProvince":""}]
     * bankLoan : {"periods":12,"loanAmount":2000000,"monthAmount":0,"fee":0,"等等":"","bankRate":"","totalRate":"","rebateRate":"","rateType":"","isAdvanceFund":"","isDiscount":"","discountAmount":"","highCashAmount":"","totalFee":"","surchargeAmount":"","bankCommitDatetime":"","bankCommitNote":"","bankFkDatetime":"","repayBankDate":"","repayBillDate":"","repayBankcardNumber":"","receiptRemark":"","repayFirstMonthAmount":"","repayFirstMonthDatetime":"","openCardAmount":"","discountRate":"","wanFactor":"","customerBearRate":"","surchargeRate":"","bankFkDate":"","bankFkNote":"","loanNumber":""}
     * carInfo : {"isPublicCard":"二手车","carType":"国产车","carBrand":"JJ","carSeries":"进来了","carModel":"栏","carFrameNo":"看","carEngineNo":"家里","region":"北京 北京市 东城区","等等":"KKK","shopCarGarage":"","shopCarGarageName":"","secondCarReport":"","regDate":"","mile":"","carPrice":"","evalPrice":"","isAzGps":"","carNumber":"","carBrandName":"","carSeriesName":"","carModelName":""}
     * attachments : []
     * loanBankName :
     * saleUserName :
     * saleUserCompanyName :
     * saleUserDepartMentName :
     * saleUserPostName :
     * insideJobName :
     * insideJobCompanyName :
     * insideJobDepartMentName :
     * insideJobPostName :
     * fxAmount :
     * lyDeposit :
     * repointAmount :
     * gpsFee :
     * otherFee :
     * bank : {"code":"","bankCode":"","bankName":"","bankFullName":"","subbranch":"","mobile":"","rate12":"","rate18":"","rate24":"","rate36":"","等等":""}
     * teamName :
     * enterLocationName :
     * rationaleDatetime :
     * rationaleNote :
     * hitPieceDatetime :
     * hitPieceNote :
     * carFunds3 :
     * carFunds4 :
     * carFunds5 :
     * enterCode :
     * insuranceCompany :
     * insuranceCompanyName :
     * advance : {"code":"A202001011532132784615","bizCode":"CB332019122003202A","backAdvanceStatus":"1","advanceFundDatetime":"Jan 1, 2020 12:00:00 AM","advanceFundAmount":300000,"makeBillNote":"说你","advanceOutCardCode":"CB201906171130293041327","advanceOutCard":{"code":"CB201906171130293041327","type":"4","advanceType":"1","companyCode":"DP201800000000000000001","realName":"户名","bankCode":"PAB","bankName":"平安银行","subbranch":"XX支行","bankcardNumber":"45670987654345672","pointRate":0.1,"remark":"","companyName":"温州浩源控股有限公司"}}
     * applyUser : 柴运来
     * applyDepartment : 浙江业务部
     */

    private String code;
    private String bizType;
    private String saleUserId;
    private String insideJob;
    private String loanBank;
    private String loanAmount;
    private String ascription;
    private String ascriptionName;
    private String applyDatetime;
    private String remark;
    private String curNodeCode;
    private String pledgeNodeCode;
    private String teamCode;
    private BankLoanBean bankLoan;
    private CarInfoBean carInfo;
    private String loanBankName;
    private String saleUserName;
    private String saleUserCompanyName;
    private String saleUserDepartMentName;
    private String saleUserPostName;
    private String insideJobName;
    private String insideJobCompanyName;
    private String insideJobDepartMentName;
    private String insideJobPostName;
    private String fxAmount;
    private String lyDeposit;
    private String repointAmount;
    private String gpsFee;
    private String otherFee;
    private BankBean bank;
    private String teamName;
    private String regionName;
    private String enterLocationName;
    private String rationaleDatetime;
    private String rationaleNote;
    private String hitPieceDatetime;
    private String hitPieceNote;
    private String carFunds3;
    private String carFunds4;
    private String carFunds5;
    private String enterCode;
    private String insuranceCompany;
    private String insuranceCompanyName;
    private String syxDateStart;
    private String syxDateEnd;
    private AdvanceBean advance;
    private String applyUser;
    private String applyDepartment;
    private DkrxxBean creditUser;
    private List<GPSBean> gpsAzList;
    private List<DkrxxBean> creditUserList;
    private List<AttachmentsBean> attachments;

    public String getAscription() {
        return ascription;
    }

    public void setAscription(String ascription) {
        this.ascription = ascription;
    }

    public String getAscriptionName() {
        return ascriptionName;
    }

    public void setAscriptionName(String ascriptionName) {
        this.ascriptionName = ascriptionName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getPledgeNodeCode() {
        return pledgeNodeCode;
    }

    public void setPledgeNodeCode(String pledgeNodeCode) {
        this.pledgeNodeCode = pledgeNodeCode;
    }

    public String getCurNodeCode() {
        return curNodeCode;
    }

    public void setCurNodeCode(String curNodeCode) {
        this.curNodeCode = curNodeCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getSyxDateStart() {
        return syxDateStart;
    }

    public void setSyxDateStart(String syxDateStart) {
        this.syxDateStart = syxDateStart;
    }

    public String getSyxDateEnd() {
        return syxDateEnd;
    }

    public void setSyxDateEnd(String syxDateEnd) {
        this.syxDateEnd = syxDateEnd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getSaleUserId() {
        return saleUserId;
    }

    public void setSaleUserId(String saleUserId) {
        this.saleUserId = saleUserId;
    }

    public String getInsideJob() {
        return insideJob;
    }

    public void setInsideJob(String insideJob) {
        this.insideJob = insideJob;
    }

    public String getLoanBank() {
        return loanBank;
    }

    public void setLoanBank(String loanBank) {
        this.loanBank = loanBank;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(String applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BankLoanBean getBankLoan() {
        return bankLoan;
    }

    public void setBankLoan(BankLoanBean bankLoan) {
        this.bankLoan = bankLoan;
    }

    public CarInfoBean getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(CarInfoBean carInfo) {
        this.carInfo = carInfo;
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

    public String getSaleUserCompanyName() {
        return saleUserCompanyName;
    }

    public void setSaleUserCompanyName(String saleUserCompanyName) {
        this.saleUserCompanyName = saleUserCompanyName;
    }

    public String getSaleUserDepartMentName() {
        return saleUserDepartMentName;
    }

    public void setSaleUserDepartMentName(String saleUserDepartMentName) {
        this.saleUserDepartMentName = saleUserDepartMentName;
    }

    public String getSaleUserPostName() {
        return saleUserPostName;
    }

    public void setSaleUserPostName(String saleUserPostName) {
        this.saleUserPostName = saleUserPostName;
    }

    public String getInsideJobName() {
        return insideJobName;
    }

    public void setInsideJobName(String insideJobName) {
        this.insideJobName = insideJobName;
    }

    public String getInsideJobCompanyName() {
        return insideJobCompanyName;
    }

    public void setInsideJobCompanyName(String insideJobCompanyName) {
        this.insideJobCompanyName = insideJobCompanyName;
    }

    public String getInsideJobDepartMentName() {
        return insideJobDepartMentName;
    }

    public void setInsideJobDepartMentName(String insideJobDepartMentName) {
        this.insideJobDepartMentName = insideJobDepartMentName;
    }

    public String getInsideJobPostName() {
        return insideJobPostName;
    }

    public void setInsideJobPostName(String insideJobPostName) {
        this.insideJobPostName = insideJobPostName;
    }

    public String getFxAmount() {
        return fxAmount;
    }

    public void setFxAmount(String fxAmount) {
        this.fxAmount = fxAmount;
    }

    public String getLyDeposit() {
        return lyDeposit;
    }

    public void setLyDeposit(String lyDeposit) {
        this.lyDeposit = lyDeposit;
    }

    public String getRepointAmount() {
        return repointAmount;
    }

    public void setRepointAmount(String repointAmount) {
        this.repointAmount = repointAmount;
    }

    public String getGpsFee() {
        return gpsFee;
    }

    public void setGpsFee(String gpsFee) {
        this.gpsFee = gpsFee;
    }

    public String getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(String otherFee) {
        this.otherFee = otherFee;
    }

    public BankBean getBank() {
        return bank;
    }

    public void setBank(BankBean bank) {
        this.bank = bank;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getEnterLocationName() {
        return enterLocationName;
    }

    public void setEnterLocationName(String enterLocationName) {
        this.enterLocationName = enterLocationName;
    }

    public String getRationaleDatetime() {
        return rationaleDatetime;
    }

    public void setRationaleDatetime(String rationaleDatetime) {
        this.rationaleDatetime = rationaleDatetime;
    }

    public String getRationaleNote() {
        return rationaleNote;
    }

    public void setRationaleNote(String rationaleNote) {
        this.rationaleNote = rationaleNote;
    }

    public String getHitPieceDatetime() {
        return hitPieceDatetime;
    }

    public void setHitPieceDatetime(String hitPieceDatetime) {
        this.hitPieceDatetime = hitPieceDatetime;
    }

    public String getHitPieceNote() {
        return hitPieceNote;
    }

    public void setHitPieceNote(String hitPieceNote) {
        this.hitPieceNote = hitPieceNote;
    }

    public String getCarFunds3() {
        return carFunds3;
    }

    public void setCarFunds3(String carFunds3) {
        this.carFunds3 = carFunds3;
    }

    public String getCarFunds4() {
        return carFunds4;
    }

    public void setCarFunds4(String carFunds4) {
        this.carFunds4 = carFunds4;
    }

    public String getCarFunds5() {
        return carFunds5;
    }

    public void setCarFunds5(String carFunds5) {
        this.carFunds5 = carFunds5;
    }

    public String getEnterCode() {
        return enterCode;
    }

    public void setEnterCode(String enterCode) {
        this.enterCode = enterCode;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    public String getInsuranceCompanyName() {
        return insuranceCompanyName;
    }

    public void setInsuranceCompanyName(String insuranceCompanyName) {
        this.insuranceCompanyName = insuranceCompanyName;
    }

    public AdvanceBean getAdvance() {
        return advance;
    }

    public void setAdvance(AdvanceBean advance) {
        this.advance = advance;
    }

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyDepartment() {
        return applyDepartment;
    }

    public void setApplyDepartment(String applyDepartment) {
        this.applyDepartment = applyDepartment;
    }

    public DkrxxBean getCreditUser() {
        return creditUser;
    }

    public void setCreditUser(DkrxxBean creditUser) {
        this.creditUser = creditUser;
    }

    public List<GPSBean> getGpsAzList() {
        return gpsAzList;
    }

    public void setGpsAzList(List<GPSBean> gpsAzList) {
        this.gpsAzList = gpsAzList;
    }

    public List<DkrxxBean> getCreditUserList() {
        return creditUserList;
    }

    public void setCreditUserList(List<DkrxxBean> creditUserList) {
        this.creditUserList = creditUserList;
    }

    public List<AttachmentsBean> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentsBean> attachments) {
        this.attachments = attachments;
    }

    public static class BankLoanBean implements Serializable {

        /**
         * periods : 12
         * loanAmount : 2000000
         * monthAmount : 0
         * fee : 0
         * 等等 :
         * bankRate :
         * totalRate :
         * rebateRate :
         * rateType :
         * isAdvanceFund :
         * isDiscount :
         * discountAmount :
         * highCashAmount :
         * totalFee :
         * surchargeAmount :
         * bankCommitDatetime :
         * bankCommitNote :
         * bankFkDatetime :
         * repayBankDate :
         * repayBillDate :
         * repayBankcardNumber :
         * receiptRemark :
         * repayFirstMonthAmount :
         * repayFirstMonthDatetime :
         * openCardAmount :
         * discountRate :
         * wanFactor :
         * customerBearRate :
         * surchargeRate :
         * bankFkDate :
         * bankFkNote :
         * loanNumber :
         */

        private int periods;
        private String loanAmount;
        private String monthAmount;
        private String fee;
        private String bankRate;
        private String totalRate;
        private String rebateRate;
        private String rateType;
        private String isAdvanceFund;
        private String isDiscount;
        private String discountAmount;
        private String highCashAmount;
        private String totalFee;
        private String surchargeAmount;
        private String bankCommitDatetime;
        private String bankCommitNote;
        private String bankFkDatetime;
        private String bankFkRemark;
        private int repayBankDate;
        private int repayBillDate;
        private String repayBankcardNumber;
        private String receiptRemark;
        private String repayFirstMonthAmount;
        private String repayFirstMonthDatetime;
        private String openCardAmount;
        private String discountRate;
        private String wanFactor;
        private String customerBearRate;
        private String surchargeRate;
        private String bankFkDate;
        private String bankFkNote;
        private String loanNumber;
        private String invoicePrice;
        private String loanRatio;
        private String notes;

        public int getPeriods() {
            return periods;
        }

        public void setPeriods(int periods) {
            this.periods = periods;
        }

        public String getLoanAmount() {
            return loanAmount;
        }

        public void setLoanAmount(String loanAmount) {
            this.loanAmount = loanAmount;
        }

        public String getMonthAmount() {
            return monthAmount;
        }

        public void setMonthAmount(String monthAmount) {
            this.monthAmount = monthAmount;
        }

        public String getFee() {
            return fee;
        }

        public void setFee(String fee) {
            this.fee = fee;
        }

        public String getBankRate() {
            return bankRate;
        }

        public void setBankRate(String bankRate) {
            this.bankRate = bankRate;
        }

        public String getTotalRate() {
            return totalRate;
        }

        public void setTotalRate(String totalRate) {
            this.totalRate = totalRate;
        }

        public String getRebateRate() {
            return rebateRate;
        }

        public void setRebateRate(String rebateRate) {
            this.rebateRate = rebateRate;
        }

        public String getRateType() {
            return rateType;
        }

        public void setRateType(String rateType) {
            this.rateType = rateType;
        }

        public String getIsAdvanceFund() {
            return isAdvanceFund;
        }

        public void setIsAdvanceFund(String isAdvanceFund) {
            this.isAdvanceFund = isAdvanceFund;
        }

        public String getIsDiscount() {
            return isDiscount;
        }

        public void setIsDiscount(String isDiscount) {
            this.isDiscount = isDiscount;
        }

        public String getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(String discountAmount) {
            this.discountAmount = discountAmount;
        }

        public String getHighCashAmount() {
            return highCashAmount;
        }

        public void setHighCashAmount(String highCashAmount) {
            this.highCashAmount = highCashAmount;
        }

        public String getTotalFee() {
            return totalFee;
        }

        public void setTotalFee(String totalFee) {
            this.totalFee = totalFee;
        }

        public String getSurchargeAmount() {
            return surchargeAmount;
        }

        public void setSurchargeAmount(String surchargeAmount) {
            this.surchargeAmount = surchargeAmount;
        }

        public String getBankCommitDatetime() {
            return bankCommitDatetime;
        }

        public void setBankCommitDatetime(String bankCommitDatetime) {
            this.bankCommitDatetime = bankCommitDatetime;
        }

        public String getBankCommitNote() {
            return bankCommitNote;
        }

        public void setBankCommitNote(String bankCommitNote) {
            this.bankCommitNote = bankCommitNote;
        }

        public String getBankFkDatetime() {
            return bankFkDatetime;
        }

        public void setBankFkDatetime(String bankFkDatetime) {
            this.bankFkDatetime = bankFkDatetime;
        }

        public String getBankFkRemark() {
            return bankFkRemark;
        }

        public void setBankFkRemark(String bankFkRemark) {
            this.bankFkRemark = bankFkRemark;
        }

        public int getRepayBankDate() {
            return repayBankDate;
        }

        public void setRepayBankDate(int repayBankDate) {
            this.repayBankDate = repayBankDate;
        }

        public int getRepayBillDate() {
            return repayBillDate;
        }

        public void setRepayBillDate(int repayBillDate) {
            this.repayBillDate = repayBillDate;
        }

        public String getRepayBankcardNumber() {
            return repayBankcardNumber;
        }

        public void setRepayBankcardNumber(String repayBankcardNumber) {
            this.repayBankcardNumber = repayBankcardNumber;
        }

        public String getReceiptRemark() {
            return receiptRemark;
        }

        public void setReceiptRemark(String receiptRemark) {
            this.receiptRemark = receiptRemark;
        }

        public String getRepayFirstMonthAmount() {
            return repayFirstMonthAmount;
        }

        public void setRepayFirstMonthAmount(String repayFirstMonthAmount) {
            this.repayFirstMonthAmount = repayFirstMonthAmount;
        }

        public String getRepayFirstMonthDatetime() {
            return repayFirstMonthDatetime;
        }

        public void setRepayFirstMonthDatetime(String repayFirstMonthDatetime) {
            this.repayFirstMonthDatetime = repayFirstMonthDatetime;
        }

        public String getOpenCardAmount() {
            return openCardAmount;
        }

        public void setOpenCardAmount(String openCardAmount) {
            this.openCardAmount = openCardAmount;
        }

        public String getDiscountRate() {
            return discountRate;
        }

        public void setDiscountRate(String discountRate) {
            this.discountRate = discountRate;
        }

        public String getWanFactor() {
            return wanFactor;
        }

        public void setWanFactor(String wanFactor) {
            this.wanFactor = wanFactor;
        }

        public String getCustomerBearRate() {
            return customerBearRate;
        }

        public void setCustomerBearRate(String customerBearRate) {
            this.customerBearRate = customerBearRate;
        }

        public String getSurchargeRate() {
            return surchargeRate;
        }

        public void setSurchargeRate(String surchargeRate) {
            this.surchargeRate = surchargeRate;
        }

        public String getBankFkDate() {
            return bankFkDate;
        }

        public void setBankFkDate(String bankFkDate) {
            this.bankFkDate = bankFkDate;
        }

        public String getBankFkNote() {
            return bankFkNote;
        }

        public void setBankFkNote(String bankFkNote) {
            this.bankFkNote = bankFkNote;
        }

        public String getLoanNumber() {
            return loanNumber;
        }

        public void setLoanNumber(String loanNumber) {
            this.loanNumber = loanNumber;
        }

        public String getInvoicePrice() {
            return invoicePrice;
        }

        public void setInvoicePrice(String invoicePrice) {
            this.invoicePrice = invoicePrice;
        }

        public String getLoanRatio() {
            return loanRatio;
        }

        public void setLoanRatio(String loanRatio) {
            this.loanRatio = loanRatio;
        }

        public String getNotes() {
            return notes;
        }

        public void setNotes(String notes) {
            this.notes = notes;
        }
    }

    public static class CarInfoBean implements Serializable {

        /**
         * isPublicCard : 二手车
         * carType : 国产车
         * carBrand : JJ
         * carSeries : 进来了
         * carModel : 栏
         * carFrameNo : 看
         * carEngineNo : 家里
         * region : 北京 北京市 东城区
         * 等等 : KKK
         * shopCarGarage :
         * shopCarGarageName :
         * secondCarReport :
         * regDate :
         * mile :
         * carPrice :
         * evalPrice :
         * isAzGps :
         * carNumber :
         * carBrandName :
         * carSeriesName :
         * carModelName :
         */

        private String isPublicCard;
        private String carType;
        private String carBrand;
        private String carSeries;
        private String carModel;
        private String carFrameNo;
        private String carEngineNo;
        private String region;
        private String shopCarGarage;
        private String shopCarGarageName;
        private String secondCarReport;
        private String regDate;
        private String mile;
        private String invoicePrice;
        private String carPrice;
        private String evalPrice;
        private String isAzGps;
        private String carNumber;
        private String carBrandName;
        private String carSeriesName;
        private String carModelName;
        private String model;
        private String regAddress;
        private String shopCarGarageRate;

        public String getShopCarGarageRate() {
            return shopCarGarageRate;
        }

        public void setShopCarGarageRate(String shopCarGarageRate) {
            this.shopCarGarageRate = shopCarGarageRate;
        }

        public String getIsPublicCard() {
            return isPublicCard;
        }

        public void setIsPublicCard(String isPublicCard) {
            this.isPublicCard = isPublicCard;
        }

        public String getCarType() {
            return carType;
        }

        public void setCarType(String carType) {
            this.carType = carType;
        }

        public String getCarBrand() {
            return carBrand;
        }

        public void setCarBrand(String carBrand) {
            this.carBrand = carBrand;
        }

        public String getCarSeries() {
            return carSeries;
        }

        public void setCarSeries(String carSeries) {
            this.carSeries = carSeries;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public String getCarFrameNo() {
            return carFrameNo;
        }

        public void setCarFrameNo(String carFrameNo) {
            this.carFrameNo = carFrameNo;
        }

        public String getCarEngineNo() {
            return carEngineNo;
        }

        public void setCarEngineNo(String carEngineNo) {
            this.carEngineNo = carEngineNo;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getShopCarGarage() {
            return shopCarGarage;
        }

        public void setShopCarGarage(String shopCarGarage) {
            this.shopCarGarage = shopCarGarage;
        }

        public String getShopCarGarageName() {
            return shopCarGarageName;
        }

        public void setShopCarGarageName(String shopCarGarageName) {
            this.shopCarGarageName = shopCarGarageName;
        }

        public String getSecondCarReport() {
            return secondCarReport;
        }

        public void setSecondCarReport(String secondCarReport) {
            this.secondCarReport = secondCarReport;
        }

        public String getRegDate() {
            return regDate;
        }

        public void setRegDate(String regDate) {
            this.regDate = regDate;
        }

        public String getMile() {
            return mile;
        }

        public void setMile(String mile) {
            this.mile = mile;
        }

        public String getInvoicePrice() {
            return invoicePrice;
        }

        public void setInvoicePrice(String invoicePrice) {
            this.invoicePrice = invoicePrice;
        }

        public String getCarPrice() {
            return carPrice;
        }

        public void setCarPrice(String carPrice) {
            this.carPrice = carPrice;
        }

        public String getEvalPrice() {
            return evalPrice;
        }

        public void setEvalPrice(String evalPrice) {
            this.evalPrice = evalPrice;
        }

        public String getIsAzGps() {
            return isAzGps;
        }

        public void setIsAzGps(String isAzGps) {
            this.isAzGps = isAzGps;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getCarBrandName() {
            return carBrandName;
        }

        public void setCarBrandName(String carBrandName) {
            this.carBrandName = carBrandName;
        }

        public String getCarSeriesName() {
            return carSeriesName;
        }

        public void setCarSeriesName(String carSeriesName) {
            this.carSeriesName = carSeriesName;
        }

        public String getCarModelName() {
            return carModelName;
        }

        public void setCarModelName(String carModelName) {
            this.carModelName = carModelName;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getRegAddress() {
            return regAddress;
        }

        public void setRegAddress(String regAddress) {
            this.regAddress = regAddress;
        }
    }

    public static class BankBean implements Serializable {

        /**
         * code :
         * bankCode :
         * bankName :
         * bankFullName :
         * subbranch :
         * mobile :
         * rate12 :
         * rate18 :
         * rate24 :
         * rate36 :
         * 等等 :
         */

        private String code;
        private String bankCode;
        private String bankName;
        private String bankFullName;
        private String subbranch;
        private String mobile;
        private String rate12;
        private String rate18;
        private String rate24;
        private String rate36;

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

        public String getBankFullName() {
            return bankFullName;
        }

        public void setBankFullName(String bankFullName) {
            this.bankFullName = bankFullName;
        }

        public String getSubbranch() {
            return subbranch;
        }

        public void setSubbranch(String subbranch) {
            this.subbranch = subbranch;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getRate12() {
            return rate12;
        }

        public void setRate12(String rate12) {
            this.rate12 = rate12;
        }

        public String getRate18() {
            return rate18;
        }

        public void setRate18(String rate18) {
            this.rate18 = rate18;
        }

        public String getRate24() {
            return rate24;
        }

        public void setRate24(String rate24) {
            this.rate24 = rate24;
        }

        public String getRate36() {
            return rate36;
        }

        public void setRate36(String rate36) {
            this.rate36 = rate36;
        }

    }

    public static class AdvanceBean implements Serializable {

        /**
         * code : A202001011532132784615
         * bizCode : CB332019122003202A
         * backAdvanceStatus : 1
         * advanceFundDatetime : Jan 1, 2020 12:00:00 AM
         * advanceFundAmount : 300000
         * makeBillNote : 说你
         * advanceOutCardCode : CB201906171130293041327
         * advanceOutCard : {"code":"CB201906171130293041327","type":"4","advanceType":"1","companyCode":"DP201800000000000000001","realName":"户名","bankCode":"PAB","bankName":"平安银行","subbranch":"XX支行","bankcardNumber":"45670987654345672","pointRate":0.1,"remark":"","companyName":"温州浩源控股有限公司"}
         */

        private String code;
        private String bizCode;
        private String backAdvanceStatus;
        private String advanceFundDatetime;
        private String advanceFundAmount;
        private String makeBillNote;
        private String advanceOutCardCode;
        private AdvanceOutCardBean advanceOutCard;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getBizCode() {
            return bizCode;
        }

        public void setBizCode(String bizCode) {
            this.bizCode = bizCode;
        }

        public String getBackAdvanceStatus() {
            return backAdvanceStatus;
        }

        public void setBackAdvanceStatus(String backAdvanceStatus) {
            this.backAdvanceStatus = backAdvanceStatus;
        }

        public String getAdvanceFundDatetime() {
            return advanceFundDatetime;
        }

        public void setAdvanceFundDatetime(String advanceFundDatetime) {
            this.advanceFundDatetime = advanceFundDatetime;
        }

        public String getAdvanceFundAmount() {
            return advanceFundAmount;
        }

        public void setAdvanceFundAmount(String advanceFundAmount) {
            this.advanceFundAmount = advanceFundAmount;
        }

        public String getMakeBillNote() {
            return makeBillNote;
        }

        public void setMakeBillNote(String makeBillNote) {
            this.makeBillNote = makeBillNote;
        }

        public String getAdvanceOutCardCode() {
            return advanceOutCardCode;
        }

        public void setAdvanceOutCardCode(String advanceOutCardCode) {
            this.advanceOutCardCode = advanceOutCardCode;
        }

        public AdvanceOutCardBean getAdvanceOutCard() {
            return advanceOutCard;
        }

        public void setAdvanceOutCard(AdvanceOutCardBean advanceOutCard) {
            this.advanceOutCard = advanceOutCard;
        }

        public static class AdvanceOutCardBean implements Serializable {

            /**
             * code : CB201906171130293041327
             * type : 4
             * advanceType : 1
             * companyCode : DP201800000000000000001
             * realName : 户名
             * bankCode : PAB
             * bankName : 平安银行
             * subbranch : XX支行
             * bankcardNumber : 45670987654345672
             * pointRate : 0.1
             * remark :
             * companyName : 温州浩源控股有限公司
             */

            private String code;
            private String type;
            private String advanceType;
            private String companyCode;
            private String realName;
            private String bankCode;
            private String bankName;
            private String subbranch;
            private String bankcardNumber;
            private double pointRate;
            private String remark;
            private String companyName;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAdvanceType() {
                return advanceType;
            }

            public void setAdvanceType(String advanceType) {
                this.advanceType = advanceType;
            }

            public String getCompanyCode() {
                return companyCode;
            }

            public void setCompanyCode(String companyCode) {
                this.companyCode = companyCode;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
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

            public String getSubbranch() {
                return subbranch;
            }

            public void setSubbranch(String subbranch) {
                this.subbranch = subbranch;
            }

            public String getBankcardNumber() {
                return bankcardNumber;
            }

            public void setBankcardNumber(String bankcardNumber) {
                this.bankcardNumber = bankcardNumber;
            }

            public double getPointRate() {
                return pointRate;
            }

            public void setPointRate(double pointRate) {
                this.pointRate = pointRate;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getCompanyName() {
                return companyName;
            }

            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }
        }
    }

    public static class AttachmentsBean implements Serializable {

        /**
         * code : AT202001011352411891216
         * bizCode : CB332020010103301B
         * category : credit_user
         * kname : id_no_front_apply
         * vname : 申请人身份证正面
         * attachType : 图片
         * url : ANDROID_1577857644576_1134_724.jpg
         */

        private String code;
        private String bizCode;
        private String category;
        private String kname;
        private String vname;
        private String attachType;
        private String url;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getBizCode() {
            return bizCode;
        }

        public void setBizCode(String bizCode) {
            this.bizCode = bizCode;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getKname() {
            return kname;
        }

        public void setKname(String kname) {
            this.kname = kname;
        }

        public String getVname() {
            return vname;
        }

        public void setVname(String vname) {
            this.vname = vname;
        }

        public String getAttachType() {
            return attachType;
        }

        public void setAttachType(String attachType) {
            this.attachType = attachType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

}
