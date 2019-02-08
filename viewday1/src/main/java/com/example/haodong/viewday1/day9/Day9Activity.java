package com.example.haodong.viewday1.day9;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.haodong.viewday1.MainActivity;
import com.example.haodong.viewday1.R;

public class Day9Activity extends AppCompatActivity {
    TabLayout mTabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day9);
        mTabLayout=findViewById(R.id.tab_layout);
        mTabLayout.setmAdapter(new TabBaseAdapter() {
            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public View getView(int position, ViewGroup parent) {
                View view=LayoutInflater.from(Day9Activity.this).inflate(R.layout.item_tag,parent,false);
                return view;
            }
        });
    }
}
