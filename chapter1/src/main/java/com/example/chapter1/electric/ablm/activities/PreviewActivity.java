package com.example.chapter1.electric.ablm.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.SlideImage;
import com.example.chapter1.electric.ablm.preview.PreviewAdapter;
import com.example.chapter1.electric.ablm.preview.PreviewSwitcher;
import com.example.chapter1.electric.ablm.tools.UiTool;

import java.util.ArrayList;

/**
 * description:
 * author: linghailong
 * date: 2019/3/26
 */
public class PreviewActivity extends AppCompatActivity {

    private PreviewSwitcher mViewSwitcher;
    private FrameLayout mFirstLayout;
    private FrameLayout mScecondLayout;
    private ArrayList<SlideImage> mSlideArr = new ArrayList<>();
    private int mCurrentPage=0;
    private int mAllPages=0;
    private PreviewAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        initView();
        initListener();
        initData();
    }

    private void initView() {
        mSlideArr = getIntent().getParcelableArrayListExtra("slideArray");
        mAllPages=mSlideArr.size();
        mViewSwitcher=findViewById(R.id.preview_view_switcher);
        mAdapter=new PreviewAdapter(this,mSlideArr);
        mViewSwitcher.setmAdapter(mAdapter);
    }


    private void initData() {
       mViewSwitcher.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mViewSwitcher.stop();
    }

    private void initListener() {

    }
}
