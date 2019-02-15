package com.example.haodong.viewday1.day14;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * describe:
 * created at 2019/2/13
 * Author linghailong
 */
public class VerticalDragListView extends FrameLayout {
    private ViewDragHelper mDragHelper;
    private View mDragerListView;
    private int mMenuHeight;
    /*菜单是否打开*/
    private boolean mMenuIsOpen = false;
    private float mDownY;

    public VerticalDragListView(Context context) {
        this(context, null);
    }

    public VerticalDragListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VerticalDragListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, mDragHelperCallback);
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_TOP);

    }

    private ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            /*指定子View是否可以拖动*/
            /*判断当前view是否为listView*/
            return view == mDragerListView;
        }
        /**
         * 水平拖动移动的位置
         * @param child
         * @param left
         * @param dx
         * @return
         */
//        @Override
//        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
//           return left;
//        }

        /**
         * 垂直拖动，移动的位置
         * @param child
         * @param top
         * @param dy
         * @return
         */
        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            if (top <= 0) {
                top = 0;
            } else if (top >= mMenuHeight) {
                top = mMenuHeight;
            }
            return top;
        }

        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (releasedChild == mDragerListView) {
                if (mDragerListView.getTop() > mMenuHeight / 2) {
                    // 滚动到菜单的高度（打开）
                    mDragHelper.settleCapturedViewAt(0, mMenuHeight);
                    mMenuIsOpen = true;
                } else {
                    // 滚动到0的位置（关闭）
                    mDragHelper.settleCapturedViewAt(0, 0);
                    mMenuIsOpen = false;
                }
                invalidate();
            }

        }
    };

    /**
     * 响应滚动
     */
    @Override
    public void computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.e("tag", "dispatchTouchEvent:---->ViewDrager" );
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mMenuIsOpen) {
            return true;
        }
        Log.e("tag", "onInterceptTouchEvent:----->ViewDrager" );
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.e("tag", "onInterceptTouchEvent: ----->ViewDrager-->DOWN");
                mDownY= ev.getY();
                /*让dragerhelper拿一个完整的事件,因为当actionDowm的时候*/
                mDragHelper.processTouchEvent(ev);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("tag", "onInterceptTouchEvent: ----ViewDrager---MOVE");
                float moveY=ev.getY();
                if((moveY-mDownY)>0&& !canChildScrollUp()){
                    /*向下滑动*/
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.e("tag", "onInterceptTouchEvent:---->ViewDrager---UP " );
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("tag" ,"onTouchEvent:---->ViewDrager ");
        mDragHelper.processTouchEvent(event);
        return true;

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount != 2) {
            throw new RuntimeException("child count should be equals two");
        }
        mDragerListView = getChildAt(1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        View menuView = getChildAt(0);
        mMenuHeight = menuView.getMeasuredHeight();
    }

    public boolean canChildScrollUp() {
//        if (android.os.Build.VERSION.SDK_INT < 14) {
//            if (mDragerListView instanceof AbsListView) {
//                final AbsListView absListView = (AbsListView) mDragerListView;
//                return absListView.getChildCount() > 0
//                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
//                        .getTop() < absListView.getPaddingTop());
//            } else {
//                return ViewCompat.canScrollVertically(mDragerListView, -1) || mDragerListView.getScrollY() > 0;
//            }
//        } else {
//            return ViewCompat.canScrollVertically(mDragerListView, -1);
//        }
        return mDragerListView.canScrollVertically(-1);
    }
}
