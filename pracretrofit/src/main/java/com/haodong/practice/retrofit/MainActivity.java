package com.haodong.practice.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        count(3,5);
    }

    public int count(int a, int b) {
        final int c = a + b;
        return c;
    }
}
