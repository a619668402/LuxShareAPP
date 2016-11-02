package com.luxshare.testscrollview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.luxshare.testscrollview.Tools.StatusBarUtil;
import com.luxshare.testscrollview.View.GradationScrollView;

public class MainActivity extends AppCompatActivity implements GradationScrollView.ScrollViewListener {

    private GradationScrollView mScrollView;
    private ImageView mIv;
    private TextView mtv;
    private int mHeight;
    private TextView mClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        StatusBarUtil.setImgTransparent(this);
        setContentView(R.layout.activity_main);

        initViewById();

        initListeners();
    }

    private void initListeners() {
        // 获取顶部图片的高度，设置滚动监听
        ViewTreeObserver vto = mIv.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mtv.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mHeight = mIv.getHeight();
                mScrollView.setScrollViewListener(MainActivity.this);
            }
        });
    }

    private void initViewById() {
        mScrollView = ((GradationScrollView) findViewById(R.id.scrollview));
        mIv = ((ImageView) findViewById(R.id.imageview));
        mtv = ((TextView) findViewById(R.id.textview));
        mClick = ((TextView) findViewById(R.id.one));

        mClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });

        mIv.setFocusable(true);
        mIv.setFocusableInTouchMode(true);
        mIv.requestFocus();
    }

    @Override
    public void onScrollChanged(GradationScrollView scrollView, int x, int y, int oldx, int oldy) {
        if (y <= 0) {
            mtv.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));
        } else if (y > 0 && y <= mHeight) {
            float scale = (float) y / mHeight;
            float alpha = (255 * scale);
            // 设置字体颜色随着滚动逐渐改变
            mtv.setTextColor(Color.argb((int) alpha, 255, 255, 255));
            // 设置背景随着滚动逐渐改变
            mtv.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
        } else {
            mtv.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
        }
    }
}
