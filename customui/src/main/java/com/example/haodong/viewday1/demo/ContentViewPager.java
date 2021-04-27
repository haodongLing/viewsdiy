package com.example.haodong.viewday1.demo;

import android.content.Context;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;

import com.example.haodong.common.util.LogUtil;

/**
 * describe :
 * date on 2021/4/14
 * author Aaron
 * description:
 */
public class ContentViewPager extends ViewPager {
    public SparseArray<Integer> map = new SparseArray<>();
    public int mHeight;

    public ContentViewPager(Context context) {
        super(context);
    }

    public ContentViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int index = getCurrentItem();
        int height = 0;
        LogUtil.i();
        View v = ((FragmentPagerAdapter) getAdapter()).getItem(index).getView();
        if (v != null) {
            v.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
            height = v.getMeasuredHeight();
            map.put(index, v.getMeasuredHeight());
        }
        if (mHeight != 0) {
            height = mHeight;
        }
        map.put(index, height);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onPageScrolled(int position, float offset, int offsetPixels) {
        super.onPageScrolled(position, offset, offsetPixels);
        requestLayout();
    }

    public void setHeight(int height) {
        mHeight = height;
        postInvalidate();
    }
}