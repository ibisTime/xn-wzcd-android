package com.cdkj.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.ImageView;

import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.utils.glidetransforms.GlideCircleBorderTransform;
import com.cdkj.baselibrary.utils.glidetransforms.GlideCircleTransform;

/**
 * 图片加载工具类
 * Created by Administrator on 2016-09-14.
 */
public class ImgUtils {

    public static void loadQiniuImg(Object obj, String imgid, ImageView img) {
        loadImg(obj, MyCdConfig.QINIU_URL + imgid, img);
    }

    public static void loadQiniuLogo(Object obj, String imgid, ImageView img) {
        loadLogo(obj, MyCdConfig.QINIU_URL + imgid, img);
    }


    public static void loadImg(Object obj, String imgid, ImageView img) {
        if (!isHaveHttp(imgid)) {

            //如果没有http头 则加上七牛头
            imgid = MyCdConfig.QINIU_URL + imgid;

        }
        if (obj instanceof Activity) {

            if (!AppUtils.isActivityExist((Activity) obj)) {

                LogUtil.E("图片加载界面销毁");
                return;
            }
            if (obj == null || img == null) {
                return;
            }
            try {
                GlideApp.with((Activity) obj).load(imgid).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(img);
            } catch (Exception e) {
                LogUtil.E("图片加载错误");
            }

        } else if (obj instanceof Fragment) {
            try {
                GlideApp.with((Fragment) obj).load(imgid).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(img);
            } catch (Exception e) {
                LogUtil.E("图片加载错误");
            }
        } else if (obj instanceof Context) {
            try {
                GlideApp.with((Context) obj).load(imgid).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(img);
            } catch (Exception e) {
                LogUtil.E("图片加载错误");
            }
        }
    }




    public static void loadLogo(Object obj, Object imgId, ImageView img) {

        if (imgId instanceof Integer || imgId instanceof String) {

            if (obj instanceof Activity) {

                if (!AppUtils.isActivityExist((Activity) obj)) {

                    LogUtil.E("图片加载界面销毁");
                    return;
                }
                if (img == null) {
                    return;
                }
                try {
                    GlideApp.with((Activity) obj).load(imgId).placeholder(R.drawable.photo_default).error(R.drawable.photo_default).transform(new GlideCircleTransform(((Activity) obj))).into(img);
                } catch (Exception e) {
                    LogUtil.E("图片加载错误");
                }

            } else if (obj instanceof Fragment) {
                try {
                    GlideApp.with((Fragment) obj).load(imgId).placeholder(R.drawable.photo_default).error(R.drawable.photo_default).transform(new GlideCircleTransform(((Fragment) obj).getContext())).into(img);
                } catch (Exception e) {
                    LogUtil.E("图片加载错误");
                }
            } else if (obj instanceof Context) {
                try {
                    GlideApp.with((Context) obj).load(imgId).placeholder(R.drawable.photo_default).error(R.drawable.photo_default).transform(new GlideCircleTransform(((Context) obj))).into(img);
                } catch (Exception e) {
                    LogUtil.E("图片加载错误");
                }
            }
        }

    }

    public static void loadQiNiuBorderLogo(Context context, String url, ImageView imageView, @ColorRes int borderColor) {
        try {
            /*.skipMemoryCache(true)   .diskCacheStrategy(DiskCacheStrategy.NONE)*/
            GlideApp.with(context).load(MyCdConfig.QINIU_URL + url).error(R.drawable.photo_default).transform(new GlideCircleBorderTransform(context, 2, ContextCompat.getColor(context, borderColor))).into(imageView);

        } catch (Exception e) {

        }
    }

    /**
     * 用于判断链接是否添加了http
     *
     * @param url
     * @return
     */
    public static boolean isHaveHttp(String url) {
        if (TextUtils.isEmpty(url)) return false;
        return url.indexOf("http") != -1;
    }


}
