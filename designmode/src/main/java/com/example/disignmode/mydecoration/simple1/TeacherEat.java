package com.example.disignmode.mydecoration.simple1;

import android.util.Log;

/**
 * Created by hcDarren on 2017/9/30.
 */

public class TeacherEat implements Eat{
    @Override
    public void eat() {
        Log.e("TAG","喝个汤");
        Log.e("TAG","点个菜");
        Log.e("TAG","吃饭吃菜");
        Log.e("TAG","盘子不用送吃完走人");
    }
}
