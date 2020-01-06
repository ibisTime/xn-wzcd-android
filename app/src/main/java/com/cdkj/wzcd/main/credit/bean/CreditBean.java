package com.cdkj.wzcd.main.credit.bean;

/**
 * @author : qianLei
 * @since : 2019/12/26 10:45
 */
public class CreditBean {

    private String code;
    private String name;
    private Integer pic;
    private Integer number;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPic() {
        return pic;
    }

    public void setPic(Integer pic) {
        this.pic = pic;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public CreditBean(String code, String name, Integer pic) {
        this.code = code;
        this.name = name;
        this.pic = pic;
    }
}
