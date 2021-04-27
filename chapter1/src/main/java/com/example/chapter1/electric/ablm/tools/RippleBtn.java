package com.example.chapter1.electric.ablm.tools;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * @author Created by LiuZh.
 * @date on 2018/2/1.
 * <p>
 * 没有写不出来的bug,只有不努力的码农
 */
public class RippleBtn extends AppCompatTextView {

    private static final String TAG = "RippleBtn";
    Paint mCircle1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mCircle2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float mRadius = -50;
    private float mOffset = 50;
    private ValueAnimator mAnimator;

    public RippleBtn(Context context) {
        this(context, null);
    }

    public RippleBtn(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RippleBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs);
    }

    public RippleBtn(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        this(context, attrs);
    }

    private void init() {
        mCircle1Paint.setColor(Color.WHITE);
        mCircle1Paint.setStyle(Paint.Style.FILL);
        mCircle2Paint.setColor(Color.WHITE);
        mCircle2Paint.setStyle(Paint.Style.FILL);
        setClickable(true);
        setFocusable(true);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2,
                mRadius, mCircle1Paint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2,
                mRadius + mOffset, mCircle2Paint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 动画参数和宽度有关, 所以放在sizeChanged内调用start
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }

    public void stop() {
        if (mAnimator != null) {
            mAnimator.cancel();
            mAnimator = null;
        }
    }

    long lastMillis = 0;

    private void start() {
        mAnimator = ValueAnimator.ofFloat(-50, getWidth() / 2 + 50);
        mAnimator.setDuration(2000);
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRadius = (float) animation.getAnimatedValue();
                long currentMillis = System.currentTimeMillis();
                float frc = 1 - mRadius / (getWidth() / 2f + 50f);
                mCircle1Paint.setAlpha((int) (90 * frc));
                mCircle2Paint.setAlpha((int) (50 * frc));
                mOffset = 50 + (1 - frc) * 50;
                // 限制重绘频率
                if (currentMillis - lastMillis > 30) {
                    invalidate();
                    lastMillis = currentMillis;
                }
            }
        });
        mAnimator.setRepeatMode(ValueAnimator.RESTART);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.start();
    }
}
