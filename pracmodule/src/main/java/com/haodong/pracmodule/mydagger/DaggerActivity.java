package com.haodong.pracmodule.mydagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.haodong.pracmodule.R;

public class DaggerActivity extends AppCompatActivity {
    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        btn1 = findViewById(R.id.btn_dagger_main_test1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ZhaiNan zhaiNan = DaggerPlatform.builder()
                        .build()
                        .waimai();
                Toast.makeText(DaggerActivity.this, zhaiNan.eat(), Toast.LENGTH_SHORT).show();
            }

        });

    }
}
