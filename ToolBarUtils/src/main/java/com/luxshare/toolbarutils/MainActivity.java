package com.luxshare.toolbarutils;

import android.os.Bundle;

import com.luxshare.toolbarutils.toolbar.ToolBarActivity;

public class MainActivity extends ToolBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置toolbar标题
        setToolbarTitle("测试");

        // 设置返回按钮
        setNavigationBack();
    }
}
