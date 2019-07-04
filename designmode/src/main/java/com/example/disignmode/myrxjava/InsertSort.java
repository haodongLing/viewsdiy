package com.example.disignmode.myrxjava;

/**
 * describe :
 * date on 2019/6/29
 * author linghailong
 * email 105354999@qq.com
 */
public class InsertSort {
    public static int[] sort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i], j;
            for (j = i - 1; j >= 0 && temp < arr[j]; j--)
                arr[j + 1] = arr[j];
            arr[j + 1] = temp;

        }
        return null;
    }
}
