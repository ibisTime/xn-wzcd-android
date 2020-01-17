package com.cdkj.wzcd.common;

import java.io.Serializable;

/**
 * @updateDts 2020/1/7
 */
public class SearchBean implements Serializable {
    private String key;
    private String value;
    private String tag;
    private String logo;

    public String getKey() {
        return key;
    }

    public SearchBean setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public SearchBean setValue(String value) {
        this.value = value;
        return  this;
    }

    public String getTag() {
        return tag;
    }

    public SearchBean setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
