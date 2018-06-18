package com.cdkj.wzcd.model;

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
}
