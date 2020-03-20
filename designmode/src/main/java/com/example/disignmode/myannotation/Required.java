package com.example.disignmode.myannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * created by linghaoDo on 2020-03-19
 * description:
 * <p>
 * version:
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
    String name() default "name";
    int value() default 0;
}
