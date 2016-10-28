package com.luxshare.utils.utils;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by Administrator on 2016/10/6.
 * 键盘相关的工具类
 */
public class InputMethodUtils {


    /**
     * 显示键盘
     * @param context
     */
    public static void openSoftKeyboard(Context context, EditText editText) {

        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText,InputMethodManager.SHOW_IMPLICIT);
    }


    /**
     * 关闭软键盘
     * @param activity
     */
    public static void closeSoftKeyboard(Activity activity) {

        InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);

        if (manager.isActive()) {

            manager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }



    /**
     * 判断隐藏软键盘是否弹出,弹出就隐藏
     * @param activity
     * @return
     */
    public boolean keyBoxIsShow(Activity activity) {

        if (activity.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {

            // 隐藏键盘
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            return true;
        } else {

            return false;
        }
    }
}
