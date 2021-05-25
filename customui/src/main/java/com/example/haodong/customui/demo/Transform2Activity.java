package com.example.haodong.customui.demo;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.haodong.customui.R;
import com.example.haodong.customui.demo.model.IndexEntryBean;
import com.example.haodong.customui.demo.model.IndexTopCentralEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * date on 2021/4/14
 * author Aaron
 * description:
 */
public class Transform2Activity extends AppCompatActivity {
    Transfrom2Adapter adapter;
    RecyclerView recyclerView;
    ArrayList<IndexTopCentralEntity> page1 = new ArrayList<>();
    ArrayList<IndexTopCentralEntity> page2 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfrom2);
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        Gson gson = new Gson();
        IndexEntryBean entity = gson.fromJson(AssetHelper.getJson("benneritem.json", this),
                IndexEntryBean.class);
        if (entity.topEntries != null) {
            List<IndexTopCentralEntity> datas = entity.topEntries;
            if (datas.size() > 5) {
                page1 = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    page1.add(datas.get(i));
                }
                for (int i = 5; i < datas.size(); i++) {
                    page2.add(datas.get(i));
                }
                List<List<IndexTopCentralEntity>> dataList = new ArrayList<>();
                dataList.add(page1);
                dataList.add(page2);

                adapter = new Transfrom2Adapter(R.layout.item_transform2, dataList);
                snapHelper.attachToRecyclerView(recyclerView);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adapter);
                recyclerView.setNestedScrollingEnabled(false);
                recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                        recyclerView.requestLayout();

                    }

                    @Override
                    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                    }
                });

            }
        }


    }

    static class Transfrom2Adapter extends BaseQuickAdapter<List<IndexTopCentralEntity>, BaseViewHolder> {

        public Transfrom2Adapter(int layoutResId, @Nullable List<List<IndexTopCentralEntity>> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, List<IndexTopCentralEntity> item) {
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 5);
            RecyclerView recyclerView = helper.getView(R.id.recycler);
            ContentFragment.ContentAdapter contentAdapter = new ContentFragment.ContentAdapter(R.layout.item_content
                    , item);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(contentAdapter);
        }
    }
}
