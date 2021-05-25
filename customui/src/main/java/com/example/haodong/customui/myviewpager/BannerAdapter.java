package com.example.haodong.customui.myviewpager;

import android.view.View;

/**
 * created by linghaoDo on 2020-04-08
 * description:
 * <p>
 * version:
 */
public abstract class BannerAdapter {
    public abstract View getView(int position);

    /**
     * 获取轮播数量
     *
     * @return
     */
    public abstract int getCount();

    /**
     * @param mCurrentPosition
     * @return
     */
    public String getBannerDesc(int mCurrentPosition) {
        return "";
    }

}
