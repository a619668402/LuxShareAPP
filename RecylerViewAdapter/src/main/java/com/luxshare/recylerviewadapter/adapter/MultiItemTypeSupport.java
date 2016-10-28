package com.luxshare.recylerviewadapter.adapter;

/**
 * Created by Administrator on 2016/9/30.
 */
public interface MultiItemTypeSupport<T> {

    int getLayoutId(int viewType);

    int getItemViewType(int position, T t);
}
