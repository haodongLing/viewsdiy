package com.example.haodong.customui;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);
        ImageView imageView=findViewById(R.id.image);
        Glide.with(this).load(Uri.parse("https://tva1.sinaimg.cn/large/006fCF3Pgy1gwmr54tn9qj30ha0oi3zy.jpg")).into(imageView);
    }
}