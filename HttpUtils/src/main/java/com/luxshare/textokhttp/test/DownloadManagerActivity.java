package com.luxshare.textokhttp.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.luxshare.textokhttp.R;
import com.luxshare.textokhttp.test.adapter.ManagerAdapter;
import com.lzy.okserver.download.DownloadInfo;
import com.lzy.okserver.download.DownloadManager;
import com.lzy.okserver.download.DownloadService;
import com.lzy.okserver.task.ExecutorWithListener;

import java.util.List;

public class DownloadManagerActivity extends AppCompatActivity implements View.OnClickListener,ExecutorWithListener.OnAllTaskEndListener {

    private ListView mListView;

    private DownloadManager downloadManager;
    private List<DownloadInfo> allTask;
    private ManagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_manager);

        initViewById();
        initList();
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new ManagerAdapter(this,allTask,downloadManager);
        mListView.setAdapter(mAdapter);
    }

    private void initList() {
        downloadManager = DownloadService.getDownloadManager();
        allTask = downloadManager.getAllTask();
        downloadManager.getThreadPool().getExecutor().addOnAllTaskEndListener(this);
    }


    private void initViewById() {
        mListView = ((ListView) findViewById(R.id.listView));
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.removeAll:
                downloadManager.removeAllTask();
                for (DownloadInfo info: allTask) {

                }
                mAdapter.notifyDataSetChanged();
                break;
            case R.id.pauseAll:
                downloadManager.pauseAllTask();
                break;
            case R.id.stopAll:
                downloadManager.stopAllTask();
                break;
            case R.id.startAll:
                downloadManager.startAllTask();
                break;
        }
    }

    @Override
    public void onAllTaskEnd() {
        for (DownloadInfo info : allTask) {
            if (info.getState() != DownloadManager.FINISH) {
                Toast.makeText(this,"所有下载线程结束，部分下载未完成",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        Toast.makeText(DownloadManagerActivity.this, "所有下载任务完成", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //记得移除，否者会回调多次
        downloadManager.getThreadPool().getExecutor().removeOnAllTaskEndListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }
}
