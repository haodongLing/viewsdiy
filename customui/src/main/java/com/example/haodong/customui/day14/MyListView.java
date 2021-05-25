package com.example.haodong.customui.day14;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * describe:
 * created at 2019/2/14
 * Author linghailong
 */
public class MyListView extends ListView {
    public MyListView(Context context) {
        this(context,null);
    }

    public MyListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("tag", "dispatchTouchEvent:--->mListView " );
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptHoverEvent(MotionEvent event) {
        Log.e("tag", "onInterceptHoverEvent: --->mListView" );
        return super.onInterceptHoverEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.e("tag", "onTouchEvent: ---->mListView" );
        return super.onTouchEvent(ev);
    }
}
