package com.luxshare.utils.utils;

import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by Administrator on 2016/10/21.
 */
public class WebViewManager {

    private WebView mWebView;

    private WebSettings mSettings;


    public WebViewManager(WebView webView) {
        this.mWebView = webView;
        mSettings = mWebView.getSettings();
        mSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
    }


    /**
     * 开启自适应功能
     *
     * @return
     */
    public WebViewManager enalbeAdaptive() {
        mSettings.setUseWideViewPort(true);
        mSettings.setLoadWithOverviewMode(true);
        return this;
    }


    /**
     * 开启JavaScript
     *
     * @return
     */
    public WebViewManager enableJavaScript() {
        mSettings.setJavaScriptEnabled(true);
        return this;
    }


    /**
     * 禁用JavaScript
     */
    public WebViewManager disableJavaScript() {
        mSettings.setJavaScriptEnabled(false);
        return this;
    }

    /**
     * 开启JavaScript自动弹窗
     */
    public WebViewManager enableJavaScriptOpenWindowsAutomatically() {
        mSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        return this;
    }


    /**
     * 禁用JavaScript自动弹窗
     */
    public WebViewManager disableJavaScriptOpenWindowsAutomatically() {
        mSettings.setJavaScriptCanOpenWindowsAutomatically(false);
        return this;
    }


    /**
     * 返回
     *
     * @return true：已经返回，false：到头了没法返回了
     */
    public boolean goBack() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {

            return false;
        }
    }
}
