package com.cdkj.baselibrary.model;

import java.io.Serializable;

/**
 * Created by cdkj on 2018/5/30.
 */

public class DataDictionary implements Serializable {


    /**
     * dkey : currency
     * dvalue : 币种
     * id : 1
     * remark : 备注
     * systemCode : 8
     * type : 1
     * updateDatetime : Sep 17, 2016 2:36:07 PM
     * updater : admin
     */

    private String dkey;
    private String dvalue;
    private String id;
    private String remark;
    private String systemCode;
    private String type;
    private String updateDatetime;
    private String updater;

    public String getDkey() {
        return dkey;
    }

    public DataDictionary setDkey(String dkey) {
        this.dkey = dkey;

        return this;
    }

    public String getDvalue() {
        return dvalue;
    }

    public DataDictionary setDvalue(String dvalue) {
        this.dvalue = dvalue;

        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }
}
