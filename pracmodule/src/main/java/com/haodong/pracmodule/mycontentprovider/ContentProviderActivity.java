package com.haodong.pracmodule.mycontentprovider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.haodong.pracmodule.R;

public class ContentProviderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
//        getContentResolver().query()
    }
}
