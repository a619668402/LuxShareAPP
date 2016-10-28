package com.luxshare.textokhttp.okGo;

import com.google.gson.stream.JsonReader;
import com.lzy.okgo.convert.Converter;

import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by Administrator on 2016/10/14.
 */
public class JsonConvert<T> implements Converter<T> {

    private Type type;

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public T convertSuccess(Response response) throws Exception {

        JsonReader jsonReader = new JsonReader(response.body().charStream());

        if (type == null) {
            throw new IllegalArgumentException("没有填写泛型参数");
        }

        Object o = Convert.fromJson(jsonReader, type);
        return (T) o;
    }

}
