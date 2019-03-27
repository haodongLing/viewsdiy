package com.example.chapter1.electric.ablm.preview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.SlideImage;
import com.example.chapter1.electric.ablm.activities.PreviewActivity;
import com.example.chapter1.electric.ablm.tools.Blur;
import com.example.chapter1.electric.ablm.tools.UiTool;

import java.util.List;

/**
 * description:
 * author: linghailong
 * date: 2019/3/26
 */
public class PreviewAdapter implements IPreviewAdapter<SlideImage> {
    private Context mContext;
    private List<SlideImage> mDatas;


    public PreviewAdapter(Context mContext, List<SlideImage> mDatas) {
        this.mContext = mContext;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    @Override
    public SlideImage getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public View makeView() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_preview_normal, null);
        return view;
    }

    @Override
    public void bindView(View view, SlideImage data) {
        ImageView imgNormal = view.findViewById(R.id.iv_first_normal);
        ImageView imgFull = view.findViewById(R.id.iv_first_full);
        int i = data.getId();
        String path = data.getPath();
        /*第一种情况 长大于宽并且是小图*/
        Log.e("lhl", "bindView:--->adapter "+i );
        switch (i) {
            case 0:
                Glide.with(mContext)
                        .load(data.getUri())
                        .into(imgFull);
                imgNormal.setVisibility(View.INVISIBLE);
                imgFull.setVisibility(View.VISIBLE);
                imgFull.setAlpha(1f);
                break;
            case 1:
                /*第二种情况：需要背景*/
                Log.e("lhl", "bindView:--->width >height ");
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                Blur blur = new Blur(mContext, bitmap);
                Bitmap newBmp = blur.fastBlur(20);
                newBmp=new Blur(mContext,newBmp).fastBlur(20);
                Glide.with(mContext)
                        .load(newBmp)
                        .into(imgFull);
                Glide.with(mContext)
                        .load(data.getUri())
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(imgNormal);
                imgNormal.setVisibility(View.VISIBLE);
                imgNormal.setScaleType(ImageView.ScaleType.FIT_XY);
                imgFull.setVisibility(View.VISIBLE);
                break;
            case 2:
                Log.e("lhl", "bindView: ");
                Glide.with(mContext)
                        .load(data.getUri())
                        .into(imgFull);
                imgNormal.setVisibility(View.INVISIBLE);
                imgFull.setVisibility(View.VISIBLE);
                imgFull.setAlpha(1f);
                break;
        }
    }

}
