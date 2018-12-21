package com.haodong.mycavas.chapter01;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.haodong.mycavas.R;

/**
 * @author linghailong
 * @date on 2018/12/19
 * @email 105354999@qq.com
 * @describe :
 */
public class CustomCircleView extends View {
    private Bitmap mBitmap;
    private Paint mPaint;
    private Path mPath;
    public CustomCircleView(Context context) {
        this(context,null);
    }

    public CustomCircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CustomCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mBitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.avator);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPath=new Path();
        int width=mBitmap.getWidth();
        int height=mBitmap.getHeight();
        mPath.addCircle(width/2,height/2,width/2,Path.Direction.CCW);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.clipPath(mPath);
        canvas.drawBitmap(mBitmap,0,0,mPaint);
        canvas.restore();
       
    }
}
