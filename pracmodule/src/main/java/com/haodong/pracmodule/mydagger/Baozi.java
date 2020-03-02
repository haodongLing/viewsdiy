package com.haodong.pracmodule.mydagger;

import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * created by linghaoDo on 2019-12-24
 * description:
 * <p>
 * version:
 */
public class Baozi {
    @Inject
    public Baozi() {
    }

    @NonNull
    @Override
    public String toString() {
        return "小笼包";
    }
}
