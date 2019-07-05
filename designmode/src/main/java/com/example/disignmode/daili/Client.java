package com.example.disignmode.daili;

/**
 * description
 * 2019-05-20
 * linghailong
 */
public class Client {
    public static void main(String[] args){
        Man man=new Man("haha");
        /*被代理对象*/
        BankWorker bankWorker=new BankWorker(man);
        bankWorker.applyBank();
    }
}
