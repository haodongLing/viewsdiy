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

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {

    }
}
