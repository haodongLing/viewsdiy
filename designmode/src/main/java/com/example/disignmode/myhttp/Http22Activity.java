package com.example.disignmode.myhttp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.disignmode.BuildConfig;
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
        DownloadFacade.getDefault().init(this);
        DownloadFacade.getDefault()
                .startDownLoad("http://acj3.pc6.com/pc6_soure/2017-11/com.ss.android.essay.joke_664.apk", new
                        DownloadCallback() {
                            @Override
                            public void onFailure(IOException e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onSucceed(File file) {
                                installFile(file);
                            }
                        });
    }

    private void installFile(File file) {
        // 核心是下面几句代码
        Intent intent = new Intent(Intent.ACTION_VIEW);
        //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", file);
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);
    }
}