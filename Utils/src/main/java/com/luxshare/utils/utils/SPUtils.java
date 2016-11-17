package com.luxshare.utils.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/21.
 * SharedPreferences 相关操作的工具类
 */
public class SPUtils {

    public static String DEFAULT_STR_VALUE = "null";
    public static int DEFAULT_INT_VALUE = Integer.MIN_VALUE;
    public static boolean DEFAULT_BOOL_VALUE = Boolean.FALSE;
    public static float DEFAULT_FLOAT_VALUE = Float.MIN_VALUE;
    public static long DEFAULT_LONG_VALUE = Long.MIN_VALUE;

    private SPUtils() {
        throw new AssertionError();
    }


    public static SharedPreferences getSP(Context context, String name, int mode) {
        SharedPreferences sp = context.getSharedPreferences(name, mode);
        return sp;
    }

    /**
     * 保存多条数据
     *
     * @param context
     * @param name
     * @param map     保存数据的集合
     * @return
     */
    public static boolean putMap(Context context, String name, Map<String, Object> map) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        for (Map.Entry<String, Object> entry : map.entrySet()) {

            if (entry.getValue() instanceof String) {
                editor.putString(entry.getKey(), (String) entry.getValue());
            } else if (entry.getValue() instanceof Integer) {
                editor.putInt(entry.getKey(), (Integer) entry.getValue());
            } else if (entry.getValue() instanceof Boolean) {
                editor.putBoolean(entry.getKey(), (Boolean) entry.getValue());
            } else if (entry.getValue() instanceof Float) {
                editor.putFloat(entry.getKey(), (Float) entry.getValue());
            } else if (entry.getValue() instanceof Long) {
                editor.putLong(entry.getKey(), (Long) entry.getValue());
            }
        }
        return editor.commit();
    }

    /**
     * @param context
     * @param map
     * @return
     */
    public static boolean putMap(Context context, Map<String, Object> map) {
        return putMap(context, context.getPackageName(), map);
    }

    /**
     * 保存string类型数据
     *
     * @param context
     * @param key     存储的键
     * @param value   存储的值
     * @return
     */
    public static boolean putString(Context context, String name, String key, String value) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    public static boolean putString(Context context, String key, String value) {
        return putString(context, context.getPackageName(), key, value);
    }


    /**
     * 获取string类型值
     *
     * @param context  上下文对象
     * @param name
     * @param key
     * @param defValue 默认值
     * @return key对应的value
     */
    public static String getString(Context context, String name, String key, String defValue) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    public static String getString(Context context, String name, String key) {
        return getString(context, name, key, DEFAULT_STR_VALUE);
    }

    public static String getString(Context context, String key) {
        return getString(context, context.getPackageName(), key, DEFAULT_STR_VALUE);
    }


    /**
     * 保存int类型数据
     *
     * @param context
     * @param name
     * @param key
     * @param value
     * @return
     */
    public static boolean putInt(Context context, String name, String key, int value) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    public static boolean putInt(Context context, String key, int value) {
        return putInt(context, context.getPackageName(), key, value);
    }


    /**
     * 获取int类型值
     *
     * @param context  上下文对象
     * @param name
     * @param key
     * @param defValue 默认值
     * @return key对应的value
     */
    public static int getInt(Context context, String name, String key, int defValue) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    public static int getInt(Context context, String name, String key) {
        return getInt(context, name, key, DEFAULT_INT_VALUE);
    }

    public static int getInt(Context context, String key, int defValue) {
        return getInt(context, context.getPackageName(), key, defValue);
    }

    public static int getInt(Context context, String key) {
        return getInt(context, context.getPackageName(), key, DEFAULT_INT_VALUE);
    }


    /**
     * 保存boolean类型数据
     *
     * @param context
     * @param name
     * @param key
     * @param value
     * @return
     */
    public static boolean putBoolean(Context context, String name, String key, boolean value) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    public static boolean putBoolean(Context context, String key, boolean value) {
        return putBoolean(context, context.getPackageName(), key, value);
    }


    /**
     * 获取boolean类型值
     *
     * @param context  上下文对象
     * @param name
     * @param key
     * @param defValue 默认值
     * @return key对应的value
     */
    public static boolean getBoolean(Context context, String name, String key, boolean defValue) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    public static boolean getBoolean(Context context, String name, String key) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        return sp.getBoolean(key, DEFAULT_BOOL_VALUE);
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getBoolean(context, context.getPackageName(), key, defValue);
    }

    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, context.getPackageName(), key, DEFAULT_BOOL_VALUE);
    }


    /**
     * 保存float类型数据
     *
     * @param context
     * @param name
     * @param key
     * @param value
     * @return
     */
    public static boolean putFloat(Context context, String name, String key, float value) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    public static boolean putFloat(Context context, String key, float value) {
        return putFloat(context, context.getPackageName(), key, value);
    }


    /**
     * 获取boolean类型值
     *
     * @param context  上下文对象
     * @param name
     * @param key
     * @param defValue 默认值
     * @return key对应的value
     */
    public static float getFloat(Context context, String name, String key, float defValue) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    public static float getFloat(Context context, String name, String key) {
        return getFloat(context, name, key, DEFAULT_FLOAT_VALUE);
    }

    public static float getFloat(Context context, String key, float defValue) {
        return getFloat(context, context.getPackageName(), key, defValue);
    }

    public static float getFloat(Context context, String key) {
        return getFloat(context, context.getPackageName(), key, DEFAULT_FLOAT_VALUE);
    }


    /**
     * 保存long类型数据
     *
     * @param context
     * @param name
     * @param key
     * @param value
     * @return
     */
    public static boolean putLong(Context context, String name, String key, long value) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    public static boolean putLong(Context context, String key, long value) {
        return putLong(context, context.getPackageName(), key, value);
    }


    /**
     * 获取boolean类型值
     *
     * @param context  上下文对象
     * @param name
     * @param key
     * @param defValue 默认值
     * @return key对应的value
     */
    public static long getLong(Context context, String name, String key, long defValue) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    public static long getLong(Context context, String name, String key) {
        return getLong(context, name, key, DEFAULT_LONG_VALUE);
    }

    public static long getLong(Context context, String key, long defValue) {
        return getLong(context, context.getPackageName(), key, defValue);
    }

    public static long getLong(Context context, String key) {
        return getLong(context, context.getPackageName(), key, DEFAULT_LONG_VALUE);
    }


    /**
     * 清除保存的数据
     *
     * @param context
     * @param name    保存的名字
     * @return
     */
    public static boolean clearData(Context context, String name) {
        SharedPreferences sp = getSP(context, name, Context.MODE_PRIVATE);
        return sp.edit().clear().commit();
    }

    public static boolean clearData(Context context) {
        return clearData(context, context.getPackageName());
    }
}
