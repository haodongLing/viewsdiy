package com.example.disignmode.starbrak;

/**
 * Author: tangyuan
 * Time : 2021/10/10
 * Description:
 */
public class Mocha extends CondimentDecorator{
    private Beverage beverage;// 持有被装饰者抽象

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public double cost() {
        return 0.20+ beverage.cost();
    }

    @Override
    public String getDescription() {
        return beverage.getDescription()+",Mocha";
    }
}
