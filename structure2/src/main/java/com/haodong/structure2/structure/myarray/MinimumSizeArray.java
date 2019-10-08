package com.haodong.structure2.structure.myarray;

import com.haodong.structure2.structure.MyUtil;

/**
 * created by linghaoDo on 2019-09-29
 * <p>
 * description:滑动窗口，求最小的连续数组
 */
public class MinimumSizeArray {
    public static int minSubArrayLen(int s, int[] nums) {
        int l = 0, r = -1;// nums[l...r]为我们的滑动窗口
        int sum = 0; // 滑动窗口中元素的和
        int res = nums.length + 1; // 初始的元素数组长度+1
        while (l < nums.length) { // 说明l指针可以向右移动
            if (r + 1 < nums.length && sum < s) {

                sum += nums[++r];

            } else
                sum -= nums[l++];
            if (sum >= s)
                res = Math.min(res, r - l + 1); // 因为我们的数组是前闭 后开的 这一步是判断数组个数的
        }
        if (res == nums.length + 1) {
            return 0;
        } else
            return res;

    }
    public static void main(String[] args){
        final int[] arrs={2,1,4,5,3,7,2,4};
        System.out.println("answer-->"+minSubArrayLen(13,arrs));
    }
}
