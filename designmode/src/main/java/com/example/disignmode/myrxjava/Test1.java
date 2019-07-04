package com.example.disignmode.myrxjava;

/**
 * describe :
 * date on 2019/6/28
 * author linghailong
 * email 105354999@qq.com
 */
public class Test1 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 9, 5, 2, 4, 3};
        int[] newArr = selectSort(arr);
        for (int a : newArr) {
            System.out.print(a + " ");
        }
    }

    public static int[] selectSort(int[] arr) {
        int[] newArr = arr;
        for (int i = 0; i < newArr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < newArr.length; j++) {
                if (newArr[min] > newArr[j]) {
                    min = j;
                }
            }
            // 需要交换位置
            if (i != min) {
                int a = newArr[i];
                newArr[i] = arr[min];
                newArr[min] = a;
            }

        }
        return newArr;
    }
    public static int[] insertSort(int[] arr){
        int j;
        int len=arr.length;
        for(int i=1;i<len;i++){
            int temp=arr[i];
            for(j=i;j>0&&temp<arr[j-1];j--){
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
        return arr;
    }
}
