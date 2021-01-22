package com.haodong.structure2.structure.myqueue.qusetions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * created by linghaoDo on 2020/11/11
 * description:
 * <p>
 * version:
 */
public class Pet {
    private String type;

    public Pet(String type) {

        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class Dog extends Pet {
        public Dog() {
            super("dog");
        }
    }

    public static class Cat extends Pet {
        public Cat() {
            super("cat");
        }
    }

    Queue<Pet> mCats = new LinkedList<>();
    Queue<Pet> mDogs = new LinkedList<>();

    public static void main(String[] args) {

    }

}
