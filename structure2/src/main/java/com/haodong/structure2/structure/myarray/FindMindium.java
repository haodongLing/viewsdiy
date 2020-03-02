package com.haodong.structure2.structure.myarray;

import com.haodong.structure2.structure.heap.Array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * created by linghaoDo on 2019-11-14
 * description:
 * <p>
 * version:
 */
public class FindMindium {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 4, 6, 7};
        int[] nums2 = new int[]{2, 4, 5, 8};
        System.out.println(String.valueOf(findMindium(nums1, nums2)));
    }

    public static int findMindium(int[] nums1, int[] nums2) {
        final int length1 = nums1.length;
        final int length2 = nums2.length;
        final int lengthCount = length1 + length2;
        int index = 0; // 索引

        if (length1 == 0) {
            if (length2 % 2 == 1) {
                return nums2[(length2 - 1) / 2];
            } else {
                return (nums2[length2 / 2] + nums2[length2 / 2 - 1]) / 2;
            }

        }
        if (length2 == 0) {
            if (length1 % 2 == 1) {
                return nums1[(length1 - 1) / 2];
            } else {
                return (nums1[length1 / 2] + nums1[length1 / 2 - 1]) / 2;
            }
        }

        //
        final int mid = lengthCount / 2; //代表的是索引
        // 限定条件
        // 判断需要取几个数
        // 判断单双 7 /2=3 ,索引应该是4
        if((length1+length2)%2==1){
            return getIndex(nums1,0,length1-1,nums2,0,length2-1,mid+1);
        }else{
            return (getIndex(nums1,0,length1-1,nums2,0,length2-1,mid)
                    +getIndex(nums1,0,length2-1,nums2,0,length2-1,mid+1))/2;
        }
    }

    public static int getIndex(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int mid) {
        int length1 = end1 - start1 + 1; // 注意+1
        int length2 = end2 - start2 + 1;

        if (mid == 1) {
            return Math.min(nums1[start1], nums2[start2]); // 取量数值中的最小值
        }
        if (length1 > length2) { // 保证顺序
            return getIndex(nums2, start2, end2, nums1, start1, end1, mid);
        }
        if (length1 == 0) { // 只需要在长数组中进行查找
            return nums2[start2 + mid - 1];
        }
        // 3 5 7
        // 2 6 8 10
        // 索引位置
        int a = Math.min(mid / 2, length1);
        int b = mid - a;
        int c = nums1[start1 + a - 1];
        int d = nums2[start2 + b - 1];
        if (c == d) {
            return c;
        } else if (c< d) {
            return getIndex(nums1, start1 + a, end1, nums2, start2, start2 + b - 1, mid - a);
        } else {
            return getIndex(nums1, start1, start1 + a - 1, nums2, start2 + b, end2, mid - b);
        }
    }


}
