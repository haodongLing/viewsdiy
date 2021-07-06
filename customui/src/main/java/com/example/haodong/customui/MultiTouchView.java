package com.example.haodong.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/6/20
 * Description:
 */
public class MultiTouchView extends View {
    private boolean hasSecondPoint = false;
    private PointF pointF = new PointF(0, 0);
    private Paint mDefaultPaint;

    public MultiTouchView(Context context) {
        this(context, null);
    }

    public MultiTouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mDefaultPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDefaultPaint.setColor(Color.WHITE);
        mDefaultPaint.setTextAlign(Paint.Align.CENTER);
        mDefaultPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
        if (hasSecondPoint) {
            canvas.drawCircle(pointF.x, pointF.y, 50, mDefaultPaint);
        }
        canvas.save();
        canvas.translate(getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        canvas.drawText("追踪第二个按下手指的位置", 0, 0, mDefaultPaint);
        canvas.restore();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_POINTER_DOWN:
                if (event.getPointerCount() > 1) {
                    hasSecondPoint = true;
                    pointF.set(event.getX(), event.getY());
                }
                break;

            case MotionEvent.ACTION_MOVE:
                try {
                    if (hasSecondPoint) {
//                        int pointerIndex = event.findPointerIndex(1);
                        pointF.set(event.getX(1), event.getY(1));

                    }
                } catch (Exception ex) {
                    hasSecondPoint = false;
                }

                break;

            case MotionEvent.ACTION_POINTER_UP:
                if (event.getPointerCount() < 2) {
                    hasSecondPoint = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                hasSecondPoint = false;
                break;
        }
        invalidate();
        return true;
    }
}
