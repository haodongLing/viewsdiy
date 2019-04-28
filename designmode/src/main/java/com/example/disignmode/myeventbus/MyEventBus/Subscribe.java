package com.example.disignmode.myeventbus.MyEventBus;

import org.greenrobot.eventbus.ThreadMode;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * describe :
 * date on 2019/4/28
 * author linghailong
 * email 105354999@qq.com
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Subscribe {
    ThreadMode threadMode() default ThreadMode.POSTING;

    boolean sticky() default false;

    int priority() default 0;
}
