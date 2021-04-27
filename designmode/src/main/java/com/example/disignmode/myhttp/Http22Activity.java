package com.example.disignmode.myhttp;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.example.disignmode.BuildConfig;
import com.example.disignmode.R;

import java.io.File;
import java.io.IOException;

public class Http22Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http22);
        // 如果下载中断(网络断开，程序退出)，下次可以接着上次的地方下载

        // 多线程+断点，最多一次能下载集合文件，一些暂停，一些准备，



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
