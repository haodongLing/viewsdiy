package com.haodong.structure2.structure.mystack;

import java.util.Stack;

/**
 * describe :有效的括号
 * date on 2019/7/4
 * author linghailong
 * email 105354999@qq.com
 */
public class Solution {
    public static void main(String[] args) {
        String s = "";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
//        Stack<Character> stack = new ArrayStack<>();
        Stack<Character> stack = new Stack<>();
        if (s.equals("")) {
            return true;
        }
        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c == '[' || c == '{' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (top == '(' && c != ')')
                    return false;
                else if (top == '{' && c != '}') {
                    return false;
                } else if (top == '[' && c != ']') {
                    return false;
                }
            }

        }
        return stack.isEmpty();
    }
}
