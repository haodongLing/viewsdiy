package com.example.chapter1.electric.ablm.preview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.SlideImage;

import java.util.List;

/**
 * description:
 * author: linghailong
 * date: 2019/3/26
 */
public class PreviewAdapter implements IPreviewAdapter<SlideImage> {
    private Context mContext;
    private List<SlideImage>mDatas;

    public PreviewAdapter(Context mContext, List<SlideImage> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas!=null?mDatas.size():0;
    }

    @Override
    public SlideImage getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public View makeView() {
         View view=LayoutInflater.from(mContext).inflate(R.layout.item_preview_normal,null);
        return view;
    }

    @Override
    public void bindView(View view, SlideImage data) {
        ImageView img=view.findViewById(R.id.iv_first_normal);
        Glide.with(mContext)
                .load(data.getUri())
                .fitCenter()
                .into(img);


    }
}
