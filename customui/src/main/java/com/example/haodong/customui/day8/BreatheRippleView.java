package com.example.haodong.customui.day8;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.example.haodong.customui.SizeUtils;

/**
 * describe:
 * created at 2019/2/18
 * Author linghailong
 */
public class BreatheRippleView extends View {
    private float mInitialRadius = SizeUtils.dip2px(getContext(), 10);   // 初始波纹半径
    private float mMaxRadiusRate = 0.85f;   // 如果没有设置mMaxRadius，可mMaxRadius = 最小长度 * mMaxRadiusRate;
    private float mMaxRadius = SizeUtils.dip2px(getContext(), 20);   // 最大波纹半径
    private long mDuration = 1500; // 一个波纹从创建到消失的持续时间
    private boolean mIsRunning=false;

    private Paint mPaint;
    private long mLastCreateTime;
    private boolean isFirstIn=true;
    private LinearInterpolator mInterpolator = new LinearInterpolator();

    public BreatheRippleView(Context context) {
        this(context,null);
    }

    public BreatheRippleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BreatheRippleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(Color.WHITE);
        start();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop();
    }
    /**
     * 开始
     */
    public void start() {
        if (!mIsRunning) {
            mIsRunning = true;
            mCreateCircle.run();
        }
    }
    /**
     * 停止
     */
    public void stop() {
        mIsRunning = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setAlpha(getMyAlpha());
        canvas.drawCircle(getWidth() / 2, getHeight() / 2,getCurrentRadius(), mPaint);
    }
    private Runnable mCreateCircle = new Runnable() {
        @Override
        public void run() {
            if (mIsRunning) {
                mLastCreateTime=System.currentTimeMillis();
                postInvalidate();
                postDelayed(mCreateCircle, mDuration);
            }
        }
    };
    public int getMyAlpha() {
        float percent = (System.currentTimeMillis() - mLastCreateTime) * 1.0f / 1500;
        return (int) ((1.0f - mInterpolator.getInterpolation(percent)) * 255);
    }
    public float getCurrentRadius() {
        float percent = (System.currentTimeMillis() - mLastCreateTime) * 1.0f / mDuration;
        return mInitialRadius + mInterpolator.getInterpolation(percent) * (mMaxRadius - mInitialRadius);
    }
}
