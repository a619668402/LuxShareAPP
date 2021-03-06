package com.luxshare.luxshareapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luxshare.luxshareapp.R;

/**
 * Created by Administrator on 2016/9/26.
 */
public class OneFragment extends Fragment {

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (mRootView == null) {

            mRootView = inflater.inflate(R.layout.fragment_one, null);
            System.out.println("-----------ONE------------");
        }

        ViewGroup parent = (ViewGroup) mRootView.getParent();

        if (parent != null) {

            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("One---------");
    }
}
