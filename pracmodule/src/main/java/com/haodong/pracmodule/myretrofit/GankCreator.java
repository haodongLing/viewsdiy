package com.haodong.pracmodule.myretrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by linghaoDo on 2020-04-27
 * description:
 * <p>
 * version:
 */
public class GankCreator {
    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient.Builder BUILDER = new OkHttpClient.Builder();

        private static OkHttpClient.Builder addInterceptor() {
            return BUILDER;

        }

        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RetrofitHolder {
        private static final String BASE_URL = "https://gank.io/api/v2/categories/";
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .build();
    }

    /**
     * Service接口
     */
    private static final class RestServiceHolder {
        private static final Gank REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(Gank.class);
    }

    public static Gank getGank() {
        return RestServiceHolder.REST_SERVICE;
    }
}
