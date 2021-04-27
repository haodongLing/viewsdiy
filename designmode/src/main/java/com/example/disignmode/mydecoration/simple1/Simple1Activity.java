package com.example.disignmode.mydecoration.simple1;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.disignmode.R;

/**
 * describe:
 * created at 2019/3/13
 * Author linghailong
 */
public class Simple1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple1);
        // 一般的写法new对象调用方法
        TeacherEat teacherEat = new TeacherEat();
        teacherEat.eat();

        /*装饰设计模式怎么写*/
    }
}
