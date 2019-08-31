package com.haodong.structure2.structure.mystack;

/**
 * describe :
 * date on 2019/7/4
 * author linghailong
 * email 105354999@qq.com
 */
public class Main {
    public static void main(String args[]){
        ArrayStack<Integer>stack=new ArrayStack<>();
        for (int i=0;i<5;i++){
            stack.push(i);
            System.out.println(stack.toString());
        }
        stack.pop();
        System.out.println(stack.toString());
    }


}
