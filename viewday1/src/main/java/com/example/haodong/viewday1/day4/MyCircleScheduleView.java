package com.example.haodong.viewday1.day4;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.haodong.viewday1.R;
import com.example.haodong.viewday1.SizeUtils;

/**
 * duscribe:
 * created at 2019/1/28
 * Auther linghailong
 */
public class MyCircleScheduleView extends View {
    private TextPaint mTxtPaint;
    private Paint mSchedulePaint;
    private Paint mNormalPaint;
    private Rect mBounds;
    private RectF mRectF;
    private int mStokeWidth = 6;
    private int progress=0;

    public MyCircleScheduleView(Context context) {
        this(context, null);
    }

    public MyCircleScheduleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyCircleScheduleView(Context context, AttributeSet attrs,
                                int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable
                .MyCircleScheduleView);
        mStokeWidth = typedArray.getDimensionPixelOffset(R.styleable
                .MyCircleScheduleView_myStrokeWidth, mStokeWidth);
        mStokeWidth = SizeUtils.dip2px(context, mStokeWidth);
        mNormalPaint = initPaint(typedArray.getColor(R.styleable
                .MyCircleScheduleView_myNormalColor, Color.BLUE), mStokeWidth);
        mSchedulePaint = initPaint(typedArray.getColor(R.styleable
                .MyCircleScheduleView_myScheduledColor, Color.RED), mStokeWidth);
        mTxtPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setDither(true);
        mTxtPaint.setColor(typedArray.getColor(R.styleable
                .MyCircleScheduleView_myScheduledColor, Color.RED));
        mTxtPaint.setTextSize(mStokeWidth);
        typedArray.recycle();
        init();
    }

    private void init() {
        mBounds = new Rect();
        mRectF=new RectF();

    }

    private Paint initPaint(int color, int strokeWidth) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setDither(true);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeWidth);
        return paint;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST) {
//            widthSize=SizeUtils.dip2px()
        }
        if (heightMode == MeasureSpec.AT_MOST) {

        }
        /*保证是正方形*/
        setMeasuredDimension(widthSize > heightSize ? heightSize : widthSize,
                widthSize > heightSize ? heightSize : widthSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*1. drawNormal*/
        int randius=getHeight()>getWidth()?getWidth()/2:getHeight()/2;
        canvas.drawCircle(getWidth()/2,getHeight()/2,randius-mStokeWidth/2,mNormalPaint);
        /*2. drawColor*/
        mRectF.set(mStokeWidth/2,mStokeWidth/2,getWidth()-mStokeWidth/2,getHeight()-mStokeWidth/2);
        float sweepAngle = (float)progress/100;
        canvas.drawArc(mRectF, 0, 360 * sweepAngle, false, mSchedulePaint);
        /*3. drawTxt*/
        String mStrCurrentStep = progress + "%";
        mTxtPaint.getTextBounds(mStrCurrentStep, 0, mStrCurrentStep.length(), mBounds);
        /*文字是在是中央，所以计算公式如下*/
        int dx = getWidth() / 2 - mBounds.width() / 2;
        Paint.FontMetrics mFontMetrics=mTxtPaint.getFontMetrics();
        int dy = (int) ((mFontMetrics.bottom - mFontMetrics.top) / 2 - mFontMetrics.bottom);
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(mStrCurrentStep, dx, baseLine, mTxtPaint);

    }

    public synchronized void setProgress(int progress) {
        this.progress = progress;
        // 不断绘制  onDraw()
        invalidate();
    }
}
