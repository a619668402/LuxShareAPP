package com.luxshare.testlistitemdelete;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.luxshare.testlistitemdelete.Widget.PopupList;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    private ArrayList<String> mList;
    private ArrayAdapter<String> mDataAdapter;
    private PopupList mPopupList;
    private ArrayList<String> mPopMenuList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initPotMenuList();

        initView();

    }

    private void initPotMenuList() {
        mPopMenuList = new ArrayList<>();
        mPopMenuList.add("one");
        mPopMenuList.add("two");
        mPopMenuList.add("three");
    }


    private void initView() {
        mLv = ((ListView) findViewById(R.id.lv));
        mList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mList.add("this is a " + i + "line");
        }
        mDataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, mList);
        mLv.setAdapter(mDataAdapter);

        mPopupList = new PopupList();
        mPopupList.init(this, mLv, mPopMenuList, new PopupList.OnPopupListClickListener() {
            @Override
            public void onPopupListClick(View contextView, int contextPosition, int position) {
                Toast.makeText(MainActivity.this, contextPosition + "," + position, Toast.LENGTH_LONG).show();
            }
        });

        mPopupList.setTextSize(mPopupList.sp2px(16));
        mPopupList.setTextPadding(mPopupList.dp2px(10), mPopupList.dp2px(5), mPopupList.dp2px(10), mPopupList.dp2px(5));
        mPopupList.setIndicatorView(mPopupList.getDefaultIndicatorView(mPopupList.dp2px(16), mPopupList.dp2px(8), 0xFF444444));
    }
}
