package com.example.disignmode.structure.mystack;

/**
 * describe :
 * date on 2019/7/4
 * author linghailong
 * email 105354999@qq.com
 */
public interface Stack<E> {
    int getSize();
    void push(E e);
    E peek();
    E pop();
    boolean isEmpty();
}
