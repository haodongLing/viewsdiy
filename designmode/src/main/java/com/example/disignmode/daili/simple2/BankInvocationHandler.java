package com.example.disignmode.daili.simple2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * description 银行执行的代理Handler
 * 2019-05-20
 * linghailong
 */
public class BankInvocationHandler implements InvocationHandler {
    private Object mObject;
    public BankInvocationHandler(Object mObject){
        this.mObject=mObject;
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        Object voidObject=method.invoke(mObject,objects);
        return voidObject;
    }
}
