package com.example.disignmode.myhttp.intercepter;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * created by linghaoDo on 2020-01-08
 * description:
 * <p>
 * version:
 */
public class BridgeInterceptor implements Interceptor{
    @Override
    public Response intercept(Chain chain) throws IOException {
        // 上一层的request
       Request request= chain.request();
       // 下一个responce
       Response response= chain.proceed(request);
        return null;
    }
}
