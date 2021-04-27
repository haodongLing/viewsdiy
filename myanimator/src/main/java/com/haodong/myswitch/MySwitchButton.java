package com.haodong.myswitch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import androidx.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @author linghailong
 * @date on 2018/12/21
 * @email 105354999@qq.com
 * @describe :
 */
public class MySwitchButton extends View {
    private final static String TAG = "MySwitchButton";
    /*像素，默认使用苹果*/
    private static final int UI_WIDTH = 750;
    private static final int UI_HEIGHT = 1334;
    private Paint mBottomPaint;
    private Paint mLeftPaint;

    /*顶部白色滑块*/
    private Paint mTopPaint;
    /*提示字体及验证完成的画笔*/
    private Paint mTxtPaint;
    private String txtHint = "拖动验证滑动";
    private String txtCompleted = "验证成功";

    // 滑块的宽度，设置为view宽的 1/5
    private float widthSlide = 0;
    private float viewWidth; // 窗体宽度
    private float viewHeight;//窗体高度
    private float mTextHintWidth;// 提示字体的宽度
    private float mTextCompletedWidth;// 验证完成的宽度
    // 当前手指所在位置
    private float currentX = 0;
    // 是否正在滑动
    private boolean isSliding = false;
    // 是否完成
    private boolean iscompleted = false;

    private ScrollCompletedListener mScrollCompletedListener;


    public MySwitchButton(Context context) {
        this(context, null);
    }

    public MySwitchButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        /*实例化底部背景的Paint*/
        mBottomPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBottomPaint.setColor(Color.GRAY);
        mBottomPaint.setStyle(Paint.Style.FILL);
        /*实例化白色滑块的Paint*/
        mTopPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTopPaint.setStyle(Paint.Style.FILL);
        mTopPaint.setColor(Color.WHITE);
        /*实例化绿色滑块的Paint*/
        mLeftPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLeftPaint.setStyle(Paint.Style.FILL);
        mLeftPaint.setColor(Color.GREEN);
        /*实例化字体的paint*/
        mTxtPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setColor(Color.BLACK);
        mTxtPaint.setTextSize(45);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);

        int heiSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int heiSpecSize=MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode==MeasureSpec.AT_MOST&&heiSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(300,50);
        }else if (widthMeasureSpec==MeasureSpec.AT_MOST){
            setMeasuredDimension(300,heiSpecSize);
        }else if (heightMeasureSpec==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,50);
        }
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = getWidth();
        viewHeight = getHeight();
        widthSlide = viewWidth / 5;
        /*提示文字的宽度*/
        mTextHintWidth = mTxtPaint.measureText(txtHint);
        mTextCompletedWidth = mTxtPaint.measureText(txtCompleted);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*画背景*/
        drawBackground(canvas);
        /*画拉条*/
        drawBrace(canvas);
        /*画左边的绿色*/
        drawLeft(canvas);
    }

    private void drawLeft(Canvas canvas) {
        canvas.drawRect(0, 0, currentX, viewHeight, mLeftPaint);
         /*当滑块滑动到右侧时，画出滑块上的绿色圆环及验证成功字样
        widthSlide：白色滑块的宽度
        */
        if (isSliding) {
            canvas.drawCircle((currentX + widthSlide / 2), viewHeight / 2,
                    widthSlide / 4, mLeftPaint);
            mTxtPaint.setColor(Color.WHITE);
            mTxtPaint.setTextSize(50);
            canvas.drawText(txtCompleted, (viewWidth / 2 - mTextCompletedWidth / 2),
                    viewHeight / 2 + 15, mTxtPaint);
            iscompleted = true;
            // 设置滑动完成监听
            if (mScrollCompletedListener != null) {
                mScrollCompletedListener.scrollCompleted();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                currentX=0;
                isSliding=false;
                postInvalidate();
            }

        }
    }

    private void drawBrace(Canvas canvas) {
        // 根据滑块的坐标位置，画出白色滑块，滑块的坐标根据手触摸到屏幕上坐标位置计算
        canvas.drawRect(currentX, 0, (currentX + widthSlide), viewHeight, mTopPaint);

    }

    private void drawBackground(Canvas canvas) {
        canvas.drawRect(0, 0, viewWidth, viewHeight, mBottomPaint);
        // 当滑块右侧位置滑动到提示字体左侧时去除掉（不再画出）提示字体
        if ((currentX + widthSlide) <= (viewWidth / 2 - mTextHintWidth / 2)) {
            canvas.drawText(txtHint, (viewWidth / 2 - mTextHintWidth / 2),
                    viewHeight / 2 + 15, mTxtPaint);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (!iscompleted) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    break;
                case MotionEvent.ACTION_MOVE:
                    currentX = event.getX() < 0 ? 0 : event.getX();
                    if (currentX >= (viewWidth - widthSlide)) {
                        currentX = viewWidth - widthSlide;
                    }
                    invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                    if (currentX < (viewWidth - widthSlide - 10)) {
                        currentX = 0;
                        isSliding = false;
                    } else {
                        currentX = viewWidth - widthSlide;
                        isSliding = true;
                    }
                    invalidate();
                    break;
                default:
                    break;
            }
        }
        return true;
    }

    public void setmScrollCompletedListener(ScrollCompletedListener mScrollCompletedListener) {
        this.mScrollCompletedListener = mScrollCompletedListener;
    }

    public interface ScrollCompletedListener {
        void scrollCompleted();
    }

}
