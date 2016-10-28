package com.luxshare.utils.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 * 系统相关的工具类
 */
public class SystemUtils {

    private static final String TAG = "SystemUtils";

    /**
     * 获取android当前可用内存大小
     *
     * @param context
     * @return
     */
    public static String getAvailMemory(Context context) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        // 将获取的内存大小规格化
        return Formatter.formatFileSize(context, mi.availMem);
    }


    /**
     * 获取手机内存的大小
     *
     * @param context
     * @return
     */
    public static String getTotalMemory(Context context) {

        String str1 = "/proc/meminfo"; // 系统内存信息文件
        String str2;
        String[] arrayOfString;
        int initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(localFileReader, 8192);
            str2 = localBufferedReader.readLine(); // 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i("TAG", num + "\t");

            }
            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024; //  获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();
            return Formatter.formatFileSize(context, initial_memory); // Byte转换为KB或者MB，内存大小规格化
        } catch (FileNotFoundException e) {
            Log.i(TAG, "getTotalMemory: 文件不存在");
        } catch (IOException e) {
            Log.i(TAG, "getTotalMemory: IOException");
        }
        return null;
    }


    /**
     * 获得SD卡总大小
     *
     * @param context
     * @return
     */
    public static String getSDTotalSize(Context context) {

        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        return Formatter.formatFileSize(context, blockSize * totalBlocks);
    }


    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @param context
     * @return
     */
    public static String getSDAvailableSize(Context context) {

        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        return Formatter.formatFileSize(context, blockSize * availableBlocks);
    }


    /**
     * 获取应用运行的最大内存
     *
     * @return 最大内存
     */
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 安装apk
     *
     * @param context 上下文
     * @param file    APK文件
     */
    public static void installApk(Context context, File file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }


    /**
     * 安装apk
     *
     * @param context 上下文
     * @param file    APK文件uri
     */
    public static void installApk(Context context, Uri file) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(file, "application/vnd.android.package-archive");
        context.startActivity(intent);
    }


    /**
     * 判断程序是处于前台还是在后台运行
     *
     * @param context 上下文
     * @return true 为后台运行，false 为前台运行
     */
    public static boolean isBackground(Context context) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = am.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {

            if (appProcess.processName.equals(context.getPackageName())) {

                Log.i(TAG, "此appimportace ="
                        + appProcess.importance
                        + ",context.getClass().getName()="
                        + context.getClass().getName());

                if (appProcess.importance != ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    Log.i(TAG, "处于后台"
                            + appProcess.processName);

                    return true;
                } else {
                    Log.i(TAG, "处于前台"
                            + appProcess.processName);
                    return false;
                }
            }
        }
        return false;
    }


    /**
     * 获取手机系统SDK版本
     *
     * @return 如API 17则返回17
     */
    public static int getSDKVersion() {

        return Build.VERSION.SDK_INT;
    }


    /**
     * 获取手机MAC地址
     * 6.0以上需要加入
     * <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
     * <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
     * @return MAC地址
     */
    public static String getMac() {
        String str = "";
        String macSerial = "";
        try {
            Process pp = Runtime.getRuntime().exec("cat /sys/class/net/wlan0/address ");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);

            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    macSerial = str.trim();// 空格
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (macSerial == null || "".equals(macSerial)) {
            try {
                return loadFileAsString("/sys/class/net/eth0/address")
                        .toUpperCase().substring(0, 17);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return macSerial;
    }

    private static String loadFileAsString(String fileName) throws IOException {
        FileReader reader = new FileReader(fileName);
        String text = loadReaderAsString(reader);
        reader.close();
        return text;
    }

    private static String loadReaderAsString(FileReader reader) throws IOException {
        StringBuilder builder = new StringBuilder();
        char[] buffer = new char[1024 * 4];
        int readLength = reader.read(buffer);
        while (readLength >= 0) {
            builder.append(buffer, 0, readLength);
            readLength = reader.read(buffer);
        }
        return builder.toString();
    }
}
