package haodong.com.views_diy;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import java.util.Collection;


/**
 * @auther linghaoDo QQ:1052354999
 * Created on 2018/10/12
 * @Describu:
 */
public class CircleView extends View {
    private Paint mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mPaint1=new Paint(Paint.ANTI_ALIAS_FLAG);
    //矩阵的坐标点
    private RectF rectF;
    //屏幕高度
    private int width=0;
    private int circleRadius=0;
    private int circleStartY=20;
    private int circleEndY=0;
    /** 初始进度 */
    private static float currentPorcent = 0;
    /** 进度是多少 */
    private static  float maxPorcent = 0;

    /**满级回调*/
//    public RestoreCirclr rc;
    /** 是否还原 */
    public boolean isRestore = false;


    /**满级回调*/
    public RestoreCircle rc;

    private static Handler handler=new Handler();

    private Runnable drawRunnable=new Runnable() {
        @Override
        public void run() {
            if (!isRestore){
                if (currentPorcent>maxPorcent){
                    currentPorcent=maxPorcent;
                    invalidate();
                    handler.removeCallbacks(drawRunnable);
                } else {
                    currentPorcent += 5;//这里是动画速度，当前为5。可自己去调试经验值增长速度
                    handler.postDelayed(drawRunnable, (long) (1300 / maxPorcent));
                    invalidate();
                }
                if (currentPorcent == 360) {
                    if (rc != null) {
                        isRestore = rc.OnRestoreCircle();
                        handler.postDelayed(drawRunnable, 0);
                    }
                }
            } else {//满级之后经验条动画返回0进度
                if (currentPorcent <= 0) {
                    currentPorcent = 0;
                    invalidate();
                    handler.removeCallbacks(drawRunnable);
                } else {
                    currentPorcent -= 3;//这里是动画速度，当前为3。可自己去调试经验值反0速度
                    handler.postDelayed(drawRunnable, (long) (1300 / maxPorcent));
                    invalidate();
                }
            }
        }
    };


    public CircleView(Context context) {
        super(context);
        init(context);
    }


    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        mPaint.setColor(Color.GRAY);
        //圆心掏空
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(dip2px(context, 10));

        mPaint1.setColor(Color.BLUE);
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setStrokeWidth(dip2px(context, 10));
        mPaint1.setStrokeCap(Paint.Cap.ROUND);
        width = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        circleRadius = width / 4;
        circleEndY = circleStartY + circleRadius * 2;

        rectF = new RectF(width / 2 - circleRadius, circleStartY, width / 2 + circleRadius, circleEndY);// 弧形

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSize=MeasureSpec.getSize(widthMeasureSpec);

        int heiSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSize=MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode==MeasureSpec.AT_MOST&&heightMeasureSpec==MeasureSpec.AT_MOST){
            setMeasuredDimension(400,400);
        }else if (widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,heightSize);
        }else if (heightMeasureSpec==MeasureSpec.AT_MOST){
            setMeasuredDimension(width,400);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        final  int paddingLeft=getPaddingLeft();
        final int paddingRight=getPaddingRight();
        final int paddingTop=getPaddingTop();
        final int paddingBottom=getPaddingBottom();

        int width=getWidth()-paddingLeft-paddingRight;
        int height=getHeight()-paddingTop-paddingBottom;
        int randius=Math.min(width,height)/2;


    }
    private void invalidateView(){
        handler.postDelayed(drawRunnable,0);

    }
    public boolean isRestore() {
        return isRestore;
    }
    public void setRestore(boolean isRestore) {
        this.isRestore = isRestore;
    }
    /** 设置等级进度，传入升级经验，以及当前经验  maxPorcent就是当前经验在升级经验占的百分比*/
    public void setCirclePlan(int max, int current) {
        maxPorcent = (int) (((float)360 / (float)max) * current);
    }

    /** 设置园线的颜色 */
    public void setCircleColor(int color) {
        mPaint.setColor(color);
    }
    /** 设置进度线的颜色 */
    public void setCirclePlanColor(int color) {
        mPaint1.setColor(color);
    }
    public void setRc(RestoreCircle rc) {
        this.rc = rc;
    }


    /**传入dp，返回px*/
    public float dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (float) (dpValue * scale + 0.5f);
    }
    public interface RestoreCircle{
        public boolean OnRestoreCircle();
    }
}
