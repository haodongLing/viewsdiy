package com.example.disignmode.mode_adapter;

/**
 * Author: tangyuan
 * Time : 2021/10/15
 * Description:
 */
public class DuckTestDrive {
    public static void main(String[] args){
        MallardDuck duck=new MallardDuck();
        WildTurkey wildTurkey=new WildTurkey();
        Duck turkeyAdapter=new TurkeyAdapter(wildTurkey);

    }

}
