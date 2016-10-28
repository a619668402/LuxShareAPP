package com.luxshare.nohttp.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.luxshare.nohttp.R;
import com.yyydjk.library.DropDownMenu;

public class Main3Activity extends AppCompatActivity {

    private DropDownMenu mMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mMenu = (DropDownMenu) findViewById(R.id.ddmenu);

//        mMenu.setDropDownMenu();
    }
}
