package com.haodong.structure2.structure.myarray;

import com.haodong.structure2.structure.link.SwapPairs;

/**
 * created by linghaoDo on 2020-03-13
 * description:
 * <p>
 * version:
 */
public class Questions {

    public static void main(String args[]) {
        int[] arr = new int[]{0, 1, 0, 3, 12};
        moveZeros(arr);

    }

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     * <p>
     * 示例:
     * <p>
     * 输入: [0,1,0,3,12]
     * 输出: [1,3,12,0,0]
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/move-zeroes
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param arr
     */
    public static void moveZeros(int[] arr) {
        if (arr.length == 0) {
            return;
        }
        int k = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                if (k != i) {
                    swap(arr, k++, i);
                } else
                    k++;
            }

        }
        for (int value : arr) {
            System.out.print(value + "  ");
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
