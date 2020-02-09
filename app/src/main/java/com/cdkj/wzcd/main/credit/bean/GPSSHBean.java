package com.cdkj.wzcd.main.credit.bean;

import java.util.List;

/**
 * @updateDts 2020/2/4
 */
public class GPSSHBean {

    /**
     * code : GA202002041413174855052
     * companyCode : DP201800000000000000001
     * applyUser : U202001061440411518279
     * applyDatetime : Feb 4, 2020 2:13:17 PM
     * applyReason : 原因
     * applyCount : 1
     * teamCode : BT201909171301034281856
     * status : 1
     * operator : U202001061440411518279
     * updateDatetime : Feb 4, 2020 4:19:14 PM
     * remark : 111111
     * applyUserName : 齐胜涛
     * companyName : 温州浩源控股有限公司
     * teamName : A团队
     * roleName : 超级管理员
     * gpsList : [{"code":"G202001074131274435","gpsDevNo":"0671411","companyCode":"DP201800000000000000001","applyUser":"U202001061440411518279","applyDatetime":"Feb 4, 2020 2:13:17 PM","useStatus":"1"},{"code":"G202001074390936540","gpsDevNo":"0713114","companyCode":"DP201800000000000000001","applyUser":"U202001061440411518279","applyDatetime":"Feb 4, 2020 2:13:17 PM","useStatus":"1"},{"code":"G202001075673713418","gpsDevNo":"1416113","companyCode":"DP201800000000000000001","applyUser":"U202001061440411518279","applyDatetime":"Feb 4, 2020 2:13:17 PM","useStatus":"1"}]
     */

    private String code;
    private String companyCode;
    private String applyUser;
    private String applyDatetime;
    private String applyReason;
    private int applyCount;
    private String teamCode;
    private String status;
    private String operator;
    private String updateDatetime;
    private String remark;
    private String applyUserName;
    private String companyName;
    private String teamName;
    private String roleName;
    private List<GpsListBean> gpsList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
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

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public String getApplyUserName() {
        return applyUserName;
    }

    public void setApplyUserName(String applyUserName) {
        this.applyUserName = applyUserName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<GpsListBean> getGpsList() {
        return gpsList;
    }

    public void setGpsList(List<GpsListBean> gpsList) {
        this.gpsList = gpsList;
    }

    public static class GpsListBean {
        /**
         * code : G202001074131274435
         * gpsDevNo : 0671411
         * companyCode : DP201800000000000000001
         * applyUser : U202001061440411518279
         * applyDatetime : Feb 4, 2020 2:13:17 PM
         * useStatus : 1
         */

        private String code;
        private String gpsDevNo;
        private String companyCode;
        private String applyUser;
        private String applyDatetime;
        private String useStatus;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getGpsDevNo() {
            return gpsDevNo;
        }

        public void setGpsDevNo(String gpsDevNo) {
            this.gpsDevNo = gpsDevNo;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
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

        public String getUseStatus() {
            return useStatus;
        }

        public void setUseStatus(String useStatus) {
            this.useStatus = useStatus;
        }
    }
}
