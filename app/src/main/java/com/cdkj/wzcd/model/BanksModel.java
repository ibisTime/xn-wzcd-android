package com.cdkj.wzcd.model;

import java.util.List;

/**
 * @author qi
 * @updateDts 2018/8/6
 */

public class BanksModel  {


    /**
     * bankCode : BOC
     * bankName : 中国银行
     * bankRateList : [{"bankCode":"BA201806291550584978017","id":1,"period":24,"rate":0.33,"remark":""},{"bankCode":"BA201806291550584978017","id":2,"period":12,"rate":0.05,"remark":""},{"bankCode":"BA201806291550584978017","id":3,"period":36,"rate":0.5,"remark":""},{"bankCode":"BA201807081621529236963","id":4,"period":12,"rate":0.2,"remark":"备注"},{"bankCode":"BA201807081621529236963","id":5,"period":24,"rate":0.3,"remark":""},{"bankCode":"BA201807081621529236963","id":6,"period":36,"rate":0.4,"remark":""},{"bankCode":"BA201806060406039453855","id":7,"period":12,"rate":0.2,"remark":"备注"},{"bankCode":"BA201806060406039453855","id":8,"period":24,"rate":0.2,"remark":"备注"},{"bankCode":"BA201806060406039453855","id":9,"period":36,"rate":0.2,"remark":"备注"},{"bankCode":"BA201806060405534759680","id":10,"period":12,"rate":0.1,"remark":""},{"bankCode":"BA201806060405534759680","id":11,"period":24,"rate":0.2,"remark":""}]
     * code : BA201806060405534759680
     * rate12 : 0.0
     * rate18 : 0.0
     * rate24 : 0.0
     * rate36 : 0.0
     * remark :
     * updateDatetime : Jul 21, 2018 5:17:34 PM
     * updater : U201807171419188821682
     */

    private String bankCode;
    private String bankName;
    private String code;
    private double rate12;
    private double rate18;
    private double rate24;
    private double rate36;
    private String remark;
    private String updateDatetime;
    private String updater;
    private List<BankRateListBean> bankRateList;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getRate12() {
        return rate12;
    }

    public void setRate12(double rate12) {
        this.rate12 = rate12;
    }

    public double getRate18() {
        return rate18;
    }

    public void setRate18(double rate18) {
        this.rate18 = rate18;
    }

    public double getRate24() {
        return rate24;
    }

    public void setRate24(double rate24) {
        this.rate24 = rate24;
    }

    public double getRate36() {
        return rate36;
    }

    public void setRate36(double rate36) {
        this.rate36 = rate36;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public List<BankRateListBean> getBankRateList() {
        return bankRateList;
    }

    public void setBankRateList(List<BankRateListBean> bankRateList) {
        this.bankRateList = bankRateList;
    }

    public static class BankRateListBean {
        /**
         * bankCode : BA201806291550584978017
         * id : 1.0
         * period : 24.0
         * rate : 0.33
         * remark :
         */

        private String bankCode;
        private double id;
        private double period;
        private double rate;
        private String remark;

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public double getId() {
            return id;
        }

        public void setId(double id) {
            this.id = id;
        }

        public double getPeriod() {
            return period;
        }

        public void setPeriod(double period) {
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
