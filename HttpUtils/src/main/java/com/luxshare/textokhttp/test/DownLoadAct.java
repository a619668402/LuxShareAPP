package com.luxshare.textokhttp.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.luxshare.textokhttp.R;
import com.luxshare.textokhttp.test.adapter.MyAdapter;
import com.lzy.okserver.download.DownloadManager;
import com.lzy.okserver.download.DownloadService;

import java.io.File;
import java.util.ArrayList;

public class DownLoadAct extends AppCompatActivity {


    private TextView targetFolder;
    private TextView tvCorePoolSize;
    private SeekBar sbCorePoolSize;
    private Button openManager;
    private ListView recyclerView;

    private ArrayList<ApkModel> apks;
    private DownloadManager downloadManager;

    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_down_load);

        initData();
        initViewById();
        initDownLoadManager();
        targetFolder.setText("下载路径: " + downloadManager.getTargetFolder());
        initAdapter();
    }

    private void initAdapter() {
        mAdapter = new MyAdapter(this,apks,downloadManager);
        recyclerView.setAdapter(mAdapter);
    }

    private void initDownLoadManager() {
        downloadManager = DownloadService.getDownloadManager();
        sbCorePoolSize.setMax(5);
        sbCorePoolSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                downloadManager.getThreadPool().setCorePoolSize(progress);
                tvCorePoolSize.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sbCorePoolSize.setProgress(3);
    }

    private void initViewById() {

        targetFolder = ((TextView) findViewById(R.id.targetFolder));
        tvCorePoolSize = ((TextView) findViewById(R.id.tvCorePoolSize));
        sbCorePoolSize = ((SeekBar) findViewById(R.id.sbCorePoolSize));
        openManager = ((Button) findViewById(R.id.openManager));
        recyclerView = ((ListView) findViewById(R.id.recyclerView));

        openManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DownloadManagerActivity.class));
            }
        });
    }

    public String getSDPath() {
        File sdDir = null;
        // 判断SD卡是否存在
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory(); // 获取根目录
        }
        return sdDir.toString();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void initData() {
        apks = new ArrayList<>();
        ApkModel apkInfo1 = new ApkModel();
        apkInfo1.setName("美丽加");
        apkInfo1.setIconUrl("http://pic3.apk8.com/small2/14325422596306671.png");
        apkInfo1.setUrl("http://download.apk8.com/d2/soft/meilijia.apk");
        apks.add(apkInfo1);
        ApkModel apkInfo2 = new ApkModel();
        apkInfo2.setName("果然方便");
        apkInfo2.setIconUrl("http://pic3.apk8.com/small2/14313175771828369.png");
        apkInfo2.setUrl("http://download.apk8.com/d2/soft/guoranfangbian.apk");
        apks.add(apkInfo2);
        ApkModel apkInfo3 = new ApkModel();
        apkInfo3.setName("薄荷");
        apkInfo3.setIconUrl("http://pic3.apk8.com/small2/14308183888151824.png");
        apkInfo3.setUrl("http://download.apk8.com/d2/soft/bohe.apk");
        apks.add(apkInfo3);
        ApkModel apkInfo4 = new ApkModel();
        apkInfo4.setName("GG助手");
        apkInfo4.setIconUrl("http://pic3.apk8.com/small2/14302008166714263.png");
        apkInfo4.setUrl("http://download.apk8.com/d2/soft/GGzhushou.apk");
        apks.add(apkInfo4);
        ApkModel apkInfo5 = new ApkModel();
        apkInfo5.setName("红包惠锁屏");
        apkInfo5.setIconUrl("http://pic3.apk8.com/small2/14307106593913848.png");
        apkInfo5.setUrl("http://download.apk8.com/d2/soft/hongbaohuisuoping.apk");
        apks.add(apkInfo5);
        ApkModel apkInfo6 = new ApkModel();
        apkInfo6.setName("快的打车");
        apkInfo6.setIconUrl("http://up.apk8.com/small1/1439955061264.png");
        apkInfo6.setUrl("http://download.apk8.com/soft/2015/%E5%BF%AB%E7%9A%84%E6%89%93%E8%BD%A6.apk");
        apks.add(apkInfo6);
        ApkModel apkInfo7 = new ApkModel();
        apkInfo7.setName("叮当快药");
        apkInfo7.setIconUrl("http://pic3.apk8.com/small2/14315954626414886.png");
        apkInfo7.setUrl("http://d2.apk8.com:8020/soft/dingdangkuaiyao.apk");
        apks.add(apkInfo7);
        ApkModel apkInfo8 = new ApkModel();
        apkInfo8.setName("悦跑圈");
        apkInfo8.setIconUrl("http://pic3.apk8.com/small2/14298490191525146.jpg");
        apkInfo8.setUrl("http://d2.apk8.com:8020/soft/yuepaoquan.apk");
        apks.add(apkInfo8);
        ApkModel apkInfo9 = new ApkModel();
        apkInfo9.setName("悠悠导航");
        apkInfo9.setIconUrl("http://pic3.apk8.com/small2/14152456988840667.png");
        apkInfo9.setUrl("http://d2.apk8.com:8020/soft/%E6%82%A0%E6%82%A0%E5%AF%BC%E8%88%AA2.3.32.1.apk");
        apks.add(apkInfo9);
        ApkModel apkInfo10 = new ApkModel();
        apkInfo10.setName("虎牙直播");
        apkInfo10.setIconUrl("http://up.apk8.com/small1/1439892235841.jpg");
        apkInfo10.setUrl("http://download.apk8.com/down4/soft/hyzb.apk");
        apks.add(apkInfo10);
    }

}
