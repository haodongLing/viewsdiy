package com.haodong.structure2.structure.chapter2;

/**
 * created by linghaoDo on 2019/8/4
 * <p>
 * description:选择排序
 */
public class Selection {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 0; i < N; i++) {
            int min = i; /*第一趟，i=0;第二趟，i=1*/
            for (int j = i + 1; j < N; j++) {
                if (Example.less(a[j], a[min]))
                    min = j;
                Example.exch(a,i,min);
            }
        }
    }

}
