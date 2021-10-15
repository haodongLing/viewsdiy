package com.example.disignmode.mode_adapter;

/**
 * Author: tangyuan
 * Time : 2021/10/15
 * Description:
 */
public class WildTurkey implements Turkey{
    @Override
    public void gobble() {
        System.out.println("Gobble gobble");
    }

    @Override
    public void fly() {
        System.out.println("I'm flying a short distance");
    }
}
