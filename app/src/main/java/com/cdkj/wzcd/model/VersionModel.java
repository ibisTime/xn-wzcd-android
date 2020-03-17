package com.cdkj.wzcd.model;

/**
 * Created by cdkj on 2017/11/27.
 */

public class VersionModel {

    /**
     * note : v100版本更新说明
     * downloadUrl : http://m.bcoin.im/v100/app-release.apk
     * forceUpdate : 0 不强制更新
     * version : 1.0.0
     */

    private String note;
    private String downloadUrl;
    private String forceUpdate;
    private int version;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public String getForceUpdate() {
        return forceUpdate;
    }

    public void setForceUpdate(String forceUpdate) {
        this.forceUpdate = forceUpdate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
