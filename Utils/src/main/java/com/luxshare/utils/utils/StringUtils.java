package com.luxshare.utils.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016/10/6.
 * 字符串相关的工具类
 */
public class StringUtils {

    private StringUtils() {
        throw new AssertionError();
    }


    /**
     * is null or its length is 0 or it is made by space
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {

        return (str == null || str.trim().length() == 0);
    }


    /**
     * is null or its length is 0
     * @param str
     * @return
     */
    public static boolean isEmpty(CharSequence str) {

        return (str == null || str.length() == 0);
    }


    /**
     * get length of String
     * @param str
     * @return
     */
    public static int getLength(String str) {

        return str == null ? 0 : str.length();
    }


    /**
     * null Object to empty string
     * @param str
     * @return
     */
    public static String nullStrToEmpty(Object str) {

        return (str == null ? "" : (str instanceof String ? ((String) str) : str.toString()));
    }


    /**
     * encoded in utf-8
     * @param str
     * @return
     */
    public static String utf8Encode(String str) {

        if (!isEmpty(str) && str.getBytes().length != str.length()) {

            try {
                return URLEncoder.encode(str,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("UnsupportedEncodingException occurred.",e);
            }
        }
        return str;
    }


    /**
     *
     * @param source
     * @return 返回htmL到字符串
     */
    public static String htmlEscapeCharsToString(String source) {

        return StringUtils.isEmpty(source) ? source : source.replaceAll("&lt;","<")
                                                            .replaceAll("&gt;",">")
                                                            .replaceAll("&amp;","&")
                                                            .replaceAll("&quot;","\"");
    }


    /**
     * 判断给定的字符串是否为null或者是空的
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {

        return string == null || "".equals(string.trim());
    }


    /**
     * 判断给定的字符串是否不为null且不为空
     * @param string
     * @return
     */
    public static boolean isNotEmpty(String string) {

        return !isEmpty(string);
    }


    /**
     * 如果字符串是null或者空就返回""
     * @param str
     * @return
     */
    public static String filterEmpty(String str) {

        return StringUtils.isNotEmpty(str) ? str : "";
    }
}
