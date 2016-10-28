package com.luxshare.nohttp;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void getBtnClick(View view) {
        Notification foregroundNote;
        RemoteViews bigView = new RemoteViews(getApplicationContext().getPackageName(),
                R.layout.notification_layout_big);

        Notification.Builder mNotifyBuilder = new Notification.Builder(this);
//        NotificationCompat.Builder mNotifyBuilder = new NotificationCompat.Builder(this);
        foregroundNote = mNotifyBuilder.setContent(bigView)
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        foregroundNote.bigContentView = bigView;

        NotificationManager mNotifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifyManager.notify(1, foregroundNote);
    }

    public void postBtnClick(View view) {
        String url = "http://dcs.luxshare-ict.com:19999/api/Product/OrderBook";

        OkHttpClient client = new OkHttpClient();
        TempBean bean = new TempBean();
        bean.setEmpCode("L033712");
        bean.setProfitceterCode("SEE-D");
        bean.setLine("XT029");
        bean.setWorkOrderCode("Test-One");

        ArrayList<OrderBean> list = new ArrayList<>();
        OrderBean orderBean = new OrderBean();
        orderBean.setMaterialCode("L56TH059-SD-R");
        orderBean.setMaterialName("Other Cable:HSEC8 160 POS TO PCIE,DELL 5KV2Y");
        orderBean.setQuantity("50");
        orderBean.setDemandQuantity("10");
        orderBean.setDemandTime("10-10 10:10:10");
        list.add(orderBean);

        bean.setList(list);

        String json = new Gson().toJson(bean);

        System.out.println("---" + json);

        RequestBody requestBody = RequestBody.create(JSON, json);

        okhttp3.Request requestBuilder = new okhttp3.Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        Call call = client.newCall(requestBuilder);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("-----" + e);
            }

            @Override
            public void onResponse(Call call, okhttp3.Response response) throws IOException {
                System.out.println("-----" + response.body().string());
            }
        });

    }

    public void DropDownMenuClick(View view) {

        Toast.makeText(this, getMacAddress() + "-------", Toast.LENGTH_SHORT).show();
        System.out.println(getMacAddress() + "-------");
    }


    private String getMacAddress() {
        String result = "";
        WifiManager wifiManager = (WifiManager) getSystemService(this.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        result = wifiInfo.getMacAddress();
        return result;
    }
}
