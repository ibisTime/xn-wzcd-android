package com.cdkj.wzcd.model;

/**
 * Created by cdkj on 2018/6/7.
 */

public class LoanProductModel {


    /**
     * code : LP201806060407243426367
     * type : 1
     * name : 二手车贷款产品
     * loanBank : BA201806060405534759680
     * wanFactor : 200000
     * yearRate : 0.01
     * gpsFee : 1000000
     * authRate : 0.01
     * backRate : 0.01
     * preRate : 0.01
     * status : 2
     * updater : hss
     * updateDatetime : Jun 6, 2018 4:07:30 AM
     * loanBankName : 招商银行
     */

    private String code;
    private String type;
    private String name;
    private String loanBank;
    private int wanFactor;
    private double yearRate;
    private int gpsFee;
    private double authRate;
    private double backRate;
    private double preRate;
    private String status;
    private String updater;
    private String updateDatetime;
    private String loanBankName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoanBank() {
        return loanBank;
    }

    public void setLoanBank(String loanBank) {
        this.loanBank = loanBank;
    }

    public int getWanFactor() {
        return wanFactor;
    }

    public void setWanFactor(int wanFactor) {
        this.wanFactor = wanFactor;
    }

    public double getYearRate() {
        return yearRate;
    }

    public void setYearRate(double yearRate) {
        this.yearRate = yearRate;
    }

    public int getGpsFee() {
        return gpsFee;
    }

    public void setGpsFee(int gpsFee) {
        this.gpsFee = gpsFee;
    }

    public double getAuthRate() {
        return authRate;
    }

    public void setAuthRate(double authRate) {
        this.authRate = authRate;
    }

    public double getBackRate() {
        return backRate;
    }

    public void setBackRate(double backRate) {
        this.backRate = backRate;
    }

    public double getPreRate() {
        return preRate;
    }

    public void setPreRate(double preRate) {
        this.preRate = preRate;
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

    public String getLoanBankName() {
        return loanBankName;
    }

    public void setLoanBankName(String loanBankName) {
        this.loanBankName = loanBankName;
    }
}
