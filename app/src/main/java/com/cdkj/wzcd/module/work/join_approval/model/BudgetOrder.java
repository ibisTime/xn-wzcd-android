//package com.cdkj.wzcd.module.work.join_approval.model;
//
//import java.util.Date;
//import java.util.List;
//
//import com.cdkj.loan.dao.base.ABaseDO;
//
///**
//* 预算单
//* @author: xieyj
//* @since: 2018-05-26 17:53:19
//* @history:
//*/
//public class BudgetOrder {
//    private static final long serialVersionUID = 1L;
//
//    // 编号
//    private String code;
//
//    // 征信单编号
//    private String creditCode;
//
//    // 客户类型
//    private String customerType;
//
//    // 客户姓名
//    private String customerName;
//
//    // 汽车经销商编号
//    private String carDealerCode;
//
//    // 贷款银行编号
//    private String loanBankCode;
//
//    // 经办支行
//    private String loanBankSubbranch;
//
//    // 厂商指导价
//    private Long originalPrice;
//
//    // 车辆型号
//    private String carModel;
//
//    // 贷款周期
//    private String loanPeriods;
//
//    // 发票价格
//    private Long invoicePrice;
//
//    // 购车途径
//    private String shopWay;
//
//    // 利率类型
//    private String rateType;
//
//    // 贷款金额
//    private Long loanAmount;
//
//    // 发票不匹配之前贷款金额
//    private Long preLoanAmount;
//
//    // 是否需要贷前调查
//    private String isSurvey;
//
//    // 银行利率
//    private double bankRate;
//
//    // 我司贷款成数
//    private double companyLoanCs;
//
//    // 是否垫资
//    private String isAdvanceFund;
//
//    // 综合利率
//    private double globalRate;
//
//    // 服务费
//    private Long fee;
//
//    // 厂家贴息
//    private Long carDealerSubsidy;
//
//    // 银行贷款成数
//    private double bankLoanCs;
//
//    // 业务公司编号
//    private String companyCode;
//
//    // 业务员编号
//    private String saleUserId;
//
//    // 申请人就职单位
//    private String applyUserCompany;
//
//    // 申请人职位
//    private String applyUserDuty;
//
//    // 申请人与共还人关系
//    private String applyUserGhrRelation;
//
//    // 婚姻状况
//    private String marryState;
//
//    // 申请人月收入
//    private Long applyUserMonthIncome;
//
//    // 申请人结息
//    private Long applyUserSettleInterest;
//
//    // 申请人余额
//    private Long applyUserBalance;
//
//    // 申请人流水是否体现月收入
//    private String applyUserJourShowIncome;
//
//    // 申请人是否打件
//    private String applyUserIsPrint;
//
//    // 共还人月收入
//    private Long ghMonthIncome;
//
//    // 共还人结息
//    private Long ghSettleInterest;
//
//    // 共还人余额
//    private Long ghBalance;
//
//    // 共还人流水是否体现月收入
//    private String ghJourShowIncome;
//
//    // 共还人是否打件
//    private String ghIsPrint;
//
//    // 担保人1月收入
//    private Long guarantor1MonthIncome;
//
//    // 担保人1结息
//    private Long guarantor1SettleInterest;
//
//    // 担保人1余额
//    private Long guarantor1Balance;
//
//    // 担保人1流水是否体现月收入
//    private String guarantor1JourShowIncome;
//
//    // 担保人1是否打件
//    private String guarantor1IsPrint;
//
//    // 担保人2月收入
//    private Long guarantor2MonthIncome;
//
//    // 担保人2结息
//    private Long guarantor2SettleInterest;
//
//    // 担保人2余额
//    private Long guarantor2Balance;
//
//    // 担保人2流水是否体现月收入
//    private String guarantor2JourShowIncome;
//
//    // 担保人2是否打件
//    private String guarantor2IsPrint;
//
//    // 其他收入说明
//    private String otherIncomeNote;
//
//    // 房产证情况
//    private String isHouseProperty;
//
//    // 房产证
//    private String houseProperty;
//
//    // 有无购房合同
//    private String isHouseContract;
//
//    // 购房合同
//    private String houseContract;
//
//    // 购房发票
//    private String houseInvoice;
//
//    // 营业执照情况
//    private String isLicense;
//
//    // 营业执照
//    private String license;
//
//    // 提供场地证明
//    private String isSiteProve;
//
//    // 场地证明
//    private String siteProve;
//
//    // 经营场地面积
//    private String siteArea;
//
//    // 现有车辆类型
//    private String carType;
//
//    // 有无驾照
//    private String isDriceLicense;
//
//    // 驾照
//    private String driceLicense;
//
//    // 其他资产说明
//    private String otherPropertyNote;
//
//    // 申请人户籍地
//    private String applyBirthAddress;
//
//    // 现住地址
//    private String applyNowAddress;
//
//    // 现住房屋类型
//    private String houseType;
//
//    // 共还人户籍地
//    private String ghBirthAddress;
//
//    // 担保1户籍地
//    private String guarantor1BirthAddress;
//
//    // 担保2户籍地
//    private String guarantor2BirthAddress;
//
//    // 其他情况说明
//    private String otherNote;
//
//    // 油补
//    private Long oilSubsidy;
//
//    // 油补公里数
//    private double oilSubsidyKil;
//
//    // 是否我司续保
//    private String isPlatInsure;
//
//    // GPS收费
//    private Long gpsFee;
//
//    // GPS提成
//    private Double gpsDeduct;
//
//    // GPS收费方式
//    private String gpsFeeWay;
//
//    // 履约保证金
//    private Long lyAmount;
//
//    // 担保风险金
//    private Long fxAmount;
//
//    // 杂费
//    private Long otherFee;
//
//    // 手续费收取方式
//    private String serviceChargeWay;
//
//    // 手续费（收客户手续费合计）
//    private Long serviceCharge;
//
//    // 结婚证离婚证
//    private String marryDivorce;
//
//    // 户口本(主贷本人页)
//    private String applyUserHkb;
//
//    // 银行流水
//    private String bankBillPdf;
//
//    // 单身证明
//    private String singleProvePdf;
//
//    // 收入证明
//    private String incomeProvePdf;
//
//    // 居住证明
//    private String liveProvePdf;
//
//    // 自建房证明
//    private String buildProvePdf;
//
//    // 户口本（首页）
//    private String hkbFirstPage;
//
//    // 户口本（户主页）
//    private String hkbMainPage;
//
//    // 共还人户口本
//    private String ghHkb;
//
//    // 担保人1身份证
//    private String guarantor1IdNo;
//
//    // 担保人1户口本
//    private String guarantor1Hkb;
//
//    // 担保人2身份证
//    private String guarantor2IdNo;
//
//    // 担保人2户口本
//    private String guarantor2Hkb;
//
//    // 小区外观
//    private String housePic;
//
//    // 单元楼照片
//    private String houseUnitPic;
//
//    // 门牌照片
//    private String houseDoorPic;
//
//    // 客厅照片
//    private String houseRoomPic;
//
//    // 主贷与住宅合影
//    private String houseCustomerPic;
//
//    // 签约员与客户在房子合影照片
//    private String houseSaleCustomerPic;
//
//    // 企业名称照片
//    private String companyNamePic;
//
//    // 办公场地照片
//    private String companyPlacePic;
//
//    // 生产车间照片
//    private String companyWorkshopPic;
//
//    // 签约员与客户在公司合影照片
//    private String companySaleCustomerPic;
//
//    // 二手车合格证
//    private String secondHgz;
//
//    // 里程表
//    private String secondOdometer;
//
//    // 车前正面照
//    private String secondCarFrontPic;
//
//    // 中控台
//    private String secondConsolePic;
//
//    // 车300评估页
//    private String second300Pdf;
//
//    // 汽修宝截图
//    private String secondQxbPic;
//
//    // 车内饰
//    private String secondCarInPic;
//
//    // 铭牌
//    private String secondNumber;
//
//    // 其他材料附件
//    private String otherFilePdf;
//
//    // 申请说明事项
//    private String otherApplyNote;
//
//    // 申请时间
//    private Date applyDatetime;
//
//    // 撤销理由
//    private String cancelReason;
//
//    // 银行提交时间
//    private Date bankCommitDatetime;
//
//    // 银行提交说明
//    private String bankCommitNote;
//
//    // 银行放款金额
//    private Long bankFkAmount;
//
//    // 银行放款时间
//    private Date bankFkDatetime;
//
//    // 收款银行
//    private String bankReceiptCode;
//
//    // 收款银行账号
//    private String bankReceiptNumber;
//
//    // 收款凭证
//    private String bankReceiptPdf;
//
//    // 收款说明
//    private String bankReceiptNote;
//
//    // 抵押提交时间
//    private Date pledgeCommitDatetime;
//
//    // 抵押提交说明
//    private String pledgeCommitNote;
//
//    // 抵押合同编号
//    private String pledgeContractCode;
//
//    // 保险公司编号
//    private String insuranceCompanyCode;
//
//    // 车辆颜色
//    private String carColor;
//
//    // 车辆品牌
//    private String carBrand;
//
//    // 车架号码
//    private String frameNo;
//
//    // 发动机号码
//    private String engineNo;
//
//    // 商业险合计
//    private String commerceInsurance;
//
//    // 保险生效日期
//    private Date insuranceEffectDatetime;
//
//    // 保险经办银行
//    private String insuranceBank;
//
//    // 续保提醒次数
//    private int insuranceRemindCount;
//
//    // 投保时间
//    private Date insuranceApplyDatetime;
//
//    // 到期日期
//    private Date insuranceEndDatetime;
//
//    // 交强险保单
//    private String insuranceForcePdf;
//
//    // 商业险保单
//    private String insuranceBusinessPdf;
//
//    // 续保说明
//    private String insuranceNote;
//
//    // 担保合同编号
//    private String guaranteeContractCode;
//
//    // 登记证书号
//    private String regCertificateCode;
//
//    // 其他联系人
//    private String otherContact;
//
//    // 联系人手机
//    private String contactMobile;
//
//    // 担保人姓名
//    private String guarantorName;
//
//    // 担保人手机
//    private String guarantorMobile;
//
//    // 银行卡号
//    private String bankCardNumber;
//
//    // 对账单日
//    private Date billDatetime;
//
//    // 月还款额
//    private Long monthAmount;
//
//    // 银行还款日
//    private int repayBankDate;
//
//    // 首期还款金额
//    private Long repayFirstMonthAmount;
//
//    // 首期还款日期
//    private Date repayFirstMonthDatetime;
//
//    // 每期月供金额
//    private Long repayMonthAmount;
//
//    // 手机号
//    private String mobile;
//
//    // 证件类型
//    private String idKind;
//
//    // 身份证号
//    private String idNo;
//
//    // 身份证照片
//    private String idNoPic;
//
//    // 已入档清单
//    private String fileList;
//
//    // 资料是否完善
//    private String isComplete;
//
//    // 存放位置
//    private String storePlace;
//
//    // 入档备注
//    private String fileRemark;
//
//    // 经办人
//    private String operator;
//
//    // 经办日期
//    private Date operateDatetime;
//
//    // 经办部门
//    private String operateDepartment;
//
//    // 提车日期
//    private Date deliveryDatetime;
//
//    // 发票是否正确
//    private String isRightInvoice;
//
//    // 现发票价
//    private Long currentInvoicePrice;
//
//    // 发票
//    private String invoice;
//
//    // 合格证
//    private String certification;
//
//    // 交强险
//    private Long forceInsurance;
//
//    // 商业险
//    private Long businessInsurance;
//
//    // 机动车登记证书
//    private String motorRegCertification;
//
//    // 批单
//    private String pdPdf;
//
//    // 发保合备注
//    private String fbhRemark;
//
//    // 发保合预警天数
//    private Integer fbhWarnDay;
//
//    // 发保合状态（已录入/待录入）
//    private String fbhStatus;
//
//    // 应退按揭款
//    private Long shouldBackAmount;
//
//    // 退按揭款状态(0无需退款1银行已放款待财务退款2财务已退垫资款)
//    private String shouldBackStatus;
//
//    // 退按揭款付款时间
//    private Date shouldBackDatetime;
//
//    // 退按揭款付款账号
//    private String shouldBackBankcardCode;
//
//    // 退按揭款付款凭证
//    private String shouldBackBillPdf;
//
//    // 作废申请日期
//    private Date zfApplyDatetime;
//
//    // 作废作废原因
//    private String zfReason;
//
//    // 作废收款金额
//    private Long zfSkAmount;
//
//    // 作废收款账号编号
//    private String zfSkBankcardCode;
//
//    // 作废收款时间
//    private Date zfSkReceiptDatetime;
//
//    // 作废财务备注
//    private String zfFinanceRemark;
//
//    // 制卡状态
//    private String makeCardStatus;
//
//    // 制卡更新人
//    private String makeCardOperator;
//
//    // 制卡备注
//    private String makeCardRemark;
//
//    // 冻结状态(0冻结 1正常)
//    private String frozenStatus;
//
//    // 客户申请作废时的节点编号
//    private String cancelNodeCode;
//
//    // 是否提交作废申请
//    private String isSubmitCancel;
//
//    // 节点编号
//    private String curNodeCode;
//
//    // 还款业务编号
//    private String repayBizCode;
//
//    /*-------辅助字段-------*/
//
//    private String roleCode;// 角色编号
//
//    private Date applyDatetimeStart;// 申请时间起始
//
//    private Date applyDatetimeEnd;// 申请时间结束
//
//    private List<String> fileListArray;
//
//    private String bankReceiptName;// 收款银行
//
//    private String carDealerName;// 汽车经销商
//
//    private String insuranceCompanyName;// 保险公司
//
//    private String loanBankName;// 贷款银行
//
//    private String operateDepartmentName;// 经办部门
//
//    private String operatorName;// 经办人
//
//    private User user; // 用户
//
//    private String saleUserName;// 业务员
//
//    private String companyName;// 业务公司名称
//
//    private Bank bank;// 贷款银行
//
//    private List<BudgetOrderGps> budgetOrderGpsList;
//
//    private BankSubbranch bankSubbranch;// 支行
//
//    private List<String> curNodeCodeList;// 节点
//
//    private List<RepointDetail> repointDetailList1;// 返点，1应退按揭款
//
//    private List<RepointDetail> repointDetailList2;// 返点，2协议内返点
//
//    private List<RepointDetail> repointDetailList3; // 返点，3协议外返点
//
//    private String companyLoanCsSection;// 我司贷款成数区间
//
//    public String getCompanyLoanCsSection() {
//        return companyLoanCsSection;
//    }
//
//    public void setCompanyLoanCsSection(String companyLoanCsSection) {
//        this.companyLoanCsSection = companyLoanCsSection;
//    }
//
//    public List<RepointDetail> getRepointDetailList1() {
//        return repointDetailList1;
//    }
//
//    public void setRepointDetailList1(List<RepointDetail> repointDetailList1) {
//        this.repointDetailList1 = repointDetailList1;
//    }
//
//    public List<RepointDetail> getRepointDetailList2() {
//        return repointDetailList2;
//    }
//
//    public void setRepointDetailList2(List<RepointDetail> repointDetailList2) {
//        this.repointDetailList2 = repointDetailList2;
//    }
//
//    public List<RepointDetail> getRepointDetailList3() {
//        return repointDetailList3;
//    }
//
//    public void setRepointDetailList3(List<RepointDetail> repointDetailList3) {
//        this.repointDetailList3 = repointDetailList3;
//    }
//
//    public String getCompanyName() {
//        return companyName;
//    }
//
//    public void setCompanyName(String companyName) {
//        this.companyName = companyName;
//    }
//
//    public List<String> getCurNodeCodeList() {
//        return curNodeCodeList;
//    }
//
//    public void setCurNodeCodeList(List<String> curNodeCodeList) {
//        this.curNodeCodeList = curNodeCodeList;
//    }
//
//    public List<BudgetOrderGps> getBudgetOrderGpsList() {
//        return budgetOrderGpsList;
//    }
//
//    public void setBudgetOrderGpsList(List<BudgetOrderGps> budgetOrderGpsList) {
//        this.budgetOrderGpsList = budgetOrderGpsList;
//    }
//
//    public Bank getBank() {
//        return bank;
//    }
//
//    public void setBank(Bank bank) {
//        this.bank = bank;
//    }
//
//    public BankSubbranch getBankSubbranch() {
//        return bankSubbranch;
//    }
//
//    public void setBankSubbranch(BankSubbranch bankSubbranch) {
//        this.bankSubbranch = bankSubbranch;
//    }
//
//    public String getRoleCode() {
//        return roleCode;
//    }
//
//    public void setRoleCode(String roleCode) {
//        this.roleCode = roleCode;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public String getRepayBizCode() {
//        return repayBizCode;
//    }
//
//    public void setRepayBizCode(String repayBizCode) {
//        this.repayBizCode = repayBizCode;
//    }
//
//    public Long getPreLoanAmount() {
//        return preLoanAmount;
//    }
//
//    public void setPreLoanAmount(Long preLoanAmount) {
//        this.preLoanAmount = preLoanAmount;
//    }
//
//    public String getBankReceiptName() {
//        return bankReceiptName;
//    }
//
//    public void setBankReceiptName(String bankReceiptName) {
//        this.bankReceiptName = bankReceiptName;
//    }
//
//    public String getCarDealerName() {
//        return carDealerName;
//    }
//
//    public void setCarDealerName(String carDealerName) {
//        this.carDealerName = carDealerName;
//    }
//
//    public String getMakeCardStatus() {
//        return makeCardStatus;
//    }
//
//    public void setMakeCardStatus(String makeCardStatus) {
//        this.makeCardStatus = makeCardStatus;
//    }
//
//    public String getMakeCardOperator() {
//        return makeCardOperator;
//    }
//
//    public void setMakeCardOperator(String makeCardOperator) {
//        this.makeCardOperator = makeCardOperator;
//    }
//
//    public String getMakeCardRemark() {
//        return makeCardRemark;
//    }
//
//    public void setMakeCardRemark(String makeCardRemark) {
//        this.makeCardRemark = makeCardRemark;
//    }
//
//    public String getFrozenStatus() {
//        return frozenStatus;
//    }
//
//    public void setFrozenStatus(String frozenStatus) {
//        this.frozenStatus = frozenStatus;
//    }
//
//    public String getCancelNodeCode() {
//        return cancelNodeCode;
//    }
//
//    public void setCancelNodeCode(String cancelNodeCode) {
//        this.cancelNodeCode = cancelNodeCode;
//    }
//
//    public String getIsSubmitCancel() {
//        return isSubmitCancel;
//    }
//
//    public void setIsSubmitCancel(String isSubmitCancel) {
//        this.isSubmitCancel = isSubmitCancel;
//    }
//
//    public String getShouldBackStatus() {
//        return shouldBackStatus;
//    }
//
//    public Date getShouldBackDatetime() {
//        return shouldBackDatetime;
//    }
//
//    public void setShouldBackDatetime(Date shouldBackDatetime) {
//        this.shouldBackDatetime = shouldBackDatetime;
//    }
//
//    public void setShouldBackStatus(String shouldBackStatus) {
//        this.shouldBackStatus = shouldBackStatus;
//    }
//
//    public String getShouldBackBankcardCode() {
//        return shouldBackBankcardCode;
//    }
//
//    public void setShouldBackBankcardCode(String shouldBackBankcardCode) {
//        this.shouldBackBankcardCode = shouldBackBankcardCode;
//    }
//
//    public String getShouldBackBillPdf() {
//        return shouldBackBillPdf;
//    }
//
//    public void setShouldBackBillPdf(String shouldBackBillPdf) {
//        this.shouldBackBillPdf = shouldBackBillPdf;
//    }
//
//    public String getInsuranceCompanyName() {
//        return insuranceCompanyName;
//    }
//
//    public void setInsuranceCompanyName(String insuranceCompanyName) {
//        this.insuranceCompanyName = insuranceCompanyName;
//    }
//
//    public String getLoanBankName() {
//        return loanBankName;
//    }
//
//    public void setLoanBankName(String loanBankName) {
//        this.loanBankName = loanBankName;
//    }
//
//    public String getOperateDepartmentName() {
//        return operateDepartmentName;
//    }
//
//    public void setOperateDepartmentName(String operateDepartmentName) {
//        this.operateDepartmentName = operateDepartmentName;
//    }
//
//    public String getOperatorName() {
//        return operatorName;
//    }
//
//    public void setOperatorName(String operatorName) {
//        this.operatorName = operatorName;
//    }
//
//    public String getSaleUserName() {
//        return saleUserName;
//    }
//
//    public void setSaleUserName(String saleUserName) {
//        this.saleUserName = saleUserName;
//    }
//
//    public List<String> getFileListArray() {
//        return fileListArray;
//    }
//
//    public void setFileListArray(List<String> fileListArray) {
//        this.fileListArray = fileListArray;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public String getCustomerType() {
//        return customerType;
//    }
//
//    public void setCustomerType(String customerType) {
//        this.customerType = customerType;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public String getCarDealerCode() {
//        return carDealerCode;
//    }
//
//    public void setCarDealerCode(String carDealerCode) {
//        this.carDealerCode = carDealerCode;
//    }
//
//    public String getLoanBankCode() {
//        return loanBankCode;
//    }
//
//    public void setLoanBankCode(String loanBankCode) {
//        this.loanBankCode = loanBankCode;
//    }
//
//    public String getLoanBankSubbranch() {
//        return loanBankSubbranch;
//    }
//
//    public void setLoanBankSubbranch(String loanBankSubbranch) {
//        this.loanBankSubbranch = loanBankSubbranch;
//    }
//
//    public Long getOriginalPrice() {
//        return originalPrice;
//    }
//
//    public void setOriginalPrice(Long originalPrice) {
//        this.originalPrice = originalPrice;
//    }
//
//    public String getCarModel() {
//        return carModel;
//    }
//
//    public void setCarModel(String carModel) {
//        this.carModel = carModel;
//    }
//
//    public String getLoanPeriods() {
//        return loanPeriods;
//    }
//
//    public void setLoanPeriods(String loanPeriods) {
//        this.loanPeriods = loanPeriods;
//    }
//
//    public Long getInvoicePrice() {
//        return invoicePrice;
//    }
//
//    public void setInvoicePrice(Long invoicePrice) {
//        this.invoicePrice = invoicePrice;
//    }
//
//    public String getShopWay() {
//        return shopWay;
//    }
//
//    public void setShopWay(String shopWay) {
//        this.shopWay = shopWay;
//    }
//
//    public String getRateType() {
//        return rateType;
//    }
//
//    public void setRateType(String rateType) {
//        this.rateType = rateType;
//    }
//
//    public Long getLoanAmount() {
//        return loanAmount;
//    }
//
//    public void setLoanAmount(Long loanAmount) {
//        this.loanAmount = loanAmount;
//    }
//
//    public String getIsSurvey() {
//        return isSurvey;
//    }
//
//    public void setIsSurvey(String isSurvey) {
//        this.isSurvey = isSurvey;
//    }
//
//    public double getBankRate() {
//        return bankRate;
//    }
//
//    public void setBankRate(double bankRate) {
//        this.bankRate = bankRate;
//    }
//
//    public double getCompanyLoanCs() {
//        return companyLoanCs;
//    }
//
//    public void setCompanyLoanCs(double companyLoanCs) {
//        this.companyLoanCs = companyLoanCs;
//    }
//
//    public String getIsAdvanceFund() {
//        return isAdvanceFund;
//    }
//
//    public void setIsAdvanceFund(String isAdvanceFund) {
//        this.isAdvanceFund = isAdvanceFund;
//    }
//
//    public double getGlobalRate() {
//        return globalRate;
//    }
//
//    public void setGlobalRate(double globalRate) {
//        this.globalRate = globalRate;
//    }
//
//    public Long getFee() {
//        return fee;
//    }
//
//    public void setFee(Long fee) {
//        this.fee = fee;
//    }
//
//    public Long getCarDealerSubsidy() {
//        return carDealerSubsidy;
//    }
//
//    public void setCarDealerSubsidy(Long carDealerSubsidy) {
//        this.carDealerSubsidy = carDealerSubsidy;
//    }
//
//    public double getBankLoanCs() {
//        return bankLoanCs;
//    }
//
//    public void setBankLoanCs(double bankLoanCs) {
//        this.bankLoanCs = bankLoanCs;
//    }
//
//    public String getCompanyCode() {
//        return companyCode;
//    }
//
//    public void setCompanyCode(String companyCode) {
//        this.companyCode = companyCode;
//    }
//
//    public String getSaleUserId() {
//        return saleUserId;
//    }
//
//    public void setSaleUserId(String saleUserId) {
//        this.saleUserId = saleUserId;
//    }
//
//    public String getApplyUserCompany() {
//        return applyUserCompany;
//    }
//
//    public void setApplyUserCompany(String applyUserCompany) {
//        this.applyUserCompany = applyUserCompany;
//    }
//
//    public String getApplyUserDuty() {
//        return applyUserDuty;
//    }
//
//    public void setApplyUserDuty(String applyUserDuty) {
//        this.applyUserDuty = applyUserDuty;
//    }
//
//    public String getApplyUserGhrRelation() {
//        return applyUserGhrRelation;
//    }
//
//    public void setApplyUserGhrRelation(String applyUserGhrRelation) {
//        this.applyUserGhrRelation = applyUserGhrRelation;
//    }
//
//    public String getMarryState() {
//        return marryState;
//    }
//
//    public void setMarryState(String marryState) {
//        this.marryState = marryState;
//    }
//
//    public String getOtherIncomeNote() {
//        return otherIncomeNote;
//    }
//
//    public void setOtherIncomeNote(String otherIncomeNote) {
//        this.otherIncomeNote = otherIncomeNote;
//    }
//
//    public String getIsHouseProperty() {
//        return isHouseProperty;
//    }
//
//    public void setIsHouseProperty(String isHouseProperty) {
//        this.isHouseProperty = isHouseProperty;
//    }
//
//    public String getHouseProperty() {
//        return houseProperty;
//    }
//
//    public void setHouseProperty(String houseProperty) {
//        this.houseProperty = houseProperty;
//    }
//
//    public String getIsHouseContract() {
//        return isHouseContract;
//    }
//
//    public void setIsHouseContract(String isHouseContract) {
//        this.isHouseContract = isHouseContract;
//    }
//
//    public String getHouseContract() {
//        return houseContract;
//    }
//
//    public void setHouseContract(String houseContract) {
//        this.houseContract = houseContract;
//    }
//
//    public String getHouseInvoice() {
//        return houseInvoice;
//    }
//
//    public void setHouseInvoice(String houseInvoice) {
//        this.houseInvoice = houseInvoice;
//    }
//
//    public String getIsLicense() {
//        return isLicense;
//    }
//
//    public void setIsLicense(String isLicense) {
//        this.isLicense = isLicense;
//    }
//
//    public String getLicense() {
//        return license;
//    }
//
//    public void setLicense(String license) {
//        this.license = license;
//    }
//
//    public String getIsSiteProve() {
//        return isSiteProve;
//    }
//
//    public void setIsSiteProve(String isSiteProve) {
//        this.isSiteProve = isSiteProve;
//    }
//
//    public String getSiteProve() {
//        return siteProve;
//    }
//
//    public void setSiteProve(String siteProve) {
//        this.siteProve = siteProve;
//    }
//
//    public String getSiteArea() {
//        return siteArea;
//    }
//
//    public void setSiteArea(String siteArea) {
//        this.siteArea = siteArea;
//    }
//
//    public String getCarType() {
//        return carType;
//    }
//
//    public void setCarType(String carType) {
//        this.carType = carType;
//    }
//
//    public String getIsDriceLicense() {
//        return isDriceLicense;
//    }
//
//    public void setIsDriceLicense(String isDriceLicense) {
//        this.isDriceLicense = isDriceLicense;
//    }
//
//    public String getDriceLicense() {
//        return driceLicense;
//    }
//
//    public void setDriceLicense(String driceLicense) {
//        this.driceLicense = driceLicense;
//    }
//
//    public String getOtherPropertyNote() {
//        return otherPropertyNote;
//    }
//
//    public void setOtherPropertyNote(String otherPropertyNote) {
//        this.otherPropertyNote = otherPropertyNote;
//    }
//
//    public String getApplyBirthAddress() {
//        return applyBirthAddress;
//    }
//
//    public void setApplyBirthAddress(String applyBirthAddress) {
//        this.applyBirthAddress = applyBirthAddress;
//    }
//
//    public String getApplyNowAddress() {
//        return applyNowAddress;
//    }
//
//    public void setApplyNowAddress(String applyNowAddress) {
//        this.applyNowAddress = applyNowAddress;
//    }
//
//    public String getHouseType() {
//        return houseType;
//    }
//
//    public void setHouseType(String houseType) {
//        this.houseType = houseType;
//    }
//
//    public String getGhBirthAddress() {
//        return ghBirthAddress;
//    }
//
//    public void setGhBirthAddress(String ghBirthAddress) {
//        this.ghBirthAddress = ghBirthAddress;
//    }
//
//    public String getGuarantor1BirthAddress() {
//        return guarantor1BirthAddress;
//    }
//
//    public void setGuarantor1BirthAddress(String guarantor1BirthAddress) {
//        this.guarantor1BirthAddress = guarantor1BirthAddress;
//    }
//
//    public String getGuarantor2BirthAddress() {
//        return guarantor2BirthAddress;
//    }
//
//    public void setGuarantor2BirthAddress(String guarantor2BirthAddress) {
//        this.guarantor2BirthAddress = guarantor2BirthAddress;
//    }
//
//    public String getOtherNote() {
//        return otherNote;
//    }
//
//    public void setOtherNote(String otherNote) {
//        this.otherNote = otherNote;
//    }
//
//    public Long getOilSubsidy() {
//        return oilSubsidy;
//    }
//
//    public void setOilSubsidy(Long oilSubsidy) {
//        this.oilSubsidy = oilSubsidy;
//    }
//
//    public String getIsPlatInsure() {
//        return isPlatInsure;
//    }
//
//    public void setIsPlatInsure(String isPlatInsure) {
//        this.isPlatInsure = isPlatInsure;
//    }
//
//    public Long getGpsFee() {
//        return gpsFee;
//    }
//
//    public void setGpsFee(Long gpsFee) {
//        this.gpsFee = gpsFee;
//    }
//
//    public String getGpsFeeWay() {
//        return gpsFeeWay;
//    }
//
//    public void setGpsFeeWay(String gpsFeeWay) {
//        this.gpsFeeWay = gpsFeeWay;
//    }
//
//    public Long getLyAmount() {
//        return lyAmount;
//    }
//
//    public void setLyAmount(Long lyAmount) {
//        this.lyAmount = lyAmount;
//    }
//
//    public Long getFxAmount() {
//        return fxAmount;
//    }
//
//    public void setFxAmount(Long fxAmount) {
//        this.fxAmount = fxAmount;
//    }
//
//    public Long getOtherFee() {
//        return otherFee;
//    }
//
//    public void setOtherFee(Long otherFee) {
//        this.otherFee = otherFee;
//    }
//
//    public String getMarryDivorce() {
//        return marryDivorce;
//    }
//
//    public void setMarryDivorce(String marryDivorce) {
//        this.marryDivorce = marryDivorce;
//    }
//
//    public String getApplyUserHkb() {
//        return applyUserHkb;
//    }
//
//    public void setApplyUserHkb(String applyUserHkb) {
//        this.applyUserHkb = applyUserHkb;
//    }
//
//    public String getBankBillPdf() {
//        return bankBillPdf;
//    }
//
//    public void setBankBillPdf(String bankBillPdf) {
//        this.bankBillPdf = bankBillPdf;
//    }
//
//    public String getSingleProvePdf() {
//        return singleProvePdf;
//    }
//
//    public void setSingleProvePdf(String singleProvePdf) {
//        this.singleProvePdf = singleProvePdf;
//    }
//
//    public String getIncomeProvePdf() {
//        return incomeProvePdf;
//    }
//
//    public void setIncomeProvePdf(String incomeProvePdf) {
//        this.incomeProvePdf = incomeProvePdf;
//    }
//
//    public String getLiveProvePdf() {
//        return liveProvePdf;
//    }
//
//    public void setLiveProvePdf(String liveProvePdf) {
//        this.liveProvePdf = liveProvePdf;
//    }
//
//    public String getBuildProvePdf() {
//        return buildProvePdf;
//    }
//
//    public void setBuildProvePdf(String buildProvePdf) {
//        this.buildProvePdf = buildProvePdf;
//    }
//
//    public String getHkbFirstPage() {
//        return hkbFirstPage;
//    }
//
//    public void setHkbFirstPage(String hkbFirstPage) {
//        this.hkbFirstPage = hkbFirstPage;
//    }
//
//    public String getHkbMainPage() {
//        return hkbMainPage;
//    }
//
//    public void setHkbMainPage(String hkbMainPage) {
//        this.hkbMainPage = hkbMainPage;
//    }
//
//    public String getGhHkb() {
//        return ghHkb;
//    }
//
//    public void setGhHkb(String ghHkb) {
//        this.ghHkb = ghHkb;
//    }
//
//    public String getGuarantor1IdNo() {
//        return guarantor1IdNo;
//    }
//
//    public void setGuarantor1IdNo(String guarantor1IdNo) {
//        this.guarantor1IdNo = guarantor1IdNo;
//    }
//
//    public String getGuarantor1Hkb() {
//        return guarantor1Hkb;
//    }
//
//    public void setGuarantor1Hkb(String guarantor1Hkb) {
//        this.guarantor1Hkb = guarantor1Hkb;
//    }
//
//    public String getGuarantor2IdNo() {
//        return guarantor2IdNo;
//    }
//
//    public void setGuarantor2IdNo(String guarantor2IdNo) {
//        this.guarantor2IdNo = guarantor2IdNo;
//    }
//
//    public String getGuarantor2Hkb() {
//        return guarantor2Hkb;
//    }
//
//    public void setGuarantor2Hkb(String guarantor2Hkb) {
//        this.guarantor2Hkb = guarantor2Hkb;
//    }
//
//    public String getHousePic() {
//        return housePic;
//    }
//
//    public void setHousePic(String housePic) {
//        this.housePic = housePic;
//    }
//
//    public String getHouseUnitPic() {
//        return houseUnitPic;
//    }
//
//    public void setHouseUnitPic(String houseUnitPic) {
//        this.houseUnitPic = houseUnitPic;
//    }
//
//    public String getHouseDoorPic() {
//        return houseDoorPic;
//    }
//
//    public void setHouseDoorPic(String houseDoorPic) {
//        this.houseDoorPic = houseDoorPic;
//    }
//
//    public String getHouseRoomPic() {
//        return houseRoomPic;
//    }
//
//    public void setHouseRoomPic(String houseRoomPic) {
//        this.houseRoomPic = houseRoomPic;
//    }
//
//    public int getRepayBankDate() {
//        return repayBankDate;
//    }
//
//    public void setRepayBankDate(int repayBankDate) {
//        this.repayBankDate = repayBankDate;
//    }
//
//    public Long getRepayFirstMonthAmount() {
//        return repayFirstMonthAmount;
//    }
//
//    public void setRepayFirstMonthAmount(Long repayFirstMonthAmount) {
//        this.repayFirstMonthAmount = repayFirstMonthAmount;
//    }
//
//    public Date getRepayFirstMonthDatetime() {
//        return repayFirstMonthDatetime;
//    }
//
//    public void setRepayFirstMonthDatetime(Date repayFirstMonthDatetime) {
//        this.repayFirstMonthDatetime = repayFirstMonthDatetime;
//    }
//
//    public Long getRepayMonthAmount() {
//        return repayMonthAmount;
//    }
//
//    public void setRepayMonthAmount(Long repayMonthAmount) {
//        this.repayMonthAmount = repayMonthAmount;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//
//    public String getIdKind() {
//        return idKind;
//    }
//
//    public void setIdKind(String idKind) {
//        this.idKind = idKind;
//    }
//
//    public String getIdNo() {
//        return idNo;
//    }
//
//    public void setIdNo(String idNo) {
//        this.idNo = idNo;
//    }
//
//    public String getHouseCustomerPic() {
//        return houseCustomerPic;
//    }
//
//    public Long getApplyUserMonthIncome() {
//        return applyUserMonthIncome;
//    }
//
//    public void setApplyUserMonthIncome(Long applyUserMonthIncome) {
//        this.applyUserMonthIncome = applyUserMonthIncome;
//    }
//
//    public Long getApplyUserSettleInterest() {
//        return applyUserSettleInterest;
//    }
//
//    public void setApplyUserSettleInterest(Long applyUserSettleInterest) {
//        this.applyUserSettleInterest = applyUserSettleInterest;
//    }
//
//    public Long getApplyUserBalance() {
//        return applyUserBalance;
//    }
//
//    public void setApplyUserBalance(Long applyUserBalance) {
//        this.applyUserBalance = applyUserBalance;
//    }
//
//    public String getApplyUserJourShowIncome() {
//        return applyUserJourShowIncome;
//    }
//
//    public void setApplyUserJourShowIncome(String applyUserJourShowIncome) {
//        this.applyUserJourShowIncome = applyUserJourShowIncome;
//    }
//
//    public String getApplyUserIsPrint() {
//        return applyUserIsPrint;
//    }
//
//    public void setApplyUserIsPrint(String applyUserIsPrint) {
//        this.applyUserIsPrint = applyUserIsPrint;
//    }
//
//    public Long getGhMonthIncome() {
//        return ghMonthIncome;
//    }
//
//    public void setGhMonthIncome(Long ghMonthIncome) {
//        this.ghMonthIncome = ghMonthIncome;
//    }
//
//    public Long getGhSettleInterest() {
//        return ghSettleInterest;
//    }
//
//    public void setGhSettleInterest(Long ghSettleInterest) {
//        this.ghSettleInterest = ghSettleInterest;
//    }
//
//    public Long getGhBalance() {
//        return ghBalance;
//    }
//
//    public void setGhBalance(Long ghBalance) {
//        this.ghBalance = ghBalance;
//    }
//
//    public String getGhJourShowIncome() {
//        return ghJourShowIncome;
//    }
//
//    public void setGhJourShowIncome(String ghJourShowIncome) {
//        this.ghJourShowIncome = ghJourShowIncome;
//    }
//
//    public String getGhIsPrint() {
//        return ghIsPrint;
//    }
//
//    public void setGhIsPrint(String ghIsPrint) {
//        this.ghIsPrint = ghIsPrint;
//    }
//
//    public Long getGuarantor1MonthIncome() {
//        return guarantor1MonthIncome;
//    }
//
//    public void setGuarantor1MonthIncome(Long guarantor1MonthIncome) {
//        this.guarantor1MonthIncome = guarantor1MonthIncome;
//    }
//
//    public Long getGuarantor1SettleInterest() {
//        return guarantor1SettleInterest;
//    }
//
//    public void setGuarantor1SettleInterest(Long guarantor1SettleInterest) {
//        this.guarantor1SettleInterest = guarantor1SettleInterest;
//    }
//
//    public Long getGuarantor1Balance() {
//        return guarantor1Balance;
//    }
//
//    public void setGuarantor1Balance(Long guarantor1Balance) {
//        this.guarantor1Balance = guarantor1Balance;
//    }
//
//    public String getGuarantor1JourShowIncome() {
//        return guarantor1JourShowIncome;
//    }
//
//    public void setGuarantor1JourShowIncome(String guarantor1JourShowIncome) {
//        this.guarantor1JourShowIncome = guarantor1JourShowIncome;
//    }
//
//    public String getGuarantor1IsPrint() {
//        return guarantor1IsPrint;
//    }
//
//    public void setGuarantor1IsPrint(String guarantor1IsPrint) {
//        this.guarantor1IsPrint = guarantor1IsPrint;
//    }
//
//    public Long getGuarantor2MonthIncome() {
//        return guarantor2MonthIncome;
//    }
//
//    public void setGuarantor2MonthIncome(Long guarantor2MonthIncome) {
//        this.guarantor2MonthIncome = guarantor2MonthIncome;
//    }
//
//    public Long getGuarantor2SettleInterest() {
//        return guarantor2SettleInterest;
//    }
//
//    public void setGuarantor2SettleInterest(Long guarantor2SettleInterest) {
//        this.guarantor2SettleInterest = guarantor2SettleInterest;
//    }
//
//    public Long getGuarantor2Balance() {
//        return guarantor2Balance;
//    }
//
//    public void setGuarantor2Balance(Long guarantor2Balance) {
//        this.guarantor2Balance = guarantor2Balance;
//    }
//
//    public String getGuarantor2JourShowIncome() {
//        return guarantor2JourShowIncome;
//    }
//
//    public void setGuarantor2JourShowIncome(String guarantor2JourShowIncome) {
//        this.guarantor2JourShowIncome = guarantor2JourShowIncome;
//    }
//
//    public String getGuarantor2IsPrint() {
//        return guarantor2IsPrint;
//    }
//
//    public void setGuarantor2IsPrint(String guarantor2IsPrint) {
//        this.guarantor2IsPrint = guarantor2IsPrint;
//    }
//
//    public void setHouseCustomerPic(String houseCustomerPic) {
//        this.houseCustomerPic = houseCustomerPic;
//    }
//
//    public String getHouseSaleCustomerPic() {
//        return houseSaleCustomerPic;
//    }
//
//    public void setHouseSaleCustomerPic(String houseSaleCustomerPic) {
//        this.houseSaleCustomerPic = houseSaleCustomerPic;
//    }
//
//    public String getCompanyNamePic() {
//        return companyNamePic;
//    }
//
//    public void setCompanyNamePic(String companyNamePic) {
//        this.companyNamePic = companyNamePic;
//    }
//
//    public String getCompanyPlacePic() {
//        return companyPlacePic;
//    }
//
//    public void setCompanyPlacePic(String companyPlacePic) {
//        this.companyPlacePic = companyPlacePic;
//    }
//
//    public String getCompanyWorkshopPic() {
//        return companyWorkshopPic;
//    }
//
//    public void setCompanyWorkshopPic(String companyWorkshopPic) {
//        this.companyWorkshopPic = companyWorkshopPic;
//    }
//
//    public String getCompanySaleCustomerPic() {
//        return companySaleCustomerPic;
//    }
//
//    public void setCompanySaleCustomerPic(String companySaleCustomerPic) {
//        this.companySaleCustomerPic = companySaleCustomerPic;
//    }
//
//    public String getSecondHgz() {
//        return secondHgz;
//    }
//
//    public void setSecondHgz(String secondHgz) {
//        this.secondHgz = secondHgz;
//    }
//
//    public String getSecondOdometer() {
//        return secondOdometer;
//    }
//
//    public void setSecondOdometer(String secondOdometer) {
//        this.secondOdometer = secondOdometer;
//    }
//
//    public String getSecondCarFrontPic() {
//        return secondCarFrontPic;
//    }
//
//    public void setSecondCarFrontPic(String secondCarFrontPic) {
//        this.secondCarFrontPic = secondCarFrontPic;
//    }
//
//    public String getSecondConsolePic() {
//        return secondConsolePic;
//    }
//
//    public void setSecondConsolePic(String secondConsolePic) {
//        this.secondConsolePic = secondConsolePic;
//    }
//
//    public String getSecond300Pdf() {
//        return second300Pdf;
//    }
//
//    public void setSecond300Pdf(String second300Pdf) {
//        this.second300Pdf = second300Pdf;
//    }
//
//    public String getSecondQxbPic() {
//        return secondQxbPic;
//    }
//
//    public void setSecondQxbPic(String secondQxbPic) {
//        this.secondQxbPic = secondQxbPic;
//    }
//
//    public String getSecondCarInPic() {
//        return secondCarInPic;
//    }
//
//    public void setSecondCarInPic(String secondCarInPic) {
//        this.secondCarInPic = secondCarInPic;
//    }
//
//    public String getSecondNumber() {
//        return secondNumber;
//    }
//
//    public void setSecondNumber(String secondNumber) {
//        this.secondNumber = secondNumber;
//    }
//
//    public String getOtherFilePdf() {
//        return otherFilePdf;
//    }
//
//    public void setOtherFilePdf(String otherFilePdf) {
//        this.otherFilePdf = otherFilePdf;
//    }
//
//    public String getOtherApplyNote() {
//        return otherApplyNote;
//    }
//
//    public void setOtherApplyNote(String otherApplyNote) {
//        this.otherApplyNote = otherApplyNote;
//    }
//
//    public Date getApplyDatetime() {
//        return applyDatetime;
//    }
//
//    public void setApplyDatetime(Date applyDatetime) {
//        this.applyDatetime = applyDatetime;
//    }
//
//    public String getCancelReason() {
//        return cancelReason;
//    }
//
//    public void setCancelReason(String cancelReason) {
//        this.cancelReason = cancelReason;
//    }
//
//    public Date getBankCommitDatetime() {
//        return bankCommitDatetime;
//    }
//
//    public void setBankCommitDatetime(Date bankCommitDatetime) {
//        this.bankCommitDatetime = bankCommitDatetime;
//    }
//
//    public String getBankCommitNote() {
//        return bankCommitNote;
//    }
//
//    public void setBankCommitNote(String bankCommitNote) {
//        this.bankCommitNote = bankCommitNote;
//    }
//
//    public Long getBankFkAmount() {
//        return bankFkAmount;
//    }
//
//    public void setBankFkAmount(Long bankFkAmount) {
//        this.bankFkAmount = bankFkAmount;
//    }
//
//    public Date getBankFkDatetime() {
//        return bankFkDatetime;
//    }
//
//    public void setBankFkDatetime(Date bankFkDatetime) {
//        this.bankFkDatetime = bankFkDatetime;
//    }
//
//    public String getBankReceiptCode() {
//        return bankReceiptCode;
//    }
//
//    public void setBankReceiptCode(String bankReceiptCode) {
//        this.bankReceiptCode = bankReceiptCode;
//    }
//
//    public String getBankReceiptNumber() {
//        return bankReceiptNumber;
//    }
//
//    public void setBankReceiptNumber(String bankReceiptNumber) {
//        this.bankReceiptNumber = bankReceiptNumber;
//    }
//
//    public String getBankReceiptPdf() {
//        return bankReceiptPdf;
//    }
//
//    public void setBankReceiptPdf(String bankReceiptPdf) {
//        this.bankReceiptPdf = bankReceiptPdf;
//    }
//
//    public String getBankReceiptNote() {
//        return bankReceiptNote;
//    }
//
//    public void setBankReceiptNote(String bankReceiptNote) {
//        this.bankReceiptNote = bankReceiptNote;
//    }
//
//    public Date getPledgeCommitDatetime() {
//        return pledgeCommitDatetime;
//    }
//
//    public void setPledgeCommitDatetime(Date pledgeCommitDatetime) {
//        this.pledgeCommitDatetime = pledgeCommitDatetime;
//    }
//
//    public String getPledgeCommitNote() {
//        return pledgeCommitNote;
//    }
//
//    public void setPledgeCommitNote(String pledgeCommitNote) {
//        this.pledgeCommitNote = pledgeCommitNote;
//    }
//
//    public String getPledgeContractCode() {
//        return pledgeContractCode;
//    }
//
//    public void setPledgeContractCode(String pledgeContractCode) {
//        this.pledgeContractCode = pledgeContractCode;
//    }
//
//    public String getInsuranceCompanyCode() {
//        return insuranceCompanyCode;
//    }
//
//    public void setInsuranceCompanyCode(String insuranceCompanyCode) {
//        this.insuranceCompanyCode = insuranceCompanyCode;
//    }
//
//    public String getCarColor() {
//        return carColor;
//    }
//
//    public void setCarColor(String carColor) {
//        this.carColor = carColor;
//    }
//
//    public String getCarBrand() {
//        return carBrand;
//    }
//
//    public void setCarBrand(String carBrand) {
//        this.carBrand = carBrand;
//    }
//
//    public String getFrameNo() {
//        return frameNo;
//    }
//
//    public void setFrameNo(String frameNo) {
//        this.frameNo = frameNo;
//    }
//
//    public String getEngineNo() {
//        return engineNo;
//    }
//
//    public void setEngineNo(String engineNo) {
//        this.engineNo = engineNo;
//    }
//
//    public String getCommerceInsurance() {
//        return commerceInsurance;
//    }
//
//    public void setCommerceInsurance(String commerceInsurance) {
//        this.commerceInsurance = commerceInsurance;
//    }
//
//    public Date getInsuranceEffectDatetime() {
//        return insuranceEffectDatetime;
//    }
//
//    public void setInsuranceEffectDatetime(Date insuranceEffectDatetime) {
//        this.insuranceEffectDatetime = insuranceEffectDatetime;
//    }
//
//    public String getInsuranceBank() {
//        return insuranceBank;
//    }
//
//    public void setInsuranceBank(String insuranceBank) {
//        this.insuranceBank = insuranceBank;
//    }
//
//    public String getGuaranteeContractCode() {
//        return guaranteeContractCode;
//    }
//
//    public void setGuaranteeContractCode(String guaranteeContractCode) {
//        this.guaranteeContractCode = guaranteeContractCode;
//    }
//
//    public String getRegCertificateCode() {
//        return regCertificateCode;
//    }
//
//    public void setRegCertificateCode(String regCertificateCode) {
//        this.regCertificateCode = regCertificateCode;
//    }
//
//    public String getOtherContact() {
//        return otherContact;
//    }
//
//    public void setOtherContact(String otherContact) {
//        this.otherContact = otherContact;
//    }
//
//    public String getContactMobile() {
//        return contactMobile;
//    }
//
//    public void setContactMobile(String contactMobile) {
//        this.contactMobile = contactMobile;
//    }
//
//    public String getGuarantorName() {
//        return guarantorName;
//    }
//
//    public void setGuarantorName(String guarantorName) {
//        this.guarantorName = guarantorName;
//    }
//
//    public String getGuarantorMobile() {
//        return guarantorMobile;
//    }
//
//    public void setGuarantorMobile(String guarantorMobile) {
//        this.guarantorMobile = guarantorMobile;
//    }
//
//    public String getBankCardNumber() {
//        return bankCardNumber;
//    }
//
//    public void setBankCardNumber(String bankCardNumber) {
//        this.bankCardNumber = bankCardNumber;
//    }
//
//    public Date getBillDatetime() {
//        return billDatetime;
//    }
//
//    public void setBillDatetime(Date billDatetime) {
//        this.billDatetime = billDatetime;
//    }
//
//    public Long getMonthAmount() {
//        return monthAmount;
//    }
//
//    public void setMonthAmount(Long monthAmount) {
//        this.monthAmount = monthAmount;
//    }
//
//    public String getIdNoPic() {
//        return idNoPic;
//    }
//
//    public void setIdNoPic(String idNoPic) {
//        this.idNoPic = idNoPic;
//    }
//
//    public String getFileList() {
//        return fileList;
//    }
//
//    public void setFileList(String fileList) {
//        this.fileList = fileList;
//    }
//
//    public String getIsComplete() {
//        return isComplete;
//    }
//
//    public void setIsComplete(String isComplete) {
//        this.isComplete = isComplete;
//    }
//
//    public String getStorePlace() {
//        return storePlace;
//    }
//
//    public void setStorePlace(String storePlace) {
//        this.storePlace = storePlace;
//    }
//
//    public String getFileRemark() {
//        return fileRemark;
//    }
//
//    public void setFileRemark(String fileRemark) {
//        this.fileRemark = fileRemark;
//    }
//
//    public String getOperator() {
//        return operator;
//    }
//
//    public void setOperator(String operator) {
//        this.operator = operator;
//    }
//
//    public Date getOperateDatetime() {
//        return operateDatetime;
//    }
//
//    public void setOperateDatetime(Date operateDatetime) {
//        this.operateDatetime = operateDatetime;
//    }
//
//    public String getOperateDepartment() {
//        return operateDepartment;
//    }
//
//    public void setOperateDepartment(String operateDepartment) {
//        this.operateDepartment = operateDepartment;
//    }
//
//    public String getIsRightInvoice() {
//        return isRightInvoice;
//    }
//
//    public void setIsRightInvoice(String isRightInvoice) {
//        this.isRightInvoice = isRightInvoice;
//    }
//
//    public double getOilSubsidyKil() {
//        return oilSubsidyKil;
//    }
//
//    public void setOilSubsidyKil(double oilSubsidyKil) {
//        this.oilSubsidyKil = oilSubsidyKil;
//    }
//
//    public Double getGpsDeduct() {
//        return gpsDeduct;
//    }
//
//    public void setGpsDeduct(Double gpsDeduct) {
//        this.gpsDeduct = gpsDeduct;
//    }
//
//    public Date getDeliveryDatetime() {
//        return deliveryDatetime;
//    }
//
//    public void setDeliveryDatetime(Date deliveryDatetime) {
//        this.deliveryDatetime = deliveryDatetime;
//    }
//
//    public Long getCurrentInvoicePrice() {
//        return currentInvoicePrice;
//    }
//
//    public void setCurrentInvoicePrice(Long currentInvoicePrice) {
//        this.currentInvoicePrice = currentInvoicePrice;
//    }
//
//    public String getInvoice() {
//        return invoice;
//    }
//
//    public void setInvoice(String invoice) {
//        this.invoice = invoice;
//    }
//
//    public String getCertification() {
//        return certification;
//    }
//
//    public void setCertification(String certification) {
//        this.certification = certification;
//    }
//
//    public Long getForceInsurance() {
//        return forceInsurance;
//    }
//
//    public void setForceInsurance(Long forceInsurance) {
//        this.forceInsurance = forceInsurance;
//    }
//
//    public Long getBusinessInsurance() {
//        return businessInsurance;
//    }
//
//    public void setBusinessInsurance(Long businessInsurance) {
//        this.businessInsurance = businessInsurance;
//    }
//
//    public String getMotorRegCertification() {
//        return motorRegCertification;
//    }
//
//    public void setMotorRegCertification(String motorRegCertification) {
//        this.motorRegCertification = motorRegCertification;
//    }
//
//    public String getPdPdf() {
//        return pdPdf;
//    }
//
//    public void setPdPdf(String pdPdf) {
//        this.pdPdf = pdPdf;
//    }
//
//    public String getFbhRemark() {
//        return fbhRemark;
//    }
//
//    public void setFbhRemark(String fbhRemark) {
//        this.fbhRemark = fbhRemark;
//    }
//
//    public Integer getFbhWarnDay() {
//        return fbhWarnDay;
//    }
//
//    public void setFbhWarnDay(Integer fbhWarnDay) {
//        this.fbhWarnDay = fbhWarnDay;
//    }
//
//    public Long getShouldBackAmount() {
//        return shouldBackAmount;
//    }
//
//    public void setShouldBackAmount(Long shouldBackAmount) {
//        this.shouldBackAmount = shouldBackAmount;
//    }
//
//    public String getFbhStatus() {
//        return fbhStatus;
//    }
//
//    public void setFbhStatus(String fbhStatus) {
//        this.fbhStatus = fbhStatus;
//    }
//
//    public Date getZfApplyDatetime() {
//        return zfApplyDatetime;
//    }
//
//    public void setZfApplyDatetime(Date zfApplyDatetime) {
//        this.zfApplyDatetime = zfApplyDatetime;
//    }
//
//    public String getZfReason() {
//        return zfReason;
//    }
//
//    public void setZfReason(String zfReason) {
//        this.zfReason = zfReason;
//    }
//
//    public Long getZfSkAmount() {
//        return zfSkAmount;
//    }
//
//    public void setZfSkAmount(Long zfSkAmount) {
//        this.zfSkAmount = zfSkAmount;
//    }
//
//    public String getZfSkBankcardCode() {
//        return zfSkBankcardCode;
//    }
//
//    public void setZfSkBankcardCode(String zfSkBankcardCode) {
//        this.zfSkBankcardCode = zfSkBankcardCode;
//    }
//
//    public Date getZfSkReceiptDatetime() {
//        return zfSkReceiptDatetime;
//    }
//
//    public void setZfSkReceiptDatetime(Date zfSkReceiptDatetime) {
//        this.zfSkReceiptDatetime = zfSkReceiptDatetime;
//    }
//
//    public String getZfFinanceRemark() {
//        return zfFinanceRemark;
//    }
//
//    public void setZfFinanceRemark(String zfFinanceRemark) {
//        this.zfFinanceRemark = zfFinanceRemark;
//    }
//
//    public String getCurNodeCode() {
//        return curNodeCode;
//    }
//
//    public void setCurNodeCode(String curNodeCode) {
//        this.curNodeCode = curNodeCode;
//    }
//
//    public Date getApplyDatetimeStart() {
//        return applyDatetimeStart;
//    }
//
//    public void setApplyDatetimeStart(Date applyDatetimeStart) {
//        this.applyDatetimeStart = applyDatetimeStart;
//    }
//
//    public Date getApplyDatetimeEnd() {
//        return applyDatetimeEnd;
//    }
//
//    public void setApplyDatetimeEnd(Date applyDatetimeEnd) {
//        this.applyDatetimeEnd = applyDatetimeEnd;
//    }
//
//    public String getCreditCode() {
//        return creditCode;
//    }
//
//    public void setCreditCode(String creditCode) {
//        this.creditCode = creditCode;
//    }
//
//    public int getInsuranceRemindCount() {
//        return insuranceRemindCount;
//    }
//
//    public void setInsuranceRemindCount(int insuranceRemindCount) {
//        this.insuranceRemindCount = insuranceRemindCount;
//    }
//
//    public Date getInsuranceApplyDatetime() {
//        return insuranceApplyDatetime;
//    }
//
//    public void setInsuranceApplyDatetime(Date insuranceApplyDatetime) {
//        this.insuranceApplyDatetime = insuranceApplyDatetime;
//    }
//
//    public Date getInsuranceEndDatetime() {
//        return insuranceEndDatetime;
//    }
//
//    public void setInsuranceEndDatetime(Date insuranceEndDatetime) {
//        this.insuranceEndDatetime = insuranceEndDatetime;
//    }
//
//    public String getInsuranceForcePdf() {
//        return insuranceForcePdf;
//    }
//
//    public void setInsuranceForcePdf(String insuranceForcePdf) {
//        this.insuranceForcePdf = insuranceForcePdf;
//    }
//
//    public String getInsuranceBusinessPdf() {
//        return insuranceBusinessPdf;
//    }
//
//    public void setInsuranceBusinessPdf(String insuranceBusinessPdf) {
//        this.insuranceBusinessPdf = insuranceBusinessPdf;
//    }
//
//    public String getInsuranceNote() {
//        return insuranceNote;
//    }
//
//    public void setInsuranceNote(String insuranceNote) {
//        this.insuranceNote = insuranceNote;
//    }
//
//    public String getFeeWay() {
//        return serviceChargeWay;
//    }
//
//    public void setFeeWay(String serviceChargeWay) {
//        this.serviceChargeWay = serviceChargeWay;
//    }
//
//    public Long getServiceCharge() {
//        return serviceCharge;
//    }
//
//    public void setServiceCharge(Long serviceCharge) {
//        this.serviceCharge = serviceCharge;
//    }
//
//}
