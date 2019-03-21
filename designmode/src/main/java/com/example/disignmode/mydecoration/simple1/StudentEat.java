package com.example.disignmode.mydecoration.simple1;

import android.util.Log;

/**
 * describe:
 * created at 2019/3/13
 * Author linghailong
 */
public class StudentEat implements  Eat {
    @Override
    public void eat() {
        Log.e("TAG","点个菜");
        Log.e("TAG","吃饭吃菜");
        Log.e("TAG","盘子送回指定的地点");
    }
}
