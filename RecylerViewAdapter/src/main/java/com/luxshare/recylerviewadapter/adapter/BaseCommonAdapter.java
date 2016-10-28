package com.luxshare.recylerviewadapter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public abstract class BaseCommonAdapter<T, H extends BaseViewHolder> extends RecyclerView.Adapter<BaseViewHolder> implements View.OnClickListener {

    private Context mContext;

    private List<T> mList;

    private int mLayoutId;

    private OnRecyclerViewItemClickListener mItemClickListener;

    private OnRecyclerViewItemLongClickListener mLongClickListener;

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;


    public void setItemClickListener(OnRecyclerViewItemClickListener itemClickListener) {
        mItemClickListener = itemClickListener;
    }

    public void setLongClickListener(OnRecyclerViewItemLongClickListener longClickListener) {
        mLongClickListener = longClickListener;
    }

    protected BaseCommonAdapter(Context context, int layoutId) {

        this(context, layoutId, null);
    }

    protected BaseCommonAdapter(Context context, int layoutId, List<T> list) {
        mContext = context;
        mLayoutId = layoutId;
        mList = list == null ? (List<T>) new ArrayList<>() : list;
    }

    protected BaseCommonAdapter(Context context, MultiItemTypeSupport<T> itemTypeSupport) {

        this(context, itemTypeSupport, null);
    }

    protected BaseCommonAdapter(Context context, MultiItemTypeSupport<T> itemTypeSupport, List<T> list) {

        this.mContext = context;
        this.mList = list == null ? (List<T>) new ArrayList<>() : new ArrayList<>(list);
        this.mMultiItemTypeSupport = itemTypeSupport;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public T getItem(int position) {

        if (position > mList.size()) {

            return null;
        }
        return mList.get(position);
    }

    @Override
    public int getItemViewType(int position) {

        if (mMultiItemTypeSupport != null) {

            return mMultiItemTypeSupport.getItemViewType(position, getItem(position));
        }
        return super.getItemViewType(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;
        if (mMultiItemTypeSupport != null) {

            int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);

            view = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
        } else {

            view = LayoutInflater.from(mContext).inflate(mLayoutId, parent, false);
        }
        view.setOnClickListener(this);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        holder.itemView.setTag(position);
        onBindViewholder(holder, mList.get(position), position);
    }

    protected abstract void onBindViewholder(BaseViewHolder holder, T t, int position);

    @Override
    public void onClick(View v) {

        if (mItemClickListener != null) {
            mItemClickListener.onItemClick(v, (Integer) v.getTag());
        }
    }

    public void add(T elem, int position) {

        mList.add(position, elem);
        notifyItemInserted(position);
    }

    public void addAll(Collection<? extends T> list) {

        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void set(T oldElem, T newElem) {

        set(mList.indexOf(oldElem), newElem);
    }

    public void set(int index, T elem) {

        mList.set(index, elem);
        notifyItemChanged(index);
    }

    public void remove(int position) {

        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void remove(T elem) {

        mList.remove(elem);
        notifyItemRemoved(mList.indexOf(elem));
    }


    public void repleaceAll(Collection<? extends T> elem) {

        mList.clear();
        mList.addAll(elem);
        notifyDataSetChanged();
    }

    public boolean contains(T elem) {

        return mList.contains(elem);
    }

    public void clear() {

        mList.clear();
        notifyDataSetChanged();
    }

    // 点击事件
    public static interface OnRecyclerViewItemClickListener<T> {

        void onItemClick(View view, int position);
    }

    // 长按事件
    public static interface OnRecyclerViewItemLongClickListener<T> {

        void onItemLongClick(View view, int position);
    }
}
