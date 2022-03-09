package com.haodong.structure2.structure.mysort;

/**
 * Author: tangyuan
 * Time : 2022/2/20
 * Description:
 */
public class GuibingSort {
    private static void mergeSort(int[] arr){
        int start=0;int end=arr.length-1;
        mergeSort(arr,start,end);
    }
    private static void mergeSort(int[] arr, int start,int end){
        if (start>=end){
            return;
        }
        int mid=start+(end-start>>1);
        mergeSort(arr,0,mid);
        mergeSort(arr,mid+1,end);
        merge(arr,start,mid,end);

    }
    private static void merge(int[] arr,int start,int mid,int end){
        // 创建一个临时数组
        int[] tempArr=new int[arr.length];

        int start1=start;int end1=mid;int start2=mid+1;int end2=end;
        // 创建一个下标
        int pos = start1;
        // 缓存左边数组的第一个元素的索引
        int tmp = start1;
        // 剩余部分依次放入临时数组，实际上下面两个 while 只会执行其中一个
        while (start1<=end1&&start2<=end2){
            if (arr[start1]<=arr[start2]){
                tempArr[pos++]=arr[start1++];
            }else {
                tempArr[pos++]=arr[start2++];
            }
        }
        while (start1<=end1){
            tempArr[pos++] = arr[start1++];
        }
        while (start2<=end2){
            tempArr[pos++]=arr[start2++];
        }
        // 将临时数组中的内容拷贝回原来的数组中
        while (tmp<=end){
            arr[tmp]=tempArr[tmp++];
        }
    }
}
