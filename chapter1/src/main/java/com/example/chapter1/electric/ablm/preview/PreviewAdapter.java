package com.example.chapter1.electric.ablm.preview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.chapter1.electric.ablm.SlideImage;
import com.example.chapter1.electric.ablm.recycler.RecyclerAdapter;

/**
 * description:
 * author: linghailong
 * date: 2019/3/26
 */
public class MyAdapter extends RecyclerAdapter<RecyclerView.ViewHolder> {
    @Override
    protected int getItemViewType(int position, RecyclerView.ViewHolder viewHolder) {
        return 0;
    }

    @Override
    protected ViewHolder<RecyclerView.ViewHolder> onCreateViewHolder(View root, int viewType) {
        return null;
    }

}
