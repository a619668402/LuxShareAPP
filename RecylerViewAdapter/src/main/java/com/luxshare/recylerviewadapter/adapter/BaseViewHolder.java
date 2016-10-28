package com.luxshare.recylerviewadapter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/30.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private LayoutInflater mInflater;

    private Context mContext;

    private SparseArray<View> mViews;

    private View mConvertView;

    public BaseViewHolder(View itemView) {

        super(itemView);
        this.mConvertView = itemView;
        this.mViews = new SparseArray<>();
    }


    /*public BaseViewHolder(Context context, View itemView, ViewParent group) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<>();
    }*/

    /**
     * 生成BaseViewHolder对象
     * @param context
     * @param parent
     * @param layoutId
     * @return
     *//*
    public static BaseViewHolder get(Context context, ViewGroup parent, int layoutId) {

        View view = LayoutInflater.from(context).inflate(layoutId, parent, false);

        BaseViewHolder holder = new BaseViewHolder(context, view, parent);

        return holder;
    }*/

    /**
     * 获取View
     * @param viewId
     * @param <T>
     * @return
     */
    protected <T extends View> T retrieveView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public <T extends View> T getView(int viewId) {

        return retrieveView(viewId);
    }

    /**
     * 为TextView设置文字
     * @param viewId
     * @param values
     * @return
     */
    public BaseViewHolder setText(int viewId, String values) {

        TextView tv = getView(viewId);
        tv.setText(values);
        return this;
    }

    public BaseViewHolder setButtonText(int viewId, String values) {

        Button btn = getView(viewId);
        btn.setText(values);
        return this;
    }

    /**
     * 为ImageView设置资源
     * @param viewId
     * @param resId
     * @return
     */
    public BaseViewHolder setImageResource(int viewId, int resId) {

        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    /**
     * 为View设置背景颜色
     * @param viewId
     * @param color
     * @return
     */
    public BaseViewHolder setBackgroundColor(int viewId, int color) {

        View view = getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    /**
     * 为View设置背景资源
     * @param viewId
     * @param resId
     * @return
     */
    public BaseViewHolder setBackgroundRes(int viewId, int resId) {

        View view = getView(viewId);
        view.setBackgroundResource(resId);
        return this;
    }

    /**
     * 为TextView文字设置颜色
     * @param viewId
     * @param color
     * @return
     */
    public BaseViewHolder setTextColor(int viewId, int color) {
        TextView tv = getView(viewId);
        tv.setTextColor(color);
        return this;
    }

    /**
     * 为ImageView设置图片
     * @param viewId
     * @param drawable
     * @return
     */
    public BaseViewHolder setImageDrawable(int viewId, Drawable drawable) {

        ImageView iv = getView(viewId);
        iv.setImageDrawable(drawable);
        return this;
    }

    /**
     * 为ImageView设置图片
     * @param viewId
     * @param bitmap
     * @return
     */
    public BaseViewHolder setImageBitmap(int viewId, Bitmap bitmap) {

        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置View的可见与不可见
     * @param viewId
     * @param visible
     * @return
     */
    public BaseViewHolder setVisible(int viewId, boolean visible) {

        View view = getView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    /**
     * 为progress设置进度
     * @param viewId
     * @param progress
     * @return
     */
    public BaseViewHolder setProgress(int viewId, int progress) {

        ProgressBar view = getView(viewId);
        view.setProgress(progress);
        return this;
    }

    /**
     * 给View设置点击监听事件
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {

        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 给View设置长按监听事件
     * @param viewId
     * @param listener
     * @return
     */
    public BaseViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {

        View view = getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }

    public BaseViewHolder linkify(int viewId) {
        TextView view = retrieveView(viewId);
        Linkify.addLinks(view, Linkify.ALL);
        return this;
    }


    public BaseViewHolder setTag(int viewId,Object tag) {

        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    /**
     * 获取一个convertView对象
     * @return
     */
    public View getConvertView() {

        return mConvertView;
    }

    /**
     * 根据id获取TextView
     * @param viewId
     * @return
     */
    public TextView getTextView(int viewId) {

        return getView(viewId);
    }

    /**
     * 根据id获取Button
     * @param viewId
     * @return
     */
    public Button getButton(int viewId) {

        return getView(viewId);
    }

    /**
     * 根据id获取ImageView
     * @param viewId
     * @return
     */
    public ImageView getImageViwe(int viewId) {

        return getView(viewId);
    }
}
