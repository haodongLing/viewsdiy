package com.example.haodong.customui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.haodong.customui.adapter.HorizontalAdapter;
import com.example.haodong.customui.databinding.ActivityHorizontalRecyclerViewBinding;
import com.example.haodong.customui.horizontalrefreshlayout.HorizontalRefreshLayout;
import com.example.haodong.customui.horizontalrefreshlayout.RefreshCallBack;
import com.example.haodong.customui.horizontalrefreshlayout.refreshhead.LoadingRefreshHeader;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.horizontal.RefreshContentHorizontal;
import com.scwang.smartrefresh.horizontal.SmartRefreshHorizontal;
import com.scwang.smartrefresh.layout.impl.RefreshFooterWrapper;

import java.util.ArrayList;
import java.util.List;

public class HorizontalRecyclerViewActivity extends AppCompatActivity implements RefreshCallBack {
    ActivityHorizontalRecyclerViewBinding mBinding;
    List<String> mData = new ArrayList<>();
    HorizontalRefreshLayout refreshLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_horizontal_recycler_view);
        mBinding.setLifecycleOwner(this);
        if (mData != null) {
            mData.clear();
        }
        for (int i = 0; i < 10; i++) {
            mData.add("第" + i + "个view");
        }
        refreshLayout = (HorizontalRefreshLayout) findViewById(R.id.refresh);
        refreshLayout.setRefreshCallback(this);
        refreshLayout.setRefreshHeader(new LoadingRefreshHeader(this), HorizontalRefreshLayout.RIGHT);

        mBinding.recycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        mBinding.recycler.setAdapter(new HorizontalAdapter(mData));

        mBinding.verticalRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mBinding.verticalRecycler.setAdapter(new HorizontalAdapter(mData));
    }

    @Override
    public void onLeftRefreshing() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.onRefreshComplete();
            }
        }, 2000);
    }

    @Override
    public void onRightRefreshing() {
        refreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshLayout.onRefreshComplete();
            }
        }, 2000);
    }
}