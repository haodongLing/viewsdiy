package com.example.disignmode.handlertest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.disignmode.R;
import com.example.haodong.common.util.LogUtil;

import java.util.concurrent.CountDownLatch;

public class Handler2Activity extends AppCompatActivity {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler2);
        LogUtil.i("onCreate");

    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.i("onAttachedToWindow");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i("onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i("onDestroy");
    }
}
