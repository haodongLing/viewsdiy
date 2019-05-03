package com.example.disignmode.structure.redblacktree;

import java.util.HashMap;
import java.util.Map;

/**
 * describe :
 * date on 2019/5/4
 * author linghailong
 * email 105354999@qq.com
 */
public class ArthmeticPrac {
    public static void main(String[] args){
    }
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])){
                return new int[]{map.get(nums[i]),i};
            }else {
                map.put(target-nums[i],i);
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
