package com.example.haodong.viewday1.iphone;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.example.haodong.viewday1.SizeUtils;

/**
 * describe:
 * created at 2019/2/21
 * Author linghailong
 */
public class HorizontalSlipView extends RelativeLayout {
    /*是否在主应用中，如果不在主应用中，不进行事件分发*/
    private boolean isCanWork=false;
    private ViewDragHelper mDragHelper;
    private View mDragerTouchedView;
    private View mTextView;
    /*touchedView是否移动了指定长度的位置*/
    private boolean mTouchedViewIsMoved=false;
    private int mGroupWidth;
    /*TouchedView的长度*/
    private int mWidth;
    /*touchedview的起始Top*/
    private int mTouchedViewTop;
    /*touchedview的起始left*/
    private int mTouchedViewLeft;
    /*误差width*/
    private int mFaultWidth=SizeUtils.dip2px(getContext(),10);

    /*滑动距离*/
    private int mSlidWidth;
    private OnChildViewMovedListener mOnChildViewMovedListener;

    public HorizontalSlipView(@NonNull Context context) {
        this(context, null);
    }

    public HorizontalSlipView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalSlipView(@NonNull Context context, @Nullable AttributeSet attrs, int
            defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDragHelper = ViewDragHelper.create(this, mDragHelperCallback);
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_ALL);
    }

    public void setCanWork(boolean canWork) {
        this.isCanWork = canWork;
    }

    private ViewDragHelper.Callback mDragHelperCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View view, int i) {
            /*指定子View是否可以拖动*/
            /*判断当前view是否为listView*/
            return view == mDragerTouchedView;
        }
        /**
         * 水平拖动移动的位置
         * @param child
         * @param left
         * @param dx
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
            mTouchedViewIsMoved=false;

            if (left<=mTouchedViewLeft){
                left=mTouchedViewLeft;
            }
            if (left>(mTouchedViewLeft+10)){
                mTouchedViewIsMoved=true;
                mTextView.setVisibility(View.INVISIBLE);
//                postInvalidate();
            }
            if (left>=mSlidWidth){
                left=mSlidWidth;
            }
            Log.e("tag", "clampViewPositionHorizontal: left--->"+left );
           return left;
        }

        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            return mTouchedViewTop;
        }

        @Override
        public int getViewHorizontalDragRange(@NonNull View child) {
//            if (child==mDragerTouchedView){
//
//            }
            return getMeasuredHeight()-child.getMeasuredWidth();
        }

        /**
         * 当手指松开的时候 要么打开，要么原来的位置
         * @param releasedChild
         * @param xvel
         * @param yvel
         */
        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            super.onViewReleased(releasedChild, xvel, yvel);
            if (releasedChild == mDragerTouchedView) {
                /*如果说滑动的距离大于阈值，那木执行*/
                if(mDragerTouchedView.getLeft()==mSlidWidth){
                    if (mOnChildViewMovedListener!=null){
                        mOnChildViewMovedListener.onChildViewMove();
                    }
                    Log.e("tag", "HorizontalSlipView-->onViewReleased: 的mOnChildViewMovedListener=null" );
                    mDragHelper.settleCapturedViewAt(mTouchedViewLeft,mTouchedViewTop);
                    mTextView.setVisibility(View.VISIBLE);
                }else {
                    /*移动到初始位置*/
                    mDragHelper.settleCapturedViewAt(mTouchedViewLeft,mTouchedViewTop);
                    mTextView.setVisibility(View.VISIBLE);
                }
                invalidate();
            }

        }

    };

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mGroupWidth=getMeasuredWidth();
        View view=getChildAt(0);
        mWidth=view.getMeasuredWidth();
        mTouchedViewTop=view.getTop();
        mTouchedViewLeft=view.getLeft();
        Log.e("tag", "onLayout: --->"+"mWidth--->"+mWidth+"mTouchedViewTop--->"+mTouchedViewTop
                +"mTouchedViewLeft--->"+mTouchedViewLeft);
        mSlidWidth=mGroupWidth-mWidth-mFaultWidth;
        Log.e("tag", "onLayout:mSlidWidth----> "+mSlidWidth);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        /*拿到两个布局*/
        int childCount=getChildCount();
        if (childCount!=2){
            throw  new RuntimeException("HorizontalSlipView 只能包含两个布局");
        }
        /*第一个布局是TouchView*/
        mDragerTouchedView=getChildAt(0);
        /*第二个布局是TextView*/
        mTextView=getChildAt(1);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!isCanWork){
            return false;
        }
        mDragHelper.processTouchEvent(event);
        return true;
    }

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
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /*如果请求拦截*/
        if (isCanWork){
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return super.onInterceptTouchEvent(ev);
    }

    public void setOnChildViewMovedListener(OnChildViewMovedListener onChildViewMovedListener) {
        this.mOnChildViewMovedListener = onChildViewMovedListener;
    }

    public interface  OnChildViewMovedListener{
        void onChildViewMove();
    }



}
