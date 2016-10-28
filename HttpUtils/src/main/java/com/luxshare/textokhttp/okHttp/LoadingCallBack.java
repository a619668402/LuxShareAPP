package com.luxshare.textokhttp.okHttp;

import android.content.Context;

import com.bigkoo.svprogresshud.SVProgressHUD;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/28.
 */
public abstract class LoadingCallBack<T> extends BaseCallBack<T> {

    private SVProgressHUD mProgressHUD;
    private boolean mIsShowLoading;

    public LoadingCallBack(Context context, boolean isShowLoading) {
        mProgressHUD = new SVProgressHUD(context);
        this.mIsShowLoading = isShowLoading;
    }

    @Override
    public void onRequestBefore(Request request) {
        if (mIsShowLoading) {
            showLoading();
        }
    }

    public void showLoading() {
        mProgressHUD.showWithStatus("正在加载...");
    }

    public void disLoading() {
        if (mProgressHUD != null) {
            mProgressHUD.dismiss();
        }
    }

    @Override
    public void onFailure(Request request, IOException e) {
        if (mIsShowLoading) {
            disLoading();
        }
    }

    @Override
    public void onResponse(Response response) {
        if (mIsShowLoading) {
            disLoading();
        }
    }
}
