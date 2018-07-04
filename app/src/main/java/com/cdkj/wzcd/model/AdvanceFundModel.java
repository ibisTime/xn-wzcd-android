package com.cdkj.wzcd.model;

/**
 * Created by cdkj on 2018/7/3.
 */

public class AdvanceFundModel {


    /**
     * code : AF201807030909044214320
     * budgetCode : HX33180703000002
     * type : 1
     * customerName : 雷黔
     * companyCode : DP201800000000000000001
     * carDealerCode : CD201806291546038258895
     * useAmount : 29700000
     * loanAmount : 30000000
     * serviceCharge : 102800000
     * gpsFee : 300000
     * gpsFeeWay : 2
     * loanBankCode : BS201806281624274261029
     * isAdvanceFund : 1
     * collectBankcardCode : CB201806291546038315615
     * curNodeCode : 003_01
     * updater : U201806282054006348312
     * updateDatetime : Jul 3, 2018 9:09:04 AM
     * creditCode : C201807030826120873941
     * collectionAccountNo : 622262170000962
     * loanBankName : 中国工商银行
     * bizCompanyName : 温州浩源有限公司
     * carDealerName : 杭州仓前经销商
     * applyUserIdNo : 522101199507184619
     * budgetOrder : {"code":"HX33180703000002","creditCode":"C201807030826120873941","customerType":"1","customerName":"雷黔","customerSex":"男","customerBirth":"19950718","carDealerCode":"CD201806291546038258895","loanBankCode":"BS201806281624274261029","originalPrice":40000000,"carModel":"车辆型号","loanPeriods":12,"invoicePrice":40000000,"shopWay":"1","rateType":"1","loanAmount":30000000,"isSurvey":"1","bankRate":0.3,"companyLoanCs":0.75,"PreCompanyLoanCs":0,"isAdvanceFund":"1","globalRate":0.4,"preGlobalRate":0,"fee":3000000,"carDealerSubsidy":0,"bankLoanCs":0.82,"preBankLoanCs":0,"companyCode":"DP201800000000000000001","saleUserId":"U201806282054224028887","applyUserCompany":"橙袋","applyUserDuty":"程序员","applyUserGhrRelation":"1","marryState":"1","applyUserMonthIncome":20000000,"applyUserSettleInterest":10000000,"applyUserBalance":20000000,"applyUserJourShowIncome":"1","applyUserIsPrint":"1","ghMonthIncome":0,"ghSettleInterest":0,"ghBalance":0,"guarantor1MonthIncome":0,"guarantor1SettleInterest":0,"guarantor1Balance":0,"guarantor2MonthIncome":0,"guarantor2SettleInterest":0,"guarantor2Balance":0,"isHouseProperty":"0","houseProperty":"","houseInvoice":"","isLicense":"0","license":"","siteProve":"","siteArea":"","otherPropertyNote":"","applyBirthAddress":"杭州","applyNowAddress":"杭州","houseType":"1","ghBirthAddress":"杭州","guarantor1BirthAddress":"杭州","guarantor2BirthAddress":"杭州","otherNote":"","oilSubsidy":18000000,"oilSubsidyKil":100,"isPlatInsure":"1","gpsFee":300000,"gpsDeduct":9000000,"gpsFeeWay":"2","lyAmount":1000000,"fxAmount":100000000,"otherFee":1500000,"serviceCharge":102800000,"marryDivorce":"","applyUserHkb":"","bankBillPdf":"","singleProvePdf":"","incomeProvePdf":"","liveProvePdf":"","buildProvePdf":"","hkbFirstPage":"","hkbMainPage":"","ghHkb":"","guarantor1IdNo":"","guarantor1Hkb":"","guarantor2IdNo":"","guarantor2Hkb":"","housePic":"","houseUnitPic":"","houseDoorPic":"","houseRoomPic":"","houseCustomerPic":"","houseSaleCustomerPic":"","companyNamePic":"","companyPlacePic":"","companyWorkshopPic":"","companySaleCustomerPic":"","otherFilePdf":"","otherApplyNote":"","applyDatetime":"Jul 3, 2018 8:38:28 AM","frameNo":"车架号","insuranceRemindCount":0,"guarantMonthFeeRate":0,"repayBankDate":0,"mobile":"18984955240","idKind":"1","idNo":"522101199507184619","shouldBackAmount":29700000,"shouldBackStatus":"1","curNodeCode":"002_06"}
     */

    private String code;
    private String budgetCode;
    private String type;
    private String customerName;
    private String companyCode;
    private String carDealerCode;
    private int useAmount;
    private String loanAmount;
    private int serviceCharge;
    private int gpsFee;
    private String gpsFeeWay;
    private String loanBankCode;
    private String isAdvanceFund;
    private String collectBankcardCode;
    private String curNodeCode;
    private String updater;
    private String updateDatetime;
    private String creditCode;
    private String collectionAccountNo;
    private String loanBankName;
    private String bizCompanyName;
    private String carDealerName;
    private String applyUserIdNo;
    private BudgetOrderBean budgetOrder;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBudgetCode() {
        return budgetCode;
    }

    public void setBudgetCode(String budgetCode) {
        this.budgetCode = budgetCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCarDealerCode() {
        return carDealerCode;
    }

    public void setCarDealerCode(String carDealerCode) {
        this.carDealerCode = carDealerCode;
    }

    public int getUseAmount() {
        return useAmount;
    }

    public void setUseAmount(int useAmount) {
        this.useAmount = useAmount;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(int serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public int getGpsFee() {
        return gpsFee;
    }

    public void setGpsFee(int gpsFee) {
        this.gpsFee = gpsFee;
    }

    public String getGpsFeeWay() {
        return gpsFeeWay;
    }

    public void setGpsFeeWay(String gpsFeeWay) {
        this.gpsFeeWay = gpsFeeWay;
    }

    public String getLoanBankCode() {
        return loanBankCode;
    }

    public void setLoanBankCode(String loanBankCode) {
        this.loanBankCode = loanBankCode;
    }

    public String getIsAdvanceFund() {
        return isAdvanceFund;
    }

    public void setIsAdvanceFund(String isAdvanceFund) {
        this.isAdvanceFund = isAdvanceFund;
    }

    public String getCollectBankcardCode() {
        return collectBankcardCode;
    }

    public void setCollectBankcardCode(String collectBankcardCode) {
        this.collectBankcardCode = collectBankcardCode;
    }

    public String getCurNodeCode() {
        return curNodeCode;
    }

    public void setCurNodeCode(String curNodeCode) {
        this.curNodeCode = curNodeCode;
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

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getCollectionAccountNo() {
        return collectionAccountNo;
    }

    public void setCollectionAccountNo(String collectionAccountNo) {
        this.collectionAccountNo = collectionAccountNo;
    }

    public String getLoanBankName() {
        return loanBankName;
    }

    public void setLoanBankName(String loanBankName) {
        this.loanBankName = loanBankName;
    }

    public String getBizCompanyName() {
        return bizCompanyName;
    }

    public void setBizCompanyName(String bizCompanyName) {
        this.bizCompanyName = bizCompanyName;
    }

    public String getCarDealerName() {
        return carDealerName;
    }

    public void setCarDealerName(String carDealerName) {
        this.carDealerName = carDealerName;
    }

    public String getApplyUserIdNo() {
        return applyUserIdNo;
    }

    public void setApplyUserIdNo(String applyUserIdNo) {
        this.applyUserIdNo = applyUserIdNo;
    }

    public BudgetOrderBean getBudgetOrder() {
        return budgetOrder;
    }

    public void setBudgetOrder(BudgetOrderBean budgetOrder) {
        this.budgetOrder = budgetOrder;
    }

    public static class BudgetOrderBean {
        /**
         * code : HX33180703000002
         * creditCode : C201807030826120873941
         * customerType : 1
         * customerName : 雷黔
         * customerSex : 男
         * customerBirth : 19950718
         * carDealerCode : CD201806291546038258895
         * loanBankCode : BS201806281624274261029
         * originalPrice : 40000000
         * carModel : 车辆型号
         * loanPeriods : 12
         * invoicePrice : 40000000
         * shopWay : 1
         * rateType : 1
         * loanAmount : 30000000
         * isSurvey : 1
         * bankRate : 0.3
         * companyLoanCs : 0.75
         * PreCompanyLoanCs : 0
         * isAdvanceFund : 1
         * globalRate : 0.4
         * preGlobalRate : 0
         * fee : 3000000
         * carDealerSubsidy : 0
         * bankLoanCs : 0.82
         * preBankLoanCs : 0
         * companyCode : DP201800000000000000001
         * saleUserId : U201806282054224028887
         * applyUserCompany : 橙袋
         * applyUserDuty : 程序员
         * applyUserGhrRelation : 1
         * marryState : 1
         * applyUserMonthIncome : 20000000
         * applyUserSettleInterest : 10000000
         * applyUserBalance : 20000000
         * applyUserJourShowIncome : 1
         * applyUserIsPrint : 1
         * ghMonthIncome : 0
         * ghSettleInterest : 0
         * ghBalance : 0
         * guarantor1MonthIncome : 0
         * guarantor1SettleInterest : 0
         * guarantor1Balance : 0
         * guarantor2MonthIncome : 0
         * guarantor2SettleInterest : 0
         * guarantor2Balance : 0
         * isHouseProperty : 0
         * houseProperty :
         * houseInvoice :
         * isLicense : 0
         * license :
         * siteProve :
         * siteArea :
         * otherPropertyNote :
         * applyBirthAddress : 杭州
         * applyNowAddress : 杭州
         * houseType : 1
         * ghBirthAddress : 杭州
         * guarantor1BirthAddress : 杭州
         * guarantor2BirthAddress : 杭州
         * otherNote :
         * oilSubsidy : 18000000
         * oilSubsidyKil : 100
         * isPlatInsure : 1
         * gpsFee : 300000
         * gpsDeduct : 9000000
         * gpsFeeWay : 2
         * lyAmount : 1000000
         * fxAmount : 100000000
         * otherFee : 1500000
         * serviceCharge : 102800000
         * marryDivorce :
         * applyUserHkb :
         * bankBillPdf :
         * singleProvePdf :
         * incomeProvePdf :
         * liveProvePdf :
         * buildProvePdf :
         * hkbFirstPage :
         * hkbMainPage :
         * ghHkb :
         * guarantor1IdNo :
         * guarantor1Hkb :
         * guarantor2IdNo :
         * guarantor2Hkb :
         * housePic :
         * houseUnitPic :
         * houseDoorPic :
         * houseRoomPic :
         * houseCustomerPic :
         * houseSaleCustomerPic :
         * companyNamePic :
         * companyPlacePic :
         * companyWorkshopPic :
         * companySaleCustomerPic :
         * otherFilePdf :
         * otherApplyNote :
         * applyDatetime : Jul 3, 2018 8:38:28 AM
         * frameNo : 车架号
         * insuranceRemindCount : 0
         * guarantMonthFeeRate : 0
         * repayBankDate : 0
         * mobile : 18984955240
         * idKind : 1
         * idNo : 522101199507184619
         * shouldBackAmount : 29700000
         * shouldBackStatus : 1
         * curNodeCode : 002_06
         */

        private String code;
        private String creditCode;
        private String customerType;
        private String customerName;
        private String customerSex;
        private String customerBirth;
        private String carDealerCode;
        private String loanBankCode;
        private int originalPrice;
        private String carModel;
        private int loanPeriods;
        private int invoicePrice;
        private String shopWay;
        private String rateType;
        private String loanAmount;
        private String isSurvey;
        private double bankRate;
        private double companyLoanCs;
        private int PreCompanyLoanCs;
        private String isAdvanceFund;
        private double globalRate;
        private int preGlobalRate;
        private int fee;
        private int carDealerSubsidy;
        private double bankLoanCs;
        private int preBankLoanCs;
        private String companyCode;
        private String saleUserId;
        private String applyUserCompany;
        private String applyUserDuty;
        private String applyUserGhrRelation;
        private String marryState;
        private int applyUserMonthIncome;
        private int applyUserSettleInterest;
        private int applyUserBalance;
        private String applyUserJourShowIncome;
        private String applyUserIsPrint;
        private int ghMonthIncome;
        private int ghSettleInterest;
        private int ghBalance;
        private int guarantor1MonthIncome;
        private int guarantor1SettleInterest;
        private int guarantor1Balance;
        private int guarantor2MonthIncome;
        private int guarantor2SettleInterest;
        private int guarantor2Balance;
        private String isHouseProperty;
        private String houseProperty;
        private String houseInvoice;
        private String isLicense;
        private String license;
        private String siteProve;
        private String siteArea;
        private String otherPropertyNote;
        private String applyBirthAddress;
        private String applyNowAddress;
        private String houseType;
        private String ghBirthAddress;
        private String guarantor1BirthAddress;
        private String guarantor2BirthAddress;
        private String otherNote;
        private int oilSubsidy;
        private int oilSubsidyKil;
        private String isPlatInsure;
        private int gpsFee;
        private int gpsDeduct;
        private String gpsFeeWay;
        private int lyAmount;
        private int fxAmount;
        private int otherFee;
        private int serviceCharge;
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
        private String otherFilePdf;
        private String otherApplyNote;
        private String applyDatetime;
        private String frameNo;
        private int insuranceRemindCount;
        private int guarantMonthFeeRate;
        private int repayBankDate;
        private String mobile;
        private String idKind;
        private String idNo;
        private int shouldBackAmount;
        private String shouldBackStatus;
        private String curNodeCode;

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

        public String getCustomerSex() {
            return customerSex;
        }

        public void setCustomerSex(String customerSex) {
            this.customerSex = customerSex;
        }

        public String getCustomerBirth() {
            return customerBirth;
        }

        public void setCustomerBirth(String customerBirth) {
            this.customerBirth = customerBirth;
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

        public int getOriginalPrice() {
            return originalPrice;
        }

        public void setOriginalPrice(int originalPrice) {
            this.originalPrice = originalPrice;
        }

        public String getCarModel() {
            return carModel;
        }

        public void setCarModel(String carModel) {
            this.carModel = carModel;
        }

        public int getLoanPeriods() {
            return loanPeriods;
        }

        public void setLoanPeriods(int loanPeriods) {
            this.loanPeriods = loanPeriods;
        }

        public int getInvoicePrice() {
            return invoicePrice;
        }

        public void setInvoicePrice(int invoicePrice) {
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

        public int getPreCompanyLoanCs() {
            return PreCompanyLoanCs;
        }

        public void setPreCompanyLoanCs(int PreCompanyLoanCs) {
            this.PreCompanyLoanCs = PreCompanyLoanCs;
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

        public int getPreGlobalRate() {
            return preGlobalRate;
        }

        public void setPreGlobalRate(int preGlobalRate) {
            this.preGlobalRate = preGlobalRate;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public int getCarDealerSubsidy() {
            return carDealerSubsidy;
        }

        public void setCarDealerSubsidy(int carDealerSubsidy) {
            this.carDealerSubsidy = carDealerSubsidy;
        }

        public double getBankLoanCs() {
            return bankLoanCs;
        }

        public void setBankLoanCs(double bankLoanCs) {
            this.bankLoanCs = bankLoanCs;
        }

        public int getPreBankLoanCs() {
            return preBankLoanCs;
        }

        public void setPreBankLoanCs(int preBankLoanCs) {
            this.preBankLoanCs = preBankLoanCs;
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

        public String getApplyUserGhrRelation() {
            return applyUserGhrRelation;
        }

        public void setApplyUserGhrRelation(String applyUserGhrRelation) {
            this.applyUserGhrRelation = applyUserGhrRelation;
        }

        public String getMarryState() {
            return marryState;
        }

        public void setMarryState(String marryState) {
            this.marryState = marryState;
        }

        public int getApplyUserMonthIncome() {
            return applyUserMonthIncome;
        }

        public void setApplyUserMonthIncome(int applyUserMonthIncome) {
            this.applyUserMonthIncome = applyUserMonthIncome;
        }

        public int getApplyUserSettleInterest() {
            return applyUserSettleInterest;
        }

        public void setApplyUserSettleInterest(int applyUserSettleInterest) {
            this.applyUserSettleInterest = applyUserSettleInterest;
        }

        public int getApplyUserBalance() {
            return applyUserBalance;
        }

        public void setApplyUserBalance(int applyUserBalance) {
            this.applyUserBalance = applyUserBalance;
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

        public int getGhMonthIncome() {
            return ghMonthIncome;
        }

        public void setGhMonthIncome(int ghMonthIncome) {
            this.ghMonthIncome = ghMonthIncome;
        }

        public int getGhSettleInterest() {
            return ghSettleInterest;
        }

        public void setGhSettleInterest(int ghSettleInterest) {
            this.ghSettleInterest = ghSettleInterest;
        }

        public int getGhBalance() {
            return ghBalance;
        }

        public void setGhBalance(int ghBalance) {
            this.ghBalance = ghBalance;
        }

        public int getGuarantor1MonthIncome() {
            return guarantor1MonthIncome;
        }

        public void setGuarantor1MonthIncome(int guarantor1MonthIncome) {
            this.guarantor1MonthIncome = guarantor1MonthIncome;
        }

        public int getGuarantor1SettleInterest() {
            return guarantor1SettleInterest;
        }

        public void setGuarantor1SettleInterest(int guarantor1SettleInterest) {
            this.guarantor1SettleInterest = guarantor1SettleInterest;
        }

        public int getGuarantor1Balance() {
            return guarantor1Balance;
        }

        public void setGuarantor1Balance(int guarantor1Balance) {
            this.guarantor1Balance = guarantor1Balance;
        }

        public int getGuarantor2MonthIncome() {
            return guarantor2MonthIncome;
        }

        public void setGuarantor2MonthIncome(int guarantor2MonthIncome) {
            this.guarantor2MonthIncome = guarantor2MonthIncome;
        }

        public int getGuarantor2SettleInterest() {
            return guarantor2SettleInterest;
        }

        public void setGuarantor2SettleInterest(int guarantor2SettleInterest) {
            this.guarantor2SettleInterest = guarantor2SettleInterest;
        }

        public int getGuarantor2Balance() {
            return guarantor2Balance;
        }

        public void setGuarantor2Balance(int guarantor2Balance) {
            this.guarantor2Balance = guarantor2Balance;
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

        public int getOilSubsidy() {
            return oilSubsidy;
        }

        public void setOilSubsidy(int oilSubsidy) {
            this.oilSubsidy = oilSubsidy;
        }

        public int getOilSubsidyKil() {
            return oilSubsidyKil;
        }

        public void setOilSubsidyKil(int oilSubsidyKil) {
            this.oilSubsidyKil = oilSubsidyKil;
        }

        public String getIsPlatInsure() {
            return isPlatInsure;
        }

        public void setIsPlatInsure(String isPlatInsure) {
            this.isPlatInsure = isPlatInsure;
        }

        public int getGpsFee() {
            return gpsFee;
        }

        public void setGpsFee(int gpsFee) {
            this.gpsFee = gpsFee;
        }

        public int getGpsDeduct() {
            return gpsDeduct;
        }

        public void setGpsDeduct(int gpsDeduct) {
            this.gpsDeduct = gpsDeduct;
        }

        public String getGpsFeeWay() {
            return gpsFeeWay;
        }

        public void setGpsFeeWay(String gpsFeeWay) {
            this.gpsFeeWay = gpsFeeWay;
        }

        public int getLyAmount() {
            return lyAmount;
        }

        public void setLyAmount(int lyAmount) {
            this.lyAmount = lyAmount;
        }

        public int getFxAmount() {
            return fxAmount;
        }

        public void setFxAmount(int fxAmount) {
            this.fxAmount = fxAmount;
        }

        public int getOtherFee() {
            return otherFee;
        }

        public void setOtherFee(int otherFee) {
            this.otherFee = otherFee;
        }

        public int getServiceCharge() {
            return serviceCharge;
        }

        public void setServiceCharge(int serviceCharge) {
            this.serviceCharge = serviceCharge;
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

        public String getFrameNo() {
            return frameNo;
        }

        public void setFrameNo(String frameNo) {
            this.frameNo = frameNo;
        }

        public int getInsuranceRemindCount() {
            return insuranceRemindCount;
        }

        public void setInsuranceRemindCount(int insuranceRemindCount) {
            this.insuranceRemindCount = insuranceRemindCount;
        }

        public int getGuarantMonthFeeRate() {
            return guarantMonthFeeRate;
        }

        public void setGuarantMonthFeeRate(int guarantMonthFeeRate) {
            this.guarantMonthFeeRate = guarantMonthFeeRate;
        }

        public int getRepayBankDate() {
            return repayBankDate;
        }

        public void setRepayBankDate(int repayBankDate) {
            this.repayBankDate = repayBankDate;
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

        public int getShouldBackAmount() {
            return shouldBackAmount;
        }

        public void setShouldBackAmount(int shouldBackAmount) {
            this.shouldBackAmount = shouldBackAmount;
        }

        public String getShouldBackStatus() {
            return shouldBackStatus;
        }

        public void setShouldBackStatus(String shouldBackStatus) {
            this.shouldBackStatus = shouldBackStatus;
        }

        public String getCurNodeCode() {
            return curNodeCode;
        }

        public void setCurNodeCode(String curNodeCode) {
            this.curNodeCode = curNodeCode;
        }
    }
}
