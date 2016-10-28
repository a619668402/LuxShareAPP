package com.luxshare.textokhttp.okGo.callback;

import android.content.Context;
import android.support.annotation.Nullable;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.lzy.okgo.request.BaseRequest;

/**
 * Created by Administrator on 2016/10/14.
 */
public abstract class JsonDialogCallBack<T> extends JsonCallBack<T> {

    private SVProgressHUD mLoading;

    private void initLoading(Context context) {
        mLoading = new SVProgressHUD(context);
    }

    public JsonDialogCallBack(Context context) {
        super();
        initLoading(context);
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        // 网络请求前显示对话框
        if (mLoading != null && !mLoading.isShowing()) {
            mLoading.showWithStatus("请求网络中...");
        }
    }

    @Override
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
        // 网络请求结束后关闭对话框
        if (mLoading != null && mLoading.isShowing()) {
            mLoading.dismiss();
        }
    }
}
