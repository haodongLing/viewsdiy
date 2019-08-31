package com.haodong.structure.structure.mystack;

/**
 * describe : stack的数组实现
 * date on 2019/7/4
 * author linghailong
 * email 105354999@qq.com
 */
public class ArrayStack<E> implements Stack<E> {
    Array<E> array;
    public  ArrayStack(int capacity){
        array=new Array<>(capacity);

    }

    public ArrayStack() {
        array=new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
    public  int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder res=new StringBuilder();
        res.append("array{");
        for (int i=0;i<array.getSize();i++){
            res.append(array.get(i));
            if (i!=array.getSize()-1)
                res.append(", ");

        }
        res.append("} top");
        return res.toString();
    }
}
