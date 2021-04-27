package haodong.com.views_diy.prac_point;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import androidx.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import haodong.com.views_diy.prac_point.DiyPoint;
import haodong.com.views_diy.prac_point.PointEvaluator;

/**
 * @auther linghaoDo QQ:1052354999
 * Created on 2018/10/14
 * @Describu:
 */
public class MyView extends View {
    public static final float RANDIUS = 70f;
    private DiyPoint currentPoint;
    private Paint mPaint;

    public MyView(Context context) {
        super(context);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLUE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heigthSize = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(80, 80);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(80, heigthSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, 80);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint == null) {
            currentPoint = new DiyPoint(RANDIUS, RANDIUS);
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RANDIUS, mPaint);

            DiyPoint startPoint = new DiyPoint(RANDIUS, RANDIUS);
            DiyPoint endPoint=new DiyPoint(700,1000);
            ValueAnimator valueAnimator=ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
            valueAnimator.setDuration(3000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentPoint= (DiyPoint) animation.getAnimatedValue();
                    invalidate();
                }
            });
            valueAnimator.start();

        }else {
            float x=currentPoint.getX();
            float y=currentPoint.getY();
            canvas.drawCircle(x,y,RANDIUS,mPaint);
        }
    }
}
