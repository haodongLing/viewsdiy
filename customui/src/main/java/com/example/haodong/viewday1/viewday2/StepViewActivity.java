package com.example.haodong.viewday1.viewday2;

import android.animation.ValueAnimator;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.animation.DecelerateInterpolator;

import com.example.haodong.viewday1.R;

public class StepViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_view);
        final QQStepView qqStepView =  findViewById(R.id.step_view);
        qqStepView.setStepMax(4000);

        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 3000);
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int currentStep = (int) animation.getAnimatedValue();
                qqStepView.setCurrentStep(currentStep);
            }
        });
        valueAnimator.start();
    }
}
