package com.example.haodong.viewday1.day7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * describe:
 * created at 2019/1/31
 * Author linghailong
 */
public class QuadToView extends View {
    private Paint mPaint;
    private Path mPath;
    private float mPreX, mPreY;

    public QuadToView(Context context) {
        this(context, null);
    }

    public QuadToView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuadToView(Context context, AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath,mPaint);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                /*获取手指按下的x,y*/
                mPreX = event.getX();
                mPreY = event.getY();
                mPath.moveTo(mPreX, mPreY);
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                float y = event.getY();
                mPath.quadTo((x + mPreX) / 2, (y + mPreY) / 2, x, y);
                mPreX = event.getX();
                mPreY = event.getY();
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }
}
