package com.example.disignmode.mode_adapter;

/**
 * Author: tangyuan
 * Time : 2021/10/15
 * Description:
 */
public class MallardDuck implements Duck{
    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying");
    }
}
