package com.example.haodong.viewday1.day9;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.haodong.common.util.LogUtil;
import com.example.haodong.viewday1.R;

public class Day9Activity extends AppCompatActivity {
    TouchView mTouchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day9);
        mTouchView = findViewById(R.id.touch_view);
        mTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: ");
            }
        });
        mTouchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                LogUtil.i("TAG", "onTouch");
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
