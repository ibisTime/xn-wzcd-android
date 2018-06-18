package com.cdkj.wzcd.model;

/**
 * Created by cdkj on 2018/6/5.
 */

public class DataTransferModel  {


    /**
     * code : L201806051607028009280
     * type : 1
     * bizCode : BO201806050154003632973
     * userId : U201806031626324415728
     * fromNodeCode : 002_11
     * toNodeCode : 002_13
     * refFileList : 购车合同
     * sendFileList : 合同,材料
     * sendType : 1
     * sendNote : 备注
     * status : 1
     * userName : 雷黔
     */

    private String code;
    private String type;
    private String bizCode;
    private String userId;
    private String fromNodeCode;
    private String toNodeCode;
    private String refFileList;
    private String sendFileList;
    private String sendType;
    private String sendNote;
    private String status;
    private String userName;
    private String sendDatetime;
    private String logisticsCode;
    private String logisticsCompany;

    public String getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(String sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
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

    public String getRefFileList() {
        return refFileList;
    }

    public void setRefFileList(String refFileList) {
        this.refFileList = refFileList;
    }

    public String getSendFileList() {
        return sendFileList;
    }

    public void setSendFileList(String sendFileList) {
        this.sendFileList = sendFileList;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public String getSendNote() {
        return sendNote;
    }

    public void setSendNote(String sendNote) {
        this.sendNote = sendNote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
