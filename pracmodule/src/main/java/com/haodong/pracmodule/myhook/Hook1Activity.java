package com.haodong.pracmodule.myhook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.haodong.common.util.LogUtil;
import com.haodong.pracmodule.R;

public class Hook1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook1);
        ClassLoader classLoader = getClassLoader();
        if (classLoader != null) {
            LogUtil.i("classLoader-->" + classLoader.toString());
        }
        while (classLoader.getParent() != null) {
            classLoader = classLoader.getParent();
            LogUtil.i("classLoader-->" + classLoader.toString());
        }

    }
}
