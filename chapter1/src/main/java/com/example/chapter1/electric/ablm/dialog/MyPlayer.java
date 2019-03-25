package com.example.chapter1.electric.ablm.dialog;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

/**
 * @Auther linghailong
 * created at 2018/12/28
 * @duscribe:
 */
public class MyPlayer implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener,MediaPlayer.OnInfoListener {
    private MediaPlayer mPlayer;
    private boolean hasPrepared;
//    private SurfaceView mSurfaceView;

    private void initIfNessary() {
        if (null == mPlayer) {
            mPlayer = new MediaPlayer();
            mPlayer.setOnErrorListener(this);
            mPlayer.setOnCompletionListener(this);
            mPlayer.setOnPreparedListener(this);
            mPlayer.setOnInfoListener(this);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        this.seekTo(0);
        this.start();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        hasPrepared = true;
        start();
    }

    interface OnPreparedCompatListener{
        void onPrepared();
    }


    public void play(Context context, String dataSource, SurfaceHolder holder ) {
        hasPrepared = false;
        initIfNessary();
        //设置音频流类型
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        // 设置播放的视频源
        AssetFileDescriptor fd = null;
        try {
            fd = context.getAssets().openFd(dataSource);
            mPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(),
                    fd.getLength());
            mPlayer.setDisplay(holder);
            mPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void start(){
        if (null!=mPlayer){
            mPlayer.start();
        }
    }
    public void pause(){
        if (null!=mPlayer&&hasPrepared){
            mPlayer.pause();
        }
    }

    /**
     * 绑定surfaceHolder
     * @param holder
     */
    public void setDisplay(SurfaceHolder holder){
        if (null!=mPlayer){
            mPlayer.setDisplay(holder);
        }
    }
    /**
     * 释放资源
     */
    public void release(){
        if (mPlayer!=null){
            hasPrepared=false;
            mPlayer.stop();
            mPlayer.release();
            mPlayer=null;
        }

    }

    private OnPreparedCompatListener listener;

    public void setOnPreparedCompatListener(OnPreparedCompatListener listener){
        this.listener = listener;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        if(what == mp.MEDIA_INFO_VIDEO_RENDERING_START){
            //隐藏缩略图
//            ivBg.setVisibility(View.GONE);
        }

        return false;
    }
    public void seekTo(int position){
        if(null!=mPlayer&&hasPrepared){
            mPlayer.seekTo(position);
        }
    }

}