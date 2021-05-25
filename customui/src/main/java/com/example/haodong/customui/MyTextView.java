package com.example.haodong.customui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import androidx.annotation.Nullable;
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
    private TextPaint mTxtPaint;
    private Rect mTxtRect;

    /*在代码中声明*/
    public MyTextView(Context context) {
        this(context, null);
    }
    /*在布局中使用*/
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }
    /*使用style的时候会调用*/
    public MyTextView(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*获取TypeArray*/
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyTextView);
        mText=typedArray.getString(R.styleable.MyTextView_myText);
        mTextColor=typedArray.getColor(R.styleable.MyTextView_myColor, mTextColor);
        mTextSize=SizeUtils.sp2px(context,typedArray.getDimensionPixelSize(R.styleable
                        .MyTextView_myTextSize,
                mTextSize));
        /*回收*/
        typedArray.recycle();
        init();
    }

    private void init() {
        mTxtPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setColor(mTextColor);
        mTxtPaint.setTextSize(mTextSize);
        mTxtRect=new Rect();
        mTxtPaint.getTextBounds(mText,0,mText.length(),mTxtRect);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);
        int heightMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode==MeasureSpec.AT_MOST){
            widthSize=mTxtRect.width()+ getPaddingLeft() +getPaddingRight();
        }
        if (heightMode==MeasureSpec.AT_MOST){
            heightSize=mTxtRect.height() + getPaddingTop() + getPaddingBottom();
        }

        setMeasuredDimension(widthSize,heightSize);
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
         /*// 画文本
        canvas.drawText();
        // 画弧
        canvas.drawArc();
        // 画圆
        canvas.drawCircle();*/
        // 画文字 text  x  y  paint
        // x 就是开始的位置   0
        // y 基线 baseLine   求？   getHeight()/2知道的   centerY


        //dy 代表的是：高度的一半到 baseLine的距离
        Paint.FontMetricsInt fontMetrics = mTxtPaint.getFontMetricsInt();
        // top 是一个负值  bottom 是一个正值    top，bttom的值代表是  bottom是baseLine到文字底部的距离（正值）
        // 必须要清楚的，可以自己打印就好
        int dy = (fontMetrics.bottom - fontMetrics.top)/2 - fontMetrics.bottom;
        int baseLine = getHeight()/2 + dy;

        int x = getPaddingLeft();
        canvas.drawText(mText,getX()+getPaddingLeft(),baseLine,
                mTxtPaint);
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
