package com.example.disignmode.starbrak;

/**
 * Author: tangyuan
 * Time : 2021/10/10
 * Description:
 */
public abstract class Beverage {
    protected String description="unknown beverage";

    public String getDescription() {
        return description;
    }
    public abstract double cost();
}
