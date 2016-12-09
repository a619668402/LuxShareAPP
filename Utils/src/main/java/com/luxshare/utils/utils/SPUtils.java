package com.luxshare.utils.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;
import android.util.Log;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/21.
 * SharedPreferences 相关操作的工具类
 */
public class SPUtils {

    /**
     * 保存在手机里面的文件名
     */
    public static final String FILE_NAME = "share_data";


    /**
     * 保存数据的方法,我们需要拿到保存数据的具体类型,然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    public static void put(Context context, String key, Object object) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (object instanceof String) {
            editor.putString(key, ((String) object));
        } else if (object instanceof Integer) {
            editor.putInt(key, ((Integer) object));
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, ((Boolean) object));
        } else if (object instanceof Float) {
            editor.putFloat(key, ((Float) object));
        } else if (object instanceof Long) {
            editor.putLong(key, ((Long) object));
        } else {
            editor.putString(key, ((String) object));
        }
        SharedPreferenceCompat.apply(editor);
    }

    /**
     * 获取保存数据的方法,根据默认值得到数据类型,调用对应的方法获取值
     *
     * @param context
     * @param key
     * @param defObject
     * @return
     */
    public static Object get(Context context, String key, Object defObject) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        if (defObject instanceof String) {
            return sharedPreferences.getString(key, ((String) defObject));
        } else if (defObject instanceof Integer) {
            return sharedPreferences.getInt(key, ((Integer) defObject));
        } else if (defObject instanceof Boolean) {
            return sharedPreferences.getBoolean(key, ((Boolean) defObject));
        } else if (defObject instanceof Float) {
            return sharedPreferences.getFloat(key, ((Float) defObject));
        } else if (defObject instanceof Long) {
            return sharedPreferences.getLong(key, ((Long) defObject));
        }
        return null;
    }


    /**
     * 移除某个key对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(key);
        SharedPreferenceCompat.apply(editor);
    }


    /**
     * 清除所有的数据
     *
     * @param context
     */
    public static void clear(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        SharedPreferenceCompat.apply(editor);
    }


    /**
     * 查询某个key是否存在
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context, String key) {
        SharedPreferences sharePrefernecr = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharePrefernecr.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    public static Map<String, ?> getAll(Context context) {
        SharedPreferences sharePreference = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharePreference.getAll();
    }


    private static class SharedPreferenceCompat {

        private static final Method mApplyMethod = findApplyMethod();

        /**
         * 反射查找apply方法
         *
         * @return
         */
        private static Method findApplyMethod() {
            try {
                Class clazz = SharedPreferences.Editor.class;
                return clazz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (mApplyMethod != null) {
                    mApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }
    }

}
