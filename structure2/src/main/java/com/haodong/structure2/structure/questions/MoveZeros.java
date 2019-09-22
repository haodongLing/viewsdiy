package com.haodong.structure2.structure.questions;


import com.haodong.structure2.structure.MyUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * created by linghaoDo on 2019-09-04
 * <p>
 * description: leetcode 283
 */
public class MoveZeros {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 3, 0, 2};
        for (int i : arr) {
            System.out.print(i + "   ");
        }
        System.out.println("----");
        for (int j : solution2(arr)) {
            System.out.print(j + "   ");
        }

    }

    /**
     * 时间复杂度O(n)
     * 空间复杂度O(n)
     *
     * @param nums
     * @return
     */
    public static int[] solution(int[] nums) {
        Queue<Integer> nomal = new LinkedList<>();
        for (int num : nums) {
            if (num != 0)
                nomal.add(num);

        }
        final int size = nomal.size();
        for (int i = 0; i < size; i++) {
            nums[i] = nomal.poll();
        }
        for (int i = size; i < nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }

    public static int[] solution2(int[] nums) {
        int k = 0; // nums中从[0..k)的元素均为非0元素
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k = k + 1;
            }
        }
        // 此时k已经改变
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
        return nums;
    }

    public static int[] solution3(int[] nums) {
        int k = 0; // nums中从[0..k)的元素均为非0元素
        // 测试数据 0，1，0，3，2
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != k) {
                    MyUtil.swap(nums, k, i);
                    k = k + 1;
                } else {
                    k = k + 1;
                }

            }
        }

        return nums;
    }
}
