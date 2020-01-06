package com.cdkj.baselibrary.model.eventmodels;

/**
 * @author : qianLei
 * @since : 2019/9/4 18:54
 */
public class EventBean {

    private String tag;
    private Object value;
    private String value1;
    private String value2;
    private String value3;
    private String value4;

    public String getTag() {
        return tag;
    }

    public EventBean setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public EventBean setValue(Object value) {
        this.value = value;
        return this;
    }

    public String getValue1() {
        return value1;
    }

    public EventBean setValue1(String value1) {
        this.value1 = value1;
        return this;
    }

    public String getValue2() {
        return value2;
    }

    public EventBean setValue2(String value2) {
        this.value2 = value2;
        return this;
    }

    public String getValue3() {
        return value3;
    }

    public EventBean setValue3(String value3) {
        this.value3 = value3;
        return this;
    }

    public String getValue4() {
        return value4;
    }

    public EventBean setValue4(String value4) {
        this.value4 = value4;
        return this;
    }
}
