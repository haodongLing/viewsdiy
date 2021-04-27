package com.example.haodong.viewday1.mynested;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.example.haodong.common.util.LogUtil;

/**
 * created by linghaoDo on 2019-11-26
 * description:
 * <p>
 * version:
 */
public class MyNestedScrollParentView extends HorizontalScrollView {
    private View mHeaderView;
    private int mHeaderwidth;

    public MyNestedScrollParentView(@NonNull Context context) {
        super(context);
    }

    public MyNestedScrollParentView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollParentView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    float preX;
    float preY;
    @Override

    public boolean onInterceptTouchEvent(MotionEvent e) {
        LogUtil.i("onInterceptTouchEvent");
        float currentY;
        float currentX;
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            preX = e.getX();
            preY = e.getY();
        } else if (e.getAction() == MotionEvent.ACTION_MOVE) {
            currentX = e.getX();
            currentY = e.getY();
            if (Math.abs(currentX - preX) > Math.abs(currentY - preY)) {
                LogUtil.i("Math.abs(currentX - preX) >Math.abs(currentY - preY)-->" +
                        String.valueOf(Math.abs(currentX - preX) > Math.abs(currentY - preY)));
                return true;
            }else {
                return false;
            }
        } else if (e.getAction() == MotionEvent.ACTION_UP) {
            preX = 0;
            preY = 0;
            currentX = 0;
            currentY = 0;
        }else if (e.getAction()==MotionEvent.ACTION_CANCEL){
            LogUtil.i("MotionEvent.ACTION_CANCEL");
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction()==MotionEvent.ACTION_MOVE){
            LogUtil.i("MotionEvent.ACTION_MOVE");
        }
        return super.dispatchTouchEvent(ev);
    }

}
