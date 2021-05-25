package com.example.haodong.customui.mynested;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.util.AttributeSet;

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
