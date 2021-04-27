package com.example.haodong.viewday1.day8;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import com.example.haodong.viewday1.SizeUtils;

/**
 * describe:
 * 1.  手指按下后在1s内出现一个透明度为0.8的圆
 * 2. 手指放开后在1s内圆缩小
 * created at 2019/2/18
 * Author linghailong
 */
public class CircleView extends androidx.appcompat.widget.AppCompatImageView {
    /*最大圆的半径*/
    private float mMaxRadius = SizeUtils.dip2px(getContext(), 90);
    /*最小圆的半径*/
    private float mMinRadius = SizeUtils.dip2px(getContext(), 30);
    private Paint mPaint;
    private float mCurrentRandius = mMinRadius;
    private long mLastCreateTime;
    private boolean isFirstIn = true;
    private int mPreX, mPreY, mCurrentX, mCurrentY;

    /*最小移动距离*/
    private int mMinGap = SizeUtils.dip2px(getContext(), 8);
    private int mMaxGap = SizeUtils.dip2px(getContext(), 60);
    /*是否滑动了最小距离*/
    private boolean mIsMoved = false;
    /*若移动后，使用透明颜色的画笔*/
    private Paint mTransPaint;
    private boolean mIsFinished = false;

    public CircleView(Context context) {
        this(context, null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#70ffffff"));
        mPaint.setStyle(Paint.Style.FILL);

        mTransPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTransPaint.setColor(Color.parseColor("#ffffff"));
        mTransPaint.setStyle(Paint.Style.STROKE);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mIsMoved) {
            float strokeWidth = mMaxRadius - mCurrentRandius;
            mTransPaint.setAlpha(70);
            if (strokeWidth < 3) {
                mTransPaint.setAlpha(0);
            } else {
                mTransPaint.setStrokeWidth(strokeWidth);
                canvas.drawCircle(getWidth() / 2, getHeight() / 2, mCurrentRandius + (strokeWidth
                                / 2),
                        mTransPaint);
            }
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mCurrentRandius + (strokeWidth / 2),
                    mTransPaint);

        } else {
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, mCurrentRandius, mPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 手指按下后在1s内出现一个透明度为0.8的圆
                resetValues();
                mLastCreateTime = System.currentTimeMillis();
                mPreX = (int) event.getX();
                mPreY = (int) event.getY();
                performEnlargeAnimator(mMinRadius, mMaxRadius);
                break;
            case MotionEvent.ACTION_MOVE:
                mCurrentX = (int) event.getX();
                mCurrentY = (int) event.getY();
                //出现小圆圈
                int gap = Math.max(mCurrentY - mPreY, mCurrentX - mPreX);
                if (gap > mMinGap && gap < mMaxGap) {
                    mIsMoved = true;
                    /*保证只能变大*/
                    if(mIsFinished){
                        mCurrentRandius=mMinRadius+gap;
                        invalidate();
                    }

                }
                break;
            case MotionEvent.ACTION_UP:
                if (mIsMoved) {
                    performEnlargeAnimator(mCurrentRandius, mMaxRadius);

                } else if (mIsFinished) {
                    performShrinkAnimator(mMaxRadius, mMinRadius);
                } else {
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            performShrinkAnimator(mMaxRadius, mMinRadius);
                        }
                    }, 150);
                }
                break;
        }
        return true;
    }

    public void resetValues() {
        mCurrentRandius = mMinRadius;
        mIsMoved = false;
        mIsFinished = false;
    }


    public void performEnlargeAnimator(float startValues, final float endValues) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(startValues, endValues);
        valueAnimator.setDuration(150);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float radius = (float) animation.getAnimatedValue();
                Log.e("lhl", "onAnimationUpdate: " + radius);
                if (endValues - radius < 3) {
                    mIsFinished = true;
                }
                Log.e("lhl", "onAnimationUpdate: " + mIsFinished);
                setRadius(radius);
            }
        });
        valueAnimator.start();
    }

    public void performShrinkAnimator(float startValues, final float endValues) {
        ValueAnimator valueAnimator2 = ValueAnimator.ofFloat(startValues, endValues);
        valueAnimator2.setDuration(250);
        valueAnimator2.start();
        valueAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float radius = (float) animation.getAnimatedValue();
                if (radius - endValues < 3) {
                    mIsFinished = true;
                }
                setRadius(radius);
            }
        });
    }

    public void setRadius(float radius) {
        this.isFirstIn = false;
        this.mCurrentRandius = radius;
        postInvalidate();
    }

}
