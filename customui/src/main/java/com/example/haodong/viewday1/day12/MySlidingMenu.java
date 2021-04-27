package com.example.haodong.viewday1.day12;

import android.content.Context;
import android.content.res.TypedArray;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.example.haodong.viewday1.R;
import com.example.haodong.viewday1.SizeUtils;

public class MySlidingMenu extends HorizontalScrollView {
    private int mMenuWidth;
    private int mHalfMenuWidth;
    private boolean isOpen = false;
    private View mMenuView, mContentView;

    private Context mContext;

    private GestureDetector mGestureDetector; // 系统自带的手势处理类
    // 是否拦截
    private boolean mIsIntercept = false;

    public MySlidingMenu(Context context) {
        this(context, null);
    }

    public MySlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySlidingMenu);
        int rightMargin = (int) typedArray.getDimension(R.styleable
                .MySlidingMenu_menuRightMargin, 50);
        rightMargin = SizeUtils.dp2px(rightMargin, context);
        mMenuWidth = (ScreenUtils.getScreenWidth(context) - rightMargin);
        Log.e("tag", "MySlidingMenu: " + mMenuWidth);
        mHalfMenuWidth = mMenuWidth / 2;
        typedArray.recycle();
        // 只关注快速滑动,只要快速就会回掉
        // 条件 打开的时候往右边快速滑动切换（关闭），关闭的时候往左边快速滑动切换（打开）
        // 快速往左边滑动的时候是一个负数，往右边滑动的时候是一个正数
        // 打开的时候往右边快速滑动切换（关闭）
        // 关闭的时候往左边快速滑动切换（打开）
        GestureDetector.OnGestureListener mGestureListener = new
                GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float
                            velocityY) {
                        // 只关注快速滑动,只要快速就会回掉
                        // 条件 打开的时候往右边快速滑动切换（关闭），关闭的时候往左边快速滑动切换（打开）
                        Log.e("TAG", "velocityX -> " + velocityX);
                        // 快速往左边滑动的时候是一个负数，往右边滑动的时候是一个正数
                        if (isOpen) {
                            // 打开的时候往右边快速滑动切换（关闭）
                            if (velocityX < 0) {
                                closeMenu();
                                return true;
                            }
                        } else {
                            // 关闭的时候往左边快速滑动切换（打开）
                            if (velocityX > 0) {
                                openMenu();
                                return true;
                            }
                        }
                        return super.onFling(e1, e2, velocityX, velocityY);
                    }
                };
        mGestureDetector = new GestureDetector(context, mGestureListener);
        this.mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(mMenuWidth, 0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 这个方法代表整个布局加载完毕
        // 1.获取菜单和内容View
        // 这个获取的是 根布局  LinearLayout
        ViewGroup container = (ViewGroup) getChildAt(0);

        // 2.给其指定宽度
        mMenuView = container.getChildAt(0);
        // 设置只能通过 LayoutParams ，
        ViewGroup.LayoutParams menuParams = mMenuView.getLayoutParams();
        menuParams.width = mMenuWidth;
        Log.e("tag", "onFinishInflate: " + menuParams.width);
        // 7.0 以下的手机必须采用下面的方式
        mMenuView.setLayoutParams(menuParams);

        // 2.菜单页的宽度是 屏幕的宽度 - 右边的一小部分距离（自定义属性）
        mContentView = container.getChildAt(1);
        ViewGroup.LayoutParams contentParams = mContentView.getLayoutParams();
        contentParams.width = ScreenUtils.getScreenWidth(mContext);
        mContentView.setLayoutParams(contentParams);

        // 3. 默认是关闭的  也就是说默认划过去
        // scrollTo(mMenuWidth,0);  靠折腾  靠蒙  靠猜
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) { // 如果有拦截不要执行自己的 onTouch
        mIsIntercept = false;

        // 2. 处理事件拦截 + ViewGroup 事件分发的源码实践
        //    当菜单打开的时候，手指触 摸右边内容部分需要关闭菜单，还需要拦截事件（打开情况下点击内容页不会响应点击事件）
        if (isOpen) {
            float currentX = ev.getX();
            if (currentX > mMenuWidth) {
                // 1.关闭菜单
                closeMenu();
                // 2.子 View 不需要响应任何事件（点击和触摸），拦截子 View 的事件
                // 如果返回 true 代表我会拦截子View的事件，但是我会响应自己的 onTouch 事件
                mIsIntercept = true;
                return true;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // 如果有拦截不要执行自己的 onTouch
        if (mIsIntercept) {
            return true;
        }

        //重要
        if (mGestureDetector.onTouchEvent(ev)) {
            // 快速滑动触发了下面的就不要执行了
            return true;
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            // 只需要管手指抬起 ，根据我们当前滚动的距离来判断
            int currentScrollX = getScrollX();

            if (currentScrollX > mMenuWidth / 2) {
                // 关闭
                closeMenu();
            } else {
                // 打开
                openMenu();
            }
            // 确保 super.onTouchEvent() 不会执行
            return true;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 打开菜单 滚动到 0 的位置
     */
    private void openMenu() {
        // smoothScrollTo 有动画
        smoothScrollTo(0, 0);
        isOpen = true;
    }

    /**
     * 关闭菜单 滚动到 mMenuWidth 的位置
     */
    private void closeMenu() {
        smoothScrollTo(mMenuWidth, 0);
        isOpen = false;
    }
    // 处理content的缩放以及menu的位移


    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        float scale = 1f * l / mMenuWidth;
        float rightScale = 0.7f + 0.3f * scale;
        Log.e("tag", "onScrollChanged: " + rightScale);

        mContentView.setPivotX(0);
        mContentView.setPivotY(mContentView.getMeasuredHeight() / 2);
        /*缩放默认是以中心来缩放，所以最好要定一个缩放点*/
        mContentView.setScaleX(rightScale);
        mContentView.setScaleY(rightScale);

        // 菜单的缩放和透明度
        // 透明度是 半透明到完全透明  0.5f - 1.0f
        float leftAlpha = 0.5f + (1 - scale) * 0.5f;
        mMenuView.setAlpha(leftAlpha);
        // 缩放 0.7f - 1.0f
        float leftScale = 0.7f + (1 - scale) * 0.3f;
        mMenuView.setScaleX(leftScale);
        mMenuView.setScaleY(leftScale);

        // 最后一个效果 退出这个按钮刚开始是在右边，按照我们目前的方式永远都是在左边
        // 设置平移，先看一个抽屉效果
        // ViewCompat.setTranslationX(mMenuView,l);
        // 平移 l*0.7f
        mMenuView.setTranslationX(0.25f * l);
    }


}
