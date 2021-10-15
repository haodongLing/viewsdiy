package com.haodong.study.remoteapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

/**
 * created by linghaoDo on 2021/1/13
 * description:
 * <p>
 * version:
 */
public class MyRemoteService extends Service {
    BookManagerImpl bookManager = new BookManagerImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return bookManager.asBinder();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();

    }
    
}
