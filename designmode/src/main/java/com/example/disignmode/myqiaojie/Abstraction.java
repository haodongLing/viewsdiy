package com.example.disignmode.myqiaojie;

/**
 * created by linghaoDo on 2019-11-08
 * description:
 * <p>
 * version:
 */
public abstract class Abstraction {
    private Implementer mImplementer; // 获取对象抽象

    public Abstraction(Implementer mImplementer) {
        this.mImplementer = mImplementer;
    }
    public void operation(){
        mImplementer.operationImpl();// 调用抽象的对象的相关方法。
    }
}
