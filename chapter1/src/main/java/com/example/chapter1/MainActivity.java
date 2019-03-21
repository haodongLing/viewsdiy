package com.example.chapter1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
