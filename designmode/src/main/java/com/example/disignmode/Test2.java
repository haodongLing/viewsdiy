package com.example.disignmode;

/**
 * Create by wuguangliang on 2019/3/13
 */
public class Test2 {
    public static void main(String[] args) {
        int temp = 845454545;
        long first1 = System.currentTimeMillis();
        int b = 0;
        for (int i = 0; i < 845454545; i++) {
            b = temp / 16;
        }
        long last1 = System.currentTimeMillis();
        System.out.println();
        System.out.println("b:" + (last1 - first1) + " " + b);
        System.out.println();

        int temp1 = 845454545;


        long first = System.currentTimeMillis();
        int a = 0;
        for (int i = 0; i < 845454545; i++) {
            a = temp1 >> 4;
        }
        long last = System.currentTimeMillis();
        System.out.println();
        System.out.println("a:" + (last - first) + " " + a);
        System.out.println();


    }
}
