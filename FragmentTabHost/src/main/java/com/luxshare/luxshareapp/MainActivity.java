package com.luxshare.luxshareapp;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.luxshare.luxshareapp.bean.IndicatorBean;
import com.luxshare.luxshareapp.fragment.FourFragment;
import com.luxshare.luxshareapp.fragment.OneFragment;
import com.luxshare.luxshareapp.fragment.ThreeFragment;
import com.luxshare.luxshareapp.fragment.TwoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentTabHost mFragmentTabHost;
    // 存放底部Item的标题和图片
    private List<IndicatorBean> mIndicatorBeanList;
    private List<View> mViews;
    private List<Class> mClassList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initClassList();

    }

    private void initData() {
        mIndicatorBeanList = new ArrayList<>();
        mIndicatorBeanList.add(new IndicatorBean(R.drawable.tab_icon_selector, R.string.tab1));
        mIndicatorBeanList.add(new IndicatorBean(R.drawable.tab_icon_selector, R.string.tab2));
        mIndicatorBeanList.add(new IndicatorBean(R.drawable.tab_icon_selector, R.string.tab3));
        mIndicatorBeanList.add(new IndicatorBean(R.drawable.tab_icon_selector, R.string.tab4));

    }

    private void initTabHost() {
        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mFragmentTabHost.setup(this, getSupportFragmentManager(), R.id.maincontent);

        initIndicator();
        for (int i = 0; i < mViews.size(); i++) {
            TabHost.TabSpec spec = mFragmentTabHost.newTabSpec(getString(mIndicatorBeanList.get(i).getTitleId())).setIndicator(mViews.get(i));
            mFragmentTabHost.addTab(spec, mClassList.get(i), null);
        }
        mFragmentTabHost.getTabWidget().setDividerDrawable(null);
    }

    private void initIndicator() {
        mViews = new ArrayList<>();
        for (int i = 0; i < mIndicatorBeanList.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.indicator_view, null);
            ImageView iv = (ImageView) view.findViewById(R.id.tab_icon);
            TextView title = (TextView) view.findViewById(R.id.tab_title);
            iv.setImageResource(mIndicatorBeanList.get(i).getIconId());
            title.setText(mIndicatorBeanList.get(i).getTitleId());
            mViews.add(view);
        }
    }

    private void initClassList() {
        mClassList = new ArrayList<>();
        mClassList.add(OneFragment.class);
        mClassList.add(TwoFragment.class);
        mClassList.add(ThreeFragment.class);
        mClassList.add(FourFragment.class);
    }
}
