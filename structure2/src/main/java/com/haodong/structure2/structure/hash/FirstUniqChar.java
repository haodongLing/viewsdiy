package com.haodong.structure2.structure.hash;

import java.util.TreeMap;

/**
 * describe :
 * date on 2019/6/30
 * author linghailong
 * email 105354999@qq.com
 */
public class FirstUniqChar {
    public static void main(String[] args) {

    }

    static int query(String s) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        int[] freq = new int[26];
        for (int i = 0; i < s.length(); i++) {
            // 得到对应的ascll码，频率++
            freq[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        }
        return -1;
    }
}
