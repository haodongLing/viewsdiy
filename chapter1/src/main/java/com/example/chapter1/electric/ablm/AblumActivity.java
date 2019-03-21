package com.example.chapter1.electric.ablm;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.chapter1.R;
import com.example.matisse.Matisse;
import com.example.matisse.MimeType;
import com.example.matisse.engine.impl.Glide4Engine;

/**
 * description:
 * author: linghailong
 * date: 2019/3/20
 */
public class AblumActivity extends AppCompatActivity {
    private Button btnAdd, btnPreview, btnSet;
    private GalleryFragment galleryFragment;
    private static final int REQUEST_CODE_CHOOSE = 23;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int hasReadStorage = ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission
                .READ_EXTERNAL_STORAGE);
        int hasWriteStorage = ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (hasReadStorage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .READ_EXTERNAL_STORAGE}, 23);
        }
        if (hasWriteStorage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 23);
        }
        setContentView(R.layout.activity_ablum);
        initView();
    }

    private void initView() {
        btnAdd = findViewById(R.id.btn_ablum_add);
        btnPreview = findViewById(R.id.btn_ablum_preview);
        btnSet = findViewById(R.id.btn_ablum_shezhi);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                galleryFragment = new GalleryFragment()
                        .setListener(new GalleryFragment.OnSelectedListener() {
                            @Override
                            public void onSelectedImage(String[] path) {
                            }
                        });
                galleryFragment.show(getSupportFragmentManager(), GalleryFragment.class.getName());
            }
        });
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matisse.from(AblumActivity.this)
                        .choose(MimeType.ofOnlyImage())
                        .theme(R.style.Matisse_Zhihu)
                        .countable(false)
                        .maxSelectable(9)
                        .originalEnable(true)
                        .maxOriginalSize(10)
                        .imageEngine(new Glide4Engine())
                        .forResult(REQUEST_CODE_CHOOSE);
            }
        });
    }

    // 隐藏软件盘
    private void hideSoftKeyboard() {
        // 当前焦点的View
        View view = getCurrentFocus();
        if (view == null)
            return;

        InputMethodManager imm = (InputMethodManager) getSystemService(Context
                .INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
