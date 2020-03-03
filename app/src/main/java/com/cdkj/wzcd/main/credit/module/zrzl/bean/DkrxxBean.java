package com.cdkj.wzcd.main.credit.module.zrzl.bean;

import java.io.Serializable;

/**
 * @author : qianLei
 * @since : 2019/12/31 13:28
 */
public class DkrxxBean implements Serializable {


    /**
     * userName :
     * loanRole :
     * gender :
     * nation :
     * idNo :
     * customerBirth :
     * birthAddress :
     * authref :
     * statdate :
     * mobile :
     * bankCreditResult :
     * holdIdCardPdf :
     * idFront :
     * idReverse :
     * bankCreditResultRemark :
     */

    private String userName;
    private String loanRole;
    private String loanRoleName;
    private String gender;
    private String nation;
    private String idNo;
    private String customerBirth;
    private String birthAddress;
    private String authref;
    private String statdate;
    private String startDate;
    private String mobile;
    private String bankCreditResult;
    private String holdIdCardPdf;
    private String idFront;
    private String idReverse;
    private String bankCreditResultRemark;

    /**
     * code :
     * education :
     * nowAddress :
     * nowAddressProvince :
     * nowAddressCity :
     * nowAddressArea :
     * marryState :
     * nowHouseType :
     * companyName :
     * companyProvince :
     * companyCity :
     * companyArea :
     * companyAddress :
     * position :
     * yearIncome :
     * permanentType :
     * emergencyName1 :
     * emergencyRelation1 :
     * emergencyMobile1 :
     * emergencyName2 :
     * emergencyRelation2 :
     * emergencyMobile2 :
     * localBirthAddress :
     * localResidencePermit :
     * presentJobYears :
     * workCompanyProperty :
     * workDatetime :
     * nowAddressDate :
     * nowAddressMobile :
     * nowAddressState :
     * relation :
     */

    private String code;
    private String education;
    private String nowAddress;
    private String nowAddressProvince;
    private String nowAddressCity;
    private String nowAddressArea;
    private String marryState;
    private String nowHouseType;
    private String companyName;
    private String companyProvince;
    private String companyCity;
    private String companyArea;
    private String companyAddress;
    private String position;
    private String yearIncome;
    private String permanentType;
    private String cardPostAddress;
    private String emergencyName1;
    private String emergencyRelation1;
    private String emergencyMobile1;
    private String emergencyName2;
    private String emergencyRelation2;
    private String emergencyMobile2;
    private String localBirthAddress;
    private String localResidencePermit;
    private String presentJobYears;
    private String workCompanyProperty;
    private String workDatetime;
    private String nowAddressDate;
    private String nowAddressMobile;
    private String nowAddressState;
    private String relation;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoanRole() {
        return loanRole;
    }

    public void setLoanRole(String loanRole) {
        this.loanRole = loanRole;
    }

    public String getLoanRoleName() {
        return loanRoleName;
    }

    public void setLoanRoleName(String loanRoleName) {
        this.loanRoleName = loanRoleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getCustomerBirth() {
        return customerBirth;
    }

    public void setCustomerBirth(String customerBirth) {
        this.customerBirth = customerBirth;
    }

    public String getBirthAddress() {
        return birthAddress;
    }

    public void setBirthAddress(String birthAddress) {
        this.birthAddress = birthAddress;
    }

    public String getAuthref() {
        return authref;
    }

    public void setAuthref(String authref) {
        this.authref = authref;
    }

    public String getStatdate() {
        return statdate;
    }

    public void setStatdate(String statdate) {
        this.statdate = statdate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getBankCreditResult() {
        return bankCreditResult;
    }

    public void setBankCreditResult(String bankCreditResult) {
        this.bankCreditResult = bankCreditResult;
    }

    public String getHoldIdCardPdf() {
        return holdIdCardPdf;
    }

    public void setHoldIdCardPdf(String holdIdCardPdf) {
        this.holdIdCardPdf = holdIdCardPdf;
    }

    public String getIdFront() {
        return idFront;
    }

    public void setIdFront(String idFront) {
        this.idFront = idFront;
    }

    public String getIdReverse() {
        return idReverse;
    }

    public void setIdReverse(String idReverse) {
        this.idReverse = idReverse;
    }

    public String getBankCreditResultRemark() {
        return bankCreditResultRemark;
    }

    public void setBankCreditResultRemark(String bankCreditResultRemark) {
        this.bankCreditResultRemark = bankCreditResultRemark;
    }

    public DkrxxBean(){

    }

    public DkrxxBean(String loanRole, String loanRoleName){
        this.loanRole = loanRole;
        this.loanRoleName = loanRoleName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getNowAddress() {
        return nowAddress;
    }

    public void setNowAddress(String nowAddress) {
        this.nowAddress = nowAddress;
    }

    public String getNowAddressProvince() {
        return nowAddressProvince;
    }

    public void setNowAddressProvince(String nowAddressProvince) {
        this.nowAddressProvince = nowAddressProvince;
    }

    public String getNowAddressCity() {
        return nowAddressCity;
    }

    public void setNowAddressCity(String nowAddressCity) {
        this.nowAddressCity = nowAddressCity;
    }

    public String getNowAddressArea() {
        return nowAddressArea;
    }

    public void setNowAddressArea(String nowAddressArea) {
        this.nowAddressArea = nowAddressArea;
    }

    public String getMarryState() {
        return marryState;
    }

    public void setMarryState(String marryState) {
        this.marryState = marryState;
    }

    public String getNowHouseType() {
        return nowHouseType;
    }

    public void setNowHouseType(String nowHouseType) {
        this.nowHouseType = nowHouseType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyProvince() {
        return companyProvince;
    }

    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyArea() {
        return companyArea;
    }

    public void setCompanyArea(String companyArea) {
        this.companyArea = companyArea;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getYearIncome() {
        return yearIncome;
    }

    public void setYearIncome(String yearIncome) {
        this.yearIncome = yearIncome;
    }

    public String getPermanentType() {
        return permanentType;
    }

    public void setPermanentType(String permanentType) {
        this.permanentType = permanentType;
    }

    public String getCardPostAddress() {
        return cardPostAddress;
    }

    public void setCardPostAddress(String cardPostAddress) {
        this.cardPostAddress = cardPostAddress;
    }

    public String getEmergencyName1() {
        return emergencyName1;
    }

    public void setEmergencyName1(String emergencyName1) {
        this.emergencyName1 = emergencyName1;
    }

    public String getEmergencyRelation1() {
        return emergencyRelation1;
    }

    public void setEmergencyRelation1(String emergencyRelation1) {
        this.emergencyRelation1 = emergencyRelation1;
    }

    public String getEmergencyMobile1() {
        return emergencyMobile1;
    }

    public void setEmergencyMobile1(String emergencyMobile1) {
        this.emergencyMobile1 = emergencyMobile1;
    }

    public String getEmergencyName2() {
        return emergencyName2;
    }

    public void setEmergencyName2(String emergencyName2) {
        this.emergencyName2 = emergencyName2;
    }

    public String getEmergencyRelation2() {
        return emergencyRelation2;
    }

    public void setEmergencyRelation2(String emergencyRelation2) {
        this.emergencyRelation2 = emergencyRelation2;
    }

    public String getEmergencyMobile2() {
        return emergencyMobile2;
    }

    public void setEmergencyMobile2(String emergencyMobile2) {
        this.emergencyMobile2 = emergencyMobile2;
    }

    public String getLocalBirthAddress() {
        return localBirthAddress;
    }

    public void setLocalBirthAddress(String localBirthAddress) {
        this.localBirthAddress = localBirthAddress;
    }

    public String getLocalResidencePermit() {
        return localResidencePermit;
    }

    public void setLocalResidencePermit(String localResidencePermit) {
        this.localResidencePermit = localResidencePermit;
    }

    public String getPresentJobYears() {
        return presentJobYears;
    }

    public void setPresentJobYears(String presentJobYears) {
        this.presentJobYears = presentJobYears;
    }

    public String getWorkCompanyProperty() {
        return workCompanyProperty;
    }

    public void setWorkCompanyProperty(String workCompanyProperty) {
        this.workCompanyProperty = workCompanyProperty;
    }

    public String getWorkDatetime() {
        return workDatetime;
    }

    public void setWorkDatetime(String workDatetime) {
        this.workDatetime = workDatetime;
    }

    public String getNowAddressDate() {
        return nowAddressDate;
    }

    public void setNowAddressDate(String nowAddressDate) {
        this.nowAddressDate = nowAddressDate;
    }

    public String getNowAddressMobile() {
        return nowAddressMobile;
    }

    public void setNowAddressMobile(String nowAddressMobile) {
        this.nowAddressMobile = nowAddressMobile;
    }

    public String getNowAddressState() {
        return nowAddressState;
    }

    public void setNowAddressState(String nowAddressState) {
        this.nowAddressState = nowAddressState;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
