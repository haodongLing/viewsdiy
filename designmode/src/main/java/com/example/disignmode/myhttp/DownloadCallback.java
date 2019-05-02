package com.example.disignmode.myhttp;

import java.io.File;
import java.io.IOException;

/**
 * describe :
 * date on 2019/5/2
 * author linghailong
 * email 105354999@qq.com
 */
public interface DownloadCallback {
    void onFailure(IOException e);

    void onSucceed(File file);
}
