package com.cdkj.wzcd.model;

import java.io.Serializable;

/**
 * Created by cdkj on 2018/6/8.
 */

public class ReasonReplaceModel implements Serializable {

    private int location;
    private ReasonModel reasonModel;

    public int getLocation() {
        return location;
    }

    public ReasonReplaceModel setLocation(int location) {
        this.location = location;

        return this;
    }

    public ReasonModel getReasonModel() {
        return reasonModel;
    }

    public ReasonReplaceModel setReasonModel(ReasonModel reasonModel) {
        this.reasonModel = reasonModel;

        return this;
    }
}
