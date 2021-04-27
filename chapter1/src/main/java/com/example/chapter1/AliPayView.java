package com.example.chapter1;

import android.content.Context;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * created by tangyuan on 4/27/21
 * description:
 */
public class AliPayView extends View {
    Path mDstPath;
    Path mCirclePath;
    private int mCentX;
    private int mCentY;

    public AliPayView(Context context) {
        this(context, null);
    }

    public AliPayView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AliPayView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setLayerType(LAYER_TYPE_SOFTWARE, null);
        mDstPath = new Path();
        mCirclePath = new Path();

    }
}
