package com.cdkj.wzcd.util;

import android.text.TextUtils;

import com.cdkj.baselibrary.utils.MoneyUtils;

import java.math.BigDecimal;

/**
 * Created by cdkj on 2018/5/31.
 */

public class RequestUtil {


    /**
     * 人民币金额乘以1000
     * @return
     */
    public static String formatAmountMul(String strAmount){
        if(TextUtils.isEmpty(strAmount))
            return "0.00";

        return new BigDecimal(strAmount).multiply(new BigDecimal(1000)).toPlainString();

    }

    /**
     * 人民币金额乘以1000
     * @return
     */
    public static String formatAmountMulSign(String strAmount){
        if(TextUtils.isEmpty(strAmount))
            return "0.00";

        return MoneyUtils.MONEYSING + new BigDecimal(strAmount).multiply(new BigDecimal(1000)).toPlainString();

    }

    /**
     * 人民币金额除以1000
     * @return
     */
    public static String formatAmountDiv(String strAmount){
        if(TextUtils.isEmpty(strAmount))
            return "0.00";

        BigDecimal amount = new BigDecimal(strAmount);

        if(amount.equals(new BigDecimal(0))){
            return "0.00";
        }

        return scale(amount.divide(new BigDecimal(1000)).toPlainString(), 2);
    }

    /**
     * 人民币金额除以1000
     * @return
     */
    public static String formatAmountDivSign(String strAmount){
        if(TextUtils.isEmpty(strAmount))
            return  MoneyUtils.MONEYSING+"0.00";

        BigDecimal amount = new BigDecimal(strAmount);

        if(amount.equals(new BigDecimal(0))){
            return  MoneyUtils.MONEYSING+"0.00";
        }

        return MoneyUtils.MONEYSING+scale(amount.divide(new BigDecimal(1000)).toPlainString(), 2);
    }


    /**
     * 格式化输出的金额格式
     * @param s
     * @return
     */
    public static String scale(String s, int scale){
        String amount[] = s.split("\\.");
        if (amount.length > 1){
            if (amount[1].length() > scale){
                return amount[0]+"."+amount[1].substring(0,scale);
            }else {
                return amount[0]+"."+amount[1];
            }
        }else {
            return amount[0];
        }
    }

}
