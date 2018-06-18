package com.cdkj.wzcd.model;

/**
 * Created by cdkj on 2018/5/31.
 */

public class ExchangeBankModel {


    /**
     * code : BA201805301743536885439
     * bankCode : ABCD
     * bankName : 中国农业银行
     * subbranch : 余杭支行
     * rate12 : 0
     * rate18 : 0
     * rate24 : 0
     * rate36 : 0
     * status : 1
     * updater : admin
     * updateDatetime : May 30, 2018 5:43:53 PM
     */

    private String code;
    private String bankCode;
    private String bankName;
    private String subbranch;
    private int rate12;
    private int rate18;
    private int rate24;
    private int rate36;
    private String status;
    private String updater;
    private String updateDatetime;

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

    public String getSubbranch() {
        return subbranch;
    }

    public void setSubbranch(String subbranch) {
        this.subbranch = subbranch;
    }

    public int getRate12() {
        return rate12;
    }

    public void setRate12(int rate12) {
        this.rate12 = rate12;
    }

    public int getRate18() {
        return rate18;
    }

    public void setRate18(int rate18) {
        this.rate18 = rate18;
    }

    public int getRate24() {
        return rate24;
    }

    public void setRate24(int rate24) {
        this.rate24 = rate24;
    }

    public int getRate36() {
        return rate36;
    }

    public void setRate36(int rate36) {
        this.rate36 = rate36;
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
}
