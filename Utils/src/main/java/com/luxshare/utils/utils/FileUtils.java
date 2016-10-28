package com.luxshare.utils.utils;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/10/26.
 */
public class FileUtils {

    private static final String TAG = "FileUtils";


    /**
     * 创建目录
     *
     * @param dir 目录
     */
    public static void mkdir(String dir) {
        try {
            String dirTemp = dir;
            File dirPath = new File(dirTemp);
            if (!dirPath.exists()) {
                dirPath.mkdir();
            }
        } catch (Exception e) {
            Log.i(TAG, "mkdir: 创建目录操作出错" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 新建文件
     *
     * @param fileName 包含路径的文件名 如：E:\phsftp\src\123.txt
     * @param content  文件内容
     */
    public static void createNewFile(String fileName, String content) {
        try {
            String fileNameTemp = fileName;
            File filePath = new File(fileNameTemp);
            if (!filePath.exists()) {
                filePath.createNewFile();
            }
            FileWriter fw = new FileWriter(filePath);
            PrintWriter pw = new PrintWriter(fw);
            String strContent = content;
            pw.print(strContent);
            pw.flush();
            pw.close();
            fw.close();
        } catch (Exception e) {
            Log.i(TAG, "createNewFile: 新建文件出错" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param fileName 包含路径的文件名 如:E:\phsftp\src\123.txt
     */
    public static void deleteFile(String fileName) {
        try {
            String filePath = fileName;
            File deleteFile = new File(filePath);
            deleteFile.delete();
        } catch (Exception e) {
            Log.i(TAG, "deleteFile: 删除文件出错" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 删除文件夹
     *
     * @param folderPath 文件夹路径
     */
    public static void deleteFolder(String folderPath) {
        try {
            // 删除文件夹里面所有内容
            deleteAllFile(folderPath);
            String filePaht = folderPath;
            File file = new File(filePaht);
            // 删除空文件夹
            file.delete();
        } catch (Exception e) {
            Log.i(TAG, "deleteFolder: 删除文件夹出错" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 删除文件夹里面所有文件
     *
     * @param folderPath 文件夹路径
     */
    public static void deleteAllFile(String folderPath) {
        File file = new File(folderPath);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] childFiles = file.list();
        File temp = null;
        for (int i = 0; i < childFiles.length; i++) {
            // File.separator 与系统有关的默认名称分隔符
            // 在UNIX系统上，此字段的值为 '/'；在windows系统上，它为'\'
            if (folderPath.endsWith(File.separator)) {
                temp = new File(folderPath + childFiles[i]);
            } else {
                temp = new File(folderPath + File.separator + childFiles[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                deleteAllFile(folderPath + "/" + childFiles[i]);// 先删除文件夹里面的文件
                deleteFolder(folderPath + "/" + childFiles[i]); //再删除空文件夹
            }
        }
    }


    /**
     * 复制单个文件
     *
     * @param srcFile 包含路径的源文件 如：E:/phsftp/src/abc.txt
     * @param dirDest 目标文件目录，若文件目录不存在则自动创建 如：E:/phsftp/src
     */
    public static void copyFile(String srcFile, String dirDest) {
        try {
            FileInputStream in = new FileInputStream(srcFile);
            mkdir(dirDest);
            FileOutputStream out = new FileOutputStream(dirDest + "/" + new File(srcFile).getName());
            int len;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.close();
            in.close();
        } catch (Exception e) {
            Log.i(TAG, "copyFile: 复制文件操作出错" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 复制文件夹
     *
     * @param oldPath 源文件夹路径 如：E:/phsftp/src
     * @param newPath 目标文件夹路径 如：E:/phsftp/dest
     */
    public static void copyFolder(String oldPath, String newPath) {
        try {
            // 如果文件夹不存在，则新建文件夹
            mkdir(newPath);
            File file = new File(oldPath);
            String[] files = file.list();
            File temp = null;
            for (int i = 0; i < files.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + files[i]);
                } else {
                    temp = new File(oldPath + File.separator + files[i]);
                }
                if (temp.isFile()) {
                    FileInputStream in = new FileInputStream(temp);
                    FileOutputStream out = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                    byte[] buffer = new byte[1024 * 2];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                    out.flush();
                    out.close();
                    in.close();
                }
                if (temp.isDirectory()) {
                    copyFolder(oldPath + "/" + files[i], newPath + "/" + files[i]);
                }
            }
        } catch (Exception e) {
            Log.i(TAG, "copyFolder: 复制文件夹操作出错" + e.getMessage());
            e.printStackTrace();
        }
    }


    /**
     * 移动文件到指定目录
     *
     * @param oldPath 包含路径的文件名 如：E:/phsftp/src/ljq.txt
     * @param newPath 目标文件目录 如：E:/phsftp/dest
     */
    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        deleteFile(oldPath);
    }


    /**
     * 移动文件到指定目录，不会删除文件夹
     *
     * @param oldPath 源文件目录 如：E:/phsftp/src
     * @param newPath 目标文件目录 如：E:/phsftp/dest
     */
    public static void moveFiles(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        deleteAllFile(oldPath);
    }


    /**
     * 移动文件夹到指定目录，会删除文件夹
     *
     * @param oldPath 源文件目录  如：E:/phsftp/src
     * @param newPath 目标文件目录 如：E:/phsftp/dest
     */
    public static void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        deleteFolder(oldPath);
    }

}
