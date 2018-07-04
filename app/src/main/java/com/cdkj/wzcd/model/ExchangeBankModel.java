package com.cdkj.wzcd.model;

/**
 * Created by cdkj on 2018/5/31.
 */

public class ExchangeBankModel {


    /**
     * code : BS201806190609220389033
     * bankCode : BA201806190559381823713
     * bankType : BOC
     * abbrName : 中国银行简称1
     * fullName : 中国银行支行全称
     * openBank : 中国银行开户行
     * address : 中国银行银行地址
     * phoneNumber : 13222222222
     * postCode : 000000
     * bankClient : 中国银行银行委托人
     * autherName : 中国银行授权人姓名
     * autherPhoneNumber : 15522222222
     * autherIdNo : 150404199806080808
     * autherAddress : 中国银行授权人地址
     * creditCardType : 1
     * creditCardName : 中国银行用卡名称
     * belongArea : 中国银行所属地区
     * updater : U201806190612230644076
     * updateDatetime : Jun 19, 2018 6:09:22 AM
     * remark : 备注
     * bankName : 中国银行
     * bank : {"code":"BA201806190559381823713","bankCode":"BOC","bankName":"中国银行","rate12":0,"rate18":0,"rate24":0,"rate36":0,"updater":"U201806190612230644076","updateDatetime":"Jun 19, 2018 6:07:33 AM","remark":"备注"}
     * updaterName : 悟空
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
    private String bankName;
    private BankBean bank;
    private String updaterName;

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public BankBean getBank() {
        return bank;
    }

    public void setBank(BankBean bank) {
        this.bank = bank;
    }

    public String getUpdaterName() {
        return updaterName;
    }

    public void setUpdaterName(String updaterName) {
        this.updaterName = updaterName;
    }

    public static class BankBean {
        /**
         * code : BA201806190559381823713
         * bankCode : BOC
         * bankName : 中国银行
         * rate12 : 0
         * rate18 : 0
         * rate24 : 0
         * rate36 : 0
         * updater : U201806190612230644076
         * updateDatetime : Jun 19, 2018 6:07:33 AM
         * remark : 备注
         */

        private String code;
        private String bankCode;
        private String bankName;
        private int rate12;
        private int rate18;
        private int rate24;
        private int rate36;
        private String updater;
        private String updateDatetime;
        private String remark;

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
    }
}
