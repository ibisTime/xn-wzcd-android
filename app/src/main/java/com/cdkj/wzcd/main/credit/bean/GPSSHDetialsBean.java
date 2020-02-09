package com.cdkj.wzcd.main.credit.bean;

import java.util.List;

/**
 * @updateDts 2020/2/4
 */
public class GPSSHDetialsBean {

    /**
     * code : GA202002041108202081257
     * companyCode : DP201800000000000000001
     * applyUser : U202001131555159925212
     * applyDatetime : Feb 4, 2020 11:08:20 AM
     * applyReason : 原因
     * applyCount : 1
     * teamCode : BT201909171301034281856
     * saleUserId : U202001131555159925212
     * status : 0
     * applyUserName : 齐胜涛2
     * companyName : 温州浩源控股有限公司
     * teamName : A团队
     * saleUserName : 齐胜涛2
     * roleName : 业务员
     * gpsList : []
     */

    private String code;
    private String companyCode;
    private String applyUser;
    private String applyDatetime;
    private String applyReason;
    private int applyCount;
    private String teamCode;
    private String saleUserId;
    private String status;
    private String applyUserName;
    private String companyName;
    private String teamName;
    private String saleUserName;
    private String roleName;
    private List<?> gpsList;

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

    public String getSaleUserId() {
        return saleUserId;
    }

    public void setSaleUserId(String saleUserId) {
        this.saleUserId = saleUserId;
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

    public String getSaleUserName() {
        return saleUserName;
    }

    public void setSaleUserName(String saleUserName) {
        this.saleUserName = saleUserName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<?> getGpsList() {
        return gpsList;
    }

    public void setGpsList(List<?> gpsList) {
        this.gpsList = gpsList;
    }
}
