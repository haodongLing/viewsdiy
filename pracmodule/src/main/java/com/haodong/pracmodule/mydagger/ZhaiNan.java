package com.haodong.pracmodule.mydagger;

import javax.inject.Inject;

/**
 * created by linghaoDo on 2019-12-25
 * description:
 * <p>
 * version:
 */
public class ZhaiNan {
    @Inject
    Baozi baozi;
    @Inject
    Noodle noodle;

    @Inject
    public ZhaiNan() {

    }

    public String eat() {
        StringBuilder sb = new StringBuilder();
        sb.append("我吃的是： ");
        if (baozi != null) {
            sb.append(baozi.toString());
        }
        if (noodle != null) {
            sb.append(" ").append(noodle.toString());
        }
        return sb.toString();
    }

}
