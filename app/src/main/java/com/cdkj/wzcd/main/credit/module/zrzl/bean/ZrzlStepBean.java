package com.cdkj.wzcd.main.credit.module.zrzl.bean;

/**
 * @author : qianLei
 * @since : 2019/12/26 14:48
 */
public class ZrzlStepBean {

    private boolean selected;

    private String code;
    private String name;
    private boolean result;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

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

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public ZrzlStepBean(boolean selected, String code, String name) {
        this.selected = selected;
        this.code = code;
        this.name = name;
    }
}
