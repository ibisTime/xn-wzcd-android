package com.cdkj.wzcd.model;

/**
 * @author : qianLei
 * @since : 2019/12/27 14:18
 */
public class LocalityModel {


    /**
     * id : 1180
     * cityId : 288
     * cityName : 定西
     * provId : 21
     * provName : 甘肃
     * createDatetime : Sep 16, 2019 6:43:04 PM
     */

    private int id;
    private String cityId;
    private String cityName;
    private String provId;
    private String provName;
    private String createDatetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getProvId() {
        return provId;
    }

    public void setProvId(String provId) {
        this.provId = provId;
    }

    public String getProvName() {
        return provName;
    }

    public void setProvName(String provName) {
        this.provName = provName;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }
}
