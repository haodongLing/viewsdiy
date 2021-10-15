package com.example.disignmode.mode_adapter;

/**
 * Author: tangyuan
 * Time : 2021/10/15
 * Description:
 */
public class TurkeyAdapter implements Duck {
    Turkey turkey;

    public TurkeyAdapter(Turkey turkey) {
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        /*这里因为turkey 的fly功能与 duck的fly功能不同，需要通过一些方式以实现duck的fly功能*/
        for (int i = 0; i < 5; i++) {
            turkey.fly();
        }
    }
}
