package com.haodong.study.remoteapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import java.time.chrono.MinguoDate;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler=new Handler(Looper.getMainLooper());

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThreadLocal<MinguoDate> threadLocal=new ThreadLocal<>();
//        mHandler.getLooper().getQueue().addIdleHandler();

    }
}