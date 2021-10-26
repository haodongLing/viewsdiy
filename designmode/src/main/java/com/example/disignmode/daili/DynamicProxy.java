package com.example.disignmode.daili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author: tangyuan
 * Time : 2021/10/26
 * Description:
 */
public class DynamicProxy implements InvocationHandler {
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result=method.invoke(object,args);
        return result;
    }
}
