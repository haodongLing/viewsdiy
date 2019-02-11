package com.example.haodong.viewday1.day12;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import com.example.haodong.viewday1.R;
import com.example.haodong.viewday1.SizeUtils;

public class MySlidingMenu extends HorizontalScrollView {
    private int mScreenWidth;
    private int mMenuRightPadding;
    private int mMenuWidth;
    private int mHalfMenuWidth;
    private boolean isOpen;
    private boolean once;
    private View mMenuView,mContentView;

    private Context mContext;

    private GestureDetector mGestureDetector; // 系统自带的手势处理类

    public MySlidingMenu(Context context) {
        this(context, null);
    }

    public MySlidingMenu(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MySlidingMenu);
        float rightMargin = typedArray.getDimension(R.styleable.MySlidingMenu_menuRightMargin, 50);
        rightMargin = SizeUtils.dip2px(context, rightMargin);
        mMenuWidth = (int) (ScreenUtils.getScreenWidth(context) - rightMargin);
        typedArray.recycle();
        init(context);
    }

    private void init(Context context) {
        mMenuRightPadding = SizeUtils.dip2px(context, 50);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        if (!once) {
//            LinearLayout wrapper = (LinearLayout) getChildAt(0);
//            ViewGroup menu = (ViewGroup) wrapper.getChildAt(0);
//            ViewGroup content = (ViewGroup) wrapper.getChildAt(1);
//
//            mMenuWidth = mScreenWidth - mMenuRightPadding;
//            mHalfMenuWidth = mMenuWidth / 2;
//            menu.getLayoutParams().width = mMenuWidth;
//            content.getLayoutParams().width = mScreenWidth;
//
//        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(mMenuWidth,0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        // 这个方法代表整个布局加载完毕
        // 1.获取菜单和内容View
        // 这个获取的是 根布局  LinearLayout
        ViewGroup container = (ViewGroup) getChildAt(0);

        // 2.给其指定宽度
        mMenuView =  container.getChildAt(0);
        // 2.1 指定菜单的宽度 LayoutParams 是布局的一些属性

        mMenuView.getLayoutParams().width = mMenuWidth;

        mContentView = container.getChildAt(1);
        // 2.1 指定内容的宽度  指定宽高会重新摆放  onLayout() 方法
        mContentView.getLayoutParams().width = ScreenUtils.getScreenWidth(mContext);

        // 3. 默认是关闭的  也就是说默认划过去
        // scrollTo(mMenuWidth,0);  靠折腾  靠蒙  靠猜
    }
}
