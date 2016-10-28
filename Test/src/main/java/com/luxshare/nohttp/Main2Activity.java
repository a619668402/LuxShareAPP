package com.luxshare.nohttp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends AppCompatActivity {

    private WebView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mView = (WebView) findViewById(R.id.wb);

        mView.loadUrl("http://dcs.luxshare-ict.com:8008/zh-CN/Personal/RecruitRequestNew");
        mView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        WebSettings settings = mView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mView.canGoBack()) {
                mView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
