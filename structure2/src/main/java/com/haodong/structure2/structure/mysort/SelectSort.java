package com.haodong.structure2.structure.mysort;

import com.haodong.structure2.structure.MyUtil;

/**
 * Author: tangyuan
 * Time : 2021/7/11
 * Description:
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 4, 3};
        SelectSort(arr);
        for (int i=0;i<arr.length;i++){
            System.out.println("--->"+arr[i]);
        }
    }

    public static int[] SelectSort(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < cur) {
                    MyUtil.swap(arr, i, j);
                }
            }
        }
        return arr;
    }
}
