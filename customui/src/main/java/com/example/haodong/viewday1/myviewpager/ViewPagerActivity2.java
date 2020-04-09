package com.example.haodong.viewday1.myviewpager;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.haodong.viewday1.R;
import com.example.haodong.viewday1.day12.ScreenUtils;

public class ViewPagerActivity2 extends AppCompatActivity {
    private ViewPager mViewPager;
    int[] imgRes = {R.mipmap.img01, R.mipmap.img02, R.mipmap.img03, R.mipmap.img03, R.mipmap.img04};
    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return imgRes.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView view = new ImageView(ViewPagerActivity2.this);
            view.setImageResource(imgRes[position]);
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setPageMargin(ScreenUtils.dip2px(ViewPagerActivity2.this, 20));
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(true,new CustomTransform());
    }
}
