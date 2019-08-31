package com.haodong.structure.structure.bst;

/**
 * describe :
 * date on 2019/4/26
 * author linghailong
 * email 105354999@qq.com
 */
public class BSTSet<E extends Comparable<E>>implements Set<E> {
    private BST<E>bst;
    public BSTSet(){
        bst=new BST<>();
    }
    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.inEmpty();
    }
}
