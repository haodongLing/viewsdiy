package com.example.haodong.viewday1.day9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.haodong.viewday1.MainActivity;
import com.example.haodong.viewday1.R;

public class Day9Activity extends AppCompatActivity {
    TouchView mTouchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day9);
        mTouchView=findViewById(R.id.touch_view);
        mTouchView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.e("TAG", "onTouch: " + event.getAction());
                return false;
            }
        });
        mTouchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG", "onClick: " );
            }
        });

    }
}
