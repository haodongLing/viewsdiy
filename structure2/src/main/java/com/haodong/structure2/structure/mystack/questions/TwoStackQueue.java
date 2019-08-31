package com.haodong.structure2.structure.mystack.questions;


import java.util.Stack;

/**
 * created by linghaoDo on 2019-08-31
 * <p>
 * description: 由 两个栈组成的队列
 */
public class TwoStackQueue {
    private Stack<Integer> stackPush=new Stack<>();
    private Stack<Integer> stackPop=new Stack<>();

    public TwoStackQueue() {
    }
    public void add(int element){
        stackPush.add(element);
    }
    public int poll(){
        if (this.stackPush.isEmpty()&&stackPop.isEmpty()){
            throw new RuntimeException("your stack is empty!");
        }
        while (!stackPush.isEmpty()){
            stackPop.push(stackPush.pop());
        }
        return stackPop.pop();
    }
    public int peek(){
        if (this.stackPush.isEmpty()&&stackPop.isEmpty()){
            throw new RuntimeException("your stack is empty!");
        }
        while (!stackPush.isEmpty()){
            stackPop.push(stackPush.pop());
        }
        return stackPop.peek();
    }



}