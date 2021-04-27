package com.example.chapter1;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Auther linghailong
 * created at 2018/12/11
 * @duscribe: 当手指在矩形区域内点击的时候，矩形边框是红色的。
 */
public class RextPointView extends View {
    private Paint mPaint;
    private Path mPath;
    private Rect mRect;
    /*获取点击的坐标*/


    public RextPointView(Context context) {
        this(context, null);
    }

    public RextPointView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RextPointView(Context context, AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.BLUE);
        mRect = new Rect(100, 10, 300, 100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean isIntercept = false;
        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            isIntercept = true;
        }
        return isIntercept;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
