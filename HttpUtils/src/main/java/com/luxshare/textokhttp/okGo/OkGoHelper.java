package com.luxshare.textokhttp.okGo;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.HttpParams;

import java.util.Map;

/**
 * Created by Administrator on 2016/10/14.
 */
public class OkGoHelper {

    private OkGoHelper() {

    }

    public static OkGoHelper getInstance() {
        return new OkGoHelper();
    }


    // get请求
    public void doGet(String url, Object tag, String cacheKey, CacheMode mode, AbsCallback callback) {

        OkGo.get(url)
                .tag(tag)
                .cacheKey(cacheKey)
                .cacheMode(mode)
                .execute(callback);
    }

    public void doGet(String url, Object tag, AbsCallback callback) {
        this.doGet(url, tag, "", CacheMode.NO_CACHE, callback);
    }

    // 普通的post请求
    public void doPost(String url, Object tag, Map<String, String> param, String cacheKey, CacheMode mode, AbsCallback callback) {

        HttpParams params = getParams(param);

        OkGo.post(url)
                .tag(tag)
                .params(params)
                .cacheKey(cacheKey)
                .cacheMode(mode)
                .execute(callback);
    }

    public void doPost(String url, Object tag, Map<String, String> param, AbsCallback callback) {
        this.doPost(url, tag, param, "", CacheMode.NO_CACHE, callback);
    }

    // 上传json字符串
    public void doPost(String url, Object tag, String json, String cacheKey, CacheMode mode, AbsCallback callback) {

        OkGo.post(url)
                .tag(tag)
                .upJson(json)
                .cacheKey(cacheKey)
                .cacheMode(mode)
                .execute(callback);
    }

    public void doPost(String url, Object tag, String json, AbsCallback callback) {
        this.doPost(url, tag, json, "", CacheMode.NO_CACHE, callback);
    }


    private HttpParams getParams(Map<String, String> map) {

        HttpParams params = new HttpParams();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            params.put(entry.getKey(), entry.getValue());
        }
        return params;
    }

}
