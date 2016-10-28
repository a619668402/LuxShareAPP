package com.luxshare.baseadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TestAdapter mAdapter;
    private List<MyBean> mList;
    private ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLv = ((ListView) findViewById(R.id.lv));

        mList = new ArrayList();
        for (int i = 0;i < 20;i ++) {

            mList.add(new MyBean(false,"this is the " + i + " line."));
        }

        mAdapter = new TestAdapter(this,mList,R.layout.test_item);
        mLv.setAdapter(mAdapter);
    }
}
