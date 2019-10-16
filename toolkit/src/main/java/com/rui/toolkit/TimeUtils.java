package com.rui.toolkit;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by linet on 16/1/27.
 * 修改于:2017/04/14
 */
public class TimeUtils {
    /**
     * 命名规则:
     * 2017-04-14 format_yyyy_MM_dd     默认分隔符 -
     * 20170414 format_yyyy_MM_dd0      0代表没有分隔符
     * 2017年04月14日 format_yyyy_MM_dd1   1代码分隔符 年月日
     * 2017/04/14 format_yyyy_MM_dd1        2代表分隔符 /
     * <p>
     * 所有日期格式分情况是否需要设置 北京时区
     * 所有日期格式分情况是否需要以后台为基准时间
     * <p>
     * 所有加注释的都是常用方法并放在最前面
     */

    public static SimpleDateFormat format_yyyy_MM_dd_HH_mm_ss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static String text_yyyy_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";

    public static SimpleDateFormat format_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");
    public static String text_yyyy_MM_dd = "yyyy-MM-dd";

    public static SimpleDateFormat format_yyyy_MM_dd0 = new SimpleDateFormat("yyyyMMdd");

    public static SimpleDateFormat format_yyyy_MM_dd1 = new SimpleDateFormat("yyyy年MM月dd日");
    public static String text_yyyy_MM_dd1 = "yyyy年MM月dd日";

    public static SimpleDateFormat format_yy_MM_dd1 = new SimpleDateFormat("yy年MM月dd日");

    public static SimpleDateFormat format_yyyy_MM1 = new SimpleDateFormat("yyyy年MM月");
    public static String text_yyyy_MM1 = "yyyy年MM月";

    public static SimpleDateFormat format_yyyy_MM = new SimpleDateFormat("yyyy-MM");
    public static String text_yyyy_MM = "yyyy-MM";

    public static SimpleDateFormat format_yy_MM1 = new SimpleDateFormat("yy年MM月");

    public static SimpleDateFormat format_dd1 = new SimpleDateFormat("dd日");
    public static String text_dd1 = "dd日";

    public static SimpleDateFormat format_MM_dd1 = new SimpleDateFormat("MM月dd日");
    public static String text_MM_dd1 = "MM月dd日";

    public static final TimeZone mTimeZone = TimeZone.getTimeZone("GMT+08:00");

    /**
     * 所有解析时间格式异常全丢在这里
     *
     * @param strDate 时间格式的字符串 例如:2017年04月14日
     * @param format  yyyy年MM月dd日
     * @return Date
     */
    public static Date getDate(String strDate, SimpleDateFormat format) {
        try {
            return format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    /**
     * @param strDate 时间格式的字符串 例如:2017年04月14日
     * @param format  yyyy年MM月dd日
     * @return Calendar
     */
    public static Calendar getCalendar(String strDate, SimpleDateFormat format) {
        Date date = getDate(strDate, format);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * @param date      new Date(1492133240003)
     * @param formatStr 时间格式的字符串 例如:yyyy年MM月dd日
     * @return 例如:2017年04月14日
     */
    public static String getDateFormatter(Date date, String formatStr) {
        SimpleDateFormat format = new SimpleDateFormat(formatStr);
        format.setTimeZone(mTimeZone);//设置北京时区
        String time = format.format(date);
        if (TextUtils.isEmpty(time))
            return "";
        return time;
    }

    /**
     * 转换日期格式
     * 将格式formatStr1转换为formatStr2
     *
     * @param date
     * @return
     */
    public static String switchDateFormatter(String date, String formatStr1, String formatStr2) {
        if (TextUtils.isEmpty(date))
            return "";
        SimpleDateFormat format1=new SimpleDateFormat(formatStr1);
        format1.setTimeZone(mTimeZone);//设置北京时区

        return getDateFormatter(getDate(date,format1), formatStr2);
    }


    /**
     * @param cal
     * @return 返回一个当天中 时分秒毫秒置零的日历
     */
    public static Calendar getTimeOfZero(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
//        cal.add(Calendar.DAY_OF_MONTH, 1);
        return cal;
    }


    /**
     * 获取当前月份第一天的开始的cal
     *
     * @param cal
     * @return 返回一个当天中 日时分秒毫秒置零的日历
     */
    public static Calendar getMonthTimeOfZero(Calendar cal) {
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal;
    }

    /**
     * @param date 2017-04-14 08:30:35
     * @return 04月14日
     */
    public static String get_MMddS(String date) {
        Calendar calendar = getCalendar(date, format_yyyy_MM_dd_HH_mm_ss);
        return format_MM_dd1.format(calendar.getTime());
    }


    //获得yyyy-MM-dd 00:00:00的字符串
    public static String getDateTimeStart(Calendar mStartDate) {
        return new StringBuilder(getDateFormatter(mStartDate.getTime(), "yyyy-MM-dd HH:mm:ss")).replace(11, 19, "00:00:00").toString();
    }


    //获得yyyy-MM-dd 23:59:59的字符串
    public static String getDateTimeEnd(Calendar mEndDate) {
        return new StringBuilder(getDateFormatter(mEndDate.getTime(), "yyyy-MM-dd HH:mm:ss")).replace(11, 19, "23:59:59").toString();
    }


    /**
     * 对比时间
     *
     * @param date
     * @return
     */
    public static String compareCurrentTime(Date date) {
        return compareTwoTime(date, Calendar.getInstance().getTime());
    }

    public static String compareTwoTime(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        try {
            Date start = format_yyyy_MM_dd0.parse(format_yyyy_MM_dd0.format(startDate));
            Date end = format_yyyy_MM_dd0.parse(format_yyyy_MM_dd0.format(endDate));
            int hour = getHourOfDay(startDate);
            String apm = hour > 12 ? "下午" : "上午";

            long timeLong = end.getTime() - start.getTime();
            if (timeLong == 0) {
                return getDateFormatter(startDate, String.format("%s hh:mm", apm));
            } else if (timeLong == 24 * 60 * 60 * 1000) {
                return getDateFormatter(startDate, String.format("昨天 %s hh:mm", apm));
            } else {
                int startY = getYear(startDate);
                int endY = getYear(endDate);

                String dateF = startY == endY ? "MM月dd日" : "yyyy年MM月dd日";
                return getDateFormatter(startDate, String.format("%s %s hh:mm", dateF, apm));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String compareCurrentTime_A(Date date) {
        return compareTwoTime_A(date, Calendar.getInstance().getTime());
    }

    public static String compareTwoTime_A(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        try {
            Date start = format_yyyy_MM_dd0.parse(format_yyyy_MM_dd0.format(startDate));
            Date end = format_yyyy_MM_dd0.parse(format_yyyy_MM_dd0.format(endDate));
            int hour = getHourOfDay(startDate);
            String apm = hour > 12 ? "下午" : "上午";

            long timeLong = end.getTime() - start.getTime();
            if (timeLong == 0) {
                return getDateFormatter(startDate, String.format("%s hh:mm", apm));
            } else if (timeLong == 24 * 60 * 60 * 1000) {
                return getDateFormatter(startDate, String.format("昨天 %s hh:mm", apm));
            } else {
                return getDateFormatter(startDate, String.format("yyyy年MM月dd日 %s hh:mm", apm));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static String compareCurrentTime_D(Date date) {
        return compareTwoTime_D(date, Calendar.getInstance().getTime());
    }

    public static String compareTwoTime_D(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        try {
            Date start = format_yyyy_MM_dd0.parse(format_yyyy_MM_dd0.format(startDate));
            Date end = format_yyyy_MM_dd0.parse(format_yyyy_MM_dd0.format(endDate));

            long timeLong = end.getTime() - start.getTime();
            if (timeLong < 0) {
                return "明天";
            } else if (timeLong == 0) {
                return "今天";
            } else if (timeLong == 24 * 60 * 60 * 1000) {
                return "昨天";
            } else {
                return getDateFormatter(startDate, "yyyy年MM月dd日");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public static String compareTwoTime_DA(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        try {
            Date start = format_yyyy_MM_dd0.parse(format_yyyy_MM_dd0.format(startDate));
            Date end = format_yyyy_MM_dd0.parse(format_yyyy_MM_dd0.format(endDate));

            long timeLong = end.getTime() - start.getTime();
            if (timeLong < 0) {
                return "明日";
            } else if (timeLong == 0) {
                return "今日";
            } else if (timeLong == 24 * 60 * 60 * 1000) {
                return "昨日";
            } else {
                return getDateFormatter(startDate, "yyyy年MM月dd日");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }


    public static int getYear(Date date) {
        return get(date, Calendar.YEAR);
    }


    public static int getMonth(Date date) {
        return get(date, Calendar.MONTH);
    }


    public static int getDate(Date date) {
        return get(date, Calendar.DATE);
    }

    public static int getAMofPM(Date date) {
        return get(date, Calendar.AM_PM);
    }

    public static int getHourOfDay(Date date) {
        return get(date, Calendar.HOUR_OF_DAY);
    }

    private static int get(Date date, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(field);
    }


    public static String getWeekday(Date date) {
        String dayNames[] = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return dayNames[dayOfWeekday(date) - 1];
    }

    public static String getWeekdayNew(Date date) {
        String dayNames[] = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        return dayNames[dayOfWeekday(date) - 1];
    }

    public static int dayOfWeekday(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }


    public static Calendar getLastDayOfMonth(Date sDate1) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(sDate1);
        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date lastDate = calendar.getTime();
        lastDate.setDate(lastDay);
        calendar.setTime(lastDate);
        return calendar;
    }

    /**
     * 获取某年的某周开始和结束时间
     *
     * @param year
     * @param weekindex
     * @return
     */
    public static String[] getDayOfWeeks(int year, int weekindex, int dayindex) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy年MM月dd日");
        Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(Calendar.MONDAY);
//        c.setWeekDate(year, weekindex, 1);
//        c.set(Calendar.YEAR, year);
//        c.set(Calendar.WEEK_OF_YEAR, weekindex);
//        c.set(Calendar.DAY_OF_WEEK, 2);
        c.add(Calendar.YEAR, year);
        c.add(Calendar.WEEK_OF_YEAR, weekindex);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 2;
        c.add(Calendar.DATE, -dayOfWeek); // 得到本周的第一天
        String begin = sdf.format(c.getTime());
        c.add(Calendar.DATE, dayindex); // 得到本周的今天，要得到最后一天需要令dayindex = 6即可
        String end = sdf.format(c.getTime());
        String[] range = new String[2];
        range[0] = begin;
        range[1] = end;
        return range;
    }

//--------

    /**
     * @param time 2010-10-10
     * @return 指定日期所属那一周的起始日期
     */
    public static String getWeekBegin(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }

        Calendar calendar = getCalendar(time, format_yyyy_MM_dd);
        int i = calendar.get(Calendar.DAY_OF_WEEK);//如果是星期天,先减去一周
        if (i == 1)
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.set(Calendar.DAY_OF_WEEK, 2);//一个星期的星期一
        String weekBegin = getDateFormatter(calendar.getTime(), "yyyy-MM-dd");

        return weekBegin;
    }

    /**
     * @param time 2010-10-10
     * @return 指定日期所属那一周的结束日期
     */
    public static String getWeekEnd(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }

        Calendar calendar = getCalendar(time, format_yyyy_MM_dd);

        int i = calendar.get(Calendar.DAY_OF_WEEK);//如果是星期天,先减去一周
        if (i == 1)
            calendar.add(Calendar.WEEK_OF_YEAR, -1);
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.set(Calendar.DAY_OF_WEEK, 1);//一个星期的星期日
        String weekEnd = getDateFormatter(calendar.getTime(), "yyyy-MM-dd");

        return weekEnd;
    }

    /**
     * @param time 2010-10-10
     * @return 指定日期所属那一月的开始日期
     */
    public static String getMonthBegin(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }

        Calendar calendar = getCalendar(time, format_yyyy_MM_dd);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String monthBegin = getDateFormatter(calendar.getTime(), "yyyy-MM-dd");

        return monthBegin;
    }

    /**
     * @param time 2010年10月
     * @return 指定日期所属那一月的开始日期 返回格式:2010-10-10
     */
    public static String getMonthBegin2(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }

        Calendar calendar = getCalendar(time, format_yyyy_MM1);

        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String monthBegin = getDateFormatter(calendar.getTime(), "yyyy-MM-dd");

        return monthBegin;
    }

    /**
     * @param time 2010-10-10
     * @return 指定日期所属那一月的结束日期
     */
    public static String getMonthEnd(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }

        Calendar calendar = getCalendar(time, format_yyyy_MM_dd);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String monthEnd = getDateFormatter(calendar.getTime(), "yyyy-MM-dd");

        return monthEnd;
    }

    /**
     * @param time 2010年10月
     * @return 指定日期所属那一月的结束日期 返回格式:2010-10-10
     */
    public static String getMonthEnd2(String time) {
        if (TextUtils.isEmpty(time)) {
            return null;
        }

        Calendar calendar = getCalendar(time, format_yyyy_MM1);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String monthEnd = getDateFormatter(calendar.getTime(), "yyyy-MM-dd");

        return monthEnd;
    }

    /**
     * 给定的两个时间是否属于同一个周
     *
     * @param oneTime 格式:2010-10-10
     * @param twoTime 格式:2010-10-10
     * @return
     */
    public static boolean equelWeekTime(String oneTime, String twoTime) {
        if (TextUtils.isEmpty(oneTime) || TextUtils.isEmpty(twoTime))
            return false;

        if (getWeekBegin(oneTime).equals(getWeekBegin(twoTime)))
            return true;


        return false;
    }

    /**
     * 给定的两个时间是否属于同一个月
     *
     * @param oneTime
     * @param twoTime
     * @return
     */
    public static boolean equelMonthTime(String oneTime, String twoTime) {
        if (TextUtils.isEmpty(oneTime) || TextUtils.isEmpty(twoTime))
            return false;

        if (getMonthBegin(oneTime).equals(getMonthBegin(twoTime)))
            return true;

        return false;
    }


    /**
     * 给定的时间是否小于当前时间的凌晨0点
     *
     * @param time 格式2010-10-10
     * @return 小于:true(也就是过期了) 不小于:false
     */
    public static boolean isTimeOut(String time) {
        if (TextUtils.isEmpty(time))
            return true;

        Calendar calendar1 = getCalendar(time, format_yyyy_MM_dd);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_YEAR, -1);//将当前时间前推一天,
        int i = calendar1.compareTo(calendar2);
        if (i == -1)
            return true;

        return false;
    }


    /**
     * 判断给定时间是否是历史日期
     *
     * @param time 2016年10月
     * @return 不是:fasle 是:true
     */
    public static boolean isOutDate(String time) {

        //给定时间
        Calendar calendar1 = getCalendar(time, format_yyyy_MM1);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH,-1);

        int i = calendar1.compareTo(calendar);//判定值 相等:0 calendar1大:1 calendar1小:-1

        if (i < 0)
            return true;
        return false;
    }

    //-----

    /**
     * 获得这个月有多少天
     *
     * @param str yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static int getDayOfMonth(String str) {
        Calendar aCalendar = TimeUtils.getCalendar(str, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    /**
     * 获得这是几月
     *
     * @param str yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static int getMonthOfYear(String str) {
        Calendar aCalendar = TimeUtils.getCalendar(str, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String str2 = TimeUtils.getDateFormatter(aCalendar.getTime(), "MM");
        return Integer.parseInt(str2);
    }

}
