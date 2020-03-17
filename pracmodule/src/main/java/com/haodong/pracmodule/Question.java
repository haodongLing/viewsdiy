package com.haodong.pracmodule;

/**
 * created by linghaoDo on 2020-03-04
 * description:
 * <p>
 * version:
 */
public class Question {
    public static void main(String args[]) {
        SuperClass superClass = new SubClass();
        printHello(superClass);
    }

    public static void printHello(SuperClass superClass) {
        System.out.println("Hello--super-->" + superClass.getName());
    }

    public static void printHello(SubClass subClass) {
        System.out.println("Hello--sub-->" + subClass.getName());
    }
}
