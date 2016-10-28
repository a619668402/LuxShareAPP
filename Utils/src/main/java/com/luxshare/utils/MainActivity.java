package com.luxshare.utils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.luxshare.utils.utils.SystemUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String macStr = SystemUtils.getMac();
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Mac")
                .setMessage(macStr.replace(":",""))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
    }
}
