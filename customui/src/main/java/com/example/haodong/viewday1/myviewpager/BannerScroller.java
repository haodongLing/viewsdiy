package com.example.haodong.viewday1.myviewpager;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * created by linghaoDo on 2020-04-09
 * description: 改变viewpager切换的速率
 * <p>
 * version:
 */
public class BannerScroller extends Scroller {
    /*动画持续时间*/
    private int mScrollerDuration=850;

    /**
     * 设置切换页面持续时间
     * @param scrollerDuration
     */
    public void setScrollerDuration(int scrollerDuration) {
        this.mScrollerDuration = scrollerDuration;
    }

    public BannerScroller(Context context) {
        super(context);
    }

    public BannerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public BannerScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mScrollerDuration);
    }
}
