package com.example.disignmode.mybuilder;

import android.content.Context;
import android.view.ViewGroup;

/**
 * description: 这是可以直接使用的
 * author: linghaoDo
 * date: 2019/3/7
 */
public class NavigationBar extends AbsNavigationBar {
    protected NavigationBar(Builder builder) {
        super(builder);
    }

    /**
     * 导航栏的Builder
     */
    public static class Builder extends AbsNavigationBar.Builder {
        public Builder(Context context, int layoutId, ViewGroup viewGroup) {
            super(context, layoutId, viewGroup);
        }

        @Override
        public AbsNavigationBar create() {
            /*this 传入自己的这个类*/
            return new NavigationBar(this);
        }
    }


}
