package com.example.disignmode.myhttp;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * describe : 进行下载任务的分发
 * date on 2019/5/2
 * author linghailong
 * email 105354999@qq.com
 */
public class DownloadDispatcher  {
    private static final  DownloadDispatcher sDispatcher=new DownloadDispatcher();
    /** Ready async calls in the order they'll be run. */
    private final Deque<DownloadTask> readyTasks = new ArrayDeque<>();

    /** Running asynchronous calls. Includes canceled calls that haven't finished yet. */
    private final Deque<DownloadTask> runningTasks = new ArrayDeque<>();

    /** Running synchronous calls. Includes canceled calls that haven't finished yet. */
    private final Deque<DownloadTask> stopTasks = new ArrayDeque<>();

    private DownloadDispatcher() {
    }
    public static DownloadDispatcher getDispatcher(){
         return sDispatcher;
    }
    public void startDownLoad(String url,DownloadCallback callback){
        Call call=OkHttpManager.getDefault().asyncCall(url);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callback.onFailure(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取body的大小。
                long contentLength=response.body().contentLength();
                if (contentLength<=-1){
                    return;
                }
                DownloadTask downloadTask=new DownloadTask(url,contentLength,callback);
                downloadTask.init();
                runningTasks.add(downloadTask);
            }
        });
    }

    public void recyclerTask(DownloadTask downloadTask) {
        runningTasks.remove(downloadTask);
    }
}
