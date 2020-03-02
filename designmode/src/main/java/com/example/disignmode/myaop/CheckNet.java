package com.example.disignmode.myaop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by linghaoDo on 2019-11-17
 * description:
 * <p>
 * version: aop 注解，检查网络
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckNet {
    int vsersion() default -1;
    String name() default "checkName";

}
