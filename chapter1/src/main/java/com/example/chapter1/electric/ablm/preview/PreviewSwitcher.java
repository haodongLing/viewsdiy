package com.example.chapter1.electric.ablm.preview;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ViewSwitcher;

import com.example.chapter1.R;

/**
 * description:
 * author: linghailong
 * date: 2019/3/26
 */
public class PreviewSwitcher extends ViewSwitcher implements ViewSwitcher.ViewFactory {
    private static final int FLAG_START_SCROLL = 0;
    private static final int FLAG_STOP_SCROLL = 1;
    private static final int FLAG_REFRESH = 2;

    //默认时间间隔
    private static final int DEFAULT_TIME_SPAN = 6000;
    private static final int DEFAULT_IN_ANIM_ID = R.anim.advert_scroll_in;
    private static final int DEFAULT_OUT_ANIM_ID = R.anim.advert_scroll_out;
    private static final int DEFAULT_INTERPOLATOR = android.R.interpolator.linear;

    private Context mContext;
    /**
     * 广告滚动的时间间隔
     */
    private long mTimeSpan;
    /**
     * view进入动画
     */
    private int mInAnimId;
    /**
     * view离开动画
     */
    private int mOutAnimId;
    /**
     *
     */
    private int interpolator;

    /**
     * 当前角标
     */
    private int currentIndex;

    private Handler mHandler;

    private IPreviewAdapter mAdapter;

    public PreviewSwitcher(Context context) {
        this(context, null);

    }

    public PreviewSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        initAttrubute(attrs);
        init();
    }

    private void init() {
        currentIndex = 0;
        mHandler=new ScrollHandler();
        Animation inAnimation=AnimationUtils.loadAnimation(mContext,mInAnimId);
        inAnimation.setInterpolator(new AccelerateInterpolator());
        Animation outAnimation=AnimationUtils.loadAnimation(mContext,mOutAnimId);
        outAnimation.setInterpolator(new DecelerateInterpolator());
        setInAnimation(inAnimation);
        setOutAnimation(outAnimation);

    }

    private void initAttrubute(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable
                .PreviewSwitcher);
        mTimeSpan = typedArray.getInteger(R.styleable.PreviewSwitcher_timeSpan, DEFAULT_TIME_SPAN);
        mInAnimId = typedArray.getResourceId(R.styleable.PreviewSwitcher_inAnim,
                DEFAULT_IN_ANIM_ID);
        mOutAnimId = typedArray.getResourceId(R.styleable.PreviewSwitcher_outAnim,
                DEFAULT_OUT_ANIM_ID);
        interpolator = typedArray.getResourceId(R.styleable.PreviewSwitcher_interpolator,
                DEFAULT_INTERPOLATOR);
        typedArray.recycle();
        typedArray = null;
    }

    @Override
    public View makeView() {
        return mAdapter.makeView();
    }

    public void setmAdapter(IPreviewAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public IPreviewAdapter getmAdapter() {
        return mAdapter;
    }

    /**
     * 建议在onResume中调用
     */
    public void start() {
        //确保setFactory只在无子View的时候调用
        if (getChildCount() == 0) {
            setFactory(this);
        }
        mHandler.sendEmptyMessage(FLAG_START_SCROLL);
    }
    /**
     * 建议在onPause中调用
     */
    public void stop(){
        mHandler.sendEmptyMessage(FLAG_STOP_SCROLL);
    }

    /**
     * 数据源发生变化后，从列表头开始重新播放
     */
    public void refresh(){
        mHandler.removeMessages(FLAG_START_SCROLL);
        currentIndex = 0;
        start();
    }



    private class ScrollHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case FLAG_START_SCROLL:
                    View view = getNextView();
                    mAdapter.bindView(view, mAdapter.getItem(currentIndex));
                    showNext();
                    currentIndex = ++currentIndex % mAdapter.getCount();
                    Log.e("lhl", "handleMessage: "+currentIndex);
                    mHandler.sendEmptyMessageDelayed(FLAG_START_SCROLL, mTimeSpan);
                    break;
                case FLAG_STOP_SCROLL:
                    mHandler.removeMessages(FLAG_START_SCROLL);
                    break;
                case FLAG_REFRESH:
                    currentIndex = 0;
                    removeAllViews();
                    start();
                    break;
            }
        }
    }
}
