package com.cdkj.wzcd.model;

/**
 * Created by cdkj on 2018/7/1.
 */

public class SystemParameterModel {


    /**
     * id : 35
     * type : budget_order
     * ckey : budget_gps_deduct_rate
     * cvalue : 0.5
     * updater : U201806281558123468782
     * updateDatetime : Jun 30, 2018 2:33:44 PM
     * remark : gps提成百分比
     */

    private int id;
    private String type;
    private String ckey;
    private String cvalue;
    private String updater;
    private String updateDatetime;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCkey() {
        return ckey;
    }

    public void setCkey(String ckey) {
        this.ckey = ckey;
    }

    public String getCvalue() {
        return cvalue;
    }

    public void setCvalue(String cvalue) {
        this.cvalue = cvalue;
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
}
