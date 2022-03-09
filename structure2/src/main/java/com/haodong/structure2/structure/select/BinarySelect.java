package com.haodong.structure2.structure.select;

/**
 * created by linghaoDo on 2019-09-04
 * <p>
 * description: 二分查找法
 */
public class BinarySelect {
    public static void mian(String[] args) {
    }

    int binarySearch(int arr[], int n, int target) {
        /*重点：限定左右两个边界*/
        /**/
        int l = 0, r = n - 1; // 在[l...r]的范围里寻找target
        while (l <= r) {
            /*另外声明一下，计算 mid 时需要防止溢出，代码中 left + (right - left) / 2 就和 (left + right) / 2 的结果相同，但是有效防止了 left 和 right 太大直接相加导致溢出。*/
            int mid = l + (r - l) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            if (target > arr[mid]) l = mid + 1; // target 在[mid =1...r]中
            else r = mid - 1; // target 在[l...mid -1]中
        }
        return -1;

    }
}
