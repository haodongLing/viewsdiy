package com.example.haodong.viewday1.mynested;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * created by linghaoDo on 2019-11-27
 * description:
 * <p>
 * version:
 */
public class InventoryStatusAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public InventoryStatusAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    public InventoryStatusAdapter(@Nullable List<String> data) {
        super(data);
    }

    public InventoryStatusAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
