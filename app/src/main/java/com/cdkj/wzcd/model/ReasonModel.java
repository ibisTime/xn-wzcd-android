package com.cdkj.wzcd.model;

import java.io.Serializable;

/**
 * Created by cdkj on 2018/7/16.
 */

public class ReasonModel implements Serializable {

    private String reason;
    private boolean isChoice;

    public boolean isChoice() {
        return isChoice;
    }

    public void setChoice(boolean choice) {
        isChoice = choice;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
