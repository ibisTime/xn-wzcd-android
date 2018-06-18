package com.cdkj.baselibrary.interfaces;

/**
 * 发布信息状态是否包含敏感字符
 * Created by cdkj on 2018/3/27.
 */

public interface CheckInfoReleaseStateListener {

    void succ(); //发布成功

    void fail();//发布失败

    void haveSensitive(String tipsInfo);//包含敏感字符

}
