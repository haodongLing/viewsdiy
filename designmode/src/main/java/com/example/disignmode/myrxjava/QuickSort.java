package com.example.disignmode.myrxjava;

import java.util.Arrays;

/**
 * describe :
 * date on 2019/6/29
 * author linghailong
 * email 105354999@qq.com
 */
public class QuickSort {
    /**
     * /**
     * 快速排序（左右指针法）
     *
     * @param arr  待排序数组
     * @param low  左边界
     * @param high 右边界
     */
    public static void sort2(int arr[], int low, int high) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        if (low >= high) {
            return;
        }

        int left = low;
        int right = high;

        int key = arr[left]; // 子序列的第一个值作为基准值

        while (left < right) {
            //如果右边比左边大，则判断下一个
            while (left < right && arr[right] >= key) {
                right--;
            }
            // 如果left小于right,left往右移动。
            while (left < right && arr[left] <= key) {
                left++;
            }
            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, low, left);
        System.out.println("Sorting: " + Arrays.toString(arr));
        sort2(arr, low, left - 1);
        sort2(arr, left + 1, high);
    }

    public static void swap(int arr[], int low, int high) {
        int tmp = arr[low];
        arr[low] = arr[high];
        arr[high] = tmp;
    }
}

