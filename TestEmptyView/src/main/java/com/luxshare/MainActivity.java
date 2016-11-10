package com.luxshare;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.luxshare.Widget.CustomRelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Button mBtn_Start;
    private CustomRelativeLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        mBtn_Start = ((Button) findViewById(R.id.btn_start));
        mLayout = ((CustomRelativeLayout) findViewById(R.id.container));

        mLayout.setRetryListener(new CustomRelativeLayout.RetryListener() {
            @Override
            public void retry() {
                mLayout.setInProgress();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLayout.setChildrenVisible();
                    }
                }, 5000);
            }
        });

        mBtn_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLayout.setInProgress();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLayout.setChildrenVisible();
                    }
                }, 3000);
            }
        });
        mLayout.setInProgress();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLayout.setChildrenGone();
            }
        }, 5000);
    }
}
