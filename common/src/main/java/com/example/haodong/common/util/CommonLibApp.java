package com.example.haodong.common.util;

import android.app.Application;

/**
 * created by linghaoDo on 2019-11-28
 * description:
 * <p>
 * version:
 */
public class CommonLibApp extends Application {
    private static CommonLibApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static CommonLibApp getInstance() {
        return sInstance;
    }
}
