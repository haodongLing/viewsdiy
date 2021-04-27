package com.haodong.recyclerviewprac.demo02;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.haodong.recyclerviewprac.R;

import java.util.List;

/**
 * @author linghailong
 * @date on 2018/10/29
 * @email 105354999@qq.com
 * @describe :
 */
public class GridAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context mContext;
    private List<Meizi> datas;

    public GridAdapter(Context mContext, List<Meizi> datas) {
        this.mContext = mContext;
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       if (viewType==0){
           View view= LayoutInflater.from(mContext).inflate(R.layout.grid_meizi_item,parent,false);
           ViewHolder1 holder1=new ViewHolder1(view);
           return holder1;
       }
       else {
           MyViewHolder2 holder2=new MyViewHolder2(LayoutInflater.from(
                   mContext).inflate(R.layout.page_item, parent,
                   false));
           return holder2;
       }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder1){
            Glide.with(mContext).load(datas.get(position)).into(((ViewHolder1) holder).iv);
        }else if (holder instanceof MyViewHolder2){
            ((MyViewHolder2) holder).tv11.setText(datas.get(position).getPage()+"页");
        }

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 自定义View,用于加载图片
     */
    class ViewHolder1 extends RecyclerView.ViewHolder {
        private ImageButton iv;
        public ViewHolder1(View itemView) {
            super(itemView);
            iv = (ImageButton) itemView.findViewById(R.id.iv);
        }
    }

    /**
     * 自定义ViewHolder，用于显示页数
     */
    class MyViewHolder2 extends RecyclerView.ViewHolder {
        private TextView tv11;

        public MyViewHolder2(View view) {
            super(view);
            tv11 = (TextView) view.findViewById(R.id.tv11);
        }
    }


}
