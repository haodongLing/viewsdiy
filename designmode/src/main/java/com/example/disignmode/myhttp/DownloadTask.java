package com.example.disignmode.myhttp;

import android.app.DownloadManager;

import com.example.disignmode.myhttp.db.DaoManagerHelper;
import com.example.disignmode.myhttp.db.DownloadEntity;

import java.io.File;
import java.io.IOException;
import java.net.PortUnreachableException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.OkHttpClient;

/**
 * describe :
 * date on 2019/5/2
 * author linghailong
 * email 105354999@qq.com
 */
public class DownloadTask {
    private String mUrl;
    private long mContentLength;
    private List<DownloadRunnable>mRunnables;
    // OkHttp 为什么搞一个能被回收的线程池？
    OkHttpClient client = new OkHttpClient();
    private ExecutorService executorService;
    private volatile int mSuccessedNumber;
    private DownloadCallback mCallback;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    // We want at least 2 threads and at most 4 threads in the core pool,
    // preferring to have 1 less than the CPU count to avoid saturating
    // the CPU with background work
    private static final int THREAD_SIZE = Math.max(2, Math.min(CPU_COUNT - 1, 4));
    private static final int KEEP_ALIVE_SECONDS = 30;

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "AsyncTask #" + mCount.getAndIncrement());
        }
    };
    public synchronized ExecutorService executorService(){
        if (executorService==null){
            executorService=new ThreadPoolExecutor(0,THREAD_SIZE,KEEP_ALIVE_SECONDS, TimeUnit.SECONDS,new
                    SynchronousQueue<Runnable>(),sThreadFactory);
        }
        return executorService;
    }

    public DownloadTask(String url, long contentLength, DownloadCallback callback) {
        this.mUrl = url;
        this.mContentLength = contentLength;
        mRunnables = new ArrayList<>();
        this.mCallback = callback;
    }
    public void init(){
        for (int i=0;i<THREAD_SIZE;i++){
            long threadSize=mContentLength/THREAD_SIZE;
            long start=i*threadSize;
            long end=(i+threadSize)-1;
            if (i == THREAD_SIZE - 1) {
                end = mContentLength - 1;
            }
            List<DownloadEntity>entities= DaoManagerHelper.getManager().queryAll(mUrl);
            DownloadEntity downloadEntity = getEntity(i, entities);
            if (downloadEntity == null) {
                downloadEntity = new DownloadEntity(start, end, mUrl, i, 0, mContentLength);
            }
            DownloadRunnable downloadRunnable=new DownloadRunnable(mUrl, i, start, end, downloadEntity.getProgress(),
                    downloadEntity, new DownloadCallback() {
                @Override
                public void onFailure(IOException e) {
                    mCallback.onFailure(e);
                }

                @Override
                public void onSucceed(File file) {
                    synchronized (DownloadTask.this) {
                        mSuccessedNumber += 1;
                        if (mSuccessedNumber == THREAD_SIZE) {
                            mCallback.onSucceed(file);
                            DownloadDispatcher.getDispatcher().recyclerTask(DownloadTask.this);
                            // 清楚数据库的这个文件下载存储

                        }
                    }
                }
            });
            // 通过线程池去执行
            executorService().execute(downloadRunnable);
        }

    }
    private DownloadEntity getEntity(int threadId, List<DownloadEntity> entities) {
        for (DownloadEntity entity : entities) {
            if (threadId == entity.getThreadId()) {
                return entity;
            }
        }
        return null;
    }
}
