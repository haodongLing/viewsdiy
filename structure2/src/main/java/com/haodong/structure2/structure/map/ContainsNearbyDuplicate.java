package com.haodong.structure2.structure.map;

import java.util.HashMap;
import java.util.TreeSet;

/**
 * created by linghaoDo on 2019-11-12
 * description:
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的绝对值最大为 k。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/contains-duplicate-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * version:
 */
public class ContainsNearbyDuplicate {
    public static void main(String[] args) {

    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (record.containsKey(nums[i])) {
                if (k >= (i - record.get(nums[i]))) {
                    return true;
                } else
                    //  更新位置
                    record.put(nums[i], i);
            } else {
                record.put(nums[i], i);
            }
        }

        return false;
    }

    /**
     * 内存优化，使用滑动窗口的方式
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean soultion2(int[] nums, int k) {
        TreeSet<Integer> record = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (record.contains(nums[i]))
                return true;
            record.add(nums[i]);
            // 此时，数组数量到大边界
            if (record.size() == k + 1)
                record.remove(nums[i - k]);
        }
        return false;
    }

}
