package com.luxshare.utils.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Luxshare-ICT on 2016/11/30.
 */

public class CacheUtils {

    private static final String TAG = "CacheUtils";

    /**
     * 获取缓存大小
     *
     * @param context
     * @return
     */
    public static String getTotalCacheSize(Context context) {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 获取文件大小
     *
     * @param file
     * @return
     */
    public static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果下面还有文件
                if (files[i].isDirectory()) {
                    size = size + getFolderSize(files[i]);
                } else {
                    size = size + files[i].length();
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "getFolderSize: failure");
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "OK";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result = new BigDecimal(Double.toString(kiloByte));
            return result.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result = new BigDecimal(Double.toString(megaByte));
            return result.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "M";
        }

        double teraByte = gigaByte / 1024;
        if (teraByte < 1) {
            BigDecimal result = new BigDecimal(Double.toString((gigaByte)));
            return result.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "GB";
        }

        BigDecimal result = new BigDecimal(teraByte);
        return result.setScale(2, BigDecimal.ROUND_HALF_UP).toString() + "TB";
    }

    /**
     * 清除缓存
     *
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}
