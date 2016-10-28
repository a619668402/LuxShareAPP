package com.luxshare.recylerviewadapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.luxshare.recylerviewadapter.adapter.MultiItemTypeSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultiTypeAct extends AppCompatActivity {

    private RecyclerView mRecyclerView;

    private MutilAdapter mAdapter;

    private List<News> mNewsList;

    private RecyclerViewHeader mHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type);

        mRecyclerView = (RecyclerView) findViewById(R.id.multi_recylerview);
        mHeader = (RecyclerViewHeader) findViewById(R.id.header);

        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(manager);
        mHeader.attachTo(mRecyclerView);

        MultiItemTypeSupport support = new MultiItemTypeSupport<News>() {
            @Override
            public int getLayoutId(int viewType) {

                if (viewType == News.ITEM_TYPE_TEXT) {
                    return R.layout.multi_item_text;
                } else if (viewType == News.ITEM_TYPE_IMAGE) {
                    return R.layout.multi_item_image;
                } else if (viewType == News.ITEM_TYPE_BUTTON) {
                    return R.layout.multi_item_button;
                }

                return News.ITEM_TYPE_TEXT;
            }

            @Override
            public int getItemViewType(int position, News news) {
                return news.getItemType();
            }
        };

        mAdapter = new MutilAdapter(this,support);
        mRecyclerView.setAdapter(mAdapter);
        initData();
    }
    public void initData() {

        mNewsList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 40; i ++) {

            News news = new News();
            int index = random.nextInt(100) % 3;
            if (index == 0) {

                news.setText("新闻标题　" + i);
                news.setItemType(News.ITEM_TYPE_TEXT);
            } else if (index == 1) {

                news.setButton("点击我 " + i);
                news.setItemType(News.ITEM_TYPE_BUTTON);
            } else if (index == 2) {

                news.setImage(R.mipmap.ic_launcher);
                news.setItemType(News.ITEM_TYPE_IMAGE);
            }
            mNewsList.add(news);
            System.out.println(index + "------------");
        }
        mAdapter.addAll(mNewsList);
    }
}
