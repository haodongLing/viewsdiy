package com.haodong.structure2.structure.myarray;

/**
 * created by linghaoDo on 2019-09-29
 * <p>
 * description:longest Substring Without Repeating Characters
 * 获取不重复最长字符串
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int freq[] = new int[256];// 类似于一个hash表，
        int l = 0, r = -1;//[l...r] 集合初始元素个数为0；
        int res = 0;// 满足条件的字串.
        while (l < s.length()) {
            if (r + 1 < s.length() && freq[s.charAt(r + 1)] == 0) {
                r++;//往右继续移动
                freq[s.charAt(r)]++;// 频率++
            } else {
                freq[s.charAt(l++)]--;
            }
            res = Math.max(res, r - l + 1);
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
