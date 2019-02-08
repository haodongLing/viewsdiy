package com.example.haodong.viewday1.day9;

import android.view.View;
import android.view.ViewGroup;

public abstract class TabBaseAdapter {
    public abstract int getCount();
    public abstract View getView(int position, ViewGroup parent);
    public void notifyDatasetChanged(){}

}
