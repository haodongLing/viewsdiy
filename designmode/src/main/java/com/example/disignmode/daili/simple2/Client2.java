package com.example.disignmode.daili.simple2;

import com.example.disignmode.daili.BankWorker;
import com.example.disignmode.daili.IBank;
import com.example.disignmode.daili.Man;

import java.lang.reflect.Proxy;

/**
 * description
 * 2019-05-20
 * linghailong
 */
public class Client2 {
    public static void main(String[] args) {
        Man man = new Man("haha");
//        /*被代理对象*/
//        BankWorker bankWorker=new BankWorker(man);
//        bankWorker.applyBank();
        IBank bank = (IBank) Proxy.newProxyInstance(IBank.class.getClassLoader(),
                new Class<?>[]{IBank.class}, new BankInvocationHandler(man));
        bank.applyBank();
    }
}
