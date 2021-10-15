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

    public static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
            System.out.println();
        }

    }

    public static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

}
