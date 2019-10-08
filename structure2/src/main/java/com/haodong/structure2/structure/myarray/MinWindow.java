package com.haodong.structure2.structure.myarray;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.HashMap;
import java.util.Map;

/**
 * created by linghaoDo on 2019-10-07
 * <p>
 * description: 最小覆盖子串
 * <p>
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 */
public class MinWindow {
    public static void main(String[] args) {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }
        int start = 0, minLen = Integer.MAX_VALUE;
        int left = 0, right = 0;
        /*字典，它保存当前窗口中所有唯一字符的计数。*/
        Map<Character, Integer> windowCounts = new HashMap<>();
        Map<Character, Integer> needs = new HashMap<>();
        // Dictionary which keeps a count of all the unique characters in t.
        Map<Character, Integer> dictT = new HashMap<Character, Integer>();
        for (int i = 0; i < t.length(); i++) {
            int count = dictT.getOrDefault(t.charAt(i), 0);
            dictT.put(t.charAt(i), count + 1);
        }
        // Number of unique characters in t, which need to be present in the desired window.
        int required = dictT.size();
        // formed用于记录t中有多少个唯一字符
        // 以期望的频率出现在当前窗口中。
        // 例如，如果t是“AABC”，那么窗口必须有两个A，一个B和一个C。
        // 当所有这些条件都满足时，则得到= 3。
        int formed = 0;
        // ans list of the form (window length, left, right)
        int[] ans = {-1, 0, 0};
        while (right < s.length()) {
            char c = s.charAt(right);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
            while (left<right&&formed==required){
//                c=s.charAt(left);
                c = s.charAt(left);
                // Save the smallest window until now.
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right- left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                    formed--;
                }

                // Move the left pointer ahead, this would help to look for a new window.
                left++;
            }
            right++;
        }
        return ans[0]==-1?"": s.substring(ans[1], ans[2] + 1);
    }
}
