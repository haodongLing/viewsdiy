package com.example.disignmode.myproxy;

import java.lang.reflect.Proxy;

/**
 * created by linghaoDo on 2020-03-02
 * description:动态代理测试
 * <p>
 * version:
 */
public class Client {
    public static void main(String args[]) {
        /*返回的是iBank的实例对象  调用的是jni*/
      IBank iBank= (IBank) Proxy.newProxyInstance(IBank.class.getClassLoader(), // ClassLoader
                new Class<?>[]{IBank.class}, new BankInvocationHandler(new Person("小明",17))); // 目标接口
        // 调用这个方法的时候，会进入invoke方法。
        iBank.applyBank();
        iBank.lostBank();
    }
}
