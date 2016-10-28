package com.luxshare.baseadapter.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/9/29.
 */
public class BaseViewHolder {

    private SparseArray<View> mViews;

    private int mPosition;

    private View mConvertView;

    private Context mContext;

    private BaseViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<>();
        this.mContext = context;
        mConvertView = LayoutInflater.from(context).inflate(layoutId, null);
        mConvertView.setTag(this);
    }

    /**
     * 获取ViewHolder对象
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static BaseViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new BaseViewHolder(context, parent, layoutId, position);
        } else {
            return (BaseViewHolder) convertView.getTag();
        }
    }

    /**
     * 获取convertView对象
     *
     * @return
     */
    public View getConvertView() {

        return mConvertView;
    }

    // 获取布局上面的View对象
    public <T extends View> T getView(int viewId) {

        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public int getPosition() {
        return mPosition;
    }


    /**
     * 为 TextView 设置文字
     *
     * @param viewId 控件Id
     * @param text   文字
     * @return
     */
    public BaseViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 为TextView设置文字
     *
     * @param viewId 控件Id
     * @param resId  文字资源
     * @return
     */
    public BaseViewHolder setText(int viewId, int resId) {
        TextView tv = getView(viewId);
        tv.setText(resId);
        return this;
    }

    /**
     * 为ImageView设置图片
     * @param viewId 控件Id
     * @param resId 资源Id
     * @return
     */
    public BaseViewHolder setImageWithDrawable(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }
    /**
     * 为ImageView设置图片
     * @param viewId 控件Id
     * @param bitmap 图片
     * @return
     */
    public BaseViewHolder setImageWithBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

}
