package com.haodong.structure2.structure.mystack.questions;


import java.util.Stack;

/**
 * created by linghaoDo on 2019-08-31
 * <p>
 * description:
 * 1. 仅仅使用栈操作和递归函数逆序一个栈
 */
public class StackUtil {
    public static int getAndRemoveLastElement(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            throw new RuntimeException("your stack is empty");
        }
        int result = stack.pop();
        if (stack.isEmpty()) {
            return result;
        } else {
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }

    }
    public static void reverse(Stack<Integer>stack){
        if (stack.isEmpty()){
            return;
        }
        int i=getAndRemoveLastElement(stack);
        reverse(stack);
        stack.push(i);
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
//        System.out.println(getAndRemoveLastElement(stack));
//        System.out.println(stack.size()+"----"+stack.peek());
        for (int element:stack){
            System.out.print(element+"   ");
        }
        reverse(stack);
        System.out.println("-------");
        for (int element:stack){
            System.out.print(element+"   ");
        }
    }


}
