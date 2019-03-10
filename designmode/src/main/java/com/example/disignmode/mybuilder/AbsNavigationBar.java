package com.example.disignmode.mybuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * description: 这个是导航栏的基类
 * author: linghaoDo
 * date: 2019/3/7
 */
public abstract class AbsNavigationBar<B extends AbsNavigationBar.Builder> implements INavigation {
    private B mBuilder;
    private View mNavigationBar;

    AbsNavigationBar(B builder) {
        this.mBuilder = builder;
        createNavigationBar();
    }

    @Override
    public void createNavigationBar() {
        mNavigationBar = LayoutInflater.from(mBuilder.mContext).inflate(mBuilder.mLayoutId, mBuilder
                .mParent, false);
        /*创建完之后是添加*/
        attachParent(mNavigationBar, mBuilder.mParent);
        /*添加到父布局之后是增加属性（绑定参数）*/
        attachNavigationParams();
    }

    /**
     * @return 返回Builder
     */
    public B getBuilder() {
        return mBuilder;
    }

    @Override
    public void attachNavigationParams() {
        /*设置文本*/
        Map<Integer, CharSequence> textMaps = mBuilder.mTextMaps;
        /*遍历map*/
        for (Map.Entry<Integer, CharSequence> entry : textMaps.entrySet()) {
            TextView textView = findViewById(entry.getKey());
            textView.setText(entry.getValue());
        }
        // 设置点击事件
        Map<Integer, View.OnClickListener> clickListenerMaps = mBuilder.mClickMaps;
        for (Map.Entry<Integer, View.OnClickListener> entry : clickListenerMaps.entrySet()) {
            View view = findViewById(entry.getKey());
            view.setOnClickListener(entry.getValue());
        }
    }

    public <T extends View> T findViewById(int viewId) {
        /*必须是View的findViewById*/
        return mNavigationBar.findViewById(viewId);
    }


    /**
     * 构建参数
     */
    public static abstract class Builder<B extends Builder> {
        public Context mContext;
        public int mLayoutId;
        public ViewGroup mParent;
        /*参数保存*/
        public Map<Integer, CharSequence> mTextMaps;
        public Map<Integer, View.OnClickListener> mClickMaps;


        public Builder(Context context, int layoutId, ViewGroup viewGroup) {
            this.mContext = context;
            this.mLayoutId = layoutId;
            this.mParent = viewGroup;
            mTextMaps = new HashMap<>();
            mClickMaps = new HashMap<>();
        }

        /**
         * 设置文本
         *
         * @param viewId
         * @param text
         * @return
         */
        public B setText(int viewId, String text) {
            mTextMaps.put(viewId, text);
            return (B) this;
        }

        /**
         * 设置点击事件
         *
         * @param viewId
         * @param clickListener
         * @return
         */
        public B setOnClickListener(int viewId, View.OnClickListener clickListener) {
            mClickMaps.put(viewId, clickListener);
            return (B) this;
        }


        public abstract AbsNavigationBar create();


    }

    /**
     * 参数存储
     */
    public static class NavigationParams {

    }

    /**
     * 将navigationBar添加到父布局
     */
    @Override
    public void attachParent(View navigationBar, ViewGroup parent) {
        parent.addView(navigationBar, 0);
    }
}
