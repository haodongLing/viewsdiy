package com.example.haodong.viewday1.day8;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.haodong.viewday1.R;

public class Day8Activity extends AppCompatActivity {
    private TextView tvDianji;
    private static final String TAG=Day8Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day8_acitivy);
        tvDianji=findViewById(R.id.tv_day8_dianji);
        Log.e(TAG, "onCreate: "+tvDianji.getMeasuredHeight() );
        tvDianji.post(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "run: "+tvDianji.getMeasuredHeight() );
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: "+tvDianji.getMeasuredHeight() );
    }
}
