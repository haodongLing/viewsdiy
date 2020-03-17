package com.example.disignmode.myproxy;

/**
 * created by linghaoDo on 2020-03-02
 * description:
 * <p>
 * version:
 */
public class Person implements IBank {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public void applyBank() {
        System.out.println(name+"--applyBank--");
    }

    @Override
    public void lostBank() {
        System.out.println(name+"--lostBank--");
    }
}
