package com.example.chapter1.electric.ablm;

/**
 * description: 轮播图的数据结构
 * author: linghailong
 * date: 2019/3/21
 */
public class SlideImage {
    // 轮播顺序 如果是-1 就证明没有被赋值
    private int position=-1;
    // 数据id
    private int id;
    // 图片的创建日期 降序排序使用
    private long date;
    // 图片的路径
    private String path;
    // 是否被选中
    boolean isSelected;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SlideImage image = (SlideImage) o;

        return path != null ? path.equals(image.path) : image.path == null;
    }

    @Override
    public int hashCode() {
        return path != null ? path.hashCode() : 0;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
