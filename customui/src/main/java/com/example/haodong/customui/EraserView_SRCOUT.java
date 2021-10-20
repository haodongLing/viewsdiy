package com.example.haodong.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/10/20
 * Description:
 */
public class EraserView_SRCOUT extends View {
    private Paint mBitPaint;
    private Bitmap bmpDST,bmpSRC;
    private Path mPath;
    private float mPreX,mPreY;
    public EraserView_SRCOUT(Context context) {
        this(context,null);
    }

    public EraserView_SRCOUT(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public EraserView_SRCOUT(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        mBitPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mBitPaint.setColor(Color.RED);
        mBitPaint.setStyle(Paint.Style.STROKE);
        mBitPaint.setStrokeWidth(45);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize=2;
        bmpSRC=BitmapFactory.decodeResource(getResources(),R.drawable.ios_copy);
        bmpDST=Bitmap.createBitmap(bmpSRC.getWidth(),bmpSRC.getHeight(),Bitmap.Config.ARGB_8888);
        mPath=new Path();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(),event.getY());
                mPreX=event.getX();
                mPreY=event.getY();
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX=(mPreX+event.getX())/2;
                float endY=(mPreY+event.getY())/2;
                mPath.quadTo(mPreX,mPreY,endX,endY);
                mPreX=event.getX();
                mPreY=event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return super.onTouchEvent(event);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int layerId=canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);
        // 将路径画出来
        Canvas c=new Canvas(bmpDST);
        c.drawPath(mPath,mBitPaint);
        // 将有路径的bmp画到画布上
        canvas.drawBitmap(bmpDST,0,0,mBitPaint);
        mBitPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(bmpSRC,0,0,mBitPaint);
        mBitPaint.setXfermode(null);
        canvas.restoreToCount(layerId);

    }
}
