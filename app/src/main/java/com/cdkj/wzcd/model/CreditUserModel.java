package com.cdkj.wzcd.model;

import java.io.Serializable;

/**
 * Created by cdkj on 2018/5/30.
 */

public class CreditUserModel implements Serializable {

    private boolean isChoice;//是否被选中
    private CreditResult bankCreditResult;
    private String birthAddress;

    public CreditResult getBankCreditResult() {
        return bankCreditResult;
    }

    public void setBankCreditResult(CreditResult bankCreditResult) {
        this.bankCreditResult = bankCreditResult;
    }

    public String getBirthAddress() {
        return birthAddress;
    }

    public void setBirthAddress(String birthAddress) {
        this.birthAddress = birthAddress;
    }

    private String userName;
    private String relation;
    private String loanRole;
    private String mobile;
    private String idNo;
    private String idNoFront;
    private String idNoReverse;
    private String authPdf;
    private String interviewPic;
    private long dkdyCount;
    private long dkdyAmount;
    private long dkdy2YearOverTimes;
    private long dkdyMaxOverAmount;
    private long dkdyCurrentOverAmount;
    private long dkdy6MonthAvgAmount;
    private long hkxyUnsettleCount;
    private long hkxyUnsettleAmount;
    private long hkxy2YearOverTimes;
    private long hkxyMonthMaxOverAmount;
    private long hkxyCurrentOverAmount;
    private long hkxy6MonthAvgAmount;
    private long xykCount;
    private long xykCreditAmount;
    private long xyk6MonthUseAmount;
    private long xyk2YearOverTimes;
    private long xykMonthMaxOverAmount;
    private long xykCurrentOverAmount;
    private long outGuaranteesCount;
    private long outGuaranteesAmount;
    private String outGuaranteesRemark;
    private String isFirstAudit;

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    private String code;
    private String courtNetworkMesg;//自己加的  法院网录入结果

    public String getCourtNetworkMesg() {
        return courtNetworkMesg;
    }

    public void setCourtNetworkMesg(String courtNetworkMesg) {
        this.courtNetworkMesg = courtNetworkMesg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
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

    public String getAuthPdf() {
        return authPdf;
    }

    public void setAuthPdf(String authPdf) {
        this.authPdf = authPdf;
    }

    public String getInterviewPic() {
        return interviewPic;
    }

    public void setInterviewPic(String interviewPic) {
        this.interviewPic = interviewPic;
    }

    public String getDkdyCount() {
        return dkdyCount+"";
    }

    public void setDkdyCount(long dkdyCount) {
        this.dkdyCount = dkdyCount;
    }

    public String getDkdyAmount() {
        return dkdyAmount+"";
    }

    public void setDkdyAmount(long dkdyAmount) {
        this.dkdyAmount = dkdyAmount;
    }

    public String getDkdy2YearOverTimes() {
        return dkdy2YearOverTimes+"";
    }

    public void setDkdy2YearOverTimes(long dkdy2YearOverTimes) {
        this.dkdy2YearOverTimes = dkdy2YearOverTimes;
    }

    public String getDkdyMaxOverAmount() {
        return dkdyMaxOverAmount+"";
    }

    public void setDkdyMaxOverAmount(long dkdyMaxOverAmount) {
        this.dkdyMaxOverAmount = dkdyMaxOverAmount;
    }

    public String getDkdyCurrentOverAmount() {
        return dkdyCurrentOverAmount+"";
    }

    public void setDkdyCurrentOverAmount(long dkdyCurrentOverAmount) {
        this.dkdyCurrentOverAmount = dkdyCurrentOverAmount;
    }

    public String getDkdy6MonthAvgAmount() {
        return dkdy6MonthAvgAmount+"";
    }

    public void setDkdy6MonthAvgAmount(long dkdy6MonthAvgAmount) {
        this.dkdy6MonthAvgAmount = dkdy6MonthAvgAmount;
    }

    public String getHkxyUnsettleCount() {
        return hkxyUnsettleCount+"";
    }

    public void setHkxyUnsettleCount(long hkxyUnsettleCount) {
        this.hkxyUnsettleCount = hkxyUnsettleCount;
    }

    public String getHkxyUnsettleAmount() {
        return hkxyUnsettleAmount+"";
    }

    public void setHkxyUnsettleAmount(long hkxyUnsettleAmount) {
        this.hkxyUnsettleAmount = hkxyUnsettleAmount;
    }

    public String getHkxy2YearOverTimes() {
        return hkxy2YearOverTimes+"";
    }

    public void setHkxy2YearOverTimes(long hkxy2YearOverTimes) {
        this.hkxy2YearOverTimes = hkxy2YearOverTimes;
    }

    public String getHkxyMonthMaxOverAmount() {
        return hkxyMonthMaxOverAmount+"";
    }

    public void setHkxyMonthMaxOverAmount(long hkxyMonthMaxOverAmount) {
        this.hkxyMonthMaxOverAmount = hkxyMonthMaxOverAmount;
    }

    public String getHkxyCurrentOverAmount() {
        return hkxyCurrentOverAmount+"";
    }

    public void setHkxyCurrentOverAmount(long hkxyCurrentOverAmount) {
        this.hkxyCurrentOverAmount = hkxyCurrentOverAmount;
    }

    public String getHkxy6MonthAvgAmount() {
        return hkxy6MonthAvgAmount+"";
    }

    public void setHkxy6MonthAvgAmount(long hkxy6MonthAvgAmount) {
        this.hkxy6MonthAvgAmount = hkxy6MonthAvgAmount;
    }

    public String getXykCount() {
        return xykCount+"";
    }

    public void setXykCount(long xykCount) {
        this.xykCount = xykCount;
    }

    public String getXykCreditAmount() {
        return xykCreditAmount+"";
    }

    public void setXykCreditAmount(long xykCreditAmount) {
        this.xykCreditAmount = xykCreditAmount;
    }

    public String getXyk6MonthUseAmount() {
        return xyk6MonthUseAmount+"";
    }

    public void setXyk6MonthUseAmount(long xyk6MonthUseAmount) {
        this.xyk6MonthUseAmount = xyk6MonthUseAmount;
    }

    public String getXyk2YearOverTimes() {
        return xyk2YearOverTimes+"";
    }

    public void setXyk2YearOverTimes(long xyk2YearOverTimes) {
        this.xyk2YearOverTimes = xyk2YearOverTimes;
    }

    public String getXykMonthMaxOverAmount() {
        return xykMonthMaxOverAmount+"";
    }

    public void setXykMonthMaxOverAmount(long xykMonthMaxOverAmount) {
        this.xykMonthMaxOverAmount = xykMonthMaxOverAmount;
    }

    public String getXykCurrentOverAmount() {
        return xykCurrentOverAmount+"";
    }

    public void setXykCurrentOverAmount(long xykCurrentOverAmount) {
        this.xykCurrentOverAmount = xykCurrentOverAmount;
    }

    public String getOutGuaranteesCount() {
        return outGuaranteesCount+"";
    }

    public void setOutGuaranteesCount(long outGuaranteesCount) {
        this.outGuaranteesCount = outGuaranteesCount;
    }

    public String getOutGuaranteesAmount() {
        return outGuaranteesAmount+"";
    }

    public void setOutGuaranteesAmount(long outGuaranteesAmount) {
        this.outGuaranteesAmount = outGuaranteesAmount;
    }

    public String getOutGuaranteesRemark() {
        return outGuaranteesRemark;
    }

    public void setOutGuaranteesRemark(String outGuaranteesRemark) {
        this.outGuaranteesRemark = outGuaranteesRemark;
    }

    public String getIsFirstAudit() {
        return isFirstAudit;
    }

    public void setIsFirstAudit(String isFirstAudit) {
        this.isFirstAudit = isFirstAudit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
