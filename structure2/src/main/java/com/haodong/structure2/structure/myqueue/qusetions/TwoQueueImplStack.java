package com.haodong.structure2.structure.myqueue.qusetions;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * created by linghaoDo on 2019-09-16
 * <p>
 * description: 两个队列实现一个栈
 */
public class TwoQueueImplStack {
    Queue<Integer> queue1 = new ArrayDeque<Integer>();
    Queue<Integer> queue2 = new ArrayDeque<Integer>();

    public void push(Integer element) {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            queue1.add(element);
            return;
        }
        //如果queue1为空，queue2有数据，直接放入queue2
        if (queue1.isEmpty()) {
            queue2.add(element);
            return;
        }

        //如果queue2为空，queue1有数据，直接放入queue1中
        if (queue2.isEmpty()) {
            queue1.add(element);
            return;
        }

    }

    /*
     * 从栈中弹出一个数据
     */
    public Integer pop() {
        //如果两个栈都为空，则没有元素可以弹出，异常
        if (queue1.isEmpty() && queue2.isEmpty()) {
            try {
                throw new Exception("satck is empty!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //如果queue1中没有元素，queue2中有元素，将其queue2中的元素依次放入queue1中，直到最后一个元素，弹出即可
        if (queue1.isEmpty()) {
            while (queue2.size() > 1) {
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        }

        //如果queue2中没有元素，queue1中有元素，将其queue1中的元素依次放入queue2中，直到最后一个元素，弹出即可
        if (queue2.isEmpty()) {
            while (queue1.size() > 1) {
                queue2.add(queue1.poll());
            }
            return queue1.poll();
        }

        return (Integer) null;
    }

    public static void main(String[] args) {
        TwoQueueImplStack qs = new TwoQueueImplStack();
        qs.push(2);
        qs.push(4);
        qs.push(7);
        qs.push(5);
        System.out.println(qs.pop());
        System.out.println(qs.pop());

        qs.push(1);
        System.out.println(qs.pop());
    }
}
