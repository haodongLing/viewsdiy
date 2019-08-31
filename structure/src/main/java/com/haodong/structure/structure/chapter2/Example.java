package com.haodong.structure.structure.chapter2;

import javax.xml.transform.Source;

import retrofit2.http.PUT;

/**
 * created by linghaoDo on 2019/8/4
 * <p>
 * description:排序算法类的模板
 */
public class Example {
    /**
     * @param a
     */
    public static void sort(Comparable[] a) {
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
            if (Example.less(a[i], a[i - 1]))
                return false;
        }
        return true;
    }

}
