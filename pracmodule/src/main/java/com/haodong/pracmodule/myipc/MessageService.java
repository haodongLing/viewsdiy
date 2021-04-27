package com.haodong.pracmodule.myipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.annotation.Nullable;

/**
 * created by linghaoDo on 2020-03-29
 * description:
 * <p>
 * version:
 */
public class MessageService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
   private final UserAidl.Stub mBinder=new UserAidl.Stub() {
       @Override
       public String getUserName() throws RemoteException {
           return "linghaoDo";
       }

       @Override
       public String getUserPwd() throws RemoteException {
           return "1052354999";
       }
   };
}
