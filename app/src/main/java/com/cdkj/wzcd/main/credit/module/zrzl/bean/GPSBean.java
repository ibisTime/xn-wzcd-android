package com.cdkj.wzcd.main.credit.module.zrzl.bean;

import java.io.Serializable;

/**
 * @author : qianLei
 * @since : 2019/12/31 23:40
 */
public class GPSBean implements Serializable {

    private boolean isInit = true;
    private String applyUserId;

    private String code;
    private String gpsDevNo;
    private String azPhotos;
    private String budgetOrder;

    public boolean isInit() {
        return isInit;
    }

    public void setInit(boolean init) {
        isInit = init;
    }

    public String getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(String applyUserId) {
        this.applyUserId = applyUserId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGpsDevNo() {
        return gpsDevNo;
    }

    public void setGpsDevNo(String gpsDevNo) {
        this.gpsDevNo = gpsDevNo;
    }

    public String getAzPhotos() {
        return azPhotos;
    }

    public void setAzPhotos(String azPhotos) {
        this.azPhotos = azPhotos;
    }

    public String getBudgetOrder() {
        return budgetOrder;
    }

    public void setBudgetOrder(String budgetOrder) {
        this.budgetOrder = budgetOrder;
    }
}
