package com.haodong.structure2.structure.sort;

import java.util.Arrays;

/**
 * created by linghaoDo on 2019-09-03
 * <p>
 * description: 插入排序 选择排序是选择后面的元素
 * 插入排序是选择后面的元素插入到前面的数组中
 */
public class InsertSort {
    public int[] sort(int[] sourceArr){
        int[] arr= Arrays.copyOf(sourceArr,sourceArr.length);
        // i=1 流出后移插入的位置
        for (int i=1;i<arr.length;i++){
            int temp=arr[i];
            int j=i;
            while(j>0&&temp<arr[j-1]){
                arr[j]=arr[j-1];
                j--;
            }
            if (j!=i){
                arr[j]=temp;
            }
        }
        return arr;

    }
}
