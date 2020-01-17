package com.cdkj.wzcd.model;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cdkj on 2018/7/2.
 */

public class DealersModel  extends DataSupport implements Serializable{


    /**
     * code : CD201806281622377756337
     * fullName : 经销商全称
     * abbrName : 经销商简称
     * isSelfDevelop : 1
     * address : 经销商地址
     * carDealerType : 0
     * mainContact : 主要联系人1
     * contactPhone : 18202771070
     * mainBrand : 主营品牌
     * parentGroup : 所属集团
     * agreementValidDateStart : Jun 21, 2018 12:00:00 AM
     * agreementValidDateEnd : Jul 13, 2018 12:00:00 AM
     * agreementStatus : 1
     * agreementPic : FvTw5nazUbBwSRoJ8DpDUWAPcKCg
     * settleWay : 1
     * businessArea : 业务区域1
     * belongBranchCompany : DP201800000000000000001
     * curNodeCode : 006_02
     * approveNote : 审核说明
     * remark : 备注
     * jxsCollectBankcardList : [{"code":"CB201806281622378072063","type":"2","companyCode":"CD201806281622377756337","realName":"户名","bankCode":"ICBC","subbranch":"悟空支行","bankcardNumber":"222222222222222"}]
     * carDealerProtocolList : [{"id":1,"carDealerCode":"CD201806281622377756337","bankCode":"ICBC","platCtRate12":0.5,"platCtRate24":0.5,"platCtRate36":0.5,"platZkRate12":0.5,"platZkRate24":0.5,"platZkRate36":0.5,"assureType":"1","assureFee":2222000,"dzType":"1","dzFee":3333000,"lyAmountType":"2","lyAmountRate":0.01,"gpsType":"2","gpsRate":0.01,"otherType":"2","otherRate":0.01,"introduceType":"1","introduceFee":222000,"returnPointType":"2","returnPointRate":0.01,"isDz":"1","insuAgencyYear1Type":"1","insuAgencyYear2Type":"1","insuAgencyYear3Type":"1"},{"id":2,"carDealerCode":"CD201806281622377756337","bankCode":"BOC","platCtRate12":0.1,"platCtRate24":0.1,"platCtRate36":0.1,"platZkRate12":0.1,"platZkRate24":0.1,"platZkRate36":0.1,"assureType":"1","assureFee":2222000,"dzType":"1","dzFee":333000,"lyAmountType":"1","lyAmountFee":444000,"gpsType":"2","gpsRate":0,"otherType":"1","otherFee":333000,"introduceType":"1","introduceFee":4444000,"returnPointType":"1","returnPointFee":1111000,"isDz":"1","insuAgencyYear1Type":"2","insuAgencyYear2Type":"2","insuAgencyYear3Type":"2"},{"id":3,"carDealerCode":"CD201806281622377756337","bankCode":"CCB","platCtRate12":0.4,"platCtRate24":0.4,"platCtRate36":0.4,"platZkRate12":0.4,"platZkRate24":0.4,"platZkRate36":0.4,"assureType":"2","assureRate":0,"dzType":"1","dzFee":222000,"lyAmountType":"2","lyAmountRate":0,"gpsType":"1","gpsFee":111000,"otherType":"2","otherRate":0,"introduceType":"1","introduceFee":111000,"returnPointType":"1","returnPointFee":3333000,"isDz":"1","insuAgencyYear1Type":"1","insuAgencyYear2Type":"2","insuAgencyYear3Type":"1"}]
     * gsCollectBankcardList : [{"code":"CB201806281622378188747","type":"3","belongBank":"1","companyCode":"CD201806281622377756337","realName":"八戒","bankCode":"ICBC","subbranch":"八戒支行","bankcardNumber":"111111111111111","pointRate":0.4}]
     * zhCollectBankcardList : [{"code":"CB201806281622378202335","type":"3","belongBank":"2","companyCode":"CD201806281622377756337","realName":"沙僧","bankCode":"CCB","subbranch":"沙僧支行","bankcardNumber":"222222222222222","pointRate":0.1}]
     * jhCollectBankcardList : [{"code":"CB201806281622378228236","type":"3","belongBank":"3","companyCode":"CD201806281622377756337","realName":"唐僧","bankCode":"BOC","subbranch":"唐僧支行","bankcardNumber":"222222222222222","pointRate":0.9}]
     */

    // 是否垫资
    private boolean isAdvanceFund;

    private String code;
    private String fullName;
    private double rebateRate;
    private String abbrName;
    private String isSelfDevelop;
    private String address;
    private String carDealerType;
    private String mainContact;
    private String contactPhone;
    private String mainBrand;
    private String parentGroup;
    private String agreementValidDateStart;
    private String agreementValidDateEnd;
    private String agreementStatus;
    private String agreementPic;
    private String settleWay;
    private String businessArea;
    private String belongBranchCompany;
    private String curNodeCode;
    private String approveNote;
    private String remark;
    private List<JxsCollectBankcardListBean> jxsCollectBankcardList;
    private List<CarDealerProtocolListBean> carDealerProtocolList;
    private List<GsCollectBankcardListBean> gsCollectBankcardList;
    private List<ZhCollectBankcardListBean> zhCollectBankcardList;
    private List<JhCollectBankcardListBean> jhCollectBankcardList;

    public double getRebateRate() {
        return rebateRate;
    }

    public void setRebateRate(double rebateRate) {
        this.rebateRate = rebateRate;
    }

    public boolean isAdvanceFund() {
        return isAdvanceFund;
    }

    public void setAdvanceFund(boolean advanceFund) {
        isAdvanceFund = advanceFund;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAbbrName() {
        return abbrName;
    }

    public void setAbbrName(String abbrName) {
        this.abbrName = abbrName;
    }

    public String getIsSelfDevelop() {
        return isSelfDevelop;
    }

    public void setIsSelfDevelop(String isSelfDevelop) {
        this.isSelfDevelop = isSelfDevelop;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCarDealerType() {
        return carDealerType;
    }

    public void setCarDealerType(String carDealerType) {
        this.carDealerType = carDealerType;
    }

    public String getMainContact() {
        return mainContact;
    }

    public void setMainContact(String mainContact) {
        this.mainContact = mainContact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getMainBrand() {
        return mainBrand;
    }

    public void setMainBrand(String mainBrand) {
        this.mainBrand = mainBrand;
    }

    public String getParentGroup() {
        return parentGroup;
    }

    public void setParentGroup(String parentGroup) {
        this.parentGroup = parentGroup;
    }

    public String getAgreementValidDateStart() {
        return agreementValidDateStart;
    }

    public void setAgreementValidDateStart(String agreementValidDateStart) {
        this.agreementValidDateStart = agreementValidDateStart;
    }

    public String getAgreementValidDateEnd() {
        return agreementValidDateEnd;
    }

    public void setAgreementValidDateEnd(String agreementValidDateEnd) {
        this.agreementValidDateEnd = agreementValidDateEnd;
    }

    public String getAgreementStatus() {
        return agreementStatus;
    }

    public void setAgreementStatus(String agreementStatus) {
        this.agreementStatus = agreementStatus;
    }

    public String getAgreementPic() {
        return agreementPic;
    }

    public void setAgreementPic(String agreementPic) {
        this.agreementPic = agreementPic;
    }

    public String getSettleWay() {
        return settleWay;
    }

    public void setSettleWay(String settleWay) {
        this.settleWay = settleWay;
    }

    public String getBusinessArea() {
        return businessArea;
    }

    public void setBusinessArea(String businessArea) {
        this.businessArea = businessArea;
    }

    public String getBelongBranchCompany() {
        return belongBranchCompany;
    }

    public void setBelongBranchCompany(String belongBranchCompany) {
        this.belongBranchCompany = belongBranchCompany;
    }

    public String getCurNodeCode() {
        return curNodeCode;
    }

    public void setCurNodeCode(String curNodeCode) {
        this.curNodeCode = curNodeCode;
    }

    public String getApproveNote() {
        return approveNote;
    }

    public void setApproveNote(String approveNote) {
        this.approveNote = approveNote;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<JxsCollectBankcardListBean> getJxsCollectBankcardList() {
        return jxsCollectBankcardList;
    }

    public void setJxsCollectBankcardList(List<JxsCollectBankcardListBean> jxsCollectBankcardList) {
        this.jxsCollectBankcardList = jxsCollectBankcardList;
    }

    public List<CarDealerProtocolListBean> getCarDealerProtocolList() {
        return carDealerProtocolList;
    }

    public void setCarDealerProtocolList(List<CarDealerProtocolListBean> carDealerProtocolList) {
        this.carDealerProtocolList = carDealerProtocolList;
    }

    public List<GsCollectBankcardListBean> getGsCollectBankcardList() {
        return gsCollectBankcardList;
    }

    public void setGsCollectBankcardList(List<GsCollectBankcardListBean> gsCollectBankcardList) {
        this.gsCollectBankcardList = gsCollectBankcardList;
    }

    public List<ZhCollectBankcardListBean> getZhCollectBankcardList() {
        return zhCollectBankcardList;
    }

    public void setZhCollectBankcardList(List<ZhCollectBankcardListBean> zhCollectBankcardList) {
        this.zhCollectBankcardList = zhCollectBankcardList;
    }

    public List<JhCollectBankcardListBean> getJhCollectBankcardList() {
        return jhCollectBankcardList;
    }

    public void setJhCollectBankcardList(List<JhCollectBankcardListBean> jhCollectBankcardList) {
        this.jhCollectBankcardList = jhCollectBankcardList;
    }

    public static class JxsCollectBankcardListBean {
        /**
         * code : CB201806281622378072063
         * type : 2
         * companyCode : CD201806281622377756337
         * realName : 户名
         * bankCode : ICBC
         * subbranch : 悟空支行
         * bankcardNumber : 222222222222222
         */

        private String code;
        private String type;
        private String companyCode;
        private String realName;
        private String bankCode;
        private String subbranch;
        private String bankcardNumber;

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

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getSubbranch() {
            return subbranch;
        }

        public void setSubbranch(String subbranch) {
            this.subbranch = subbranch;
        }

        public String getBankcardNumber() {
            return bankcardNumber;
        }

        public void setBankcardNumber(String bankcardNumber) {
            this.bankcardNumber = bankcardNumber;
        }
    }

    public static class CarDealerProtocolListBean {
        /**
         * id : 1
         * carDealerCode : CD201806281622377756337
         * bankCode : ICBC
         * platCtRate12 : 0.5
         * platCtRate24 : 0.5
         * platCtRate36 : 0.5
         * platZkRate12 : 0.5
         * platZkRate24 : 0.5
         * platZkRate36 : 0.5
         * assureType : 1
         * assureFee : 2222000
         * dzType : 1
         * dzFee : 3333000
         * lyAmountType : 2
         * lyAmountRate : 0.01
         * gpsType : 2
         * gpsRate : 0.01
         * otherType : 2
         * otherRate : 0.01
         * introduceType : 1
         * introduceFee : 222000
         * returnPointType : 2
         * returnPointRate : 0.01
         * isDz : 1
         * insuAgencyYear1Type : 1
         * insuAgencyYear2Type : 1
         * insuAgencyYear3Type : 1
         * lyAmountFee : 444000
         * otherFee : 333000
         * returnPointFee : 1111000
         * assureRate : 0
         * gpsFee : 111000
         */

        private int id;
        private String carDealerCode;
        private String bankCode;
        private double platCtRate12;
        private double platCtRate24;
        private double platCtRate36;
        private double platZkRate12;
        private double platZkRate24;
        private double platZkRate36;
        private String assureType;
        private String assureFee;
        private String dzType;
        private String dzFee;
        private String lyAmountType;
        private double lyAmountRate;
        private String gpsType;
        private double gpsRate;
        private String otherType;
        private double otherRate;
        private String introduceType;
        private String introduceFee;
        private String returnPointType;
        private double returnPointRate;
        private String isDz;
        private String insuAgencyYear1Type;
        private String insuAgencyYear2Type;
        private String insuAgencyYear3Type;
        private String lyAmountFee;
        private String otherFee;
        private String returnPointFee;
        private double assureRate;
        private String gpsFee;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getCarDealerCode() {
            return carDealerCode;
        }

        public void setCarDealerCode(String carDealerCode) {
            this.carDealerCode = carDealerCode;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public double getPlatCtRate12() {
            return platCtRate12;
        }

        public void setPlatCtRate12(double platCtRate12) {
            this.platCtRate12 = platCtRate12;
        }

        public double getPlatCtRate24() {
            return platCtRate24;
        }

        public void setPlatCtRate24(double platCtRate24) {
            this.platCtRate24 = platCtRate24;
        }

        public double getPlatCtRate36() {
            return platCtRate36;
        }

        public void setPlatCtRate36(double platCtRate36) {
            this.platCtRate36 = platCtRate36;
        }

        public double getPlatZkRate12() {
            return platZkRate12;
        }

        public void setPlatZkRate12(double platZkRate12) {
            this.platZkRate12 = platZkRate12;
        }

        public double getPlatZkRate24() {
            return platZkRate24;
        }

        public void setPlatZkRate24(double platZkRate24) {
            this.platZkRate24 = platZkRate24;
        }

        public double getPlatZkRate36() {
            return platZkRate36;
        }

        public void setPlatZkRate36(double platZkRate36) {
            this.platZkRate36 = platZkRate36;
        }

        public String getAssureType() {
            return assureType;
        }

        public void setAssureType(String assureType) {
            this.assureType = assureType;
        }

        public String getAssureFee() {
            return assureFee;
        }

        public void setAssureFee(String assureFee) {
            this.assureFee = assureFee;
        }

        public String getDzType() {
            return dzType;
        }

        public void setDzType(String dzType) {
            this.dzType = dzType;
        }

        public String getDzFee() {
            return dzFee;
        }

        public void setDzFee(String dzFee) {
            this.dzFee = dzFee;
        }

        public String getLyAmountType() {
            return lyAmountType;
        }

        public void setLyAmountType(String lyAmountType) {
            this.lyAmountType = lyAmountType;
        }

        public double getLyAmountRate() {
            return lyAmountRate;
        }

        public void setLyAmountRate(double lyAmountRate) {
            this.lyAmountRate = lyAmountRate;
        }

        public String getGpsType() {
            return gpsType;
        }

        public void setGpsType(String gpsType) {
            this.gpsType = gpsType;
        }

        public double getGpsRate() {
            return gpsRate;
        }

        public void setGpsRate(double gpsRate) {
            this.gpsRate = gpsRate;
        }

        public String getOtherType() {
            return otherType;
        }

        public void setOtherType(String otherType) {
            this.otherType = otherType;
        }

        public double getOtherRate() {
            return otherRate;
        }

        public void setOtherRate(double otherRate) {
            this.otherRate = otherRate;
        }

        public String getIntroduceType() {
            return introduceType;
        }

        public void setIntroduceType(String introduceType) {
            this.introduceType = introduceType;
        }

        public String getIntroduceFee() {
            return introduceFee;
        }

        public void setIntroduceFee(String introduceFee) {
            this.introduceFee = introduceFee;
        }

        public String getReturnPointType() {
            return returnPointType;
        }

        public void setReturnPointType(String returnPointType) {
            this.returnPointType = returnPointType;
        }

        public double getReturnPointRate() {
            return returnPointRate;
        }

        public void setReturnPointRate(double returnPointRate) {
            this.returnPointRate = returnPointRate;
        }

        public String getIsDz() {
            return isDz;
        }

        public void setIsDz(String isDz) {
            this.isDz = isDz;
        }

        public String getInsuAgencyYear1Type() {
            return insuAgencyYear1Type;
        }

        public void setInsuAgencyYear1Type(String insuAgencyYear1Type) {
            this.insuAgencyYear1Type = insuAgencyYear1Type;
        }

        public String getInsuAgencyYear2Type() {
            return insuAgencyYear2Type;
        }

        public void setInsuAgencyYear2Type(String insuAgencyYear2Type) {
            this.insuAgencyYear2Type = insuAgencyYear2Type;
        }

        public String getInsuAgencyYear3Type() {
            return insuAgencyYear3Type;
        }

        public void setInsuAgencyYear3Type(String insuAgencyYear3Type) {
            this.insuAgencyYear3Type = insuAgencyYear3Type;
        }

        public String getLyAmountFee() {
            return lyAmountFee;
        }

        public void setLyAmountFee(String lyAmountFee) {
            this.lyAmountFee = lyAmountFee;
        }

        public String getOtherFee() {
            return otherFee;
        }

        public void setOtherFee(String otherFee) {
            this.otherFee = otherFee;
        }

        public String getReturnPointFee() {
            return returnPointFee;
        }

        public void setReturnPointFee(String returnPointFee) {
            this.returnPointFee = returnPointFee;
        }

        public double getAssureRate() {
            return assureRate;
        }

        public void setAssureRate(double assureRate) {
            this.assureRate = assureRate;
        }

        public String getGpsFee() {
            return gpsFee;
        }

        public void setGpsFee(String gpsFee) {
            this.gpsFee = gpsFee;
        }
    }

    public static class GsCollectBankcardListBean {
        /**
         * code : CB201806281622378188747
         * type : 3
         * belongBank : 1
         * companyCode : CD201806281622377756337
         * realName : 八戒
         * bankCode : ICBC
         * subbranch : 八戒支行
         * bankcardNumber : 111111111111111
         * pointRate : 0.4
         */

        private String code;
        private String type;
        private String belongBank;
        private String companyCode;
        private String realName;
        private String bankCode;
        private String subbranch;
        private String bankcardNumber;
        private double pointRate;

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

        public String getBelongBank() {
            return belongBank;
        }

        public void setBelongBank(String belongBank) {
            this.belongBank = belongBank;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getSubbranch() {
            return subbranch;
        }

        public void setSubbranch(String subbranch) {
            this.subbranch = subbranch;
        }

        public String getBankcardNumber() {
            return bankcardNumber;
        }

        public void setBankcardNumber(String bankcardNumber) {
            this.bankcardNumber = bankcardNumber;
        }

        public double getPointRate() {
            return pointRate;
        }

        public void setPointRate(double pointRate) {
            this.pointRate = pointRate;
        }
    }

    public static class ZhCollectBankcardListBean {
        /**
         * code : CB201806281622378202335
         * type : 3
         * belongBank : 2
         * companyCode : CD201806281622377756337
         * realName : 沙僧
         * bankCode : CCB
         * subbranch : 沙僧支行
         * bankcardNumber : 222222222222222
         * pointRate : 0.1
         */

        private String code;
        private String type;
        private String belongBank;
        private String companyCode;
        private String realName;
        private String bankCode;
        private String subbranch;
        private String bankcardNumber;
        private double pointRate;

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

        public String getBelongBank() {
            return belongBank;
        }

        public void setBelongBank(String belongBank) {
            this.belongBank = belongBank;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getSubbranch() {
            return subbranch;
        }

        public void setSubbranch(String subbranch) {
            this.subbranch = subbranch;
        }

        public String getBankcardNumber() {
            return bankcardNumber;
        }

        public void setBankcardNumber(String bankcardNumber) {
            this.bankcardNumber = bankcardNumber;
        }

        public double getPointRate() {
            return pointRate;
        }

        public void setPointRate(double pointRate) {
            this.pointRate = pointRate;
        }
    }

    public static class JhCollectBankcardListBean {
        /**
         * code : CB201806281622378228236
         * type : 3
         * belongBank : 3
         * companyCode : CD201806281622377756337
         * realName : 唐僧
         * bankCode : BOC
         * subbranch : 唐僧支行
         * bankcardNumber : 222222222222222
         * pointRate : 0.9
         */

        private String code;
        private String type;
        private String belongBank;
        private String companyCode;
        private String realName;
        private String bankCode;
        private String subbranch;
        private String bankcardNumber;
        private double pointRate;

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

        public String getBelongBank() {
            return belongBank;
        }

        public void setBelongBank(String belongBank) {
            this.belongBank = belongBank;
        }

        public String getCompanyCode() {
            return companyCode;
        }

        public void setCompanyCode(String companyCode) {
            this.companyCode = companyCode;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getBankCode() {
            return bankCode;
        }

        public void setBankCode(String bankCode) {
            this.bankCode = bankCode;
        }

        public String getSubbranch() {
            return subbranch;
        }

        public void setSubbranch(String subbranch) {
            this.subbranch = subbranch;
        }

        public String getBankcardNumber() {
            return bankcardNumber;
        }

        public void setBankcardNumber(String bankcardNumber) {
            this.bankcardNumber = bankcardNumber;
        }

        public double getPointRate() {
            return pointRate;
        }

        public void setPointRate(double pointRate) {
            this.pointRate = pointRate;
        }
    }
}
