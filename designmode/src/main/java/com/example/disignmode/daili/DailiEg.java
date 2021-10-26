package com.example.disignmode.daili;

import java.lang.reflect.Proxy;

/**
 * Author: tangyuan
 * Time : 2021/10/26
 * Description:
 */
public class DailiEg {
    public static void main(String[] args){
        SmsService smsService1= new SmsService() {
            @Override
            public String send(String message) {
                return "current"+message;
            }
        };
       SmsService smsService= (SmsService) Proxy.newProxyInstance(SmsService.class.getClassLoader(),new Class[]{SmsService.class},new DebugInvocationHandler(smsService1));
       smsService.send("hahha");
    }
}
