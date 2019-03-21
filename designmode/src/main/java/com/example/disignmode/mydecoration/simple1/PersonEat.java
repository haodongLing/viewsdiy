package com.example.disignmode.mydecoration.simple1;

import android.util.Log;

/**
 * describe:
 * created at 2019/3/13
 * Author linghailong
 */
public class PersonEat implements Eat {
    @Override
    public void eat() {
        Log.e("tag", "eat: 人吃饭");
    }
}
