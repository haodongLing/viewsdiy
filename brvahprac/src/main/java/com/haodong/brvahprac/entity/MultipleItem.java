package com.haodong.brvahprac.entity;

import android.content.Context;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author linghailong
 * @date on 2018/10/30
 * @email 105354999@qq.com
 * @describe :
 */
public class MultipleItem implements MultiItemEntity {
    private int itemType;
    private int spanSize;
    private String content;

    public MultipleItem(int itemType, int spanSize) {
        this.itemType = itemType;
        this.spanSize = spanSize;
    }
    public MultipleItem(int itemType, int spanSize,String content) {
        this.itemType = itemType;
        this.spanSize = spanSize;
        this.content=content;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
