package com.haodong.mycavas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BrokenLineView mBrokenLineView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBrokenLineView=findViewById(R.id.broken_line);
        List<Integer>list=new ArrayList<>();
        list.add(14);
        list.add(17);
        list.add(17);
        list.add(20);
        list.add(21);
        list.add(21);
        mBrokenLineView.setData(list);
    }
}
