package com.haodong.structure2.structure.mystack.questions;


import java.util.Stack;

/**
 * created by linghaoDo on 2020-04-13
 * description:
 * <p>
 * version:
 */
public class Questions {
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        if (s.equals(""))
            return true;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                /*1. 判断stack的size*/
                stack.push(c);
            } else {
                if (stack.size() > 0) {
                    char peekC = stack.peek();
                    if ((peekC == '(' && c != ')') || (peekC == '{' && c != '}') || (peekC == '[' && c != ']')) {
                        return false;
                    } else {
                        stack.pop();
                    }
                } else {
                    return false;
                }
            }

        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        String s = "{[]}";
        System.out.println("solution-->" + isValid(s));

    }
}
