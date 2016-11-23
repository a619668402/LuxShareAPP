package com.luxshare.textokhttp.okGo.callback;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.Window;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.lzy.okgo.request.BaseRequest;

/**
 * Created by Administrator on 2016/10/14.
 */
public abstract class JsonDialogCallBack<T> extends JsonCallBack<T> {

    private ProgressDialog mDialog;

    private void initLoading(Context context) {
        mDialog = new ProgressDialog(context);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setMessage("正在加载...");

    }

    public JsonDialogCallBack(Context context) {
        super();
        initLoading(context);
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        // 网络请求前显示对话框
        if (mDialog != null && !mDialog.isShowing()) {
            mDialog.show();
        }
    }

    @Override
    public void onAfter(@Nullable T t, @Nullable Exception e) {
        super.onAfter(t, e);
        // 网络请求结束后关闭对话框
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }
}
