package com.example.haodong.customui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class JumpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump);
        findViewById(R.id.btn_junp).setOnClickListener(v -> {
            startActivity(new Intent(JumpActivity.this,GlideActivity.class));
        });
        SharedPreferences sharedPreferences=getSharedPreferences("data", Context.MODE_PRIVATE);

        //步骤2： 实例化SharedPreferences.Editor对象
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //步骤3：将获取过来的值放入文件
        editor.putString("name","Tom");
        editor.putInt("age", 28);
        editor.putBoolean("marrid",false);
        //步骤4：提交
        editor.commit();

    }
}