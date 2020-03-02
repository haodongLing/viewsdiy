package com.example.disignmode.myeventbus.MyEventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * describe :
 * date on 2019/4/28
 * author linghailong
 * email 105354999@qq.com
 */
public class MyEventbus {
    private final static ExecutorService DEFAULT_EXECUTOR_SERVICE = Executors.newCachedThreadPool();
    public static volatile MyEventbus defaultInstance;
    private static final EventBusBuilder DEFAULT_BUILDER = new EventBusBuilder();
    private static final Map<Class<?>, List<Class<?>>> eventTypesCache = new HashMap<>();
//    private final Map<Class<?>, CopyOnWriteArrayList<SubscriberMethod>>

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

    public MyEventbus() {
        this(DEFAULT_BUILDER);
    }

    MyEventbus(EventBusBuilder builder) {

    }
}
