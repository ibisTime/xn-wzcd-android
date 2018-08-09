package com.cdkj.wzcd.model;

import java.util.List;

/**
 * @author qi
 * @updateDts 2018/8/8
 * 收件bean
 */

public class CollectionBean {


    /**
     * type : GA
     * codeList : ["GA201808081316302748390","GA201808081313571951674"]
     */

    private String type;
    private List<String> codeList;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getCodeList() {
        return codeList;
    }

    public void setCodeList(List<String> codeList) {
        this.codeList = codeList;
    }
}
