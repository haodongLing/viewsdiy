package com.example.haodong.customui.iphone;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.example.haodong.customui.R;

/**
 * describe:
 * created at 2019/2/21
 * Author linghailong
 */
public class IponeActivity extends AppCompatActivity {
    private HorizontalSlipView view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iphone_layout);
        view=findViewById(R.id.preview_layout_slide);
        view.setCanWork(true);
        view.setOnChildViewMovedListener(new HorizontalSlipView.OnChildViewMovedListener() {
            @Override
            public void onChildViewMove() {
                Log.e("tag", "onChildViewMove: " );
            }
        });
    }
}
