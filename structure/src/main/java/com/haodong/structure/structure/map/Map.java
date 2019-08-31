package com.haodong.structure.structure.map;

/**
 * describe :
 * date on 2019/4/28
 * author linghailong
 * email 105354999@qq.com
 */
public interface Map<K,V> {
    void add(K key,V value);
    boolean contains(K key);
    V remove(K key);
    void set(K key,V newValue);
    int getSize();
    boolean isEmpty();
    V get(K key);
}
