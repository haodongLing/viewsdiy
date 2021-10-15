package com.example.haodong.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

/**
 * Author: tangyuan
 * Time : 2021/7/6
 * Description:
 */
public class Draglayout extends LinearLayout {
    private ViewDragHelper mDragger;

    public Draglayout(Context context) {
        this(context, null);
    }

    public Draglayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Draglayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mDragger = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return false;
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull  View child, int left, int dx) {
                return super.clampViewPositionHorizontal(child, left, dx);
            }

            @Override
            public int clampViewPositionVertical(@NonNull  View child, int top, int dy) {
                return super.clampViewPositionVertical(child, top, dy);
            }

            @Override
            public int getViewHorizontalDragRange(@NonNull  View child) {
                return super.getViewHorizontalDragRange(child);
            }

            @Override
            public void onEdgeDragStarted(int edgeFlags, int pointerId) {
                super.onEdgeDragStarted(edgeFlags, pointerId);
            }

            @Override
            public void onEdgeTouched(int edgeFlags, int pointerId) {
                super.onEdgeTouched(edgeFlags, pointerId);
            }

            @Override
            public boolean onEdgeLock(int edgeFlags) {
                return super.onEdgeLock(edgeFlags);
            }
        });
    }
}
