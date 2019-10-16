package com.rui.toolkit;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @desc：计算金钱的工具类，提供加减乘除的精确计算
 */
public class CurrencyUtils {

    /**
     * 判断valA是否大于valB，如果valA大于valB，那么返回true，否则返回false
     *
     * @param valA
     * @param valB
     * @return
     * @author wangjc
     * @date 2014-12-8
     */
    public static boolean greaterThan(BigDecimal valA, BigDecimal valB) {
        return (valA.compareTo(valB) > 0);
    }

    /**
     * 判断valA和valB的值是否相等，如果valA和valB的值是否相等，那么返回true，否则返回false
     *
     * @param valA
     * @param valB
     * @return
     * @author wangjc
     * @date 2014-12-8
     */
    public static boolean equals(BigDecimal valA, BigDecimal valB) {
        return (valA.compareTo(valB) == 0);
    }

    /**
     * 用于货币计算的加法，返回结果默认精确到小数点后两位，舍入模式：四舍五入
     *
     * @param valA
     * @param valB
     * @return （valA + valB）的结果
     * @author wangjc
     * @date 2014-12-10
     */
    public static BigDecimal add(BigDecimal valA, BigDecimal valB) {
        return valA.add(valB).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的加法，返回结果的舍入模式：四舍五入
     *
     * @param valA
     * @param valB
     * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
     * @return （valA + valB）的结果
     * @author wangjc
     * @date 2014-12-10
     */
    public static BigDecimal add(BigDecimal valA, BigDecimal valB, int scale) {
        return valA.add(valB).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的减法，返回结果默认精确到小数点后两位
     *
     * @param valA
     * @param valB
     * @return （valA - valB）的结果
     * @author wangjc
     * @date 2014-12-10
     */
    public static BigDecimal minus(BigDecimal valA, BigDecimal valB) {
        return valA.add(valB.negate()).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的减法，返回结果的舍入模式：四舍五入
     *
     * @param valA
     * @param valB
     * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
     * @return （valA - valB）的结果
     * @author wangjc
     * @date 2014-12-10
     */
    public static BigDecimal minus(BigDecimal valA, BigDecimal valB, int scale) {
        return valA.add(valB.negate()).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的乘法，返回结果默认精确到小数点后两位
     *
     * @param valA
     * @param valB
     * @return （valA * valB）的结果
     * @author wangjc
     * @date 2014-12-10
     */
    public static BigDecimal multiply(BigDecimal valA, BigDecimal valB) {
        return valA.multiply(valB).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的乘法，返回结果的舍入模式：四舍五入
     *
     * @param valA
     * @param valB
     * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
     * @return （valA * valB）的结果
     * @author wangjc
     * @date 2014-12-10
     */
    public static BigDecimal multiply(BigDecimal valA, BigDecimal valB, int scale) {
        return valA.multiply(valB).setScale(scale, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的除法，返回结果默认精确到小数点后两位
     *
     * @param valA 被除数
     * @param valB 除数
     * @return （valA / valB）的结果
     * @author wangjc
     * @date 2014-12-10
     */
    public static BigDecimal divide(BigDecimal valA, BigDecimal valB) {
        if (BigDecimal.ZERO.compareTo(valB) == 0) {
            throw new ArithmeticException("除数不能为0");
        }
        return valA.divide(valB, 2, RoundingMode.HALF_UP);
    }

    /**
     * 用于货币计算的除法，返回结果的舍入模式：四舍五入
     *
     * @param valA  被除数
     * @param valB  除数
     * @param scale 返回结果的精确度，设置返回结果精确到小数点后几位
     * @return （valA / valB）的结果
     * @author wangjc
     * @date 2014-12-10
     */
    public static BigDecimal divide(BigDecimal valA, BigDecimal valB, int scale) {
        if (BigDecimal.ZERO.compareTo(valB) == 0) {
            throw new ArithmeticException("除数不能为0");
        }
        return valA.divide(valB, scale, RoundingMode.HALF_UP);
    }

    /**
     * 将指定的值转换为BigDecimal对象，如果val为null或者为空，那么默认返回0
     *
     * @param val
     * @return
     * @author wjc
     * @date 2017年1月17日
     */
    public static BigDecimal toBigDecimal(String val) {
        if (val == null || "".equals(val.trim())) {
            return BigDecimal.ZERO;
        } else {
            return new BigDecimal(val);
        }
    }

    /**
     * Company: YunBeiTeac
     * Copyright: Copyright (c) 2017-2018
     *
     * @author Created by YangTianKun at 2018/4/12/012 and 11:28
     * @Email 245086168@qq.com
     * describe:使用Bigdecimal保留2位小数  用于设定金钱方面的显示
     */
    public static String format2(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.toString();
    }

    /**
     * 用于经纬度计算的加法，返回结果默认精确到小数点后6位，舍入模式：四舍五入
     *
     * @param valA
     * @param valB
     * @return （valA + valB）的结果
     * @author wangjc
     * @date 2014-12-10
     */
    public static BigDecimal addLatAndLng(BigDecimal valA, BigDecimal valB) {
        return valA.add(valB).setScale(6, RoundingMode.HALF_UP);
    }

    /**
     * 用于经纬度计算的减法，返回结果默认精确到小数点后6位
     *
     * @param valA
     * @param valB
     * @return （valA - valB）的结果
     * @author wangjc
     * @date 2014-12-10
     */
    public static BigDecimal minusLatAndLng(BigDecimal valA, BigDecimal valB) {
        return valA.add(valB.negate()).setScale(6, RoundingMode.HALF_UP);
    }

}


