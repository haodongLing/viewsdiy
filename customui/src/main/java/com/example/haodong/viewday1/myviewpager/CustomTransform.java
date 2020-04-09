package com.example.haodong.viewday1.myviewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * created by linghaoDo on 2020-04-08
 * description:
 * <p>
 * version:
 */
public class CustomTransform implements ViewPager.PageTransformer {
    private static final float DEFAULT_MIN_ALPHA = 0.5f;
    private float mMinAlpha = DEFAULT_MIN_ALPHA;
    private float mMinScale = 0.8f;

    @Override
    public void transformPage(@NonNull View view, float v) {
        /*[,-1]*/

        if (v < -1) {
            view.setAlpha(mMinAlpha);
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);
        } else if (v <= 1) {

            if (v < 0) {
                /*(-1,0]*/
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + v);
                float scale=mMinScale+ (1 - mMinScale) * (1 + v);
                view.setAlpha(factor);
                view.setScaleX(scale);
                view.setScaleY(scale);


            } else {
                /*[0,1]*/
                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - v);
                view.setAlpha(factor);
                float scale=mMinScale+ (1 - mMinScale) * (1 - v);
                view.setScaleX(scale);
                view.setScaleY(scale);
            }

        } else {

            view.setAlpha(mMinAlpha);
            view.setScaleX(mMinScale);
            view.setScaleY(mMinScale);

        }
    }
}

