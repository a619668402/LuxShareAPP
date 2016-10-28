package com.luxshare.nohttp;

import android.app.Application;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.rest.RequestQueue;

/**
 * Created by Administrator on 2016/9/26.
 */
public class MyApplication extends Application {

    public static RequestQueue mQueue = null;
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化NoHttp
        NoHttp.initialize(this);
        // 初始化Queue
        mQueue = NoHttp.newRequestQueue();

    }
}
