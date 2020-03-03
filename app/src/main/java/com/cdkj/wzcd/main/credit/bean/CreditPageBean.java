package com.cdkj.wzcd.main.credit.bean;

import java.io.Serializable;

/**
 * @author : qianLei
 * @since : 2019/12/26 14:05
 */
public class CreditPageBean implements Serializable {


    /**
     * code : CB332019122003202A
     * bizCode : CB332019122003202A
     * region : 1
     * bizType : 0
     * companyCode : DP201800000000000000001
     * teamCode : BT201909171301034281856
     * captainCode : U201909171125025874839
     * saleUserId : U201909171125329802131
     * insideJob : U201909171125329802131
     * loanBank : BA201905161644497469804
     * loanAmount : 300000000
     * fxAmount : 0
     * lyDeposit : 0
     * repointAmount : 24000000
     * gpsFee : 300000
     * otherFee : 0
     * carFunds3 : 5340000
     * carFunds4 : 0
     * carFunds5 : 0
     * status : 004
     * cancelStatus : 0
     * curNodeCode : a1
     * isAdvanceFund : 1
     * isCancel : 0
     * applyDatetime : Dec 20, 2019 10:11:08 PM
     * customerName : 金阳
     * teamName : A团队
     * saleUserName : 虞瑞冬
     * saleUserCompanyName : 温州浩源控股有限公司
     * saleUserDepartMentName : 浙江业务部
     * saleUserPostName : 温州业务员
     * insideJobName : 虞瑞冬
     * insideJobCompanyName : 温州浩源控股有限公司
     * insideJobDepartMentName : 浙江业务部
     * insideJobPostName : 温州业务员
     * creditUser : {"code":"CU201912241506024357869","bizCode":"CB332019122003202A","loanRole":"1","userName":"金阳","mobile":"17611591955","idNo":"","authref":"北京市公安局西城分局","startDate":"2004-10-27","statdate":"2004-10-27","bankCreditResult":"","bankCreditResultRemark":"送快递发货","gender":"","nation":"汉","customerBirth":"","emergencyName1":"12","emergencyRelation1":"3","emergencyMobile1":"13738545408","emergencyName2":"13","emergencyRelation2":"9","emergencyMobile2":"13738545467","companyAddress":"asdad","workDatetime":"2019-12-24 18:35:40","birthAddress":"北京市西城区复兴门外大街999号院11号楼3单元502室","nowAddressDate":"2019-12-24 18:35:40","setEmergencyRelation1Name":"妻子","setEmergencyRelation2Name":"同事"}
     * loanBankName : 中国建设银行
     * subbranchBankName : 杭州科创支行
     * regionName : 浙江-温州
     * periods : 24
     */

    private String code;
    private String bizCode;
    private String region;
    private String bizType;
    private String companyCode;
    private String teamCode;
    private String captainCode;
    private String saleUserId;
    private String insideJob;
    private String loanBank;
    private String loanAmount;
    private int fxAmount;
    private int lyDeposit;
    private int repointAmount;
    private int gpsFee;
    private int otherFee;
    private String carFunds3;
    private String carFunds4;
    private String carFunds5;
    private String status;
    private String cancelStatus;
    private String curNodeCode;
    private String pledgeNodeCode;
    private String materialNodeCode;
    private String isAdvanceFund;
    private String isCancel;
    private String applyDatetime;
    private String customerName;
    private String teamName;
    private String saleUserName;
    private String saleUserCompanyName;
    private String saleUserDepartMentName;
    private String saleUserPostName;
    private String insideJobName;
    private String insideJobCompanyName;
    private String insideJobDepartMentName;
    private String insideJobPostName;
    private CreditUserBean creditUser;
    private String loanBankName;
    private String subbranchBankName;
    private String regionName;
    private int periods;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public String getCaptainCode() {
        return captainCode;
    }

    public void setCaptainCode(String captainCode) {
        this.captainCode = captainCode;
    }

    public String getSaleUserId() {
        return saleUserId;
    }

    public void setSaleUserId(String saleUserId) {
        this.saleUserId = saleUserId;
    }

    public String getInsideJob() {
        return insideJob;
    }

    public void setInsideJob(String insideJob) {
        this.insideJob = insideJob;
    }

    public String getLoanBank() {
        return loanBank;
    }

    public void setLoanBank(String loanBank) {
        this.loanBank = loanBank;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getFxAmount() {
        return fxAmount;
    }

    public void setFxAmount(int fxAmount) {
        this.fxAmount = fxAmount;
    }

    public int getLyDeposit() {
        return lyDeposit;
    }

    public void setLyDeposit(int lyDeposit) {
        this.lyDeposit = lyDeposit;
    }

    public int getRepointAmount() {
        return repointAmount;
    }

    public void setRepointAmount(int repointAmount) {
        this.repointAmount = repointAmount;
    }

    public int getGpsFee() {
        return gpsFee;
    }

    public void setGpsFee(int gpsFee) {
        this.gpsFee = gpsFee;
    }

    public int getOtherFee() {
        return otherFee;
    }

    public void setOtherFee(int otherFee) {
        this.otherFee = otherFee;
    }

    public String getCarFunds3() {
        return carFunds3;
    }

    public void setCarFunds3(String carFunds3) {
        this.carFunds3 = carFunds3;
    }

    public String getCarFunds4() {
        return carFunds4;
    }

    public void setCarFunds4(String carFunds4) {
        this.carFunds4 = carFunds4;
    }

    public String getCarFunds5() {
        return carFunds5;
    }

    public void setCarFunds5(String carFunds5) {
        this.carFunds5 = carFunds5;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCancelStatus() {
        return cancelStatus;
    }

    public void setCancelStatus(String cancelStatus) {
        this.cancelStatus = cancelStatus;
    }

    public String getPledgeNodeCode() {
        return pledgeNodeCode;
    }

    public void setPledgeNodeCode(String pledgeNodeCode) {
        this.pledgeNodeCode = pledgeNodeCode;
    }

    public String getMaterialNodeCode() {
        return materialNodeCode;
    }

    public void setMaterialNodeCode(String materialNodeCode) {
        this.materialNodeCode = materialNodeCode;
    }

    public String getCurNodeCode() {
        return curNodeCode;
    }

    public void setCurNodeCode(String curNodeCode) {
        this.curNodeCode = curNodeCode;
    }

    public String getIsAdvanceFund() {
        return isAdvanceFund;
    }

    public void setIsAdvanceFund(String isAdvanceFund) {
        this.isAdvanceFund = isAdvanceFund;
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public String getApplyDatetime() {
        return applyDatetime;
    }

    public void setApplyDatetime(String applyDatetime) {
        this.applyDatetime = applyDatetime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getSaleUserCompanyName() {
        return saleUserCompanyName;
    }

    public void setSaleUserCompanyName(String saleUserCompanyName) {
        this.saleUserCompanyName = saleUserCompanyName;
    }

    public String getSaleUserDepartMentName() {
        return saleUserDepartMentName;
    }

    public void setSaleUserDepartMentName(String saleUserDepartMentName) {
        this.saleUserDepartMentName = saleUserDepartMentName;
    }

    public String getSaleUserPostName() {
        return saleUserPostName;
    }

    public void setSaleUserPostName(String saleUserPostName) {
        this.saleUserPostName = saleUserPostName;
    }

    public String getInsideJobName() {
        return insideJobName;
    }

    public void setInsideJobName(String insideJobName) {
        this.insideJobName = insideJobName;
    }

    public String getInsideJobCompanyName() {
        return insideJobCompanyName;
    }

    public void setInsideJobCompanyName(String insideJobCompanyName) {
        this.insideJobCompanyName = insideJobCompanyName;
    }

    public String getInsideJobDepartMentName() {
        return insideJobDepartMentName;
    }

    public void setInsideJobDepartMentName(String insideJobDepartMentName) {
        this.insideJobDepartMentName = insideJobDepartMentName;
    }

    public String getInsideJobPostName() {
        return insideJobPostName;
    }

    public void setInsideJobPostName(String insideJobPostName) {
        this.insideJobPostName = insideJobPostName;
    }

    public CreditUserBean getCreditUser() {
        return creditUser;
    }

    public void setCreditUser(CreditUserBean creditUser) {
        this.creditUser = creditUser;
    }

    public String getLoanBankName() {
        return loanBankName;
    }

    public void setLoanBankName(String loanBankName) {
        this.loanBankName = loanBankName;
    }

    public String getSubbranchBankName() {
        return subbranchBankName;
    }

    public void setSubbranchBankName(String subbranchBankName) {
        this.subbranchBankName = subbranchBankName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    public static class CreditUserBean implements Serializable {

        /**
         * code : CU201912241506024357869
         * bizCode : CB332019122003202A
         * loanRole : 1
         * userName : 金阳
         * mobile : 17611591955
         * idNo :
         * authref : 北京市公安局西城分局
         * startDate : 2004-10-27
         * statdate : 2004-10-27
         * bankCreditResult :
         * bankCreditResultRemark : 送快递发货
         * gender :
         * nation : 汉
         * customerBirth :
         * emergencyName1 : 12
         * emergencyRelation1 : 3
         * emergencyMobile1 : 13738545408
         * emergencyName2 : 13
         * emergencyRelation2 : 9
         * emergencyMobile2 : 13738545467
         * companyAddress : asdad
         * workDatetime : 2019-12-24 18:35:40
         * birthAddress : 北京市西城区复兴门外大街999号院11号楼3单元502室
         * nowAddressDate : 2019-12-24 18:35:40
         * setEmergencyRelation1Name : 妻子
         * setEmergencyRelation2Name : 同事
         */

        private String code;
        private String bizCode;
        private String loanRole;
        private String userName;
        private String mobile;
        private String idNo;
        private String authref;
        private String startDate;
        private String statdate;
        private String bankCreditResult;
        private String bankCreditResultRemark;
        private String gender;
        private String nation;
        private String customerBirth;
        private String emergencyName1;
        private String emergencyRelation1;
        private String emergencyMobile1;
        private String emergencyName2;
        private String emergencyRelation2;
        private String emergencyMobile2;
        private String companyAddress;
        private String workDatetime;
        private String birthAddress;
        private String nowAddressDate;
        private String setEmergencyRelation1Name;
        private String setEmergencyRelation2Name;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getBizCode() {
            return bizCode;
        }

        public void setBizCode(String bizCode) {
            this.bizCode = bizCode;
        }

        public String getLoanRole() {
            return loanRole;
        }

        public void setLoanRole(String loanRole) {
            this.loanRole = loanRole;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getIdNo() {
            return idNo;
        }

        public void setIdNo(String idNo) {
            this.idNo = idNo;
        }

        public String getAuthref() {
            return authref;
        }

        public void setAuthref(String authref) {
            this.authref = authref;
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

        public String getBankCreditResult() {
            return bankCreditResult;
        }

        public void setBankCreditResult(String bankCreditResult) {
            this.bankCreditResult = bankCreditResult;
        }

        public String getBankCreditResultRemark() {
            return bankCreditResultRemark;
        }

        public void setBankCreditResultRemark(String bankCreditResultRemark) {
            this.bankCreditResultRemark = bankCreditResultRemark;
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

        public String getCustomerBirth() {
            return customerBirth;
        }

        public void setCustomerBirth(String customerBirth) {
            this.customerBirth = customerBirth;
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

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getWorkDatetime() {
            return workDatetime;
        }

        public void setWorkDatetime(String workDatetime) {
            this.workDatetime = workDatetime;
        }

        public String getBirthAddress() {
            return birthAddress;
        }

        public void setBirthAddress(String birthAddress) {
            this.birthAddress = birthAddress;
        }

        public String getNowAddressDate() {
            return nowAddressDate;
        }

        public void setNowAddressDate(String nowAddressDate) {
            this.nowAddressDate = nowAddressDate;
        }

        public String getSetEmergencyRelation1Name() {
            return setEmergencyRelation1Name;
        }

        public void setSetEmergencyRelation1Name(String setEmergencyRelation1Name) {
            this.setEmergencyRelation1Name = setEmergencyRelation1Name;
        }

        public String getSetEmergencyRelation2Name() {
            return setEmergencyRelation2Name;
        }

        public void setSetEmergencyRelation2Name(String setEmergencyRelation2Name) {
            this.setEmergencyRelation2Name = setEmergencyRelation2Name;
        }
    }
}
