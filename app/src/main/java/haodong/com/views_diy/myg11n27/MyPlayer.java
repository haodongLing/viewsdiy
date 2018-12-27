package haodong.com.views_diy.myg11n27;

import android.media.MediaPlayer;

/**
 * @author linghailong
 * @date on 2018/12/28
 * @email 105354999@qq.com
 * @describe :
 */
public class MyPlayer implements MediaPlayer.OnPreparedListener,MediaPlayer.OnCompletionListener,
        MediaPlayer.OnErrorListener {
    private MediaPlayer mPlayer;
    private boolean hasPrepared;
    private void initIfNessary(){
        if (null==mPlayer){
            mPlayer=new MediaPlayer();
            mPlayer.setOnErrorListener(this);
            mPlayer.setOnCompletionListener(this);
            mPlayer.setOnPreparedListener(this);
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        hasPrepared=false;

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    /**
     * 准备完成
     * @param mp
     */
    @Override
    public void onPrepared(MediaPlayer mp) {
        hasPrepared=true;

    }
}
