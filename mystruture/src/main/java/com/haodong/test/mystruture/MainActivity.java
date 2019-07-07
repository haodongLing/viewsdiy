package com.haodong.test.mystruture;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IntentFilter filter = new IntentFilter();
        filter.addAction("Intent.ACTION_PACKAGE_REMOVED");
        MyReciver reciver = new MyReciver();
        registerReceiver(reciver,filter);
//        Class clz=new Cla
//        startActivity();
    }
}
