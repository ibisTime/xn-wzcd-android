package com.cdkj.wzcd.module.work.join_approval.model;

/**
* 返点明细表
* @author: jiafr 
* @since: 2018-06-16 14:16:05
* @history:
*/
public class RepointDetail {

    // 编号
    private String code;

    // 预算单编号
    private String budgetCode;

    // 返点编号
    private String repointCode;

    // 客户姓名
    private String userName;

    // 身份证号
    private String idNo;

    // 汽车经销商编号
    private String carDealerCode;

    // 业务公司编号
    private String companyCode;

    // 车辆型号
    private String carType;

    // 贷款金额
    private Long loanAmount;

    // 银行实际利率
    private Double bankRate;

    // 基准利率
    private Double benchmarkRate;

    // 服务费
    private Long fee;

    // 用款用途（1应退按揭款2协议内返点3协议外返点）
    private String useMoneyPurpose;

    // 返点金额(返点金额或应退按揭款金额)
    private Long repointAmount;

    // 账号编号（公司或车行的收款账号编号）
    private String accountCode;

    // 收款账号（用于客户不垫资，手动输入的应退按揭款的收款账号 ）
    private String mortgageAccountNo;

    // 节点(0待制单1已制单待打款2已打款)
    private String curNodeCode;

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getMortgageAccountNo() {
        return mortgageAccountNo;
    }

    public void setMortgageAccountNo(String mortgageAccountNo) {
        this.mortgageAccountNo = mortgageAccountNo;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setBudgetCode(String budgetCode) {
        this.budgetCode = budgetCode;
    }

    public String getBudgetCode() {
        return budgetCode;
    }

    public void setRepointCode(String repointCode) {
        this.repointCode = repointCode;
    }

    public String getRepointCode() {
        return repointCode;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setCarDealerCode(String carDealerCode) {
        this.carDealerCode = carDealerCode;
    }

    public String getCarDealerCode() {
        return carDealerCode;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getCarType() {
        return carType;
    }

    public Long getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Long loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Double getBankRate() {
        return bankRate;
    }

    public void setBankRate(Double bankRate) {
        this.bankRate = bankRate;
    }

    public Double getBenchmarkRate() {
        return benchmarkRate;
    }

    public void setBenchmarkRate(Double benchmarkRate) {
        this.benchmarkRate = benchmarkRate;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getUseMoneyPurpose() {
        return useMoneyPurpose;
    }

    public void setUseMoneyPurpose(String useMoneyPurpose) {
        this.useMoneyPurpose = useMoneyPurpose;
    }

    public Long getRepointAmount() {
        return repointAmount;
    }

    public void setRepointAmount(Long repointAmount) {
        this.repointAmount = repointAmount;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setCurNodeCode(String curNodeCode) {
        this.curNodeCode = curNodeCode;
    }

    public String getCurNodeCode() {
        return curNodeCode;
    }

}
