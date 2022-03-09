package com.haodong.pracmodule.myipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2022/3/7
 * Description:
 */
public class ClientService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
