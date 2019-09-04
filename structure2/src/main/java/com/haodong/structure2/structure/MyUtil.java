package com.haodong.structure2.structure;

import java.util.Random;

/**
 * created by linghaoDo on 2019-09-04
 * <p>
 * description: 一些工厂方法
 */
public class MyUtil {
    public static int[] generateRandomArr(int n, int rangeL, int rangeR) {
        assert n > 0 && rangeL < rangeR;
        int[] arr = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(rangeR) + rangeL;
        }
        return arr;
    }

    public static int[] generatOrderdArr(int n) {
        assert n > 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        return arr;
    }
    public static void swap(int arr[], int low, int high) {
        int tmp = arr[low];
        arr[low] = arr[high];
        arr[high] = tmp;
    }

}
