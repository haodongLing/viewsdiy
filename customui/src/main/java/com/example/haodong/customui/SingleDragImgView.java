package com.example.haodong.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/6/18
 * Description:
 */
public class SingleDragImgView extends androidx.appcompat.widget.AppCompatImageView {
    private int mLeft, mTop;
    private float mStartX, mStartY;

    public SingleDragImgView(Context context) {
        this(context, null);
    }

    public SingleDragImgView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleDragImgView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mStartX = event.getX();
                mStartY = event.getY();
                mLeft = getLeft();
                mTop = getTop();
                break;
            case MotionEvent.ACTION_MOVE:
                mLeft = (int) (mLeft + event.getX() - mStartX);
                mTop = (int) (mTop + event.getY() - mStartY);
                layout(mLeft, mTop, mLeft + getWidth(), mTop + getHeight());

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
