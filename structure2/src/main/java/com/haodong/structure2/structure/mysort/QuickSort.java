package com.haodong.structure2.structure.mysort;

import java.util.Arrays;

/**
 * created by linghaoDo on 2020-03-03
 * description:
 * <p>
 * version:
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 5, 2, 6, 3};

//        quickSort(arr, 0, arr.length - 1);
//        seletctSort(arr);
        insertSort(arr);
        printArr(arr);

    }

    public static void sort2(int arr[], int low, int hign) {
        if (arr == null || arr.length <= 0) {
            return;
        }
        if (low >= hign) {
            return;
        }
        int left = low;
        int right = hign;
        int key = arr[left];
        while (left < right) {
            while (left < right && arr[right] >= key) {
                right--;
            }
            while (left < right && arr[right] <= key) {
                left++;

            }
            if (left < right) {
                swap(arr, left, right);
            }
        }
        swap(arr, low, left);
        System.out.println("Sorting: " + Arrays.toString(arr));
        sort2(arr, low, left - 1);
        sort2(arr, left + 1, hign);

    }

    public static void swap(int arr[], int low, int high) {
        int tmp = arr[low];
        arr[low] = arr[high];
        arr[high] = tmp;
    }

    public static void printArr(int[] arr) {
        for (int anArr : arr) {
            System.out.print(anArr + " ");
        }
    }

    public static int partition(int[] arr, int left, int right) {
        int temp = arr[left];
        while (left < right) {
            while (temp <= arr[right] && left < right) {
                --right;
            }
            // 当基准数大于了arr[right]，则填坑
            if (left < right) {
                arr[left] = arr[right];
                ++left;
            }
            // 现在arr[right]需要填坑了
            while (left < right && temp >= arr[left]) {
                ++left;
            }
            if (left < right) {
                arr[right] = arr[left];
                --right;
            }
        }
        arr[left] = temp;
        return left;
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (arr == null || left >= right || arr.length <= 1) {
            return;
        }
        int mid = partition(arr, left, right);
        quickSort(arr, left, mid);
        quickSort(arr, mid + 1, right);
    }

    public static int partition2(int[] arr, int left, int right) {
        int temp = arr[left];
        while (left < right) {
            while (left < right && temp < arr[right]) {
                --right;

            }
            if (left < right) {
                arr[left] = arr[right];
                ++left;
            }
            while (left < right && temp > arr[left]) {
                ++left;
            }
            if (left < right) {
                arr[right] = arr[left];
                --right;
            }
        }
        arr[left] = temp;
        return left;

    }

    /**
     * 选择排序 遍历后面
     *
     * @param arr
     */
    public static void seletctSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(arr, min, i);
            }
        }
    }

    /**
     * 插入排序，遍历前面
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
        if (arr == null)
            return;
        int temp;

        for (int i = 0; i < arr.length ; i++) {
            temp = arr[i];
            int j = i ;
            while (j >0 && arr[j-1] > temp) {
                arr[j] = arr[j-1];
                j--; // 精髓
            }
            arr[j] = temp;
        }
    }


}
