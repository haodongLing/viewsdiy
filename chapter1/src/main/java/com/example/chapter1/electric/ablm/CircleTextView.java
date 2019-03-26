package com.example.chapter1.electric.ablm;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.view.Gravity;

import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.tools.SizeUtils;

/**
 * description:
 * author: linghailong
 * date: 2019/3/20
 */
public class CircleTextView extends AppCompatTextView {
    private Paint strokePaint;
    private Paint backPaint;
    private Paint textPaint;
    private int strokeColor = Color.WHITE;
    private int backColor = Color.TRANSPARENT;
    private float storkWidth;

    public CircleTextView(Context context) {
        this(context, null);
    }

    public CircleTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleTextView);
        strokeColor = typedArray.getColor(R.styleable.CircleTextView_strokeColor, Color
                .parseColor("#e0ffffff"));
        backColor = typedArray.getColor(R.styleable.CircleTextView_backColor, Color
                .parseColor("#30000000"));
        typedArray.recycle();
        /*text*/
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(getTextSize());
        /*圆形边线*/
        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setColor(strokeColor);
        storkWidth = SizeUtils.dp2px(1, getContext());
        strokePaint.setStrokeWidth(storkWidth);
        strokePaint.setStyle(Paint.Style.STROKE);

        /*背景画笔*/
        backPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        backPaint.setColor(backColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setGravity(Gravity.CENTER);
        int height = getHeight();
        int width = getWidth();
        int radius;
        int storkRadius;
        int textWidth = (int) textPaint.measureText(getText().toString());
        if (width > height) {
            if (height > textWidth) {
                radius = height;
            } else {
                setHeight(textWidth + getPaddingTop() + getPaddingBottom());
                radius = textWidth;
            }
        } else {
            if (width > textWidth) {
                radius = width;
            } else {
                setWidth(textWidth + getPaddingRight() + getPaddingLeft());
                radius = textWidth;
            }
        }
        storkRadius = (int) (radius / 2 - storkWidth / 2);
        radius = (int) (storkRadius - storkWidth / 2);
        if (storkWidth != 0)
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, storkRadius, strokePaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, radius, backPaint);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        canvas.drawText(getText().toString(), getWidth() / 2 - textPaint.measureText(getText()
                .toString()) / 2, getHeight() / 2 - fontMetrics.descent + (fontMetrics.bottom -
                fontMetrics.top) / 2, textPaint);
    }

    public void setCircleStorkColor(@ColorInt int color) {
        this.strokeColor = color;
        strokePaint.setColor(strokeColor);
        invalidate();
    }

    public void setCircleBackColor(@ColorInt int color) {
        this.backColor = color;
        backPaint.setColor(backColor);
        invalidate();
    }

    public void setCircleTextColor(@ColorInt int color) {
        textPaint.setColor(color);
        invalidate();
    }
}
