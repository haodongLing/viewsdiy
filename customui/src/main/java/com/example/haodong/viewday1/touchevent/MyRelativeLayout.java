package com.example.haodong.viewday1.touchevent;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.example.haodong.common.util.LogUtil;

/**
 * created by linghaoDo on 2019-09-19
 * <p>
 * description:
 */
public class MyRelativeLayout extends RelativeLayout {
    public MyRelativeLayout(Context context) {
        this(context, null);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.i("dispatchTouchEvent-->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.i("dispatchTouchEvent-->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.i("dispatchTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtil.i("dispatchTouchEvent---ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.i("onInterceptTouchEvent-->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.i("onInterceptTouchEvent-->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.i("onInterceptTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtil.i("onInterceptTouchEvent---ACTION_CANCEL");
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                LogUtil.i("onTouchEvent-->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.i("onTouchEvent-->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.i("onTouchEvent---ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtil.i("onTouchEvent---ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }
}
