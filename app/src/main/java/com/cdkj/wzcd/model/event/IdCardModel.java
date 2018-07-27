package com.cdkj.wzcd.model.event;

/**
 * Created by cdkj on 2018/7/1.
 */

public class IdCardModel {


    /**
     * residenceAddress : 贵州省遵义市红花岗区深溪镇深溪街900号
     * birth : 19950718
     * realName : 雷黔
     * nationality : 汉
     * idNo : 522101199507184619
     * gender : 男
     * success : true
     */

    private String residenceAddress;
    private String birth;
    private String realName;
    private String nationality;
    private String idNo;
    private String gender;
    private String success;

    // 反面信息
    private String endDate;
    private String startDate;
    private String issue;


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getResidenceAddress() {
        return residenceAddress;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
