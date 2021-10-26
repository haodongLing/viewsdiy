package com.example.disignmode.daili;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Author: tangyuan
 * Time : 2021/10/26
 * Description:
 */
public class DebugInvocationHandler implements InvocationHandler {
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before method");
        Object result=method.invoke(target,args);
        System.out.println("after method"+method.getName());
        return result;
    }
}
