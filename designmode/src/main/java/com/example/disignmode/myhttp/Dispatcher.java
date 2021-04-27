package com.example.disignmode.myhttp;

import androidx.annotation.NonNull;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * describe :
 * date on 2019/4/29
 * author linghailong
 * email 105354999@qq.com
 */
public class Dispatcher {
    ExecutorService executorService;
    public synchronized ExecutorService executorService(){
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>(), new ThreadFactory() {
                @Override
                public Thread newThread(@NonNull Runnable r) {
                    Thread thread = new Thread(r,"OkHttp");
                    thread.setDaemon(false);
                    return thread;
                }
            });
        }
        return executorService;
    }
//    public void enqueue(Rea)
}
