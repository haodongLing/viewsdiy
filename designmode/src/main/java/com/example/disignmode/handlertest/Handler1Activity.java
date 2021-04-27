package com.example.disignmode.handlertest;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.disignmode.R;
import com.example.haodong.common.util.LogUtil;

public class Handler1Activity extends AppCompatActivity {
    //    MyHandler<Handler1Activity> myHandler = new MyHandler<>(Looper.getMainLooper());
    private Handler mWorkHandler, mUiHandler;
    private DownLoadHandlerThread downLoadHandlerThread;
    private TextView mTvShow;
    private Button mJumpBtn;
    AlertDialog alertDialog;
    private LinearLayout mRootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler1);
        mTvShow = findViewById(R.id.handler1_tv);
        LogUtil.i("onCreate");
        mJumpBtn = findViewById(R.id.btn_jump_activity2);
        mRootLayout = findViewById(R.id.root_layout);

        mJumpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Handler1Activity.this, Handler2Activity.class));
            }
        });
        alertDialog = new AlertDialog.Builder(Handler1Activity.this)
                .setTitle("title")
                .setMessage("message")
                .setCancelable(true)
                .setPositiveButton("sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialog.cancel();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        alertDialog.cancel();
                    }
                }).create();
        findViewById(R.id.btn_show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.show();
            }
        });

        findViewById(R.id.btn_show_pop_up_window).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectPicPopupWindow window = new SelectPicPopupWindow(Handler1Activity.this);
                window.showAtLocation(mRootLayout, Gravity.CENTER, 0, 0);
            }
        });
//        mUiHandler = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                switch (msg.what) {
//                    case DownLoadHandlerThread.UPDATE_UI:
//                        String str = (String) msg.obj;
//                        LogUtil.i(Thread.currentThread().getName());
//                        mTvShow.setText(str);
//                        downLoadHandlerThread.quitSafely();
//                }
//            }
//        };
//        downLoadHandlerThread = new DownLoadHandlerThread("work thread");
//        downLoadHandlerThread.start();
//        downLoadHandlerThread.setmUIHandler(mUiHandler);
//        mWorkHandler=new Handler(downLoadHandlerThread.getLooper(),downLoadHandlerThread);
//        Message message =Message.obtain();
//        message.what=DownLoadHandlerThread.START_DOWNLOAD;
//        mWorkHandler.sendMessage(message);


    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.i("onRestart");
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LogUtil.i("onAttachedToWindow");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("onResume");
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
