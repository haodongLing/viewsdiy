package com.haodong.structure2.structure.sort;

import android.content.Intent;

import com.haodong.structure2.structure.heap.Array;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * created by linghaoDo on 2019-09-03
 * <p>
 * description: 选择排序
 */
public class SelectSort {
    public static void main(String args[]) {
        int[] arr=new int[]{8,3,1,9,6};
        ArrayList<Integer>arrayList=Select(arr);
        for (int ele:arrayList){
            System.out.print(ele+"----");
        }
    }

    public static ArrayList<Integer> Select(int[] arr) {
        ArrayList<Integer> currentArr = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int min = arr[i];
            for (int j = i+1; j < arr.length; j++) {
                if (min > arr[j]) {
                    int current = min;
                    min = arr[j];
                    arr[j] = current;
                }
            }
            currentArr.add(min);
        }
        return currentArr;
    }

}
