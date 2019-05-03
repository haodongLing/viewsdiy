package com.haodong.practice.rxjava;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 请求网络图片，并且添加图片
 */
public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    MyHandler<MainActivity> myHandler;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHandler = new MyHandler<>(this);
        imageView = findViewById(R.id.image);

        // 开启线程
        /*2. 获取 bitmap*/
        /*3. 添加水印*/
        /*4. 设置图片*/
        Observable.just("http://ww1.sinaimg.cn/large/006fCF3Ply1g2o5jxkphzj30go0b475q.jpg")
                .map(new Function<String, Bitmap>() {
                    @Override
                    public Bitmap apply(String s) throws Exception {
                        URL url = new URL(s);
                        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                        InputStream inputStream = urlConnection.getInputStream();
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        return bitmap;
                        /*添加水印*/
                    }
                })
                .map(new Function<Bitmap, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull Bitmap bitmap) throws Exception {
                        bitmap = createWatermark(bitmap, "RxJava2.0");
                        return bitmap;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bitmap>() {
                    @Override
                    public void accept(Bitmap bitmap) throws Exception {
                        imageView.setImageBitmap(bitmap);
                    }
                });
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                URL url = null;
//                HttpURLConnection urlConnection;
//                try {
//                    url = new URL("http://ww1.sinaimg.cn/large/006fCF3Ply1g2o5jxkphzj30go0b475q.jpg");
//                    urlConnection = (HttpURLConnection) url.openConnection();
//                    InputStream inputStream = urlConnection.getInputStream();
//                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                    bitmap = createWatermark(bitmap, "RxJava2.0");
//                    // 显示到图片
//                    Message message = Message.obtain();
//                    message.obj = bitmap;
//                    message.what = 1;
//                    myHandler.sendMessage(message);
//                    /*添加水印*/
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


    }

    private static class MyHandler<T extends MainActivity> extends Handler {
        private WeakReference<T> weakReference;
        private T mActivity;

        public MyHandler(T t) {
            weakReference = new WeakReference<T>(t);
            mActivity = weakReference.get();
        }

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    mActivity.imageView.setImageBitmap(bitmap);
            }
        }
    }

    private Bitmap createWatermark(Bitmap bitmap, String mark) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Bitmap bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint p = new Paint();
        // 水印颜色
        p.setColor(Color.parseColor("#C5FF0000"));
        // 水印字体大小
        p.setTextSize(20);
        //抗锯齿
        p.setAntiAlias(true);
        //绘制图像
        canvas.drawBitmap(bitmap, 0, 0, p);
        //绘制文字
        canvas.drawText(mark, 0, h / 2, p);
        canvas.save();
        canvas.restore();
        return bmp;
    }

}
