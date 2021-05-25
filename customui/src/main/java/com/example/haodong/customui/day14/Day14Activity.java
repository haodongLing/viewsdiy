package com.example.haodong.customui.day14;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.haodong.customui.R;

import java.util.ArrayList;
import java.util.List;

public class Day14Activity extends AppCompatActivity {
    private MyListView mListView;
    private List<String> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day14);
        mListView = findViewById(R.id.list_view);
        mItems = new ArrayList<String>();

        for (int i = 0; i < 200; i++) {
            mItems.add("i -> " + i);
        }

        mListView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return mItems.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView item = (TextView) LayoutInflater.from(Day14Activity.this)
                        .inflate(R.layout.item_lv, parent, false);
                item.setText(mItems.get(position));
                return item;
            }
        });
    }
}
