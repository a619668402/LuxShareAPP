package com.luxshare.recylerviewadapter.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public abstract class CommonAdapter<T> extends BaseCommonAdapter<T, BaseViewHolder> {

    protected CommonAdapter(Context context, MultiItemTypeSupport<T> itemTypeSupport) {
        super(context, itemTypeSupport);
    }

    protected CommonAdapter(Context context, MultiItemTypeSupport<T> itemTypeSupport, List<T> list) {
        super(context, itemTypeSupport, list);
    }

    protected CommonAdapter(Context context, int layoutId) {
        super(context, layoutId);
    }

    protected CommonAdapter(Context context, int layoutId, List<T> list) {
        super(context, layoutId, list);
    }
}
