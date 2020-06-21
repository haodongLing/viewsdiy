package com.haodong.prac.dagger;

import javax.inject.Inject;

/**
 * created by linghaoDo on 2020-05-18
 * description:
 * <p>
 * version:
 */
public class ZhaiNan {
    @Inject
    Baozi baozi;
    @Inject
    Miantiao miantiao;

    @Inject
    public ZhaiNan() {
    }

    public String eat() {
        StringBuilder sb = new StringBuilder();
        sb.append("我吃的是：");
        if (baozi != null) {
            sb.append(baozi.toString());

        }
        if (miantiao != null) {
            sb.append(miantiao.toString());

        }
        return sb.toString();
    }
}
