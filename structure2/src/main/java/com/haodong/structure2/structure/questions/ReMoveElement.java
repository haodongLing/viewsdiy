package com.haodong.structure2.structure.questions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * created by linghaoDo on 2019-09-04
 * <p>
 * description: 给定一个数组nums和一个数值Val,将数组中所有等于val的元素删除，并返回剩余元素的个数
 */
public class ReMoveElement {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 3, 0, 2};
        for (int i : arr) {
            System.out.print(i + "   ");
        }
        System.out.println("----");
        solution(arr,0);
    }

    public static int solution(int[] arr, int val) {
        Queue<Integer> queue=new LinkedList<>();
        for (int i=0;i<arr.length;i++){

        }
        return queue.size();
    }

}
