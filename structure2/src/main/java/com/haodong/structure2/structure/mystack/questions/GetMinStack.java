package com.haodong.structure2.structure.mystack.questions;

import com.haodong.structure2.structure.mystack.ArrayStack;
import com.haodong.structure2.structure.mystack.Stack;

/**
 * created by linghaoDo on 2019-08-31
 * <p>
 * description: 设计一个有getMin()功能的栈
 */
public class GetMinStack {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public GetMinStack() {
        this.stackData = new ArrayStack<>();
        this.stackMin = new ArrayStack<>();

    }

    public int getMin() {
        if (stackMin.isEmpty()) {
            throw new RuntimeException("your stack is empty!");

        }
        return this.stackMin.peek();
    }

    public void push(int min) {
        if (stackMin.isEmpty()) {
            stackMin.push(min);
        } else if (min < stackMin.peek()) {
            stackMin.push(min);
        }
        stackData.push(min);
    }

    public int pop() {
        if (this.stackMin.isEmpty()) {
            throw new RuntimeException("your stack is empty!");

        } else
            return this.stackMin.pop();
    }


}
