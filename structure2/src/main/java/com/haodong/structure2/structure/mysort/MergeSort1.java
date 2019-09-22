package com.haodong.structure2.structure.mysort;

import java.util.Arrays;

/**
 * created by linghaoDo on 2019-09-20
 * <p>
 * description: 归并排序1
 */
public class MergeSort1 {
    public static void main(String[] args) {
//        int a[] = {51, 46, 20, 18, 65, 97, 82, 30, 77, 50};
        int a[] = {2, 6, 1, 4};
        mergeSort(a, 0, a.length - 1);
        System.out.println("排序结果：" + Arrays.toString(a));
    }

    private static void mergeSort(int[] a, int low, int high) {
        // 找到标定点
        if (low >= high)
            return;
        int mid = (low + high) / 2;
        if (low < high) {
            // 左边
            mergeSort(a, low, mid);
            mergeSort(a, mid + 1, high);
            merge(a, low, mid, high);
            System.out.println(Arrays.toString(a));
        }

    }

    private static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid +1;
        int k = 0;
        // 确定临界点
        while (i <= mid && j <= high) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
        while (i <= mid) {
            temp[k++] = a[j++];
        }
        while (j <= high) {
            temp[k++] = a[j++];
        }
        for (int k2 = 0; k2 < temp.length; k2++) {
            a[k2 + low] = temp[k2];
        }
    }

}
