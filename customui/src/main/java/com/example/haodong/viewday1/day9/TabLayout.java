package com.example.haodong.viewday1.day9;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class TabLayout extends ViewGroup {
    private TabBaseAdapter mAdapter;
    private List<List<View>> mChildViews = new ArrayList<>();

    public TabLayout(Context context) {
        super(context);
    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 指定子view的位置
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childCount = getChildCount();
        int left, top = getPaddingTop(), right, bottom;
        for (List<View> childViews : mChildViews) {

            /*每行中的View*/
            left = getPaddingLeft();
            int maxHeight = 0;
            for (View view : childViews) {
                if(view.getVisibility() == GONE){
                    continue;
                }
                ViewGroup.MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();
                left += params.leftMargin;
                int childTop = top + params.topMargin;
                right = left + view.getMeasuredWidth();
                bottom = childTop + view.getMeasuredHeight();
                /*摆放*/
                view.layout(left, top, right, bottom);
                /*叠加*/
                left += view.getMeasuredWidth() + params.rightMargin;
                // 不断的叠加top值
                int childHeight = view.getMeasuredHeight()+ params.topMargin+params.bottomMargin;
                maxHeight = Math.max(maxHeight,childHeight);
            }
            // 不断的叠加top值
            top += maxHeight;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 清空集合
        mChildViews.clear();
        /*获取高度以及宽度*/
        /*这个没有太大的变化*/
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = getPaddingTop() + MeasureSpec.getSize(heightMeasureSpec);
        /*计算lineWidth*/
        int lineWidth = getPaddingLeft();
        int childCount = getChildCount();
        ArrayList<View> childViews = new ArrayList<>();
        mChildViews.add(childViews);
        // 子View高度不一致的情况下
        int maxHeight = 0;
        for (int i = 0; i < childCount; i++) {
            /*获取childView*/
            View childView = getChildAt(i);
            if(childView.getVisibility()==GONE){
                continue;
            }
            /*这段话执行完之后，可以获取子view的宽gao，因为会调用子View的onMeasure方法。*/
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            /*根据子View计算和指定自己的布局*/
            // margin值 ViewGroup.LayoutParams 没有 就用系统的MarginLayoutParams
            // 想想 LinearLayout为什么有？
            // LinearLayout有自己的 LayoutParams  会复写一个非常重要的方法
            ViewGroup.MarginLayoutParams params = (MarginLayoutParams) childView.getLayoutParams();
            /*换行计算*/
            if (lineWidth + (childView.getMeasuredWidth() + params.rightMargin + params.leftMargin) > width) {
                // 换行,累加高度  加上一行条目中最大的高度
                height += childView.getMeasuredHeight() + params.bottomMargin + params.topMargin;
                lineWidth = childView.getMeasuredWidth() + params.rightMargin + params.leftMargin;
                childViews = new ArrayList<>();
                mChildViews.add(childViews);
            } else {
                /*不换行*/
                lineWidth += childView.getMeasuredWidth() + params.rightMargin + params.leftMargin;
                maxHeight = Math.max(childView.getMeasuredHeight() + params.bottomMargin + params
                        .topMargin, maxHeight);
            }
            childViews.add(childView);
        }
        /*获取该行View中的最大值作为View的高*/
        height += maxHeight;
        /*计算ViewGroup自己的宽高*/
        setMeasuredDimension(width, height);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    public void setmAdapter(TabBaseAdapter mAdapter) {
        if (mAdapter==null){
            /*空指针异常*/
            throw new NullPointerException("TabBaseAdapter is not allowed to be null");
        }
        /*清空所有的子View*/
        removeAllViews();
        this.mAdapter=null;
        this.mAdapter = mAdapter;
        int childCount=mAdapter.getCount();
        for(int i=0;i<childCount;i++){
            View view=mAdapter.getView(i,this);
            addView(view);
        }
    }
}
