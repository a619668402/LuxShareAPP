package com.luxshare.textokhttp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.luxshare.textokhttp.okGo.OkGoHelper;
import com.luxshare.textokhttp.okGo.callback.JsonDialogCallBack;
import com.luxshare.textokhttp.okGo.callback.StringDialogCallBack;
import com.luxshare.textokhttp.okHttp.LoadingCallBack;
import com.luxshare.textokhttp.okHttp.OkHttpHelper;
import com.luxshare.textokhttp.test.DownLoadAct;
import com.luxshare.textokhttp.test.PersonBean;
import com.luxshare.textokhttp.test.Test;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpHelper mHttpHelper = OkHttpHelper.getInstance();

    private OkGoHelper mHelper = OkGoHelper.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getBtnClick(View view) {

        mHttpHelper.doGet("http://dcs.luxshare-ict.com:19999/api/Product/GetOrder?EmpCode=L033712", new LoadingCallBack<Test>(this, true) {

            @Override
            public void onSuccess(Response response, Test test) {
                System.out.println(test.getData().get(0).getG().get(0).getId() + "-----" + test.getData().get(0).getHead());
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }
        });
    }

    public static <T> T bindModel(String content, Class<T> cls) {
        try {
            JSONObject mjson = new JSONObject(content);
            try {
                T instance = cls.newInstance();
                Iterator it = mjson.keys();
                while (it.hasNext()) {
                    String next = it.next().toString();
                    ModelReflector.setProperty(instance, next, mjson.get(next)
                            .toString());
                }
                return instance;
            } catch (IllegalAccessException e) {
            } catch (InstantiationException e) {
            }
        } catch (JSONException e) {
        }
        return null;
    }


    public void getBtnClick1(View view) {

        String url = "http://dcs.luxshare-ict.com:9999/api/FixedAssets/GetEmpAssets?code=P000329";

        mHelper.doGet(url, this, new JsonDialogCallBack<PersonBean>(this) {

            @Override
            public void onSuccess(PersonBean bean, Call call, Response response) {

                System.out.println(bean.getData().get(0).getCode() + bean.getData().get(0).getCompanyName() + "-----11-----");
            }
        });
    }


    public void getBtnClick2(View view) {

        startActivity(new Intent(MainActivity.this, DownLoadAct.class));
    }



}
