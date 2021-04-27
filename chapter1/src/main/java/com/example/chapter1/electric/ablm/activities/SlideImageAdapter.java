package com.example.chapter1.electric.ablm.activities;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.SlideImage;
import com.example.chapter1.electric.ablm.draghelper.OnDragVHListener;
import com.example.chapter1.electric.ablm.draghelper.OnItemMoveListener;

import java.util.ArrayList;

/**
 * description:
 * author: linghailong
 * date: 2019/3/22
 */
public class SlideImageAdapter extends RecyclerView.Adapter<SlideImageAdapter.SlideViewHolder>
        implements OnItemMoveListener {
    private Context mContext;
    private ArrayList<SlideImage> mSlideImageArr=new ArrayList<>();

    public SlideImageAdapter(Context mContext, ArrayList<SlideImage> slideImageArr) {
        this.mContext = mContext;
        this.mSlideImageArr = slideImageArr;
    }

    public ArrayList<SlideImage> getSlideImageArr() {
        return mSlideImageArr;
    }

    public void setSlideImageArr(ArrayList<SlideImage> slideImageArr) {
        if (mSlideImageArr.size()!=0){
            mSlideImageArr.clear();
        }
        this.mSlideImageArr = slideImageArr;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view =LayoutInflater.from(mContext).inflate(R.layout.slide_gallery,viewGroup,false);
        return new SlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SlideViewHolder slideViewHolder, final int position) {
        slideViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mSlideImageArr.remove(position);
                notifyDataSetChanged();
            }
        });
        Glide.with(mContext)
                .load(mSlideImageArr.get(position).getPath()) // 加载路径
                .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用缓存，直接从原图加载
                .centerCrop() // 居中剪切
                .placeholder(R.color.grey_200) // 默认颜色
                .into(slideViewHolder.imgPreview);
    }

    @Override
    public int getItemCount() {
        return mSlideImageArr.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        SlideImage imgFrom=mSlideImageArr.get(fromPosition);
        SlideImage imgTo=mSlideImageArr.get(toPosition);
        mSlideImageArr.set(fromPosition,imgTo);
        mSlideImageArr.set(toPosition,imgFrom);
        notifyItemMoved(fromPosition,toPosition);
    }

    class SlideViewHolder extends RecyclerView.ViewHolder implements OnDragVHListener{
        private ImageView imgDelete;
        private ImageView imgPreview;
        private ImageView imgShade;
        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDelete=itemView.findViewById(R.id.slide_item_delete);
            imgPreview=itemView.findViewById(R.id.slide_item_image);
            imgShade=itemView.findViewById(R.id.slide_item_shade);
        }

        @Override
        public void onItemSelected() {
            imgShade.setVisibility(View.VISIBLE);
        }

        @Override
        public void onItemFinish() {
            imgShade.setVisibility(View.INVISIBLE);
        }
    }

}
