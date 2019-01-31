package com.example.haodong.viewday1;

import android.animation.ValueAnimator;
import android.database.ContentObserver;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;

import com.example.haodong.viewday1.day4.ColorTrackTextView;
import com.example.haodong.viewday1.day4.MyCircleScheduleView;

public class MainActivity extends AppCompatActivity {
    private ColorTrackTextView mTv;
    private Button mBtnStart;

    private MyCircleScheduleView myCircleScheduleView;
    private Button btn1;

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

        myCircleScheduleView=findViewById(R.id.myCircleView);
                btn1=findViewById(R.id.btn2);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ValueAnimator valueAnimator=ValueAnimator.ofInt(0,100);
                        valueAnimator.setDuration(2000);
                        valueAnimator.setInterpolator(new DecelerateInterpolator());
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                int progress= (int) animation.getAnimatedValue();
                                myCircleScheduleView.setProgress(progress);
                            }
                        });
                        valueAnimator.start();
                    }
                });
    }
}
