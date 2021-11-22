package com.haodong.study.pracarouter;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/9/29
 * Description:
 */
@Route(path =Constance.ACTIVITY_THIRD)
public class ThirdActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
