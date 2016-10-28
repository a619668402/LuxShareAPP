package com.luxshare.recylerviewadapter;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.luxshare.recylerviewadapter.adapter.BaseViewHolder;
import com.luxshare.recylerviewadapter.adapter.CommonAdapter;
import com.luxshare.recylerviewadapter.adapter.MultiItemTypeSupport;

import java.util.List;

/**
 * Created by Administrator on 2016/9/30.
 */
public class MutilAdapter extends CommonAdapter<News> {

    private Context mContext;

    protected MutilAdapter(Context context, MultiItemTypeSupport<News> itemTypeSupport) {
        super(context, itemTypeSupport);
        this.mContext = context;
    }

    protected MutilAdapter(Context context, MultiItemTypeSupport<News> itemTypeSupport, List<News> list) {
        super(context, itemTypeSupport, list);
        this.mContext = context;
    }

    protected MutilAdapter(Context context, int layoutId) {
        super(context, layoutId);
        this.mContext = context;
    }

    protected MutilAdapter(Context context, int layoutId, List<News> list) {
        super(context, layoutId, list);
        this.mContext = context;
    }

    @Override
    protected void onBindViewholder(final BaseViewHolder holder, News news, int position) {
        switch (holder.getItemViewType()) {

            case News.ITEM_TYPE_BUTTON:
                holder.setButtonText(R.id.muil_btn, news.getButton());
                holder.setOnClickListener(R.id.muil_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "你点击了按钮" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;

            case News.ITEM_TYPE_IMAGE:
                holder.setImageResource(R.id.muil_iv, news.getImage());
                break;

            case News.ITEM_TYPE_TEXT:
                holder.setText(R.id.mutil_tv, news.getText());
                break;
        }
    }

}
