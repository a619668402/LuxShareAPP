package com.luxshare.textokhttp.test.adapter;

import android.content.Context;
import android.text.format.Formatter;
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
import com.luxshare.textokhttp.test.utils.ApkUtils;
import com.luxshare.textokhttp.test.utils.AppCacheUtils;
import com.luxshare.textokhttp.test.view.NumberProgressBar;
import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.lzy.okserver.listener.DownloadListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/10/25.
 */
public class ManagerAdapter extends BaseAdapter {

    private Context mContext;

    private DownloadManager mManager;

    private List<DownloadInfo> mList;

    public ManagerAdapter(Context context, List<DownloadInfo> list, DownloadManager manager) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_download_manager, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        DownloadInfo downloadInfo = (DownloadInfo) getItem(position);
        holder.refresh(downloadInfo, mContext, mManager, this);

        //对于非进度更新的ui放在这里，对于实时更新的进度ui，放在holder中
        ApkModel apk = (ApkModel) AppCacheUtils.getInstance(mContext).getObject(downloadInfo.getUrl());
        if (apk != null) {
            Picasso.with(mContext).load(apk.getIconUrl()).error(R.mipmap.ic_launcher).into(holder.mIcon);
            holder.mName.setText(apk.getName());
        } else {
            holder.mName.setText(downloadInfo.getFileName());
        }

        MyDownLoadListener downloadListener = new MyDownLoadListener();
        downloadListener.setUserTag(holder);
        downloadInfo.setListener(downloadListener);
        return convertView;
    }

    static class ViewHolder implements View.OnClickListener {

        private Button mDownload;
        private Button mRemove;
        private Button mRestart;
        private DownloadInfo mDownloadInfo;
        private ImageView mIcon;
        private TextView mName;
        private TextView mDownloadSize;
        private TextView mTvProgress;
        private TextView mNetSpeed;
        private NumberProgressBar mPbProgress;
        private Context mContext;
        private DownloadManager mManager;
        private ManagerAdapter mAdapter;

        public ViewHolder(View convertView) {
            mIcon = (ImageView) convertView.findViewById(R.id.icon);
            mName = ((TextView) convertView.findViewById(R.id.name));
            mDownloadSize = ((TextView) convertView.findViewById(R.id.downloadSize));
            mTvProgress = ((TextView) convertView.findViewById(R.id.tvProgress));
            mNetSpeed = (TextView) convertView.findViewById(R.id.netSpeed);
            mPbProgress = (NumberProgressBar) convertView.findViewById(R.id.pbProgress);
            mDownload = ((Button) convertView.findViewById(R.id.start));
            mRemove = ((Button) convertView.findViewById(R.id.remove));
            mRestart = ((Button) convertView.findViewById(R.id.restart));
        }

        public void refresh(DownloadInfo downloadInfo, Context context, DownloadManager manager, ManagerAdapter adapter) {
            this.mDownloadInfo = downloadInfo;
            this.mContext = context;
            this.mManager = manager;
            this.mAdapter = adapter;
            refresh();
        }

        //对于实时更新的进度ui，放在这里，例如进度的显示，而图片加载等，不要放在这，会不停的重复回调
        //也会导致内存泄漏
        private void refresh() {
            String downloadLength = Formatter.formatFileSize(mContext, mDownloadInfo.getDownloadLength());
            String totalLength = Formatter.formatFileSize(mContext, mDownloadInfo.getTotalLength());
            mDownloadSize.setText(downloadLength + "/" + totalLength);
            if (mDownloadInfo.getState() == DownloadManager.NONE) {
                mNetSpeed.setText("停止");
                mDownload.setText("下载");
            } else if (mDownloadInfo.getState() == DownloadManager.PAUSE) {
                mNetSpeed.setText("暂停中");
                mDownload.setText("继续");
            } else if (mDownloadInfo.getState() == DownloadManager.ERROR) {
                mNetSpeed.setText("下载出错");
                mDownload.setText("出错");
            } else if (mDownloadInfo.getState() == DownloadManager.WAITING) {
                mNetSpeed.setText("等待中");
                mDownload.setText("等待");
            } else if (mDownloadInfo.getState() == DownloadManager.FINISH) {
                if (ApkUtils.isAvailable(mContext, new File(mDownloadInfo.getTargetPath()))) {
                    mDownload.setText("卸载");
                } else {
                    mDownload.setText("安装");
                }
            } else if (mDownloadInfo.getState() == DownloadManager.DOWNLOADING) {
                String networkSpeed = Formatter.formatFileSize(mContext, mDownloadInfo.getNetworkSpeed());
                mNetSpeed.setText(networkSpeed + "/s");
                mDownload.setText("暂停");
            }
            mTvProgress.setText((Math.round(mDownloadInfo.getProgress() * 10000) * 1.0f / 100) + "%");
            mPbProgress.setMax((int) mDownloadInfo.getTotalLength());
            mPbProgress.setProgress((int) mDownloadInfo.getDownloadLength());

            mDownload.setOnClickListener(this);
            mRestart.setOnClickListener(this);
            mRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.start) {
                switch (mDownloadInfo.getState()) {
                    case DownloadManager.PAUSE:
                    case DownloadManager.NONE:
                    case DownloadManager.ERROR:
                        mManager.addTask(mDownloadInfo.getUrl(), mDownloadInfo.getRequest(), mDownloadInfo.getListener());
                        break;
                    case DownloadManager.DOWNLOADING:
                        mManager.pauseTask(mDownloadInfo.getUrl());
                        break;
                    case DownloadManager.FINISH:
                        if (ApkUtils.isAvailable(mContext, new File(mDownloadInfo.getTargetPath()))) {
                            ApkUtils.uninstall(mContext, ApkUtils.getPackageName(mContext, mDownloadInfo.getTargetPath()));
                        } else {
                            ApkUtils.install(mContext, new File(mDownloadInfo.getTargetPath()));
                        }
                        break;
                }
                refresh();
            } else if (v.getId() == R.id.remove) {
                mManager.removeTask(mDownloadInfo.getUrl());
                mAdapter.notifyDataSetChanged();
            } else if (v.getId() == R.id.restart) {
                mManager.restartTask(mDownloadInfo.getUrl());
            }
        }
    }

    private class MyDownLoadListener extends DownloadListener {

        @Override
        public void onProgress(DownloadInfo downloadInfo) {
            if (getUserTag() == null) return;
            ViewHolder holder = (ViewHolder) getUserTag();
            holder.refresh();
        }

        @Override
        public void onFinish(DownloadInfo downloadInfo) {
            Toast.makeText(mContext, "下载完成:" + downloadInfo.getTargetPath(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(DownloadInfo downloadInfo, String errorMsg, Exception e) {
            if (errorMsg != null) {
                Toast.makeText(mContext, errorMsg, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
