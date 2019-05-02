package com.example.disignmode.myhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * describe :
 * date on 2019/5/2
 * author linghailong
 * email 105354999@qq.com
 */
public class OkHttpManager {
    private OkHttpManager() {
    }

    public static OkHttpManager getDefault() {
        return ManagerHolder.managerInstance;
    }

    private static class ManagerHolder {
        private static final OkHttpManager managerInstance = new OkHttpManager();
    }

    private static class OkHttpClientHolder {
        private static final OkHttpClient instance = new OkHttpClient();
    }

    public Call asyncCall(String url) {
        Request request = new Request.Builder().url(url).build();
        return OkHttpClientHolder.instance.newCall(request);
    }

    public Response syncResponse(String url, long start, long end) throws IOException {
        Request request = new Request.Builder().url(url)
                .addHeader("Range", "bytes=" + start + "-" + end).build();
        return OkHttpClientHolder.instance.newCall(request).execute();
    }
}
