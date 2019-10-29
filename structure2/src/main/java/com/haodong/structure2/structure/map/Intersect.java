package com.haodong.structure2.structure.map;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * created by linghaoDo on 2019-10-08
 * <p>
 * description: 两数的交集2
 */
public class Intersect {
    public static void main(String[] args) {
    }

    /**
     * 核心在于需要记录每个元素出现的次数，这就需要使用映射
     *
     * @param nums1
     * @param nums2
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public int[] intersect(int[] nums1, int[] nums2) {
        /*第一步，遍历第一个数组中元素*/
        TreeMap<Integer, Integer> mapNums1 = new TreeMap<>();
        for (int i = 0; i < nums1.length; i++) {
            if (mapNums1.containsKey(nums1[i])) {
                mapNums1.put(nums1[i], mapNums1.get(nums1[i]) + 1);
            } else {
                mapNums1.put(nums1[i], 1);
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        /*遍历第二个数组，获取*/
        for (int num2 : nums2) {
            if (mapNums1.containsKey(num2) && mapNums1.get(num2) > 0) {
                result.add(num2);
                mapNums1.put(num2, mapNums1.get(num2) - 1);
            }
        }
        int[] ret = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ret[i] = result.get(i);
        }

        return ret;

    }

    /**
     * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
     * <p>
     * 示例 1:
     * <p>
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: s = "rat", t = "car"
     * 输出: false
     * 说明:
     * 你可以假设字符串只包含小写字母。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/valid-anagram
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param t
     * @return
     */

    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> sMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            final char c = s.charAt(i);
            if (sMap.containsKey(c)) {
                sMap.put(c, sMap.get(c) + 1);
            } else
                sMap.put(c, 1);
        }
        HashMap<Character, Integer> tMap = new HashMap<>();
        if (s.length() == t.length()) {
            for (int i = 0; i < t.length(); i++) {
                final char c = t.charAt(i);
                if (!sMap.containsKey(c)) {
                    return false;
                }
                if (tMap.containsKey(c)) {
                    tMap.put(c, tMap.get(c) + 1);
                } else
                    tMap.put(c, 1);
            }
        }
//        char[] result = new char[sMap.keySet().size()];
//       for (int i=0;i<sMap.keySet().size();i++){
//           result[i]=sMap.keySet().toArray(result);
//       }
        for (int i = 0; i < sMap.size(); i++) {
            if (tMap.containsKey(sMap.get(i))){

            }
        }
        return false;

    }
}
