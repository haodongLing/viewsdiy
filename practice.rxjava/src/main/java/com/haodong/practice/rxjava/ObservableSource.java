package com.haodong.practice.rxjava;

/**
 * describe :
 * date on 2019/5/3
 * author linghailong
 * email 105354999@qq.com
 */
public interface ObservableSource<T> {
    void suscrice(Observer<T>observer);
}
