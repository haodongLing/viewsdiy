package com.example.disignmode.myhttp;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * describe :
 * date on 2019/4/30
 * author linghailong
 * email 105354999@qq.com
 */
public class CacheIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request=chain.request();
        /*执行*/
        Response response=chain.proceed(request);
        /*设置响应*/
        Response response1=response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .addHeader("Cache-Control","max-age"+60)
                .build();

        return response1;
    }
}
