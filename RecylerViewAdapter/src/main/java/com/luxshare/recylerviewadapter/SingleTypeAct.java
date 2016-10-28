package com.luxshare.recylerviewadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.luxshare.recylerviewadapter.adapter.BaseCommonAdapter;

import java.util.ArrayList;
import java.util.List;

public class SingleTypeAct extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private List<Person> mList;

    private BaseCommonAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_type);

        mRecyclerView = (RecyclerView) findViewById(R.id.single_recylerview);

        RecyclerView.LayoutManager manager1 = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        RecyclerView.LayoutManager manager2 = new GridLayoutManager(this,4);

        mRecyclerView.setLayoutManager(manager1);

        mAdapter = new MyAdapter(this,R.layout.recylerview_item_normal);
        mRecyclerView.setAdapter(mAdapter);

        initData();
    }

    public void initData() {

        mList = new ArrayList<>();
        for (int i = 0; i < 40; i ++) {

            mList.add(new Person("this is the " + i + " lines"));
        }

        mAdapter.addAll(mList);
    }
}
