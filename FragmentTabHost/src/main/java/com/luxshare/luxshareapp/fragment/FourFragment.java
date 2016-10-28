package com.luxshare.luxshareapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luxshare.luxshareapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FourFragment extends Fragment {

    private View mRootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_four,null);
        }

        ViewGroup parent = (ViewGroup) mRootView.getParent();

        if (parent != null) {

            parent.removeView(mRootView);
        }

        return mRootView;
    }

}
