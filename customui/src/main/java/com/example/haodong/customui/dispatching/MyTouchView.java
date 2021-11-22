package com.example.haodong.customui.dispatching;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.TextView;

import com.example.haodong.common.util.LogUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * Author: tangyuan
 * Time : 2021/11/21
 * Description:
 */
public class MyTouchView extends AppCompatTextView {

    public MyTouchView(Context context) {
        this(context, null);
    }

    public MyTouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtil.i("MyTouchView  dispatchTouchEvent-->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.i("MyTouchView  dispatchTouchEvent-->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.i("MyTouchView  dispatchTouchEvent-->ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtil.i("MyTouchView  dispatchTouchEvent-->ACTION_CANCEL");
                break;
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                LogUtil.i("MyTouchView  onTouchEvent-->ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                LogUtil.i("MyTouchView  onTouchEvent-->ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                LogUtil.i("MyTouchView  onTouchEvent-->ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                LogUtil.i("MyTouchView  onTouchEvent-->ACTION_CANCEL");
                break;
        }
        return super.onTouchEvent(event);
    }
}
