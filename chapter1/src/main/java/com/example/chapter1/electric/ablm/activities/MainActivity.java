package com.example.chapter1.electric.ablm.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.chapter1.R;
import com.example.chapter1.SpiderView;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LinkedList<Object>linkedList=new LinkedList<>();
        SpiderView spiderView=new SpiderView(this);
      linkedList.add(spiderView);
    }
}
