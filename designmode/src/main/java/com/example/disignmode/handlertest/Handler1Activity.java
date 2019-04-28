package com.example.disignmode.handlertest;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.disignmode.R;

import java.lang.ref.WeakReference;

public class Handler1Activity extends AppCompatActivity {
    MyHandler<Handler1Activity>myHandler=new MyHandler<>(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Looper.loop();
            }
        }).start();

    }

    static class MyHandler<T extends Handler1Activity> extends Handler {
        WeakReference<T>weakReference;

        public MyHandler(Looper mainLooper) {
            super(mainLooper);
        }

        public void setWeakReference(T t){
            weakReference=new WeakReference<>(t);
        }
        Handler1Activity handler1Activity=weakReference.get();
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    break;
            }
        }
    }
}
