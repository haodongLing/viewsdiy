package com.example.disignmode.myannotation;

import java.io.PipedReader;

/**
 * created by linghaoDo on 2020-03-19
 * description:
 * <p>
 * version:
 */
public class Person {
    @Required(name = "小明", value = 18)
    private PersonFiled personFiled;
    private int time;
}
