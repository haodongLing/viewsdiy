package com.example.disignmode.myaop;

/**
 * created by linghaoDo on 2019-11-18
 * description:
 * <p>
 * version:
 */
@CheckNet(vsersion = 2, name = "lal")
public class T1 {
    public static void main(String[] args) {
        boolean hasAnnotation = T1.class.isAnnotationPresent(CheckNet.class); // 判断是否有注解
        if (hasAnnotation) {
            CheckNet checkNet = T1.class.getAnnotation(CheckNet.class);
            System.out.println("version-->" + checkNet.vsersion());
            System.out.println("name-->" + checkNet.name());
        }
    }


}
