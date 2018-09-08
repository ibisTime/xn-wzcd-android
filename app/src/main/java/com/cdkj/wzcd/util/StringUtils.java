package com.cdkj.wzcd.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author qi
 * @updateDts 2018/9/5
 */

public class StringUtils {

    public static List<String> splitPIC(String pic) {
        if (TextUtils.isEmpty(pic)) {
            return null;
        }
        List<String> list = new ArrayList<>();
        if (pic.contains("||")) {
            String[] split = pic.split("\\|\\|");
            list = Arrays.asList(split);
            return list;
        } else {
            list.add(pic);
            return list;
        }
    }
}
