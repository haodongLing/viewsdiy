package com.example.disignmode.mydecoration.simple3;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 对RecyclerView.Adapter进行的功能扩展
 * describe:
 * created at 2019/3/13
 * Author linghailong
 */
public class WrapRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final RecyclerView.Adapter<RecyclerView.ViewHolder> mRealAdapter;
    ArrayList<View> mHeaderViews;
    ArrayList<View> mFooterViews;

    public WrapRecyclerAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> mRealAdapter) {
        /*原来的recyclerView的Adapter,并不支持头部以及底部的添加*/
        this.mRealAdapter = mRealAdapter;
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        /*需要知道真实的位置才能知道是哪个ViewType*/
        /*头部返回头部的ViewHolder*/
        int numHeaders = getHeaderCount();
        /*position是从0开始的，而numHeader是size*/
        if (position < numHeaders) {
            return createHeaderFooterViewHolder(mHeaderViews.get(position));
        }
        final int adjPosition = position - numHeaders;
        /*真实的返回真实的ViewHolder*/
        int adapterCount = 0;
        if (mRealAdapter != null) {
            adapterCount = mRealAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                // 直接传 position ,不兼容 万能适配多布局条目
                return mRealAdapter.onCreateViewHolder(viewGroup, mRealAdapter.getItemViewType
                        (adjPosition));
            }
        }
        /*底部返回底部的ViewHolder*/
        // 底部返回 底部的ViewHolder
        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return createHeaderFooterViewHolder(mFooterViews.get(adjPosition - adapterCount));
    }

    @Override
    public int getItemViewType(int position) {
        /*返回位置，直接把位置作为ViewType*/
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        // 头部和底部是都不需要做处理的，只要 mRealAdapter 要去做处理
        int numHeaders = getHeaderCount();
        if (position< numHeaders) {
            return ;
        }

        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mRealAdapter != null) {
            adapterCount = mRealAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mRealAdapter.onBindViewHolder(holder,position);
            }
        }
    }

    private RecyclerView.ViewHolder createHeaderFooterViewHolder(View view) {
        return new RecyclerView.ViewHolder(view) {
        };
    }


    @Override
    public int getItemCount() {
        return mFooterViews.size() + mHeaderViews.size() + mRealAdapter.getItemCount();
    }

    public void addHeaderView(View view) {
        if (!mHeaderViews.contains(view)) {
            mHeaderViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void addFooterView(View view) {
        if (!mFooterViews.contains(view)) {
            mFooterViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void removeHeaderView(View view) {
        if (mHeaderViews.contains(view)) {
            mHeaderViews.remove(view);
            notifyDataSetChanged();
        }
    }

    public void removeFooterView(View view) {
        if (mFooterViews.contains(view)) {
            mFooterViews.remove(view);
            notifyDataSetChanged();
        }
    }

    public int getHeaderCount() {
        return mHeaderViews.size();
    }

    public int getFooterCount() {
        return mFooterViews.size();
    }
}
