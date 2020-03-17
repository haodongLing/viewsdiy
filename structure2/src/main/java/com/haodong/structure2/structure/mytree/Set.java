package com.haodong.structure2.structure.mytree;

/**
 * describe :
 * date on 2019/4/26
 * author linghailong
 * email 105354999@qq.com
 */
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
