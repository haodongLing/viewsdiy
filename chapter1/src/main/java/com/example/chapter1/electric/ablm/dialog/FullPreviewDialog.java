package com.example.chapter1.electric.ablm.dialog;

import android.graphics.PixelFormat;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.tools.GradientTextView;

/**
 * description:
 * author: linghailong
 * date: 2019/3/22
 */
public class FullPreviewDialog extends AppCompatActivity implements View.OnClickListener {
    private TextView tvStart;
    private TextView tvNotNow;
    private GradientTextView gradientTextView1;
    private TextView tv1, tv2;
    private ImageView imgDelete;
    /*视频播放*/
    private SurfaceView mSurfaceView;
    private MyPlayer mMediaPlayer;
    private SurfaceHolder mHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("lhl", "onCreate: ");
        setContentView(R.layout.dialog_full);
       initView();
       initMedia();
    }

    private void initMedia() {
    }

    private void initView() {
        tvStart = findViewById(R.id.btn_start);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        // 设置文字
        Typeface typeface;
        try {
            typeface = Typeface.createFromAsset(getAssets(), "Roboto-Regular 2.ttf");
        } catch (Exception e) {
            typeface = Typeface.DEFAULT;
            e.printStackTrace();
        }
        tv1.setTypeface(typeface);
        tv2.setTypeface(typeface);
        mMediaPlayer = new MyPlayer();
        tvNotNow=findViewById(R.id.btn_not_now);
        /*初始化*/
        mSurfaceView = findViewById(R.id.dialog_full_surface_view);
        mHolder = mSurfaceView.getHolder();
        mHolder.setKeepScreenOn(true);
        mHolder.addCallback(new SurfaceViewLis());
        /*mediaplayer*/
        mSurfaceView.setZOrderMediaOverlay(true);
        mHolder.setFormat(PixelFormat.TRANSLUCENT);
        imgDelete = findViewById(R.id.img_close);

        imgDelete.setOnClickListener(this);
        tvStart.setOnClickListener(this);
        tvNotNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*tvStart*/
            case R.id.btn_start:

                break;
            case R.id.img_close:
                mMediaPlayer.release();
                finish();
                break;
            case R.id.btn_not_now:
                mMediaPlayer.release();
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    class SurfaceViewLis implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mMediaPlayer.play(FullPreviewDialog.this, "album_preview.mp4", mHolder);
            mMediaPlayer.start();
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            mMediaPlayer.release();
        }
    }
}
