package com.haodong.structure2.structure.questions;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Author: tangyuan
 * Time : 2022/3/6
 * Description:
 */
public class Min_k {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        // 优先级队列 默认为最小堆
        Queue<Integer> heap = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        for (int a : arr) {
            if (heap.isEmpty() || heap.size() < k || a < heap.peek()) {
                heap.offer(a);
            }
            if (heap.size() > k) {
                heap.poll();
            }
        }
        int[] res = new int[heap.size()];
        int j = 0;
        for (int e : heap) {
            res[j++] = e;
        }
        return res;
    }
}
