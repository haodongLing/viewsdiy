package com.example.haodong.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/10/19
 * Description:
 */
public class AovidXformodeView  extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    public AovidXformodeView(Context context) {
        this(context,null);
    }

    public AovidXformodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AovidXformodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE,null);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.RED);
        mBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.ios_copy);



    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth()/2;
        int height=width*mBitmap.getHeight()/mBitmap.getWidth();
        int layerId=canvas.saveLayer(0,0,getWidth(),getHeight(),null,Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mBitmap,null,new Rect(0,0,width,height),mPaint);

    }
}
