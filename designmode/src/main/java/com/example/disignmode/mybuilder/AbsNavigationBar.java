package com.example.disignmode.mybuilder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * description: 这个是导航栏的基类
 * author: linghaoDo
 * date: 2019/3/7
 */
public abstract class AbsNavigationBar implements INavigation{
    private Builder mBuilder;
    private View mNavigationBar;
    protected AbsNavigationBar (Builder builder){
        this.mBuilder=builder;
        createNavigationBar();
    }

    private void createNavigationBar() {
        mNavigationBar=LayoutInflater.from(mBuilder.mContext).inflate(mBuilder.mLayoutId,mBuilder
                .mParent,false);
    }

    /**
     * 构建参数
     */
    public static abstract class Builder{
        public Context mContext;
        public int mLayoutId;
        public ViewGroup mParent;
        public Builder(Context context, int layoutId,ViewGroup viewGroup){
            this.mContext=context;
            this.mLayoutId=layoutId;
            this.mParent=viewGroup;
        }
        public void build(){

        }



    }
    public abstract AbsNavigationBar create();

    /**
     * 参数存储
     */
    public static class NavigationParams{

    }
}
