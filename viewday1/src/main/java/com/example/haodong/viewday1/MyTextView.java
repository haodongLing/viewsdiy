package com.example.haodong.viewday1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Auther linghailong
 * created at 2019/1/22
 * @duscribe:
 */
public class MyTextView extends View {
    private String mText;
    private int mTextSize = 15;
    private int mTextColor = Color.BLACK;
    private Rect mTxtBounds = null;
    private TextPaint mTxtPaint = null;

    /*在代码中声明*/
    public MyTextView(Context context) {
        this(context, null);
    }

    /*在布局中使用*/
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        /*获取TypeArray*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        typedArray.getString(R.styleable.MyTextView_myText);
        typedArray.getColor(R.styleable.MyTextView_myColor, mTextColor);
        typedArray.getDimensionPixelSize(R.styleable.MyTextView_myTextSize, SizeUtil.sp2Px
                (context, mTextSize));
        /*回收*/
        typedArray.recycle();
    }

    /*使用style的时候会调用*/
    public MyTextView(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mTxtPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setColor(mTextColor);
        mTxtPaint.setTextSize(mTextSize);
        mTxtPaint.setStyle(Paint.Style.STROKE);
        mTxtBounds = new Rect();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST) {

            mTxtPaint.getTextBounds(mText, 0, mText.length(), mTxtBounds);
            widthSize = mTxtBounds.width() + getPaddingLeft() + getPaddingRight();
        }


        if (widthMode == MeasureSpec.AT_MOST) {
            // 获取文本的Rect
            mTxtPaint.getTextBounds(mText, 0, mText.length(), mTxtBounds);
            heightSize = mTxtBounds.height() + getPaddingTop() + getPaddingBottom();
        }

        // 设置控件的宽高
        setMeasuredDimension(widthSize, heightSize);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawText(canvas);

    }

    private void drawText(Canvas canvas) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
