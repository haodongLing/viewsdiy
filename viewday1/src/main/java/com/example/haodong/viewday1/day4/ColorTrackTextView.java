package com.example.haodong.viewday1.day4;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.example.haodong.viewday1.R;

/**
 * duscribe:
 * created at 2019/1/28
 * Auther linghailong
 */
@SuppressLint("AppCompatCustomView")
public class ColorTrackTextView extends TextView {
    private static final String TAG = ColorTrackTextView.class.getSimpleName();
    // 1. 实现一个文字两种颜色 - 绘制不变色字体的画笔
    private Paint mOriginPaint;
    // 1. 实现一个文字两种颜色 - 绘制变色字体的画笔
    private Paint mChangePaint;
    // 1. 实现一个文字两种颜色 - 当前的进度
    private float mCurrentProgress = 0.0f;
    // 文字变色的方向
    private Direction mDirection = Direction.LEFT_TO_RIGHT;
    private Rect mBounds;
    private Rect mPanelRect;

    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

    public ColorTrackTextView(Context context) {
        this(context, null);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ColorTrackTextView(Context context, @Nullable AttributeSet attrs,
                              int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
                .ColorTrackTextView);
        /*getDefaultColor()是字体库中自带的颜色*/
        int originColor = typedArray.getColor(R.styleable.ColorTrackTextView_originColor,
                getTextColors().getDefaultColor());
        int changeColor = typedArray.getColor(R.styleable.ColorTrackTextView_changeColor,
                getTextColors().getDefaultColor());

        mOriginPaint = getPaintByColor(originColor);
        mChangePaint = getPaintByColor(changeColor);
        typedArray.recycle();
        init();
    }

    private void init() {
        mBounds = new Rect();
        mPanelRect = new Rect();
    }

    private Paint getPaintByColor(int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setColor(color);
        paint.setTextSize(getTextSize());
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 根据进度把中间值算出来
        int middle = (int) (mCurrentProgress * getWidth());
        if (mDirection == Direction.LEFT_TO_RIGHT) {  // 左边是红色右边是黑色
            // 绘制变色
            drawText(canvas, mChangePaint, 0, middle);
            drawText(canvas, mOriginPaint, middle, getWidth());

        } else {
            // 右边是红色左边是黑色
            drawText(canvas, mChangePaint, getWidth() - middle, getWidth());
            // 绘制变色
            drawText(canvas, mOriginPaint, 0, getWidth() - middle);
        }


    }

    /**
     * 绘制Text
     *
     * @param canvas
     * @param txtPaint
     * @param start    文字的起始点
     * @param end      文字的结束点
     */
    private void drawText(Canvas canvas, Paint txtPaint, int start, int end) {
        canvas.save();

        // 绘制不变色

        mPanelRect.set(start, 0, end, getHeight());
        canvas.clipRect(mPanelRect);
        Log.e(TAG, "drawText: " + mPanelRect.toString());
        canvas.clipRect(mPanelRect);
        String text = getText().toString();
        txtPaint.getTextBounds(text, 0, text.length(), mBounds);
        Paint.FontMetrics mFontMetrics = txtPaint.getFontMetrics();
        int dx = getWidth() / 2 - mBounds.width() / 2;
        int dy = (int) ((mFontMetrics.bottom - mFontMetrics.top) / 2 - mFontMetrics.bottom);
        int baseline = getHeight() / 2 + dy;
        canvas.drawText(text, dx, baseline, txtPaint);
        canvas.restore();
    }

    public void setDirection(Direction direction) {
        this.mDirection = direction;
    }

    public synchronized void setCurrentProgress(float currentProgress) {
        this.mCurrentProgress = currentProgress;
        invalidate();
    }

    public void setChangeColor(int changeColor) {
        this.mChangePaint.setColor(changeColor);
    }

    public void setOriginColor(int originColor) {
        this.mOriginPaint.setColor(originColor);
    }
}
