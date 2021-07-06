package com.example.haodong.customui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/6/15
 * Description:
 */
public class CustomRoundLayout extends LinearLayout {
    private Path mPath;
    private String mBgColor;

    public CustomRoundLayout(Context context) {
        this(context, null);
    }

    public CustomRoundLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomRoundLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPath = new Path();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        mPath.reset();
        mPath.addRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), 50, 50, Path.Direction.CW);
        canvas.save();
        canvas.clipPath(mPath);
        canvas.drawColor(Color.GREEN);
        if (!TextUtils.isEmpty(mBgColor)) {
            canvas.drawColor(Color.parseColor(mBgColor));
        }

        super.dispatchDraw(canvas);

        canvas.restore();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (TextUtils.isEmpty(mBgColor)) {
            Drawable bgDrawable = getBackground();
            if (bgDrawable instanceof ColorDrawable) {
                ColorDrawable colorDrawable = (ColorDrawable) bgDrawable;
                int color = colorDrawable.getColor();
                mBgColor = "#" + String.format("%08x", color);
            }
            setBackgroundColor(Color.parseColor("#00FFFFFF"));
        }
        super.onDraw(canvas);

    }
}
