package com.example.haodong.viewday1.myviewpager;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.haodong.viewday1.R;
import com.example.haodong.viewday1.day12.ScreenUtils;

/**
 * created by linghaoDo on 2020-04-09
 * description: 自定义banner view
 * <p>
 * version:
 */
public class BannerView extends RelativeLayout {
    private BannerViewPager mViewPager;
    /*轮播描述*/
    private TextView mBannerDescTv;
    /*点的容器*/
    private LinearLayout mDotContainer;
    private BannerAdapter mAdapter;
    private Context mContext;
    /*选中的指示器的drawable*/
    private Drawable mIndicatorFocusDrawable;
    /*初始化默认的点指示器*/
    private Drawable mIndicatorNormalDrawable;
    private int mCurrentPosition;

    public BannerView(Context context) {
        this(context, null);
    }

    public BannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        /*加载布局*/
//        LayoutInflater.from(context).inflate()
        this.mContext = context;
        inflate(context, R.layout.banner_layout, this);
        initView();
        mIndicatorFocusDrawable = new ColorDrawable(Color.RED);
        mIndicatorFocusDrawable = new ColorDrawable(Color.WHITE);
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mBannerDescTv = findViewById(R.id.tv_banner_desc);
        mDotContainer = findViewById(R.id.dot_container);
    }

    public void setAdapter(BannerAdapter bannerAdapter) {
        mAdapter = bannerAdapter;
        mViewPager.setAdapter(bannerAdapter);
        initDotIndicator();
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {


            }

            @Override
            public void onPageSelected(int i) {
                pageSelect(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        String firstDesc = mAdapter.getBannerDesc(0);
        if (!TextUtils.isEmpty(firstDesc)) {
            mBannerDescTv.setText(firstDesc);
        } else {
            mBannerDescTv.setText(" ");
        }
    }

    private void pageSelect(int position) {
        DotIndicatorView oldIndicatorView = (DotIndicatorView) mDotContainer.getChildAt(mCurrentPosition);
        oldIndicatorView.setDrawable(mIndicatorNormalDrawable);
        mCurrentPosition = position % mAdapter.getCount();
        DotIndicatorView currentIndicatorView = (DotIndicatorView) mDotContainer.getChildAt(mCurrentPosition);
        currentIndicatorView.setDrawable(mIndicatorFocusDrawable);
        String bannerDesc = mAdapter.getBannerDesc(mCurrentPosition);
        if (!TextUtils.isEmpty(bannerDesc)) {
            mBannerDescTv.setText(bannerDesc);
        } else {
            mBannerDescTv.setText(" ");
        }
    }

    /**
     * 初始化点的指示器
     */
    private void initDotIndicator() {
        int count = mAdapter.getCount();
        /**/
        mDotContainer.setGravity(Gravity.RIGHT);

        for (int i = 0; i < count; i++) {
            /*不断地往点的指示器添加圆点*/
            DotIndicatorView indicatorView = new DotIndicatorView(mContext);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ScreenUtils.dip2px(mContext, 8)
                    , ScreenUtils.dip2px(mContext, 8));
            layoutParams.leftMargin = layoutParams.rightMargin = ScreenUtils.dip2px(mContext, 8);
            indicatorView.setLayoutParams(layoutParams);
            if (i == 0) {
                indicatorView.setDrawable(mIndicatorFocusDrawable);
            } else {
                indicatorView.setDrawable(mIndicatorNormalDrawable);
            }
            mDotContainer.addView(indicatorView);

        }
    }

}
