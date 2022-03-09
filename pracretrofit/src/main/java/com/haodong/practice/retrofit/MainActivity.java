package com.haodong.practice.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Authenticator;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import android.os.Bundle;

import com.example.haodong.common.util.LogUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OkHttpClient httpClient = new OkHttpClient();

        new OkHttpClient.Builder().proxyAuthenticator(new Authenticator() {
            @Nullable
            @Override
            public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
                return null;
            }
        });

        String url = "https://www.baidu.com/";
        Request getRequest = new Request.Builder()
                .url(url)
                .get()
                .build();

        Call call = httpClient.newCall(getRequest);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //同步请求，要放到子线程执行
                    Response response = call.execute();
                    LogUtil.i("okHttpGet run: response:"+ response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public int count(int a, int b) {
        final int c = a + b;
        return c;
    }
}
