package com.haodong.pracmodule.myipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.example.haodong.common.util.FFLog;
import com.example.haodong.common.util.LogUtil;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2022/3/7
 * Description:
 */
public class WebService extends Service {
    private IWeb2Main.Stub serverBinder=new IWeb2Main.Stub(){

        @Override
        public void handleWebAction(String actionName, String jsonParams, ICallback callback) throws RemoteException {
            FFLog.i("actionName-->"+actionName  +"jsonParams-->"+jsonParams);
            callback.onResult(111,"WebService 回调","dsfs");
        }
    };
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return serverBinder;
    }
}
