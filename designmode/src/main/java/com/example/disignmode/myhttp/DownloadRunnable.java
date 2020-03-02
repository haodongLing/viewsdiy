package com.example.disignmode.myhttp;

import android.util.Log;

import com.example.disignmode.myhttp.db.DaoManagerHelper;
import com.example.disignmode.myhttp.db.DownloadEntity;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.Response;

/**
 * describe :
 * date on 2019/5/2
 * author linghailong
 * email 105354999@qq.com
 */
public class DownloadRunnable implements Runnable {
    private static final int STATUS_DOWNLOADING = 1;
    private static final int STATUS_STOP = 2;
    private final long start;
    private final long stop;
    private final int threadId;
    private final String url;
    private final DownloadCallback mCallback;
    private int mStatus = STATUS_DOWNLOADING;
    private long mProgress;
    private DownloadEntity downloadEntity;

    public DownloadRunnable(String url, int threadId, long start, long stop, long progress, DownloadEntity
            downloadEntity, DownloadCallback downloadCallback) {
        this.url = url;
        this.threadId = threadId;
        this.start = start+progress;
        this.stop = stop;
        this.mProgress = progress;
        this.downloadEntity = downloadEntity;
        this.mCallback = downloadCallback;
    }

    @Override
    public void run() {
        RandomAccessFile randomAccessFile=null;
        InputStream inputStream = null;
        try{
            /*请求获取断点数据*/
            Response response=OkHttpManager.getDefault().syncResponse(url,start,stop);
            Log.e("TAG",this.toString());
//            BufferedSource bufferedSource=response.body().source();
//            /*写数据*/
//            File file=FileManager.getManager().getFile(url);
//            BufferedSink bufferedSink= Okio.buffer(Okio.sink(file));
//            int len=0;
//            byte[] buffer=new byte[8*1024];
//            while ((len=bufferedSource.read(buffer))!=-1){
//                if (mStatus==STATUS_STOP){
//                    break;
//                }
//                mProgress+=len;
//                bufferedSink.write(buffer,0,len);
//                bufferedSink.flush();
//            }
            inputStream = response.body().byteStream();
            // 写数据
            File file = FileManager.getManager().getFile(url);
            // 从这里开始
            randomAccessFile.seek(start);

            int len = 0;
            byte[] buffer = new byte[1024*10];

            while ((len = inputStream.read(buffer))!=-1){
                if(mStatus == STATUS_STOP)
                    break;
                // 保存进度，做断点 , 100kb
                mProgress += len;
                randomAccessFile.write(buffer,0,len);
            }

        } catch (IOException e) {
            e.printStackTrace();
            HttpUtils.close(inputStream);
            HttpUtils.close(randomAccessFile);
            // 存到数据库，数据库怎么存？
            downloadEntity.setProgress(mProgress);
//            DaoManagerHelper.getManager().addEntity(downloadEntity);
        }

    }

    @Override
    public String toString() {
        return "DownloadRunnable{" +
                "start=" + start +
                ", end=" + stop +
                ", threadId=" + threadId +
                ", url='" + url + '\'' +
                '}';
    }

    public void stop() {
        mStatus = STATUS_STOP;
    }
}
