package com.example.haodong.customui.xiangxue;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Author: tangyuan
 * Time : 2022/1/10
 * Description:
 */
public class WrapperViewPager extends ViewPager {
    public WrapperViewPager(@NonNull Context context) {
        this(context, null);
    }

    public WrapperViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            ViewGroup.LayoutParams childParam = child.getLayoutParams();
            child.measure(widthMeasureSpec, getChildMeasureSpec(heightMeasureSpec, getPaddingTop() + getPaddingBottom(), childParam.height));
            int h = child.getMeasuredHeight();
            if (h > height) {
                height = h;
            }
        }
        heightMeasureSpec=MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }
}
