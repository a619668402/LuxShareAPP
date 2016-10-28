package com.luxshare.textokhttp;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;

/**
 * Created by Administrator on 2016/10/14.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        OkGo.init(this);

        try {
            OkGo.getInstance()
                    .setConnectTimeout(15 * 1000)
                    .setReadTimeOut(OkGo.DEFAULT_MILLISECONDS)
                    .setWriteTimeOut(OkGo.DEFAULT_MILLISECONDS)
                    .setCacheMode(CacheMode.NO_CACHE) // 设置缓存
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE); // 缓存永不过期
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
