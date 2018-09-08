package com.cdkj.wzcd.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author cdkj
 * @updateDts 2018/5/30
 */

public class GpsApplyModel {


    /**
     * code : GA201806042003056459347
     * type : 2
     * applyUser : U201806031626324415728
     * applyDatetime : Jun 4, 2018 8:03:05 PM
     * applyReason : 领两个
     * applyCount : 2
     * status : 0
     * applyUserName : 雷黔
     */

    private String code;
    private String type;
    private String applyUser;
    private String applyDatetime;
    private String applyReason;
    private int applyCount;
    private String status;
    private String applyUserName;
    private String companyName;
    /**
     * applyCount : 1.0
     * approveNote : 审核意见
     * approveUser : U201808181253226614914
     * companyCode : DP201800000000000000001
     * gpsList : [{"applyStatus":"0","code":"G201808201601309081257","companyApplyCode":"GA201808222231526947462","companyApplyDatetime":"Aug 22, 2018 10:31:52 PM","companyApplyStatus":"1","companyCode":"DP201800000000000000001","gpsDevNo":"008","gpsType":"1","supplierCode":"GS201808201519037695117","useStatus":"0"},{"applyStatus":"0","code":"G201808212345317657223","companyApplyCode":"GA201808222231526947462","companyApplyDatetime":"Aug 22, 2018 10:31:52 PM","companyApplyStatus":"1","companyCode":"DP201800000000000000001","gpsDevNo":"GPS120132453132","gpsType":"1","supplierCode":"GS201808212313369787456","useStatus":"0"}]
     * receiveDatetime : Aug 22, 2018 10:33:26 PM
     * sendDatetime : Aug 22, 2018 10:32:47 PM
     */

    @SerializedName("applyCount")
    private double applyCountX;
    private String approveNote;
    private String approveUser;
    private String companyCode;
    private String receiveDatetime;
    private String sendDatetime;
    private List<GpsListBean> gpsList;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(String applyUser) {
        this.applyUser = applyUser;
    }

    public String getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(String applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public double getApplyCountX() {
        return applyCountX;
    }

    public void setApplyCountX(double applyCountX) {
        this.applyCountX = applyCountX;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getReceiveDatetime() {
        return receiveDatetime;
    }

    public void setReceiveDatetime(String receiveDatetime) {
        this.receiveDatetime = receiveDatetime;
    }

    public String getSendDatetime() {
        return sendDatetime;
    }

    public void setSendDatetime(String sendDatetime) {
        this.sendDatetime = sendDatetime;
    }

    public List<GpsListBean> getGpsList() {
        return gpsList;
    }

    public void setGpsList(List<GpsListBean> gpsList) {
        this.gpsList = gpsList;
    }

    public static class GpsListBean {
        /**
         * applyStatus : 0
         * code : G201808201601309081257
         * companyApplyCode : GA201808222231526947462
         * companyApplyDatetime : Aug 22, 2018 10:31:52 PM
         * companyApplyStatus : 1
         * companyCode : DP201800000000000000001
         * gpsDevNo : 008
         * gpsType : 1
         * supplierCode : GS201808201519037695117
         * useStatus : 0
         */

        private String applyStatus;
        @SerializedName("code")
        private String codeX;
        private String companyApplyCode;
        private String companyApplyDatetime;
        private String companyApplyStatus;
        private String companyCode;
        private String gpsDevNo;
        private String gpsType;
        private String supplierCode;
        private String useStatus;

        public String getApplyStatus() {
            return applyStatus;
        }

        public void setApplyStatus(String applyStatus) {
            this.applyStatus = applyStatus;
        }

        public String getCodeX() {
            return codeX;
        }

        public void setCodeX(String codeX) {
            this.codeX = codeX;
        }

        public String getCompanyApplyCode() {
            return companyApplyCode;
        }

        public void setCompanyApplyCode(String companyApplyCode) {
            this.companyApplyCode = companyApplyCode;
        }

        public String getCompanyApplyDatetime() {
            return companyApplyDatetime;
        }

        public void setCompanyApplyDatetime(String companyApplyDatetime) {
            this.companyApplyDatetime = companyApplyDatetime;
        }

        public String getCompanyApplyStatus() {
            return companyApplyStatus;
        }

        public void setCompanyApplyStatus(String companyApplyStatus) {
            this.companyApplyStatus = companyApplyStatus;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getGpsDevNo() {
            return gpsDevNo;
        }

        public void setGpsDevNo(String gpsDevNo) {
            this.gpsDevNo = gpsDevNo;
        }

        public String getGpsType() {
            return gpsType;
        }

        public void setGpsType(String gpsType) {
            this.gpsType = gpsType;
        }

        public String getSupplierCode() {
            return supplierCode;
        }

        public void setSupplierCode(String supplierCode) {
            this.supplierCode = supplierCode;
        }

        public String getUseStatus() {
            return useStatus;
        }

        public void setUseStatus(String useStatus) {
            this.useStatus = useStatus;
        }
    }
}
