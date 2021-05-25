package com.example.haodong.customui.day7;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.haodong.customui.SizeUtils;

/**
 * duscribe:
 * created at 2019/1/30
 * Auther linghailong
 */
public class LetterSideBar extends View {

    private Paint.FontMetrics mFontMetrics;
    // 定义26个字母
    public static String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private Rect mTxtBounds;
    private Paint mTxtPaint;
    private Paint mFocusedPaint;
    // 当前触摸的位置字母
    private String mCurrentTouchLetter;
    private float mPreY;
    private int mLetterHeight;
    private int mCurrentPosition;

    public LetterSideBar(Context context) {
        this(context, null);
    }

    public LetterSideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LetterSideBar(Context context, AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*  TypedArray typedArray=context.obtainStyledAttributes()*/
        mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setTextSize(SizeUtils.dip2px(context, 12));
        mTxtPaint.setColor(Color.BLUE);
        /*当滑动到字母对应的位置时，字母高亮的画笔的初始化*/
        mFocusedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFocusedPaint.setColor(Color.RED);
        mFocusedPaint.setTextSize(SizeUtils.dip2px(context, 12));

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST) {
            widthSpecSize = (int) (getPaddingLeft() + getPaddingRight() + mTxtPaint.measureText
                    ("A"));

        }
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mLetterHeight = (getHeight() - getPaddingTop() - getPaddingBottom()) / mLetters.length;
        setMeasuredDimension(widthSpecSize, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < mLetters.length; i++) {
            int letterCenterY = mLetterHeight * i + mLetterHeight / 2 + getPaddingTop();
            mFontMetrics = mTxtPaint.getFontMetrics();
            int baseLine = (int) ((mFontMetrics.bottom - mFontMetrics.top) / 2 - mFontMetrics
                    .bottom) + letterCenterY;
            // x 绘制在最中间 = 宽度/2 - 文字/2
            int textWidth = (int) mTxtPaint.measureText(mLetters[i]);
            int x = getWidth() / 2 - textWidth / 2;
            // 当前字母 高亮  用两个画笔(最好) 改变颜色
            if (mLetters[i].equals(mCurrentTouchLetter)) {
                canvas.drawText(mLetters[i], x, baseLine, mFocusedPaint);
            } else {
                canvas.drawText(mLetters[i], x, baseLine, mTxtPaint);
            }


        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mPreY = event.getY();
                mCurrentPosition = (int) (mPreY / mLetterHeight);
                if (mCurrentPosition < 0)
                    mCurrentPosition = 0;
                if (mCurrentPosition > mLetters.length - 1)
                    mCurrentPosition = mLetters.length - 1;
                // 要判断 ？
                mCurrentTouchLetter = mLetters[mCurrentPosition];
                mLetterTouchedListener.onTouched(mCurrentTouchLetter, true);
                /*重绘*/
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                float currentMoveY = event.getY();
                if (Math.abs(currentMoveY - mPreY) > mLetterHeight) {
                    mCurrentPosition = (int) (currentMoveY / mLetterHeight);
                    if (mCurrentPosition < 0)
                        mCurrentPosition = 0;
                    if (mCurrentPosition > mLetters.length - 1)
                        mCurrentPosition = mLetters.length - 1;
                    // 要判断 ？
                    mCurrentTouchLetter = mLetters[mCurrentPosition];
                    mLetterTouchedListener.onTouched(mCurrentTouchLetter, true);
                    /*重绘*/
                    mPreY = event.getY();
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                mLetterTouchedListener.onTouched(mCurrentTouchLetter, false);
                break;
            default:
                break;

        }
        return true;

    }

    private LetterTouchedListener mLetterTouchedListener;

    public void setOnLetterTouchedListener(LetterTouchedListener letterTouchedListener) {
        this.mLetterTouchedListener = letterTouchedListener;
    }

    public interface LetterTouchedListener {
        void onTouched(String letter, boolean isTouched);
    }

}
