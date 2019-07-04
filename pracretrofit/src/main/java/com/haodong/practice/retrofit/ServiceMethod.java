package com.haodong.practice.retrofit;

import com.haodong.practice.retrofit.http.GET;
import com.haodong.practice.retrofit.http.POST;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * describe :
 * date on 2019/6/23
 * author linghailong
 * email 105354999@qq.com
 */
public class ServiceMethod {
    private ServiceMethod(Builder builder) {
    }

    public static class Builder {
        final Retrofit retrofit;
        final Method method;
        final Annotation[] methodAnnotations;
        Annotation[][]parameterAnnotions;
        private String httpMethod;
        // 相对路径
        private String relativeUrl;

        public Builder(Retrofit retrofit, Method method) {
            this.method = method;
            this.retrofit = retrofit;
            methodAnnotations = method.getAnnotations();
            parameterAnnotions=method.getParameterAnnotations();
        }

        public ServiceMethod build() {
            // 解析 请求  url=baseUrl+相对路径
            /*2. method*/
            for(Annotation annotation:methodAnnotations){
                /*解析所有的请求方法*/
                parseAnnotationMethod(annotation);
            }
            /*3解析参数注解*/
            for(Annotation[] parameterAnnotation:parameterAnnotions){
                Annotation parameter=parameterAnnotation[0];
            }

            return new ServiceMethod(this);
        }
        // 解析注解
        private void parseAnnotationMethod(Annotation annotation) {
            if (annotation instanceof GET){
                    parseMethodAndPath("GET",((retrofit2.http.GET)annotation).value());

            }else if (annotation instanceof POST){
                parseMethodAndPath("POST",((POST)annotation).value());
            }
        }

        private void parseMethodAndPath(String method, String value) {
            this.httpMethod=method;
            this.relativeUrl=value;
        }
    }
}
