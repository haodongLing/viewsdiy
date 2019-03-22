package com.example.chapter1.electric.ablm.tools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.chapter1.MainActivity;
import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.dialog.FullPreviewDialog;

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
               FullPreviewDialog fullPreviewDialog=new FullPreviewDialog(DialogActivity.this);
               fullPreviewDialog.create();
               fullPreviewDialog.show();
            }
        });
    }
}
