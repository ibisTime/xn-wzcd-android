package com.cdkj.wzcd.custom.util;

import android.view.View;
import android.widget.LinearLayout;

import com.cdkj.wzcd.custom.BaseDateLayout;
import com.cdkj.wzcd.custom.BaseEditLayout;
import com.cdkj.wzcd.custom.BaseImageLayout;
import com.cdkj.wzcd.custom.BaseRemarkLayout;
import com.cdkj.wzcd.custom.BaseSelectLayout;
import com.cdkj.wzcd.main.credit.module.zrzl.bean.ZrzlBean;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : qianLei
 * @since : 2019/12/31 15:48
 */
public class BaseViewUtil {

    public static Map<String, String> buildMap(LinearLayout parent) {

        Map<String, String> map = new LinkedHashMap<>();

        int count = parent.getChildCount();

        View childView;
        for (int i = 0; i < count; i++) {
            childView = parent.getChildAt(i);

            if (childView instanceof BaseSelectLayout) {

                map.putAll(((BaseSelectLayout) childView).getMap());

            }

            if (childView instanceof BaseDateLayout) {

                map.putAll(((BaseDateLayout) childView).getMap());

            }

            if (childView instanceof BaseEditLayout) {

                map.putAll(((BaseEditLayout) childView).getMap());

            }

            if (childView instanceof BaseImageLayout) {

                map.putAll(((BaseImageLayout) childView).getMap());

            }

            if (childView instanceof BaseRemarkLayout) {

                map.putAll(((BaseRemarkLayout) childView).getMap());

            }

        }

        return map;

    }

    /**
     * 返回true代表检查通过
     * @param parent
     * @return
     */
    public static boolean check(LinearLayout parent) {

        int count = parent.getChildCount();

        View childView;
        for (int i = 0; i < count; i++) {
            childView = parent.getChildAt(i);

            if (childView instanceof BaseSelectLayout) {

                if (((BaseSelectLayout) childView).check()) {
                    return false;
                }

            }

            if (childView instanceof BaseEditLayout) {

                if (((BaseEditLayout) childView).check()) {
                    return false;
                }

            }

            if (childView instanceof BaseImageLayout) {

                if (((BaseImageLayout) childView).check()) {
                    return false;
                }

            }
        }

        return true;
    }

    public static void setData(LinearLayout parent, Object cls) {

        if (cls instanceof ZrzlBean) {

        }

        ZrzlBean bean = (ZrzlBean) cls;
        Field[] fields = bean.getClass().getDeclaredFields();

        int count = parent.getChildCount();

        View childView;
        for (int i = 0; i < count; i++) {
            childView = parent.getChildAt(i);

            if (childView instanceof BaseSelectLayout) {

                for (Field field : fields) {

                    if (((BaseSelectLayout) childView).getField().equals(field.getName())) {

                        try {
                            ((BaseSelectLayout) childView)
                                    .setContentByKey((String) field.get(bean));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        break;
                    }

                }


            }

            if (childView instanceof BaseEditLayout) {

                for (Field field : fields) {

                    if (((BaseEditLayout) childView).getField().equals(field.getName())) {

                        try {
                            ((BaseEditLayout) childView).setText((String) field.get(bean));
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }

                        break;
                    }

                }

            }

            if (childView instanceof BaseImageLayout) {

            }
        }

    }


    public static void setUnFocusable(LinearLayout parent) {

        int count = parent.getChildCount();

        View childView;
        for (int i = 0; i < count; i++) {
            childView = parent.getChildAt(i);

            if (childView instanceof BaseSelectLayout) {

                ((BaseSelectLayout) childView).setOnClickEnable(false);

            }

            if (childView instanceof BaseDateLayout) {

                ((BaseDateLayout) childView).setOnClickEnable(false);

            }

            if (childView instanceof BaseEditLayout) {

                ((BaseEditLayout) childView).setFocusable(false);

            }

            if (childView instanceof BaseImageLayout) {

                ((BaseImageLayout) childView).setOnClickEnable(false);

            }

            if (childView instanceof BaseRemarkLayout) {

                ((BaseRemarkLayout) childView).setFocusable(false);

            }

        }


    }

}
