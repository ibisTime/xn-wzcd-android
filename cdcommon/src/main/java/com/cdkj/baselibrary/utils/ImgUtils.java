package com.cdkj.baselibrary.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cdkj.baselibrary.R;
import com.cdkj.baselibrary.appmanager.MyCdConfig;
import com.cdkj.baselibrary.utils.glidetransforms.GlideCircleTransform;

/**
 * 图片加载工具类
 * Created by Administrator on 2016-09-14.
 */
public class ImgUtils {

    public static void loadQiniuImg(Object obj, String imgid, ImageView img) {
        loadMarketImg(obj, MyCdConfig.QINIU_URL + imgid, img);
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
                Glide.with((Activity) obj).load(imgid).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(img);
            } catch (Exception e) {
                LogUtil.E("图片加载错误");
            }

        } else if (obj instanceof Fragment) {
            try {
                Glide.with((Fragment) obj).load(imgid).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(img);
            } catch (Exception e) {
                LogUtil.E("图片加载错误");
            }
        } else if (obj instanceof Context) {
            try {
                Glide.with((Context) obj).load(imgid).placeholder(R.drawable.default_pic).error(R.drawable.default_pic).into(img);
            } catch (Exception e) {
                LogUtil.E("图片加载错误");
            }
        }
    }

    public static void loadMarketImg(Object obj, String imgid, ImageView img) {
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
                Glide.with((Activity) obj)
                        .load(imgid)
                        .placeholder(R.drawable.market_symbol_default_icon)
                        .error(R.drawable.market_symbol_default_icon)
                        .into(img);
            } catch (Exception e) {
                LogUtil.E("图片加载错误");
            }

        } else if (obj instanceof Fragment) {
            try {
                Glide.with((Fragment) obj)
                        .load(imgid)
                        .placeholder(R.drawable.market_symbol_default_icon)
                        .error(R.drawable.market_symbol_default_icon)
                        .into(img);
            } catch (Exception e) {
                LogUtil.E("图片加载错误");
            }
        } else if (obj instanceof Context) {
            try {
                Glide.with((Context) obj)
                        .load(imgid)
                        .placeholder(R.drawable.market_symbol_default_icon)
                        .error(R.drawable.market_symbol_default_icon)
                        .into(img);
            } catch (Exception e) {
                LogUtil.E("图片加载错误");
            }
        }
    }



    public static void loadLogo(Object obj, Object imgid, ImageView img) {

        if (imgid instanceof Integer || imgid instanceof String) {

            if (obj instanceof Activity) {

                if (!AppUtils.isActivityExist((Activity) obj)) {

                    LogUtil.E("图片加载界面销毁");
                    return;
                }
                if (obj == null || img == null) {
                    return;
                }
                try {
                    Glide.with((Activity) obj).load(imgid).placeholder(R.drawable.photo_default).error(R.drawable.photo_default).transform(new GlideCircleTransform(((Activity) obj))).into(img);
                } catch (Exception e) {
                    LogUtil.E("图片加载错误");
                }

            } else if (obj instanceof Fragment) {
                try {
                    Glide.with((Fragment) obj).load(imgid).placeholder(R.drawable.photo_default).error(R.drawable.photo_default).transform(new GlideCircleTransform(((Fragment) obj).getContext())).into(img);
                } catch (Exception e) {
                    LogUtil.E("图片加载错误");
                }
            } else if (obj instanceof Context) {
                try {
                    Glide.with((Context) obj).load(imgid).placeholder(R.drawable.photo_default).error(R.drawable.photo_default).transform(new GlideCircleTransform(((Context) obj))).into(img);
                } catch (Exception e) {
                    LogUtil.E("图片加载错误");
                }
            }
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
