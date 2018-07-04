package com.cdkj.wzcd.model;

/**
 * Created by cdkj on 2018/6/18.
 */

public class GpsInstallReplaceModel {

    private int location;
    private GpsInstallModel gpsInstallModel;

    public int getLocation() {
        return location;
    }

    public GpsInstallReplaceModel setLocation(int location) {
        this.location = location;

        return this;
    }

    public GpsInstallModel getGpsInstallModel() {
        return gpsInstallModel;
    }

    public GpsInstallReplaceModel setGpsInstallModel(GpsInstallModel gpsInstallModel) {
        this.gpsInstallModel = gpsInstallModel;

        return this;
    }
}
