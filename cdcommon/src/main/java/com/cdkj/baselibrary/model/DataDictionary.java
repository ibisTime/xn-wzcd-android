package com.cdkj.baselibrary.model;


import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by cdkj on 2018/5/30.
 */

public class DataDictionary extends DataSupport implements Serializable  {


    /**
     * id : 41
     * type : 1
     * parentKey : loan_period
     * dkey : 12
     * dvalue : 12期
     * updater : admin
     * updateDatetime : Jun 20, 2018 12:27:16 AM
     */

    private int id;
    private String type;
    private String tag;//用于记录一些其他的值
    private String parentKey;
    private String dkey;
    private String dvalue;
    private String updater;
    private String updateDatetime;

    public String getTag() {
        return tag;
    }

    public DataDictionary setTag(String tag) {
        this.tag = tag;
        return this;
    }

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

    public String getParentKey() {
        return parentKey;
    }

    public void setParentKey(String parentKey) {
        this.parentKey = parentKey;
    }

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
}
