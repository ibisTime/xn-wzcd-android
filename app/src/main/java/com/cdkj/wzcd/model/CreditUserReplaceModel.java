package com.cdkj.wzcd.model;

import java.io.Serializable;

/**
 * Created by cdkj on 2018/6/8.
 */

public class CreditUserReplaceModel implements Serializable {

    private int location;
    private CreditUserModel creditUserModel;

    public int getLocation() {
        return location;
    }

    public CreditUserReplaceModel setLocation(int location) {
        this.location = location;

        return this;
    }

    public CreditUserModel getCreditUserModel() {
        return creditUserModel;
    }

    public CreditUserReplaceModel setCreditUserModel(CreditUserModel creditUserModel) {
        this.creditUserModel = creditUserModel;

        return this;
    }
}
