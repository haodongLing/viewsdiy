package com.example.disignmode.myhttp;

import android.content.Context;

import com.example.disignmode.myhttp.db.DaoManagerHelper;

/**
 * describe :
 * date on 2019/5/2
 * author linghailong
 * email 105354999@qq.com
 */
public class DownloadFacade {
    public static DownloadFacade sFacade=new DownloadFacade();

    private DownloadFacade() {
    }
    public static DownloadFacade getDefault(){
        return sFacade;
    }
    public void startDownLoad(String url,DownloadCallback downloadCallback){
        DownloadDispatcher.getDispatcher().startDownLoad(url,downloadCallback);
    }
    public void init(Context context){
        FileManager.getManager().init(context);
        DaoManagerHelper.getManager().init(context);
    }

}
