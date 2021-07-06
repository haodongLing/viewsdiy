package com.example.haodong.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/6/20
 * Description:
 */
public class TouchScaleTextView extends TextView {
    private int mode = 0;
    private float mOldDist;
    private float mTextSize;

    public TouchScaleTextView(Context context) {
        this(context, null);
    }

    public TouchScaleTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchScaleTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mTextSize == 0) {
            mTextSize = getTextSize();
        }
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                mOldDist = 0;
                mode = 1;
                break;
            case MotionEvent.ACTION_UP:
                mode = 0;
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                mOldDist = spacing(event);
                mode += 1;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mode -= 1;
                break;

            case MotionEvent.ACTION_MOVE:
                if (mode >= 2) {
                    float newDist = spacing(event);
                    if (Math.abs(newDist - mOldDist) > 50) {
                        zoom(newDist / mOldDist);
                        mOldDist = newDist;
                    }
                }
                break;
        }

        return true;
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void zoom(float f) {
        mTextSize *= f;
        setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
    }
}
