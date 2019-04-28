package com.example.disignmode.myeventbus.MyEventBus;

import org.greenrobot.eventbus.EventBusBuilder;

/**
 * describe :
 * date on 2019/4/28
 * author linghailong
 * email 105354999@qq.com
 */
public class MyEventbus {
    public static volatile MyEventbus defaultInstance;

    public static MyEventbus getDefault() {
        if (defaultInstance == null) {
            synchronized (MyEventbus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new MyEventbus();
                }
            }
        }
        return defaultInstance;
    }
}
