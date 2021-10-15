package com.haodong.study.remoteapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.time.chrono.MinguoDate;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ThreadLocal<MinguoDate> threadLocal=new ThreadLocal<>();

    }
}