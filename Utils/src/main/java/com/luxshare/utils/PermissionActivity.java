package com.luxshare.utils;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Luxshare-ICT on 2016/12/9.
 */

public abstract class PermissionActivity extends AppCompatActivity {


    /**
     * 单个权限检查,如果没有授权,请求授权
     *
     * @param permissionName 权限名称
     * @param requestCode    回调检测的值
     * @return
     */
    protected boolean isPermissionGranted(String permissionName, int requestCode) {
        // SDK版本小于6.0不作检查
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        // 如果没有授权就请求授权
        if (ContextCompat.checkSelfPermission(this, permissionName) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{permissionName}, requestCode);
            return false;
        }
        return true;
    }

    protected boolean isPermissionAllGranted(String[] permissions, int requestCode) {
        // SDK版本小于6.0不作检查
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        //获得批量请求但被禁止的权限列表
        List<String> denyList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                denyList.add(permissions[i]);
            }
        }
        // 进行批量请求
        int denyCount = denyList.size();
        if (denyCount != 0) {
            ActivityCompat.requestPermissions(this, denyList.toArray(new String[denyCount]), PermissionConstant.PERMISSION_ALL);
            return false;
        }
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            // 联系人相关权限处理
            case PermissionConstant.PERMISSION_READ_CONTACTS:
            case PermissionConstant.PERMISSION_WRITE_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showAlertDialog("联系人权限被禁止,某些功能可能无法使用,是否开启该权限?(步骤：应用信息->权限->'勾选'联系人)");
                } else {
                    doSuccessRequestPermission(requestCode);
                }
                break;
            // 相机相关权限处理
            case PermissionConstant.PERMISSION_CAMERA:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showAlertDialog("相机权限被禁止,某些功能可能无法使用,是否开启该权限?(步骤：应用信息->权限->'勾选'联系人)");
                } else {
                    doSuccessRequestPermission(requestCode);
                }
                break;
            // 位置相关权限处理
            case PermissionConstant.PERMISSION_ACCESS_FINE_LOCATION:
            case PermissionConstant.PERMISSION_ACCESS_COARSE_LOCATION:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showAlertDialog("权限被禁止,某些功能可能无法使用,是否开启该权限?(步骤：应用信息->权限->'勾选'联系人)");
                } else {
                    doSuccessRequestPermission(requestCode);
                }
                break;
            // 信息相关权限处理
            case PermissionConstant.PERMISSION_READ_SMS:
            case PermissionConstant.PERMISSION_RECEIVE_SMS:
            case PermissionConstant.PERMISSION_SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showAlertDialog("权限被禁止,某些功能可能无法使用,是否开启该权限?(步骤：应用信息->权限->'勾选'联系人)");
                } else {
                    doSuccessRequestPermission(requestCode);
                }
                break;
            // 存储相关权限处理
            case PermissionConstant.PERMISSION_READ_EXTERNAL_STORAGE:
            case PermissionConstant.PERMISSION_WRITE_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    showAlertDialog("权限被禁止,某些功能可能无法使用,是否开启该权限?(步骤：应用信息->权限->'勾选'联系人)");
                } else {
                    doSuccessRequestPermission(requestCode);
                }
                break;
            // 批量检查处理
            case PermissionConstant.PERMISSION_ALL:
                checkAllPermission(requestCode, permissions, grantResults);
                break;
        }
    }

    // 批量权限检查
    protected void checkAllPermission(int requestCode, String[] permissions, int[] grantResults) {
        int grantedPermNum = 0;
        int totalPermissions = permissions.length;
        int totalResults = grantResults.length;
        if (totalResults == 0 || totalPermissions == 0) {
            return;
        }
        // 初始化Map容器，用于判断哪些权限被授予
        Map<String, Integer> permResults = new HashMap<String, Integer>();
        for (String perm : permissions) {
            permResults.put(perm, PackageManager.PERMISSION_DENIED);
        }
        for (int i = 0; i < totalResults; i++) {
            permResults.put(permissions[i], grantResults[i]);
            if (permResults.get(permissions[i]) == PackageManager.PERMISSION_GRANTED) {
                grantedPermNum++;
            }
        }
        if (grantedPermNum == totalPermissions) {
            doSuccessRequestPermission(requestCode);
        } else {
            doFailRequestPermission();
        }
    }

    private void showAlertDialog(String msgContent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示")
                .setMessage(msgContent)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri packUri = Uri.parse("package:" + getPackageName());
                        Intent intent = new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packUri);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                }).show();
    }

    // 获取权限成功时回调
    protected abstract void doSuccessRequestPermission(int requestCode);

    // 获取权限失败时回调(目前仅对批量获取有效)
    protected abstract void doFailRequestPermission();

}
