package com.luxshare.testscrollview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.luxshare.testscrollview.View.PullUpToLoadMore;

public class Main2Activity extends AppCompatActivity {

    private PullUpToLoadMore mPtlm;
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViewById();
    }

    private void initViewById() {
        mPtlm = ((PullUpToLoadMore) findViewById(R.id.ptlm));
        mBtn = ((Button) findViewById(R.id.btn));

        mPtlm.setPageChangeListener(new PullUpToLoadMore.PageChangeListener() {
            @Override
            public void onPageChangeListener(int position) {
                if (position == 0) {
                    mBtn.setVisibility(View.GONE);
                } else if (position == 1) {
                    mBtn.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void scrollTop(View view) {
        mPtlm.scrollToTop();
    }
}
