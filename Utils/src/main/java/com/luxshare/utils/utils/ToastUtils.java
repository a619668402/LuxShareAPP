package com.luxshare.utils.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/10/6.
 */
public class ToastUtils {

    private static Toast toast = null;

    public static void showToast(Context context,String id) {

        if (toast == null) {

            toast = Toast.makeText(context,id,Toast.LENGTH_SHORT);
        } else {

            toast.setText(id);
        }
        toast.show();
    }
}
