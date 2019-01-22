package haodong.com.views_diy.myg11n27;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;

import java.io.IOException;

/**
 * @author linghailong
 * @date on 2018/12/28
 * @email 105354999@qq.com
 * @describe :
 */
public class MyPlayer implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener {
    private MediaPlayer mPlayer;
    private boolean hasPrepared;

    private void initIfNessary() {
        if (null == mPlayer) {
            mPlayer = new MediaPlayer();
            mPlayer.setOnErrorListener(this);
            mPlayer.setOnCompletionListener(this);
            mPlayer.setOnPreparedListener(this);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        hasPrepared = false;
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        hasPrepared = true;
    }

    public void play(Context context, String dataSource) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.prepareAsync();
    }
    public void start(){
        if (null!=mPlayer&&hasPrepared){
            mPlayer.start();
        }
    }
    public void pause(){
        if (null!=mPlayer&&hasPrepared){
            mPlayer.pause();
        }
    }

    public void seekTo(int position){
        if(null!=mPlayer&&hasPrepared){
            mPlayer.seekTo(position);
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
        hasPrepared=false;
        mPlayer.stop();
        mPlayer.release();
        mPlayer=null;
    }
}