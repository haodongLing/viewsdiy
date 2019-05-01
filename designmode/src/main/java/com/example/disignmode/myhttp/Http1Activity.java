package com.example.disignmode.myhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.disignmode.R;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Http1Activity extends AppCompatActivity {
    private static final int maxSize = 50 * 1024 * 1024;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http1);
        /*1. 构建缓存尝试*/
        Cache cache = new Cache(this.getCacheDir(), maxSize);
        /*2. 构建拦截器尝试*/


        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(60, TimeUnit.SECONDS)
                .addNetworkInterceptor(new CacheIntercepter())
                .cache(cache)
                .build();
        /*会返回码307 重定向https://www.baidu.com*/
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("key", "value")
                .build();
        Request request = new Request.Builder().url("http://www.baidu.com").post(requestBody).build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String str = response.body().string();
            }
        });
    }
}
