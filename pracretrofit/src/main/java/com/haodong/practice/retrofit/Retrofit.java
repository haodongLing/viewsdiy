package com.haodong.practice.retrofit;

import android.util.Log;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.OkHttpClient;

/**
 * describe :
 * date on 2019/6/23
 * author linghailong
 * email 105354999@qq.com
 */
public class Retrofit {
    String baseUrl;
    okhttp3.Call.Factory callFactory;
    private Map<Method, ServiceMethod> serviceMethodMapCache = new ConcurrentHashMap<>();

    public static class Builder {
        String baseUrl;
        okhttp3.Call.Factory callFactory;

        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder client(okhttp3.Call.Factory callFactory) {
            this.callFactory = callFactory;
            return this;
        }
    }

    public Retrofit(Builder builder) {
        this.baseUrl = builder.baseUrl;
        this.callFactory = builder.callFactory;
    }

    public <T> T create(Class<T> service) {
        // 检验
        return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[]{service}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Log.e("Tag", "methodname" + method.getName());
                /*如果调用的方法是object的方法*/
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }
                /*解析参数的注解*/
                /*封装okhttpcall*/

                return null;
            }
        });
    }

    private ServiceMethod loadServiceMethod(Method method) {
        ServiceMethod serviceMethod = serviceMethodMapCache.get(method);
        if (serviceMethod == null) {
//            serviceMethod = new ServiceMethod.Builder(this, method).build;
            serviceMethodMapCache.put(method, serviceMethod);
        }
        return serviceMethod;
    }


}
