package com.example.haodong.viewday1.day24;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.haodong.viewday1.SizeUtils;

/**
 * package_name: com.example.haodong.viewday1.day24
 * description: 1. 不动圆的半径是变化的。有一个初始大小，而且小到一定大小后回消失
 * author: linghaoDo
 * date: 2019/2/22
 */
public class MessageBubbleView extends View {
    /*两个圆的圆心*/
    private PointF mFixactionPoint, mDragPoint;
    /*圆的半径*/
    private int mDragCircleRandius;
    private int mFixedCircleRandius;
    private int mFixationRadiusMin;
    /*画圆的画笔*/
    private Paint mDragPaint, mFixedPaint;


    public MessageBubbleView(Context context) {
        this(context, null);
    }

    public MessageBubbleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MessageBubbleView(Context context, AttributeSet attrs,
                             int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mFixedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mFixedPaint.setStyle(Paint.Style.FILL);
        mFixedPaint.setColor(Color.RED);
        /*防抖动*/
        mFixedPaint.setDither(true);
        mDragCircleRandius = SizeUtils.dip2px(getContext(), 10);
        mFixedCircleRandius = SizeUtils.dip2px(getContext(), 10);
        mFixationRadiusMin = SizeUtils.dip2px(getContext(), 3);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                /*手指按下 指定当前的位置*/
                float downX = event.getX();
                float downY = event.getY();
                initPoint(downX, downY);
                break;
            case MotionEvent.ACTION_MOVE:
                float currentX = event.getX();
                float currentY = event.getY();
                /*计算两个点的距离*/
                updateDragPoint(currentX, currentY);
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        invalidate();
        return true;
    }

    /**
     * 更新拖拽点的位置
     *
     * @param currentX
     * @param currentY
     */
    private void updateDragPoint(float currentX, float currentY) {
        mDragPoint.x = currentX;
        mDragPoint.y = currentY;
    }

    /**
     * 初始化位置
     *
     * @param downX
     * @param downY
     */
    private void initPoint(float downX, float downY) {
        mFixactionPoint = new PointF(downX, downY);
        mDragPoint = new PointF(downX, downY);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDragPoint == null || mFixactionPoint == null) {
            return;
        }
        /*画两个圆*/
        // 画拖拽圆
        canvas.drawCircle(mDragPoint.x, mDragPoint.y, mDragCircleRandius, mDragPaint);
        double distance = getDistance(mDragPoint, mFixactionPoint);
        mFixedCircleRandius = (int) (mFixedCircleRandius - distance / 14);

    }

    /**
     * 获取两个圆之间的距离
     *
     * @param point1
     * @param point2
     * @return
     */
    private double getDistance(PointF point1, PointF point2) {
        return Math.sqrt((point1.x - point2.x) * (point1.x - point2.x) + (point1.y - point2.y) *
                (point1.y - point2.y));
    }

}
