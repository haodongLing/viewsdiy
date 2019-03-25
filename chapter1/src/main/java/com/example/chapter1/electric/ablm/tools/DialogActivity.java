package com.example.chapter1.electric.ablm.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.Button;

import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.dialog.FullPreviewDialog;
import com.example.chapter1.electric.ablm.dialog.NormalPreviewDialog;

public class DialogActivity extends AppCompatActivity {
    private Button btnFull,btnNormal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        initView();
    }

    private void initView() {
        btnFull=findViewById(R.id.btn_full);
        btnNormal=findViewById(R.id.btn_normal);
        btnFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(new Intent(DialogActivity.this,FullPreviewDialog.class));
            }
        });
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DialogActivity.this,NormalPreviewDialog.class));
            }
        });

    }

}
