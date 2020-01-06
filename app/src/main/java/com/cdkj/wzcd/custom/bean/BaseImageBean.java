package com.cdkj.wzcd.custom.bean;

/**
 * @author : qianLei
 * @since : 2019/12/28 20:17
 */
public class BaseImageBean {

    private String title;
    private String field;
    private String pic;

    private boolean isDetail = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public boolean isDetail() {
        return isDetail;
    }

    public void setDetail(boolean detail) {
        isDetail = detail;
    }

    public BaseImageBean() {

    }

    public BaseImageBean(String title, String field) {
        this.title = title;
        this.field = field;
    }

    public BaseImageBean(String title, String field, String pic) {
        this.title = title;
        this.field = field;
        this.pic = pic;
    }
}
