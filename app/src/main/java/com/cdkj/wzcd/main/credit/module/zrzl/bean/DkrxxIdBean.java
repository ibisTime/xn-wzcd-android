package com.cdkj.wzcd.main.credit.module.zrzl.bean;

import java.io.Serializable;

/**
 * @author : qianLei
 * @since : 2019/12/31 15:12
 */
public class DkrxxIdBean implements Serializable {


    /**
     * birthAddress : 山西省稷山县西社镇柴家庄村第一居民组
     * customerBirth : 1995-08-21
     * userName : 柴运来
     * nation : 汉
     * idNo : 14272719950821351X
     * gender : 男
     * success : true
     */

    // 正面
    private String birthAddress;
    private String customerBirth;
    private String userName;
    private String nation;
    private String idNo;
    private String gender;

    /**
     * startDate : 2014-11-20
     * statdate : 2024-11-20
     * authref : 稷山县公安局
     */
    //反面
    private String startDate;
    private String statdate;
    private String authref;

    private String success;

    public String getBirthAddress() {
        return birthAddress;
    }

    public void setBirthAddress(String birthAddress) {
        this.birthAddress = birthAddress;
    }

    public String getCustomerBirth() {
        return customerBirth;
    }

    public void setCustomerBirth(String customerBirth) {
        this.customerBirth = customerBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatdate() {
        return statdate;
    }

    public void setStatdate(String statdate) {
        this.statdate = statdate;
    }

    public String getAuthref() {
        return authref;
    }

    public void setAuthref(String authref) {
        this.authref = authref;
    }
}
