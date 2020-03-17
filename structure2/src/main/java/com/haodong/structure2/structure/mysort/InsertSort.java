package com.haodong.structure2.structure.mysort;

/**
 * created by linghaoDo on 2020-03-03
 * description:
 * <p>
 * version:
 */
public class InsertSort {
    public static void sort(int a[]){
        if (a==null||a.length==0)
            return;
        for (int i=1;i<a.length;i++){
            int j=i-1;// i 前面的数
            int temp=a[i];
            while (j>=0&&a[j]>temp){  // 条件
                a[j+1]=a[j]; // 当前值向前移动
                j--;
            }
            a[j+1]=temp; // 在插入初插入。
        }
    }

}
