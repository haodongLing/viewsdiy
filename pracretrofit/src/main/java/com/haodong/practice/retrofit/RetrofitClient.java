package com.haodong.practice.retrofit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * describe :
 * date on 2019/5/5
 * author linghailong
 * email 105354999@qq.com
 */
public class RetrofitClient {
    private static final ServiceApi SERVICE_API;
    static {
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .build();
        // 各种套路和招式 ，发现问题解决问题，基础，源码的理解
        // 1. 没打印？
        // 2. 数据格式不一致？成功 data 是个对象，不成功 data 是个 String
        // 3. 还有就是 baseUrl 问题？ (Retrofit 找不到任何入口可以修改)
        //        3.1 不同的 baseUrl 构建不同的 Retrofit 对象 （直不应该首选）
        //        3.2 自己想办法，取巧也行走漏洞
        Retrofit retrofit = new Retrofit.Builder()
                // 访问后台接口的主路径
                .baseUrl("http://192.168.8.169:8080/OkHttpServer/")
                // 添加解析转换工厂,Gson 解析，Xml解析，等等
                .addConverterFactory(GsonConverterFactory.create())
                // 添加 OkHttpClient,不添加默认就是 光杆 OkHttpClient
                .client(okHttpClient)
                .build();

        // 创建一个 实例对象
        SERVICE_API = retrofit.create(ServiceApi.class);
    }
    public static ServiceApi getServiceApi() {
        return SERVICE_API;
    }

}
