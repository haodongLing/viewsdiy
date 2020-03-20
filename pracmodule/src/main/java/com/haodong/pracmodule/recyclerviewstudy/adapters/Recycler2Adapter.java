package com.haodong.pracmodule.recyclerviewstudy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.haodong.pracmodule.R;

import java.util.ArrayList;
import java.util.List;

/**
 * created by linghaoDo on 2020-03-20
 * description:
 * <p>
 * version:
 */
public class Recycler2Adapter extends RecyclerView.Adapter {
    private List<String> mData = new ArrayList<>();
    private Context mContext;

    public Recycler2Adapter(Context context, List data) {
        mData = data;
        mContext = context;
    }

    @NonNull
    @Override
    public Recycler2ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_text, viewGroup, false);
        return new Recycler2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((Recycler2ViewHolder) viewHolder).tv.setText(mData.get(i));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class Recycler2ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public Recycler2ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
