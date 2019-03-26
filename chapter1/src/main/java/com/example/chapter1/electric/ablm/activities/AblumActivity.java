package com.example.chapter1.electric.ablm;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.example.chapter1.MainActivity;
import com.example.chapter1.R;
import com.example.chapter1.electric.ablm.draghelper.ItemDragHelperCallback;
import com.example.matisse.Matisse;
import com.example.matisse.MimeType;
import com.example.matisse.engine.impl.Glide4Engine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * description:
 * author: linghailong
 * date: 2019/3/20
 */
public class AblumActivity extends AppCompatActivity {
    private Button btnAdd, btnPreview, btnSet;
    private GalleryFragment galleryFragment;
    private RecyclerView recyclerView;
    private SlideImageAdapter mSlideImgAdapter;
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
        /*BtnPreview*/
        btnPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSet = findViewById(R.id.btn_ablum_shezhi);
        btnAdd.setOnClickListener(new View.OnClickListener() {
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
        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        recyclerView = findViewById(R.id.ablum_recycler_view);
        GridLayoutManager manager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(manager);

        ItemDragHelperCallback callback = new ItemDragHelperCallback() {
            @Override
            public boolean isLongPressDragEnabled() {
                // 长按拖拽打开
                return true;
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);
    }

    private void initAdapter(ArrayList<SlideImage> slideImages) {
        if (mSlideImgAdapter == null) {
            mSlideImgAdapter = new SlideImageAdapter(AblumActivity.this, slideImages);
            recyclerView.setAdapter(mSlideImgAdapter);
        } else {
            mSlideImgAdapter.setSlideImageArr(slideImages);
            mSlideImgAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            List<Uri> uriList = Matisse.obtainResult(data);
            List<String> stringList = Matisse.obtainPathResult(data);
            initAdapter(getSlideArray(uriList, stringList));
        }
    }

    private ArrayList<SlideImage> getSlideArray(List<Uri> uriList, List<String> pathList) {
        ArrayList<SlideImage> arrayList = new ArrayList<>();
        int size = uriList.size();
        for (int i = 0; i < size; i++) {
            SlideImage slideImage = new SlideImage();
            slideImage.setPath(pathList.get(i));
            slideImage.setUri(uriList.get(i));
            arrayList.add(slideImage);
        }
        return arrayList;
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
