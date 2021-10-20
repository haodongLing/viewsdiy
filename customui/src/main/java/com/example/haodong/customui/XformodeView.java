package com.example.haodong.customui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/5/31
 * Description:
 */
public class XformodeView extends View {
    private int width = 400;
    private int height = 400;
    private Bitmap dstMap;
    private Bitmap srcMap;
    private Paint mPaint;

    public XformodeView(Context context) {
        this(context, null);
    }

    public XformodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XformodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        srcMap = makeSrc(width, height);
        dstMap = makeDst(width, height);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);

    }

    public static Bitmap makeSrc(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(0xffffcc44);
        c.drawRect(new RectF(0, 0, w, h), paint);
        return bm;
    }

    public static Bitmap makeDst(int w, int h) {
        Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(0xff66aff);
        c.drawOval(0, 0, w, h, p);
        return bm;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.GREEN);
        int layerID = canvas.saveLayer(0, 0, width * 2, height * 2, mPaint, Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(dstMap, 0, 0, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcMap, width / 2, height / 2, mPaint);
        mPaint.setXfermode(null);
        canvas.restoreToCount(layerID);
    }
}
