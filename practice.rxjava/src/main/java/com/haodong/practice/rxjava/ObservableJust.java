package com.haodong.practice.rxjava;

/**
 * describe :
 * date on 2019/5/3
 * author linghailong
 * email 105354999@qq.com
 */
public class ObservableJust<T>extends Observable {
    private T item;
    public ObservableJust(T item) {
        this.item = item;
    }
    @Override
    public void suscrice(Observer observer) {

    }
}
