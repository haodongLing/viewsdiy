package com.example.haodong.customui;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.bumptech.glide.RequestBuilder;
import com.example.haodong.common.util.LogUtil;

import java.io.IOException;
import java.util.HashMap;

public class AnimationActivity extends AppCompatActivity {
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.button_scale);
        btn1=findViewById(R.id.btn1);
        btn1.startAnimation(animation);
    }
}