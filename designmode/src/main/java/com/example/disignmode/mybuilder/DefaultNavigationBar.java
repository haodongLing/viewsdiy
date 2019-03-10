package com.example.disignmode.mybuilder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.disignmode.R;

/**
 * describe : 默认样式的导航栏
 * date on 2019/3/11
 * author linghailong
 * email 105354999@qq.com
 */
public class DefaultNavigationBar  extends AbsNavigationBar<DefaultNavigationBar.Builder>{


    DefaultNavigationBar(Builder builder) {
        super(builder);
    }

    @Override
    public void attachNavigationParams() {
        super.attachNavigationParams();
        // 处理特有的
        TextView leftView = findViewById(R.id.back_tv);
        leftView.setVisibility(getBuilder().mLeftVisible);
    }

    public static class Builder extends AbsNavigationBar.Builder<DefaultNavigationBar.Builder>{
        public int mLeftVisible = View.VISIBLE;

        public Builder(Context context, int layoutId, ViewGroup viewGroup) {
            super(context, layoutId, viewGroup);
        }


        @Override
        public AbsNavigationBar create() {
            return null;
        }
        public Builder setLeftText(String text){
            setText(R.id.back_tv,text);
            return this;
        }

        public Builder setLeftClickListener(View.OnClickListener clickListener){
            setOnClickListener(R.id.back_tv,clickListener);
            return this;
        }

        public Builder hideLeftText() {
            mLeftVisible = View.INVISIBLE;
            return this;
        }
    }

}
