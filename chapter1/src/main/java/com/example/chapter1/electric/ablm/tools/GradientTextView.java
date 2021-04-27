package com.example.chapter1.electric.ablm.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * description:
 * author: linghailong
 * date: 2019/3/22
 */
public class GradientTextView extends AppCompatTextView {
    private LinearGradient mLinearGradient;
    private Paint mPaint;
    private int mViewWidth = 0;//文字的宽度
    private int mViewHeight = 0;//文字的高度
    private Rect mTextBound = new Rect();
    private int[] mColorList;//存放颜色的数组
    private boolean isVertrial;//默认是横向

    public GradientTextView(Context context) {
        this(context, null);
    }

    public GradientTextView(Context context,
                            AttributeSet attrs) {
        super(context, attrs);
        //设置默认的颜色

//        mColorList = new int[]{R.color.colorStart, R.color.colorMedium, R.color.colorEnd};
        mColorList = new int[]{0xFFFFEABA, 0xFFDFBB82, 0xFFBE8B49};
        int start = Color.parseColor("#57C2B8");
        int medium = Color.parseColor("#5ECCA6");
        int end = Color.parseColor("#67D595");
        mColorList = new int[]{start,medium,end};
    }


    @Override
    protected void onDraw(Canvas canvas) {
        mViewWidth = getMeasuredWidth();
        mPaint = getPaint();
        String mTipText = getText().toString();
        mPaint.getTextBounds(mTipText, 0, mTipText.length(), mTextBound);
        mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, mColorList, null, Shader
                .TileMode.REPEAT);
        mPaint.setShader(mLinearGradient);
        Paint.FontMetrics mFontMetrics = mPaint.getFontMetrics();
        int dx = getWidth() / 2 - mTextBound.width() / 2;
        int dy = (int) ((mFontMetrics.bottom - mFontMetrics.top) / 2 - mFontMetrics.bottom);
        int baseline = getHeight() / 2 + dy;
        canvas.drawText(mTipText, dx, baseline, mPaint);
    }

    /**
     * 设置渐变的颜色
     *
     * @param mColorList
     */
    public void setmColorList(int[] mColorList) {
        if (mColorList != null && mColorList.length < 2) {
            throw new RuntimeException("mClorList's length must be > 2");
        } else {

            this.mColorList = mColorList;
        }
    }
}
