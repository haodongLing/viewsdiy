package com.example.haodong.customui.day24;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.haodong.customui.SizeUtils;

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
    private int mDragCircleRandius = 10;
    private int mFixedCircleRandius;
    private int mFixationRadiusMax = 7;
    private int mFixationRadiusMin = 3;
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

        mDragPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDragPaint.setStyle(Paint.Style.FILL);
        mDragPaint.setColor(Color.RED);
        /*防抖动*/
        mDragPaint.setDither(true);
        /*实例化需要放在这里进行*/
        mDragCircleRandius = SizeUtils.dip2px(getContext(), 10);
        mFixationRadiusMax = SizeUtils.dip2px(getContext(), 7);
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

        Path bezeierPath = getBezeierPath();
        if (bezeierPath != null) {
            canvas.drawCircle(mFixactionPoint.x, mFixactionPoint.y, mFixedCircleRandius,
                    mFixedPaint);
            /*画bezeier曲线*/
            canvas.drawPath(bezeierPath, mDragPaint);

        }

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

    /**
     * 获取贝塞尔路径
     *
     * @return
     */
    public Path getBezeierPath() {
        double distance = getDistance(mDragPoint, mFixactionPoint);

        mFixedCircleRandius = (int) (mFixationRadiusMax - distance / 14);
        if (mFixedCircleRandius < mFixationRadiusMin) {
            /*超过一定的距离 贝塞尔和固定的圆都不用画了*/
            return null;
        }
        Path bezeierPath = new Path();
        /*求∠a*/
        /*求斜率*/
        float dy = (mDragPoint.y - mFixactionPoint.y);
        float dx = (mDragPoint.x - mFixactionPoint.x);
        float tanA = dy / dx;

        // 求角a
        double arcTanA = Math.atan(tanA);
        // p0
        float p0x = (float) (mFixactionPoint.x + mFixedCircleRandius * Math.sin(arcTanA));
        float p0y = (float) (mFixactionPoint.y - mFixedCircleRandius * Math.cos(arcTanA));

        // p1
        float p1x = (float) (mDragPoint.x + mDragCircleRandius * Math.sin(arcTanA));
        float p1y = (float) (mDragPoint.y - mDragCircleRandius * Math.cos(arcTanA));

        // p2
        float p2x = (float) (mDragPoint.x - mDragCircleRandius * Math.sin(arcTanA));
        float p2y = (float) (mDragPoint.y + mDragCircleRandius * Math.cos(arcTanA));

        // p3
        float p3x = (float) (mFixactionPoint.x - mFixedCircleRandius * Math.sin(arcTanA));
        float p3y = (float) (mFixactionPoint.y + mFixedCircleRandius * Math.cos(arcTanA));
        //
        bezeierPath.moveTo(p0x,p0y);
        PointF controlPoint=getControlPoint();
        bezeierPath.quadTo(controlPoint.x,controlPoint.y,p1x,p1y);

        /*画第二条*/
        bezeierPath.lineTo(p2x,p2y);
        bezeierPath.quadTo(controlPoint.x,controlPoint.y,p3x,p3y);
        bezeierPath.close();
        return null;
    }
    public PointF getControlPoint() {
        /*注意，DragPoint是变化的*/
        return new PointF((mDragPoint.x+mFixactionPoint.x)/2,(mDragPoint.y+mFixactionPoint.y)/2);
    }

}
