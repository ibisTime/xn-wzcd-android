package com.cdkj.wzcd.util;

import android.text.TextUtils;

import com.cdkj.baselibrary.utils.LogUtil;
import com.cdkj.baselibrary.utils.MoneyUtils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static java.math.BigDecimal.ROUND_HALF_DOWN;
import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Created by cdkj on 2018/5/31.
 */

public class RequestUtil {

    public static boolean isEmpty(String strAmount){
        if(TextUtils.isEmpty(strAmount))
            return true;

        if (new BigDecimal(strAmount).compareTo(BigDecimal.ZERO) == 0){ // 等于0
            return true;
        }

        return false;
    }

    public static String formatInt(double money){
        DecimalFormat df = new DecimalFormat("#######0.000");
        String showMoney = df.format(money);

        return showMoney.substring(0,showMoney.length()-1).split("\\.")[0];
    }

    /**
     * 人民币金额乘以1000
     * @return
     */
    public static String formatAmountMul(String strAmount){
        if(TextUtils.isEmpty(strAmount))
            return "0.00";

        return new BigDecimal(strAmount).multiply(new BigDecimal(1000)).toPlainString().split("\\.")[0];

    }

    /**
     * 人民币金额乘以1000
     * @return
     */
    public static String formatAmountMulSign(String strAmount){
        if(TextUtils.isEmpty(strAmount))
            return "0.00";

        return new BigDecimal(strAmount).multiply(new BigDecimal(1000)).toPlainString() + " " + MoneyUtils.MONEYSING;

    }

    /**
     * 人民币金额除以1000
     * @return
     */
    public static String formatAmountDiv(String strAmount){
        if(TextUtils.isEmpty(strAmount)||strAmount==null)
            return "0";

        BigDecimal amount = new BigDecimal(Double.parseDouble(strAmount));

        if(amount.equals(new BigDecimal(0))){
            return "0";
        }

        return scale(amount.divide(new BigDecimal(1000), ROUND_HALF_DOWN).toPlainString(), 2);
    }

    /**
     * 人民币金额除以1000
     * @return
     */
    public static String formatAmountDivSign(String strAmount){
        if(TextUtils.isEmpty(strAmount))
            return "";

        BigDecimal amount = new BigDecimal(Double.parseDouble(strAmount));

        if(amount.equals(new BigDecimal(0))){
            return "0.00" + " " + MoneyUtils.MONEYSING;
        }

        return scale(amount.divide(new BigDecimal(1000), ROUND_HALF_DOWN).toPlainString(), 2) + " " + MoneyUtils.MONEYSING;
    }


    /**
     * 人民币金额除以1000
     * @return
     */
    public static String formatAmountDivSign(BigDecimal amount){

        if(amount.equals(new BigDecimal(0))){
            return "0.00" + " " + MoneyUtils.MONEYSING;
        }

        return scale(amount.divide(new BigDecimal(1000), ROUND_HALF_DOWN).toPlainString(), 2) + " " + MoneyUtils.MONEYSING;
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

    /**
     * 提供精确加法计算的add方法
     * @param value1 被加数
     * @param value2 加数
     * @return 两个参数的和
     */
    public static String add(String value1, String value2){
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);

        LogUtil.E("b1"+b1.toPlainString());
        LogUtil.E("b2"+b2.toPlainString());
        LogUtil.E("add"+b1.add(b2).toPlainString());

        return scale(b1.add(b2).toPlainString(),2);
    }

    /**
     * 提供精确乘法运算的mul方法
     * @param value1 被乘数
     * @param value2 乘数
     * @return 两个参数的积
     */
    public static String mul(String value1, String value2){
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return scale(b1.multiply(b2).toPlainString(),2);
    }

    /**
     * 提供精确的除法运算方法div
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     * @throws IllegalAccessException
     */
    public static String div(String value1, String value2, int scale) throws IllegalAccessException {
        //如果精确范围小于0，抛出异常信息
        if(scale<0){
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);


        return scale(b1.divide(b2, scale, ROUND_HALF_DOWN).toPlainString(),scale);
    }
    /**
     * 提供精确的除法运算方法div
     * @param value1 被除数
     * @param value2 除数
     * @param scale 精确范围
     * @return 两个参数的商
     *
     * 结果四舍五入保留两位小数
     * @throws IllegalAccessException
     */
    public static String divUP(String value1, String value2, int scale) throws IllegalAccessException {
        //如果精确范围小于0，抛出异常信息
        if(scale<0){
            throw new IllegalAccessException("精确度不能小于0");
        }
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);


        return scale(b1.divide(b2, scale, ROUND_HALF_UP).toPlainString(),scale);
    }

}
