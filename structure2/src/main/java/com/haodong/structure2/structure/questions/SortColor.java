package com.haodong.structure2.structure.questions;

import com.haodong.structure2.structure.MyUtil;

import java.util.LinkedList;
import java.util.Queue;

/**
 * created by linghaoDo on 2019-09-20
 * <p>
 * description: 三路快排
 */
public class SortColor {
    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 3, 0, 2};
        for (int i : arr) {
            System.out.print(i + "   ");
        }
        System.out.println("----");
    }

    /**
     * 计数法
     *
     * @param nums
     * @return
     */
    public int[] sortColor1(int[] nums) {
        int[] count = {0, 0, 0};
        for (int i = 0; i < nums.length; i++) {
            assert (nums[i] >= 0 && nums[i] <= 2);
            count[nums[i]]++;
        }
        int index = 0;
        for (int i = 0; i < count[0]; i++)
            nums[index++] = 0;
        for (int i = 0; i < count[1]; i++)
            nums[index++] = 1;
        for (int i = 0; i < count[2]; i++)
            nums[index++] = 2;
        return nums;
    }

    /**
     * 三路快排
     *
     * @param nums
     * @return
     */
    public int[] sortColor2(int[] nums) {
        int zero = -1;// nums{0...zero}保证数组一开始就是一个无效的区间
        int two = nums.length;
        for (int i = 0; i < two; ) {
            if (nums[i] == 1) {
                i++;

            } else if (nums[i] == 2) {
                two--;
                MyUtil.swap(nums, nums[i], nums[two]);
            } else {
                assert (nums[i] == 0);
                zero++;
                MyUtil.swap(nums, nums[zero], nums[i]);
                i++;
            }
        }

        return nums;
    }


}
