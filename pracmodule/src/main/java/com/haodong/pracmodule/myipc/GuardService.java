package com.haodong.pracmodule.myipc;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * created by linghaoDo on 2020-03-29
 * description: 守护进程
 * <p>
 * version:
 */
public class GuardService extends Service {
    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
