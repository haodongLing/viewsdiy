package com.example.chapter1.electric.ablm;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.recycler.RecyclerAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * description:
 * author: linghailong
 * date: 2019/3/20
 */
public class GalleryView extends RecyclerView {
    private static final int LOADER_ID = 0x0100;
    private static final int MIN_IMAGE_FILE_SIZE = 20 * 1024; // 最小的图片大小
    private static final int MAX_IMAGE_COUNT = 9; //最多能选择的图片数量
    private LoaderCallback mLoaderCallback = new LoaderCallback();
    private Adapter mAdapter = new Adapter();
    // 存放已选择的slideImage 需要设置position
    private List<SlideImage> mSelectedImages = new LinkedList<>();
    private SelectedChangeListener mListener;

    public GalleryView(@NonNull Context context) {
        this(context, null);
    }

    public GalleryView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GalleryView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setLayoutManager(new GridLayoutManager(getContext(), 3));
        setAdapter(mAdapter);
        mAdapter.setListener(new RecyclerAdapter.AdapterListenerImpl<SlideImage>() {
            @Override
            public void onItemClick(RecyclerAdapter.ViewHolder holder, SlideImage slideImage) {
                if (onItemSelectClick(slideImage)) {
                    holder.updateData(slideImage);
                }
            }
        });
    }

    /**
     * 初始化方法
     *
     * @param loaderManager Loader管理器
     * @return 任何一个LOADER_ID，可用于销毁Loader
     */
    public int setup(LoaderManager loaderManager, SelectedChangeListener listener) {
        mListener = listener;
        loaderManager.initLoader(LOADER_ID, null, mLoaderCallback);
        return LOADER_ID;
    }


    /**
     * slideImage是否被点击的逻辑
     * 注意：这时候涉及到数据位置的处理
     *
     * @param slideImage
     * @return
     */
    private boolean onItemSelectClick(SlideImage slideImage) {
        // 是否需要进行更新
        boolean notifyRefresh;
        if (mSelectedImages.contains(slideImage)) {
            // 如果之前在，现在就需要进行移除
            mSelectedImages.remove(slideImage);
            slideImage.setPosition(-1);
            slideImage.setSelected(false);
            // 状态改变后更新
            notifyRefresh = true;

        } else {
            if (mSelectedImages.size() >= MAX_IMAGE_COUNT) {
                // 得到提示文字
                String str = "至多选择%s张";
                // 格式化填充
                str = String.format(str, MAX_IMAGE_COUNT);
                Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
                notifyRefresh = false;
            } else {
                mSelectedImages.add(slideImage);
                slideImage.setPosition(mSelectedImages.size());
                slideImage.setSelected(true);
                notifyRefresh = true;
            }
        }
        // 如果数据有更改，
        // 那么我们需要通知外面的监听者我们的数据选中改变了
        if (notifyRefresh)
            notifySelectChanged();
        return true;
    }

    /**
     * 得到选中的图片的全部地址
     *
     * @return 返回一个数组
     */
    public String[] getSelectedPath() {
        String[] paths = new String[mSelectedImages.size()];
        int index = 0;
        for (SlideImage image : mSelectedImages) {
            paths[index++] = image.getPath();
        }
        return paths;
    }

    /**
     * 可以进行清空选中的图片
     */
    public void clear() {
        for (SlideImage image : mSelectedImages) {
            // 一定要先重置状态
            image.setSelected(false);
        }
        mSelectedImages.clear();
        // 通知更新
        mAdapter.notifyDataSetChanged();

        // 通知选中数量改变
        notifySelectChanged();

    }

    /**
     * 通知选中状态改变
     */
    private void notifySelectChanged() {
        // 得到监听者，并判断是否有监听者，然后进行回调数量变化
        SelectedChangeListener listener = mListener;
        if (listener != null) {
            listener.onSelectedCountChanged(mSelectedImages);
        }
    }


    /**
     * 图片的读取
     */
    private class LoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {
        private final String[] IMAGE_PROJECTION = new String[]{
                MediaStore.Images.Media._ID, // Id
                MediaStore.Images.Media.DATA, // 图片路径
                MediaStore.Images.Media.DATE_ADDED // 图片的创建时间ø
        };

        @NonNull
        @Override
        public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle bundle) {
            if (id == LOADER_ID) {
                return new CursorLoader(getContext(),
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        IMAGE_PROJECTION,
                        null,
                        null,
                        IMAGE_PROJECTION[2] + " DESC"); // 倒序查询
            }
            return null;
        }

        @Override
        public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
            List<SlideImage> images = new ArrayList<>();
            if (cursor != null) {
                int count = cursor.getCount();
                if (count > 0) {
                    cursor.moveToFirst();
                    int indexId = cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[0]);
                    int indexPath = cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[1]);
                    int indexDate = cursor.getColumnIndexOrThrow(IMAGE_PROJECTION[2]);
                    do {
                        int id = cursor.getInt(indexId);
                        String path = cursor.getString(indexPath);
                        long dateTime = cursor.getLong(indexDate);
                        File file = new File(path);
                        if (!file.exists() || file.length() < MIN_IMAGE_FILE_SIZE) {
                            continue;
                        }
                        /*这是相册显示的图片，所以不需要设置位置*/
                        SlideImage slideImage = new SlideImage();
                        slideImage.setId(id);
                        slideImage.setPath(path);
                        slideImage.setDate(dateTime);
                        images.add(slideImage);
                    } while (cursor.moveToNext());
                }
            }
            updateSource(images);
        }

        @Override
        public void onLoaderReset(@NonNull Loader<Cursor> loader) {
            // 当Loader销毁或者重置了, 进行界面清空
            updateSource(null);
        }
    }

    /**
     * 通知Adapter数据更改的方法
     *
     * @param images
     */
    private void updateSource(List<SlideImage> images) {
        mAdapter.replace(images);
    }

    /**
     * adapter
     */
    private class Adapter extends RecyclerAdapter<SlideImage> {

        @Override
        protected int getItemViewType(int position, SlideImage slideImage) {
            return R.layout.cell_galley;
        }

        @Override
        protected ViewHolder<SlideImage> onCreateViewHolder(View root, int viewType) {
            return new GalleryView.ViewHolder(root);
        }
    }

    /**
     * GalleryView的ViewHolder,放在一起便于维护
     */
    private class ViewHolder extends RecyclerAdapter.ViewHolder<SlideImage> {
        private ImageView mPic;
        private View mShadow;
        private CircleTextView mCircleTv;

        public ViewHolder(View itemView) {
            super(itemView);
            mPic = itemView.findViewById(R.id.im_image);
            mShadow = itemView.findViewById(R.id.view_shade);
            mCircleTv = itemView.findViewById(R.id.circle_tv);
        }

        @Override
        protected void onBind(SlideImage slideImage) {
            boolean isSlected = slideImage.isSelected;
            Glide.with(getContext())
                    .load(slideImage.getPath()) // 加载路径
                    .diskCacheStrategy(DiskCacheStrategy.NONE) // 不使用缓存，直接从原图加载
                    .centerCrop() // 居中剪切
                    .placeholder(R.color.grey_200) // 默认颜色
                    .into(mPic);
            mShadow.setVisibility(slideImage.isSelected ? VISIBLE : INVISIBLE);
            /*positon标志*/
            String s = "";
            int color = Color.parseColor("#30000000");

            if (isSlected && (slideImage.getPosition() != -1)) {
                s = slideImage.getPosition() + "";
                color = Color.parseColor("#1572fc");
            }
            mCircleTv.setCircleBackColor(color);
            mCircleTv.setText(s);
        }
    }

    /**
     * 对外的一个监听器
     */
    public interface SelectedChangeListener {
        void onSelectedCountChanged(List<SlideImage> slectedImages);
    }

}
