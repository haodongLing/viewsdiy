package com.example.disignmode.myhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.disignmode.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import okhttp3.Response;
import okhttp3.Call;
import okhttp3.Callback;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class Http22Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http22);
        Call call = OkHttpManager.getDefault().asyncCall("http://acj3.pc6.com/pc6_soure/2017-11/com.ss.android.essay" +
                ".joke_664.apk");

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // 不断的读写文件，单线程
//                BufferedSource source = response.body().source();
//                Socket socket = new Socket("127.0.0.1", 8080);
//                InputStream inputStream = socket.getInputStream();
//                OutputStream outputStream = socket.getOutputStream();
//                BufferedSource bufferedSource = Okio.buffer(Okio.source(inputStream));
//                BufferedSink bufferedSink = Okio.buffer(Okio.sink(outputStream));
            }
        });
    }
}
