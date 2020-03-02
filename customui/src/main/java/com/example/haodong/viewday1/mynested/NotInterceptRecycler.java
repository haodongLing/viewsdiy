package com.example.haodong.viewday1.mynested;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.haodong.common.util.LogUtil;

/**
 * created by linghaoDo on 2019-11-27
 * description:
 * <p>
 * version:
 */
public class NotInterceptRecycler extends RecyclerView {
    public NotInterceptRecycler(@NonNull Context context) {
        this(context, null);
    }

    public NotInterceptRecycler(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NotInterceptRecycler(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    float preX;
    float preY;



}
