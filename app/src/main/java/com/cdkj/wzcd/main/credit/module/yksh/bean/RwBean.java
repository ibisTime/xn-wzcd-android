package com.cdkj.wzcd.main.credit.module.yksh.bean;

import java.io.Serializable;

/**
 * @author : qianLei
 * @since : 2020/1/1 22:27
 */
public class RwBean implements Serializable {

    /**
     * name :
     * time :
     * getUser :
     */
    private int position;

    private String name;
    private String time;
    private String getUser;
    private String getUserName;

    public int getPosition() {
        return position;
    }

    public RwBean setPosition(int position) {
        this.position = position;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getGetUser() {
        return getUser;
    }

    public void setGetUser(String getUser) {
        this.getUser = getUser;
    }

    public String getGetUserName() {
        return getUserName;
    }

    public void setGetUserName(String getUserName) {
        this.getUserName = getUserName;
    }
}
