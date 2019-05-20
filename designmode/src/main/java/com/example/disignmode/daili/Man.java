package com.example.disignmode.daili;

/**
 * description
 * 2019-05-20
 * linghailong
 */
public class Man implements IBank {
    private String name;

    public Man(String name) {
        this.name = name;
    }

    /**
     * 我们自己需要做的操作。
     */
    @Override
    public void applyBank() {
        System.out.println(name+"申请办卡");
    }
}
