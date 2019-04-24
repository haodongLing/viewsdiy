package com.example.disignmode.structure;

import java.util.Arrays;

/**
 * describe :
 * date on 2019/4/24
 * author linghailong
 * email 105354999@qq.com
 */
public class QuickSort {
    public static void main(String[] args){
        int arr[]=new int[]{1,3,2,8,5};
        sort(arr,0,arr.length-1);
    }
    public static void sort(int arr[],int low,int high){
        if (arr==null||arr.length<0){
            return;
        }
        if (low>=high){
            return;
        }
        int left=low;
        int right=high;
        int temp=arr[left];
        while (left<right){
            while (left<right&&arr[right]>temp){
                right--;
            }
            arr[left] = arr[right];
            System.out.println("arr[left]"+arr[left]);
            while (left<right&&arr[left]<temp){
                left++;
            }
            arr[right]=arr[left];
            System.out.println("arr[right]"+arr[right]);
        }
        arr[left]=temp;
        System.out.println("Sorting: " + Arrays.toString(arr));
        sort(arr, low, left-1);
        sort(arr, left + 1, high);
    }

}
