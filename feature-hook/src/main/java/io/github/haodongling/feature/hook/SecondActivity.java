package io.github.haodongling.feature.hook;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * Author: tangyuan
 * Time : 2021/9/29
 * Description:
 */
public class SecondActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
