package com.example.disignmode.handlertest;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.disignmode.R;
import com.example.haodong.common.util.LogUtil;

import java.lang.ref.WeakReference;

public class Handler1Activity extends AppCompatActivity {
//    MyHandler<Handler1Activity> myHandler = new MyHandler<>(Looper.getMainLooper());
    private Handler mWorkHandler, mUiHandler;
    private DownLoadHandlerThread downLoadHandlerThread;
    private TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler1);
        mTvShow = findViewById(R.id.handler1_tv);

        mUiHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case DownLoadHandlerThread.UPDATE_UI:
                        String str = (String) msg.obj;
                        LogUtil.i(Thread.currentThread().getName());
                        mTvShow.setText(str);
                        downLoadHandlerThread.quitSafely();
                }
            }
        };
        downLoadHandlerThread = new DownLoadHandlerThread("work thread");
        downLoadHandlerThread.start();
        downLoadHandlerThread.setmUIHandler(mUiHandler);
        mWorkHandler=new Handler(downLoadHandlerThread.getLooper(),downLoadHandlerThread);
        Message message =Message.obtain();
        message.what=DownLoadHandlerThread.START_DOWNLOAD;
        mWorkHandler.sendMessage(message);
    }

//    static class MyHandler<T extends Handler1Activity> extends Handler {
//        WeakReference<T> weakReference;
//
//        public MyHandler(Looper mainLooper) {
//            super(mainLooper);
//        }
//
//        public void setWeakReference(T t) {
//            weakReference = new WeakReference<>(t);
//            Handler1Activity handler1Activity = weakReference.get();
//        }
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    break;
//                case 1:
//                    break;
//            }
//        }
//    }
}
