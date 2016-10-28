package com.luxshare.textokhttp.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.luxshare.textokhttp.R;
import com.luxshare.textokhttp.test.utils.ApkUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.lzy.okserver.download.DownloadService;
import com.lzy.okserver.listener.DownloadListener;
import com.squareup.picasso.Picasso;

import java.io.File;

public class DesActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIcon;
    private TextView mName;
    private TextView mDownloadSize;
    private TextView mTvProgress;
    private TextView mNetSpeed;
    private ProgressBar mPbProgress;
    private Button mDownload;
    private Button mRemove;
    private Button mRestart;

    private DownloadInfo downloadInfo;
    private DownloadManager downloadManager;
    private ApkModel apk;
    private MyListener mListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_des);

        initApkModel();

        initViewById();

        initDownLoadManager();

        setView();
    }

    private void initApkModel() {
        apk = (ApkModel) getIntent().getExtras().get("apk");
    }


    private void initDownLoadManager() {
        mListener = new MyListener();
        downloadManager = DownloadService.getDownloadManager();
        downloadInfo = downloadManager.getDownloadInfo(apk.getUrl());
        if (downloadInfo != null) {
            //如果任务存在，把任务的监听换成当前页面需要的监听
            downloadInfo.setListener(mListener);
            //需要第一次手动刷一次，因为任务可能处于下载完成，暂停，等待状态，此时是不会回调进度方法的
            refreshUI(downloadInfo);
        }
    }

    private void setView() {
        Picasso.with(this).load(apk.getIconUrl()).error(R.mipmap.ic_launcher).into(mIcon);
        mName.setText(apk.getName());

    }

    private void initViewById() {
        mIcon = ((ImageView) findViewById(R.id.icon));
        mName = ((TextView) findViewById(R.id.name));
        mDownloadSize = ((TextView) findViewById(R.id.downloadSize));
        mTvProgress = ((TextView) findViewById(R.id.tvProgress));
        mNetSpeed = ((TextView) findViewById(R.id.netSpeed));
        mPbProgress = ((ProgressBar) findViewById(R.id.pbProgress));
        mDownload = ((Button) findViewById(R.id.start));
        mRemove = ((Button) findViewById(R.id.remove));
        mRestart = ((Button) findViewById(R.id.restart));


        mDownload.setOnClickListener(this);
        mRemove.setOnClickListener(this);
        mRestart.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        downloadInfo = downloadManager.getDownloadInfo(apk.getUrl());
        if (v.getId() == mDownload.getId()) {
            if (downloadInfo == null) {
                GetRequest request = OkGo.get(apk.getUrl());
                downloadManager.addTask(apk.getUrl(), request, mListener);
                return;
            }
            switch (downloadInfo.getState()) {
                case DownloadManager.PAUSE:
                case DownloadManager.NONE:
                case DownloadManager.ERROR:
                    downloadManager.addTask(downloadInfo.getUrl(), downloadInfo.getRequest(), mListener);
                    break;
                case DownloadManager.DOWNLOADING:
                    downloadManager.pauseTask(downloadInfo.getUrl());
                    break;
                case DownloadManager.FINISH:
                    if (ApkUtils.isAvailable(this, new File(downloadInfo.getTargetPath()))) {
                        ApkUtils.uninstall(this, ApkUtils.getPackageName(this, downloadInfo.getTargetPath()));
                    } else {
                        ApkUtils.install(this, new File(downloadInfo.getTargetPath()));
                    }
                    break;
            }
        } else if (v.getId() == mRemove.getId()) {
            if (downloadInfo == null) {
                Toast.makeText(this, "请先下载任务", Toast.LENGTH_SHORT).show();
                return;
            }
            downloadManager.removeTask(downloadInfo.getUrl());
            mDownloadSize.setText("--M/--M");
            mNetSpeed.setText("--/s");
            mTvProgress.setText("--.--%");
            mPbProgress.setProgress(0);
            mDownload.setText("下载");
        } else if (v.getId() == mRestart.getId()) {
            if (downloadInfo == null) {
                Toast.makeText(this, "请先下载任务", Toast.LENGTH_SHORT).show();
                return;
            }
            downloadManager.restartTask(downloadInfo.getUrl());
        }
    }

    private void refreshUI(DownloadInfo downloadInfo) {
        String downloadLength = Formatter.formatFileSize(this, downloadInfo.getDownloadLength());
        String totalLength = Formatter.formatFileSize(this, downloadInfo.getTotalLength());
        mDownloadSize.setText(downloadLength + "/" + totalLength);
        String netSpeed = Formatter.formatFileSize(this, downloadInfo.getNetworkSpeed());
        mNetSpeed.setText(netSpeed + "/s");
        mTvProgress.setText((Math.round(downloadInfo.getProgress() * 10000) * 1.0f / 100) + "%");
        mPbProgress.setMax((int) downloadInfo.getTotalLength());
        mPbProgress.setProgress((int) downloadInfo.getDownloadLength());

        switch (downloadInfo.getState()) {
            case DownloadManager.NONE:
                mDownload.setText("下载");
                break;
            case DownloadManager.DOWNLOADING:
                mDownload.setText("暂停");
                break;
            case DownloadManager.PAUSE:
                mDownload.setText("继续");
                break;
            case DownloadManager.WAITING:
                mDownload.setText("等待");
                break;
            case DownloadManager.ERROR:
                mDownload.setText("出错");
                break;
            case DownloadManager.FINISH:
                if (ApkUtils.isAvailable(DesActivity.this, new File(downloadInfo.getTargetPath()))) {
                    mDownload.setText("卸载");
                } else {
                    mDownload.setText("安装");
                }
                break;
        }
    }


    private class MyListener extends DownloadListener {

        @Override
        public void onProgress(DownloadInfo downloadInfo) {
            refreshUI(downloadInfo);
        }

        @Override
        public void onFinish(DownloadInfo downloadInfo) {
            System.out.println("onFinish");
            Toast.makeText(DesActivity.this, "下载完成:" + downloadInfo.getTargetPath(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(DownloadInfo downloadInfo, String errorMsg, Exception e) {
            System.out.println("onError");
            if (errorMsg != null)
                Toast.makeText(DesActivity.this, errorMsg, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (downloadInfo != null) {
            refreshUI(downloadInfo);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downloadInfo != null) {
            downloadInfo.removeListener();
        }
    }
}
