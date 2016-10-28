package com.luxshare.textokhttp.okGo.callback;

import com.google.gson.internal.$Gson$Types;
import com.luxshare.textokhttp.okGo.JsonConvert;
import com.lzy.okgo.callback.AbsCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/14.
 */
public abstract class JsonCallBack<T> extends AbsCallback<T> {


    /**
     * 该方法是子线程处理，不能做ui相关的工作
     * 主要作用是解析网络返回的 response 对象,生产onSuccess回调中需要的数据对象
     * 这里的解析工作不同的业务逻辑基本都不一样,所以需要自己实现,以下给出的时模板代码,实际使用根据需要修改
     */
    @Override
    public T convertSuccess(Response response) throws Exception {


        JsonConvert<T> convert = new JsonConvert<>();
        convert.setType(getType(getClass()));
        T t = convert.convertSuccess(response);
        response.close();

        return t;
    }


    public Type getType(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }
}
