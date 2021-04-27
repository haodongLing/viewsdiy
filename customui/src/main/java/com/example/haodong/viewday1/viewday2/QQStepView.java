package com.example.haodong.viewday1.viewday2;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.example.haodong.viewday1.R;
import com.example.haodong.viewday1.SizeUtils;

/**
 * duscribe: 1. 分析效果 2. 确定自定义属性attrs 3. 在布局中使用 4. onMeasure() 5. onDraw():两个圆弧以及文字
 * created at 2019/1/25
 * Auther linghailong
 */
public class QQStepView extends View {
    private static final String TAG = QQStepView.class.getSimpleName();
    private Paint mOutPaint, mInnerPaint;
    private TextPaint mTxtPaint;
    private Rect mTxtBounds;
    private int mTxtSize = 15;
    private int mTxtColor = Color.RED;
    private int mInnerColor = Color.RED;
    private int mOuterColor = Color.BLUE;
    private int mBorderWidth = 6;

    private int mStepMax;
    private int mCurrentStep = 0;
    private Paint.FontMetrics mFontMetrics;
    private RectF mRectF;

    public QQStepView(Context context) {
        this(context, null);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QQStepView(Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QQStepView);
        /*此时获取的值并没有进行转化成对应的px*/
        mOuterColor = typedArray.getColor(R.styleable.QQStepView_stepOuterColor, mOuterColor);
        mInnerColor = typedArray.getColor(R.styleable.QQStepView_stepInnerColor, mInnerColor);
        mTxtColor = typedArray.getColor(R.styleable.QQStepView_stepFontColor, mTxtColor);

        mBorderWidth = (int) typedArray.getDimension(R.styleable.QQStepView_stepBorderWidth,
                mBorderWidth);
        mTxtSize = typedArray.getDimensionPixelSize(R.styleable.QQStepView_stepFontSize, mTxtSize);
        typedArray.recycle();
        /*将相关的size进行转化*/
        mTxtSize = SizeUtils.sp2px(context, mTxtSize);
        mBorderWidth = SizeUtils.dip2px(context, mBorderWidth);
        init();
    }

    private void init() {
        /*实例化外圆的paint*/
        mOutPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutPaint.setColor(mOuterColor);
        mOutPaint.setStrokeWidth(mBorderWidth);
        mOutPaint.setStyle(Paint.Style.STROKE);
        mOutPaint.setStrokeCap(Paint.Cap.ROUND);

        /*实力化内圆的paint*/
        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setStrokeWidth(mBorderWidth);
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setStrokeCap(Paint.Cap.ROUND);
        mInnerPaint.setColor(mInnerColor);

        /*实例化文字的txtPaint*/
        mTxtPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setColor(mTxtColor);
        mTxtPaint.setTextSize(mTxtSize);
        mFontMetrics = mTxtPaint.getFontMetrics();
        mTxtBounds = new Rect();
        mRectF=new RectF();

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
       mRectF.set(mBorderWidth / 2, mBorderWidth / 2
               , getWidth() - mBorderWidth / 2, getHeight() - mBorderWidth / 2);
        /*画外圆弧*/
        canvas.drawArc(mRectF, 135, 270, false, mOutPaint);
        /*画内圆弧*/
        /*画的圆弧的角度不能写死*/
        if (mStepMax == 0) return;
        float sweepAngle = (float)mCurrentStep/mStepMax;
        canvas.drawArc(mRectF, 135, 270 * sweepAngle, false, mInnerPaint);
        /*画文字*/
        String mStrCurrentStep = mCurrentStep + "";
        mTxtPaint.getTextBounds(mStrCurrentStep, 0, mStrCurrentStep.length(), mTxtBounds);
        /*文字是在是中央，所以计算公式如下*/
        int dx = getWidth() / 2 - mTxtBounds.width() / 2;
        int dy = (int) ((mFontMetrics.bottom - mFontMetrics.top) / 2 - mFontMetrics.bottom);
        int baseLine = getHeight() / 2 + dy;
        canvas.drawText(mStrCurrentStep, dx, baseLine, mTxtPaint);
    }


    // 7.其他 写几个方法动起来
    public synchronized void setStepMax(int stepMax) {
        this.mStepMax = stepMax;
    }

    public synchronized void setCurrentStep(int currentStep) {
        this.mCurrentStep = currentStep;
        // 不断绘制  onDraw()
        invalidate();
    }


}
