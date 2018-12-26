package com.haodong;

/**
 * @author linghailong
 * @date on 2018/12/26
 * @email 105354999@qq.com
 * @describe :单例模式练习
 */
public class Singleton {
    private static  Singleton sSingleton=null;
    /**
     * 静态内部类
     */
    private Singleton() {
    }
//    private static class  Holder{
//        private static Singleton sSingleton=new Singleton();
//    }
//    public static Singleton getInstance(){
//        return Holder.sSingleton;
//    }
    /**
     * 双重锁
     */
    public static Singleton getInstance(){
        if(sSingleton==null){
            synchronized (Singleton.class){
                if (sSingleton==null){
                    sSingleton=new Singleton();
                }
            }
        }
        return sSingleton;
    }



}
