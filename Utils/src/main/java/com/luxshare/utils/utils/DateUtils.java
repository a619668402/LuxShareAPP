package com.luxshare.utils.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/6.
 * 日期工具类
 */
public class DateUtils {

    /**
     * 英文简写：2016
     */
    public static String FORMAT_Y = "yyyy";

    /**
     * 英文简写：10:00
     */
    public static String FORMAT_HM = "HH:mm";

    /**
     * 英文简写：1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";

    /**
     * 英文简写：2016-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";

    /**
     * 英文简写：2016-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";

    /**
     * 英文简写：2016-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";


    /**
     * 中文简写：2016年12月01日
     */
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";

    /**
     * 中文简写：2016年12月01日 12时
     */
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";

    /**
     * 中文简写：2016年12月01日 12时12分
     */
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";

    /**
     * 中文简写：2016年12月01日 12时12分12秒
     */
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日 HH时mm分ss秒";


    public static Calendar calendar = null;

    // 默认日期格式
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";


    public static Date str2Date(String str) {

        return str2Date(str, null);
    }

    /**
     * 将字符串转换为date
     *
     * @param str    日期字符串
     * @param format 格式化的类型
     * @return date
     */
    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 将日期格式的字符串转换为Calendar
     *
     * @param str    日期字符串
     * @param format 格式化的类型
     * @return Calendar
     */
    public static Calendar str2Calendar(String str, String format) {

        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * 将日期格式的字符串转换为Calendar，日期格式为 yyyy-MM-dd HH:mm:ss
     *
     * @param str
     * @return Calendar
     */
    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);
    }


    /**
     * 将date格式化为字符串日期格式
     *
     * @param date
     * @param format 格式化的类型
     * @return 格式化后的日期
     */
    public static String date2Str(Date date, String format) {
        if (date == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(date);
        return s;
    }

    /**
     * 将Calendar转换为字符串日期
     *
     * @param calendar
     * @param format   格式化的类型
     * @return 格式化后的日期
     */
    public static String date2Str(Calendar calendar, String format) {
        if (calendar == null) {
            return null;
        }
        return date2Str(calendar.getTime(), format);
    }

    /**
     * 将Calendar转换为默认的日期格式 yyyy-MM-dd HH:mm:ss
     *
     * @param c
     * @return 格式化后的日期
     */
    public static String date2Str(Calendar c) {
        return date2Str(c, null);
    }


    /**
     * 将Calendar转换为默认的日期格式 yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return 格式化后的日期
     */
    public static String date2Str(Date date) {
        return date2Str(date, null);
    }


    /**
     * 获得当前日期的字符串格式
     *
     * @param format 格式化的类型
     * @return 返回格式化之后的时间
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }


    /**
     * 返回月份
     *
     * @param date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * 返回日
     *
     * @param date 日期
     * @return 返回月份中的日
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * 返回小时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    /**
     * 返回默认的日期格式
     *
     * @return
     */
    public static String getDefDatePattern() {
        return FORMAT;
    }


    /**
     * 使用用户格式提取字符串日期
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return 提取字符串日期
     */
    public static Date parseStr2Date(String strDate, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
