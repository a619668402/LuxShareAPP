package com.luxshare.textokhttp.test.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.luxshare.textokhttp.R;
import com.luxshare.textokhttp.test.ApkModel;
import com.luxshare.textokhttp.test.DesActivity;
import com.luxshare.textokhttp.test.utils.AppCacheUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.download.DownloadManager;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
public class MyAdapter extends BaseAdapter {
    private Context mContext;
    private List<ApkModel> mList;
    private DownloadManager mManager;

    public MyAdapter(Context context, List<ApkModel> list, DownloadManager manager) {
        this.mContext = context;
        this.mList = list;
        this.mManager = manager;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_download_details, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        ApkModel model = mList.get(position);
        holder.bind(model, mManager, mContext);
        return convertView;
    }

    static class ViewHolder implements View.OnClickListener {

        private TextView mName;
        private Button mDownLoad;
        private ImageView mIcon;

        private ApkModel mModel;
        private DownloadManager mManager;
        private Context mContext;
        private View mConvertView;

        public ViewHolder(View convertView) {
            this.mConvertView = convertView;
            mName = ((TextView) convertView.findViewById(R.id.name));
            mDownLoad = ((Button) convertView.findViewById(R.id.download));
            mIcon = ((ImageView) convertView.findViewById(R.id.icon));
        }

        public void bind(ApkModel model, DownloadManager manager, Context context) {
            this.mModel = model;
            this.mManager = manager;
            this.mContext = context;
            if (manager.getDownloadInfo(model.getUrl()) != null) {
                mDownLoad.setText("已在队列");
                mDownLoad.setEnabled(false);
            } else {
                mDownLoad.setText("下载");
                mDownLoad.setEnabled(true);
            }

            mName.setText(model.getName());
            Picasso.with(mContext).load(model.getIconUrl()).error(R.mipmap.ic_launcher).into(mIcon);
            mDownLoad.setOnClickListener(this);
            mConvertView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.download) {
                if (mManager.getDownloadInfo(mModel.getUrl()) != null) {
                    Toast.makeText(mContext, "任务已经在下载列表中", Toast.LENGTH_SHORT).show();
                } else {
                    GetRequest request = OkGo.get(mModel.getUrl());
                    mManager.addTask(mModel.getUrl(), request, null);
                    AppCacheUtils.getInstance(mContext).put(mModel.getUrl(), mModel);
                    mDownLoad.setText("已在队列");
                    mDownLoad.setEnabled(false);
                }
            } else {
                Intent intent = new Intent(mContext, DesActivity.class);
                intent.putExtra("apk", mModel);
                mContext.startActivity(intent);
            }
        }
    }
}
