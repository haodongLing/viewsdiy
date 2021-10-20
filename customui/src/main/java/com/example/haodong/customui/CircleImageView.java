package com.example.haodong.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * Author: tangyuan
 * Time : 2021/10/19
 * Description:
 */
public class CircleImageView  extends AppCompatImageView {
    private Paint mdistPaint;
    private Paint mSrcPaint;
    private Bitmap photo;
    private Bitmap distBitmap;
    private Paint mPaint;
    public CircleImageView(Context context) {
        this(context,null);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        TypedArray typedArray=context.obtainStyledAttributes(attrs, R);
        mdistPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        photo= BitmapFactory.decodeResource(getResources(),R.drawable.ios_copy);
        distBitmap=getDist(photo.getWidth(),photo.getHeight());
    }
    public Bitmap getDist(int width,int height){
        Bitmap bitmap=Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        canvas.drawCircle(width/2,height/2, Math.min(width/2,height/2),mdistPaint);
        return bitmap;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int layerid=canvas.saveLayer(0,0,photo.getWidth(),photo.getHeight(),mdistPaint);
     canvas.drawBitmap(distBitmap,0,0,mPaint);
     mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
     canvas.drawBitmap(photo,0,0,mPaint);
     mPaint.setXfermode(null);
     canvas.restoreToCount(layerid);
    }
}
