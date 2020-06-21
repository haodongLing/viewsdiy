package com.haodong.prac.dagger;

import android.support.annotation.NonNull;

import javax.inject.Inject;


/**
 * created by linghaoDo on 2020-05-18
 * description:
 * <p>
 * version:
 */
public class Miantiao {
    @Inject
    public Miantiao() {
    }

    @NonNull
    @Override
    public String toString() {
        return "面条";
    }
}
