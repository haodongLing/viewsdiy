package com.haodong.pracmodule.mydagger;

import androidx.annotation.NonNull;

import javax.inject.Inject;

/**
 * created by linghaoDo on 2019-12-25
 * description:
 * <p>
 * version:
 */
public class Noodle {
    @Inject
    public Noodle() {
    }

    @NonNull
    @Override
    public String toString() {
        return "面条";
    }
}
