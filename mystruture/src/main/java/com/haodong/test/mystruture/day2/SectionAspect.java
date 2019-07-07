package com.haodong.test.mystruture.day2;

import org.aspectj.lang.annotation.Pointcut;

/**
 * describe :
 * date on 2019/7/5
 * author linghailong
 * email 105354999@qq.com
 */
public class SectionAspect {

    /**
     * 找到处理的切点
     */
    @Pointcut("execution(@Che)")
    public void checkNetBehavior(){

    }
}
