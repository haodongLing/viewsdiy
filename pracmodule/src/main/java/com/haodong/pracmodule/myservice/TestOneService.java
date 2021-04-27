package com.haodong.pracmodule.myservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import androidx.annotation.Nullable;

import com.example.haodong.common.util.LogUtil;

/**
 * created by linghaoDo on 2020-03-11
 * description:
 * <p>
 * version:
 */
public class TestOneService extends Service {
    @Override
    public void onCreate() {
        LogUtil.d("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d("onStartCommand");
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        LogUtil.d("onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        LogUtil.d("onDestroy");
        super.onDestroy();
    }
}
