package com.example.haodong.viewday1.mynested;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.haodong.viewday1.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;

/**
 * created by linghaoDo on 2019-11-27
 * description:
 * <p>
 * version:
 */
public class InventoryStatusActivity extends AppCompatActivity {
    private SmartRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ArrayList<String> mData = new ArrayList<>();
    private InventoryStatusAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        initView();
        initData();

    }

    private void initData() {
        for (int i = 0; i < 40; i++) {
            mData.add("i=" + i);
        }
        mAdapter = new InventoryStatusAdapter(R.layout.item_inventory_status, mData);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.rv);
        mRefreshLayout = findViewById(R.id.srl);
//        mRefreshLayout.setEnableNestedScroll(true);


    }
}
