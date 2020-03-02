package com.example.disignmode.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import com.example.haodong.common.util.LogUtil;

/**
 * describe :
 * date on 2019/4/29
 * author linghailong
 * email 105354999@qq.com
 */
public class DownLoadHandlerThread extends HandlerThread  implements Handler.Callback{
    public static final int START_DOWNLOAD=0X01;
    public static final int UPDATE_UI=0X02;
    private Handler mUIHandler;

    public void setmUIHandler(Handler mUIHandler) {
        this.mUIHandler = mUIHandler;
    }

    public DownLoadHandlerThread(String name) {
        super(name);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg == null) {
            return false;
        }
        switch (msg.what){
            case START_DOWNLOAD:
                try {
                    LogUtil.i("current thread"+Thread.currentThread().getName());
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (mUIHandler!=null){
                    Message uiMsg=mUIHandler.obtainMessage();
                    uiMsg.what=UPDATE_UI;
                    String s ="下载完毕";
                    uiMsg.obj=s;
                    mUIHandler.sendMessage(uiMsg);
                }
                break;
        }
        return true;
    }
}
