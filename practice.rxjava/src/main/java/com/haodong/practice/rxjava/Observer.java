package com.haodong.practice.rxjava;

import androidx.annotation.NonNull;

/**
 * describe :
 * date on 2019/5/3
 * author linghailong
 * email 105354999@qq.com
 */
public interface Observer<T> {
    void onSubscribe();
    void onNext(@NonNull T item);
    void onError(@NonNull Throwable e);
    void onComplete();
}
