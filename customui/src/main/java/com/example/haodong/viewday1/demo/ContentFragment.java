package com.example.haodong.viewday1.demo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.haodong.viewday1.R;
import com.example.haodong.viewday1.demo.model.IndexTopCentralEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * date on 2021/4/14
 * author Aaron
 * description:
 */
public class ContentFragment extends Fragment {
    private View view;
    private ArrayList<IndexTopCentralEntity> mData = new ArrayList<>();
    private RecyclerView recyclerView;


    public static ContentFragment getInstance(ArrayList<IndexTopCentralEntity> data) {
        ContentFragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_content, container, false);
            initView(view);
        }
        return view;

    }

    private void initView(View view) {
        mData = (ArrayList<IndexTopCentralEntity>) getArguments().getSerializable("data");
        recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 5);
        recyclerView.setLayoutManager(layoutManager);
        if (mData != null) {
            ContentAdapter contentAdapter = new ContentAdapter(R.layout.item_content, mData);
            recyclerView.setAdapter(contentAdapter);
        }

    }

    public static class ContentAdapter extends BaseQuickAdapter<IndexTopCentralEntity, BaseViewHolder> {

        public ContentAdapter(int layoutResId, @Nullable List<IndexTopCentralEntity> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, IndexTopCentralEntity item) {
            Glide.with(mContext).load(item.icon).into((ImageView) helper.getView(R.id.img));
            helper.setText(R.id.text, item.title);
        }
    }
}
