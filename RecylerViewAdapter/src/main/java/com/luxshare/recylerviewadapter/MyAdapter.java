package com.luxshare.recylerviewadapter;

import android.content.Context;

import com.luxshare.recylerviewadapter.adapter.BaseViewHolder;
import com.luxshare.recylerviewadapter.adapter.CommonAdapter;
import com.luxshare.recylerviewadapter.adapter.MultiItemTypeSupport;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public class MyAdapter<T> extends CommonAdapter<T> {

    protected MyAdapter(Context context, MultiItemTypeSupport<T> itemTypeSupport) {
        super(context, itemTypeSupport);
    }

    protected MyAdapter(Context context, int layoutId, List<T> list) {
        super(context, layoutId, list);
    }

    protected MyAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    protected MyAdapter(Context context, MultiItemTypeSupport<T> itemTypeSupport, List<T> list) {
        super(context, itemTypeSupport, list);
    }

    @Override
    protected void onBindViewholder(BaseViewHolder holder, T t, int position) {

    }
}
