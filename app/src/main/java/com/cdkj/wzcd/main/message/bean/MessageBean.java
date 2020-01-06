package com.cdkj.wzcd.main.message.bean;

import java.io.Serializable;

/**
 * @author : qianLei
 * @since : 2019/12/25 20:40
 */
public class MessageBean implements Serializable {


    /**
     * code : 201909112344209418213
     * title :
     * type :
     * urgentStatus :
     * publishDepartmentCode :
     * content :
     * publishDatetime :
     * enclosure :
     * status :
     * publishDepartmentName :
     */

    private String code;
    private String title;
    private String type;
    private String urgentStatus;
    private String publishDepartmentCode;
    private String content;
    private String publishDatetime;
    private String updateDatetime;
    private String enclosure;
    private String status;
    private String publishDepartmentName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrgentStatus() {
        return urgentStatus;
    }

    public void setUrgentStatus(String urgentStatus) {
        this.urgentStatus = urgentStatus;
    }

    public String getPublishDepartmentCode() {
        return publishDepartmentCode;
    }

    public void setPublishDepartmentCode(String publishDepartmentCode) {
        this.publishDepartmentCode = publishDepartmentCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDatetime() {
        return publishDatetime;
    }

    public void setPublishDatetime(String publishDatetime) {
        this.publishDatetime = publishDatetime;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublishDepartmentName() {
        return publishDepartmentName;
    }

    public void setPublishDepartmentName(String publishDepartmentName) {
        this.publishDepartmentName = publishDepartmentName;
    }
}
