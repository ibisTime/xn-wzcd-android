package com.cdkj.wzcd.model;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import org.litepal.crud.DataSupport;

/**
 * @updateDts 2019/3/1
 */
public class NavigationBean {

    /**
     * code : DH201810120023250100000
     * name : 投资
     * type : app_menu
     * url : 123456
     * pic : 20190228185324.png
     * enPic : 20190228185347.png
     * status : 1
     * location : app
     * orderNo : 0
     * parentCode : DH201810120023250000000
     * remark :
     */

    private boolean isMainSelect = false;

    private String name;
    private Integer pic;
    private Integer darkPic;

    public boolean isMainSelect() {
        return isMainSelect;
    }

    public void setMainSelect(boolean mainSelect) {
        isMainSelect = mainSelect;
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

    public Integer getDarkPic() {
        return darkPic;
    }

    public void setDarkPic(Integer darkPic) {
        this.darkPic = darkPic;
    }
}
