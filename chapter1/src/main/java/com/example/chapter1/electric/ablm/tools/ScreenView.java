package com.example.chapter1.electric.ablm.tools;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * description:
 * author: linghailong
 * date: 2019/3/26
 */
public class ScreenView extends android.support.v7.widget.AppCompatImageView {
    private int height;
    public ScreenView(Context context) {
        this(context,null);
        height=UiTool.getScreenHeight(context)/3;
    }

    public ScreenView(Context context,  AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ScreenView(Context context,  AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width=MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width,height);

    }
}
