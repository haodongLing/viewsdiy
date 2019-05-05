package com.example.chapter1.electric;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

/**
 * describe :
 * date on 2019/5/5
 * author linghailong
 * email 105354999@qq.com
 */
public class MyService extends IntentService{
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public MyService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
