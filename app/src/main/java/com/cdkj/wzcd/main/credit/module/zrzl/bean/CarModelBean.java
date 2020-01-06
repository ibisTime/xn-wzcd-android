package com.cdkj.wzcd.main.credit.module.zrzl.bean;

import java.util.List;

/**
 * @author : qianLei
 * @since : 2019/12/31 10:11
 */
public class CarModelBean {


    /**
     * code : 399
     * seriesId : 13
     * modelId : 399
     * type : 1
     * name : 2012款 奥迪A1(进口) 1.4 TFSI 双离合 Ego
     * seriesCode : 13
     * seriesName : 奥迪A1(进口)
     * brandCode : 1
     * brandName : 奥迪
     * displacement : 1.4
     * salePrice : 249800000
     * modelYear : 2012
     * minRegYear : 2011
     * maxRegYear : 2014
     * liter : 1.4T
     * gearType : 自动
     * dischargeStandard : 欧5
     * seatNumber : 4
     * location : 1
     * orderNo : 21
     * status : 1
     * updater : U201906061634275406474
     * updateDatetime : Jun 21, 2019 5:12:07 PM
     * caonfigList : []
     * collectNumber : 0
     * configs : [{"code":"CC201906061455215264015","name":"配置1","updater":"USYS201800000000001","updateDatetime":"Jun 6, 2019 2:55:21 PM","remark":"备注","isConfig":"0"}]
     */

    private String code;
    private String seriesId;
    private String modelId;
    private String type;
    private String name;
    private String seriesCode;
    private String seriesName;
    private String brandCode;
    private String brandName;
    private double displacement;
    private int salePrice;
    private String modelYear;
    private String minRegYear;
    private String maxRegYear;
    private String liter;
    private String gearType;
    private String dischargeStandard;
    private String seatNumber;
    private String location;
    private int orderNo;
    private String status;
    private String updater;
    private String updateDatetime;
    private int collectNumber;
    private List<?> caonfigList;
    private List<ConfigsBean> configs;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeriesCode() {
        return seriesCode;
    }

    public void setSeriesCode(String seriesCode) {
        this.seriesCode = seriesCode;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getDisplacement() {
        return displacement;
    }

    public void setDisplacement(double displacement) {
        this.displacement = displacement;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getMinRegYear() {
        return minRegYear;
    }

    public void setMinRegYear(String minRegYear) {
        this.minRegYear = minRegYear;
    }

    public String getMaxRegYear() {
        return maxRegYear;
    }

    public void setMaxRegYear(String maxRegYear) {
        this.maxRegYear = maxRegYear;
    }

    public String getLiter() {
        return liter;
    }

    public void setLiter(String liter) {
        this.liter = liter;
    }

    public String getGearType() {
        return gearType;
    }

    public void setGearType(String gearType) {
        this.gearType = gearType;
    }

    public String getDischargeStandard() {
        return dischargeStandard;
    }

    public void setDischargeStandard(String dischargeStandard) {
        this.dischargeStandard = dischargeStandard;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public int getCollectNumber() {
        return collectNumber;
    }

    public void setCollectNumber(int collectNumber) {
        this.collectNumber = collectNumber;
    }

    public List<?> getCaonfigList() {
        return caonfigList;
    }

    public void setCaonfigList(List<?> caonfigList) {
        this.caonfigList = caonfigList;
    }

    public List<ConfigsBean> getConfigs() {
        return configs;
    }

    public void setConfigs(List<ConfigsBean> configs) {
        this.configs = configs;
    }

    public static class ConfigsBean {

        /**
         * code : CC201906061455215264015
         * name : 配置1
         * updater : USYS201800000000001
         * updateDatetime : Jun 6, 2019 2:55:21 PM
         * remark : 备注
         * isConfig : 0
         */

        private String code;
        private String name;
        private String updater;
        private String updateDatetime;
        private String remark;
        private String isConfig;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUpdater() {
            return updater;
        }

        public void setUpdater(String updater) {
            this.updater = updater;
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

        public String getIsConfig() {
            return isConfig;
        }

        public void setIsConfig(String isConfig) {
            this.isConfig = isConfig;
        }
    }
}
