package com.haodong.structure2.structure.questions;

import com.haodong.structure2.structure.mytree.Main;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: tangyuan
 * Time : 2022/2/14
 * Description: 滑动窗口总结
 */
public class MovingWindow {
//    String minWindow(String s,String t){
//        Map<Character,Integer> need=new HashMap<>();
//        Map<Character,Integer> window=new HashMap<>();
//        for (char c:t){
//            need[]
//        }
//    }

    /**
     * 11. 盛最多水的容器
     */
    int maxArea(int[] height){
        int left=0,right=height.length-1;
        int res=0;
        while (left<right){
            int cur_area= Math.min(height[left],height[right])*(right-left); // 核心
            res=Math.max(res,cur_area);
            if (height[left]<height[right]){
                left++;
            }else {
                right--;
            }
        }
        return res;
    }

    /**
     * 42 接雨水
     */
    int trap(int[] height){
        int n=height.length;
        int res=0;
        for (int i=0;i<height.length-1;i++){
            int l_max=0;int r_max=0;
            for (int j=i;j<n;j++){
                r_max=Math.max(r_max,height[j]);
            }
            // j>=0 重点
            for (int j=i;j>=0;j--){
                l_max=Math.max(l_max,height[j]);
            }
            res+=Math.min(l_max,r_max)-height[i];

        }

        return res;
    }
}

