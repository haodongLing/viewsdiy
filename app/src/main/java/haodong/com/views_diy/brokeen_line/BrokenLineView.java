package haodong.com.views_diy.brokeen_line;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import haodong.com.views_diy.R;

/**
 * @author linghailong
 * @date on 2018/12/1
 * @email 105354999@qq.com
 * @describe :先测试死数据，然后再去考虑数据的渲染
 */
public class BrokenLineView extends View {
    private static final int  MAX_COUNT=6;

    /*像素，默认使用苹果*/
    private static final int UI_WIDTH=750;
    private static final int UI_HEIGHT=1334;

    private static final float MAX_POINT_X=1.0f;
    private static final float MIN_POINT_Y=0f;

    private int  mHighTempColor= Color.parseColor("#F3551C");
    private int  mLowTempColor= Color.parseColor("#1CB3F3");

    private int mDefaultTempColor;
    /*填充色*/
    private int mDefaultShader1;
    private int mDefaultShader2;

    private static final int MAX_C=10;

    private float mGap_X;
    private float MAX_LINE_LENGTH=100;

    private float mContentLineWidth = 2;
    private float mMarginLeft=40;
    private float mMarginRight=40;
    private float mMarginTop = 30, mMarginButtom = 50;
    private float mTextMarginTop = 30;
    /*坐标原点大小*/
    private float mCircleSize = 7;

    private float mTextSize = 24;
    private int mTextColor = Color.WHITE;

    private int mWidth;
    private int mHeight;

    private int mMaxC;
    private int mMinC;
    private int mGapC;

    /*数据集，暂时使用List<Integer>作测试*/
    List<Integer> mList=new ArrayList<>();


    /*创建画笔*/
    private Paint mPaint;
    /*创建Path*/
    private Path mPath;
    /*创建TextPaint*/
    private TextPaint mTextPaint;
    private float mStartX;

    public BrokenLineView(Context context) {
       this(context,null);
    }

    public BrokenLineView(Context context, @Nullable AttributeSet attrs) {
       this(context,attrs,0);
    }

    public BrokenLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mDefaultTempColor=mHighTempColor;
        mDefaultShader1 = Color.argb(63, 255, 172, 123);
        mDefaultShader2 = Color.TRANSPARENT;

        if(attrs!=null){
            TypedArray typedArray=context.obtainStyledAttributes(attrs, R.styleable.BrokenLineView);
            mDefaultTempColor=typedArray.getColor(R.styleable.BrokenLineView_MyColor,mHighTempColor);
            if (mDefaultTempColor!= mHighTempColor){
                mDefaultShader1 = Color.argb(45, 54, 187, 243);
                mDefaultShader2 = Color.TRANSPARENT;
            }
            /*回收*/
            typedArray.recycle();
        }
        init();
    }

    /**
     * 实例化
     */
    private void init() {
        /*获取屏幕像素*/
        WindowManager manager= (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display deafultDisplay=manager.getDefaultDisplay();
        DisplayMetrics metrics=new DisplayMetrics();
        deafultDisplay.getMetrics(metrics);

        /*获取像素宽度、高度*/
        int widthPixels=metrics.widthPixels;
        int heightPixels=metrics.heightPixels;

        /*根据像素计算缩放比例*/
        float scaleX=widthPixels*1.0f/UI_WIDTH;
        float scaleY=heightPixels*1.0f/UI_HEIGHT;

        /*动态缩放*/
        mContentLineWidth*=scaleX;
        mMarginLeft*=scaleX;
        mMarginRight*=scaleX;
        mMarginTop*=scaleY;
        mMarginButtom*=scaleY;

        MAX_LINE_LENGTH*=scaleX;

        mCircleSize*=scaleX;

        mTextSize*=scaleX;

        mTextMarginTop*=scaleY;

        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mDefaultTempColor);

        /*实例化TextPaint*/
        mTextPaint=new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mPath=new Path();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    /**
     * 获取宽高
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth=w;
        mHeight=h;
        mGap_X=(mWidth-mMarginLeft-mMarginRight)/(MAX_COUNT-1);
        MAX_LINE_LENGTH=mHeight-mMarginButtom-mMarginTop-mTextMarginTop;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawShadow(canvas);

        drawPoint(canvas);

        drawLine(canvas);

        drawText(canvas);
    }

    /**
     * 绘制文字
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        if (mList == null || mList.size() != MAX_COUNT) {
            throw new IllegalStateException("数据填充错误，请检查！");
        }
        int size=mList.size();
        for (int i = 0; i < size; i++) {
            int newValue = mList.get(i);
            float x = findX(i);
            float y = findY(newValue) - mTextMarginTop;
            canvas.drawText(newValue + "°", x, y, mTextPaint);
        }

    }

    /**
     * 画线
     * @param canvas
     */
    private void drawLine(Canvas canvas) {
        if (mList == null || mList.size() != MAX_COUNT) {
            throw new IllegalStateException("获取的数据不正常");

        }
        for (int i = 1; i < mList.size(); i++) {
            int oldValue = mList.get(i - 1);
            int newValue = mList.get(i);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(findX(i - 1), findY(oldValue), findX(i), findY(newValue), mPaint);
        }

    }

    /**
     * 描点
     * @param canvas
     */
    private void drawPoint(Canvas canvas) {
        int size=mList.size();
        for(int i=0;i<size;i++){
            int value =mList.get(i);
            float x=findX(i);
            float y=findY(value);
            mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(x,y,mCircleSize,mPaint);

        }

    }

    /**
     * 画填充阴影
     * @param canvas
     */
    private void drawShadow(Canvas canvas) {
        if (mList == null || mList.size() != MAX_COUNT) {
            throw new IllegalStateException("数据填充错误，请检查！");
        }
        int size=mList.size();
        for (int i = 0; i < size; i++) {
            int newValue = mList.get(i);
            if (i == 0){
                mPath.moveTo(findX(i), findY(newValue));
            }else {
                mPath.lineTo(findX(i), findY(newValue));
            }
        }
        mPath.lineTo(findX(5), mHeight);
        mPath.lineTo(mMarginLeft, mHeight);
        mPath.close();

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setShader(new LinearGradient(0,  finMaxY(), 0, mHeight, mDefaultShader1, mDefaultShader2, Shader.TileMode.MIRROR));
        canvas.drawPath(mPath, mPaint);
        mPaint.setShader(null);
    }
    private float finMaxY() {

        for (int i = 0; i < mList.size(); i++) {
            int value = mList.get(i);
            if (value == mMaxC){
                return findY(value);
            }
        }
        return 0;
    }

    /**
     * 数据填充
     * @param list
     */
    public void setData(List<Integer> list){
        if (list==null||list.size()!=MAX_COUNT){
            throw new IllegalStateException("数据不正常");
        }
        mList.clear();
        mMaxC=mGapC=0;
        mMinC= Integer.MAX_VALUE;
        mList.addAll(list);
        findC();
    }

    /**
     *
     */
    private void findC() {
        for(Integer integer:mList){
            mMaxC=integer>mMaxC?integer:mMaxC;
            mMinC=integer<mMinC?integer:mMinC;
        }
        mGapC=mMaxC-mMinC;

    }

    /**
     * PointX
     * @param index
     * @return
     */
    private float findX(int index){
        return mGap_X*index+mMarginLeft;
    }

    /**
     * PointY
     * @param value
     * @return
     */
    private float findY(int value){

        float height = 0;
        if (mGapC < MAX_C){
            height = mGapC * 1.0f / MAX_C * MAX_LINE_LENGTH;
        }else {
            height = MAX_LINE_LENGTH;
        }
        float buttom = mHeight - mMarginButtom;
        return buttom - (value - mMinC) * 1.0f / mGapC * height ;
    }
}
