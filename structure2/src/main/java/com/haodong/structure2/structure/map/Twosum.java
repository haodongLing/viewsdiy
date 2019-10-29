package com.haodong.structure2.structure.map;

import java.util.HashMap;

/**
 * created by linghaoDo on 2019-10-17
 * <p>
 * description:
 */
public class Twosum {
    public static void main(String[] args) {

    }

    // 由于题目中只要求求出唯一的一个解。因此可以在最初的时候遍历整个数组, 将数组中的每个数字的索引放在map中。
// 此时, record中记录的永远是每一个数字最后出现的位置。
// 而对于 target = 2*a的情况, 如果nums中有两个或两个以上a,
// 我们在扫描时会先看到第一个a, 而从record中拿到的是最后一个a :)
    public int[] twoSum(int[] nums, int target) {

        HashMap<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            /*因为key 是值*/
            record.put(nums[i], i); // 此时, record中记录的永远是每一个数字最后出现的位置。
        }
        for (int i = 0; i < nums.length; i++) {
            final int value = target - nums[i];
            if (record.containsKey(value))
                if (record.get(value) != i) { // 表示不是同一个位置的 value
                    int[] res = {i, record.get(value)};
                    return res;
                }
            record.put(nums[i], i);
        }
        throw new IllegalArgumentException("nums not have solution");

    }
    public int[] twoSum2(int[] nums, int target) {

        HashMap<Integer, Integer> record = new HashMap<Integer, Integer>();
        for(int i = 0 ; i < nums.length; i ++){

            int complement = target - nums[i];
            if(record.containsKey(complement)){
                int[] res = {i, record.get(complement)};
                return res;
            }

            record.put(nums[i], i);
        }

        throw new IllegalStateException("the input has no solution");
    }
}
