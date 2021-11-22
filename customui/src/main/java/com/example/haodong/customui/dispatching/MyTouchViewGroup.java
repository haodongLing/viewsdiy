package com.example.haodong.customui.dispatching;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.example.haodong.common.util.LogUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/11/21
 * Description:
 */
public class MyTouchViewGroup extends FrameLayout {
    public MyTouchViewGroup(@NonNull Context context) {
        this(context,null);
    }

    public MyTouchViewGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyTouchViewGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtil.i("MyTouchViewGroup  dispatchTouchEvent-->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.i("MyTouchViewGroup  dispatchTouchEvent-->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.i("MyTouchViewGroup  dispatchTouchEvent-->ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtil.i("MyTouchViewGroup  dispatchTouchEvent-->ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtil.i("MyTouchViewGroup  onTouchEvent-->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.i("MyTouchViewGroup  onTouchEvent-->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.i("MyTouchViewGroup  onTouchEvent-->ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtil.i("MyTouchViewGroup  onTouchEvent-->ACTION_CANCEL");
                break;
        }

        return true;
    }
}
