package com.haodong.prac.dagger;

import androidx.annotation.NonNull;

import javax.inject.Inject;

/**
 * created by linghaoDo on 2020-05-18
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
