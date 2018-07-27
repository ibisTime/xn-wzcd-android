package com.cdkj.wzcd.model;

import java.util.List;

/**
 * Created by cdkj on 2018/6/5.
 */

public class DataTransferModel  {


    /**
     * code : L201807162230294905798
     * type : 1
     * bizCode : HX53180716000007
     * userId : U201807081543249555383
     * userName : 呜呜呜
     * fromNodeCode : 007_02
     * toNodeCode : 007_03
     * sendType : 2
     * logisticsCompany : STO
     * logisticsCode : kiug
     * sendDatetime : Jul 16, 2018 10:31:47 PM
     * sendNote :
     * receiptDatetime : Jul 17, 2018 12:11:08 AM
     * status : 3
     * customerName : 13ee
     * supplementReasonList : [{"id":16,"logisticsCode":"L201807162230294905798","reason":"Ojbk"}]
     */

    private String code;
    private String type;
    private String bizCode;
    private String userId;
    private String userName;
    private String fromNodeCode;
    private String toNodeCode;
    private String sendType;
    private String logisticsCompany;
    private String logisticsCode;
    private String sendDatetime;
    private String sendNote;
    private String receiptDatetime;
    private String status;
    private String customerName;
    private String supplementReason;
    private List<SupplementReasonListBean> supplementReasonList;

    public String getSupplementReason() {
        return supplementReason;
    }

    public void setSupplementReason(String supplementReason) {
        this.supplementReason = supplementReason;
    }

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

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFromNodeCode() {
        return fromNodeCode;
    }

    public void setFromNodeCode(String fromNodeCode) {
        this.fromNodeCode = fromNodeCode;
    }

    public String getToNodeCode() {
        return toNodeCode;
    }

    public void setToNodeCode(String toNodeCode) {
        this.toNodeCode = toNodeCode;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(String sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

    public String getSendNote() {
        return sendNote;
    }

    public void setSendNote(String sendNote) {
        this.sendNote = sendNote;
    }

    public String getReceiptDatetime() {
        return receiptDatetime;
    }

    public void setReceiptDatetime(String receiptDatetime) {
        this.receiptDatetime = receiptDatetime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<SupplementReasonListBean> getSupplementReasonList() {
        return supplementReasonList;
    }

    public void setSupplementReasonList(List<SupplementReasonListBean> supplementReasonList) {
        this.supplementReasonList = supplementReasonList;
    }

    public static class SupplementReasonListBean {
        /**
         * id : 16
         * logisticsCode : L201807162230294905798
         * reason : Ojbk
         */

        private int id;
        private String logisticsCode;
        private String reason;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogisticsCode() {
            return logisticsCode;
        }

        public void setLogisticsCode(String logisticsCode) {
            this.logisticsCode = logisticsCode;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
