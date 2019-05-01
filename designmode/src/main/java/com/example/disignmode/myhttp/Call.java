package com.example.disignmode.myhttp;

import okhttp3.Response;

/**
 * describe :
 * date on 2019/4/29
 * author linghailong
 * email 105354999@qq.com
 */
public interface Call {
    void enqueue(Callback callback);

    Response execute();
}
