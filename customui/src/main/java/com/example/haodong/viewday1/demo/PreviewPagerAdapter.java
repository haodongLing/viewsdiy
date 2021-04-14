package com.example.haodong.viewday1.demo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class PreviewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> contentFragments = new ArrayList<>();


    public PreviewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {

        return contentFragments.get(position);
    }

    @Override
    public int getCount() {
        return contentFragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);

    }

    public void addAll(ArrayList<Fragment> items) {
        contentFragments.addAll(items);
    }


}
