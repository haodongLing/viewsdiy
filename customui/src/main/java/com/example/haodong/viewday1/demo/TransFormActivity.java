package com.example.haodong.viewday1.demo;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.haodong.common.util.LogUtil;
import com.example.haodong.viewday1.R;
import com.example.haodong.viewday1.demo.model.IndexEntryBean;
import com.example.haodong.viewday1.demo.model.IndexTopCentralEntity;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * date on 2021/4/14
 * author linghailong
 * email 105354999@qq.com
 */
public class TransFormActivity extends AppCompatActivity {
    ContentViewPager viewPager;
    ImageView bottom;
    ArrayList<IndexTopCentralEntity> page1 = new ArrayList<>();
    ArrayList<IndexTopCentralEntity> page2 = new ArrayList<>();
    private LinearLayout root_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transform);
        viewPager = findViewById(R.id.view_pager);
        bottom = findViewById(R.id.bottom);
        root_layout = findViewById(R.id.root_layout);
        Gson gson = new Gson();
        IndexEntryBean entity = gson.fromJson(AssetHelper.getJson("benneritem.json", this),
                IndexEntryBean.class);
        if (entity.topEntries != null) {
            List<IndexTopCentralEntity> datas = entity.topEntries;
            if (datas.size() > 5) {
                page1 = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    page1.add(datas.get(i));
                }
                for (int i = 5; i < datas.size(); i++) {
                    page2.add(datas.get(i));
                }
                ContentFragment contentFragment = ContentFragment.getInstance(page1);
                ContentFragment contentFragment1 = ContentFragment.getInstance(page2);
                ArrayList<Fragment> fragments = new ArrayList<>();
                fragments.add(contentFragment);
                fragments.add(contentFragment1);
                PreviewPagerAdapter previewPagerAdapter = new PreviewPagerAdapter(getSupportFragmentManager());
                previewPagerAdapter.addAll(fragments);
                viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                    @Override
                    public Fragment getItem(int i) {
                        return fragments.get(i);
                    }

                    @Override
                    public int getCount() {
                        return fragments.size();
                    }
                });
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int i, float v, int i1) {
                        LogUtil.i("i-->" + i + "  v-->" + v + "   i1-->" + i1);
                        if (i == 0) {
                            viewPager.setHeight((int) (250 + 570 * v));
                        } else {
                            viewPager.setHeight((int) (870- 570 * v));
                        }

                    }

                    @Override
                    public void onPageSelected(int i) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int i) {
                        LogUtil.i("i-->" + i);
                    }
                });
            }
        }

    }
}
