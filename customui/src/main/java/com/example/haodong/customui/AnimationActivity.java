package com.example.haodong.customui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class AnimationActivity extends AppCompatActivity {
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.button_scale);
        btn1=findViewById(R.id.btn1);
        btn1.startAnimation(animation);
//        startActivity();
    }
}