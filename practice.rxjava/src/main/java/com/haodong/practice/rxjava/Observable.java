package com.haodong.practice.rxjava;

/**
 * describe :
 * date on 2019/5/3
 * author linghailong
 * email 105354999@qq.com
 */
public abstract class Observable<T> implements ObservableSource<T> {
    public static <T> Observable<T> just(T item) {
        return onAssembly(new ObservableJust<T>(item));
    }
    private static <T> Observable<T> onAssembly(Observable<T> source) {
        // 留出来了
        return source;
    }
}
