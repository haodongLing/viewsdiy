package com.haodong.structure2.structure.mystack.questions;

import java.util.LinkedList;
import java.util.Stack;

/**
 * created by linghaoDo on 2020-03-09
 * description:
 * <p>
 * version:
 */
public class ReserveStack {
    public static void main(String args[]) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        reserse(stack);
        while (!stack.isEmpty()){
            System.out.println(stack.pop()+"    ");
        }
    }

    public static int getAndRemoveLastelement(Stack<Integer> stack) {
        int result = stack.pop(); // 3  2 //1
        if (stack.isEmpty()) { // false  // false //true
            System.out.println("stack.isEmpty()-->" + result);
            return result; // 1
        } else {
            int last = getAndRemoveLastelement(stack); //1
            stack.push(result);  // 1
            return last;
        }
    }

    public static void reserse(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastelement(stack);
        System.out.println("i-->" + i);
        reserse(stack);
        stack.push(i);
    }
}
