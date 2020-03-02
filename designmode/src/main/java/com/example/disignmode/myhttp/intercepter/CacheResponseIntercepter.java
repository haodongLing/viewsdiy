package com.example.disignmode.myhttp.intercepter;

import java.io.IOException;

import okhttp3.Response;

/**
 * created by linghaoDo on 2020-01-17
 * description: 缓存intercepter，需要添加到networkIntercepter中
 * <p>
 * version:
 */
public class CacheResponseIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        response.newBuilder().removeHeader("Pragma").removeHeader("Cache-Control").addHeader("Cache-Control", "max-age=" + 30).build();
        return response;
    }
}
