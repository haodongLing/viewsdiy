package com.example.disignmode.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * describe :
 * date on 2019/4/24
 * author linghailong
 * email 105354999@qq.com
 */
public class QuickSort {
    public static void main(String[] args) {
        int arr[] = new int[]{1, 3, 2, 8, 5};
        sort(arr, 0, arr.length - 1);
    }

    public static void sort(int arr[], int low, int high) {
        if (arr == null || arr.length < 0) {
            return;
        }
        if (low >= high) {
            return;
        }
        int left = low;
        int right = high;
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] > temp) {
                right--;
            }
            arr[left] = arr[right];
            System.out.println("arr[left]" + arr[left]);
            while (left < right && arr[left] < temp) {
                left++;
            }
            arr[right] = arr[left];
            System.out.println("arr[right]" + arr[right]);
        }
        arr[left] = temp;
        System.out.println("Sorting: " + Arrays.toString(arr));
        sort(arr, low, left - 1);
        sort(arr, left + 1, high);
    }

    // 349
    class Solution {
        public int[] intersection(int[] nums1, int[] nums2) {
            TreeSet<Integer> treeSet = new TreeSet<>();
            for (int aNums1 : nums1) {
                treeSet.add(aNums1);
            }
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int aNums2 : nums2) {
                if (treeSet.contains(aNums2))
                    arrayList.add(aNums2);
                treeSet.remove(aNums2);
            }
            int[] soultion = new int[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i++) {
                soultion[i] = arrayList.get(i);
            }
            return soultion;

        }
    }

    // 给定一个字符串，查看字符串中每个字母出现的次数
    TreeSet<HashMap> getAlphabatCount(String s) {
        char[] c = s.toCharArray();
        TreeSet<HashMap> hashMaps = new TreeSet<>();
        for (char c1 : c) {
            boolean isContains = false;
            for (HashMap<Character, Integer> key : hashMaps) {
                if (key.containsKey(c1)) {
                    key.put(c1, key.get(c1) + 1);
                    isContains = true;
                }
            }
            if (!isContains) {
                HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
                characterIntegerHashMap.put(c1, 1);
            }

        }
        return hashMaps;
    }

}
