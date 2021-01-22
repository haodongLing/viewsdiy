package com.haodong.study.pracarouter;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * created by linghaoDo on 2021/1/21
 * description:
 * <p>
 * version:
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);
    }
}
