package com.cdkj.baselibrary.interfaces;

import java.util.ArrayList;

/**
 * 启用相机相册监听
 * Created by cdkj on 2017/11/7.
 */

public interface CameraPhotoListener2 {

    void onPhotoSuccessful(int requestCode, ArrayList path);

    void onPhotoFailure(int requestCode, String msg);

    void noPermissions(int requestCode);//没有权限

}
