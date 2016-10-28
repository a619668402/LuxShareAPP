package com.luxshare.textokhttp.okHttp;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/9/28.
 */
public class OkHttpHelper {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    // 上传json数据时的key
    public static String KEY = "json";

    private static OkHttpClient mOkhttpClient;

    private Gson mGson;

    private Handler mHandler;

    private OkHttpHelper() {

        mOkhttpClient = new OkHttpClient();
        mOkhttpClient.newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();
        mGson = new Gson();
        mHandler = new Handler(Looper.getMainLooper());
    }



    public static OkHttpHelper getInstance() {

        return new OkHttpHelper();
    }



    public void doGet(String url, BaseCallBack callBack) {

        Request request = buildRequest(url, null, HttpMethodType.GET, null);

        doRequest(request, callBack);
    }



    public void doPost(String url, Map<String, String> params, BaseCallBack callBack) {

        Request request = buildRequest(url, params, HttpMethodType.POST, null);

        doRequest(request, callBack);
    }



    public void doPostJson(String url, String json, BaseCallBack callBack) {

        Map<String, String> map = new HashMap<>();
        map.put(KEY, json);
        Request request = buildRequest(url, map, HttpMethodType.POST, JSON);
        doRequest(request, callBack);
    }


    public void doRequest(final Request request, final BaseCallBack callBack) {

        callBack.onRequestBefore(request);

        mOkhttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                callFailure(callBack, request, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                callBackResponse(callBack, response);

                if (response.isSuccessful()) {

                    String resultStr = response.body().string();

                    if (callBack.mType == String.class) {

                        callBackSuccess(callBack, response, resultStr);
                    } else {
                        try {

                            Object object = mGson.fromJson(resultStr, callBack.mType);
                            callBackSuccess(callBack, response, object);
                        } catch (JsonIOException e) {

                            callError(callBack, response, e);
                        }

                    }
                } else {

                    callError(callBack, response, null);
                }
            }
        });
    }

    private Request buildRequest(String url, Map<String, String> params, HttpMethodType methodType, MediaType mediaType) {

        Request.Builder builder = new Request.Builder();
        builder.url(url);
        if (methodType == HttpMethodType.GET) {
            builder.get();
        } else if (methodType == HttpMethodType.POST) {
            RequestBody body = null;
            if (mediaType == JSON) {

                body = RequestBody.create(JSON, params.get(KEY));
            } else {

                body = buildFormData(params);
            }
            builder.post(body);
        }
        return builder.build();
    }

    private RequestBody buildFormData(Map<String, String> params) {

        FormBody.Builder builder = new FormBody.Builder();
        if (params != null) {

            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        return builder.build();
    }


    private void callBackResponse(final BaseCallBack callBack, final Response response) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onResponse(response);
            }
        });
    }

    private void callBackSuccess(final BaseCallBack callBack, final Response response, final Object object) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccess(response, object);
            }
        });
    }


    private void callFailure(final BaseCallBack callBack, final Request request, final Exception e) {

        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onFailure(request, (IOException) e);
            }
        });
    }


    private void callError(final BaseCallBack callBack, final Response response, final Exception e) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onError(response, response.code(), e);
            }
        });
    }

    enum HttpMethodType {
        GET,
        POST
    }
}
