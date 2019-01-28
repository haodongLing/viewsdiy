package com.example.haodong.viewday1;

import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.haodong.viewday1.day4.ColorTrackTextView;

public class MainActivity extends AppCompatActivity {
    private ColorTrackTextView mTv;
    private Button mBtnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mTv=findViewById(R.id.color_track_text);
        mBtnStart=findViewById(R.id.btn_colorTrack);
        mBtnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
                valueAnimator.setDuration(2000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value= (float) animation.getAnimatedValue();
                       mTv.setCurrentProgress(value);
                    }
                });
                valueAnimator.start();
            }
        });
    }
}
