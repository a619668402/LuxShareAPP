package com.luxshare.baseadapter.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/9/29.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    private Context mContext;

    private LayoutInflater mInflater;

    private List<T> mList;

    private int mLayoutId;

    public CommonAdapter(Context context, List<T> list, int layoutId) {
        this.mContext = context;
        this.mList = list;
        this.mLayoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder = getHolder(mContext, convertView, parent, mLayoutId, position);
        convert(holder,getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(BaseViewHolder holder, T item);

    public BaseViewHolder getHolder(Context context, View convertView, ViewGroup parent, int layoutId, int position) {

        return BaseViewHolder.get(mContext, convertView, parent, mLayoutId, position);
    }

}
