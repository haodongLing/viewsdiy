package com.haodong.mycavas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author linghailong
 * @date on 2018/12/1
 * @email 105354999@qq.com
 * @describe :
 */
public class PathView extends View {
    private Paint mPaint;
    private Path mPath;
    public PathView(Context context) {
        super(context);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public PathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(12);
        mPath=new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width=getWidth();
        int heignt=getHeight();
        /*path.lineTo练习**/
//        mPath.addRect(200, 100, 500, 300, Path.Direction.CW);
//        canvas.drawPath(mPath, mPaint);
//        mPath.reset();
//        mPath.addRect(200, 400, 500, 600, Path.Direction.CCW);
//        canvas.drawPath(mPath, mPaint);
//
//        mPath.lineTo(width/2,heignt/2);
//        mPath.lineTo(200,200);
//        mPath.lineTo(200,0);
//        canvas.drawPath(mPath,mPaint);
        /**path.setLastPoint练习**/
        mPath.lineTo(200,200);
        mPath.setLastPoint(200,100);
        mPath.lineTo(200,0);
        canvas.drawPath(mPath,mPaint);


    }
}
