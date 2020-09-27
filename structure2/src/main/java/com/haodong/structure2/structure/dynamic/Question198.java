package com.haodong.structure2.structure.dynamic;

import java.util.Arrays;

/**
 * created by linghaoDo on 2020/9/27
 * description:
 * <p>
 * version:
 */
public class Question198 {
    private int[] memo;

    public int rob(int[] nums) {
        memo = new int[nums.length];
        Arrays.fill(memo, -1);
        return tryRob(nums,0);
    }
    // 考虑抢劫nums[index...nums.size())这个范围的所有房子
    public int tryRob(int[] nums, int index) {
        if (index >= nums.length) {
            return 0;
        }
        if (memo[index] != -1) {
            return memo[index];
        }
        int res = 0;
        for (int i = index; i < nums.length; i++) {
            /*选择最大的数据*/
            res = Math.max(res, nums[i] + tryRob(nums, i + 2));
        }
        memo[index] = res;
        return res;
    }


}
