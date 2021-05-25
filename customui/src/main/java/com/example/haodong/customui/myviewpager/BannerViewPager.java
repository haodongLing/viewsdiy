package com.example.haodong.customui.myviewpager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;


/**
 * created by linghaoDo on 2020-04-08
 * description:
 * <p>
 * version:
 */
public class BannerViewPager extends ViewPager {
    private BannerAdapter mAdapter;
    /*2. 发送消息msg*/
    private final int SCROLL_MSG = 0x0011;
    private final int DELAY_MILLS = 3500;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setCurrentItem(getCurrentItem() + 1);
            startRoll();
        }
    };
    private int mScrollerDuration = 850;
    private BannerScroller mBannerScroller;

    public void setScrollerDuration(int scrollerDuration) {
        mBannerScroller.setScrollerDuration(scrollerDuration);
    }

    public BannerViewPager(@NonNull Context context) {
        this(context, null);
    }


    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        /*3. 改变ViewPager切换的速率*/
        /*duration 持续时间，局部变量*/
        /*改变mScroller private*/
        try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            mBannerScroller = new BannerScroller(context);
            mBannerScroller.setScrollerDuration(mScrollerDuration);
            field.setAccessible(true);
            field.set(this, mBannerScroller);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void setAdapter(BannerAdapter adapter) {
        this.mAdapter = adapter;
        super.setAdapter(new BannerPagerAdapter());
    }

    public void startRoll() {
        /*清楚消息*/
        mHandler.removeMessages(SCROLL_MSG);
        mHandler.sendEmptyMessageDelayed(SCROLL_MSG, DELAY_MILLS);
    }

    /**
     * 停止发送,解决内存泄漏
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mHandler.removeMessages(SCROLL_MSG);
        mHandler = null;
    }


    private class BannerPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            // 为了实现无限循环
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            position = position % mAdapter.getCount();
            View bannerItemView = mAdapter.getView(position);
            container.addView(bannerItemView);
            return bannerItemView;
        }

        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
            object = null;
        }

    }
}
