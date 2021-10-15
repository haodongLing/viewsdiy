package com.example.haodong.customui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.ViewDragHelper;

/**
 * Author: tangyuan
 * Time : 2021/7/6
 * Description:
 */
public class SlidingMenuGroup extends FrameLayout {
    private ViewDragHelper mViewDragHelper;
    private View mMainView;
    private View mMenuView;
    private int mMenuViewWidth = 500;

    public SlidingMenuGroup(@NonNull Context context) {
        this(context, null);
    }

    public SlidingMenuGroup(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingMenuGroup(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        if (mViewDragHelper != null) {
            return;
        }
        mViewDragHelper = ViewDragHelper.create(this, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(@NonNull View child, int pointerId) {
                return child == mMainView;
            }

            @Override
            public int clampViewPositionHorizontal(@NonNull View child, int left, int dx) {
                return super.clampViewPositionHorizontal(child, left, dx);
            }

            @Override
            public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (mMainView.getLeft() < mMenuViewWidth / 2) {
                    mViewDragHelper.smoothSlideViewTo(mMainView, 0, 0);
                } else {
                    mViewDragHelper.smoothSlideViewTo(mMainView, mMenuViewWidth, 0);
                }
                invalidate();
            }

            @Override
            public void onViewPositionChanged(@NonNull View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                float percent = mMainView.getLeft() / (float) mMenuViewWidth;
                executeAnimation(percent);
            }
        });
    }

    /**
     * 值越大，mainView 变得越小
     * @param percent
     */
    private void executeAnimation(float percent) {
        mMenuView.setScaleX(0.5f+0.5f*percent);
        mMenuView.setScaleY(0.5f+0.5f*percent);

        mMainView.setScaleX(1-percent*0.2f);
        mMainView.setScaleY(1-percent*0.2f);

        mMenuView.setTranslationX(-mMenuViewWidth/2+mMenuViewWidth/2*percent);
    }

    public void setView(View mainView, LayoutParams mainLayoutParams,
                        View menuView, LayoutParams menuLayoutParams) {
        mMenuView = menuView;
        addView(menuView, menuLayoutParams);
        mMenuViewWidth = menuLayoutParams.width;

        mMainView = mainView;
        addView(mainView, mainLayoutParams);
    }
}
