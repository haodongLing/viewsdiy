package com.example.chapter1.electric.ablm;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/6/30
 * Description:
 */
public class TransFormationUtils extends ImageViewTarget<Bitmap> {
    private int width;
    private int height;
    private ImageView imageView;

    public TransFormationUtils(ImageView view) {
        super(view);
    }

    public TransFormationUtils(ImageView view, int width, int height) {
        super(view);
        this.imageView = view;
        this.width = width;
        this.height = height;
    }

    @Override
    protected void setResource(@Nullable Bitmap drawable) {
        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        if (width == -1) {
            float sy = height * 0.1f / drawable.getHeight()*0.1f ;
            params.width = (int) (sy * drawable.getWidth());
            params.height = height;
        } else if (height == -1) {
            params.width = width;
            float sy = width * 0.1f/ drawable.getWidth()*0.1f;
            params.height = (int) (sy * drawable.getHeight());
        } else if (width > 0 && height > 0) {
            params.width = width;
            params.height = height;
        }
        imageView.setLayoutParams(params);

    }
}
