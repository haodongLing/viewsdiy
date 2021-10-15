package io.github.haodongling;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Author: tangyuan
 * Time : 2021/8/26
 * Description:
 */
public class Cache<K, V> {
    final Map<K, V> mCache = new HashMap<>();
    final ReadWriteLock rwl = new ReentrantReadWriteLock();
    final Lock readLock = rwl.readLock();
    final Lock writeLock = rwl.writeLock();

    public V get(K key) {
        readLock.lock();
        try {
            return mCache.get(key);
        } finally {
            readLock.unlock();
        }

    }

    public V put(K key, V value) {
        writeLock.lock();
        try {
            return mCache.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
