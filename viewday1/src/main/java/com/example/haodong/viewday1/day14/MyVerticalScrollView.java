package com.example.haodong.viewday1.day14;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import com.example.haodong.viewday1.SizeUtils;
import com.example.haodong.viewday1.day12.ScreenUtils;

/**
 * describe: 模仿汽车之家的垂直下拉
 * created at 2019/2/13
 * Author linghailong
 */
public class MyVerticalScrollView extends ScrollView {
    private Context mContext;
    private View mMenuView,mContentView;
    private int mMenuHeight;
    public MyVerticalScrollView(Context context) {
        this(context,null);
    }

    public MyVerticalScrollView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyVerticalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mMenuHeight=SizeUtils.dp2px(200,context);
        this.mContext=context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewGroup viewGroup= (ViewGroup) getChildAt(0);
        mMenuView=viewGroup.getChildAt(0);
        mContentView=viewGroup.getChildAt(1);
        ViewGroup.LayoutParams layoutParams=mMenuView.getLayoutParams();
        layoutParams.height=mMenuHeight;
        layoutParams.width=ScreenUtils.getScreenWidth(mContext);
        mMenuView.setLayoutParams(layoutParams);
        mContentView=viewGroup.getChildAt(1);
        ViewGroup.LayoutParams contentParams=mContentView.getLayoutParams();
        contentParams.height=ScreenUtils.getScreenHeight(mContext);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        scrollTo(0,mMenuHeight);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (t<mMenuHeight){
            mMenuView.setTranslationY(t);
        }
    }
}
