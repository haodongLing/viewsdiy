package com.example.haodong.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/6/16
 * Description:
 */
public class DirectionView extends LinearLayout {
    public DirectionView(Context context) {
        this(context, null);
    }

    public DirectionView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DirectionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.direction_view_layout, this);
    }
}
