package com.example.haodong.viewday1.day7;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.haodong.viewday1.SizeUtils;

/**
 * duscribe:
 * created at 2019/1/30
 * Auther linghailong
 */
public class LetterSideBar extends View {
    private Paint mTxtPaint;
    private Paint.FontMetrics mFontMetrics;
    // 定义26个字母
    public static String[] mLetters = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "#"};
    private Rect mTxtBounds;
    public LetterSideBar(Context context) {
        this(context,null);
    }

    public LetterSideBar(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LetterSideBar(Context context,  AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
      /*  TypedArray typedArray=context.obtainStyledAttributes()*/
        mTxtPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setTextSize(SizeUtils.dip2px(context,12));
        mTxtPaint.setColor(Color.BLUE);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);
        if (widthSpecMode==MeasureSpec.AT_MOST){
            widthSpecSize= (int) (getPaddingLeft()+getPaddingRight()+mTxtPaint.measureText("A"));

        }
        int height=MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSpecSize,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int itemHeight=(getHeight()-getPaddingTop()-getPaddingBottom())/mLetters.length;

        for (int i=0;i<mLetters.length;i++) {
            int letterCenterY=itemHeight*i+itemHeight/2+getPaddingTop();
              mFontMetrics=mTxtPaint.getFontMetrics();
            int baseLine= (int) ((mFontMetrics.bottom-mFontMetrics.top)/2-mFontMetrics.bottom)+letterCenterY;
            // x 绘制在最中间 = 宽度/2 - 文字/2
            int textWidth = (int) mTxtPaint.measureText(mLetters[i]);
            int x = getWidth() / 2 - textWidth / 2;
            canvas.drawText(mLetters[i],x,baseLine,mTxtPaint);
            invalidate();


        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){


        }
        return true;

    }
}
