package com.example.haodong.viewday1.day9;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class TouchView extends View {
    public TouchView(Context context) {
        this(context,null);
    }

    public TouchView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TouchView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("TAG", "onTouchEvent: "+event.getAction());
        return super.onTouchEvent(event);
    }
}
