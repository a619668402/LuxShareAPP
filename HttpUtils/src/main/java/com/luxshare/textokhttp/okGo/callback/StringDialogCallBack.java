package com.luxshare.textokhttp.okGo.callback;

import android.content.Context;
import android.support.annotation.Nullable;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.BaseRequest;

/**
 * Created by Administrator on 2016/10/14.
 */
public abstract class StringDialogCallBack extends StringCallback {

    private SVProgressHUD mLoading;

    private void initLoading(Context context) {
        mLoading = new SVProgressHUD(context);
    }

    public StringDialogCallBack(Context context) {
        initLoading(context);
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);

        //网络请求结束后显示对话框
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.showWithStatus("请求网络中...");
        }
    }

    @Override
    public void onAfter(@Nullable String s, @Nullable Exception e) {
        super.onAfter(s, e);

        //网络请求结束后关闭对话框
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }
}
